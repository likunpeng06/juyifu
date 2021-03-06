package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum OsType {
    ALL("全部"),
    IOS("ios"),
    ANDROID("android"),
    WINDOWS("windows"),
    MAC("mac");

    private static Logger logger = LoggerFactory.getLogger(OsType.class);

    private static final Map<Integer, OsType> _MAP = new HashMap<Integer, OsType>();
    private static List<OsType> _LIST = new ArrayList<OsType>();
    private static List<OsType> _ALLLIST = new ArrayList<OsType>();
    static {
        for(OsType osType : OsType.values()){
            _MAP.put(osType.getValue(), osType);
            _ALLLIST.add(osType);
            if (!osType.equals(ALL)) {
                _LIST.add(osType);
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

    private OsType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue(){
        return this.ordinal();
    }

    public static OsType get(int value){
        try{
            return _MAP.get(value);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<OsType> list() {
        return _LIST;
    }

    public static List<OsType> listAll() {
        return _ALLLIST;
    }
}
