package cn.mixpay.engine.validator;

import cn.mixpay.engine.form.IForm;

/**
 * Created by qatang on 13-12-17.
 */
public interface IValidator {
    /**
     * 验证form对象中参数是否正确
     * @param form
     * @return
     * @throws Exception
     */
    boolean validator(IForm form) throws Exception;
}
