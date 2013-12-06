package cn.mixpay.admin.dao.impl.user;

import cn.mixpay.admin.dao.impl.AbstractBaseDaoImpl;
import cn.mixpay.admin.dao.user.RolePermissionDao;
import cn.mixpay.admin.entity.user.RolePermission;

import java.util.List;

public class RolePermissionDaoImpl extends AbstractBaseDaoImpl<RolePermission> implements RolePermissionDao {

    @Override
    public List<RolePermission> findByRoleId(Long roleId) {
        RolePermission example = new RolePermission();
        example.setRoleId(roleId);

        return this.findByExample(example, null);
    }
}
