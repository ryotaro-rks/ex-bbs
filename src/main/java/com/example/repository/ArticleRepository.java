package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * 記事用リポジトリクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Repository
public class ArticleRepository {
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

	private static Comment getCommentFromResultSet(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		comment.setId(rs.getInt("c_id"));
		comment.setName(rs.getString("c_name"));
		comment.setContent(rs.getString("c_content"));
		comment.setArticleId(rs.getInt("c_article_id"));
		return comment;
	}

	// TODO: 演習6 で使うクラス
	private final static ResultSetExtractor<List<Article>> ARTICLE_SET_EXTRACTOR = (rs) -> {
		List<Article> articleList = new ArrayList<>();
		List<Comment> commentList = new ArrayList<>();
		int beforeArticleId = 0;
		Article article = null;

		while (rs.next()) {
			// 新しい記事
			if (rs.getInt("a_id") != beforeArticleId) {
				if (beforeArticleId != 0) {
					article.setCommentList(commentList);
					articleList.add(article);
				}

				// 記事情報セット
				article = new Article();
				article.setId(rs.getInt("a_id"));
				article.setName(rs.getString("a_name"));
				article.setContent(rs.getString("a_content"));

				// コメントは初期化
				/*
				 * WARNING: if (rs.getInt("c_id") != 0) の中で初期化すると、 もし現在の記事にコメントがなかった場合、
				 * ひとつ前の記事のコメントが現在の記事に反映されてしまう.
				 */
				commentList = new ArrayList<>();
				// コメントが存在した場合
				if (rs.getInt("c_id") != 0) {
					commentList.add(getCommentFromResultSet(rs));
				}
			}
			// 一行前と同じ記事, コメント追加
			else {
				commentList.add(getCommentFromResultSet(rs));
			}
			beforeArticleId = rs.getInt("a_id");
		}
		article.setCommentList(commentList);
		System.out.println(article);
		articleList.add(article);

		return articleList;
	};

	private final static String ARTICLE_TABLE_NAME = "articles";
	private final static String COMMENT_TABLE_NAME = "comments";
	private final static String ALL_COLUMN_NAME = "id, name, content";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 全記事情報の取得.
	 * 
	 * @return 全記事情報(idの降順)
	 */
	public List<Article> findAll() {
		String sql = "select " + ALL_COLUMN_NAME + " from " + ARTICLE_TABLE_NAME + " order by id desc";
		return template.query(sql, ARTICLE_ROW_MAPPER);
	}

	public List<Article> findAllArticleAndComment() {
		String sql = "select a.id a_id, a.name a_name, a.content a_content, c.id c_id, c.name c_name, c.content c_content, c.article_id c_article_id from "
				+ ARTICLE_TABLE_NAME + " as a left outer join " + COMMENT_TABLE_NAME
				+ " as c on a.id = c.article_id order by a.id desc, c_id desc";
		return template.query(sql, ARTICLE_SET_EXTRACTOR);
	}

	/**
	 * 記事投稿.
	 * 
	 * @param article 記事内容
	 */
	public void insert(Article article) {
		String sql = "insert into " + ARTICLE_TABLE_NAME + "(name, content) values (:name, :content)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", article.getName()).addValue("content",
				article.getContent());
		template.update(sql, param);
	}

	/**
	 * IDで指定した記事を削除.
	 * 
	 * @param id 削除対象id
	 */
	public void deleteById(int id) {
		String sql = "delete from " + ARTICLE_TABLE_NAME + " where id = " + id;
		SqlParameterSource param = new MapSqlParameterSource();
		template.update(sql, param);
	}
}
