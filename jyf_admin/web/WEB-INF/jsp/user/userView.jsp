<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>用户信息查看 - ${SITE_NAME}</title>
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
                  <li><a href="<c:url value="/user/user.do"/>">用户管理</a></li>
                  <li class="active">用户信息查看</li>
              </ul>
              <div class="col-md-6">
                  <table class="table table-bordered table-hover">
                      <tr>
                          <td>编码：</td><td>${user.id}</td>
                      </tr>
                      <tr>
                          <td>用户名：</td><td>${user.username}</td>
                      </tr>
                      <tr>
                          <td>姓名：</td><td>${user.name}</td>
                      </tr>
                      <tr>
                          <td>电话：</td><td>${user.tel}</td>
                      </tr>
                      <tr>
                          <td>邮件：</td><td>${user.email}</td>
                      </tr>
                      <tr>
                          <td>创建时间：</td><td><fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>更新时间：</td><td><fmt:formatDate value="${user.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>本次登录时间：</td><td><fmt:formatDate value="${user.loginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>上次登录时间：</td><td><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>是否有效：</td><td><s:if test="user.valid == true">有效</s:if><s:else>无效</s:else></td>
                      </tr>
                      <tr>
                          <td>备注：</td><td>${user.memo}</td>
                      </tr>
                      <tr>
                          <td>角色：</td><td>${role.name}</td>
                      </tr>
                  </table>
              </div>
          </div>
      </div>
  </div>

  <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
