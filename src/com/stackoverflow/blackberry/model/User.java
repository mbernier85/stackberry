package com.stackoverflow.blackberry.model;

/**
 * 
 * @author michael
 *
 */
public class User {
	private int id;
	private String userType;		//"moderator"
	private String creationDate;	//1217514151
	private String displayName;		//"Jeff Atwood"
	private int reputation; 		//12880
	private String emailHash;		//51d623f33f8b83095db84ff35e15dbe8
	private int age;				//39
	private String lastAccessDate;	//1270437003
	private String websiteUrl;		//"http://www.codinghorror.com/blog/"
	private String location; 		//El Cerrito, CA";
	private String aboutMe; 		//<img src=\"http://img377.imageshack.us/img377/4074/wargames1xr6.jpg\" width=\"220\">\r\n<br>\r\n<br>\r\n<a href=\"http://www.codinghorror.com/blog/archives/001169.html\" rel=\"nofollow\">Stack Overflow Valued Associate #00001</a>\r\n",
	private int questionCount;		//15
	private int answerCount;		//163
	private int viewCount;			//24959
	private int upVoteCount;		//2019
	private int downVoteCount;		//432
	private int acceptRate;			//100
	
	public User() {
		
	}


	/**
	 * @param id
	 * @param userType
	 * @param creationDate
	 * @param displayName
	 * @param reputation
	 * @param emailHash
	 * @param age
	 * @param lastAccessDate
	 * @param websiteUrl
	 * @param location
	 * @param aboutMe
	 * @param questionCount
	 * @param answerCount
	 * @param viewCount
	 * @param upVoteCount
	 * @param downVoteCount
	 * @param acceptRate
	 */
	public User(final int id, final String userType, final String creationDate,
			final String displayName, final int reputation, final String emailHash, final int age,
			final String lastAccessDate, final String websiteUrl, final String location,
			final String aboutMe, final int questionCount, final int answerCount, int viewCount,
			final int upVoteCount, final int downVoteCount, final int acceptRate) {
		this.id = id;
		this.userType = userType;
		this.creationDate = creationDate;
		this.displayName = displayName;
		this.reputation = reputation;
		this.emailHash = emailHash;
		this.age = age;
		this.lastAccessDate = lastAccessDate;
		this.websiteUrl = websiteUrl;
		this.location = location;
		this.aboutMe = aboutMe;
		this.questionCount = questionCount;
		this.answerCount = answerCount;
		this.viewCount = viewCount;
		this.upVoteCount = upVoteCount;
		this.downVoteCount = downVoteCount;
		this.acceptRate = acceptRate;
	}


	public int getId() {
		return id;
	}

	public String getUserType() {
		return userType;
	}

	public String getCreationDate() {
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

	public String getLastAccessDate() {
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
