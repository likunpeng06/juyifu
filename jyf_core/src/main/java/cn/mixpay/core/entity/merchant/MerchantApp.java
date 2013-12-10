package cn.mixpay.core.entity.merchant;

import cn.mixpay.core.status.EnableDisableStatus;

import java.io.Serializable;
import java.util.Date;

/**
 * 商户app对象
 * Created by qatang on 13-12-9.
 */
public class MerchantApp implements Serializable {
    private Long id; //app编码
    private Long merchantId; //商户编码
    private String name; //app名称
    private EnableDisableStatus status;
    private String publicKey;
    private String privateKey;
    private Date createdTime;
    private Date updatedTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnableDisableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableDisableStatus status) {
        this.status = status;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
