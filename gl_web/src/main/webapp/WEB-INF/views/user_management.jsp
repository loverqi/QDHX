<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
        /*.form-inline .form-control{*/
            /*width: 120px;*/
        /*}*/
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
                <h4 class="modal-title" id="myModalLabel" style="font-weight: 600">用户新增</h4>
            </div>
            <div class="modal-body">
                <p>
                   <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">用户名</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" placeholder="" id="addUser">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">真实姓名</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" placeholder="" id="addRealUser">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">吸存号</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" placeholder="" id="addGreekId">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">密码</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" placeholder="" id="addPassword">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">手机号码</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" placeholder="" id="addPhoneNum">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">岗位</label>
                                <div class="col-sm-4 col-xs-4">
                                    <input class="form-control input-sm" type="text" onclick="return false;" readonly id="addPost" placeholder="请选择岗位">
                                    <input class="form-control input-sm" type="text" readonly id="addPostId" style="display: none;">
                                    <img src="/static/images/btnselect.png"  id="addPostBtn" style="cursor: pointer;position: relative;top:-24px;left: 170px;">
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
<div id="contentHtml" style="display: none">
   <div class="row" >
       <div class="col-sm-12" style="border-bottom: 2px #999 dashed;">
           <div class="" style="padding: 10px;">
               <span class="float_left" style="font-weight: bold;font-size: 16px">用户管理</span>
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
            <form class="form-inline" role="form" style="margin:20px 0px;width: 100%" onsubmit="return false;">
                    <div class="form-group col-md-5">
                        <label class="col-md-3" for="greekId">吸存号</label>
                        <input type="text" class="form-control col-md-9" id="greekId" name="greekId" placeholder="吸存号">
                    </div>
                        <div class="form-group col-md-5" style="margin-bottom: 5px;">
                            <label class="col-md-3" for="phoneNumber">手机号码</label>
                            <input type="text" class="form-control col-md-9" id="phoneNumber" placeholder="手机号码">
                        </div>
                        <div class="form-group col-md-5">
                            <label class="col-md-3" for="username">用户名</label>
                            <input type="text" class="form-control col-md-9" id="username" name="username" placeholder="用户名">
                        </div>
                        <div class="form-group col-md-5" style="margin-bottom: 5px;">
                            <label class="col-md-3" for="realName">真实姓名</label>
                            <input type="text" class="form-control col-md-9" id="realName" name="realName" placeholder="真实姓名">
                        </div>

                    <%--<div class="form-group" style="width: 10px;">1234</div>--%>
                    <div class="form-group col-md-5">
                        <label class="col-md-3" for="deptName">部门</label>
                        <select id="deptName" class="form-control col-md-9">
                            <option value="-1">请选择部门</option>
                        </select>
                        </div>
                    <div class="form-group col-md-5">
                        <label class="col-md-3" for="postName">岗位</label>
                        <select id="postName"  class="form-control col-md-9" style="min-width: 120px"></select>
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
                <div class="box-body" style="height: 360px">
                    <table id="tableData"  data-method="post" data-striped="true">

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
    $(document).ready(function(){
        var postArrs=[];

        ajaxPart("post",'/dept/getSimplifyDepts.do','',"","",function(response){
            for(var i=0;i<response.data.length;i++){
                $("#deptName").append("<option value="+i+">"+response.data[i].name+"</option>");
                var deptId=response.data[i].id;
                ajaxPart("post",'/post/getSimplifyPosts.do',' application/x-www-form-urlencoded;charset=UTF-8',"json",{
                    deptId:deptId
                },function(responseData) {
                    var postArr=[];
                    for(var j=0;j<responseData.data.length;j++){
                        postArr.push({name:responseData.data[j].name,id:responseData.data[j].id});
                    }
                    postArrs.push(postArr)
                })
            }
        })
        $("#deptName").change(function () {
            $("#postName").html("");
            var index = $(this).val();
            if(index -0 == -1){
                $("#postName").append("<option value = ''>请先选择部门</option>");
                return;
            }
            var postData=postArrs[index];
            for(var k in postData){
                $("#postName").append("<option value = '"+postData[k].id+"'>"+postData[k].name+"</option>")
            }
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
                url:'/user/getUsers.do',
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
                field: 'username',
                title: '用户名'
            }, {
                field: 'realName',
                title: '真实姓名'
            },{
                field: 'greekId',
                title: '吸存号'
            },{
                field: 'deptName',
                title: '部门名称'
            } ,{
                field: 'postName',
                title: '岗位名称'
            },{
                field: 'phone',
                title: '手机号码'
            }]
        });
    })
