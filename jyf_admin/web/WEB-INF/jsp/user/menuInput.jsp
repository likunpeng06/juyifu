<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>菜单信息录入 - ${SITE_NAME}</title>
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
                  <li><a href="<c:url value="/user/menu.do"/>">菜单管理</a></li>
                  <li class="active">菜单信息录入</li>
              </ul>
              <form class="form-horizontal" role="form" action="<c:url value="/user/menu.do"/>" method="post">
                  <input type="hidden" name="action" value="manage"/>
                  <input type="hidden" name="menu.id" value="${menu.id}"/>

                  <div class="form-group">
                      <label class="col-md-2 control-label" for="menu.name">菜单名称</label>

                      <div class="col-md-3">
                        <input type="text" class="form-control" id="menu.name" placeholder="菜单名称" name="menu.name" value="${menu.name}"/>
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="col-md-2 control-label" for="menu.url">菜单链接</label>

                      <div class="col-md-3">
                          <input type="text" class="form-control" id="menu.url" placeholder="菜单链接" name="menu.url" value="${menu.url}"/>
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="col-md-2 control-label" for="menu.orderView">菜单排序值</label>

                      <div class="col-md-3">
                          <input type="text" class="form-control" id="menu.orderView" placeholder="菜单排序值" name="menu.orderView" value="${menu.orderView}"/>
                      </div>
                  </div>

                  <div class="form-group">
                      <label class="col-md-2 control-label" for="menu.valid">是否有效</label>

                      <div class="col-md-2">
                          <select id="menu.valid" class="form-control" name="menu.valid">
                              <c:choose>
                                  <c:when test="${menu.valid != null && !menu.valid}">
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
                      <label class="col-md-2 control-label" for="menu.memo">备注</label>

                      <div class="col-md-3">
                          <textarea id="menu.memo" class="form-control" rows="3" name="menu.memo">${menu.memo}</textarea>
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
