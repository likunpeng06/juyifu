<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单管理 - ${SITE_NAME}</title>
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
                <li class="active">菜单管理</li>
            </ul>
            <div class="btn-group table-btn-group">
                <a class="btn btn-primary" href="<c:url value="/user/menu.do?action=input"/>">添加菜单</a>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>菜单名称</th>
                    <th>链接地址</th>
                    <th>排序值</th>
                    <th>是否有效</th>
                    <th>备注</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="menuList">
                    <tr>
                        <td>${id}</td>
                        <td><a href="<c:url value="/user/menu.do?action=view&menu.id=${id}"/>">${name}</a></td>
                        <td>${url}</td>
                        <td>${orderView}</td>
                        <td>${valid}</td>
                        <td>${memo}</td>
                        <td><a href="<c:url value="/user/menu.do?action=input&menu.id=${id}"/>">修改</a></td>
                        <td><a href="<c:url value="/user/permission.do?permission.menuId=${id}"/>">权限管理</a></td>
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
