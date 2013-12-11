package cn.mixpay.engine.service.merchant;

import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.core.type.PayType;

import java.util.List;

/**
 * 商户app支付方式接口
 * Created by qatang on 13-12-10.
 */
public interface IMerchantAppPayModeService {
    /**
     * 根据商户app对象查询对应支付方式列表
     * @param merchantApp
     * @return
     * @throws Exception
     */
    List<PayType> query(MerchantApp merchantApp) throws Exception;
}
