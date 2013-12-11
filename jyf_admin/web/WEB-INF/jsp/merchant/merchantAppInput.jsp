<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商户App信息录入 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.validate.js"/>"></script>
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
                <li><a href="<c:url value="/merchant/merchantApp.do?merchantApp.merchantId=${merchantApp.merchantId}"/>">商户App信息</a></li>
                <li class="active">商户App信息录入</li>
            </ul>
            <form class="form-horizontal" role="form" action="<c:url value="/merchant/merchantApp.do"/>" method="post">
                <input type="hidden" name="action" value="manage"/>
                <input type="hidden" name="merchantApp.id" value="${merchantApp.id}"/>
                <input type="hidden" name="merchantApp.merchantId" value="${merchantApp.merchantId}"/>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchantApp.name">名称</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchantApp.name" placeholder="名称" name="merchantApp.name" value="${merchantApp.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="statusValue">是否有效</label>

                    <div class="col-md-2">
                        <s:select name="statusValue" list="enableDisableStatusList" listKey="value"
                                  listValue="name" cssClass="form-control"/>
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
