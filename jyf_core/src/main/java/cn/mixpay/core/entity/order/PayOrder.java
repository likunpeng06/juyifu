package cn.mixpay.core.entity.order;

import cn.mixpay.core.status.OrderPayStatus;
import cn.mixpay.core.status.OrderStatus;
import cn.mixpay.core.type.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付订单
 * Created by qatang on 13-12-6.
 */
public class PayOrder implements Serializable {
    private Long id;
    private Long merchantId;
    private Long merchantAppId;
    private PayType payType;
    private PlatformType platform;
    private String externalOrderId;
    private OrderPayStatus orderPayStatus;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private Date createdTime;
    private Date updatedTime;
    private Date deadline;
    private Date payTime;
    private String sourceId;
    private String checksum;
    private Double amount;
    private CurrencyType currency;
    private String productId;
    private String productName;
    private String externalExt;
    private OsType osType;
    private String osVersion;
    private String remoteIp;
    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public PlatformType getPlatform() {
        return platform;
    }

    public void setPlatform(PlatformType platform) {
        this.platform = platform;
    }

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public OrderPayStatus getOrderPayStatus() {
        return orderPayStatus;
    }

    public void setOrderPayStatus(OrderPayStatus orderPayStatus) {
        this.orderPayStatus = orderPayStatus;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
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

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Long getMerchantAppId() {
        return merchantAppId;
    }

    public void setMerchantAppId(Long merchantAppId) {
        this.merchantAppId = merchantAppId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }
}
