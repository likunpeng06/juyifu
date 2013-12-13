package cn.mixpay.admin.service.impl.merchant;

import cn.mixpay.admin.dao.merchant.MerchantAppPayModeDao;
import cn.mixpay.admin.dao.merchant.MerchantDao;
import cn.mixpay.admin.service.impl.AbstractBaseServiceImpl;
import cn.mixpay.admin.service.merchant.MerchantAppPayModeService;
import cn.mixpay.admin.service.merchant.MerchantAppService;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.entity.merchant.MerchantApp;

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
