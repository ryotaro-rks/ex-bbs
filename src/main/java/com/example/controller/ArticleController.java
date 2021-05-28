package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ArticleService;
import com.example.service.CommentService;

@Controller
@RequestMapping("/bbs")
public class ArticleController {
	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService commentService;

	@RequestMapping("")
	public String index() {

	}
}
