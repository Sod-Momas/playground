package cc.momas.java.demo.generic.complexobj;

import java.io.Serializable;
import java.util.Date;

public class DPostView implements Serializable {
	private static final long serialVersionUID = 1L;
	private long postID;
	private String title;
	private String detail;
	private String[] images;
	private int score;
	private String scoreTxt;
	private int hit;
	private int commentCount;
	private int notice;
	private int weight;
	private int isGood;
	private Date createTime;
	private Date activeTime;
	private int line;
	private Object ext;
	private int tagid;
	private int status;
	private int praise;
	private String voice;
	private int isAuthention;
	private byte isRich;
	private byte appOrientation;
	private int isAppPost;
	private String appVersion;
	private float appSize;
	private String appSystem;
	private String appLogo;
	private String[] screenshots;
	private String appIntroduce;
	private String appUrl;
	private String appLanguage;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public long getPostID() {
		return postID;
	}

	public void setPostID(long postID) {
		this.postID = postID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getScoreTxt() {
		return scoreTxt;
	}

	public void setScoreTxt(String scoreTxt) {
		this.scoreTxt = scoreTxt;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getNotice() {
		return notice;
	}

	public void setNotice(int notice) {
		this.notice = notice;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getIsGood() {
		return isGood;
	}

	public void setIsGood(int isGood) {
		this.isGood = isGood;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public int getIsAuthention() {
		return isAuthention;
	}

	public void setIsAuthention(int isAuthention) {
		this.isAuthention = isAuthention;
	}

	public byte getIsRich() {
		return isRich;
	}

	public void setIsRich(byte isRich) {
		this.isRich = isRich;
	}

	public byte getAppOrientation() {
		return appOrientation;
	}

	public void setAppOrientation(byte appOrientation) {
		this.appOrientation = appOrientation;
	}

	public int getIsAppPost() {
		return isAppPost;
	}

	public void setIsAppPost(int isAppPost) {
		this.isAppPost = isAppPost;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public float getAppSize() {
		return appSize;
	}

	public void setAppSize(float appSize) {
		this.appSize = appSize;
	}

	public String getAppSystem() {
		return appSystem;
	}

	public void setAppSystem(String appSystem) {
		this.appSystem = appSystem;
	}

	public String getAppLogo() {
		return appLogo;
	}

	public void setAppLogo(String appLogo) {
		this.appLogo = appLogo;
	}

	public String[] getScreenshots() {
		return screenshots;
	}

	public void setScreenshots(String[] screenshots) {
		this.screenshots = screenshots;
	}

	public String getAppIntroduce() {
		return appIntroduce;
	}

	public void setAppIntroduce(String appIntroduce) {
		this.appIntroduce = appIntroduce;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppLanguage() {
		return appLanguage;
	}

	public void setAppLanguage(String appLanguage) {
		this.appLanguage = appLanguage;
	}
}
