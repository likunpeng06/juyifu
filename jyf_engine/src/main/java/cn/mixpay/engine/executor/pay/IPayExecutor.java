package cn.mixpay.engine.executor.pay;

import cn.mixpay.engine.chain.pay.IPayChainParams;
import cn.mixpay.engine.chain.pay.IPayChainResult;

/**
 *
 * Created by qatang on 14-1-13.
 */
public interface IPayExecutor {
    boolean execute(IPayChainParams payChainParams, IPayChainResult payChainResult) throws Exception;
}
