package cn.mixpay.engine.chain.pay.impl;

import cn.mixpay.engine.chain.pay.AbstractPayChainParams;
import cn.mixpay.engine.form.IForm;

/**
 * Created by qatang on 14-1-13.
 */
public class CommonPayChainParams extends AbstractPayChainParams {
    private IForm form;

    public IForm getForm() {
        return form;
    }

    public void setForm(IForm form) {
        this.form = form;
    }
}
