<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/23 0023
  Time: 下午 3:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>供应链金融信息共享平台</title>
    <%--ztree--%>
    <link rel="stylesheet" href="/static/ztree/css/zTreeStyle.css">
    <link rel="stylesheet" href="/static/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css">
    <link rel="stylesheet" href="/static/js/bootstrap-daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="/static/js/jqgrid/css/ui.jqgrid.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <%--用户新增--%>
    <link rel="stylesheet" type="text/css" href="/static/css/cloud-admin.css" >
    <link rel="stylesheet" type="text/css" href="/static/css/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/public/public.css">
    <style>
        .control-label{
            width: 108px;
            display: block;
        }
        .form-line-control{
            color: #555555;
            vertical-align: middle;
            background-color: #ffffff;
            background-image: none;
            border: 1px solid #cccccc;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
        }
        .col-sm-2.col-xs-2{
            padding-left: 0;
        }
        .redStart{
            color: red;
            margin-right:2px;
        }
        .input-sm{
            padding: 5px 4px;
        }
    </style>
</head>
<body>
<div style="width: 90%;margin: 0 auto;">
    <span style="font-weight: bold;margin: 20px 0 10px 0;display: block">审批详情:</span>
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>借款人名称</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="borrowerName" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>合作协议号</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="cooNum" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>监管机构</label>
            <div class="col-sm-2 col-xs-2">
                <input type="text" class="form-control input-sm" id="superDep" readonly="readonly">
            </div>
        </div>
        <div class="parate"></div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>业务到期时间</label>
            <div class="col-sm-2 col-xs-2">
                <input  class="form-control input-sm" type="text" name="regular" size="10" id="deadLine" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>贷款种类</label>
            <div class="col-sm-2 col-xs-2" style="position: relative;">
                <input class="form-control input-sm" type="text" placeholder="" id="morClassifyInput" disabled="disabled" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>材质</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="material" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>融资金额</label>
            <div class="col-sm-2 col-xs-2">
                <input  class="form-control input-sm" type="text" name="morValue" size="10" id="morValue" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>放款时间 </label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="morTime" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>质押率</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="mortageRate" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>质物价值</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="mortageValue" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>质物名称</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="mortgageName" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>价格单位</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="priceUnit" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>生产厂家</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="producer" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>单位价格</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="unitPrice" readonly="readonly">
            </div>
            <div >
                <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>市场</label>
                <div class="col-sm-2 col-xs-2">
                    <input class="form-control input-sm" type="text" placeholder="" id="marketCity" readonly="readonly">
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>借款人用信风险敞口</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="riskExposure" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>融资合同编号</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="businessConum" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1">复核岗</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="checker" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>客户经理人员</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="customerManager" readonly="readonly">
            </div>
            <label class="col-sm-1 control-label col-xs-1"><span class="redStart">*</span>现场检查岗人员</label>
            <div class="col-sm-2 col-xs-2">
                <input class="form-control input-sm" type="text" placeholder="" id="wareSuper" readonly="readonly">
            </div>
        </div>
    </form>
</div>
<div class="separator"></div>
<div style="width: 90%;margin: 0 auto">
    <span style="font-weight: bold">审批流程:</span>
    <!-- table -->
    <form class="form-inline" role="form" style="margin:10px 0px;" onsubmit="return false;">
        <div class="form-group">
            <label class="" for=" operateState">审批状态</label>
            <input type="text" class="form-line-control input-sm" id=" operateState" name=" operateState" placeholder="审批状态">
        </div>
        <div class="form-group">
            <label class="" for="begainTime">开始时间</label>
            <input  class="form-line-control datepicker input-sm" type="text" name="begainTime" id="begainTime" placeholder="开始时间">
        </div>
        <div class="form-group">
            <label class="" for="endTime">结束时间</label>
            <input  class="form-line-control datepicker input-sm" type="text" name="endTime" id="endTime" placeholder="结束时间">
        </div>
        <button type="button" class="btn btn-primary" id="searchBtn">查询</button>
    </form>
    <div class="row">
        <div class="col-md-12">
            <!-- BOX -->
            <div class="box">
                <div class="box-body" style="height: 420px;">
                    <table id="tableData" data-pagination="true" data-method="post" data-striped="true" data-page-number="1" data-page-size="9">

                    </table>
                </div>
            </div>
            <!-- /BOX -->
        </div>
    </div>
    <!-- /table -->
