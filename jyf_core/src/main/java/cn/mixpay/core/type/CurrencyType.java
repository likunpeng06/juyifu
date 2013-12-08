package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum CurrencyType {
    ALL("全部"),
    RMB("人民币"),
    DOLLAR("美元");

    private static Logger logger = LoggerFactory.getLogger(CurrencyType.class);

    private static final Map<Integer, CurrencyType> _MAP = new HashMap<Integer, CurrencyType>();
    private static List<CurrencyType> _LIST = new ArrayList<CurrencyType>();
    private static List<CurrencyType> _ALLLIST = new ArrayList<CurrencyType>();
    static {
        for(CurrencyType currencyType : CurrencyType.values()){
            _MAP.put(currencyType.getValue(), currencyType);
            _ALLLIST.add(currencyType);
            if (!currencyType.equals(ALL)) {
                _LIST.add(currencyType);
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

    private CurrencyType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return this.ordinal();
    }

    public static CurrencyType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<CurrencyType> list() {
        return _LIST;
    }

    public static List<CurrencyType> listAll() {
        return _ALLLIST;
    }
}
