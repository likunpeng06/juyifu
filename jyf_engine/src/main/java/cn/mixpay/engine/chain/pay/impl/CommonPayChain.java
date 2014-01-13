package cn.mixpay.engine.chain.pay.impl;

import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.engine.chain.pay.AbstractPayChain;
import cn.mixpay.engine.chain.pay.IPayChainParams;
import cn.mixpay.engine.chain.pay.IPayChainResult;
import cn.mixpay.engine.form.IForm;
import cn.mixpay.engine.response.IResponse;

/**
 * Created by qatang on 14-1-13.
 */
public class CommonPayChain extends AbstractPayChain {
    @Override
    protected IPayChainParams initPayChainParams(IForm form) {
        CommonPayChainParams commonPayChainParams = new CommonPayChainParams();
        commonPayChainParams.setForm(form);
        return commonPayChainParams;
    }

    @Override
    protected IPayChainResult initPayChainResult(IResponse response) {
        CommonPayChainResult commonPayChainResult = new CommonPayChainResult();
        commonPayChainResult.setPayOrder(new PayOrder());
        commonPayChainResult.setResponse(response);
        return commonPayChainResult;
    }
}
