package cn.mixpay.engine.admin.service;

import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.engine.service.BaseService;

/**
 * 商户基本信息管理接口
 * Created by qatang on 13-12-10.
 */
public interface MerchantService extends BaseService<Merchant> {
    public Merchant findByUsername(String username);
}
