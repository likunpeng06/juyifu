package cn.mixpay.engine.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum SequenceType {
    ALL("全部"),
    ORDER("订单");

    private static Logger logger = LoggerFactory.getLogger(SequenceType.class);

    private static final Map<Integer, SequenceType> _MAP = new HashMap<Integer, SequenceType>();
    static {
        for(SequenceType sequenceType : SequenceType.values()){
            _MAP.put(sequenceType.getValue(), sequenceType);
        }
    }

    private String name;

    private SequenceType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return this.ordinal();
    }

    public static SequenceType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
