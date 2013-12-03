<%@page contentType="text/html; charset=utf-8" %>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>角色信息录入 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <link rel="stylesheet" href="<c:url value="/css/treeview.css"/>" type="text/css">
    <script type="text/javascript" src="<c:url value="/js/jquery.treeview.js"/>"></script>
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
                <li class="active">角色信息录入</li>
            </ul>
            <form class="form-horizontal" role="form" action="<c:url value="/user/role.do"/>" method="post">
                <input type="hidden" name="action" value="manage"/>
                <s:if test="%{func == 'copy'}">
                    <input type="hidden" id="roleid" name="role.id" value=""/>
                </s:if>
                <s:else>
                    <input type="hidden" id="roleid" name="role.id" value="${role.id}"/>
                </s:else>
                <input type="hidden" id="temp_role_id" name="roleId" value="${role.id}"/>

                <div class="form-group">
                    <label class="col-md-2 control-label" for="role.name">角色名称</label>

                    <div class="col-md-3">
                        <input type="text" class="form-control" id="role.name" placeholder="角色名称" name="role.name" value="${role.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="role.valid">是否有效</label>

                    <div class="col-md-2">
                        <select id="role.valid" name="role.valid" class="form-control">
                            <c:choose>
                                <c:when test="${!role.valid}">
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
                    <label class="col-md-2 control-label" for="role.restriction">是否限制IP</label>

                    <div class="col-md-1">
                        <select id="role.restriction" name="role.restriction" class="form-control">
                            <c:choose>
                                <c:when test="${role.restriction}">
                                    <option value="false">否</option>
                                    <option value="true" selected="selected">是</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="false" selected="selected">否</option>
                                    <option value="true">是</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="role.restrictionIp">有效IP段</label>

                    <div class="col-md-3">
                        <textarea id="role.restrictionIp" class="form-control" rows="3" name="role.restrictionIp">${role.restrictionIp}</textarea>
                        <br/>(格式：211.211.211.*,201.201.201.*)
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label" for="role.memo">备注</label>

                    <div class="col-md-3">
                        <textarea id="role.memo" class="form-control" rows="3" name="role.memo">${role.memo}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-2 control-label">选择权限</label>

                    <div class="col-md-3">
                        <ul id="permissionTreeView"></ul>
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

<script type="text/javascript">
    function toggleSelectChildren(node, bool) {
        node.find('ul > li').each(function () {
            $(this).children('input').prop('checked', bool);
            toggleSelectChildren($(this), bool);
        }).end();
    }
    function selectParent(node) {
        if (!node || node.length == 0) {
            return;
        }
        var parentNode = node.parent('ul').parent('li');
        parentNode.children('input').prop('checked', true);
        selectParent(parentNode);
    }
    $(document).ready(function () {
        var permissionUrl = '<c:url value="/user/role.do?action=findPermissions"/>';

        var roleid = $('#temp_role_id').val();
        if (roleid != null) {
            permissionUrl += '&role.id=' + roleid;
        }

        $('#permissionTreeView').treeview({
            'collapsed': false,
            'unique': false,
            'url': permissionUrl,
            'success': function (obj) {
                obj.find('.perms').click(function () {
                    toggleSelectChildren($(this).parent(), $(this).prop('checked'));
                    if ($(this).prop('checked')) {
                        selectParent($(this).parent());
                    }
                });
            }
        });
    });
</script>

</body>
</html>
