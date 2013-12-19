package cn.mixpay.engine.validator.order;

import cn.mixpay.core.type.PayType;
import cn.mixpay.engine.form.IForm;
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.validator.AbstractValidator;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qatang on 13-12-17.
 */
public class CreatePayOrderValidator extends AbstractValidator {
    @Override
    public boolean validator(IForm form) throws Exception {
        if (form == null) {
            logger.error("创建订单失败：参数form不能为空。");
            throw new Exception("创建订单失败：参数form不能为空。");
        }
        PayOrderForm payOrderForm = (PayOrderForm)form;
        if (StringUtils.isEmpty(payOrderForm.getMerchantAppId())) {
            logger.error("创建订单失败：商户App编码不能为空。");
            throw new Exception("创建订单失败：商户App编码不能为空。");
        }

        if (StringUtils.isEmpty(payOrderForm.getPayTypeValue())) {
            logger.error("创建订单失败：商户支付方式编码不能为空。");
            throw new Exception("创建订单失败：商户支付方式编码不能为空。");
        }

        if (StringUtils.isEmpty(payOrderForm.getExternalOrderId())) {
            logger.error("创建订单失败：外部订单编码不能为空。");
            throw new Exception("创建订单失败：外部订单编码不能为空。");
        }

        if (StringUtils.isEmpty(payOrderForm.getAmount())) {
            logger.error("创建订单失败：金额不能为空。");
            throw new Exception("创建订单失败：金额不能为空。");
        }

        if (StringUtils.isEmpty(payOrderForm.getSign())) {
            logger.error("创建订单失败：身份签名不能为空。");
            throw new Exception("创建订单失败：身份签名编码不能为空。");
        }

        try {
            Long.valueOf(payOrderForm.getMerchantAppId());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new NumberFormatException("创建订单失败：商户App编码不合法:merchantAppId=" + payOrderForm.getMerchantAppId());
        }

        int payTypeIntValue = 0;
        try {
            payTypeIntValue = Integer.valueOf(payOrderForm.getPayTypeValue());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new NumberFormatException("创建订单失败：支付类型编码不合法:payTypeValue=" + payOrderForm.getPayTypeValue());
        }
        if (payTypeIntValue <= 0) {
            throw new Exception("创建订单失败：支付类型编码不合法:payTypeValue=" + payTypeIntValue);
        }

        PayType payType = PayType.get(payTypeIntValue);
        if (payType == null) {
            throw new Exception("创建订单失败：支付类型编码不合法:payTypeValue=" + payTypeIntValue);
        }

        try {
            Double.valueOf(payOrderForm.getAmount());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new NumberFormatException("创建订单失败：金额不合法:amount=" + payOrderForm.getAmount());
        }

        return true;
    }
}
