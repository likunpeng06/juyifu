package cn.mixpay.admin.service.impl.merchant;

import cn.mixpay.admin.dao.merchant.MerchantDao;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.core.entity.merchant.Merchant;

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
