package cn.mixpay.engine.generator.impl.generator;

import cn.mixpay.core.utils.CoreDateUtils;
import cn.mixpay.engine.dao.generator.IdGeneratorDao;
import cn.mixpay.engine.generator.IdGeneratorService;
import cn.mixpay.engine.type.SequenceType;

import java.util.Date;

/**
 * Created by qatang on 13-12-16.
 */
public class IdGeneratorServiceImpl implements IdGeneratorService {
    private IdGeneratorDao idGeneratorDao;

    @Override
    public Long generateAsDate(SequenceType sequence) {
        return null;
    }

    @Override
    public Long generateAsDate(SequenceType sequence, int count) {
        if (sequence == null) {
            return null;
        }

        Long nextValue = generate(sequence, count);
        if (nextValue == null || nextValue.longValue() == 0L) {
            return null;
        }
        String date = CoreDateUtils.formatDate(new Date(), "yyMMdd");
        String str = String.format("%s%03d%010d", date, 0, nextValue
                .longValue());

        return Long.valueOf(str);
    }

    @Override
    public Long generate(SequenceType sequence) {
        return generate(sequence, 1);
    }

    @Override
    public Long generate(SequenceType sequence, int count) {
        return idGeneratorDao.generate(sequence, count);
    }

    public void setIdGeneratorDao(IdGeneratorDao idGeneratorDao) {
        this.idGeneratorDao = idGeneratorDao;
    }
}
