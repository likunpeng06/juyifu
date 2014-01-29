package cn.mixpay.engine.service.impl.sign;

import cn.mixpay.engine.response.order.CreatePayOrderJSONResponse;
import cn.mixpay.engine.service.sign.ISignService;
import cn.mixpay.engine.signature.ISignature;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * User: leiming
 */
public class SignServiceImpl implements ISignService{
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * key:平台类型value
     */
    private Map<String,ISignature> signatureBinder;

    @Override
    public String sign(JSONObject jsonData) {
        String binderKey = buildBinderKey(jsonData);
        if(binderKey == null){
            logger.error("无法构建签名绑定器,数据内容为{}",jsonData);
            return null;
        }
        ISignature signature = signatureBinder.get(binderKey);
        if(signature == null){
            logger.error("无法获得{}对应的签名器,请检查配置",binderKey);
            return null;
        }

        return signature.sign(jsonData);
    }

    private String buildBinderKey(JSONObject jsonData){
        String keyPlatform = jsonData.getString(CreatePayOrderJSONResponse.KEY_PLATFORM);
        if(keyPlatform == null){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(keyPlatform);
        return sb.toString();
    }

    public void setSignatureBinder(Map<String, ISignature> signatureBinder) {
        this.signatureBinder = signatureBinder;
    }
}
