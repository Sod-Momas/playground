package cc.momas.java.demo.web.action.vip;

import cc.momas.java.demo.entity.Vip;
import cc.momas.java.demo.web.action.BaseAction;

import java.util.Arrays;
import java.util.List;

public class VipAction extends BaseAction {

	private List<Vip> vips;

	private Long userId;


	@Override
	public String execute() throws Exception {
		if(userId == null){
			return LOGIN;
		}
		this.vips = selectData();
		return SUCCESS;
	}

	/**
	 * vip 特权页面
	 *
	 * @return
	 */
	public String privilege() {
		return SUCCESS;
	}

	/**
	 * vip 福利页面
	 *
	 * @return
	 */
	public String benefits() {
		return SUCCESS;
	}

	private List<Vip> selectData() {
		List<Vip> list = Arrays.asList(
				new Vip(1, "初级vip"),
				new Vip(2, "中级vip"),
				new Vip(3, "高级vip")
		);
		return list;
	}

	// ======  getter and  setter  ===== //
	public List<Vip> getVips() {
		return vips;
	}

	public void setVips(List<Vip> vips) {
		this.vips = vips;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
