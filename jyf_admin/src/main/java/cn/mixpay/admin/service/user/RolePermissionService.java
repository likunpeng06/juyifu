package cn.mixpay.admin.service.user;

import cn.mixpay.admin.entity.user.RolePermission;
import cn.mixpay.admin.service.BaseService;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:48
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    public List<RolePermission> findByRoleId(Long roleId);

}
