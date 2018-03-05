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
                <span class="float_left" style="font-weight: bold;font-size: 16px">业务到期</span>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-inline" role="form" style="margin:20px 0px;" onsubmit="return false;">
                <div class="form-group">
                    <label class="" for="begainTime">开始时间</label>
                    <input  class="form-control datepicker" type="text" name="regular" id="begainTime" placeholder="开始时间">
                </div>
                <div class="form-group">
                    <label class="" for="endTime">结束时间</label>
                    <input  class="form-control datepicker" type="text" name="regular" id="endTime" placeholder="结束时间">
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
//    刷新页面时就加载
    $(document).ready(function () {
        ajaxPart('POST','/bsdeadline/query',' application/json;charset=UTF-8',"json",JSON.stringify({
            begainTime:$("#begainTime").val(),
            endTime:$("#endTime").val(),
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
                },{
                    field: 'shuhuoValue',
                    title: '赎货金额'
                },{
                    field: 'shuhuoTimes',
                    title: '赎货次数 '
                },{
                    field: 'createTime',
                    title: '记录时间'
                },{
                    field: 'isShuhuo',
                    title: '是否赎货'
                },{
                    field: 'shuhuoCount',
                    title: '赎货次数'
                },{
                    field: 'businessConum',
                    title: '借款人合同编号'
                },{
                    field: 'deadLine',
                    title: '业务到期时间'
                }],
                data: response.data.list
            });
        })
    });
    //    查询
    $("#searchBtn").click(function () {
        ajaxPart('POST','/bsdeadline/query',' application/json;charset=UTF-8',"json",JSON.stringify({
            begainTime:$("#begainTime").val(),
            endTime:$("#endTime").val(),
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
                },{
                    field: 'shuhuoValue',
                    title: '赎货金额'
                },{
                    field: 'shuhuoTimes',
                    title: '赎货次数 '
                },{
                    field: 'createTime',
                    title: '记录时间'
                },{
                    field: 'isShuhuo',
                    title: '是否赎货'
                },{
                    field: 'shuhuoCount',
                    title: '赎货次数'
                },{
                    field: 'businessConum',
                    title: '借款人合同编号'
                },{
                    field: 'deadLine',
                    title: '业务到期时间'
                }],
                data: response.data.list
            });
        })
    });
</script>
<script>
    $(document).ready(function() {
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


