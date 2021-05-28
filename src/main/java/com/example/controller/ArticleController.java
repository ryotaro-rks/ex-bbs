package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.service.ArticleService;
import com.example.service.CommentService;

/**
 * 記事用コントローラクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Controller
@RequestMapping("/bbs")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	/**
	 * @return 掲示板ページへのフォワード
	 */
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleService.showAllArticle();

		for (Article article : articleList) {
			article.setCommentList(commentService.showAllComment(article.getId()));
		}

		articleList.forEach(System.out::println);

		model.addAttribute("articleList", articleList);

		return "bbs";
	}

	/**
	 * 記事投稿
	 * 
	 * @return 掲示板ページ
	 */
	@RequestMapping("post")
	public String post(Model model) {
		return index(model);
	}
}
