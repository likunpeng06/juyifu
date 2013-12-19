package cn.mixpay.engine.response;

import cn.mixpay.core.type.OutputType;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by qatang on 13-12-18.
 */
public abstract class AbstractResponse<K, V> implements IResponse<K, V> {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static String KEY_CODE = "code";
    public final static String KEY_MESSAGE = "message";
    public final static String KEY_DATA = "data";

    private int code;
    private String message;
    private V data;

    @Override
    public String output() {
        return output(OutputType.JSON);
    }
    @Override
    public String output(OutputType outputType) {
        switch (outputType) {
            case JSON:
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(KEY_CODE, code);
                jsonObject.put(KEY_MESSAGE, message);
                jsonObject.put(KEY_DATA, data);
                return jsonObject.toString();
            case XML:
                break;
            case RAW:
                break;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public V getData() {
        return data;
    }

    @Override
    public void setData(V data) {
        this.data = data;
    }
}
