package cn.mixpay.engine.chain.pay;

import cn.mixpay.engine.executor.pay.IPayExecutor;
import cn.mixpay.engine.form.IForm;
import cn.mixpay.engine.response.IResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by qatang on 14-1-13.
 */
public abstract class AbstractPayChain implements IPayChain {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<IPayExecutor> payExecutorList;

    @Override
    public IPayChainResult executeChain(IForm form, IResponse response) throws Exception {
        IPayChainParams payChainParams = initPayChainParams(form);
        IPayChainResult payChainResult = initPayChainResult(response);
        if (payExecutorList == null || payExecutorList.isEmpty()) {
            logger.error("支付流程执行链为空");
            throw new Exception("支付流程执行链为空");
        }
        for (IPayExecutor payExecutor : payExecutorList) {
            boolean result = payExecutor.execute(payChainParams, payChainResult);
            if (!result) {
                break;
            }
        }
        return payChainResult;
    }

    protected abstract IPayChainParams initPayChainParams(IForm form);

    protected abstract IPayChainResult initPayChainResult(IResponse response);

    public void setPayExecutorList(List<IPayExecutor> payExecutorList) {
        this.payExecutorList = payExecutorList;
    }
}
