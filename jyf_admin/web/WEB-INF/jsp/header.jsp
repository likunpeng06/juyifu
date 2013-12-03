<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">${NAVBAR_TITLE}</a>
    </div>
    <p class="navbar-text navbar-right">Signed in as <a href="<c:url value='/user/logout.do'/>" class="navbar-link"><strong>${session.USER_SESSION.user.username}</strong></a></p>
</nav>