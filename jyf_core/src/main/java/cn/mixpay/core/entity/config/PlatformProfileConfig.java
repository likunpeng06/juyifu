package cn.mixpay.core.entity.config;

import cn.mixpay.core.type.PlatformType;

import java.io.Serializable;

/**
 * 平台资料配置,mixpay在各个平台的配置
 * User: leiming
 */
public class PlatformProfileConfig implements Serializable {
    private Long id;// 流水
    private PlatformType platformType;// 平台类型
    private String account;// 帐号(如卖家支付宝账号)
    private String partner;// 合作者身份ID
    private String publicKey;// 对应平台所提供的平台公钥,如支付宝公钥
    private String privateKey;// 自身私钥
    private String serviceUrl;// 服务地址
    private String notifyUrl;// 通知地址
    private String returnUrl;// 返回地址
    private String ext;// 扩展信息

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
