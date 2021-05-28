package com.example.form;

/**
 * 記事用フォームクラス.
 * 
 * @author ryotaro.seya
 *
 */
public class ArticleForm {
	/** 名前 */
	private String name;
	/** 内容 */
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
