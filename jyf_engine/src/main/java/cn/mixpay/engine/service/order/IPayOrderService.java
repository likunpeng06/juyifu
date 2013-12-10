package cn.mixpay.engine.service.order;

import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.engine.form.order.PayOrderForm;

import java.util.List;

/**
 * Created by qatang on 13-12-10.
 */
public interface IPayOrderService {
    /**
     * 创建支付订单
     * @param payOrder
     * @return
     * @throws Exception
     */
    PayOrder create(PayOrder payOrder) throws Exception;

    /**
     * 根据查询条件查询订单信息
     * @param payOrderForm
     * @return
     * @throws Exception
     */
    List<PayOrder> query(PayOrderForm payOrderForm) throws Exception;
}
