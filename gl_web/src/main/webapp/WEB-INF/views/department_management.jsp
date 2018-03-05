<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/10 0010
  Time: 上午 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menu.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
            width: 145px;
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel" style="font-weight: 600">部门新增</h4>
            </div>
            <div class="modal-body">
                <p>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">部门名称</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="addDepartment">
                        </div>
                    </div>
                </form>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="addSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="addCancle">取消</button>
            </div>
        </div>
    </div>
</div>
<div id="contentHtml" style="display: none" style="border: 1px red solid">
    <div class="row" >
        <div class="col-sm-12" style="border-bottom: 2px #999 dashed;">
            <div class="" style="padding: 10px;">
                <span class="float_left" style="font-weight: bold;font-size: 16px">部门管理</span>
               <span class="float_right" style="margin: 0 8px;">
                   <img src="../../static/images/toolbar/iconDelete.gif" alt="">
                    <button type="button" class="none_style_button" id="delBtn" onclick="">删除</button>
               </span>
               <span class="float_right" style="margin: 0 8px;">
                   <img src="../../static/images/toolbar/iconEdit.gif" alt="">
                   <button type="button" class="none_style_button" id="editBtn" onclick="" >编辑</button>
               </span>
               <span class="float_right" style="margin: 0 8px;">
                   <img src="../../static/images/toolbar/iconNew.gif" alt="">
                   <button type="button" class="none_style_button" id="addBtn" onclick="">新增</button>
               </span>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <form class="form-inline" role="form" style="margin:20px 0px;" onsubmit="return false;">
                <div class="form-group">
                    <label class="" for="deptName">部门名称</label>
                    <input type="text" class="form-control" id="deptName" name="deptName" placeholder="部门名称">
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
                    <table id="tableData" data-method="post" data-striped="true">

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
<%--toolbar--%>
<script type="text/javascript" src="/static/js/jquery.ai-toolbar.js"></script>
<%--用户新增--%>
<script src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/static/js/script.js"></script>
<script>
    function loadTableData(param){
        ajaxPart("post",'/dept/getDepts.do?pageNum=1&pageSize=100',' application/x-www-form-urlencoded;charset=UTF-8',"json","",function(response) {
            param.success({
                total:response.data.total,
                rows:response.data.list
            })
        });
    }
    var contentContainer=$("#contentHtml").html();
    $("#content").html(contentContainer);
    $(document).ready(function(){
//        ajaxPart("post",'/dept/getDepts.do?pageNum=1&pageSize=10',' application/json;charset=UTF-8',"json","",function(response) {
            $('#tableData').bootstrapTable({
                dataField:"data",
                method: 'post',
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
//                queryParams:queryParams({limit:10,offset:0}),//请求服务器时所传的参数
                queryParams:function(params){
                    return {
                        pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
                        pageNum : params.offset/params.limit+1 //当前页面,默认是上面设置的1(pageNumber)
                    }
                },
                sidePagination:'server',//指定服务器端分页
                url:'/dept/getDepts.do',
                dataType:'json',
                pageNumber:1,
                pagination:true,
                pageSize:10,//单页记录数
                pageList:[10,20,30,40,50],//分页步进值
                responseHandler:function (params){
                    return {
                        total : params.data.total, //总页数,前面的key必须为"total"
                        data : params.data.list//行数据，前面的key要与之前设置的dataField的值一致.
                    };
                },//请求数据成功后，渲染表格前的方法
                columns: [{
                    checkbox: true,
                    title: ''
                }, {
                    field: 'id',
                    title: '部门ID'
                }, {
                    field: 'deptName',
                    title: '部门名称'
                }]
            });
//        })
    });
    //    查詢
    $("#searchBtn").click(function () {
        ajaxPart('POST','/dept/getDepts.do',' application/x-www-form-urlencoded;charset=UTF-8',"json",{
                deptName:$("#deptName").val(),
                pageNum:1,
                pageSize:10
        },function (response) {
            if(response.code==0){
                $('#tableData').bootstrapTable('load',{data:response.data.list,total:response.data.total});
            }else{
                alert(response.message);
            }
        });
    })
    //删除
    $("#delBtn").click(function () {
        var getSelections=$('#tableData').bootstrapTable('getSelections');
        var getSelectionsAll=[];
        for(var i=0;i<getSelections.length;i++){
            getSelectionsAll.push(getSelections[i].id);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据")
        }else{
        ajaxPart('post','/user/deleteUsers.do',"application/json;charset=UTF-8","",JSON.stringify(getSelectionsAll),function (response) {
            if(response.code==0){
                $('#tableData').bootstrapTable('remove', {field:'id',values:getSelectionsAll});
            }else{
                alert(response.message);
            }
        });
        }
    })
//    新增
    $("#addBtn").click(function () {
        $("#addDepartment").val("");
        $("#myModalLabel").html("用户新增");
        $('#myModal').modal('show');
        //    保存
        $("#addSave").click(function () {
            ajaxPart("post","/dept/AddOrModifyDept.do",'application/json;charset=UTF-8',"json",JSON.stringify({
                deptName:$("#addDepartment").val()
            }),function (response) {
                if(response.code==0){
                    alert(response.message)
                    $('#myModal').modal('hide');
//                    $("#tableData").bootstrapTable('refresh');
                }else{
                    alert(response.message)
                }
            });
        })
    })
    //    编辑
    $("#editBtn").click(function () {
        var getSelections=$('#tableData').bootstrapTable('getSelections');
        var getSelectionsAll=[];
        for(var i=0;i<getSelections.length;i++){
            getSelectionsAll.push(getSelections[i].id);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据")
            $('#myModal').modal('hide');
        }else if(getSelectionsAll.length == 1){
        $("#myModalLabel").html("部门编辑");
        $('#myModal').modal('show');
        var getSelections=$('#tableData').bootstrapTable('getSelections');
//        输入框的自动填充
        $("#addDepartment").val(getSelections[0].deptName);
        $("#addSave").click(function () {
            ajaxPart("post","/dept/AddOrModifyDept.do","application/json;charset=UTF-8","json",JSON.stringify({
                id:getSelections[0].id,
                deptName:$("#addDepartment").val()
            }), function (response) {
                if(response.code==0){
                    $('#myModal').modal('hide');
                }else{
                    alert(response.message)
                }
            })
        })
        }else{
            alert("只能选择一条数据")
            $('#myModal').modal('hide');
        }
    })
</script>
<script>
    jQuery(document).ready(function() {
        App.setPage("add");  //Set current page
//        App.init(); //Initialise plugins and elements
    });
</script>
</body>
</html>
