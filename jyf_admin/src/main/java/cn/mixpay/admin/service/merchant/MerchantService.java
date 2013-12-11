package cn.mixpay.admin.service.merchant;

import cn.mixpay.admin.service.BaseService;
import cn.mixpay.core.entity.merchant.Merchant;

/**
 * 商户基本信息管理接口
 * Created by qatang on 13-12-10.
 */
public interface MerchantService extends BaseService<Merchant> {
    public Merchant findByUsername(String username);
}
