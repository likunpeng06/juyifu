/**
 * 
 */
package cn.mixpay.engine.dao.generator;


import cn.mixpay.engine.type.SequenceType;

/**
 * @author qatang
 *
 */
public interface IdGeneratorDao {

	/**
	 * 获取自增ID，使用行级锁进行操作
	 * @return
	 */
	public Long generate(SequenceType sequence);
	
	/**
	 * 获取自增ID若干个，使用行级锁进行操作
	 * @param count
	 * @return
	 */
	public Long generate(SequenceType sequence, int count);
}
