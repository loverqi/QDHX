<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/22 0022
  Time: 下午 3:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../menu.jsp"%>
<html>
<head>
    <title>供应链金融信息共享平台</title>
    <link rel="stylesheet" href="/static/js/jquery-ui-1.10.3.custom/css/custom-theme/jquery-ui-1.10.3.custom.min.css">
    <link rel="stylesheet" href="/static/js/bootstrap-daterangepicker/daterangepicker-bs3.css">
    <link rel="stylesheet" href="/static/js/jqgrid/css/ui.jqgrid.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <%--用户新增--%>
    <link rel="stylesheet" type="text/css" href="/static/css/cloud-admin.css" >
    <link rel="stylesheet" type="text/css" href="/static/css/font-awesome/css/font-awesome.min.css">
    <style>
        .form-inline .form-control{
            width: 103px;
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
<div id="contentHtml" style="display: none" style="border: 1px red solid">
    <div class="row" >
        <div class="col-sm-12" style="border-bottom: 2px #999 dashed;">
            <div class="" style="padding: 10px;">
                <span class="float_left" style="font-weight: bold;font-size: 16px">业务审批</span>
               <span class="float_right" style="margin: 0 8px;">
                   <img src="../../../static/images/toolbar/iconApprove.png" alt="">
                   <button type="button" class="none_style_button" id="approveBtn" onclick="" >审批</button>
               </span>
               <span class="float_right" style="margin: 0 8px;">
                   <img src="../../../static/images/toolbar/iconNew.gif" alt="">
                   <button type="button" class="none_style_button" id="detailBtn" onclick="" data-toggle="modal" data-target="#myModal">详情</button>
               </span>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-inline" role="form" style="margin:20px 0px;" onsubmit="return false;">
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="borrowerNameRequire">借款人名称</label>
                    <input type="text" class="form-control col-md-8" id="borrowerNameRequire" name="realName" placeholder="借款人名称">
                </div>
                <div class="form-group col-md-5" style="margin-bottom: 5px;">
                    <label class="col-md-4" for="mortgageNameRequire">质物名称</label>
                    <input type="text" class="form-control col-md-8" id="mortgageNameRequire" name="username" placeholder="质物名称">
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="businessConumRequire">融资合同编号</label>
                    <input type="text" class="form-control col-md-8" id="businessConumRequire" placeholder="合同编号">
                </div>
                <div class="form-group col-md-5" style="margin-bottom: 5px;">
                    <label class="col-md-4" for="superDepRequire">监管机构</label>
                    <input type="text" class="form-control col-md-8" id="superDepRequire" placeholder="监管机构">
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="superDepRequire">开始时间</label>
                    <input  class="form-control datepicker col-md-8" type="text" name="regular" id="begainTime" placeholder="开始时间">
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="superDepRequire">结束时间</label>
                    <input  class="form-control datepicker col-md-8" type="text" name="regular" id="endTime" placeholder="结束时间">
                </div>
                <div class="form-group col-md-1" style="margin-bottom: 20px;">
                    <button type="button" class="btn btn-primary" id="searchBtn">查询</button>
                </div>
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
<script type="text/javascript" src="/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="/static/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/grid.locale-en.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table-zh-CN.js"></script>
<%--toolbar--%>
<script type="text/javascript" src="/static/js/jquery.ai-toolbar.js"></script>
<%--用户新增--%>
<script src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/static/js/script.js"></script>
<%--layer--%>
<script src="/static/layer-v3.1.1/layer/layer.js"></script>
<%--时间控件--%>
<script type="text/javascript" src="/static/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script type="text/javascript" src="/static/js/script.js"></script>
<script>

    var contentContainer=$("#contentHtml").html();
    $("#content").html(contentContainer);
    var selectId=parent.$('#tableData').bootstrapTable('getSelections')[0].id;
//    页面刷新时直接显示
    $(document).ready(function () {
            $('#tableData').bootstrapTable({
                dataField: "data",
                method: 'post',
                contentType: "application/json;charset=UTF-8",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
                queryParams: function (params) {
                    return JSON.stringify({pageSize: params.limit, pageNum: params.offset / params.limit + 1})
                },
                sidePagination: 'server',//指定服务器端分页
                url: '/business/query',
                dataType: 'json',
                pageNumber: 1,
                pagination: true,
                pageSize: 10,//单页记录数
                pageList: [10, 20, 30, 40, 50],//分页步进值
                responseHandler: function (params) {
                    return {
                        total: params.data.total, //总页数,前面的key必须为"total"
                        data: params.data.list//行数据，前面的key要与之前设置的dataField的值一致.
                    };
                },//请求数据成功后，渲染表格前的方法
                columns: [{
                    checkbox: true,
                    title: ''
                }, {
                    field: 'businessConum',
                    title: '融资合同编号'
                }, {
                    field: 'borrowerName',
                    title: '借款人名称'
                }, {
                    field: 'checker',
                    title: '融资金额'
                }, {
                    field: 'cooNum',
                    title: '融资发放时间'
                }, {
                    field: 'cooNum',
                    title: '融资到期时间'
                }]
            })
    });
    //    查询
    $("#searchBtn").click(function () {
        ajaxPart('POST','/business/query',' application/json;charset=UTF-8',"json",JSON.stringify({
            borrowerName:$("#borrowerNameRequire").val(),
            mortgageName:$("#mortgageNameRequire").val(),
            businessConum:$("#businessConumRequire").val(),
            superDep:$("#superDepRequire").val(),
            begainTime:$("#begainTime").val(),
            endTime:$("#endTime").val(),
            pageNum:1,
            pageSize:100
        }),function(response) {
            if(response.code==0){
                $('#tableData').bootstrapTable('load',{data:response.data.list,total:response.data.total});
            }else{
                alert(response.message);
            }
        })
    });
    //    详情
    $("#detailBtn").click(function(){
        var getSelections=$('#tableData').bootstrapTable('getSelections');
        var getSelectionsAll=[];
        for(var i=0;i<getSelections.length;i++){
            getSelectionsAll.push(getSelections[i].id);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据");
        }else if(getSelectionsAll.length == 1){
            layer.open({
                type: 2,
                title: '详情',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area : ['800px' , '520px'],
                content: 'bussiness_approve_detail/approveDetailBusiness.do'
            });
        }else{
            alert("只能选择一条数据")
        }
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
    //    审批
    $("#approveBtn").click(function () {
        var getSelections=$('#tableData').bootstrapTable('getSelections');
        var getSelectionsAll=[];
        for(var i=0;i<getSelections.length;i++){
            getSelectionsAll.push(getSelections[i].id);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据");
        }else if(getSelectionsAll.length == 1){
            layer.open({
                type: 2,
                title: '审批',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area : ['800px' , '520px'],
                content: 'approve/approveBusiness.do'
            });
        }else{
            alert("只能选择一条数据")
        }
    })
</script>
<script>
    jQuery(document).ready(function() {
        $(".datepicker").datepicker({
            dateFormat:'yy-mm-dd', // 设置日期格式
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNamesMin: ['日','一','二','三','四','五','六']
        });
        App.setPage("add");
    });
</script>
</body>
</html>

