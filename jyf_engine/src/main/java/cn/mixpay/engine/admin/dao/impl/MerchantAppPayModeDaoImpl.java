package cn.mixpay.engine.admin.dao.impl;

import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.engine.admin.dao.MerchantAppPayModeDao;
import cn.mixpay.engine.dao.impl.AbstractBaseDaoImpl;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.io.Serializable;
import java.sql.SQLException;

/**
 * Created by qatang on 13-12-12.
 */
public class MerchantAppPayModeDaoImpl extends AbstractBaseDaoImpl<MerchantAppPayMode> implements MerchantAppPayModeDao {

    @Override
    public void deleteByAppId(final Serializable id) {
        this.getHibernateTemplate().execute(new HibernateCallback<Boolean>() {
            @Override
            public Boolean doInHibernate(Session session) throws HibernateException, SQLException {
                String hql = "DELETE FROM MerchantAppPayMode m WHERE m.appId= :appId";
                Query query = session.createQuery(hql);
                query.setParameter("appId", id);
                query.executeUpdate();
                return true;
            }
        });
    }
}
