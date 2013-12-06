package cn.mixpay.admin.dao.user;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.admin.entity.user.User;

public interface UserDao extends BaseDao<User> {

	public User findByUsername(String username);
}