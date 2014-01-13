package cn.mixpay.engine.executor.pay.impl;

import cn.mixpay.engine.chain.pay.IPayChainParams;
import cn.mixpay.engine.chain.pay.IPayChainResult;
import cn.mixpay.engine.executor.pay.AbstractPayExecutor;

/**
 * Created by qatang on 14-1-13.
 */
public class PayPlatformRequestExecutor extends AbstractPayExecutor {
    @Override
    public boolean execute(IPayChainParams payChainParams, IPayChainResult payChainResult) throws Exception {
        return true;
    }
}
