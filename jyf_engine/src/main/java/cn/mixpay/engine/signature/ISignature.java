package cn.mixpay.engine.signature;

import net.sf.json.JSONObject;

/**
 * 签名
 * User: leiming
 */
public interface ISignature {
    public String sign(JSONObject json);

}
