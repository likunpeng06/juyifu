<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单信息查看 - ${SITE_NAME}</title>
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
                <li class="active">菜单信息查看</li>
            </ul>
            <div class="col-md-6">
                <table class="table table-bordered table-hover">
	    			<tr>
		    			<td>编码：</td><td>${menu.id}</td>
		    		</tr>
		    		<tr>
		    			<td>菜单名：</td><td>${menu.name}</td>
		    		</tr>
		    		<tr>
		    			<td>菜单链接：</td><td>${menu.url}</td>
		    		</tr>
		    		<tr>
		    			<td>菜单排序值：</td><td>${menu.orderView}</td>
		    		</tr>
		    		<tr>
		    			<td>是否有效：</td><td><s:if test="menu.valid == true">有效</s:if><s:else>无效</s:else></td>
		    		</tr>
		    		<tr>
		    			<td>备注：</td><td>${menu.memo}</td>
		    		</tr>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
