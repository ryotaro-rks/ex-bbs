package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * コメント用フォームクラス.
 * 
 * @author ryotaro.seya
 *
 */
public class CommentForm {
	/** 名前 */
	@NotBlank(message = "名前が入力されていません")
	@Size(min = 1, max = 20, message = "名前は1文字以上20文字以下で入力してください")
	private String name;
	/** 内容 */
	@NotBlank(message = "コメントが入力されていません")
	@Size(min = 1, max = 140, message = "コメントは1文字以上140文字以下で入力してください")
	private String content;
	/** 記事ID */
	private Integer articleId;

	@Override
	public String toString() {
		return "Comment [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
}
