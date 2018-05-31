package cc.momas.java.demo.generic.complexobj;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestMain {

	private static final TestMain THIS = new TestMain();
	private Gson gson = new Gson();

	public static void main(String[] args) {
//		THIS.test1();
		THIS.test2();
	}

	private void test2() {
		CmsPageInfo info = getPageInfo();
		String json = JsonHlx.toJson(info);
		CmsPageInfo info1 = JsonHlx.parseJsonObject(json,CmsPageInfo.class);
		System.out.println(info.equals(info1));
		Object obj = ((Map) info1.moduleViews.get(0).list.get(0)).get("createTime");
		System.out.println(obj.getClass().getSimpleName());
	}

	private void test1() {
		CmsPageInfo info = getPageInfo();
		String json = gson.toJson(info);
		CmsPageInfo info1 = gson.fromJson(json,CmsPageInfo.class);
		boolean flag = info.equals(info1);
		System.out.println(flag);
	}

	private CmsPage getPage() {
		CmsPage page = new CmsPage();
		page.setAppId(2222);
		page.setCategoryId(8);
		page.setCreateTime(new Date());
		page.setCreatorId(3345);
		page.setEnabled((byte) 1);
		return page;
	}

	private CmsPageInfo getPageInfo() {
		CmsPageInfo info = new CmsPageInfo(getPage());
		info.nightMode = "1";
		info.postViews = findPostView();
		info.moduleViews = findModuleView();
		return info;
	}

	private List<DPostView> findPostView() {
		List<DPostView> list = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			list.add(getPostView());
		}
		return list;
	}

	private List<CmsModuleView> findModuleView() {
		List<CmsModuleView> list = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			list.add(getModuleView());
		}
		return list;
	}

	private CmsModuleView getModuleView() {
		CmsModuleView moduleView = new CmsModuleView();
		moduleView.setAppId(2222);
		moduleView.setCreateTime(new Date());
		moduleView.setId(1);
		moduleView.setName("name");
		moduleView.setPostListId(23);
		moduleView.setTitle("title");
		moduleView.list = findModuleViewItem();
		return moduleView;
	}

	private List<? extends Object> findModuleViewItem() {
		if (System.currentTimeMillis() % 2 == 0) {
			return findTopic();
		} else {
			return findHotWord();
		}
	}

	private List<CmsTopic> findTopic() {
		List<CmsTopic> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(getTopic());
		}
		return list;
	}

	private CmsTopic getTopic() {
		CmsTopic topic = new CmsTopic();
		topic.setRefId(1);
		topic.setImgUrl("imageurl");
		topic.setId(1);
		topic.setCreateTime(new Date());
		topic.setModuleId(222);
		topic.setName("topic");
		return topic;
	}

	private List<CmsHotword> findHotWord() {
		List<CmsHotword> list = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			list.add(getHotWord());
		}
		return list;
	}

	private CmsHotword getHotWord() {
		CmsHotword hotword = new CmsHotword();
		hotword.setTitle("hotword");
		hotword.setModuleId(4);
		hotword.setCreateTime(new Date());
		hotword.setFontColor("blue");
		return hotword;
	}

	private DPostView getPostView() {
		DPostView view = new DPostView();
		view.setImages(new String[]{"image1", "image2", "image3"});
		view.setActiveTime(new Date());
		view.setCreateTime(new Date());
		view.setHit(100);
		view.setCommentCount(100);
		return view;
	}
}
