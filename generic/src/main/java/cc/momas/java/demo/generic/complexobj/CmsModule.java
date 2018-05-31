package cc.momas.java.demo.generic.complexobj;

import java.util.Date;

public class CmsModule {

	private Integer id;
	private Date createTime;
	private Date updateTime;
	private Integer lastOperatorId;
	private Integer appId;
	private String name;
	private String title;
	private Integer postListId;
	private Short type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getLastOperatorId() {
		return lastOperatorId;
	}

	public void setLastOperatorId(Integer lastOperatorId) {
		this.lastOperatorId = lastOperatorId;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPostListId() {
		return postListId;
	}

	public void setPostListId(Integer postListId) {
		this.postListId = postListId;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
}
