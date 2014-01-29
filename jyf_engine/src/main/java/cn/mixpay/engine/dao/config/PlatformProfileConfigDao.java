package cn.mixpay.engine.dao.config;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;

/**
 * User: leiming
 */
public interface PlatformProfileConfigDao {
    public PlatformProfileConfig getPlatformProfileConfigByPlatform(PlatformType platformType);
}
