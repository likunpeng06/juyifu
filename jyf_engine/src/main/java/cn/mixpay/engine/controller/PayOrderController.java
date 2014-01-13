package cn.mixpay.engine.controller;

import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayPlatform;
import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.status.OrderPayStatus;
import cn.mixpay.core.status.OrderStatus;
import cn.mixpay.core.type.*;
import cn.mixpay.core.utils.CoreHttpUtils;
import cn.mixpay.engine.admin.service.MerchantAppService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayModeService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayPlatformService;
import cn.mixpay.engine.chain.pay.IPayChain;
import cn.mixpay.engine.chain.pay.IPayChainResult;
import cn.mixpay.engine.chain.pay.impl.CommonPayChainResult;
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.generator.IdGeneratorService;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.response.order.CreatePayOrderJSONResponse;
import cn.mixpay.engine.service.order.IPayOrderService;
import cn.mixpay.engine.type.SequenceType;
import cn.mixpay.engine.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by qatang on 13-12-16.
 */
@Controller
public class PayOrderController extends BaseController {
    @Autowired
    private IValidator createPayOrderValidator;
    @Value("#{payTypeChainMap}")
    private Map<PayType, IPayChain> payTypeChainMap;//autowired回报错，key只能是string，spring3.0+的解决方法

    @RequestMapping("/create")
    public void create(PayOrderForm form, HttpServletRequest request, HttpServletResponse response) {
        boolean validateResult = false;
        IResponse res = getResponseInstance(OutputType.JSON);
        try {
            validateResult = createPayOrderValidator.validator(form);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + e.getMessage());
            writeRes(response, res);
            return;
        }

        if (!validateResult) {
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName());
            writeRes(response, res);
            return;
        }

        String remoteIp = CoreHttpUtils.getClientIP(request);
        form.setRemoteIp(remoteIp);

        IPayChain payChain = payTypeChainMap.get(PayType.get(Integer.valueOf(form.getPayTypeValue())));
        if (payChain == null) {
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName());
            writeRes(response, res);
            return;
        }
        IPayChainResult payChainResult = null;
        try {
            payChainResult = payChain.executeChain(form, res);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + e.getMessage());
            writeRes(response, res);
            return;
        }

        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());

        CommonPayChainResult commonPayChainResult = (CommonPayChainResult)payChainResult;
        res.setData(res.convert(commonPayChainResult.getPayOrder()));
        writeRes(response, res);
    }

    protected IResponse getResponseInstance(OutputType outputType) {
        switch (outputType) {
            case JSON:return new CreatePayOrderJSONResponse();
            case XML:break;
            case RAW:break;
        }
        return null;
    }
}
