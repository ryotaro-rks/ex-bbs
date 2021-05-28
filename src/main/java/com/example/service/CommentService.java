package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Comment;
import com.example.repository.CommentRepository;

/**
 * コメント用サービスクラス.
 * 
 * @author ryotaro.seya
 *
 */
@Service
@Transactional
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * 記事IDに対応するコメント情報一覧を取得.
	 * 
	 * @param articleId 記事ID
	 * @return 記事IDに対応するコメント情報
	 */
	public List<Comment> showAllComment(Integer articleId) {
		return commentRepository.findByArticleId(articleId);
	}

	/**
	 * コメント投稿.
	 * 
	 * @param comment コメント内容
	 */
	public void insert(Comment comment) {
		commentRepository.insert(comment);
	}

	/**
	 * 指定した記事IDに付与されているコメントを削除
	 * 
	 * @param articleId 記事ID
	 */
	public void deleteCommentByArticleId(int articleId) {
		commentRepository.deleteByArticleId(articleId);
	}
}
