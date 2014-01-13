package cn.mixpay.engine.signature.impl;

import cn.mixpay.engine.signature.AbstractSignature;
import net.sf.json.JSONObject;

/**
 * User: leiming
 */
public class AlipaySignature extends AbstractSignature{
    @Override
    public String sign(JSONObject json) {

        return null;
    }

    private String generateOrderInfo(JSONObject json){
        StringBuilder sb = new StringBuilder();
        /*
        sb.append("partner=\"");//合作者身份ID
        sb.append(Keys.DEFAULT_PARTNER);
        sb.append("\"&out_trade_no=\"");//商户网站唯一订单号
        sb.append(order.getExternalOrderId());// TODO 确定字段是否正确
        sb.append("\"&subject=\"");//商品名称
        sb.append(order.getProductName());
        sb.append("\"&body=\"");//商品详情
        sb.append(order.getMemo());// TODO 确定字段是否正确
        sb.append("\"&total_fee=\"");//总金额
        sb.append(order.getAmount());
        sb.append("\"&notify_url=\"");//服务器异步通知页面路径

        // 网址需要做URL编码
        sb.append(URLEncoder.encode("http://notify.java.jpxx.org/index.jsp"));//TODO 设置服务器异步通知地址
        sb.append("\"&service=\"").append(Keys.SERVICE_URL);//接口名称,固定值
        sb.append("\"&_input_charset=\"UTF-8");//参数编码字符集
        sb.append("\"&return_url=\"");
        sb.append(URLEncoder.encode("http://m.alipay.com"));// TODO 需最终确定
        sb.append("\"&payment_type=\"1");//支付类型
        sb.append("\"&seller_id=\"");//卖家支付宝账号
        sb.append(Keys.DEFAULT_SELLER);

        // 如果show_url值为空，可不传
        // sb.append("\"&show_url=\"");//商品展示地址
        sb.append("\"&it_b_pay=\"1m");//未付款交易的超时时间
        sb.append("\"");

        return sb.toString();
        */
        return null;
    }
}
