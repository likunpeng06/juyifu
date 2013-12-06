package cn.mixpay.admin.dao.impl.user;

import cn.mixpay.admin.dao.user.UserRoleDao;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.dao.impl.AbstractBaseDaoImpl;

import java.util.List;

public class UserRoleDaoImpl extends AbstractBaseDaoImpl<UserRole> implements UserRoleDao {

    @Override
    public List<UserRole> findByUserId(Long userId) {
        UserRole example = new UserRole();
        example.setUserId(userId);

        return this.findByExample(example, null);
    }

}
