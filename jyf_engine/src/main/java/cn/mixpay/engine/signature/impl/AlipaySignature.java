package cn.mixpay.engine.signature.impl;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.core.utils.CharsetConstant;
import cn.mixpay.core.utils.SignatureUtils;
import cn.mixpay.engine.response.order.CreatePayOrderJSONResponse;
import cn.mixpay.engine.signature.AbstractSignature;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * User: leiming
 */
public class AlipaySignature extends AbstractSignature{

    @Override
    public String sign(JSONObject json) {
        if(json == null){
            logger.error("订单JSON为null,无法签名");
            return null;
        }

        PlatformProfileConfig platformProfileConfig = getPlatformProfileConfig();
        if(platformProfileConfig == null){
            logger.error("[{}]平台对应的资料配置为null,无法正常签名",getPlatformType().getName());
            return null;
        }

        String info = generateOrderInfo(json,platformProfileConfig);
        String sign = SignatureUtils.sign(info, platformProfileConfig.getPrivateKey());
        try {
            if(sign != null){
                sign = URLEncoder.encode(sign, CharsetConstant.CHARSET_UTF8);
            }
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(),e);
            return null;
        }
        return sign;
    }

    @Override
    protected PlatformType getPlatformType(){
        return PlatformType.PLATFORM_ALIPAY;
    }

    private String generateOrderInfo(JSONObject json,PlatformProfileConfig platformProfileConfig){
        StringBuilder sb = new StringBuilder();

        sb.append("partner=\"");//合作者身份ID
        sb.append(platformProfileConfig.getPartner());
        sb.append("\"&out_trade_no=\"");//商户网站唯一订单号

        sb.append(json.getLong(CreatePayOrderJSONResponse.KEY_EXTERNAL_ORDER_ID));// TODO 确定字段是否正确
        sb.append("\"&subject=\"");//商品名称
        sb.append(json.getString(CreatePayOrderJSONResponse.KEY_PRODUCT_NAME));
        sb.append("\"&total_fee=\"");//总金额
        sb.append(json.getString(CreatePayOrderJSONResponse.KEY_AMOUNT));
        sb.append("\"&notify_url=\"");//服务器异步通知页面路径

        // 网址需要做URL编码
        if(platformProfileConfig.getNotifyUrl() !=null){
            try {
                sb.append(URLEncoder.encode(platformProfileConfig.getNotifyUrl(), CharsetConstant.CHARSET_UTF8));//TODO 设置服务器异步通知地址
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }

        sb.append("\"&service=\"").append(platformProfileConfig.getServiceUrl());//接口名称,固定值
        sb.append("\"&_input_charset=\"UTF-8");//参数编码字符集
        sb.append("\"&return_url=\"");
        if(platformProfileConfig.getReturnUrl() != null){
            try {
                sb.append(URLEncoder.encode(platformProfileConfig.getReturnUrl(), CharsetConstant.CHARSET_UTF8));// TODO "http://m.alipay.com" 需最终确定
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }

        sb.append("\"&payment_type=\"1");//支付类型
        sb.append("\"&seller_id=\"");//卖家支付宝账号
        sb.append(platformProfileConfig.getAccount());

        // 如果show_url值为空，可不传
        // sb.append("\"&show_url=\"");//商品展示地址
        sb.append("\"&it_b_pay=\"1m");//未付款交易的超时时间
        sb.append("\"");

        return sb.toString();
    }
}
