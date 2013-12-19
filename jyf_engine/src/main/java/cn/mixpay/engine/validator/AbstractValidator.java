package cn.mixpay.engine.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 13-12-17.
 */
public abstract class AbstractValidator implements IValidator {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());
}
