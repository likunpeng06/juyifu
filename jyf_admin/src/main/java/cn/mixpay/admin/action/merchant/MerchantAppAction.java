package cn.mixpay.admin.action.merchant;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.merchant.MerchantAppService;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.admin.utils.PageUtils;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.entity.merchant.MerchantApp;
import cn.mixpay.core.status.EnableDisableStatus;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by qatang on 13-12-11.
 */
public class MerchantAppAction extends BaseAction {
    private MerchantService merchantService;
    private MerchantAppService merchantAppService;

    private Merchant merchant;
    private MerchantApp merchantApp;

    private List<MerchantApp> merchantAppList;

    private Integer statusValue;

    public String handle() {
        logger.info("进入查询商户app");
        HttpServletRequest request = ServletActionContext.getRequest();

        merchant = merchantService.findById(merchantApp.getMerchantId());

        merchantAppList = merchantAppService.findByExample(merchantApp, this.getPageBean(), Order.desc("id"));

        this.setPageString(PageUtils.getPageString(request, merchantAppService.getPageBean(merchantApp, this.getPageBean())));

        logger.info("查询商户app结束");
        return "list";
    }

    public String manage() {
        logger.info("进入更新商户app信息");

        if (merchantApp == null) {
            this.errorForward(FAILURE, "非法请求");
        }

        this.emptyCheck(merchantApp.getName(), FAILURE, "商户app姓名不能为空");

        if (merchantApp.getId() == null) {
            // 通过检验，开始添加商户app
            merchantApp.setStatus(EnableDisableStatus.get(statusValue));
            merchantApp.setCreatedTime(new Date());
            merchantApp.setUpdatedTime(merchantApp.getCreatedTime());

            merchantAppService.save(merchantApp);
        } else {
            MerchantApp updateMerchantApp = merchantAppService.findById(merchantApp.getId());

            // 通过检验，开始修改商户app
            updateMerchantApp.setName(merchantApp.getName());
            updateMerchantApp.setStatus(EnableDisableStatus.get(statusValue));

            updateMerchantApp.setUpdatedTime(new Date());

            merchantAppService.update(updateMerchantApp);
        }

        super.setSuccessMessage("更新成功");
        super.setForwardUrl("/merchant/merchantApp.do?merchantApp.merchantId=" + merchantApp.getMerchantId());
        logger.info("更新商户app信息结束");
        return "success";
    }

    /**
     * 转向添加/修改商户app
     */
    public String input() {
        if (merchantApp == null) {
            merchantApp = new MerchantApp();
        }
        if (merchantApp.getId() != null) {
            merchantApp = merchantAppService.findById(merchantApp.getId());
            statusValue = merchantApp.getStatus().getValue();
            if (merchantApp == null) {
                this.errorForward(FAILURE, "要编辑的商户app不存在");
            }
        }

        return "inputForm";
    }

    public String view() {
        logger.info("进入查看商户app详情");

        this.emptyCheck(merchantApp, FAILURE, "非法请求");
        this.emptyCheck(merchantApp.getId(), FAILURE, "非法请求");

        merchantApp = merchantAppService.findById(merchantApp.getId());
        merchant = merchantService.findById(merchantApp.getMerchantId());
        this.emptyCheck(merchantApp, FAILURE, "商户app不存在");

        logger.info("查看商户app详情结束");
        return "view";
    }

    public String del() {
        merchantApp = merchantAppService.findById(merchantApp.getId());
        merchantAppService.delete(merchantApp.getId());

        Long merchantId = merchantApp.getMerchantId();
        merchantApp = new MerchantApp();
        merchantApp.setMerchantId(merchantId);
        return handle();
    }

    public List<EnableDisableStatus> getEnableDisableStatusList() {
        return EnableDisableStatus.list();
    }

    public MerchantService getMerchantService() {
        return merchantService;
    }

    public void setMerchantService(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public MerchantAppService getMerchantAppService() {
        return merchantAppService;
    }

    public void setMerchantAppService(MerchantAppService merchantAppService) {
        this.merchantAppService = merchantAppService;
    }

    public MerchantApp getMerchantApp() {
        return merchantApp;
    }

    public void setMerchantApp(MerchantApp merchantApp) {
        this.merchantApp = merchantApp;
    }

    public List<MerchantApp> getMerchantAppList() {
        return merchantAppList;
    }

    public void setMerchantAppList(List<MerchantApp> merchantAppList) {
        this.merchantAppList = merchantAppList;
    }
}
