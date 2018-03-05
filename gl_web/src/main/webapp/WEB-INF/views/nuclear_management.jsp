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
                <span class="float_left" style="font-weight: bold;font-size: 16px">巡核库管理</span>
                  <span class="float_right" style="margin: 0 8px;">
                   <img src="../../static/images/toolbar/iconDetail.png" alt="">
                   <button type="button" class="none_style_button" id="detailBtn" onclick="" >详情</button>
               </span>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-inline" role="form" style="margin:20px 0px;" onsubmit="return false;">
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="borrowerName">借款人名称</label>
                    <input type="text" class="form-control col-md-8" id="borrowerName" name="borrowerName" placeholder="借款人名称">
                </div>
                <div class="form-group col-md-5" style="margin-bottom: 5px;">
                    <label class="col-md-4" for="superDep">监管单位</label>
                    <select name="" id="superDep" placeholder="监管单位" class="form-control col-md-8" style="min-width: 120px">
                        <option value="">请选择监管单位</option>
                        <option value="日照大宗商品交易中心">日照大宗商品交易中心</option>
                    </select>
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="cooNum">合作协议号</label>
                    <input type="text" class="form-control col-md-8" id="cooNum" placeholder="合作协议号">
                </div>
                <div class="form-group col-md-5" style="margin-bottom: 5px;">
                    <label class="col-md-4" for="riskExposure">融资敞口</label>
                    <input type="text" class="form-control col-md-8" id="riskExposure" placeholder="融资敞口">
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="begainTime">放款开始时间</label>
                    <input  class="form-control datepicker col-md-8" type="text" name="regular" id="begainTime" placeholder="放款开始时间">
                </div>
                <div class="form-group col-md-5">
                    <label class="col-md-4" for="endTime">放款结束时间</label>
                    <input  class="form-control datepicker col-md-8" type="text" name="regular" id="endTime" placeholder="放款结束时间">
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
//    加载页面时
    $(document).ready(function () {
        ajaxPart('POST', '/bs_inspect/query', ' application/json;charset=UTF-8', "json", JSON.stringify({
            pageNum: 1,
            pageSize: 100
        }), function (response) {
            $('#tableData').bootstrapTable({
                columns: [{
                    checkbox: true,
                    title: ''
                }, {
                    field: 'business_conum',
                    title: '融资合同编号'
                }, {
                    field: 'borrower_name',
                    title: '借款人全称'
                },
                    {
                    field: 'checker',
                    title: '融资金额'
                },
                    {
                        field: 'create_time',
                        title: '融资发放时间'
                    }, {
                        field: 'update_time',
                        title: '融资到期时间'
                    }],
                data: response.data.list
            });
        })
        //    查询
        $("#searchBtn").click(function () {
            ajaxPart('POST', '/bs_inspect/query', ' application/json;charset=UTF-8', "json", JSON.stringify({
                borrowerName: $("#borrowerName").val(),
                superDep: $("#superDep").val(),
                cooNum: $("#cooNum").val(),
                riskExposure: $("#riskExposure").val(),
                begainTime: $("#begainTime").val(),
                endTime: $("#endTime").val(),
                pageNum: 1,
                pageSize: 100
            }), function (response) {
                if (response.code == 0) {
                    $('#tableData').bootstrapTable('load', response.data.list);
                } else {
                    alert(response.message);
                }
            });
        })
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
                    content: 'nuclear/nuclearDetail.do'
                });
            }else{
                alert("只能选择一条数据")
            }
        })
    });

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


