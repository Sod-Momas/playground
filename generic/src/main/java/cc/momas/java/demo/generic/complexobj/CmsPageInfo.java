package cc.momas.java.demo.generic.complexobj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsPageInfo {
	// 普通模块
	public List<CmsModuleView> moduleViews = new ArrayList<>(0);
	// 交流区
	public List<DPostView> postViews = new ArrayList<>(0);
	// 夜间模式
	public String nightMode = "";

	// 复制自com.huluxia.tool.server.dataaccess.entity.hlx_tools.CmsPage
	public Integer id;
	public Date createTime;
	public Date updateTime;
	public Integer creatorId;
	public Integer updaterId;
	public Integer appId;
	public String name;
	public Integer categoryId;
	public Byte enabled;

	public CmsPageInfo() {
	}

	public CmsPageInfo(CmsPage page) {
		this.id = page.getId();
		this.createTime = page.getCreateTime();
		this.updateTime = page.getUpdateTime();
		this.creatorId = page.getCategoryId();
		this.updaterId = page.getUpdaterId();
		this.appId = page.getAppId();
		this.name = page.getName();
		this.categoryId = page.getCategoryId();
		this.enabled = page.getEnabled();
	}

	public List<CmsModuleView> getModuleViews() {
		return moduleViews;
	}

	public List<DPostView> getPostViews() {
		return postViews;
	}

	public String getNightMode() {
		return nightMode;
	}

	public Integer getId() {
		return id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public Integer getUpdaterId() {
		return updaterId;
	}

	public Integer getAppId() {
		return appId;
	}

	public String getName() {
		return name;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public Byte getEnabled() {
		return enabled;
	}
}
