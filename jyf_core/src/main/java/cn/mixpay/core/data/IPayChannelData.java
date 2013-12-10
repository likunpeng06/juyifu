package cn.mixpay.core.data;

import cn.mixpay.core.type.PlatformType;

/**
 * 根据订单信息选择支付渠道返回结果
 * Created by qatang on 13-12-10.
 */
public interface IPayChannelData {
    /**
     * 返回支付平台信息
     * @return
     */
    PlatformType getPlatformType();
}