</div>
<script type="text/javascript" src="/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="/static/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/grid.locale-en.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table-zh-CN.js"></script>
<%--时间控件--%>
<script type="text/javascript" src="/static/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script type="text/javascript" src="/static/js/script.js"></script>

<script type="text/javascript" src="/static/public/public.js"></script>
</body>
<script>
    //    ztree
    var zTreeObj;
    var treeNodeId;
    function zTreeOnClick(event, treeId, treeNode) {
        var treeNodetId=treeNode.tId;
        treeNodeId=treeNodetId;
        if(treeNode.children == null){
            $("#morClassifyInput").val(treeNode.name);
            $("#treeDiv").hide();
        }
    };
    var setting = {
        callback: {
            onClick: zTreeOnClick
        },
        view: {
            showIcon: false
        }
    };
    $(document).ready(function(){
        var zNodes;
        ajaxPart("post","/business/getbuclassfy",'application/json;charset=UTF-8',"json","", function (response) {
//            zNodes=JSON.stringify(response.data);
            zNodes=(response.data);
            $("#morClassifyBtn").click(function () {
                $("#treeDiv").show();
                zTreeObj = $.fn.zTree.init($("#morClassify"), setting, zNodes);
            })
        })
        var index = parent.layer.getFrameIndex(window.name);
        var parentId=parent.$('#tableData').bootstrapTable('getSelections')[0].id;
//    $(document).ready(function () {
        ajaxPart("post","/business/copy",'application/x-www-form-urlencoded;charset=UTF-8',"json",{id:parentId},function (response) {
            $("#borrowerName").val(response.data.borrowerName);
            $("#businessConum").val(response.data.businessConum);
            $("#checker").val(response.data.checker);
            $("#cooNum").val(response.data.cooNum);
            $("#superDep option:selected").val(response.data.superDep);
            $("#mortageRate").val(response.data.mortageRate);
            $("#riskExposure").val(response.data.riskExposure);
            $("#customerManager").val(response.data.customerManager);
            $("#wareSuper").val(response.data.wareSuper);
            $("#deadLine").val(response.data.deadLine);
            $("#marketCity").val(response.data.marketCity);
            $("#material").val(response.data.material);
            $("#specType").val(response.data.specType);
            $("#measureUnit").val(response.data.measureUnit);
            $("#count").val(response.data.count);
            $("#totalValue").val(response.data.totalValue);
            $("#mortgageName").val(response.data.mortgageName);
            $("#priceUnit").val(response.data.priceUnit);
            $("#producer").val(response.data.producer);
            $("#unitPrice").val(response.data.unitPrice);
            $("#morValue").val(response.data.morValue);
            $("#morTime").val(response.data.morTime);
        });
//    })
    $("#addSave").click(function () {
        })
    $("#addCancle").click(function () {
        parent.layer.close(index);
    })
    })
    $("#searchBtn").click(function () {
        ajaxPart('POST','/business/flow',' application/json;charset=UTF-8',"json",JSON.stringify({
            operateState:$("#operateState ").val(),
            begainTime:$("#begainTime").val(),
            endTime:$("#endTime").val(),
            pageNum:1,
            pageSize:100
        }),function(response) {
            $('#tableData').bootstrapTable({
                columns: [
//                    {
//                    checkbox: true,
//                    title: ''
//                },
                    {
                    field: 'operateState',
                    title: '审批状态'
                }, {
                    field: 'reason',
                    title: '审批理由'
                }, {
                    field: 'isDeleted',
                    title: '是否被删除'
                }, {
                    field: 'createBy',
                    title: '创建人'
                }, {
                    field: 'createTime',
                    title: '记录时间'
                }, {
                    field: 'updateBy',
                    title: '更新人'
                }, {
                    field: 'updateTime',
                    title: '更新时间'
                }],
                data: response.data.list
            });
        })

    })
</script>
<script>
    jQuery(document).ready(function() {
        $(".datepicker").datepicker({
            dateFormat:'yy-mm-dd', // 设置日期格式
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNamesMin: ['日','一','二','三','四','五','六']
        });
        App.init(); //Initialise plugins and elements
    });
</script>
</html>
