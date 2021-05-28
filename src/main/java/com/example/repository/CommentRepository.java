package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

/**
 * コメント用リポジトリクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Repository
public class CommentRepository {
	private final static RowMapper<Comment> COMMENT_ROW_MAPPER = new BeanPropertyRowMapper<>(Comment.class);

	private final static String TABLE_NAME = "comments";
	private final static String ALL_COLUMN_NAME = "id, name, content, article_id";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 記事IDに一致するコメント情報の取得.
	 * 
	 * @return 記事IDに一致するコメント情報
	 */
	public List<Comment> findByArticleId(int articleId) {
		String sql = "select " + ALL_COLUMN_NAME + " from " + TABLE_NAME
				+ " where article_id = :articleId order by id desc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		return template.query(sql, param, COMMENT_ROW_MAPPER);
	}
}
