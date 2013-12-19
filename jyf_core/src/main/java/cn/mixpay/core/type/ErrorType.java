package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 请求返回错误类型
 * Created by qatang on 13-12-18.
 */
public enum ErrorType {
    SUCCESS("操作成功", 0),
    PARAMETERS_ERROR("参数错误", 1000),
    DATA_ERROR("数据错误", 1001),
    SERVER_ERROR("服务器错误", 2000);

    private static Logger logger = LoggerFactory.getLogger(ErrorType.class);

    private static final Map<Integer, ErrorType> _MAP = new HashMap<Integer, ErrorType>();
    static {
        for(ErrorType errorType : ErrorType.values()){
            _MAP.put(errorType.getValue(), errorType);
        }
    }

    private String name;
    private int value;

    private ErrorType(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public static ErrorType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
