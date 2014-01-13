package cn.mixpay.engine.chain.pay;

import cn.mixpay.engine.form.IForm;
import cn.mixpay.engine.response.IResponse;

/**
 * 支付流程链
 * Created by qatang on 14-1-13.
 */
public interface IPayChain {
    /**
     * 执行链
     * @param form
     * @return
     * @throws Exception
     */
    IPayChainResult executeChain(IForm form, IResponse response) throws Exception;
}
