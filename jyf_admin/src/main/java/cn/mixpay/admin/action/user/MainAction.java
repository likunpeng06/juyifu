package cn.mixpay.admin.action.user;

import cn.mixpay.admin.action.BaseAction;

public class MainAction extends BaseAction {
	private static final long serialVersionUID = -8830679912602886965L;

	public String handle() {
        logger.info("进入系统");
		return MAIN;
	}
	
}
