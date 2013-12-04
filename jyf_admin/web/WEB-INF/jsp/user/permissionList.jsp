<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>权限列表 - ${SITE_NAME}</title>
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
                <li><a href="<c:url value='/user/menu.do'/>">菜单管理</a></li>
                <li class="active">权限列表</li>
            </ul>
            <div class="btn-group table-btn-group">
                <a class="btn btn-primary" href="<c:url value="/user/permission.do?action=input&permission.menuId=${permission.menuId}"/>">添加权限</a>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <td>#</td>
                    <td>权限名</td>
                    <td>链接</td>
                    <td>排序值</td>
                    <td>权限控制链接名称</td>
                    <td>权限控制参数名称</td>
                    <td>权限控制参数值</td>
                    <td>是否有效</td>
                    <td>是否显示为菜单</td>
                    <td>备注</td>
                    <td colspan="2">操作</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="permissionList">
                    <tr>
                        <td>${id}</td>
                        <td><a href="<c:url value="/user/permission.do?action=view&permission.id=${id}"/>">${name}</a></td>
                        <td>${url}</td>
                        <td>${orderView}</td>
                        <td>${actionName}</td>
                        <td>${paramName}</td>
                        <td>${paramValue}</td>
                        <td><s:if test="valid == true">有效</s:if><s:else>无效</s:else></td>
                        <td><s:if test="menuItem == true">显示</s:if><s:else>不显示</s:else></td>
                        <td>${memo}</td>
                        <td><a href="<c:url value="/user/permission.do?action=input&permission.id=${id}"/>">修改</a></td>
                        <td><a href="<c:url value="/user/permissionItem.do?permissionItem.permissionId=${id}&permission.menuId=${permission.menuId}"/>">子权限管理</a></td>
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
