<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>权限信息录入 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <script type="text/javascript" src="<c:url value="/js/jquery.validate.js"/>"></script>
</head>
  <body>
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
              <li class="active">权限信息录入</li>
          </ul>
          <form class="form-horizontal" role="form" action="<c:url value="/user/permission.do"/>" method="post">
              <input type="hidden" name="action" value="manage"/>
              <input type="hidden" name="permission.id" value="${permission.id}"/>
              <input type="hidden" name="permission.menuId" value="${permission.menuId}"/>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.name">权限名称</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.name" placeholder="权限名称" name="permission.name" value="${permission.name}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.url">链接地址</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.url" placeholder="链接地址" name="permission.url" value="${permission.url}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.orderView">权限排序值</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.orderView" placeholder="权限排序值" name="permission.orderView" value="${permission.orderView}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.valid">是否有效</label>

                  <div class="col-md-2">
                      <select id="permission.valid" class="form-control" name="permission.valid">
                          <c:choose>
                              <c:when test="${permission.valid != null && !permission.valid}">
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
                  <label class="col-md-2 control-label" for="permission.actionName">权限控制链接名称</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.actionName" placeholder="权限控制链接名称" name="permission.actionName" value="${permission.actionName}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.paramName">权限控制参数名称</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.paramName" placeholder="权限控制参数名称" name="permission.paramName" value="${permission.paramName}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.paramValue">权限控制参数值</label>

                  <div class="col-md-3">
                      <input type="text" class="form-control" id="permission.paramValue" placeholder="权限控制参数值" name="permission.paramValue" value="${permission.paramValue}"/>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.menuItem">是否显示为菜单</label>

                  <div class="col-md-2">
                      <select id="permission.menuItem" class="form-control" name="permission.menuItem">
                          <c:choose>
                              <c:when test="${permission.menuItem != null && !permission.menuItem}">
                                  <option value="true">显示</option>
                                  <option value="false" selected="selected">不显示</option>
                              </c:when>
                              <c:otherwise>
                                  <option value="true" selected="selected">显示</option>
                                  <option value="false">不显示</option>
                              </c:otherwise>
                          </c:choose>
                      </select>
                  </div>
              </div>

              <div class="form-group">
                  <label class="col-md-2 control-label" for="permission.memo">备注</label>

                  <div class="col-md-3">
                      <textarea id="permission.memo" class="form-control" rows="3" name="permission.memo">${permission.memo}</textarea>
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
