package cn.mixpay.core.status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-7.
 */
public enum EnableDisableStatus {
    ALL("全部"),
    ENABLE("启用"),
    DISABLE("禁用");

    private static Logger logger = LoggerFactory.getLogger(EnableDisableStatus.class);

    private static final Map<Integer, EnableDisableStatus> _MAP = new HashMap<Integer, EnableDisableStatus>();
    private static List<EnableDisableStatus> _LIST = new ArrayList<EnableDisableStatus>();
    private static List<EnableDisableStatus> _ALLLIST = new ArrayList<EnableDisableStatus>();
    static {
        for(EnableDisableStatus enableDisableStatus : EnableDisableStatus.values()){
            _MAP.put(enableDisableStatus.ordinal(), enableDisableStatus);
            _ALLLIST.add(enableDisableStatus);
            if (!enableDisableStatus.equals(ALL)) {
                _LIST.add(enableDisableStatus);
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

    private EnableDisableStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getValue() {
        return this.ordinal();
    }

    public static EnableDisableStatus get(int ordinal){
        try{
            return _MAP.get(ordinal);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<EnableDisableStatus> list() {
        return _LIST;
    }

    public static List<EnableDisableStatus> listAll() {
        return _ALLLIST;
    }
}
