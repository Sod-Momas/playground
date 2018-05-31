package cc.momas.java.demo.generic.simple.data;

import java.util.Date;
import java.util.List;

/**
 * 最终具体数据模型
 */
public class Model {

	private CommunityView communityView; // 交流区
	private List<ModelView> modelViewList; // 普通区列表
	private String nightMode = ""; // 夜间模式

	private Integer id;
	private Date createTime;
	private Date updateTime;
	private Integer creatorId;
	private Integer updaterId;
	private Integer appId;
	private String name;
	private Integer categoryId;
	private Byte enabled;




}
