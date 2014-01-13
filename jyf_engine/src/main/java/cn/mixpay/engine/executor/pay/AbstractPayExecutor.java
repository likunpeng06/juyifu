package cn.mixpay.engine.executor.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 14-1-13.
 */
public abstract class AbstractPayExecutor implements IPayExecutor {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());
}
