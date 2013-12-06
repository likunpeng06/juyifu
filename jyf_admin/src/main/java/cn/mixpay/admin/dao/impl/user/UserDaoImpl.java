package cn.mixpay.admin.dao.impl.user;

import cn.mixpay.admin.entity.user.User;
import cn.mixpay.admin.dao.impl.AbstractBaseDaoImpl;
import cn.mixpay.admin.dao.user.UserDao;

import java.util.List;

public class UserDaoImpl extends AbstractBaseDaoImpl<User> implements UserDao {

    @Override
    public User findByUsername(String username) {
        User example = new User();
        example.setUsername(username);
        List<User> userList = this.findByExample(example, null);
        if (userList == null || userList.isEmpty()) {
            return null;
        }
        return userList.iterator().next();
    }

}
