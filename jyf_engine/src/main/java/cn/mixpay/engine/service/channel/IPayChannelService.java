package cn.mixpay.engine.service.channel;

import cn.mixpay.core.data.IPayChannelData;
import cn.mixpay.core.entity.order.PayOrder;

/**
 * Created by qatang on 13-12-10.
 */
public interface IPayChannelService {
    /**
     * 通过支付订单获取支付渠道相关数据
     * @param payOrder
     * @return
     * @throws Exception
     */
    IPayChannelData get(PayOrder payOrder) throws Exception;
}
