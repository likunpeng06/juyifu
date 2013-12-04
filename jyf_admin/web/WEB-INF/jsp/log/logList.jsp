<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>日志管理 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <link href="<c:url value="/js/jscalendar/skins/aqua/theme.css"/>" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<c:url value="/js/jscalendar/calendar.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jscalendar/lang/cn_utf8.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/jscalendar/calendar-setup.js"/>"></script>
</head>

<body>

<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <div class="col-md-2">
            <!--Sidebar content-->
            <jsp:include page="/WEB-INF/jsp/sidebar.jsp"/>
        </div>
        <div class="col-md-10">
            <!--Body content-->
            <ul class="breadcrumb">
                <li><a href="#">系统管理</a></li>
                <li class="active">日志管理</li>
            </ul>

            <div class="alert alert-info">
                条件查询
            </div>

            <form class="form-inline" role="form" action="<c:url value="/log/log.do"/>" method="post">
                <input type="hidden" value="query" name="action"/>
                <table class="table table-bordered">
                    <tr>
                        <td>
                            <label>用户名</label>
                            <input type="text" placeholder="用户名" name="username" value="${username}"/>
                        </td>
                        <td>
                            <label>姓名</label>
                            <input type="text" placeholder="姓名" name="name" value="${name}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>日志类型</label>
                            <s:select name="logTypeId" list="logTypes" listKey="value"
                                      listValue="name" headerKey="0" headerValue="全部"/>
                        </td>
                        <td>
                            <label>链接</label>
                            <input type="text" placeholder="链接" name="url" value="${url}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>action名称</label>
                            <input type="text" placeholder="action名称" name="actionName" value="${actionName}" />
                        </td>
                        <td>
                            <label>参数</label>
                            <input type="text" placeholder="参数" name="params" value="${params}" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>ip</label>
                            <input type="text" placeholder="ip" name="ip" value="${ip}" />
                        </td>
                        <td>
                            <label>创建时间开始</label>
                            <input type="text" id="beginDate" name="beginDate" value="<fmt:formatDate value='${beginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                            <input type="button" id="beginDateTrigger" value="选择"/>
                            <label>创建时间结束</label>
                            <input type="text" id="endDate" name="endDate" value="<fmt:formatDate value='${endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                            <input type="button" id="endDateTrigger" value="选择"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div style="text-align:center">
                                <button class="btn btn-primary" type="submit">查询</button>
                            </div>
                        </td>
                    </tr>
                </table>

                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>#</th>
                        <td>用户名</td>
                        <td>姓名</td>
                        <td>日志类型</td>
                        <td>创建时间</td>
                        <td>链接</td>
                        <td>IP</td>
                        <td>操作</td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="logs">
                        <tr>
                            <td>${id}</td>
                            <td>${username}</td>
                            <td>${name}</td>
                            <td>${logType.name}</td>
                            <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>${url}</td>
                            <td>${remoteIP}</td>
                            <td><a href="<c:url value="/log/log.do?action=view&log.id=${id}"/>">查看</a></td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                ${pageString}

            </form>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

<script type="text/javascript">
    $(function(){
        Calendar.setup(
                {
                    inputField  : "beginDate",    // ID of the input field
                    ifFormat    : "%Y-%m-%d %H:%M:%S",   // the date format
                    button      : "beginDateTrigger",
                    showsTime   : true,
                    date		: Calendar.initNewDate(),
                    timeFormat  : "24"
                }
        );
        Calendar.setup(
                {
                    inputField  : "endDate",    // ID of the input field
                    ifFormat    : "%Y-%m-%d %H:%M:%S",   // the date format
                    button      : "endDateTrigger",
                    showsTime   : true,
                    date		: Calendar.initNewDate(),
                    timeFormat  : "24"
                }
        );
    });
</script>

</body>
</html>
