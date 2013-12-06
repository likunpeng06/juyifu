package cn.mixpay.core.entity;

import cn.mixpay.core.status.OrderPayStatus;
import cn.mixpay.core.type.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by qatang on 13-12-6.
 */
public class PayOrder implements Serializable {
    private Long id;
    private Long merchantId;
    private PayType payType;
    private PlatformType platform;
    private String externalOrderId;
    private OrderPayStatus orderPayStatus;
    private OrderType orderType;
    private OrderPayStatus orderStatus;
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
    private String osName;
    private String osVersion;
    private String remoteIp;
    private String memo;
}
