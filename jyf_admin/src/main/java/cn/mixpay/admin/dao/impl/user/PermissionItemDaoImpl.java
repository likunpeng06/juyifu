package cn.mixpay.admin.dao.impl.user;

import cn.mixpay.admin.dao.impl.AbstractBaseDaoImpl;
import cn.mixpay.admin.dao.user.PermissionItemDao;
import cn.mixpay.admin.entity.user.PermissionItem;

import java.util.List;

public class PermissionItemDaoImpl extends AbstractBaseDaoImpl<PermissionItem> implements PermissionItemDao {

    @Override
    public List<PermissionItem> findByPermissionId(Long permissionId) {
        PermissionItem example = new PermissionItem();
        example.setPermissionId(permissionId);

        return this.findByExample(example, null);
    }

}
