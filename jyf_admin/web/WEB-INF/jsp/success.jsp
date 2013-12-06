<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>操作成功 - ${SITE_NAME}</title>
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
            <div class="text-center alert alert-success">
                <h3>操作成功</h3>
                <h6>${successMessage}</h6>
            </div>
            <div class="text-center">
                <c:if test="${forwardUrl != null && forwardUrl != ''}">
                    <div class="btn-group table-btn-group">
                        <a class="btn btn-primary" href="<c:url value='${forwardUrl}'/>">返回列表</a>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>


