<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户信息录入 - ${SITE_NAME}</title>
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
                <li><a href="#">系统管理</a></li>
                <li><a href="<c:url value="/user/user.do"/>">用户管理</a></li>
                <li class="active">用户信息录入</li>
            </ul>
            <form class="form-horizontal" role="form" action="<c:url value="/user/user.do"/>" method="post">
                <input type="hidden" name="action" value="manage"/>
                <input type="hidden" name="user.id" value="${user.id}"/>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.username">用户名</label>

                    <div class="col-md-3">
                        <c:choose>
                            <c:when test="${user.id == null}">
                                <input type="text" class="form-control" id="user.username" placeholder="用户名" name="user.username" maxlength="16"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" id="user.username" placeholder="用户名" name="user.username" value="${user.username}" readonly="readonly"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.password">设置密码</label>

                    <div class="col-md-3">
                        <input type="password" class="form-control" id="user.password" placeholder="设置密码" name="user.password" maxlength="16"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.confirmPassword">确认密码</label>

                    <div class="col-md-3">
                        <input type="password" class="form-control" id="user.confirmPassword" placeholder="确认密码" name="confirmPassword" maxlength="16"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.name">姓名</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="user.name" placeholder="姓名" name="user.name" value="${user.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.tel">电话</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="user.tel" placeholder="电话" name="user.tel" value="${user.tel}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.email">邮件</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="user.email" placeholder="邮件" name="user.email" value="${user.email}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.role">选择角色</label>

                    <div class="col-md-2">
                        <s:select id="user.role" cssClass="form-control" name="roleId" list="roleList" listKey="id" listValue="name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="user.valid">是否有效</label>

                    <div class="col-md-2">
                        <select id="user.valid" class="form-control" name="user.valid">
                            <c:choose>
                                <c:when test="${user.valid != null && !user.valid}">
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
                    <label class="col-md-2 control-label" for="user.memo">备注</label>

                    <div class="col-md-3">
                        <textarea id="user.memo" class="form-control" rows="3" name="user.memo">${user.memo}</textarea>
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
