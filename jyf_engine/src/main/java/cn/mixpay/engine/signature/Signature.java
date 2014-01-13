package cn.mixpay.engine.signature;

import net.sf.json.JSONObject;

/**
 * 签名
 * User: leiming
 */
public interface Signature {
    public String sign(JSONObject json);

}
