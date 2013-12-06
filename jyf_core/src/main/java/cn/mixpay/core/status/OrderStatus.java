package cn.mixpay.core.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum OrderStatus {
    ALL("全部", 0),
    ALIVE("存活中", 1),
    CLOSED("已关闭", 2),
    TIMEOUT("已超时", 3);

    private static Logger logger = LoggerFactory.getLogger(OrderStatus.class);

    private static final Map<Integer, OrderStatus> _MAP = new HashMap<Integer, OrderStatus>();
    private static List<OrderStatus> _LIST = new ArrayList<OrderStatus>();
    private static List<OrderStatus> _ALLLIST = new ArrayList<OrderStatus>();
    static {
        for(OrderStatus orderStatus : OrderStatus.values()){
            _MAP.put(orderStatus.getValue(), orderStatus);
            _ALLLIST.add(orderStatus);
            if (!orderStatus.equals(ALL)) {
                _LIST.add(orderStatus);
            }
        }

        synchronized (_LIST) {
            _LIST = Collections.unmodifiableList(_LIST);
        }
        synchronized (_ALLLIST) {
            _ALLLIST = Collections.unmodifiableList(_ALLLIST);
        }
    }

    private String name;
    private int value;

    private OrderStatus(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public static OrderStatus get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OrderStatus> list() {
        return _LIST;
    }

    public static List<OrderStatus> listAll() {
        return _ALLLIST;
    }
}
