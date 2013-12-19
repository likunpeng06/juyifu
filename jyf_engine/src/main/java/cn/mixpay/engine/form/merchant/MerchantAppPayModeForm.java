package cn.mixpay.engine.form.merchant;

import cn.mixpay.engine.form.AbstractForm;

/**
 * Created by qatang on 13-12-10.
 */
public class MerchantAppPayModeForm extends AbstractForm {
    private String merchantAppId;

    public String getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(String merchantAppId) {
        this.merchantAppId = merchantAppId;
    }
}
