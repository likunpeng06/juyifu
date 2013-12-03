<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>角色信息查看 - ${SITE_NAME}</title>
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
                  <li><a href="<c:url value="/user/role.do"/>">角色管理</a></li>
                  <li class="active">角色信息查看</li>
              </ul>
              <div class="col-md-6">
                  <table class="table table-bordered table-hover">
                      <tr>
                          <td>编码：</td><td>${role.id}</td>
                      </tr>
                      <tr>
                          <td>角色名：</td><td>${role.name}</td>
                      </tr>
                      <tr>
                          <td>是否有效：</td><td><s:if test="role.valid == true">有效</s:if><s:else>无效</s:else></td>
                      </tr>
                      <tr>
                          <td>是否限制IP：</td><td><s:if test="role.restriction == true">是</s:if><s:else>否</s:else></td>
                      </tr>
                      <tr>
                          <td>有效IP段：</td><td>${role.restrictionIp}</td>
                      </tr>
                      <tr>
                          <td>备注：</td><td>${role.memo}</td>
                      </tr>
                  </table>
              </div>
          </div>
      </div>
  </div>

  <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
