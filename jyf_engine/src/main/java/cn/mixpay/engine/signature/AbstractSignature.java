package cn.mixpay.engine.signature;

import cn.mixpay.core.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: leiming
 */
public abstract class AbstractSignature implements Signature{
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private Config getPlatformDataConfig(){


        return null;
    }


}
