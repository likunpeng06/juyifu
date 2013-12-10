package cn.mixpay.merchant.service;

import cn.mixpay.core.entity.merchant.Merchant;

/**
 * Created by qatang on 13-12-10.
 */
public interface UserService {
    /**
     * 商户登录
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    Merchant login(String username, String password) throws Exception;
}
