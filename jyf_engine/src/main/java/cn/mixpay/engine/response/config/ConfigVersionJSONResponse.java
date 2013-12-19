package cn.mixpay.engine.response.config;

import cn.mixpay.core.entity.order.PayOrder;
import cn.mixpay.engine.response.AbstractResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by qatang on 13-12-18.
 */
public class ConfigVersionJSONResponse extends AbstractResponse<String, JSONObject> {
    public final static String KEY_VERSION = "version";

    @Override
    public JSONObject convert(String version) {
        return convertToJSONObject(version);
    }

    protected JSONObject convertToJSONObject(String version) {
        if (StringUtils.isEmpty(version)) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();

        jsonObject.put(KEY_VERSION, version);

        return jsonObject;
    }

    protected JSONArray convertToJSONArray(List<String> versionList) {
        if (versionList == null || versionList.size() == 0) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for (String version : versionList) {
            jsonArray.add(this.convertToJSONObject(version));
        }
        return jsonArray;
    }

}
