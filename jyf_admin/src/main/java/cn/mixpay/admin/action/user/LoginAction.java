package cn.mixpay.admin.action.user;

import cn.mixpay.admin.bean.UserSessionBean;
import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.constant.Global;
import cn.mixpay.admin.entity.user.Role;
import cn.mixpay.admin.entity.user.RolePermission;
import cn.mixpay.admin.entity.user.User;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.service.user.*;
import cn.mixpay.core.utils.CoreHttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class LoginAction extends BaseAction {
    private static final long serialVersionUID = -8830679912602886965L;

    private UserService userService;
    private RoleService roleService;
    private UserRoleService userRoleService;
    private RolePermissionService rolePermissionService;

    private PermissionService permissionService;

    private String username;
    private String password;
    private String verifyCode;

    @SuppressWarnings("unchecked")
    public String handle() {
        logger.info("进入验证登录");

        this.emptyCheck(this.getUsername(), INDEX, "用户名不能为空");
        this.emptyCheck(this.getPassword(), INDEX, "密码不能为空");

        this.emptyCheck(this.getVerifyCode(), INDEX, "验证码不能为空");

        HttpServletRequest request = ServletActionContext.getRequest();

        String kaptchaExpected = (String)request.getSession()
                .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);

        if (verifyCode == null || !verifyCode.equalsIgnoreCase(kaptchaExpected))
        {
            logger.error("verifyCode={} ,kaptchaExpected={}", new Object[]{verifyCode, kaptchaExpected});
            this.errorForward(INDEX, "验证码错误");
        }

        User user = userService.login(this.getUsername(), this.getPassword());
        if (user == null) {
            this.errorForward(INDEX, "用户名或密码错误");
        }

        List<UserRole> userRoleList = userRoleService.findByUserId(user.getId());

        List<Role> roleList = new ArrayList<Role>();

        // 根据所属角色验证登录IP
        if (userRoleList != null) {
            for (UserRole userRole : userRoleList) {
                Role role = roleService.findById(userRole.getId());
                if (role == null) {
                    // 容错
                    continue;
                }
                roleList.add(role);
                if (role.getRestriction() != null && role.getRestriction()) {//限定ip时，进行有效ip段验证
                    String remoteIp = getRemoteIp(ServletActionContext.getRequest());
                    if (!matchingIp(role.getRestrictionIp(), remoteIp)) {
                        this.errorForward(INDEX, "您的IP地址已被禁止登录");
                    }
                }
            }
        }

        // 汇总用户权限
        Set<Long> permissionIdSet = new HashSet<Long>();
        Map<Long, Set<Long>> permissionItemIdSetMap = new HashMap<Long, Set<Long>>();
        for (Role role : roleList) {
            List<RolePermission> rolePermissionList = rolePermissionService.findByRoleId(role.getId());
            for (RolePermission rolePermission : rolePermissionList) {
                permissionIdSet.add(rolePermission.getPermissionId());

                if (StringUtils.isNotBlank(rolePermission.getPermissionItemIds())) {
                    if (!permissionItemIdSetMap.containsKey(rolePermission.getPermissionId())) {
                        permissionItemIdSetMap.put(rolePermission.getPermissionId(), new HashSet<Long>());
                    }

                    Set<Long> permissionItemIdSet = permissionItemIdSetMap.get(rolePermission.getPermissionId());

                    String[] permissionItemIds = StringUtils.split(rolePermission.getPermissionItemIds(), " ,");
                    for (String permissionItemId : permissionItemIds) {
                        permissionItemIdSet.add(Long.valueOf(permissionItemId));
                    }
                }
            }
        }

        // 登录成功，更新最后登录时间
        user.setLastLoginTime(user.getLoginTime());
        user.setLoginTime(new Date());
        this.userService.update(user);

        // 创建userSessionBean
        UserSessionBean userSessionBean = new UserSessionBean();
        userSessionBean.setUser(user);
        userSessionBean.setRoleList(roleList);
        userSessionBean.setPermissionIdList(new ArrayList<Long>(permissionIdSet));
        userSessionBean.setPermissionItemIdSetMap(permissionItemIdSetMap);

        super.getSession().put(Global.USER_SESSION, userSessionBean);
        super.setForwardUrl("/main.do");
        logger.info("验证登录结束");
        return FORWARD;
    }

    //ip段匹配
    private boolean matchingIp(String restrictionIp, String remoteIp) {
        //有效ip段或请求ip不存在
        if (restrictionIp == null || remoteIp == null) {
            return false;
        }

        String[] restrictionIpArray = restrictionIp.trim().split(",");
        String[] remoteIpItem = remoteIp.trim().split("\\.");
        if (restrictionIpArray.length > 0) {
            for (String restrictionIpSegment : restrictionIpArray) {
                String[] restrictionIpItem = restrictionIpSegment.trim().split("\\.");
                if (restrictionIpItem.length == 4 && remoteIpItem.length == 4) {
                    boolean flag = true;
                    for (int i = 0; i < 4; i++) {
                        if (!restrictionIpItem[i].equals("*") && !restrictionIpItem[i].equals(remoteIpItem[i])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        return true;
                    }
                }
            }
        }


        return false;
    }

    //获取访问者IP
    private String getRemoteIp(HttpServletRequest request) {
        String[] clientIPArray = CoreHttpUtils.getClientIPArray(request);
        if (clientIPArray != null && clientIPArray.length > 0) {
            return clientIPArray[0];
        }
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public void setRolePermissionService(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }
}
