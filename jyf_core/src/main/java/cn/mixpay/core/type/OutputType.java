package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 输出数据格式
 * Created by qatang on 13-12-18.
 */
public enum OutputType {
    JSON("json", 1),
    XML("xml", 2),
    RAW("raw", 3);

    private static Logger logger = LoggerFactory.getLogger(OutputType.class);

    private static final Map<Integer, OutputType> _MAP = new HashMap<Integer, OutputType>();
    static {
        for(OutputType outputType : OutputType.values()){
            _MAP.put(outputType.getValue(), outputType);
        }
    }

    private String name;
    private int value;

    private OutputType(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public static OutputType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
