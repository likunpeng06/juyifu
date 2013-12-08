package cn.mixpay.admin.action.business;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.entity.user.Role;
import cn.mixpay.admin.entity.user.User;
import cn.mixpay.admin.entity.user.UserRole;
import cn.mixpay.admin.service.business.PayPlatformConfigService;
import cn.mixpay.admin.service.user.RoleService;
import cn.mixpay.admin.service.user.UserRoleService;
import cn.mixpay.admin.service.user.UserService;
import cn.mixpay.admin.utils.PageUtils;
import cn.mixpay.core.entity.PayPlatformConfig;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;
import cn.mixpay.core.utils.CharsetConstant;
import cn.mixpay.core.utils.CoreStringUtils;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayPlatformConfigAction extends BaseAction {

    private PayPlatformConfigService payPlatformConfigService;

    private Integer platformTypeId;
    private PlatformType platformType;
    private PayPlatformConfig payPlatformConfig;

    private List<PayPlatformConfig> payPlatformConfigList;
    private List<PayType> payTypeList;
    private List<Integer> payTypeValueList;

    public String handle() {
        return "list";
    }

    public String view() {
        platformType = PlatformType.get(platformTypeId);

        PayPlatformConfig payPlatformConfig = new PayPlatformConfig();
        payPlatformConfig.setPlatformType(platformType);
        payPlatformConfigList = payPlatformConfigService.findByExample(payPlatformConfig, null);

        List<PayType> tmpPayTypeList = new ArrayList<PayType>();
        for (PayType payType : PayType.list()) {
            boolean flag = false;
            for (PayPlatformConfig config : payPlatformConfigList) {
                if (payType.equals(config.getPayType())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                tmpPayTypeList.add(payType);
            }
        }
        payTypeList = tmpPayTypeList;
        return "view";
    }

    public String manage() {
        if (payPlatformConfigList != null && payPlatformConfigList.size() > 0) {
            for (PayPlatformConfig config : payPlatformConfigList) {
                payPlatformConfigService.delete(config.getId());
            }
        } else {
            for (Integer payTypeValue : payTypeValueList) {
                PayType payType = PayType.get(payTypeValue);
                PayPlatformConfig config = new PayPlatformConfig();
                platformType = PlatformType.get(platformTypeId);
                config.setPlatformType(platformType);
                config.setPayType(payType);
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

    public List<PlatformType> getPlatformTypeList() {
        return PlatformType.list();
    }

    public PayPlatformConfigService getPayPlatformConfigService() {
        return payPlatformConfigService;
    }

    public void setPayPlatformConfigService(PayPlatformConfigService payPlatformConfigService) {
        this.payPlatformConfigService = payPlatformConfigService;
    }

    public Integer getPlatformTypeId() {
        return platformTypeId;
    }

    public void setPlatformTypeId(Integer platformTypeId) {
        this.platformTypeId = platformTypeId;
    }

    public PlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(PlatformType platformType) {
        this.platformType = platformType;
    }

    public List<PayPlatformConfig> getPayPlatformConfigList() {
        return payPlatformConfigList;
    }

    public void setPayPlatformConfigList(List<PayPlatformConfig> payPlatformConfigList) {
        this.payPlatformConfigList = payPlatformConfigList;
    }

    public List<PayType> getPayTypeList() {
        return payTypeList;
    }

    public void setPayTypeList(List<PayType> payTypeList) {
        this.payTypeList = payTypeList;
    }

    public List<Integer> getPayTypeValueList() {
        return payTypeValueList;
    }

    public void setPayTypeValueList(List<Integer> payTypeValueList) {
        this.payTypeValueList = payTypeValueList;
    }

    public PayPlatformConfig getPayPlatformConfig() {
        return payPlatformConfig;
    }

    public void setPayPlatformConfig(PayPlatformConfig payPlatformConfig) {
        this.payPlatformConfig = payPlatformConfig;
    }
}
