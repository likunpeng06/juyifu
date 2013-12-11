package cn.mixpay.admin.dao.merchant;

import cn.mixpay.admin.dao.BaseDao;
import cn.mixpay.core.entity.merchant.Merchant;

/**
 * Created by qatang on 13-12-11.
 */
public interface MerchantDao extends BaseDao<Merchant> {
    Merchant findByUsername(String username);
}