//    查詢
    $("#searchBtn").click(function () {
        ajaxPart('POST','/user/getUsers.do',' application/x-www-form-urlencoded;charset=UTF-8',"json",{
            pageNum:1,
            pageSize:10,
            username:$("#username").val(),
            greekId:$("#greekId").val(),
            realName:$("#realName").val(),
            postId:$("#postName").val(),
            phone:$("#phoneNumber").val()
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
        ajaxPart('post','/user/deleteUsers.do',"application/json;charset=UTF-8","",JSON.stringify(getSelectionsAll),function (response) {
            if(response.code==0){
                $('#tableData').bootstrapTable('remove', {field:'id',values:getSelectionsAll});
                alert(response.message);
            }else{
                alert(response.message);
            }
        });
    })
    //新增
    $("#addBtn").click(function () {
        $("#addUser").val("");
        $("#addRealUser").val("");
        $("#addGreekId").val("");
        $("#addPassword").val("");
        $("#addPhoneNum").val("");
        $("#addPost").val("");
        $("#myModalLabel").html("用户新增");
        $('#myModal').modal('show');
        //    保存
        $("#addSave").click(function () {
            ajaxPart("post","/user/AddOrModifyUser.do",'application/json;charset=UTF-8',"json",JSON.stringify({
                        username:$("#addUser").val(),
                        realName:$("#addRealUser").val(),
                        greekId:$("#addGreekId").val(),
                        phone:$("#addPhoneNum").val(),
                        password:$("#addPassword").val(),
                        postId:$("#addPostId").val()
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
            getSelectionsAll.push(getSelections[i].id);
        }
        if(getSelectionsAll==""){
            alert("请选择至少一条数据");
            $('#myModal').modal('hide');
        }else if(getSelectionsAll.length == 1){
        $("#myModalLabel").html("用户编辑");
        $('#myModal').modal('show');
        var getSelections=$('#tableData').bootstrapTable('getSelections');

//        输入框的自动填充
        $("#addUser").val(getSelections[0].username);
        $("#addRealUser").val(getSelections[0].realName);
        $("#addPhoneNum").val(getSelections[0].phone);
        $("#addGreekId").val(getSelections[0].greekId);
        $("#addPost").val(getSelections[0].postName);
//        发数据给后台
        $("#addSave").click(function () {
            ajaxPart("post","/user/AddOrModifyUser.do","application/json;charset=UTF-8","json",JSON.stringify({
                id:getSelections[0].id,
                username:$("#addUser").val(),
                realName:$("#addRealUser").val(),
                greekId:$("#addGreekId").val(),
                phone:$("#addPhoneNum").val(),
                password:$("#addPassword").val(),
                postId:$("#addPostId").val()
            }), function (response) {
                if(response.code==0){
                    $('#myModal').modal('hide');
//                    $('#tableData').bootstrapTable('refresh',{silent:true});
                }else{
                    alert(response.message);
                }
            })
        })
        }else{
            alert("只能选择一条数据")
            $('#myModal').modal('hide');
        }
    })
    //    岗位种类树形图
    $('#addPostBtn').on('click', function(){
        layer.open({
            type: 2,
            title: '岗位种类',
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area : ['360px' , '520px'],
            content: '/views/tree/userAddPost.do'
        });
    });
</script>
<script>
    jQuery(document).ready(function() {
        App.setPage("add");  //Set current page
//        App.init(); //Initialise plugins and elements
    });
</script>
</body>
</html>
