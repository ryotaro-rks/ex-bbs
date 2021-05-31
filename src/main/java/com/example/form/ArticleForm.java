package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 記事用フォームクラス.
 * 
 * @author ryotaro.seya
 *
 */
public class ArticleForm {
	/** 名前 */
	@NotBlank(message = "名前が入力されていません")
	@Size(min = 1, max = 20, message = "名前は1文字以上20文字以下で入力してください")
	private String name;
	/** 内容 */
	@NotBlank(message = "投稿内容が入力されていません")
	@Size(min = 1, max = 140, message = "投稿内容は1文字以上140文字以下で入力してください")
	private String content;

	@Override
	public String toString() {
		return "Article [name=" + name + ", content=" + content + "]";
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

}
