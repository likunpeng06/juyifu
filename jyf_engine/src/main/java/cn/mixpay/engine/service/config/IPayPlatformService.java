package cn.mixpay.engine.service.config;

import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;

import java.util.List;

/**
 * Created by qatang on 13-12-10.
 */
public interface IPayPlatformService {
    /**
     * 查询最新支付方式和支付平台数据版本号
     * @return
     * @throws Exception
     */
    String getCurrentVersion() throws Exception;

    /**
     * 查询所有支付方式列表
     * @return
     * @throws Exception
     */
    List<PayType> getPayTypes() throws Exception;

    /**
     * 查询所有支付平台列表
     * @return
     * @throws Exception
     */
    List<PlatformType> getPlatformTypes() throws Exception;
}
