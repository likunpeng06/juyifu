package cn.mixpay.engine.admin.dao;

import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.engine.dao.BaseDao;

/**
 * Created by qatang on 13-12-11.
 */
public interface MerchantDao extends BaseDao<Merchant> {
    Merchant findByUsername(String username);
}
