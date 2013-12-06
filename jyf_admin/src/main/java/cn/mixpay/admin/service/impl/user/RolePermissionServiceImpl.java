package cn.mixpay.admin.service.impl.user;

import cn.mixpay.admin.dao.user.RolePermissionDao;
import cn.mixpay.admin.entity.user.RolePermission;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;
import cn.mixpay.admin.service.user.RolePermissionService;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:49
 */
public class RolePermissionServiceImpl extends AbstractBaseServiceImpl<RolePermission> implements RolePermissionService {

    protected RolePermissionDao getRolePermissionDao() {
        return (RolePermissionDao)this.dao;
    }

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        return this.getRolePermissionDao().findByRoleId(roleId);
    }
}
