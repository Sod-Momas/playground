package cc.momas.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 默认控制器
 *
 * @author Sod-Momas
 * @since 2021-03-13
 */
public class IndexAction extends ActionSupport {
    @Override
    public String execute() {
        return "index";
    }
}
