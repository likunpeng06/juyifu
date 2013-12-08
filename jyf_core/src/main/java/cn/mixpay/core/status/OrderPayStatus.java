package cn.mixpay.core.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum OrderPayStatus {
    ALL("全部"),
    UNPAY("未支付"),
    PAY_SUCCESS("支付成功"),
    PAY_FAILURE("支付失败");

    private static Logger logger = LoggerFactory.getLogger(OrderPayStatus.class);

    private static final Map<Integer, OrderPayStatus> _MAP = new HashMap<Integer, OrderPayStatus>();
    private static List<OrderPayStatus> _LIST = new ArrayList<OrderPayStatus>();
    private static List<OrderPayStatus> _ALLLIST = new ArrayList<OrderPayStatus>();
    static {
        for(OrderPayStatus orderPayStatus : OrderPayStatus.values()){
            _MAP.put(orderPayStatus.getValue(), orderPayStatus);
            _ALLLIST.add(orderPayStatus);
            if (!orderPayStatus.equals(ALL)) {
                _LIST.add(orderPayStatus);
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

    private OrderPayStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue() {
        return this.ordinal();
    }

    public static OrderPayStatus get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OrderPayStatus> list() {
        return _LIST;
    }

    public static List<OrderPayStatus> listAll() {
        return _ALLLIST;
    }
}
