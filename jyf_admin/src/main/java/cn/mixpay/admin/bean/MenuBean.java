package cn.mixpay.admin.bean;

import java.io.Serializable;
import java.util.List;

import cn.mixpay.admin.entity.user.Menu;
import cn.mixpay.admin.entity.user.Permission;

public class MenuBean implements Serializable {

	private static final long serialVersionUID = -3764402465865930790L;

	private Menu menu;
	private List<Permission> permissionList;
	
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
