package cn.mixpay.admin.service.user;

import cn.mixpay.admin.entity.user.PermissionItem;
import cn.mixpay.admin.service.BaseService;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:48
 */
public interface PermissionItemService extends BaseService<PermissionItem> {

    public List<PermissionItem> findByPermissionId(Long permissionId);

}
