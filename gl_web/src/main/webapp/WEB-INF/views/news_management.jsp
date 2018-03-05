<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/29 0029
  Time: 下午 5:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp"%>
<html>
<head>
    <title>供应链金融信息共享平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/static/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css">
    <link rel="stylesheet" href="/static/js/bootstrap-daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="/static/js/jqgrid/css/ui.jqgrid.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <%--用户新增--%>
    <link rel="stylesheet" type="text/css" href="/static/css/cloud-admin.css" >
    <link rel="stylesheet" type="text/css" href="/static/css/font-awesome/css/font-awesome.min.css">
    <style>
        .form-inline .form-control{
            width: 180px;
        }
        .float_left{
            float:left;
        }
        .float_right{
            float:right;
        }
        .clearfix{
            clear:both;
        }
        .none_style_button{
            border:0;
            padding: 0;
            background: none;
        }
        .navbar{
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<div id="contentHtml" style="display: none;border: 1px red solid">
    <div class="row" >
        <div class="col-sm-12" style="border-bottom: 2px #999 dashed;">
            <div class="" style="padding: 10px;">
                <span class="float_left" style="font-weight: bold;font-size: 16px">消息推送</span>
                  <span class="float_right" style="margin: 0 8px;">
                   <img src="../../static/images/toolbar/iconEdit.gif" alt="">
                   <button type="button" class="none_style_button" id="detailBtn" onclick="" >详情</button>
               </span>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-inline" role="form" style="margin:20px 0px;" onsubmit="return false;">
                <div class="form-group">
                    <label class="" for="borrowerNameRequire">借款人名称</label>
                    <input type="text" class="form-control" id="borrowerNameRequire" name="realName" placeholder="借款人名称">
                </div>
                <div class="form-group">
                    <label class="" for="mortgageNameRequire">监管机构</label>
                    <input type="text" class="form-control" id="mortgageNameRequire" name="username" placeholder="监管机构">
                </div>
                <div class="form-group">
                    <label class="" for="businessConumRequire">质物名称</label>
                    <input type="text" class="form-control" id="businessConumRequire" placeholder="质物名称">
                </div>
                <div class="form-group">
                    <label class="" for="businessConumRequire">数量</label>
                    <input type="text" class="form-control" id="" placeholder="数量">
                </div>
                <div class="form-group">
                    <label class="" for="superDepRequire">质押率</label>
                    <input type="text" class="form-control" id="superDepRequire" placeholder="质押率">
                </div>
                <button type="button" class="btn btn-primary" id="searchBtn">查询</button>
            </form>
        </div>
    </div>
    <div class="separate"></div>
    <!-- table -->
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
<script type="text/javascript" src="/static/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="/static/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/grid.locale-en.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table-zh-CN.js"></script>
<%--用户新增--%>
<script src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/static/js/script.js"></script>
<%--layer--%>
<script src="/static/layer-v3.1.1/layer/layer.js"></script>
<script>
    var contentContainer=$("#contentHtml").html();
    $("#content").html(contentContainer);
    //    查询
    $("#searchBtn").click(function () {
        ajaxPart('POST','/business/query',' application/json;charset=UTF-8',"json",JSON.stringify({
            borrowerName:$("#borrowerNameRequire").val(),
            mortgageName:$("#mortgageNameRequire").val(),
            businessConum:$("#businessConumRequire").val(),
            superDep:$("#superDepRequire").val(),
            pageNum:1,
            pageSize:100
        }),function(response) {
            $('#tableData').bootstrapTable({
                columns: [{
                    checkbox: true,
                    title: ''
                }, {
                    field: 'borrowerName',
                    title: '借款人名称'
                }, {
                    field: 'businessConum',
                    title: '融资合同编号'
                }, {
                    field: 'checker',
                    title: '复核岗'
                }, {
                    field: 'cooNum',
                    title: '合作协议号'
                }, {
                    field: 'superDep',
                    title: '监管机构'
                }, {
                    field: 'mortageRate',
                    title: '质押率'
                }, {
                    field: 'riskExposure',
                    title: '借款人用信风险敞口'
                }, {
                    field: 'customerManager',
                    title: '客户经理人员'
                }, {
                    field: 'wareSuper',
                    title: '现场检查岗人员'
                }, {
                    field: 'deadLine',
                    title: '业务到期时间'
                }, {
                    field: 'marketCity',
                    title: '市场'
                }, {
                    field: 'material',
                    title: '材质'
                }, {
                    field: 'specType',
                    title: '规格型号'
                }, {
                    field: 'measureUnit',
                    title: '计量单位'
                }, {
                    field: 'count',
                    title: '数量'
                }, {
                    field: 'mortageValue',
                    title: '质物价值'
                }, {
                    field: 'mortgageName',
                    title: '质物名称'
                }, {
                    field: 'priceUnit',
                    title: '价格单位'
                }, {
                    field: 'producer',
                    title: '生产厂家'
                }, {
                    field: 'unitPrice',
                    title: '单位价格'
                }],
                data: response.data.list
            });
        })
    });
    //    详情
    $("#detailBtn").click(function(){
        layer.open({
            type: 2,
            title: '新增',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['800px' , '520px'],
            content: 'detail/detailBusiness.do'
        });
        var selectId=parent.$('#tableData').bootstrapTable('getSelections')[0].id;
        ajaxPart("post","/business/get",'application/x-www-form-urlencoded;charset=UTF-8',"json",{id:selectId},function(response){
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
            $("#mortageValue").val(response.data.mortageValue);
            $("#mortgageName").val(response.data.mortgageName);
            $("#priceUnit").val(response.data.priceUnit);
            $("#producer").val(response.data.producer);
            $("#unitPrice").val(response.data.unitPrice);
        })
    })
</script>
<script>

</script>
</body>
</html>


