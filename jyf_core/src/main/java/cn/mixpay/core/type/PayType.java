package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum PayType {
    ALL("全部"),

    ALIPAY_BALANCE("支付宝余额支付"),
    BANK_BALANCE("网银支付"),
    BAIDUPAY_BALANCE("百付宝余额支付"),
    GAME_PRE_PAID_CARD("游戏点卡"),
    PHONE_PRE_PAID_CARD("电话充值卡");

    private static Logger logger = LoggerFactory.getLogger(PayType.class);
    private final static String VERSION = "1.0";

    private static final Map<Integer, PayType> _MAP = new HashMap<Integer, PayType>();
    private static List<PayType> _LIST = new ArrayList<PayType>();
    private static List<PayType> _ALLLIST = new ArrayList<PayType>();
    static {
        for(PayType payType : PayType.values()){
            _MAP.put(payType.ordinal(), payType);
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

    private PayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return this.ordinal();
    }

    public static String getVersion() {
        return VERSION;
    }

    public static PayType get(int ordinal){
        try{
            return _MAP.get(ordinal);
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
