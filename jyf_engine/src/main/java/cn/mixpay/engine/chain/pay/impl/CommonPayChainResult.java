package cn.mixpay.engine.chain.pay.impl;

import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.engine.chain.pay.AbstractPayChainResult;
import cn.mixpay.engine.response.IResponse;

/**
 * Created by qatang on 14-1-13.
 */
public class CommonPayChainResult extends AbstractPayChainResult {
    private PayOrder payOrder;
    private IResponse response;

    public PayOrder getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(PayOrder payOrder) {
        this.payOrder = payOrder;
    }

    public IResponse getResponse() {
        return response;
    }

    public void setResponse(IResponse response) {
        this.response = response;
    }
}
