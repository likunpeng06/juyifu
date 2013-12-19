package cn.mixpay.engine.admin.dao;

import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.engine.dao.BaseDao;

import java.io.Serializable;

/**
 * Created by qatang on 13-12-12.
 */
public interface MerchantAppPayModeDao extends BaseDao<MerchantAppPayMode> {
    public void deleteByAppId(Serializable id);
}
