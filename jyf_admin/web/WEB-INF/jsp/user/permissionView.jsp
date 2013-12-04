<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>权限信息查看 - ${SITE_NAME}</title>
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
                <li><a href="<c:url value="/user/menu.do"/>">菜单管理</a></li>
                <li class="active">权限信息查看</li>
            </ul>
            <div class="col-md-6">
                <table class="table table-bordered table-hover">
	    			<tr>
		    			<td>编码：</td><td>${permission.id}</td>
		    		</tr>
		    		<tr>
		    			<td>权限名：</td><td>${permission.name}</td>
		    		</tr>
		    		<tr>
		    			<td>链接：</td><td>${permission.url}</td>
		    		</tr>
		    		<tr>
		    			<td>权限排序值：</td><td>${permission.orderView}</td>
		    		</tr>
		    		<tr>
		    			<td>权限控制链接名称：</td><td>${permission.actionName}</td>
		    		</tr>
		    		<tr>
		    			<td>权限控制参数名称：</td><td>${permission.paramName}</td>
		    		</tr>
		    		<tr>
		    			<td>权限控制参数值：</td><td>${permission.paramValue}</td>
		    		</tr>
		    		<tr>
		    			<td>是否有效：</td><td><s:if test="permission.valid == true">有效</s:if><s:else>无效</s:else></td>
		    		</tr>
		    		<tr>
		    			<td>是否显示为菜单：</td><td><s:if test="permission.menuItem == true">显示</s:if><s:else>不显示</s:else></td>
		    		</tr>
		    		<tr>
		    			<td>备注：</td><td>${permission.memo}</td>
		    		</tr>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
