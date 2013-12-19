package cn.mixpay.engine.controller;

import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.core.type.ErrorType;
import cn.mixpay.core.type.OutputType;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.engine.admin.service.MerchantAppPayModeService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayModeService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayPlatformService;
import cn.mixpay.engine.form.config.PayPlatformConfigForm;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.response.config.ConfigDataJSONResponse;
import cn.mixpay.engine.response.config.ConfigVersionJSONResponse;
import cn.mixpay.engine.validator.IValidator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by qatang on 13-12-16.
 */
@Controller
public class PayPlatformController extends BaseController {
    @Autowired
    private MerchantAppPayModeService merchantAppPayModeService;

    @RequestMapping("/get_ver")
    public void getVersion(HttpServletResponse response) {
        IResponse res = getConfigVersionResponseInstance(OutputType.JSON);

        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());

        res.setData(res.convert(PayType.getVersion()));
        writeRes(response, res);
    }

    @RequestMapping("/get_latest_config")
    public void getLatestConfig(PayPlatformConfigForm payPlatformConfigForm, HttpServletResponse response) {
        IResponse res = getConfigDataResponseInstance(OutputType.JSON);

        if (payPlatformConfigForm == null || payPlatformConfigForm.getMerchantAppId() == null) {
            String msg = String.format("查询最新支付方式及支付平台失败：未查询到商户App编码");
            logger.error(msg);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        Long merchantAppId = 0L;
        try {
            merchantAppId = Long.valueOf(payPlatformConfigForm.getMerchantAppId());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        }
        if (merchantAppId == 0L) {
            String msg = String.format("查询最新支付方式及支付平台失败：商户App编码=%s格式不对", payPlatformConfigForm.getMerchantAppId());
            logger.error(msg);
            res.setCode(ErrorType.PARAMETERS_ERROR.getValue());
            res.setMessage(ErrorType.PARAMETERS_ERROR.getName() + "，原因是：" + msg);
            writeRes(response, res);
            return;
        }

        MerchantAppPayMode merchantAppPayMode = new MerchantAppPayMode();
        merchantAppPayMode.setAppId(Long.valueOf(payPlatformConfigForm.getMerchantAppId()));
        List<MerchantAppPayMode> merchantAppPayModeList = merchantAppPayModeService.findByExample(merchantAppPayMode, null);

        JSONObject jsonObject = new JSONObject();

        JSONArray selectablePayTypeArray = new JSONArray();
        if (merchantAppPayModeList != null && merchantAppPayModeList.size() > 0) {
            for (MerchantAppPayMode mode : merchantAppPayModeList) {
                selectablePayTypeArray.add(mode.getPayType().getValue());
            }
        }
        jsonObject.put(ConfigDataJSONResponse.KEY_SELECTABLE_PAY_TYPES, selectablePayTypeArray);

        JSONObject dictionaryJsonObject = new JSONObject();
        dictionaryJsonObject.put(ConfigDataJSONResponse.KEY_VERSION, PayType.getVersion());

        JSONArray latestPayTypeArray = new JSONArray();
        for (PayType payType : PayType.list()) {
            JSONObject latestPayTypeJsonObject = new JSONObject();
            latestPayTypeJsonObject.put(ConfigDataJSONResponse.KEY_ID, payType.getValue());
            latestPayTypeJsonObject.put(ConfigDataJSONResponse.KEY_NAME, payType.getName());
            latestPayTypeArray.add(latestPayTypeJsonObject);
        }
        dictionaryJsonObject.put(ConfigDataJSONResponse.KEY_LATEST_PAY_TYPES, latestPayTypeArray);

        JSONArray latestPlatformTypeArray = new JSONArray();
        for (PlatformType platformType : PlatformType.list()) {
            JSONObject latestPlatformTypeJsonObject = new JSONObject();
            latestPlatformTypeJsonObject.put(ConfigDataJSONResponse.KEY_ID, platformType.getValue());
            latestPlatformTypeJsonObject.put(ConfigDataJSONResponse.KEY_NAME, platformType.getName());
            latestPlatformTypeArray.add(latestPlatformTypeJsonObject);
        }
        dictionaryJsonObject.put(ConfigDataJSONResponse.KEY_LATEST_PLATFORMS, latestPlatformTypeArray);

        jsonObject.put(ConfigDataJSONResponse.KEY_DICTIONARY, dictionaryJsonObject);

        res.setCode(ErrorType.SUCCESS.getValue());
        res.setMessage(ErrorType.SUCCESS.getName());

        res.setData(res.convert(jsonObject));
        writeRes(response, res);
    }

    protected IResponse getConfigVersionResponseInstance(OutputType outputType) {
        switch (outputType) {
            case JSON:return new ConfigVersionJSONResponse();
            case XML:break;
            case RAW:break;
        }
        return null;
    }

    protected IResponse getConfigDataResponseInstance(OutputType outputType) {
        switch (outputType) {
            case JSON:return new ConfigDataJSONResponse();
            case XML:break;
            case RAW:break;
        }
        return null;
    }
}
