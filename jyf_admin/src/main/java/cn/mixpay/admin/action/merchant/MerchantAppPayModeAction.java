package cn.mixpay.admin.action.merchant;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.merchant.MerchantAppPayModeService;
import cn.mixpay.admin.service.merchant.MerchantAppService;
import cn.mixpay.admin.service.merchant.MerchantSelectablePayModeService;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.core.entity.merchant.MerchantAppPayMode;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;
import cn.mixpay.core.status.EnableDisableStatus;
import com.opensymphony.xwork2.Action;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MerchantAppPayModeAction extends BaseAction {

    private MerchantService merchantService;
    private MerchantAppService merchantAppService;
    private MerchantAppPayModeService merchantAppPayModeService;
    private MerchantSelectablePayModeService merchantSelectablePayModeService;

    private Merchant merchant;
    private MerchantApp merchantApp;
    private MerchantAppPayMode merchantAppPayMode;

    private List<MerchantAppPayMode> merchantAppPayModeList;
    private List<MerchantSelectablePayMode> merchantSelectablePayModeList;

    public String handle() {
        merchantApp = merchantAppService.findById(merchantApp.getId());
        merchant = merchantService.findById(merchantApp.getMerchantId());

        MerchantAppPayMode merchantAppPayMode = new MerchantAppPayMode();
        merchantAppPayMode.setAppId(merchantApp.getId());
        merchantAppPayModeList = merchantAppPayModeService.findByExample(merchantAppPayMode, null);

        MerchantSelectablePayMode merchantSelectablePayMode = new MerchantSelectablePayMode();
        merchantSelectablePayMode.setMerchantId(merchant.getId());
        merchantSelectablePayModeList = merchantSelectablePayModeService.findByExample(merchantSelectablePayMode, null);

        List<MerchantSelectablePayMode> tmpMerchantSelectablePayModeList = new ArrayList<MerchantSelectablePayMode>();
        for (MerchantSelectablePayMode tmpMerchantSelectablePayMode : merchantSelectablePayModeList) {
            boolean flag = false;
            for (MerchantAppPayMode mode : merchantAppPayModeList) {
                if (tmpMerchantSelectablePayMode.getPayType().equals(mode.getPayType())) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                tmpMerchantSelectablePayModeList.add(tmpMerchantSelectablePayMode);
            }
        }
        merchantSelectablePayModeList = tmpMerchantSelectablePayModeList;
        return "view";
    }

    public String manageMode() {
        if (merchantAppPayModeList != null && merchantAppPayModeList.size() > 0) {
            for (MerchantAppPayMode mode : merchantAppPayModeList) {
                merchantAppPayModeService.delete(mode.getId());
            }
        }
        return handle();
    }

    public String manageType() {
        if (merchantSelectablePayModeList != null && merchantSelectablePayModeList.size() > 0) {
            for (MerchantSelectablePayMode merchantSelectablePayMode : merchantSelectablePayModeList) {
                MerchantSelectablePayMode m = merchantSelectablePayModeService.findById(merchantSelectablePayMode.getId());

                MerchantAppPayMode mode = new MerchantAppPayMode();
                mode.setAppId(merchantApp.getId());
                mode.setPayType(m.getPayType());
                mode.setStatus(EnableDisableStatus.ENABLE);
                merchantAppPayModeService.save(mode);
            }
        }
        return handle();
    }

    public String optStatus() {
        HttpServletResponse response = ServletActionContext.getResponse();

        merchantAppPayMode = merchantAppPayModeService.findById(merchantAppPayMode.getId());
        String statusName = merchantAppPayMode.getStatus().getName();
        EnableDisableStatus enableDisableStatus = merchantAppPayMode.getStatus().equals(EnableDisableStatus.ENABLE) ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        merchantAppPayMode.setStatus(enableDisableStatus);

        merchantAppPayModeService.update(merchantAppPayMode);

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

    public MerchantAppService getMerchantAppService() {
        return merchantAppService;
    }

    public void setMerchantAppService(MerchantAppService merchantAppService) {
        this.merchantAppService = merchantAppService;
    }

    public MerchantAppPayModeService getMerchantAppPayModeService() {
        return merchantAppPayModeService;
    }

    public void setMerchantAppPayModeService(MerchantAppPayModeService merchantAppPayModeService) {
        this.merchantAppPayModeService = merchantAppPayModeService;
    }

    public MerchantSelectablePayModeService getMerchantSelectablePayModeService() {
        return merchantSelectablePayModeService;
    }

    public void setMerchantSelectablePayModeService(MerchantSelectablePayModeService merchantSelectablePayModeService) {
        this.merchantSelectablePayModeService = merchantSelectablePayModeService;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantApp getMerchantApp() {
        return merchantApp;
    }

    public void setMerchantApp(MerchantApp merchantApp) {
        this.merchantApp = merchantApp;
    }

    public MerchantAppPayMode getMerchantAppPayMode() {
        return merchantAppPayMode;
    }

    public void setMerchantAppPayMode(MerchantAppPayMode merchantAppPayMode) {
        this.merchantAppPayMode = merchantAppPayMode;
    }

    public List<MerchantAppPayMode> getMerchantAppPayModeList() {
        return merchantAppPayModeList;
    }

    public void setMerchantAppPayModeList(List<MerchantAppPayMode> merchantAppPayModeList) {
        this.merchantAppPayModeList = merchantAppPayModeList;
    }

    public List<MerchantSelectablePayMode> getMerchantSelectablePayModeList() {
        return merchantSelectablePayModeList;
    }

    public void setMerchantSelectablePayModeList(List<MerchantSelectablePayMode> merchantSelectablePayModeList) {
        this.merchantSelectablePayModeList = merchantSelectablePayModeList;
    }
}
