<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>支付配置管理 - ${SITE_NAME}</title>
    <jsp:include page="/WEB-INF/jsp/global_init.jsp"/>
    <script type="text/javascript">
        $(function(){
            //全选
            $('#config_check_all').click(function() {
                var checked = $(this).prop('checked');
                $('input[type=checkbox][name=config_chk_box]').prop('checked', checked);
            });

            $('#check_all').click(function() {
                var checked = $(this).prop('checked');
                $('input[type=checkbox][name=chk_box]').prop('checked', checked);
            });

            $('#config_batch_action').click(function() {
                var $checked = $('input[type=checkbox][name=config_chk_box]:checked');
                if ($checked.length == 0) {
                    alert("请先选择要操作的配置");
                    return;
                }
                var batchForm = $('#batchForm');
                $checked.each(function(i) {
                    var id = $(this).val();
                    var config_id = $('#config_id_' + id).val();
                    batchForm.append('<input type="hidden" name="payPlatformConfigList[' + i + '].id" value="' + config_id + '" />');
                });
                batchForm.submit();
            });
            $('#batch_action').click(function() {
                var $checked = $('input[type=checkbox][name=chk_box]:checked');
                if ($checked.length == 0) {
                    alert("请先选择要操作的支付平台");
                    return;
                }
                var batchForm = $('#batchForm');
                $checked.each(function(i) {
                    var id = $(this).val();
                    var platform_type = $('#platform_type_' + id).val();
                    batchForm.append('<input type="hidden" name="platformTypeValueList[' + i + ']" value="' + platform_type + '" />');
                });
                batchForm.submit();
            });
        });

        function optStatus(obj, config_id) {
            $.ajax({
                url:"<c:url value="/business/payPlatformConfig.do"/>",
                data:{action:"optStatus","payPlatformConfig.id":config_id},
                type:"post",
                dataType:"json",
                success:function(data){
                    $(obj).html(data.statusName);
                }
            });

        }
    </script>
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
                <li><a href="#">运营管理</a></li>
                <li><a href="<c:url value='/business/payPlatformConfig.do' />">支付配置管理</a></li>
                <li class="active">支付配置</li>
            </ul>

            <form class="form-horizontal" role="form" id="batchForm" action="<c:url value="/business/payPlatformConfig.do"/>" method="post">
            <input type="hidden" name="action" value="manage"/>
            <input type="hidden" name="payTypeId" value="${payTypeId}"/>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            ${platformType.name} 已配置支付平台
                            <div class="btn-group btn-group-xs col-md-offset-6">
                                <a class="btn btn-default" id="config_batch_action" href="javascript://">删除所选配置</a>
                            </div>
                        </div>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="config_check_all" />全选</th>
                                <th>#</th>
                                <td>支付平台</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="payPlatformConfigList" status="index">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="config_chk_box" value="${index.count - 1}"/>
                                        <input type="hidden" id="config_id_${index.count - 1}" value="${id}"/>
                                    </td>
                                    <td>${platformType.value}</td>
                                    <td>${platformType.name}</td>
                                    <td>
                                        <a href="javascript://" onclick="optStatus(this, ${id})">
                                        <s:if test="status.value == enableStatus.value">
                                            ${disableStatus.name}
                                        </s:if>
                                        <s:else>
                                            ${enableStatus.name}
                                        </s:else>
                                        </a>
                                    </td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            未配置支付平台
                            <div class="btn-group btn-group-xs col-md-offset-6">
                                <a class="btn btn-default" id="batch_action" href="javascript://">添加所选配置</a>
                            </div>
                        </div>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="check_all" />全选</th>
                                <th>#</th>
                                <td>名称</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="platformTypeList" status="index">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="chk_box" value="${index.count - 1}"/>
                                        <input type="hidden" id="platform_type_${index.count - 1}" value="${value}"/>
                                    </td>
                                    <td>${value}</td>
                                    <td>${name}</td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            </form>

        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
