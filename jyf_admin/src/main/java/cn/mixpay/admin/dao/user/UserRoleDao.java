package cn.mixpay.admin.dao.user;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.admin.entity.user.UserRole;

import java.util.List;

/**
 * 用户角色数据访问接口
 * @author chirowong
 *
 */
public interface UserRoleDao extends BaseDao<UserRole> {

	public List<UserRole> findByUserId(Long userId);

}
