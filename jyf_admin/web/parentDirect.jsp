<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <script type="text/javascript">
        parent.location.href = '<c:url value="/index.jsp"/>';
    </script>
</head>
<body>
</body>
</html>

