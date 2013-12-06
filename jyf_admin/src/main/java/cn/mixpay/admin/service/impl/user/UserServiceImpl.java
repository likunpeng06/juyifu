package cn.mixpay.admin.service.impl.user;

import cn.mixpay.admin.dao.user.UserDao;
import cn.mixpay.admin.dao.user.UserRoleDao;
import cn.mixpay.admin.entity.user.User;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;
import cn.mixpay.admin.service.user.UserService;
import cn.mixpay.admin.utils.CharsetConstant;
import cn.mixpay.admin.utils.CoreStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: sunshow
 * Date: 13-7-14
 * Time: 上午9:49
 */
public class UserServiceImpl extends AbstractBaseServiceImpl<User> implements UserService {

    private UserRoleDao userRoleDao;

    protected UserDao getUserDao() {
        return (UserDao)dao;
    }

    @Override
    public User findByUsername(String username) {
        return this.getUserDao().findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        User user = this.findByUsername(username);
        if (user != null) {
            if (CoreStringUtils.md5(password, CharsetConstant.CHARSET_UTF8).equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void saveOrUpdate(User user, List<UserRole> userRoleList) {
        if (user.getId() == null) {
            this.getUserDao().save(user);
        } else {
            this.getUserDao().merge(user);
        }

        // 先取出已有的关系
        List<UserRole> srcUserRoleList = userRoleDao.findByUserId(user.getId());

        // 按照role转置map
        Map<Long, UserRole> srcUserRoleMap = new HashMap<Long, UserRole>();
        if (srcUserRoleList != null) {
            for (UserRole userRole : srcUserRoleList) {
                srcUserRoleMap.put(userRole.getRoleId(), userRole);
            }
        }

        // 转置要修改成的数据
        Map<Long, UserRole> desUserRoleMap = new HashMap<Long, UserRole>();
        if (userRoleList != null) {
            for (UserRole userRole : userRoleList) {
                desUserRoleMap.put(userRole.getRoleId(), userRole);
            }
        }


        List<UserRole> saveUserRoleList = new ArrayList<UserRole>();
        List<UserRole> deleteUserRoleList = new ArrayList<UserRole>();

        // 查找出已被删除的权限
        if (srcUserRoleList != null) {
            for (UserRole srcUserRole : srcUserRoleList) {
                if (!desUserRoleMap.containsKey(srcUserRole.getRoleId())) {
                    // 已被删除
                    deleteUserRoleList.add(srcUserRole);
                    continue;
                }
            }
        }

        // 查找出要新增的权限
        if (userRoleList != null) {
            for (UserRole desUserRole : userRoleList) {
                if (!srcUserRoleMap.containsKey(desUserRole.getRoleId())) {
                    // 不存在的权限，需要添加
                    if (desUserRole.getUserId() == null) {
                        desUserRole.setUserId(user.getId());
                    }
                    saveUserRoleList.add(desUserRole);
                }
            }
        }

        // 执行删除
        for (UserRole userRole : deleteUserRoleList) {
            userRoleDao.delete(userRole.getId());
        }

        // 执行保存和更新
        for (UserRole userRole : saveUserRoleList) {
            userRoleDao.save(userRole);
        }
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }
}
