package cn.mixpay.engine.generator;

import cn.mixpay.engine.type.SequenceType;

/**
 * Created by qatang on 13-12-16.
 */
public interface IdGeneratorService {
    public Long generateAsDate(SequenceType sequence);

    public Long generateAsDate(SequenceType sequence, int count);

    public Long generate(SequenceType sequence);

    public Long generate(SequenceType sequence, int count);
}
