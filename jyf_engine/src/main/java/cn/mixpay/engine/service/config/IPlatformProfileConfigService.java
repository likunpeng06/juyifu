package cn.mixpay.engine.service.config;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;

/**
 * User: leiming
 */
public interface IPlatformProfileConfigService {
    public PlatformProfileConfig getPlatformProfileConfigByPlatform(PlatformType platformType);
}
