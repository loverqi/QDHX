<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/25 0025
  Time: 上午 9:38
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
    <link rel="stylesheet" href="/static/ztree/css/zTreeStyle.css" type="text/css">
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
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">部门</label>
                        <div class="col-sm-4 col-xs-4">
                            <select class="form-control input-sm" type="text" placeholder="" id="addDepartment">
                                <option value="" id="originalDepartment">请选择部门</option>
                            </select>
                            <input class="form-control input-sm" type="text" placeholder="" id="addDepartmentId"  style="display: none">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">岗位名称</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="addPost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">权限</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="addAuthority" onclick="return false;" readonly>
                            <input class="form-control input-sm" type="text" placeholder="" id="addAuthorityId" style="display: none">
                            <img src="/static/images/btnselect.png"  id="addAuthorityBtn" style="cursor: pointer;position: relative;top:-24px;left: 170px;">
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
<div id="contentHtml" style="display: none" style="">
    <div class="row" >
        <div class="col-sm-12" style="border-bottom: 2px #999 dashed;">
            <div class="" style="padding: 10px;">
                <span class="float_left" style="font-weight: bold;font-size: 16px">岗位管理</span>
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
                    <label class="" for="deptName">部门</label>
                    <select type="text" class="form-control" id="deptName" name="deptName" placeholder="部门">
                        <option value="">请选择部门</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="" for="postName">岗位名称</label>
                    <input type="text" class="form-control" id="postName" name="postName" placeholder="岗位名称">
                </div>
                <button type="button" class="btn btn-primary" id="searchBtn">查询</button>
            </form>
        </div>
    </div>
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
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="/static/js/jQuery-BlockUI/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/grid.locale-en.min.js"></script>
<script type="text/javascript" src="/static/js/jqgrid/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table-zh-CN.js"></script>
<%--toolbar--%>
<script type="text/javascript" src="/static/js/jquery.ai-toolbar.js"></script>
<%--layer--%>
<script src="/static/layer-v3.1.1/layer/layer.js"></script>
<%--用户新增--%>
<script src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/static/js/script.js"></script>
<script>
    var contentContainer=$("#contentHtml").html();
    $("#content").html(contentContainer);
    $(document).ready(function(){
        var deptId;
        var deptIdArr=[];
        ajaxPart("post",'/dept/getSimplifyDepts.do',' application/json;charset=UTF-8',"json","",function(response){
            for(var i=0;i<response.data.length;i++){
                $("#addDepartment").append("<option value="+i+">"+response.data[i].name+"</option>");
                deptId=response.data[i].id;
                deptIdArr.push(deptId)
            }
        })
        $("#addDepartment").change(function () {
            var index = $(this).val();
            if(index -0 == -1){
                $("#addDepartmentId").append("<option>请先选择部门</option>");
                return;
            }
            $("#addDepartmentId").val(deptIdArr[index]);
        })

        $('#tableData').bootstrapTable({
                dataField:"data",
                method: 'post',
                contentType: "application/x-www-form-urlencoded;charset=UTF-8",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
                queryParams:function(params){
                    return {
                        pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
                        pageNum : params.offset/params.limit+1 //当前页面,默认是上面设置的1(pageNumber)
                    }
                },
                sidePagination:'server',//指定服务器端分页
                url:'/post/getPosts.do',
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
                    field: 'deptName',
                    title: '部门'
                }, {
                    field: 'postName',
                    title: '岗位名称'
                }, {
                    field: 'privs',
                    title: '用户已有角色'
                }]
            });
        ajaxPart('POST','/dept/getSimplifyDepts.do','application/json;charset=UTF-8',"json","",function (response) {
            for(var i=0;i<response.data.length;i++){
                $("#deptName").append("<option value="+response.data[i].id+">"+response.data[i].name+"</option>");
            }
        })
        })

    //    查詢
    $("#searchBtn").click(function () {
        ajaxPart('POST','/post/getPosts.do',' application/x-www-form-urlencoded;charset=UTF-8',"json",{
            deptId:$("#deptName").val(),
            postName:$("#postName").val(),
            pageNum:1,
            pageSize:100
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
        }
        ajaxPart('post','/post/deletePosts.do',"application/json;charset=UTF-8","",JSON.stringify(getSelectionsAll),function (response) {
            if(response.code==0){
                alert(response.message);
                $('#tableData').bootstrapTable('remove', {field:'id',values:getSelectionsAll});
            }else{
                alert(response.message);
            }
        });
    })
    //    新增
    $("#addBtn").click(function () {
        $("#addPost").val("");
        $("#myModalLabel").html("岗位新增");
        $('#myModal').modal('show');
        //    保存
        $("#addSave").click(function () {
            ajaxPart("post","/post/AddOrModifyPost.do",'application/json;charset=UTF-8',"json",JSON.stringify({
                deptId:$("#addDepartmentId").val(),
                postName:$("#addPost").val(),
                privIds:($("#addAuthorityId").val()).split(',')
            }),function (response) {
                if(response.code==0){
                    alert(response.message)
                    $('#myModal').modal('hide');
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
            getSelectionsAll.push(getSelections[i].deptId);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据")
            $('#myModal').modal('hide');
        }else if(getSelectionsAll.length == 1){
            $("#myModalLabel").html("部门编辑");
            $('#myModal').modal('show');
            var getSelections = $('#tableData').bootstrapTable('getSelections');
//        输入框的自动填充
            $("#originalDepartment").text(getSelections[0].deptName);
            $("#addPost").val(getSelections[0].postName);
            $("#addSave").click(function () {
                ajaxPart("post", "/post/AddOrModifyPost.do", "application/json;charset=UTF-8", "json", JSON.stringify({
                    id: getSelections[0].id,
                    deptId:$("#addDepartmentId").val(),
                    postName:$("#addPost").val(),
                    privIds:($("#addAuthorityId").val()).split(',')
                }), function (response) {
                    if (response.code == 0) {
                        alert(response.message)
                        $('#myModal').modal('hide');
                    } else {
                        alert(response.message)
                    }
                })
            })
        }else{
            alert("只能选择一条数据")
            $('#myModal').modal('hide');
        }
    })
    //    岗位种类树形图
    $('#addAuthorityBtn').on('click', function(){
        layer.open({
            type: 2,
            title: '权限',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['360px' , '520px'],
            content: '/views/tree/addAuthority.do'
        });
    });
</script>
<script>
    $(document).ready(function() {
        App.setPage("add");  //Set current page
//        App.init(); //Initialise plugins and elements
    });
</script>
</body>
</html>

