package cn.mixpay.engine.service.impl.config;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.engine.dao.config.PlatformProfileConfigDao;
import cn.mixpay.engine.service.config.IPlatformProfileConfigService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: leiming
 */
public class PlatformProfileConfigServiceImpl implements IPlatformProfileConfigService{
    @Autowired
    private PlatformProfileConfigDao platformProfileConfigDao;

    @Override
    public PlatformProfileConfig getPlatformProfileConfigByPlatform(PlatformType platformType) {
        return platformProfileConfigDao.getPlatformProfileConfigByPlatform(platformType);
    }

    public void setPlatformProfileConfigDao(PlatformProfileConfigDao platformProfileConfigDao) {
        this.platformProfileConfigDao = platformProfileConfigDao;
    }
}
