package cn.mixpay.engine.service.sign;

import net.sf.json.JSONObject;

/**
 * User: leiming
 */
public interface ISignService {

    public String sign(JSONObject jsonData);
}
