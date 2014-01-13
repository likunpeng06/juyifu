package cn.mixpay.engine.form.order;

import cn.mixpay.engine.form.AbstractForm;

/**
 * Created by qatang on 13-12-10.
 */
public class PayOrderForm extends AbstractForm {
    private String merchantAppId;
    private String payTypeValue;
    private String externalOrderId;
    private String amount;
    private String signTypeValue;
    private String sign;
    private String sourceId;
    private String productId;
    private String productName;
    private String externalExt;
    private String osTypeValue;
    private String osVersion;
    private String remoteIp;

    public String getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(String merchantAppId) {
        this.merchantAppId = merchantAppId;
    }

    public String getPayTypeValue() {
        return payTypeValue;
    }

    public void setPayTypeValue(String payTypeValue) {
        this.payTypeValue = payTypeValue;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getExternalExt() {
        return externalExt;
    }

    public void setExternalExt(String externalExt) {
        this.externalExt = externalExt;
    }

    public String getOsTypeValue() {
        return osTypeValue;
    }

    public void setOsTypeValue(String osTypeValue) {
        this.osTypeValue = osTypeValue;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getSignTypeValue() {
        return signTypeValue;
    }

    public void setSignTypeValue(String signTypeValue) {
        this.signTypeValue = signTypeValue;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }
}
