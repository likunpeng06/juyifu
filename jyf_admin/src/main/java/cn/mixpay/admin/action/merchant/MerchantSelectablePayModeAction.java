package cn.mixpay.admin.action.merchant;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.merchant.MerchantSelectablePayModeService;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.admin.utils.PageUtils;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.entity.merchant.MerchantSelectablePayMode;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.type.PayType;
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

public class MerchantSelectablePayModeAction extends BaseAction {

    private MerchantService merchantService;
    private MerchantSelectablePayModeService merchantSelectablePayModeService;

    private Merchant merchant;
    private MerchantSelectablePayMode merchantSelectablePayMode;

    private List<Merchant> merchantList;
    private List<MerchantSelectablePayMode> merchantSelectablePayModeList;

    private String username;
    private String name;
    private String mobile;
    private Date beginDate;
    private Date endDate;

    private Integer statusValue;

    private List<PayType> payTypeList;
    private List<Integer> payTypeValueList;

    public String handle() {
        logger.info("进入查询商户");
        HttpServletRequest request = ServletActionContext.getRequest();

        List<Criterion> criterionList = new ArrayList<Criterion>();

        if (StringUtils.isNotBlank(this.getUsername())) {
            criterionList.add(Restrictions.like("username", "%" + StringUtils.trim(this.getUsername()) + "%"));
        }
        if (StringUtils.isNotBlank(this.getName())) {
            criterionList.add(Restrictions.like("name", "%" + StringUtils.trim(this.getName()) + "%"));
        }
        if (StringUtils.isNotBlank(this.getMobile())) {
            criterionList.add(Restrictions.like("mobile", "%" + StringUtils.trim(this.getMobile()) + "%"));
        }

        if (this.getStatusValue() != null && this.getStatusValue() > 0) {
            criterionList.add(Restrictions.eq("status", this.getStatusValue() > 0));
        }

        if (beginDate != null) {
            criterionList.add(Restrictions.ge("createdTime", beginDate));
        }
        if (endDate != null) {
            criterionList.add(Restrictions.le("createdTime", endDate));
        }

        merchantList = merchantService.findByExample(null, criterionList, this.getPageBean(), Order.desc("id"));

        this.setPageString(PageUtils.getPageString(request, merchantService.getPageBean(null, criterionList, this.getPageBean())));

        logger.info("查询商户结束");
        return "list";

    }

    public String view() {
        merchant = merchantService.findById(merchant.getId());

        MerchantSelectablePayMode merchantSelectablePayMode = new MerchantSelectablePayMode();
        merchantSelectablePayMode.setMerchantId(merchant.getId());
        merchantSelectablePayModeList = merchantSelectablePayModeService.findByExample(merchantSelectablePayMode, null);

        List<PayType> tmpPayTypeList = new ArrayList<PayType>();
        for (PayType payType : PayType.list()) {
            boolean flag = false;
            for (MerchantSelectablePayMode mode : merchantSelectablePayModeList) {
                if (payType.equals(mode.getPayType())) {
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

    public String manageMode() {
        if (merchantSelectablePayModeList != null && merchantSelectablePayModeList.size() > 0) {
            for (MerchantSelectablePayMode mode : merchantSelectablePayModeList) {
                merchantSelectablePayModeService.delete(mode.getId());
            }
        }
        return view();
    }

    public String manageType() {
        if (payTypeValueList != null && payTypeValueList.size() > 0) {
            for (Integer payTypeValue : payTypeValueList) {
                PayType payType = PayType.get(payTypeValue);
                MerchantSelectablePayMode mode = new MerchantSelectablePayMode();
                mode.setMerchantId(merchant.getId());
                mode.setPayType(payType);
                mode.setStatus(EnableDisableStatus.ENABLE);
                merchantSelectablePayModeService.save(mode);
            }
        }
        return view();
    }

    public String optStatus() {
        HttpServletResponse response = ServletActionContext.getResponse();

        merchantSelectablePayMode = merchantSelectablePayModeService.findById(merchantSelectablePayMode.getId());
        String statusName = merchantSelectablePayMode.getStatus().getName();
        EnableDisableStatus enableDisableStatus = merchantSelectablePayMode.getStatus().equals(EnableDisableStatus.ENABLE) ? EnableDisableStatus.DISABLE : EnableDisableStatus.ENABLE;
        merchantSelectablePayMode.setStatus(enableDisableStatus);

        merchantSelectablePayModeService.update(merchantSelectablePayMode);

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

    public List<EnableDisableStatus> getAllEnableDisableStatusList() {
        return EnableDisableStatus.listAll();
    }

    public List<PayType> getPayTypeList() {
        return payTypeList;
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

    public List<Merchant> getMerchantList() {
        return merchantList;
    }

    public void setMerchantList(List<Merchant> merchantList) {
        this.merchantList = merchantList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }

    public MerchantSelectablePayModeService getMerchantSelectablePayModeService() {
        return merchantSelectablePayModeService;
    }

    public void setMerchantSelectablePayModeService(MerchantSelectablePayModeService merchantSelectablePayModeService) {
        this.merchantSelectablePayModeService = merchantSelectablePayModeService;
    }

    public MerchantSelectablePayMode getMerchantSelectablePayMode() {
        return merchantSelectablePayMode;
    }

    public void setMerchantSelectablePayMode(MerchantSelectablePayMode merchantSelectablePayMode) {
        this.merchantSelectablePayMode = merchantSelectablePayMode;
    }

    public List<MerchantSelectablePayMode> getMerchantSelectablePayModeList() {
        return merchantSelectablePayModeList;
    }

    public void setMerchantSelectablePayModeList(List<MerchantSelectablePayMode> merchantSelectablePayModeList) {
        this.merchantSelectablePayModeList = merchantSelectablePayModeList;
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
}
