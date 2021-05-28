package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
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
	 * 掲示板ページにアクセス.
	 * 
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
	 * 記事投稿.
	 * 
	 * @param articleForm 記事用フォーム
	 * @return 掲示板ページ
	 */
	@RequestMapping("post")
	public String post(ArticleForm articleForm) {
		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleService.postArticle(article);
		return "redirect:/bbs";
	}

	/**
	 * コメント投稿.
	 * 
	 * @param commentForm コメント用フォーム
	 * @return 掲示板ページ
	 */
	@RequestMapping("comment")
	public String comment(CommentForm commentForm) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		commentService.insert(comment);
		return "redirect:/bbs";
	}
}
