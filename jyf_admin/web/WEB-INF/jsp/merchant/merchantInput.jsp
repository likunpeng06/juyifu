<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商户信息录入 - ${SITE_NAME}</title>
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
                <li><a href="<c:url value="/merchant/merchant.do"/>">商户信息</a></li>
                <li class="active">商户信息录入</li>
            </ul>
            <form class="form-horizontal" role="form" action="<c:url value="/merchant/merchant.do"/>" method="post">
                <input type="hidden" name="action" value="manage"/>
                <input type="hidden" name="merchant.id" value="${merchant.id}"/>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.username">用户名</label>

                    <div class="col-md-3">
                        <c:choose>
                            <c:when test="${merchant.id == null}">
                                <input type="text" class="form-control" id="merchant.username" placeholder="用户名" name="merchant.username" maxlength="16"/>
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" id="merchant.username" placeholder="用户名" name="merchant.username" value="${merchant.username}" readonly="readonly"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.password">设置密码</label>

                    <div class="col-md-3">
                        <input type="password" class="form-control" id="merchant.password" placeholder="设置密码" name="merchant.password" maxlength="16"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.confirmPassword">确认密码</label>

                    <div class="col-md-3">
                        <input type="password" class="form-control" id="merchant.confirmPassword" placeholder="确认密码" name="confirmPassword" maxlength="16"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.name">名称</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.name" placeholder="名称" name="merchant.name" value="${merchant.name}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.legalPerson">法人代表</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.legalPerson" placeholder="法人代表" name="merchant.legalPerson" value="${merchant.legalPerson}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.idCard">身份证号</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.idCard" placeholder="身份证号" name="merchant.idCard" value="${merchant.idCard}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.address">地址</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.address" placeholder="地址" name="merchant.address" value="${merchant.address}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.businessLicense">营业执照号</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.businessLicense" placeholder="营业执照号" name="merchant.businessLicense" value="${merchant.businessLicense}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.mobile">手机</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.mobile" placeholder="手机" name="merchant.mobile" value="${merchant.mobile}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.tel">电话</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.tel" placeholder="电话" name="merchant.tel" value="${merchant.tel}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.fax">传真</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.fax" placeholder="传真" name="merchant.fax" value="${merchant.fax}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="merchant.email">邮件</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="merchant.email" placeholder="邮件" name="merchant.email" value="${merchant.email}"/>
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
                    <label class="col-md-2 control-label" for="merchant.memo">备注</label>

                    <div class="col-md-3">
                        <textarea id="merchant.memo" class="form-control" rows="3" name="merchant.memo">${merchant.memo}</textarea>
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
