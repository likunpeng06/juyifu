package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum OrderType {
    ALL("全部"),
    COMMON("普通订单");

    private static Logger logger = LoggerFactory.getLogger(OrderType.class);

    private static final Map<Integer, OrderType> _MAP = new HashMap<Integer, OrderType>();
    private static List<OrderType> _LIST = new ArrayList<OrderType>();
    private static List<OrderType> _ALLLIST = new ArrayList<OrderType>();
    static {
        for(OrderType orderType : OrderType.values()){
            _MAP.put(orderType.getValue(), orderType);
            _ALLLIST.add(orderType);
            if (!orderType.equals(ALL)) {
                _LIST.add(orderType);
            }
        }

        synchronized (_LIST) {
            _LIST = Collections.unmodifiableList(_LIST);
        }
        synchronized (_LIST) {
            _ALLLIST = Collections.unmodifiableList(_ALLLIST);
        }
    }

    private String name;

    private OrderType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return this.ordinal();
    }

    public static OrderType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OrderType> list() {
        return _LIST;
    }

    public static List<OrderType> listAll() {
        return _ALLLIST;
    }
}
