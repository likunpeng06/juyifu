<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商户App管理 - ${SITE_NAME}</title>
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
                <li><a href="#">商户管理</a></li>
                <li class="active">商户App管理</li>
            </ul>

            <div class="btn-group table-btn-group">
                <a class="btn btn-primary" href="<c:url value='/merchant/merchantApp.do?action=input&merchantApp.merchantId=${merchant.id}'/>">添加App</a>
            </div>

            <div class="alert alert-info">
                ${merchant.name}
            </div>

            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>app名称</th>
                    <th>创建时间</th>
                    <th>是否有效</th>
                    <th colspan="3">操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="merchantAppList">
                    <tr>
                        <td>${id}</td>
                        <td><a href="<c:url value="/merchant/merchantApp.do?action=view&merchantApp.id=${id}"/>">${name}</a></td>
                        <td><s:date name="createdTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>${status.name}</td>
                        <td><a href="<c:url value="/merchant/merchantApp.do?action=input&merchantApp.id=${id}"/>">修改</a></td>
                        <td><a href="<c:url value="/merchant/merchantApp.do?action=del&merchantApp.id=${id}"/>" onclick="return confirm('确实要删除吗？');">删除</a></td>
                        <td><a href="<c:url value="/merchant/merchantAppPayMode.do?merchantAppPayMode.merchantAppId=${id}"/>">支付方式管理</a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>

            ${pageString}

        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
