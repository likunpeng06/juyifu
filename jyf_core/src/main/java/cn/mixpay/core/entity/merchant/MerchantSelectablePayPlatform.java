package cn.mixpay.core.entity.merchant;

import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;

import java.io.Serializable;

/**
 * 商户可选支付方式对应支付平台配置对象
 * Created by qatang on 13-12-9.
 */
public class MerchantSelectablePayPlatform implements Serializable {
    private Long id;
    private Long payModeId;
    private PlatformType platformType;
    private EnableDisableStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayModeId() {
        return payModeId;
    }

    public void setPayModeId(Long payModeId) {
        this.payModeId = payModeId;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public EnableDisableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableDisableStatus status) {
        this.status = status;
    }
}
