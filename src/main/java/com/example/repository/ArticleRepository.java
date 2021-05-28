package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

	private final static String TABLE_NAME = "articles";
	private final static String ALL_COLUMN_NAME = "id, name, content";

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 全記事情報の取得.
	 * 
	 * @return 全記事情報(idの降順)
	 */
	public List<Article> findAll() {
		String sql = "select " + ALL_COLUMN_NAME + " from " + TABLE_NAME + " order by id desc";
		return template.query(sql, ARTICLE_ROW_MAPPER);
	}
}
