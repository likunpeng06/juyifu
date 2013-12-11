package cn.mixpay.admin.action.merchant;

import cn.mixpay.admin.action.BaseAction;
import cn.mixpay.admin.service.merchant.MerchantService;
import cn.mixpay.admin.utils.PageUtils;
import cn.mixpay.core.entity.merchant.Merchant;
import cn.mixpay.core.status.EnableDisableStatus;
import cn.mixpay.core.utils.CharsetConstant;
import cn.mixpay.core.utils.CoreStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qatang on 13-12-11.
 */
public class MerchantAction extends BaseAction {
    private MerchantService merchantService;

    private Merchant merchant;

    private String confirmPassword;

    private List<Merchant> merchantList;

    private String username;
    private String name;
    private String mobile;
    private Date beginDate;
    private Date endDate;

    private Integer statusValue;

    public String handle() {
        logger.info("进入查询商户");
        return "list";
    }

    public String query() {
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

    public String manage() {
        logger.info("进入更新商户信息");

        if (merchant == null) {
            this.errorForward(FAILURE, "非法请求");
        }

        this.emptyCheck(merchant.getUsername(), FAILURE, "商户名不能为空");
        this.emptyCheck(merchant.getName(), FAILURE, "商户姓名不能为空");

        if (merchant.getId() == null) {
            // 新商户需要判断密码输入是否匹配
            this.emptyCheck(merchant.getPassword(), FAILURE, "密码不能为空");
            this.emptyCheck(this.getConfirmPassword(), FAILURE, "请确认密码");

            if (!merchant.getPassword().equals(this.getConfirmPassword())) {
                this.errorForward(FAILURE, "两次密码输入不一致");
            }

            // 确认商户名不重复
            if (merchantService.findByUsername(merchant.getUsername()) != null) {
                this.errorForward(FAILURE, "商户名已存在");
            }

            // 通过检验，开始添加商户
            merchant.setStatus(EnableDisableStatus.get(statusValue));
            merchant.setPassword(CoreStringUtils.md5(merchant.getPassword(), CharsetConstant.CHARSET_UTF8));
            merchant.setCreatedTime(new Date());
            merchant.setUpdatedTime(merchant.getCreatedTime());

            merchantService.save(merchant);
        } else {
            Merchant updateMerchant = merchantService.findById(merchant.getId());

            // 是否设置了修改密码
            if (StringUtils.isNotBlank(merchant.getPassword()) || StringUtils.isNotBlank(this.getConfirmPassword())) {
                // 新商户需要判断密码输入是否匹配
                this.emptyCheck(merchant.getPassword(), FAILURE, "密码不能为空");
                this.emptyCheck(this.getConfirmPassword(), FAILURE, "请确认密码");

                if (!merchant.getPassword().equals(this.getConfirmPassword())) {
                    this.errorForward(FAILURE, "两次密码输入不一致");
                }

                // 设置密码
                updateMerchant.setPassword(CoreStringUtils.md5(merchant.getPassword(), CharsetConstant.CHARSET_UTF8));
            }

            // 通过检验，开始修改商户
            updateMerchant.setName(merchant.getName());
            updateMerchant.setLegalPerson(merchant.getLegalPerson());
            updateMerchant.setIdCard(merchant.getIdCard());
            updateMerchant.setAddress(merchant.getAddress());
            updateMerchant.setBusinessLicense(merchant.getBusinessLicense());
            updateMerchant.setMobile(merchant.getMobile());
            updateMerchant.setTel(merchant.getTel());
            updateMerchant.setFax(merchant.getFax());
            updateMerchant.setEmail(merchant.getEmail());
            updateMerchant.setStatus(EnableDisableStatus.get(statusValue));
            updateMerchant.setMemo(merchant.getMemo());

            updateMerchant.setUpdatedTime(new Date());

            merchantService.update(updateMerchant);
        }

        super.setSuccessMessage("更新成功");
        super.setForwardUrl("/merchant/merchant.do");
        logger.info("更新商户信息结束");
        return "success";
    }

    /**
     * 转向添加/修改商户
     */
    public String input() {
        if (merchant == null) {
            merchant = new Merchant();
        }
        if (merchant.getId() != null) {
            merchant = merchantService.findById(merchant.getId());
            statusValue = merchant.getStatus().getValue();
            if (merchant == null) {
                this.errorForward(FAILURE, "要编辑的商户不存在");
            }
        }

        return "inputForm";
    }

    public String view() {
        logger.info("进入查看商户详情");

        this.emptyCheck(merchant, FAILURE, "非法请求");
        this.emptyCheck(merchant.getId(), FAILURE, "非法请求");

        merchant = merchantService.findById(merchant.getId());
        this.emptyCheck(merchant, FAILURE, "商户不存在");

        logger.info("查看商户详情结束");
        return "view";
    }

    public List<EnableDisableStatus> getAllEnableDisableStatusList() {
        return EnableDisableStatus.listAll();
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
}
