package cn.mixpay.engine.service.impl.order;

import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.engine.dao.order.PayOrderDao;
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.service.order.IPayOrderService;

import java.util.List;

/**
 * Created by qatang on 13-12-16.
 */
public class PayOrderServiceImpl implements IPayOrderService {
    private PayOrderDao payOrderDao;
    @Override
    public PayOrder create(PayOrder payOrder) throws Exception {
        payOrderDao.save(payOrder);
        return payOrder;
    }

    @Override
    public List<PayOrder> query(PayOrderForm payOrderForm) throws Exception {
        return null;
    }

    public void setPayOrderDao(PayOrderDao payOrderDao) {
        this.payOrderDao = payOrderDao;
    }
}
