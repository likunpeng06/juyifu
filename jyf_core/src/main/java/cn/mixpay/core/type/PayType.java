package cn.mixpay.core.type;

/**
 * Created by qatang on 13-12-6.
 */
public enum PayType {
    ALIPAY_BALANCE(1, "支付宝余额支付");

    final private int value;
    final private String name;

    private PayType(int value, String name){
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
