package cn.mixpay.engine.response.config;

import cn.mixpay.engine.response.AbstractResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by qatang on 13-12-18.
 */
public class ConfigDataJSONResponse extends AbstractResponse<JSONObject, JSONObject> {
    public final static String KEY_SELECTABLE_PAY_TYPES = "selectable_pay_types";
    public final static String KEY_DICTIONARY = "dictionary";
    public final static String KEY_VERSION = "version";
    public final static String KEY_LATEST_PAY_TYPES = "latest_pay_types";
    public final static String KEY_LATEST_PLATFORMS = "latest_platforms";
    public final static String KEY_ID = "id";
    public final static String KEY_NAME = "name";

    @Override
    public JSONObject convert(JSONObject payPlatformConfig) {
        return convertToJSONObject(payPlatformConfig);
    }

    protected JSONObject convertToJSONObject(JSONObject payPlatformConfig) {
        return payPlatformConfig;
    }

    protected JSONArray convertToJSONArray(List<JSONObject> jsonObjectList) {
        if (jsonObjectList == null || jsonObjectList.size() == 0) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for (JSONObject jsonObject : jsonObjectList) {
            jsonArray.add(this.convertToJSONObject(jsonObject));
        }
        return jsonArray;
    }

}
