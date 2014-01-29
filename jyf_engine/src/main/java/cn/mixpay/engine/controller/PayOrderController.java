package cn.mixpay.engine.controller;

import cn.mixpay.core.type.ErrorType;
import cn.mixpay.core.type.OutputType;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.utils.CoreHttpUtils;
import cn.mixpay.engine.chain.pay.IPayChain;
import cn.mixpay.engine.chain.pay.IPayChainResult;
import cn.mixpay.engine.chain.pay.impl.CommonPayChainResult;
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.response.order.CreatePayOrderJSONResponse;
import cn.mixpay.engine.service.sign.ISignService;
import cn.mixpay.engine.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by qatang on 13-12-16.
 */
@Controller
public class PayOrderController extends BaseController {
    @Autowired
    private IValidator createPayOrderValidator;
    @Value("#{payTypeChainMap}")
    private Map<PayType, IPayChain> payTypeChainMap;//autowired回报错，key只能是string，spring3.0+的解决方法

    @Autowired
    private ISignService signService;

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

        // response 错误处理
        CommonPayChainResult commonPayChainResult = (CommonPayChainResult)payChainResult;

        if(commonPayChainResult.getResponse().getCode() == ErrorType.DATA_ERROR.getValue()){
            writeRes(response, res);// TODO 错误信息直接暴露给用户?
            return;
        }

        // TODO 成功的响应填写感觉应该在执行链中填写
        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());


        res.setData(res.convert(commonPayChainResult.getPayOrder()));
        writeRes(response, res);
    }

    protected IResponse getResponseInstance(OutputType outputType) {
        switch (outputType) {
            case JSON:return new CreatePayOrderJSONResponse(signService);
            case XML:break;
            case RAW:break;
        }
        return null;
    }
}
