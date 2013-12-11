<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商户管理 - ${SITE_NAME}</title>
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
                <li><a href="#">商户管理</a></li>
                <li class="active">商户信息</li>
            </ul>

            <div class="btn-group table-btn-group">
                <a class="btn btn-primary" href="<c:url value='/merchant/merchant.do?action=input'/>">添加商户</a>
            </div>

            <div class="alert alert-info">
                条件查询
            </div>

            <form class="form-inline" role="form" action="<c:url value='/merchant/merchant.do'/>" method="post">
                <input type="hidden" value="query" name="action"/>
                <table class="table table-bordered">
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="form-control-username">用户名</label>
                                <input type="text" class="form-control" placeholder="用户名" id="form-control-username" name="username" value="${username}"/>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="form-control-name">名称</label>
                                <input type="text" class="form-control" placeholder="名称" id="form-control-name" name="name" value="${name}"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="form-control-phone">手机</label>
                                <input type="text" class="form-control" placeholder="手机" id="form-control-phone" name="mobile" value="${mobile}"/>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="statusValue">是否有效</label>
                                <s:select name="statusValue" list="allEnableDisableStatusList" listKey="value"
                                          listValue="name" cssClass="form-control"/>
                            </div>

                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="form-control-beginDate">创建时间开始</label>
                                <input type="datetime" class="form-control" placeholder="创建时间开始" id="form-control-beginDate" name="beginDate" value="<fmt:formatDate value='${beginDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default" id="beginDateTrigger">选择</button>
                            </div>
                        </td>
                        <td>
                            <div class="form-group">
                                <label class="sr-only" for="form-control-endDate">创建时间结束</label>
                                <input type="datetime" class="form-control" placeholder="创建时间结束" id="form-control-endDate" name="endDate" value="<fmt:formatDate value='${endDate}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-default" id="endDateTrigger">选择</button>
                            </div>
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
                        <th>用户名</th>
                        <th>名称</th>
                        <th>法人</th>
                        <th>手机</th>
                        <th>邮件</th>
                        <th>创建时间</th>
                        <th>是否有效</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="merchantList">
                        <tr>
                            <td>${id}</td>
                            <td><a href="<c:url value="/merchant/merchant.do?action=view&merchant.id=${id}"/>">${username}</a></td>
                            <td>${name}</td>
                            <td>${legalPerson}</td>
                            <td>${tel}</td>
                            <td>${email}</td>
                            <td><s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>${status.name}</td>
                            <td><a href="<c:url value="/merchant/merchant.do?action=input&merchant.id=${id}"/>">修改</a></td>
                            <td><a href="<c:url value="/merchant/merchantApp.do?merchantApp.merchantId=${id}"/>">App管理</a></td>
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
                    inputField  : "form-control-beginDate",    // ID of the input field
                    ifFormat    : "%Y-%m-%d %H:%M:%S",   // the date format
                    button      : "beginDateTrigger",
                    showsTime   : true,
                    date		: Calendar.initNewDate('00:00:00'),
                    timeFormat  : "24"
                }
        );
        Calendar.setup(
                {
                    inputField  : "form-control-endDate",    // ID of the input field
                    ifFormat    : "%Y-%m-%d %H:%M:%S",   // the date format
                    button      : "endDateTrigger",
                    showsTime   : true,
                    date		: Calendar.initNewDate('23:59:59'),
                    timeFormat  : "24"
                }
        );
    });
</script>

</body>
</html>
