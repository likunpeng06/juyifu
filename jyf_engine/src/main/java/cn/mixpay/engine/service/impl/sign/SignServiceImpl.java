package cn.mixpay.engine.service.impl.sign;

import cn.mixpay.engine.service.sign.SignService;
import cn.mixpay.engine.signature.Signature;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * User: leiming
 */
public class SignServiceImpl implements SignService{
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String,Signature> signatureBinder;

    @Override
    public String sign(JSONObject jsonData) {
        String binderKey = buildBinderKey(jsonData);
        if(binderKey == null){
            logger.error("无法构建签名绑定器,数据内容为{}",jsonData);
            return null;
        }
        Signature signature = signatureBinder.get(binderKey);
        if(signature == null){
            logger.error("无法获得{}对应的签名器,请检查配置",binderKey);
            return null;
        }

        return signature.sign(jsonData);
    }

    private String buildBinderKey(JSONObject jsonData){
        return null;
    }

    public Map<String, Signature> getSignatureBinder() {
        return signatureBinder;
    }

    public void setSignatureBinder(Map<String, Signature> signatureBinder) {
        this.signatureBinder = signatureBinder;
    }
}
