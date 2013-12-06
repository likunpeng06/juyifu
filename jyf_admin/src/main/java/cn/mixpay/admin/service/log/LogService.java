package cn.mixpay.admin.service.log;

import java.util.Date;
import java.util.List;

import cn.mixpay.admin.bean.PageBean;
import cn.mixpay.admin.entity.log.Log;

public interface LogService {
	List<Log> list(String userName,String name,Date beginDate,Date endDate,Long logType, String url, String actionName, String params, String ip, PageBean pageBean);
	PageBean getPageBean(String userName,String name,Date beginDate,Date endDate,Long logType, String url, String actionName, String params, String ip, PageBean pageBean);
	Log get(Long ID);
	void save(Log log);
}
