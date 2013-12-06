package cn.mixpay.admin.dao.user;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.admin.entity.user.PermissionItem;

import java.util.List;

public interface PermissionItemDao extends BaseDao<PermissionItem> {

    public List<PermissionItem> findByPermissionId(Long permissionId);

}