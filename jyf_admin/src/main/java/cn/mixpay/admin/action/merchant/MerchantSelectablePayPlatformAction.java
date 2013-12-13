package cn.mixpay.admin.action.merchant;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.merchant.MerchantSelectablePayModeService;
import cn.mixpay.admin.service.merchant.MerchantSelectablePayPlatformService;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.admin.utils.PageUtils;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayPlatform;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
import cn.mixpay.core.type.PlatformType;
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

public class MerchantSelectablePayPlatformAction extends BaseAction {

    private MerchantService merchantService;
    private MerchantSelectablePayModeService merchantSelectablePayModeService;
    private MerchantSelectablePayPlatformService merchantSelectablePayPlatformService;

    private Merchant merchant;
    private MerchantSelectablePayMode merchantSelectablePayMode;
    private MerchantSelectablePayPlatform merchantSelectablePayPlatform;

    private List<MerchantSelectablePayPlatform> merchantSelectablePayPlatformList;

    private List<PlatformType> platformTypeList;
    private List<Integer> platformTypeValueList;

    public String handle() {
        merchantSelectablePayMode = merchantSelectablePayModeService.findById(merchantSelectablePayMode.getId());
        merchant = merchantService.findById(merchantSelectablePayMode.getMerchantId());

        MerchantSelectablePayPlatform merchantSelectablePayPlatform = new MerchantSelectablePayPlatform();
        merchantSelectablePayPlatform.setPayModeId(merchantSelectablePayMode.getId());
        merchantSelectablePayPlatformList = merchantSelectablePayPlatformService.findByExample(merchantSelectablePayPlatform, null);

        List<PlatformType> tmpPlatformTypeList = new ArrayList<PlatformType>();
        for (PlatformType platformType : PlatformType.list()) {
            boolean flag = false;
            for (MerchantSelectablePayPlatform payPlatform : merchantSelectablePayPlatformList) {
                if (platformType.equals(payPlatform.getPlatformType())) {
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

    public String manageMode() {
        if (merchantSelectablePayPlatformList != null && merchantSelectablePayPlatformList.size() > 0) {
            for (MerchantSelectablePayPlatform payPlatform : merchantSelectablePayPlatformList) {
                merchantSelectablePayPlatformService.delete(payPlatform.getId());
            }
        }
        return handle();
    }

    public String manageType() {
        if (platformTypeValueList != null && platformTypeValueList.size() > 0) {
            for (Integer platformTypeValue : platformTypeValueList) {
                PlatformType platformType = PlatformType.get(platformTypeValue);
                MerchantSelectablePayPlatform payPlatform = new MerchantSelectablePayPlatform();
                payPlatform.setPayModeId(merchantSelectablePayMode.getId());
                payPlatform.setPlatformType(platformType);
                payPlatform.setStatus(EnableDisableStatus.ENABLE);
                merchantSelectablePayPlatformService.save(payPlatform);
            }
        }
        return handle();
    }

    public String optStatus() {
        HttpServletResponse response = ServletActionContext.getResponse();

        merchantSelectablePayPlatform = merchantSelectablePayPlatformService.findById(merchantSelectablePayPlatform.getId());
        String statusName = merchantSelectablePayPlatform.getStatus().getName();
        EnableDisableStatus enableDisableStatus = merchantSelectablePayPlatform.getStatus().equals(EnableDisableStatus.ENABLE) ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        merchantSelectablePayPlatform.setStatus(enableDisableStatus);

        merchantSelectablePayPlatformService.update(merchantSelectablePayPlatform);

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

    public MerchantService getMerchantService() {
        return merchantService;
    }

    public void setMerchantService(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    public MerchantSelectablePayModeService getMerchantSelectablePayModeService() {
        return merchantSelectablePayModeService;
    }

    public void setMerchantSelectablePayModeService(MerchantSelectablePayModeService merchantSelectablePayModeService) {
        this.merchantSelectablePayModeService = merchantSelectablePayModeService;
    }

    public MerchantSelectablePayPlatformService getMerchantSelectablePayPlatformService() {
        return merchantSelectablePayPlatformService;
    }

    public void setMerchantSelectablePayPlatformService(MerchantSelectablePayPlatformService merchantSelectablePayPlatformService) {
        this.merchantSelectablePayPlatformService = merchantSelectablePayPlatformService;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantSelectablePayMode getMerchantSelectablePayMode() {
        return merchantSelectablePayMode;
    }

    public void setMerchantSelectablePayMode(MerchantSelectablePayMode merchantSelectablePayMode) {
        this.merchantSelectablePayMode = merchantSelectablePayMode;
    }

    public MerchantSelectablePayPlatform getMerchantSelectablePayPlatform() {
        return merchantSelectablePayPlatform;
    }

    public void setMerchantSelectablePayPlatform(MerchantSelectablePayPlatform merchantSelectablePayPlatform) {
        this.merchantSelectablePayPlatform = merchantSelectablePayPlatform;
    }

    public List<MerchantSelectablePayPlatform> getMerchantSelectablePayPlatformList() {
        return merchantSelectablePayPlatformList;
    }

    public void setMerchantSelectablePayPlatformList(List<MerchantSelectablePayPlatform> merchantSelectablePayPlatformList) {
        this.merchantSelectablePayPlatformList = merchantSelectablePayPlatformList;
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
