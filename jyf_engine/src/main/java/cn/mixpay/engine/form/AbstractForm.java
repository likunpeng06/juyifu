package cn.mixpay.engine.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 13-12-17.
 */
public abstract class AbstractForm implements IForm {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());
}
