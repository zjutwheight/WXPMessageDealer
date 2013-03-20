package com.lazyEight.wxp.protocol;

import java.util.ArrayList;

import com.lazyEight.wxp.model.WXPArticle;
import com.lazyEight.wxp.model.WXPReplyMessage;
import com.lazyEight.wxp.model.WXPReplyMusicMessage;
import com.lazyEight.wxp.model.WXPReplyNewsMessage;
import com.lazyEight.wxp.model.WXPReplyTextMessage;

public class WXPReplyMessageEncapsulator {
	// message general constants
	private static final String HEAD_XML = "<xml>";
	// private static final int HEAD_XML_LENGTH = 5;
	private static final String TAIL_XML = "</xml>";

	private static final String HEAD_CDATA = "<![CDATA[";
	// private static final int HEAD_CDATA_LEN = 9;
	private static final String TAIL_CDATA = "]]>";

	private static final String HEAD_TO_USER_NAME = "<ToUserName>";
	// private static final int HEAD_TO_USER_NAME_LEN = 12;
	private static final String TAIL_TO_USER_NAME = "</ToUserName>";

	private static final String HEAD_FROM_USER_NAME = "<FromUserName>";
	// private static final int HEAD_FROM_USER_NAME_LEN = 14;
	private static final String TAIL_FROM_USER_NAME = "</FromUserName>";

	private static final String HEAD_CREATE_TIME = "<CreateTime>";
	// private static final int HEAD_CREATE_TIME_LEN = 12;
	private static final String TAIL_CREATE_TIME = "</CreateTime>";

	private static final String HEAD_MESSAGE_TYPE = "<MsgType>";
	// private static final int HEAD_MESSAGE_TYPE_LEN = 9;
	private static final String TAIL_MESSAGE_TYPE = "</MsgType>";

	// reply message general constants
	private static final String HEAD_FUNCTION_FLAG = "<FuncFlag>";
	// private static final int HEAD_FUNCTIOIN_FLAG = 10;
	private static final String TAIL_FUNCTION_FLAG = "</FuncFlag>";

	// other reply message constants
	private static final String HEAD_CONTENT = "<Content>";
	// private static final int HEAD_CONTENT_LEN = 9;
	private static final String TAIL_CONTENT = "</Content>";

	private static final String HEAD_TITLE = "<Title>";
	// private static final int HEAD_TITLE_LEN = 7;
	private static final String TAIL_TITLE = "</Title>";
	private static final String HEAD_DESCRIPTION = "<Description>";
	// private static final int HEAD_DESCRIPTION_LEN = 13;
	private static final String TAIL_DESCRIPTION = "</Description>";
	private static final String HEAD_MUSIC_URL = "<MusicUrl>";
	// private static final int HEAD_MUSIC_URL_LEN = 10;
	private static final String TAIL_MUSIC_URL = "</MusicUrl>";
	private static final String HEAD_HQ_MUSIC_URL = "<HQMusicUrl>";
	// private static final int HEAD_HQ_MUSIC_URL_LEN = 12;
	private static final String TAIL_HQ_MUSIC_URL = "</HQMusicUrl>";

	private static final String HEAD_ARTICLE_COUNT = "<ArticleCount>";
	// private static final int HEAD_ARTICLE_COUNT_LEN = 14;
	private static final String TAIL_ARTICLE_COUNT = "</ArticleCount>";
	private static final String HEAD_ARTICLES = "<Articles>";
	// private static final int HEAD_ACTICLES_LEN = 10;
	private static final String TAIL_ARTICLES = "</Articles>";
	private static final String HEAD_ITEM = "<Item>";
	// private static final int HEAD_ITEM_LEN = 6;
	private static final String TAIL_ITEM = "</Item>";
	private static final String HEAD_PICTURE_URL = "<PicUrl>";
	// private static final int HEAD_PICTURE_URL =8;
	private static final String TAIL_PICTURE_URL = "</PicUrl>";
	private static final String HEAD_ARTICLE_URL = "<Url>";
	// private static final int HEAD_ARTICLE_URL = 5;
	private static final String TAIL_ARTICLE_URL = "</Url>";

