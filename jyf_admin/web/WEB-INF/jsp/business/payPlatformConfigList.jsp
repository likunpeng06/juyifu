<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>支付配置管理 - ${SITE_NAME}</title>
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
                <li><a href="#">运营管理</a></li>
                <li class="active">支付配置管理</li>
            </ul>

            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <td>支付方式</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="payTypeList">
                    <tr>
                        <td>${value}</td>
                        <td>${name}</td>
                        <td><a href="<c:url value="/business/payPlatformConfig.do?action=view&payTypeId=${value}"/>">配置</a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>

        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
