package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Article;
import com.example.repository.ArticleRepository;

/**
 * 記事用サービスクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Service
@Transactional
public class ArticleService {
	@Autowired
	private ArticleRepository articleService;

	/**
	 * 全記事情報を表示.
	 * 
	 * @return 全記事情報
	 */
	public List<Article> showAllArticle() {
		return articleService.findAll();
	}

	/**
	 * 記事投稿.
	 * 
	 * @param article 投稿する記事情報.
	 */
	public void postArticle(Article article) {
		articleService.insert(article);
	}

	/**
	 * 指定したID番号の記事を削除.
	 * 
	 * @param id 削除対象ID
	 */
	public void deleteArticleByid(int id) {
		articleService.deleteById(id);
	}
}
