package com.stackoverflow.blackberry.model;

import java.util.Calendar;
import java.util.Date;

import com.stackoverflow.json.JSONArray;
import com.stackoverflow.json.JSONException;
import com.stackoverflow.json.JSONObject;
import com.stackoverflow.observer.Observable;

/**
 * 
 * @author michael
 *
 */
public class User extends Observable {
	private int id;
	private String userType;		//"moderator"
	private Date creationDate;	//1217514151
	private String displayName;		//"Jeff Atwood"
	private int reputation; 		//12880
	private String emailHash;		//51d623f33f8b83095db84ff35e15dbe8
	private int age;				//39
	private Date lastAccessDate;	//1270437003
	private String websiteUrl;		//"http://www.codinghorror.com/blog/"
	private String location; 		//El Cerrito, CA";
	private String aboutMe; 		//<img src=\"http://img377.imageshack.us/img377/4074/wargames1xr6.jpg\" width=\"220\">\r\n<br>\r\n<br>\r\n<a href=\"http://www.codinghorror.com/blog/archives/001169.html\" rel=\"nofollow\">Stack Overflow Valued Associate #00001</a>\r\n",
	private int questionCount;		//15
	private int answerCount;		//163
	private int viewCount;			//24959
	private int upVoteCount;		//2019
	private int downVoteCount;		//432
	private int acceptRate;			//100
	
	private static final String KEY_ID = "user_id";
	private static final String KEY_ANSWER_COUNT = "answer_count";
	private static final String KEY_DISPLAY_NAME = "display_name";
	private static final String KEY_CREATION_DATE = "creation_date";
	private static final String KEY_LAST_ACCESS_DATE = "last_access_date";
	private static final String KEY_AGE = "age";
	private static final String KEY_ABOUT_ME = "about_me";
	private static final String KEY_EMAIL_HASH = "email_hash";
	private static final String KEY_USER_TYPE = "user_type";
	private static final String KEY_REPUTATION = "reputation";
	private static final String KEY_QUESTION_COUNT = "question_count";
	private static final String KEY_WEBSITE_URL = "website_url";
	private static final String KEY_VIEW_COUNT = "view_count";
	private static final String KEY_LOCATION = "location";
	private static final String KEY_UP_VOTE_COUNT = "up_vote_count";
	private static final String KEY_DOWN_VOTE_COUNT = "down_vote_count";
	
	public User() {
		
	}

	public void parseJson(JSONObject json) throws JSONException {
		this.id = Integer.parseInt((String)json.get(KEY_ID));
		this.answerCount = Integer.parseInt((String)json.get(KEY_ANSWER_COUNT));
		this.displayName = (String)json.get(KEY_DISPLAY_NAME);
		this.location = (String)json.get(KEY_LOCATION);
		this.emailHash = (String)json.get(KEY_EMAIL_HASH);
		this.websiteUrl = (String)json.get(KEY_WEBSITE_URL);
		this.aboutMe = (String)json.get(KEY_ABOUT_ME);
		this.reputation = Integer.parseInt((String)json.get(KEY_REPUTATION));
		this.userType = (String)json.get(KEY_USER_TYPE);
		this.questionCount = Integer.parseInt((String)json.get(KEY_QUESTION_COUNT));
		this.upVoteCount = Integer.parseInt((String)json.get(KEY_UP_VOTE_COUNT));
		this.downVoteCount = Integer.parseInt((String)json.get(KEY_DOWN_VOTE_COUNT));
		this.viewCount = Integer.parseInt((String)json.get(KEY_VIEW_COUNT));
		this.creationDate = 
			new Date(1000*Long.parseLong((String)json.get(KEY_CREATION_DATE)));
		this.lastAccessDate = 
			new Date(1000*Long.parseLong((String)json.get(KEY_LAST_ACCESS_DATE)));
		try {
			this.age = Integer.parseInt((String)json.get(KEY_AGE));
		} catch (NumberFormatException e) {
			this.age = 0;
		}
	}

	public String getLastAccessDateString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getLastAccessDate());
		
		String lastAccessDate = 
			cal.get(Calendar.YEAR) + "/" +
			cal.get(Calendar.MONTH) + "/"+ 
			cal.get(Calendar.DAY_OF_MONTH) + " " + 
			cal.get(Calendar.HOUR_OF_DAY) + ":" + 
			cal.get(Calendar.MINUTE) + ":" + 
			cal.get(Calendar.SECOND);
		return lastAccessDate;
	}
	
	public String getCreationDateString() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCreationDate());
		
		String creationDate = 
			cal.get(Calendar.YEAR) + "/" +
			cal.get(Calendar.MONTH) + "/"+ 
			cal.get(Calendar.DAY_OF_MONTH);
		return creationDate;
	}
	
	public int getId() {
		return id;
	}

	public String getUserType() {
		return userType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int getReputation() {
		return reputation;
	}

	public String getEmail_hash() {
		return emailHash;
	}

	public int getAge() {
		return age;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public String getLocation() {
		return location;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public int getQuestionCount() {
		return questionCount;
	}

	public int getAnswerCount() {
		return answerCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public int getUp_voteCount() {
		return upVoteCount;
	}

	public int getDown_voteCount() {
		return downVoteCount;
	}

	public int getAcceptRate() {
		return acceptRate;
	}
	
	
}
