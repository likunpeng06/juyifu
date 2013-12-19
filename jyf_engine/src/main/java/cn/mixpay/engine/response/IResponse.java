package cn.mixpay.engine.response;

import cn.mixpay.core.type.OutputType;
import net.sf.json.JSONObject;

/**
 * 返回数据对象
 * Created by qatang on 13-12-18.
 */
public interface IResponse<K, V> {
    /**
     * 把数据对象转换成输出对象
     * @param k
     * @return
     */
    public V convert(K k);

    /**
     *
     * @param code
     */
    public void setCode(int code);

    /**
     *
     * @param message
     */
    public void setMessage(String message);

    /**
     *
     * @param v
     */
    public void setData(V v);
    /**
     * 输出结果
     * @return
     */
    public String output();

    /**
     * 按格式输出结果
     * @param outputType
     * @return
     */
    public String output(OutputType outputType);
}
