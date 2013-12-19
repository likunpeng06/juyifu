package cn.mixpay.engine.form.config;

import cn.mixpay.engine.form.AbstractForm;

/**
 * Created by qatang on 13-12-10.
 */
public class PayPlatformConfigForm extends AbstractForm {
    private String merchantAppId;

    public String getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(String merchantAppId) {
        this.merchantAppId = merchantAppId;
    }
}
