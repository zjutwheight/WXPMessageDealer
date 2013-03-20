package com.lazyEight.wxp.demo;

import java.util.ArrayList;

import com.lazyEight.wxp.model.*;
import com.lazyEight.wxp.protocol.*;

public class MessageDemo {
	public static void main(String[] args) {
		MessageDemo myTester = new MessageDemo();
		myTester.testParse();
		myTester.testEncapsulate();
	}

	public void testParse() {
		WXPReceivedMessageParser parser = new WXPReceivedMessageParser();
		String textMessageContent = "<xml><ToUserName><![CDATA[aaaaaa]]></ToUserName><FromUserName><![CDATA[bbbbb]]></FromUserName><CreateTime>1363684853</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[中文啊中文]]></Content><MsgId>5856981845685567529</MsgId></xml>";
		String imageMessageContent = "<xml><ToUserName><![CDATA[aaaaaa]]></ToUserName><FromUserName><![CDATA[bbbbb]]></FromUserName><CreateTime>1363681470</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[http://mmsns.qpic.cn/mmsns/ibysnTWMsiaDjgq2gb4Lw6tIS3VhWxqiabT1hd4Js8NQ5RkGpTia0k3R0A/0]]></PicUrl><MsgId>5856967315811205159</MsgId></xml>";
		String locationMessageContent = "<xml><ToUserName><![CDATA[aaaaaa]]></ToUserName><FromUserName><![CDATA[bbbbb]]></FromUserName><CreateTime>1363681532</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>29.889172</Location_X><Location_Y>121.634674</Location_Y><Scale>20</Scale><Label><![CDATA[]]></Label><MsgId>5856967582099177512</MsgId></xml>";

		testParseReceivedTextMessage(parser, textMessageContent);
		testParseReceivedImageMessage(parser, imageMessageContent);
		testParseReceivedLocatioinMessage(parser, locationMessageContent);
	}
	private void testParseReceivedTextMessage(WXPReceivedMessageParser parser,
			String textMessageContent) {
		WXPReceivedTextMessage textMessage = parser
				.parseWXPReceivedTextMessage(textMessageContent);
		System.out.println("text message content:\n" + textMessage.getContent());
	}
	private void testParseReceivedImageMessage(WXPReceivedMessageParser parser,
			String imageMessageContent) {
		WXPReceivedImageMessage imageMessage = parser
				.parseWXPReceivedImageMessage(imageMessageContent);
		System.out.println("image message picture url:\n" + imageMessage.getPictureURL());
	}
	private void testParseReceivedLocatioinMessage(WXPReceivedMessageParser parser,
			String locationMessageContent) {
		WXPReceivedLocationMessage locationMessage = parser
				.parseWXPReceivedLocatioinMessage(locationMessageContent);
		System.out.println("location message latitude and longitude:\n" + locationMessage.getLatitude() + ","
				+ locationMessage.getLongitude());
	}
	
	public void testEncapsulate() {
		WXPReplyMessageEncapsulator encapsulator = new WXPReplyMessageEncapsulator();
		// test reply text message
		WXPReplyTextMessage textMessage = new WXPReplyTextMessage();
		textMessage.setFromUserId("aaaa");
		textMessage.setToUserId("bbb");
		textMessage.setCreateTime(100000L);
		textMessage.setMessageType("text");
		textMessage.setContent("test content test content!");
		textMessage.setFuncFlag(1);
		System.out.println(encapsulator.encapsulateReplyTextMessage(textMessage));
		// test reply music message
		WXPReplyMusicMessage musicMessage = new WXPReplyMusicMessage();
		musicMessage.setFromUserId("aaaa");
		musicMessage.setToUserId("bbbb");
		musicMessage.setCreateTime(200000L);
		musicMessage.setMessageType("music");
		musicMessage.setFuncFlag(0);
		musicMessage.setTitle("Say Goodbye");
		musicMessage.setDescription("Say GoodBye!\nSay GoodBye\n");
		musicMessage.setMusicURL("http://www.baidu.com");
		musicMessage.setHQMusicURL("http://www.baidu.com");
		System.out.println(encapsulator.encapsulateReplyMusicMessage(musicMessage));
		// test reply news message
		WXPReplyNewsMessage newsMessage = new WXPReplyNewsMessage();
		newsMessage.setFromUserId("aaaa");
		newsMessage.setToUserId("bbbb");
		newsMessage.setCreateTime(200000L);
		newsMessage.setMessageType("news");
		newsMessage.setFuncFlag(0);
		newsMessage.setArticleAmount(3);
		ArrayList<WXPArticle> articles = new ArrayList<WXPArticle> ();
		for(int i = 1; i <= 3; i++) {
			WXPArticle article = new WXPArticle();
			article.setArticleURL("http://www.baidu.com");
			article.setPictureURL("http://www.baidu.com");
			article.setTitle("news" + i);
			article.setDesciprtion("news description " +i);
			articles.add(article);
		}
		newsMessage.setArticles(articles);
		System.out.println(encapsulator.encapsulateReplyNewsMessage(newsMessage));
	}
}
