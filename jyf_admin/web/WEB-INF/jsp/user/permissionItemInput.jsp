<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>子权限信息录入 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.validate.js"/>"></script>
</head>
<body>
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
            <li class="active">子权限信息录入</li>
        </ul>
        <form class="form-horizontal" role="form" action="<c:url value="/user/permissionItem.do"/>" method="post">
            <input type="hidden" name="action" value="manage"/>
            <input type="hidden" name="permissionItem.id" value="${permissionItem.id}"/>
            <input type="hidden" name="permissionItem.permissionId" value="${permissionItem.permissionId}"/>

            <div class="form-group">
                <label class="col-md-2 control-label" for="permissionItem.name">子权限名称</label>

                <div class="col-md-3">
                    <input type="text" class="form-control" id="permissionItem.name" placeholder="子权限名称" name="permissionItem.name" value="${permissionItem.name}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="permissionItem.orderView">子权限排序值</label>

                <div class="col-md-3">
                    <input type="text" class="form-control" id="permissionItem.orderView" placeholder="子权限排序值" name="permissionItem.orderView" value="${permissionItem.orderView}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="permissionItem.valid">是否有效</label>

                <div class="col-md-2">
                    <select id="permissionItem.valid" class="form-control" name="permissionItem.valid">
                        <c:choose>
                            <c:when test="${permissionItem.valid != null && !permissionItem.valid}">
                                <option value="true">有效</option>
                                <option value="false" selected="selected">无效</option>
                            </c:when>
                            <c:otherwise>
                                <option value="true" selected="selected">有效</option>
                                <option value="false">无效</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="permissionItem.methodName">子权限控制方法名称</label>

                <div class="col-md-3">
                    <input type="text" class="form-control" id="permissionItem.methodName" placeholder="子权限控制方法名称" name="permissionItem.methodName" value="${permissionItem.methodName}"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label" for="permissionItem.memo">备注</label>

                <div class="col-md-3">
                    <textarea id="permissionItem.memo" class="form-control" rows="3" name="permissionItem.memo">${permissionItem.memo}</textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-3">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
