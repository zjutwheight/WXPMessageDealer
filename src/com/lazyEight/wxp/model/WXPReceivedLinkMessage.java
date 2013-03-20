package com.lazyEight.wxp.model;

public class WXPReceivedLinkMessage extends WXPReceivedMessage {
	private String title;
	private String description;
	private String linkURL;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
}
