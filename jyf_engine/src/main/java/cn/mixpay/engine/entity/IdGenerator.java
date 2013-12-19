package cn.mixpay.engine.entity;


import java.io.Serializable;

/**
 * 发号器
 * @author qatang
 */
public class IdGenerator implements Serializable {
	private Long id;	//序号
	private String sequence;	//序列器的名字
	private Long currentValue;	//当前值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Long getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Long currentValue) {
        this.currentValue = currentValue;
    }
}
