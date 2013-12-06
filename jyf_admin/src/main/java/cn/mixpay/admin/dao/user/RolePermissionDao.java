package cn.mixpay.admin.dao.user;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.admin.entity.user.RolePermission;

import java.util.List;

public interface RolePermissionDao extends BaseDao<RolePermission> {

    public List<RolePermission> findByRoleId(Long roleId);

}
