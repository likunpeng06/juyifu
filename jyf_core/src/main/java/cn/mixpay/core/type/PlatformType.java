package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum PlatformType {
    ALL("全部"),
    PLATFORM_ALIPAY("支付宝"),
    PLATFORM_BAIDUPAY("百付宝"),
    PLATFORM_UNIONPAY("银联支付");

    private static Logger logger = LoggerFactory.getLogger(PlatformType.class);

    private static final Map<Integer, PlatformType> _MAP = new HashMap<Integer, PlatformType>();
    private static List<PlatformType> _LIST = new ArrayList<PlatformType>();
    private static List<PlatformType> _ALLLIST = new ArrayList<PlatformType>();
    static {
        for(PlatformType platformType : PlatformType.values()){
            _MAP.put(platformType.ordinal(), platformType);
            _ALLLIST.add(platformType);
            if (!platformType.equals(ALL)) {
                _LIST.add(platformType);
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

    private PlatformType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue() {
        return this.ordinal();
    }

    public static PlatformType get(int ordinal){
        try{
            return _MAP.get(ordinal);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<PlatformType> list() {
        return _LIST;
    }

    public static List<PlatformType> listAll() {
        return _ALLLIST;
    }
}
