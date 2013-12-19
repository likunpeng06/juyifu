package cn.mixpay.engine.controller;

import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.core.type.ErrorType;
import cn.mixpay.core.type.OutputType;
import cn.mixpay.core.type.PayType;
import cn.mixpay.engine.admin.service.MerchantAppPayModeService;
import cn.mixpay.engine.form.merchant.MerchantAppPayModeForm;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.response.order.MerchantAppPayModeJSONResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qatang on 13-12-16.
 */
@Controller
public class MerchantAppPayModeController extends BaseController {
    @Autowired
    private MerchantAppPayModeService merchantAppPayModeService;

    @RequestMapping("/get_pay_types")
    public void getLatestConfig(MerchantAppPayModeForm merchantAppPayModeForm, HttpServletResponse response) {
        IResponse res = getResponseInstance(OutputType.JSON);

        if (merchantAppPayModeForm == null || merchantAppPayModeForm.getMerchantAppId() == null) {
            String msg = String.format("查询支付方式列表失败：未查询到商户App编码");
            logger.error(msg);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        Long merchantAppId = 0L;
        try {
            merchantAppId = Long.valueOf(merchantAppPayModeForm.getMerchantAppId());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        if (merchantAppId == 0L) {
            String msg = String.format("查询支付方式列表失败：商户App编码=%s格式不对", merchantAppPayModeForm.getMerchantAppId());
            logger.error(msg);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        MerchantAppPayMode merchantAppPayMode = new MerchantAppPayMode();
        merchantAppPayMode.setAppId(Long.valueOf(merchantAppPayModeForm.getMerchantAppId()));
        List<MerchantAppPayMode> merchantAppPayModeList = merchantAppPayModeService.findByExample(merchantAppPayMode, null);

        if (merchantAppPayModeList == null || merchantAppPayModeList.size() == 0) {
            String msg = String.format("查询支付方式列表失败：未查询到商户App编码=%s的支付方式列表", merchantAppPayModeForm.getMerchantAppId());
            logger.error(msg);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        List<PayType> payTypeList = new ArrayList<PayType>();
        for (MerchantAppPayMode mode : merchantAppPayModeList) {
            payTypeList.add(mode.getPayType());
        }

        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());
        res.setData(res.convert(payTypeList));
        writeRes(response, res);
    }

    protected IResponse getResponseInstance(OutputType outputType) {
        switch (outputType) {
            case JSON:return new MerchantAppPayModeJSONResponse();
            case XML:break;
            case RAW:break;
        }
        return null;
    }
}
