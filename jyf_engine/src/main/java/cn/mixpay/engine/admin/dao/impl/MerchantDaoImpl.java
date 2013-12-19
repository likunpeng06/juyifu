package cn.mixpay.engine.admin.dao.impl;

import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.engine.admin.dao.MerchantDao;
import cn.mixpay.engine.dao.impl.AbstractBaseDaoImpl;

import java.util.List;

/**
 * Created by qatang on 13-12-11.
 */
public class MerchantDaoImpl extends AbstractBaseDaoImpl<Merchant> implements MerchantDao {
    @Override
    public Merchant findByUsername(String username) {
        Merchant example = new Merchant();
        example.setUsername(username);
        List<Merchant> merchantList = this.findByExample(example, null);
        if (merchantList == null || merchantList.isEmpty()) {
            return null;
        }
        return merchantList.iterator().next();
    }
}
