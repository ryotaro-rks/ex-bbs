package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;

/**
 * 記事用リポジトリクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Repository
public class ArticleRepository {
	private final static RowMapper<Article> ARTICLE_ROW_MAPPER = new BeanPropertyRowMapper<>(Article.class);

	// TODO: 演習6 で使うクラス
	// ResultSetExtractor

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

//	public List<> findAllArticleAndComment() {
//		String sql = "select a.id a_id, a.name a_name, a.content a_content, c.id c_id, c.name c_name, c.content c_content, c.article_id c_article_id from "
//				+ ARTICLE_TABLE_NAME + " as a left outer join " + COMMENT_TABLE_NAME
//				+ " as c on a.id = c.article_id order by id desc";
//		return template.query(sql, ARTICLE_ROW_MAPPER);
//	}

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
