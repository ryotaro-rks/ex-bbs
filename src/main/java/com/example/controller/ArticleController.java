package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}

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
//		List<Article> articleList = articleService.showAllArticle();
//
//		for (Article article : articleList) {
//			article.setCommentList(commentService.showAllComment(article.getId()));
//		}

		List<Article> articleList = articleService.showAllArticleAndComment();

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
	public String post(@Validated ArticleForm articleForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			return index(model);
		}

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
	public String comment(@Validated CommentForm commentForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("errorArticleId", commentForm.getArticleId());
			return index(model);
		}

		Comment comment = new Comment();
		BeanUtils.copyProperties(commentForm, comment);
		commentService.insert(comment);
		return "redirect:/bbs";
	}

	/**
	 * 指定したIDの記事の削除
	 * 
	 * @param articleId 削除する記事ID
	 * @return 掲示板ページ
	 */
	@RequestMapping("deleteArticle")
	public String deleteArticle(Integer articleId) {
		commentService.deleteCommentByArticleId(articleId);
		articleService.deleteArticleByid(articleId);
		return "redirect:/bbs";
	}
}