	// encapsulate reply text message content
	public String encapsulateReplyTextMessage(WXPReplyTextMessage textMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(textMessage);
		String textMessageContent = encapsulateReplyTextMessageContent(textMessage
				.getContent());

		messageContent.append(generalContent);
		messageContent.append(textMessageContent);
		return encapsulateReplyMessage(messageContent.toString());
	}

	// encapsulate reply music message content
	public String encapsulateReplyMusicMessage(WXPReplyMusicMessage musicMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(musicMessage);
		String title = encapsulateReplyMessageTitle(musicMessage.getTitle());
		String description = encapsulateReplyMessageDescription(musicMessage
				.getDescription());
		String musicURL = encapsulateReplyMusicMessageMusicURL(musicMessage
				.getMusicURL());
		String HQMusicURL = encapsulateReplyMusicMessageHQMusicURL(musicMessage
				.getHQMusicURL());

		messageContent.append(generalContent);
		messageContent.append(title);
		messageContent.append(description);
		messageContent.append(musicURL);
		messageContent.append(HQMusicURL);
		return encapsulateReplyMessage(messageContent.toString());
	}

	// encapsulate reply news message content
	public String encapsulateReplyNewsMessage(WXPReplyNewsMessage newsMessage) {
		StringBuffer messageContent = new StringBuffer();
		String generalContent = encapsulateReplyMessageGeneralContent(newsMessage);
		String articleCount = encapsulateReplyNewsMessageArticleCount(newsMessage
				.getArticleAmount());
		String articles = encapsulateReplyNewsMessageActicles(newsMessage.getArticles());
		
		messageContent.append(generalContent);
		messageContent.append(articleCount);
		messageContent.append(articles);
		return encapsulateReplyMessage(messageContent.toString());
	}

	// encapsulate general reply message content
	private String encapsulateReplyMessageGeneralContent(WXPReplyMessage message) {
		StringBuffer generalContent = new StringBuffer();
		String toUserName = encapsulateReplyMessageToUserName(message
				.getToUserId());
		String fromUserName = encapsulateReplyMessageFromUserName(message
				.getFromUserId());
		String createTime = encapsulateReplyMessageCreateTime(message
				.getCreateTime());
		String messageType = encapsulateReplyMessageMsgType(message
				.getMessageType());
		String functionFlag = encapsulateReplyMessageFunctionFlag(message
				.getFuncFlag());

		generalContent.append(toUserName);
		generalContent.append(fromUserName);
		generalContent.append(createTime);
		generalContent.append(messageType);
		generalContent.append(functionFlag);
		return generalContent.toString();
	}

