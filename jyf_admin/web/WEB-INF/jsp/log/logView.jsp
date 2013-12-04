<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>日志信息查看 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
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
                <li><a href="<c:url value="/log/log.do"/>">日志管理</a></li>
                <li class="active">日志信息查看</li>
            </ul>
            <div class="col-md-6">
                <table class="table table-bordered table-hover">
                    <tr>
                        <td>编码：</td><td>${log.id}</td>
                    </tr>
                    <tr>
                        <td>用户名：</td><td>${log.username}</td>
                    </tr>
                    <tr>
                        <td>姓名：</td><td>${log.name}</td>
                    </tr>
                    <tr>
                        <td>日志类型：</td><td>${log.logType.name}</td>
                    </tr>
                    <tr>
                        <td>创建时间：</td><td><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    </tr>
                    <tr>
                        <td>链接：</td><td>${log.url}</td>
                    </tr>
                    <tr>
                        <td>操作名：</td><td>${log.actionName}</td>
                    </tr>
                    <tr>
                        <td>参数：</td><td>${log.params}</td>
                    </tr>
                    <tr>
                        <td>IP：</td><td>${log.remoteIP}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
