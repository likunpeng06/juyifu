package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum PayType {
    ALL("全部", 0),
    ALIPAY_BALANCE("支付宝余额支付", 1);

    private static Logger logger = LoggerFactory.getLogger(PayType.class);

    private static final Map<Integer, PayType> _MAP = new HashMap<Integer, PayType>();
    private static List<PayType> _LIST = new ArrayList<PayType>();
    private static List<PayType> _ALLLIST = new ArrayList<PayType>();
    static {
        for(PayType payType : PayType.values()){
            _MAP.put(payType.getValue(), payType);
            _ALLLIST.add(payType);
            if (!payType.equals(ALL)) {
                _LIST.add(payType);
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
    private int value;

    private PayType(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return value;
    }

    public static PayType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<PayType> list() {
        return _LIST;
    }

    public static List<PayType> listAll() {
        return _ALLLIST;
    }
}
