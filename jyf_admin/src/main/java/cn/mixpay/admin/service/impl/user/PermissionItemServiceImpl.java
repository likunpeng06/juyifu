package cn.mixpay.admin.service.impl.user;

import cn.mixpay.admin.dao.user.PermissionItemDao;
import cn.mixpay.admin.entity.user.PermissionItem;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;
import cn.mixpay.admin.service.user.PermissionItemService;

import java.util.List;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:49
 */
public class PermissionItemServiceImpl extends AbstractBaseServiceImpl<PermissionItem> implements PermissionItemService {

    protected PermissionItemDao getPermissionItemDao() {
        return (PermissionItemDao)this.dao;
    }

    @Override
    public List<PermissionItem> findByPermissionId(Long permissionId) {
        return this.getPermissionItemDao().findByPermissionId(permissionId);
    }
}
