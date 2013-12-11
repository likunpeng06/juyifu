<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户登录 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <style type="text/css">
        body {
            background-color: #f5f5f5;
        }

        .form-signin {
            max-width: 340px;
            padding: 19px 29px 29px;
            margin: 0 auto;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .form-signin .form-signin-heading {
            color: #bbbbbb;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            padding: 7px 9px;
        }

        .verify-code {
            margin-bottom: 20px;
        }
    </style>
    <script type="text/javascript">
        var verifyCodeUrl = "/kaptcha?";
        $('#verifyImg').prop('src',verifyCodeUrl + (new Date()).getTime());
        $(function() {
            $('#verifyImg').click(function () {
                $(this).prop('src', verifyCodeUrl + (new Date()).getTime());
            }).trigger('click');
        });
    </script>
</head>
<body>

<nav class="navbar navbar-inverse" role="navigation">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">${NAVBAR_TITLE}</a>
    </div>
</nav>

<div class="container">

    <form class="form-horizontal form-signin" action="<c:url value='/login.do'/>" method="post" role="form">
        <c:if test="${errorMessage != null && errorMessage != ''}">
            <div class="alert alert-danger">
                    ${errorMessage}
            </div>
        </c:if>

        <h2 class="form-signin-heading">登录</h2>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-12">
                <input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="验证码">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10">
                <img class="verify-code img-thumbnail" id="verifyImg" title="看不清？点击图片刷新"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">登录</button>
            </div>
        </div>
    </form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
