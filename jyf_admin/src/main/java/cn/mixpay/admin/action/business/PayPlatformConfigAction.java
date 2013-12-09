package cn.mixpay.admin.action.business;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.business.PayPlatformConfigService;
import cn.mixpay.core.entity.config.PayPlatformConfig;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class PayPlatformConfigAction extends BaseAction {

    private PayPlatformConfigService payPlatformConfigService;

    private Integer payTypeId;
    private PayType payType;
    private PayPlatformConfig payPlatformConfig;

    private List<PayPlatformConfig> payPlatformConfigList;
    private List<PlatformType> platformTypeList;
    private List<Integer> platformTypeValueList;

    public String handle() {
        return "list";
    }

    public String view() {
        payType = PayType.get(payTypeId);

        PayPlatformConfig payPlatformConfig = new PayPlatformConfig();
        payPlatformConfig.setPayType(payType);
        payPlatformConfigList = payPlatformConfigService.findByExample(payPlatformConfig, null);

        List<PlatformType> tmpPlatformTypeList = new ArrayList<PlatformType>();
        for (PlatformType platformType : PlatformType.list()) {
            boolean flag = false;
            for (PayPlatformConfig config : payPlatformConfigList) {
                if (platformType.equals(config.getPlatformType())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                tmpPlatformTypeList.add(platformType);
            }
        }
        platformTypeList = tmpPlatformTypeList;
        return "view";
    }

    public String manage() {
        if (payPlatformConfigList != null && payPlatformConfigList.size() > 0) {
            for (PayPlatformConfig config : payPlatformConfigList) {
                payPlatformConfigService.delete(config.getId());
            }
        }

        if (platformTypeValueList != null && platformTypeValueList.size() > 0) {
            for (Integer platformTypeValue : platformTypeValueList) {
                PlatformType platformType = PlatformType.get(platformTypeValue);
                PayPlatformConfig config = new PayPlatformConfig();
                payType = PayType.get(payTypeId);
                config.setPayType(payType);
                config.setPlatformType(platformType);
                config.setStatus(EnableDisableStatus.ENABLE);
                payPlatformConfigService.save(config);
            }
        }
        return view();
    }

    public String optStatus() {
        HttpServletResponse response = ServletActionContext.getResponse();

        payPlatformConfig = payPlatformConfigService.findById(payPlatformConfig.getId());
        String statusName = payPlatformConfig.getStatus().getName();
        EnableDisableStatus enableDisableStatus = payPlatformConfig.getStatus().equals(EnableDisableStatus.ENABLE) ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        payPlatformConfig.setStatus(enableDisableStatus);

        payPlatformConfigService.update(payPlatformConfig);

        JSONObject obj = new JSONObject();
        obj.put("statusName", statusName);
        writeRs(response, obj);

        return Action.NONE;
    }

    public EnableDisableStatus getEnableStatus() {
        return EnableDisableStatus.ENABLE;
    }

    public EnableDisableStatus getDisableStatus() {
        return EnableDisableStatus.DISABLE;
    }

    public List<PayType> getPayTypeList() {
        return PayType.list();
    }

    public PayPlatformConfigService getPayPlatformConfigService() {
        return payPlatformConfigService;
    }

    public void setPayPlatformConfigService(PayPlatformConfigService payPlatformConfigService) {
        this.payPlatformConfigService = payPlatformConfigService;
    }

    public List<PayPlatformConfig> getPayPlatformConfigList() {
        return payPlatformConfigList;
    }

    public void setPayPlatformConfigList(List<PayPlatformConfig> payPlatformConfigList) {
        this.payPlatformConfigList = payPlatformConfigList;
    }

    public PayPlatformConfig getPayPlatformConfig() {
        return payPlatformConfig;
    }

    public void setPayPlatformConfig(PayPlatformConfig payPlatformConfig) {
        this.payPlatformConfig = payPlatformConfig;
    }

    public Integer getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(Integer payTypeId) {
        this.payTypeId = payTypeId;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public List<PlatformType> getPlatformTypeList() {
        return platformTypeList;
    }

    public void setPlatformTypeList(List<PlatformType> platformTypeList) {
        this.platformTypeList = platformTypeList;
    }

    public List<Integer> getPlatformTypeValueList() {
        return platformTypeValueList;
    }

    public void setPlatformTypeValueList(List<Integer> platformTypeValueList) {
        this.platformTypeValueList = platformTypeValueList;
    }
}
