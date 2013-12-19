package cn.mixpay.engine.response.order;

import cn.mixpay.core.type.PayType;
import cn.mixpay.engine.response.AbstractResponse;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created by qatang on 13-12-18.
 */
public class MerchantAppPayModeJSONResponse extends AbstractResponse<List<PayType>, JSONArray> {
    public final static String KEY_PAY_TYPES = "pay_types";

    @Override
    public JSONArray convert(List<PayType> payTypeList) {
        return convertToJSONArray(payTypeList);
    }

    protected JSONArray convertToJSONArray(List<PayType> payTypeList) {
        if (payTypeList == null || payTypeList.size() == 0) {
            return null;
        }
        JSONArray jsonArray = new JSONArray();
        for (PayType payType : payTypeList) {
            jsonArray.add(payType.getValue());
        }
        return jsonArray;
    }

}
