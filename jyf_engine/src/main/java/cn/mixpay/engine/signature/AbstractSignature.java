package cn.mixpay.engine.signature;

import cn.mixpay.core.entity.config.PlatformProfileConfig;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.engine.service.config.IPlatformProfileConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: leiming
 */
public abstract class AbstractSignature implements ISignature {
    protected final transient Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected IPlatformProfileConfigService platformProfileConfigService;

    protected PlatformProfileConfig getPlatformProfileConfig(){
        return platformProfileConfigService.getPlatformProfileConfigByPlatform(getPlatformType());
    }

    public void setPlatformProfileConfigService(IPlatformProfileConfigService platformProfileConfigService) {
        this.platformProfileConfigService = platformProfileConfigService;
    }

    abstract protected PlatformType getPlatformType();


}
