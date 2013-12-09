package cn.mixpay.core.entity.config;

import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * 支付方式对应支付平台配置
 * Created by qatang on 13-12-7.
 */
public class PayPlatformConfig implements Serializable {
    private Long id;
    private PlatformType platformType;
    private PayType payType;
    private EnableDisableStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
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
