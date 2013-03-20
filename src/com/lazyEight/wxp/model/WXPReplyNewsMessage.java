package com.lazyEight.wxp.model;

import java.util.ArrayList;

public class WXPReplyNewsMessage extends WXPReplyMessage {
	private int articleAmount;
	private ArrayList<WXPArticle> articles;
	public ArrayList<WXPArticle> getArticles() {
		return articles;
	}
	public void setArticles(ArrayList<WXPArticle> articles) {
		this.articles = articles;
	}
	public int getArticleAmount() {
		return articleAmount;
	}
	public void setArticleAmount(int articleAmount) {
		this.articleAmount = articleAmount;
	}
}
