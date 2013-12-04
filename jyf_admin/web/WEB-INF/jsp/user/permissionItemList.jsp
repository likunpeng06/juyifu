<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>子权限列表 - ${SITE_NAME}</title>
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
                <li class="active">子权限列表</li>
            </ul>
            <div class="btn-group table-btn-group">
                <a class="btn btn-primary" href="<c:url value='/user/permissionItem.do?action=input&permissionItem.permissionId=${permissionItem.permissionId}'/>">添加子权限</a>
            </div>
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <td>#</td>
                    <td>子权限名</td>
                    <td>排序值</td>
                    <td>子权限控制方法名称</td>
                    <td>是否有效</td>
                    <td>备注</td>
                    <td colspan="2">操作</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="permissionItems">
                    <tr>
                        <td>${id}</td>
                        <td><a href="<c:url value='/user/permissionItem.do?action=view&permissionItem.id=${id}'/>">${name}</a></td>
                        <td>${orderView}</td>
                        <td>${methodName}</td>
                        <td><s:if test="valid == true">有效</s:if><s:else>无效</s:else></td>
                        <td>${memo}</td>
                        <td><a href="<c:url value='/user/permissionItem.do?action=input&permissionItem.id=${id}'/>">修改</a></td>
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
