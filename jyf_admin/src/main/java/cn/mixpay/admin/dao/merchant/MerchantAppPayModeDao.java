package cn.mixpay.admin.dao.merchant;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;

import java.io.Serializable;

/**
 * Created by qatang on 13-12-12.
 */
public interface MerchantAppPayModeDao extends BaseDao<MerchantAppPayMode> {
    public void deleteByAppId(Serializable id);
}
