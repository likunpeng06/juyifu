package cn.mixpay.admin.service.impl.user;

import cn.mixpay.admin.dao.user.UserRoleDao;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.service.user.UserRoleService;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:49
 */
public class UserRoleServiceImpl extends AbstractBaseServiceImpl<UserRole> implements UserRoleService {

    protected UserRoleDao getUserRoleDao() {
        return (UserRoleDao)this.dao;
    }

    @Override
    public List<UserRole> findByUserId(Long userId) {
        return this.getUserRoleDao().findByUserId(userId);
    }
}
