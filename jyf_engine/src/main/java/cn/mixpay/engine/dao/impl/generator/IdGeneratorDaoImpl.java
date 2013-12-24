/**
 * 
 */
package cn.mixpay.engine.dao.impl.generator;

import cn.mixpay.engine.dao.generator.IdGeneratorDao;
import cn.mixpay.engine.entity.IdGenerator;
import cn.mixpay.engine.type.SequenceType;
import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;


/**
 * @author qatang
 *
 */
public class IdGeneratorDaoImpl extends HibernateDaoSupport implements
        IdGeneratorDao {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public Long generate(SequenceType sequence) {
		return generate(sequence, 1);
	}

	@Override
	public Long generate(final SequenceType sequence, final int count) {
		return getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				Transaction tx = session.beginTransaction();	//开始事务
				try {
					IdGenerator generator = (IdGenerator)session.get(IdGenerator.class, new Long(sequence.getValue()), LockOptions.UPGRADE);
					
					Long returnValue = 0L;
					Long nextValue = 0L;
					
					int added = count;
					if (added <= 0) {
						// 至少增加一个
						added = 1;
					}
					
					if (generator == null) {
						if (sequence == null || sequence.getValue() <= 0) {
							throw new Exception("不合法的发号请求");
						}
						generator = new IdGenerator();
						generator.setId(new Long(sequence.getValue()));
						generator.setSequence(sequence.getName());
						
						returnValue = 1L;
						nextValue += added;
						
						generator.setCurrentValue(nextValue);
						
						session.save(generator);

					} else {
						returnValue = generator.getCurrentValue() + 1;
						nextValue = generator.getCurrentValue() + added;
						generator.setCurrentValue(nextValue);
						
						session.update(generator);
					}
					tx.commit();	//提交事务
					return returnValue;
				} catch (Exception e) {
					logger.error("generate id failed, id={}, name={}", sequence.getValue(), sequence.getName());
					logger.error(e.getMessage(), e);
					tx.rollback();	//回滚事务
				}
				return null;
			}
			
		});
	}

}
