package cn.mixpay.engine.executor.pay.impl;

import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayPlatform;
import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.status.OrderPayStatus;
import cn.mixpay.core.status.OrderStatus;
import cn.mixpay.core.type.*;
import cn.mixpay.engine.admin.service.MerchantAppService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayModeService;
import cn.mixpay.engine.admin.service.MerchantSelectablePayPlatformService;
import cn.mixpay.engine.chain.pay.IPayChainParams;
import cn.mixpay.engine.chain.pay.IPayChainResult;
import cn.mixpay.engine.chain.pay.impl.CommonPayChainParams;
import cn.mixpay.engine.chain.pay.impl.CommonPayChainResult;
import cn.mixpay.engine.executor.pay.AbstractPayExecutor;
import cn.mixpay.engine.form.order.PayOrderForm;
import cn.mixpay.engine.generator.IdGeneratorService;
import cn.mixpay.engine.response.IResponse;
import cn.mixpay.engine.service.order.IPayOrderService;
import cn.mixpay.engine.type.SequenceType;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by qatang on 14-1-13.
 */
public class PayOrderCreateExecutor extends AbstractPayExecutor {
    private IdGeneratorService idGeneratorService;
    private MerchantSelectablePayPlatformService merchantSelectablePayPlatformService;
    private MerchantSelectablePayModeService merchantSelectablePayModeService;
    private MerchantAppService merchantAppService;
    private IPayOrderService payOrderService;

    @Override
    public boolean execute(IPayChainParams payChainParams, IPayChainResult payChainResult) throws Exception {
        CommonPayChainParams commonPayChainParams = (CommonPayChainParams)payChainParams;
        CommonPayChainResult commonPayChainResult = (CommonPayChainResult)payChainResult;

        PayOrderForm form = (PayOrderForm)commonPayChainParams.getForm();
        IResponse res = commonPayChainResult.getResponse();

        MerchantApp merchantApp = merchantAppService.findById(Long.valueOf(form.getMerchantAppId()));
        if (merchantApp == null) {
            String msg = String.format("创建订单失败：未查询到id=%s的商户app", form.getMerchantAppId());
            logger.error(msg);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + msg);
            return false;
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
            return false;
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
            return false;
        }
        merchantSelectablePayPlatform = merchantSelectablePayPlatformList.get(0);

        PayOrder payOrder = commonPayChainResult.getPayOrder();
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

        if (!StringUtils.isEmpty(form.getRemoteIp())) {
            payOrder.setRemoteIp(form.getRemoteIp());
        }

        try {
            payOrderService.create(payOrder);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            res.setCode(ErrorType.DATA_ERROR.getValue());
            res.setMessage(ErrorType.DATA_ERROR.getName() + "，原因是：" + e.getMessage());
            return false;
        }
        return true;
    }

    public void setIdGeneratorService(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    public void setMerchantSelectablePayPlatformService(MerchantSelectablePayPlatformService merchantSelectablePayPlatformService) {
        this.merchantSelectablePayPlatformService = merchantSelectablePayPlatformService;
    }

    public void setMerchantSelectablePayModeService(MerchantSelectablePayModeService merchantSelectablePayModeService) {
        this.merchantSelectablePayModeService = merchantSelectablePayModeService;
    }

    public void setMerchantAppService(MerchantAppService merchantAppService) {
        this.merchantAppService = merchantAppService;
    }

    public void setPayOrderService(IPayOrderService payOrderService) {
        this.payOrderService = payOrderService;
    }
}
