package cn.mixpay.engine.dao.impl.config;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.engine.dao.config.PlatformProfileConfigDao;
import cn.mixpay.engine.dao.impl.AbstractBaseDaoImpl;

import java.util.List;

/**
 * User: leiming
 */
public class PlatformProfileConfigDaoImpl extends AbstractBaseDaoImpl<PlatformProfileConfig> implements PlatformProfileConfigDao{
    @Override
    public PlatformProfileConfig getPlatformProfileConfigByPlatform(PlatformType platformType) {
        if(platformType == null){
            return null;
        }
        StringBuffer hql = new StringBuffer("from PlatformProfileConfig where platformType = ?  ");
        List<PlatformProfileConfig> platformProfileConfigList = findByHQL(hql.toString(),null,platformType);
        PlatformProfileConfig platformProfileConfig = null;
        if(platformProfileConfigList != null && !platformProfileConfigList.isEmpty()){
            platformProfileConfig = platformProfileConfigList.get(0);
        }
        return platformProfileConfig;
    }
}
