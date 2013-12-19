package cn.mixpay.engine.admin.service.impl;

import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.engine.admin.dao.MerchantDao;
import cn.mixpay.engine.admin.service.MerchantService;
import cn.mixpay.engine.service.impl.AbstractBaseServiceImpl;

/**
 * Created by qatang on 13-12-11.
 */
public class MerchantServiceImpl extends AbstractBaseServiceImpl<Merchant> implements MerchantService {
    @Override
    public Merchant findByUsername(String username) {
        return this.getMerchantDao().findByUsername(username);
    }

    public MerchantDao getMerchantDao() {
        return (MerchantDao)dao;
    }
}
