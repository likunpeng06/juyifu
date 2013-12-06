package cn.mixpay.core.type;

/**
 * Created by qatang on 13-12-6.
 */
public enum PlatformType {
    PLATFORM_ALIPAY(1, "支付宝");

    final private int value;
    final private String name;

    private PlatformType(int value, String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return value;
    }

    public String getName(){
        return name;
    }
}