	// append head, content ,tail string together
	private String strAppend(String content, String head, String tail) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(head);
		strBuffer.append(content);
		strBuffer.append(tail);
		return strBuffer.toString();
	}

	private String encapsulateReplyMessageCDATA(String CDATA) {
		return strAppend(CDATA, HEAD_CDATA, TAIL_CDATA);
	}

	private String encapsulateReplyMessage(String messageContent) {
		return strAppend(messageContent, HEAD_XML, TAIL_XML);
	}

	private String encapsulateReplyMessageToUserName(String toUserName) {
		String toUserNameContent = encapsulateReplyMessageCDATA(toUserName);
		return strAppend(toUserNameContent, HEAD_TO_USER_NAME,
				TAIL_TO_USER_NAME);
	}

	private String encapsulateReplyMessageFromUserName(String fromUserName) {
		String fromUserNameContent = encapsulateReplyMessageCDATA(fromUserName);
		return strAppend(fromUserNameContent, HEAD_FROM_USER_NAME,
				TAIL_FROM_USER_NAME);
	}

	private String encapsulateReplyMessageCreateTime(long createTime) {
		String strCreateTime = Long.toString(createTime);
		return strAppend(strCreateTime, HEAD_CREATE_TIME, TAIL_CREATE_TIME);
	}

	private String encapsulateReplyMessageMsgType(String messageType) {
		String messageTypeContent = encapsulateReplyMessageCDATA(messageType);
		return strAppend(messageTypeContent, HEAD_MESSAGE_TYPE,
				TAIL_MESSAGE_TYPE);
	}

	private String encapsulateReplyMessageFunctionFlag(int funcFlag) {
		String strFuncFlag = Integer.toString(funcFlag);
		return strAppend(strFuncFlag, HEAD_FUNCTION_FLAG, TAIL_FUNCTION_FLAG);
	}

	private String encapsulateReplyTextMessageContent(String content) {
		String contentBody = encapsulateReplyMessageCDATA(content);
		return strAppend(contentBody, HEAD_CONTENT, TAIL_CONTENT);
	}

	private String encapsulateReplyMessageTitle(String title) {
		String titleContent = encapsulateReplyMessageCDATA(title);
		return strAppend(titleContent, HEAD_TITLE, TAIL_TITLE);
	}

	private String encapsulateReplyMessageDescription(String description) {
		String descriptionContent = encapsulateReplyMessageCDATA(description);
		return strAppend(descriptionContent, HEAD_DESCRIPTION, TAIL_DESCRIPTION);
	}

	private String encapsulateReplyMusicMessageMusicURL(String musicURL) {
		String musicURLContent = encapsulateReplyMessageCDATA(musicURL);
		return strAppend(musicURLContent, HEAD_MUSIC_URL, TAIL_MUSIC_URL);
	}

	private String encapsulateReplyMusicMessageHQMusicURL(String HQMusicURL) {
		String HQMusicURLContent = encapsulateReplyMessageCDATA(HQMusicURL);
		return strAppend(HQMusicURLContent, HEAD_HQ_MUSIC_URL,
				TAIL_HQ_MUSIC_URL);
	}

	private String encapsulateReplyNewsMessageArticleCount(int articleCount) {
		String strArticleCount = Integer.toString(articleCount);
		return strAppend(strArticleCount, HEAD_ARTICLE_COUNT,
				TAIL_ARTICLE_COUNT);
	}
	
	private String encapsulateReplyNewsMessageActicles(ArrayList<WXPArticle> articles) {
		StringBuffer articlesContent = new StringBuffer();
		for(WXPArticle article: articles) {
			String articleContent = encapsulateReplyNewsMessageArticle(article);
			articlesContent.append(articleContent);
		}
		return strAppend(articlesContent.toString(), HEAD_ARTICLES, TAIL_ARTICLES);
	}
	
	private String encapsulateReplyNewsMessageArticle(WXPArticle article) {
		StringBuffer articleContent = new StringBuffer();
		String title = encapsulateReplyMessageTitle(article.getTitle());
		String description = encapsulateReplyMessageDescription(article.getDesciprtion());
		String pictureURL = encapsulateReplyNewsMessagePictureURL(article.getPictureURL());
		String articleURL = encapsulateReplyNewsMessageArticleURL(article.getArticleURL());
		
		articleContent.append(title);
		articleContent.append(description);
		articleContent.append(pictureURL);
		articleContent.append(articleURL);
		return strAppend(articleContent.toString(), HEAD_ITEM, TAIL_ITEM);
	}
	
	private String encapsulateReplyNewsMessagePictureURL(String pictureURL) {
		String pictureURLContent = encapsulateReplyMessageCDATA(pictureURL);
		return strAppend(pictureURLContent, HEAD_PICTURE_URL, TAIL_PICTURE_URL);
	}
	
	private String encapsulateReplyNewsMessageArticleURL(String articleURL) {
		String articleURLContent = encapsulateReplyMessageCDATA(articleURL);
		return strAppend(articleURLContent, HEAD_ARTICLE_URL, TAIL_ARTICLE_URL);
	}

}