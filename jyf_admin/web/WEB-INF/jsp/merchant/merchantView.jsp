<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
      <title>商户信息查看 - ${SITE_NAME}</title>
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
                  <li><a href="#">商户管理</a></li>
                  <li><a href="<c:url value="/merchant/merchant.do"/>">商户信息</a></li>
                  <li class="active">商户信息查看</li>
              </ul>
              <div class="col-md-6">
                  <table class="table table-bordered table-hover">
                      <tr>
                          <td>编码：</td><td>${merchant.id}</td>
                      </tr>
                      <tr>
                          <td>用户名：</td><td>${merchant.username}</td>
                      </tr>
                      <tr>
                          <td>名称：</td><td>${merchant.name}</td>
                      </tr>
                      <tr>
                          <td>法人：</td><td>${merchant.legalPerson}</td>
                      </tr>
                      <tr>
                          <td>身份证号：</td><td>${merchant.idCard}</td>
                      </tr>
                      <tr>
                          <td>地址：</td><td>${merchant.address}</td>
                      </tr>
                      <tr>
                          <td>营业执照号：</td><td>${merchant.businessLicense}</td>
                      </tr>
                      <tr>
                          <td>手机：</td><td>${merchant.mobile}</td>
                      </tr>
                      <tr>
                          <td>电话：</td><td>${merchant.tel}</td>
                      </tr>
                      <tr>
                          <td>传真：</td><td>${merchant.fax}</td>
                      </tr>
                      <tr>
                          <td>邮件：</td><td>${merchant.email}</td>
                      </tr>
                      <tr>
                          <td>创建时间：</td><td><fmt:formatDate value="${merchant.createdTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>更新时间：</td><td><fmt:formatDate value="${merchant.updatedTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                      </tr>
                      <tr>
                          <td>是否有效：</td><td>${merchant.status.name}</td>
                      </tr>
                      <tr>
                          <td>备注：</td><td>${merchant.memo}</td>
                      </tr>
                  </table>
              </div>
          </div>
      </div>
  </div>

  <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
  </body>
</html>
