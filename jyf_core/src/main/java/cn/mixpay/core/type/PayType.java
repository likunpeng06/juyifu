package cn.mixpay.core.type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by qatang on 13-12-6.
 */
public enum PayType {
    ALL("全部"),

    BANK_BALANCE_BC("中国银行"),
    BANK_BALANCE_ICBC("中国工商银行"),
    BANK_BALANCE_CMBC("招商银行"),
    BANK_BALANCE_CBC("中国建设银行"),
    BANK_BALANCE_BOCOM("交通银行"),
    BANK_BALANCE_ABC("中国农业银行"),
    BANK_BALANCE_SPD_BANK("上海浦东发展银行"),
    BANK_BALANCE_CIB("兴业银行"),
    //BANK_BALANCE_GDB("广发银行股份有限公司"),
    BANK_BALANCE_GDB("广东发展银行"),
    BANK_BALANCE_SDB("深圳发展银行"),
    BANK_BALANCE_CCB("中信银行"),
    BANK_BALANCE_CEB("光大银行"),
    BANK_BALANCE_MBC("民生银行"),
    BANK_BALANCE_HCCB("杭州银行"),
    BANK_BALANCE_NBCB("宁波银行"),
    BANK_BALANCE_FDBC("富滇银行"),
    BANK_BALANCE_PABC("平安银行"),
    BANK_BALANCE_SHBC("上海银行"),
    BANK_BALANCE_BRCB("北京农村商业银行"),
    BANK_BALANCE_ICBC_B2B("中国工商银行(B2B)"),
    BANK_BALANCE_CBC_B2B("中国建设银行(B2B)"),
    BANK_BALANCE_ABC_B2B("中国农业银行(B2B)"),
    BANK_BALANCE_SPD_BANK_B2B("上海浦东发展银行(B2B)"),
    BANK_BALANCE_PSBC("中国邮政储蓄银行"),
    BANK_BALANCE_NCXYS("农村信用社"),
    BANK_BALANCE_CZYH("村镇银行"),
    BANK_BALANCE_JCC("晋城市商业银行"),
    BANK_BALANCE_HKBEA("东亚银行"),
    BANK_BALANCE_HSBC("汇丰银行"),
    BANK_BALANCE_ADBC("中国农业发展银行"),
    BANK_BALANCE_RCU("农村合作银行"),
    BANK_BALANCE_NCSYYH("农村商业银行"),
    BANK_BALANCE_HXB("华夏银行"),
    BANK_BALANCE_HS("徽商银行股份有限公司"),
    BANK_BALANCE_CHARTERED("渣打银行"),
    BANK_BALANCE_CBHB("渤海银行"),
    BANK_BALANCE_EG("恒丰银行"),
    BANK_BALANCE_NCB("南洋商业银行"),
    BANK_BALANCE_CZ("浙商银行"),

    BANK_BALANCE_HYYH("华一银行"),
    BANK_BALANCE_GJKFYH("国家开发银行"),
    BANK_BALANCE_CSXYS("城市信用社"),
    BANK_BALANCE_CSSYYH("城市商业银行"),
    BANK_BALANCE_HSYH("恒生银行"),
    BANK_BALANCE_HQYH("花旗银行"),
    BANK_BALANCE_GSYH_WAP("工商银行WAP"),
    BANK_BALANCE_ZSYH_WAP("招商银行WAP"),
    BANK_BALANCE_JSYH_WAP("建设银行WAP"),

    BANK_BALANCE_NJYH("南京银行"),
    BANK_BALANCE_BJYH("北京银行"),
    BANK_BALANCE_SXJSYH("晋商银行"),
    BANK_BALANCE_WZYH("温州银行"),
    BANK_BALANCE_ZJCZSYYH("浙江稠州商业银行"),
    BANK_BALANCE_YDXYHZLS("尧都信用合作联社"),
    BANK_BALANCE_SDNXS("顺德农信社"),
    BANK_BALANCE_GZYH("广州银行"),
    BANK_BALANCE_ZHNCXYHZLS("珠海市农村信用合作联社"),
    BANK_BALANCE_HKYH("汉口银行"),
    BANK_BALANCE_SHNCSYYH("上海市农村商业银行"),
    BANK_BALANCE_GZNCXYHZS("广州市农村信用合作社"),
    BANK_BALANCE_BC_RM("中国银行(对公)"),
    BANK_BALANCE_CBC_RM("建设银行(对公)"),
    BANK_BALANCE_CMBC_RM("招商银行(对公)"),
    BANK_BALANCE_ICBC_RM("工商银行(对公)"),
    BANK_BALANCE_ABC_RM("农业银行(对公)"),

