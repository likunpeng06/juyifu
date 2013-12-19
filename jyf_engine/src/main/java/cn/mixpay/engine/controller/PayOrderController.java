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
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.generator.IdGeneratorService;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.response.order.CreatePayOrderJSONResponse;
import cn.mixpay.engine.service.order.IPayOrderService;
import cn.mixpay.engine.type.SequenceType;
import cn.mixpay.engine.validator.IValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by qatang on 13-12-16.
 */
@Controller
public class PayOrderController extends BaseController {
    @Autowired
    private IdGeneratorService idGeneratorService;
    @Autowired
    private MerchantSelectablePayPlatformService merchantSelectablePayPlatformService;
    @Autowired
    private MerchantSelectablePayModeService merchantSelectablePayModeService;
    @Autowired
    private IValidator createPayOrderValidator;
    @Autowired
    private MerchantAppService merchantAppService;
    @Autowired
    private IPayOrderService payOrderService;

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
        MerchantApp merchantApp = merchantAppService.findById(Long.valueOf(form.getMerchantAppId()));
        if (merchantApp == null) {
            String msg = String.format("创建订单失败：未查询到id=%s的商户app", form.getMerchantAppId());
            logger.error(msg);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        MerchantSelectablePayMode merchantSelectablePayMode = new MerchantSelectablePayMode();
        merchantSelectablePayMode.setMerchantId(merchantApp.getMerchantId());
        merchantSelectablePayMode.setPayType(PayType.get(Integer.valueOf(form.getPayTypeValue())));
        merchantSelectablePayMode.setStatus(EnableDisableStatus.ENABLE);
        List<MerchantSelectablePayMode> merchantSelectablePayModeList = merchantSelectablePayModeService.findByExample(merchantSelectablePayMode, null);
        if (merchantSelectablePayModeList == null || merchantSelectablePayModeList.size() == 0) {
            String msg = String.format("创建订单失败：未查询到商户编码=%s，支付方式＝%s的可选支付方式", merchantApp.getMerchantId(), form.getPayTypeValue());
            logger.error(msg);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }
        merchantSelectablePayMode = merchantSelectablePayModeList.get(0);

        MerchantSelectablePayPlatform merchantSelectablePayPlatform = new MerchantSelectablePayPlatform();
        merchantSelectablePayPlatform.setPayModeId(merchantSelectablePayMode.getId());
        merchantSelectablePayPlatform.setStatus(EnableDisableStatus.ENABLE);
        List<MerchantSelectablePayPlatform> merchantSelectablePayPlatformList = merchantSelectablePayPlatformService.findByExample(merchantSelectablePayPlatform, null);
        if (merchantSelectablePayPlatformList == null || merchantSelectablePayPlatformList.size() == 0) {
            String msg = String.format("创建订单失败：未查询到商户编码=%s，支付方式＝%s的可选支付方式", merchantApp.getMerchantId(), form.getPayTypeValue());
            logger.error(msg);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }
        merchantSelectablePayPlatform = merchantSelectablePayPlatformList.get(0);

        PayOrder payOrder = new PayOrder();
        payOrder.setId(idGeneratorService.generate(SequenceType.ORDER));
        payOrder.setMerchantId(merchantApp.getMerchantId());
        payOrder.setMerchantAppId(Long.valueOf(form.getMerchantAppId()));
        payOrder.setPayType(PayType.get(Integer.valueOf(form.getPayTypeValue())));
        payOrder.setPlatform(merchantSelectablePayPlatform.getPlatformType());
        payOrder.setAmount(Double.valueOf(form.getAmount()));
        payOrder.setExternalOrderId(form.getExternalOrderId());
        payOrder.setOrderPayStatus(OrderPayStatus.UNPAY);
        payOrder.setOrderType(OrderType.COMMON);
        payOrder.setOrderStatus(OrderStatus.ALIVE);
        Date now = new Date();
        payOrder.setCreatedTime(now);
        payOrder.setUpdatedTime(now);
        Calendar cd = Calendar.getInstance();
        cd.setTime(now);
        cd.add(Calendar.DATE, 3);
        payOrder.setDeadline(cd.getTime());
        if (!StringUtils.isEmpty(form.getSourceId())) {
            payOrder.setSourceId(form.getSourceId());
        }
        payOrder.setCurrency(CurrencyType.RMB);
        if (!StringUtils.isEmpty(form.getProductId())) {
            payOrder.setProductId(form.getProductId());
        }
        if (!StringUtils.isEmpty(form.getProductName())) {
            payOrder.setProductName(form.getProductName());
        }
        if (!StringUtils.isEmpty(form.getExternalExt())) {
            payOrder.setExternalExt(form.getExternalExt());
        }
        if (!StringUtils.isEmpty(form.getOsTypeValue())) {
            OsType osType = null;
            try {
                osType = OsType.get(Integer.valueOf(form.getOsTypeValue()));
            } catch (NumberFormatException e) {
                logger.error(e.getMessage(), e);
            }
            if (osType != null) {
                payOrder.setOsType(osType);
            }
        } else {
            payOrder.setOsType(OsType.ALL);
        }
        if (!StringUtils.isEmpty(form.getOsVersion())) {
            payOrder.setOsVersion(form.getOsVersion());
        }
        String remoteIp = CoreHttpUtils.getClientIP(request);
        if (!StringUtils.isEmpty(remoteIp)) {
            payOrder.setRemoteIp(remoteIp);
        }

        try {
            payOrderService.create(payOrder);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + e.getMessage());
            writeRes(response, res);
            return;
        }

        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());

        res.setData(res.convert(payOrder));
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
