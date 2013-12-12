<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商户可选支付管理 - ${SITE_NAME}</title>
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
                var configBatchForm = $('#configBatchForm');
                $checked.each(function(i) {
                    var id = $(this).val();
                    var config_id = $('#config_id_' + id).val();
                    configBatchForm.append('<input type="hidden" name="merchantSelectablePayModeList[' + i + '].id" value="' + config_id + '" />');
                });
                configBatchForm.submit();
            });
            $('#batch_action').click(function() {
                var $checked = $('input[type=checkbox][name=chk_box]:checked');
                if ($checked.length == 0) {
                    alert("请先选择要操作的支付方式");
                    return;
                }
                var batchForm = $('#batchForm');
                $checked.each(function(i) {
                    var id = $(this).val();
                    var pay_type = $('#pay_type_' + id).val();
                    batchForm.append('<input type="hidden" name="payTypeValueList[' + i + ']" value="' + pay_type + '" />');
                });
                batchForm.submit();
            });
        });

        function optStatus(obj, config_id) {
            $.ajax({
                url:"<c:url value="/merchant/merchantSelectablePayMode.do"/>",
                data:{action:"optStatus","merchantSelectablePayMode.id":config_id},
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
                <li><a href="#">商户管理</a></li>
                <li><a href="<c:url value='/merchant/merchantSelectablePayMode.do' />">商户可选支付管理</a></li>
                <li class="active">商户可选支付配置</li>
            </ul>

            <div class="row">
                <form class="form-horizontal" role="form" id="configBatchForm" action="<c:url value="/merchant/merchantSelectablePayMode.do"/>" method="post">
                <input type="hidden" name="action" value="manageMode"/>
                <input type="hidden" name="merchant.id" value="${merchant.id}"/>
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            ${merchant.name} 已配置支付方式
                            <div class="btn-group btn-group-xs">
                                <a class="btn btn-default" id="config_batch_action" href="javascript://">删除所选配置</a>
                            </div>
                        </div>
                        <table class="table table-bordered table-hover">
                            <thead>
                            <tr>
                                <th><input type="checkbox" id="config_check_all" />全选</th>
                                <th>#</th>
                                <td>支付方式</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody>
                            <s:iterator value="merchantSelectablePayModeList" status="index">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="config_chk_box" value="${index.count - 1}"/>
                                        <input type="hidden" id="config_id_${index.count - 1}" value="${id}"/>
                                    </td>
                                    <td>${payType.value}</td>
                                    <td>${payType.name}</td>
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
                </form>

                <form class="form-horizontal" role="form" id="batchForm" action="<c:url value="/merchant/merchantSelectablePayMode.do"/>" method="post">
                <input type="hidden" name="action" value="manageType"/>
                <input type="hidden" name="merchant.id" value="${merchant.id}"/>
                <div class="col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            未配置支付方式
                            <div class="btn-group btn-group-xs">
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
                            <s:iterator value="payTypeList" status="index">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="chk_box" value="${index.count - 1}"/>
                                        <input type="hidden" id="pay_type_${index.count - 1}" value="${value}"/>
                                    </td>
                                    <td>${value}</td>
                                    <td>${name}</td>
                                </tr>
                            </s:iterator>
                            </tbody>
                        </table>
                    </div>
                </div>
                </form>
            </div>

        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>
