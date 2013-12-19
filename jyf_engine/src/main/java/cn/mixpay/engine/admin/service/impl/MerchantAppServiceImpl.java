package cn.mixpay.engine.admin.service.impl;

import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.engine.admin.dao.MerchantAppPayModeDao;
import cn.mixpay.engine.admin.service.MerchantAppService;
import cn.mixpay.engine.service.impl.AbstractBaseServiceImpl;

import java.io.Serializable;

/**
 * Created by qatang on 13-12-11.
 */
public class MerchantAppServiceImpl extends AbstractBaseServiceImpl<MerchantApp> implements MerchantAppService {
    private MerchantAppPayModeDao merchantAppPayModeDao;
    @Override
    public void delete(Serializable id) {
        this.dao.delete(id);
        merchantAppPayModeDao.deleteByAppId(id);
    }

    public MerchantAppPayModeDao getMerchantAppPayModeDao() {
        return merchantAppPayModeDao;
    }

    public void setMerchantAppPayModeDao(MerchantAppPayModeDao merchantAppPayModeDao) {
        this.merchantAppPayModeDao = merchantAppPayModeDao;
    }
}
