package cn.mixpay.core.entity.merchant;

import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;

import java.io.Serializable;

/**
 * 商户可选支付方式配置对象
 * Created by qatang on 13-12-9.
 */
public class MerchantPayConfig implements Serializable {
    private Long id;
    private Long merchantId;
    private PayType payType;
    private EnableDisableStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public EnableDisableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableDisableStatus status) {
        this.status = status;
    }
}
