package cn.mixpay.admin.service.user;

import cn.mixpay.admin.entity.user.User;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.service.BaseService;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:48
 */
public interface UserService extends BaseService<User> {

    public User findByUsername(String username);

    public User login(String username, String password);

    public void saveOrUpdate(User user, List<UserRole> userRoleList);

}