    BANK_BALANCE_PAY_BANK_BSYH("包商银行"),
    BANK_BALANCE_PAY_BANK_CSYH("长沙银行"),
    BANK_BALANCE_PAY_BANK_CDYH("承德银行"),
    BANK_BALANCE_PAY_BANK_CDNSYH("成都农商银行"),

    BANK_BALANCE_PAY_BANK_CQNCSYYH("重庆农村商业银行"),
    BANK_BALANCE_PAY_BANK_CQYH("重庆银行"),
    BANK_BALANCE_PAY_BANK_DLYH("大连银行"),
    BANK_BALANCE_PAY_BANK_DYSSYYH("东营市商业银行"),
    BANK_BALANCE_PAY_BANK_ERDSYH("鄂尔多斯银行"),
    BANK_BALANCE_PAY_BANK_FJSNCXYL("福建省农村信用社"),
    BANK_BALANCE_PAY_BANK_GYYH("贵阳银行"),
    BANK_BALANCE_PAY_BANK_GZNCSYYH("广州农村商业银行"),
    BANK_BALANCE_PAY_BANK_HEBYH("哈尔滨银行"),
    BANK_BALANCE_PAY_BANK_HNSNCXYS("湖南省农村信用社"),

    BANK_BALANCE_PAY_BANK_HBYH("河北银行"),
    BANK_BALANCE_PAY_BANK_JZYH("锦州银行"),
    BANK_BALANCE_PAY_BANK_JSCSNCSYYH("江苏常熟农村商业银行"),
    BANK_BALANCE_PAY_BANK_JIANGSYH("江苏银行"),
    BANK_BALANCE_PAY_BANK_JYNCSYYH("江阴农村商业银行"),
    BANK_BALANCE_PAY_BANK_JJYH("九江银行"),
    BANK_BALANCE_PAY_BANK_LZYH("兰州银行"),
    BANK_BALANCE_PAY_BANK_LJYH("龙江银行"),
    BANK_BALANCE_PAY_BANK_NCXY("南昌银行"),
    BANK_BALANCE_PAY_BANK_QHYH("青海银行"),

    BANK_BALANCE_PAY_BANK_QLYH("齐鲁银行"),
    BANK_BALANCE_PAY_BANK_SRYH("上饶银行"),
    BANK_BALANCE_PAY_BANK_SDNCYH("顺德农村商业银行"),
    BANK_BALANCE_PAY_BANK_TGYH("台州银行"),
    BANK_BALANCE_PAY_BANK_WHSSYYH("威海市商业银行"),
    BANK_BALANCE_PAY_BANK_WFYH("潍坊银行"),
    BANK_BALANCE_PAY_BANK_WLMQSYYH("乌鲁木齐商业银行"),
    BANK_BALANCE_PAY_BANK_WXNCSYYH("无锡农村商业银行"),
    BANK_BALANCE_PAY_BANK_YCSSYYH("宜昌市商业银行"),

    BANK_BALANCE_PAY_BANK_NBJZYH("鄞州银行"),
    BANK_BALANCE_PAY_BANK_WJNCSYYH("吴江农村商业银行"),
    BANK_BALANCE_PAY_BANK_YDNCSYYH("尧都农村商业银行"),
    BANK_BALANCE_PAY_BANK_YINCSSYYH("银川市商业银行"),
    BANK_BALANCE_PAY_BANK_ZJTLSYYH("浙江泰隆商业银行"),
    BANK_BALANCE_PAY_BANK_ZJMTSYYH("浙江民泰商业银行"),

    ALIPAY_BALANCE("支付宝余额支付"),
    BAIDUPAY_BALANCE("百付宝余额支付"),

    CMCC_SHENZHOU_PRE_PAID_CARD("神州行充值卡"),
    UNICOM_PRE_PAID_CARD("联通充值卡"),
    TELECOM_PRE_PAID_CARD("电信充值卡");

    private static Logger logger = LoggerFactory.getLogger(PayType.class);

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
