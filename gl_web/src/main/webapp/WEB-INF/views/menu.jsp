<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/4 0004
  Time: 下午 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>供应链金融信息共享平台</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/css/cloud-admin.css" >
    <link rel="stylesheet" type="text/css"  href="/static/css/night.css" >
    <link rel="stylesheet" type="text/css"  href="/static/css/responsive.css" >
    <link rel="stylesheet" type="text/css" href="/static/css/font-awesome/css/font-awesome.min.css">
    <style>
        .clearfix{
            clear: both;
        }
       .borderWhite{
           border-bottom: 1px #cdd0d8 solid;
       }
    </style>
</head>
<body>
<div class="modal fade" id="modifyPwdModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" style="font-weight: 600">修改密码</h4>
            </div>
            <div class="modal-body">
                <p>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">原密码</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="oldPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">新密码</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="newPassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label col-sm-offset-1 col-xs-2 col-xs-offset-3">密码确认</label>
                        <div class="col-sm-4 col-xs-4">
                            <input class="form-control input-sm" type="text" placeholder="" id="confirmPassword">
                        </div>
                    </div>
                </form>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="pwdSave">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="pwdCancle">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- HEADER -->
<header class="navbar clearfix" id="header">
    <div class="container">
        <div class="navbar-brand">
            <!-- COMPANY LOGO -->
            <a href="index.html">
                <img src="../../static/images/font-logo.png" alt="" class="img-responsive" >
            </a>
            <!-- /COMPANY LOGO -->
            <!-- TEAM STATUS FOR MOBILE -->
            <div class="visible-xs">
                <a href="#" class="team-status-toggle switcher btn dropdown-toggle">
                    <i class="fa fa-users"></i>
                </a>
            </div>
            <!-- /TEAM STATUS FOR MOBILE -->
            <!-- SIDEBAR COLLAPSE -->
            <div id="sidebar-collapse" class="sidebar-collapse btn">
                <i class="fa fa-bars" data-icon1="fa fa-bars" data-icon2="fa fa-bars" ></i>
            </div>
            <!-- /SIDEBAR COLLAPSE -->
        </div>
        <ul class="nav navbar-nav pull-right">
            <!-- BEGIN NOTIFICATION DROPDOWN -->
            <li class="" id="header-notification">
                <a href="#" class="dropdown-toggle" id="getLoginUsername"></a>
            </li>
            <li class="" id="userUnlogin">
                <a href="#" class="dropdown-toggle">退出登陆</a>
            </li>
            <li class="" id="modifyPwdBtn">
                <a href="#" class="dropdown-toggle">修改密码</a>
            </li>
        </ul>
    </div>
</header>
<!--/HEADER -->
<!-- PAGE -->
<section id="page">
    <!-- SIDEBAR -->
    <div id="sidebar" class="sidebar">
        <div class="sidebar-menu nav-collapse">
            <!-- SIDEBAR MENU -->
            <ul id="sidebarContainer">

            </ul>
            <!-- /SIDEBAR MENU -->
        </div>
    </div>
    <!-- /SIDEBAR -->
    <div id="main-content">
        <div class="container">
            <div class="row">
                <div id="content" class="col-lg-12">

                </div>
            </div>
        </div>
    </div>
</section>
<!--/PAGE -->
<!--JQUERY-->
<script src="/static/js/jquery/jquery-2.0.3.min.js"></script>
<!-- JQUERY UI-->
<script src="/static/js/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<!-- BOOTSTRAP -->
<script src="/static/js/bootstrap-dist/js/bootstrap.min.js"></script>
<script src="/static/js/jQuery-slimScroll-1.3.0/jquery.slimscroll.min.js"></script>
<script src="/static/js/jQuery-slimScroll-1.3.0/slimScrollHorizontal.min.js"></script>
<script src="/static/js/jQuery-Cookie/jquery.cookie.min.js"></script>
<script src="/static/js/script.js"></script>
<script src="/static/public/public.js"></script>
<script>
    $(document).ready(function () {
        ajaxPart('post','/login/getLoginUser.do',"","","",function (response) {
            $("#getLoginUsername").html(response.data.realName);
        })
        var thisMenu  = ${thisMenu}+"";
        thisMenu=thisMenu.toString();
        var firstMenu=thisMenu.substr(0,2);
        var secondMenu=thisMenu.substr(0,4);
//        var thirdMenu=thisMenu.substr(4,2);
//        var endMenu=thisMenu.substr(thisMenu.length-2);
        ajaxPart('post','/menu/getAllMenu.do',"application/json;charset=UTF-8","json","",function (response) {
            var html='';
            for(var i=0;i<response.data.length;i++){
                var item=response.data[i];
                if(firstMenu == item.id){
                    html+='<li '+(item.children&&item.children.length>0&&('class="has-sub active"'))+'>';
                    html+='<a href="javascript:;" class="">';
                    html+='<i class="'+item.icon+'"></i><span class="menu-text">'+item.text+'</span>';
                    html+='<span class="arrow open"></span>';
                }else{
                    html+='<li '+(item.children&&item.children.length>0&&('class="has-sub"'))+'>';
                    html+='<a href="javascript:;" class="">';
                    html+='<i class="'+item.icon+'"></i><span class="menu-text">'+item.text+'</span>';
                    (item.children&&item.children.length>0)&&(html+='<span class="arrow"></span>');
                }
                    html+='</a>';
                    if(item.children&&item.children.length>0){
                        html+='<ul class="sub">';
                        for(var j in item.children) {
                            var pathUrl = item.children[j].path.length>0?item.children[j].path :"javascript:;";
                            if(secondMenu == item.children[j].id){
                                html += '<li class="has-sub-sub current">';
                                html+='<a href="' +pathUrl+ '"><i></i><span class="sub-menu-text">' + item.children[j].text + '</span>';
                                (item.children[j].children && item.children[j].children.length > 0) && (html += '<span class="arrow open"></span>');
                            }else{
                                html += '<li class="has-sub-sub">';
                                html+='<a href="' +pathUrl+ '"><i></i><span class="sub-menu-text">' + item.children[j].text + '</span>';
                                (item.children[j].children && item.children[j].children.length > 0) && (html += '<span class="arrow"></span>');
                            }
                            html += '</a>';
                            if(item.children[j].children&&item.children[j].children.length>0){
                                html+='<ul class="sub-sub">';
                                for(var k in item.children[j].children){
                                    var pathUrlThird = item.children[j].children[k].path.length>0?item.children[j].children[k].path :"javascript:;";
                                    html+='<li>';
                                    html+='<a class="" href="'+pathUrlThird+'">';
                                    if(thisMenu == item.children[j].children[k].id){
                                        html+='<span class="borderWhite"">'+item.children[j].children[k].text+'</span>';
                                    }else{
                                        html+='<span class=""">'+item.children[j].children[k].text+'</span>';
                                    }

                                    html+='</a>';
                                    html+='</li>';
                                }
                                html+='</ul>';
                            }
                            html+'</li>';
                            //如果有三级目录，继续循环
                        }
                        html+='</ul>';
                    }
                    html+='</li>';

            }
            $("#sidebarContainer").html(html);

            App.init(); //Initialise plugins and elements
        })
//        当前页面
        ajaxPart('post','/menu/getAllMenu.do',"application/json;charset=UTF-8","json","",function (responseData) {

        })
    })
//    退出登陆
    $("#userUnlogin").click(function () {
    ajaxPart('get','/login/userUnlogin.do',"","","",function (response){
        if(response.code==0){
            location.href="login.do";
        }
    })
})
    //    修改密码
    $("#modifyPwdBtn").click(function () {
            $('#modifyPwdModal').modal('show');
            $("#pwdSave").click(function () {
                if (!validateModifyPwd()) return false;
                ajaxPart('POST', '/user/changePwd.do', ' application/x-www-form-urlencoded;charset=UTF-8', "json", {
                    oldPassword:$("#oldPassword").val(),
                    newPassword:$("#newPassword").val()
                }, function (response) {
                    if(response.code==0){
                        $('#myModal').modal('hide');
                    }else{
                        alert(response.message);
                    }
                })
            })
    })

    function validateModifyPwd() {
        var oldpwd = $("#oldPassword").val();
        if (oldpwd == '') {
            alert("原密码不能为空!");
            return false;
        }
        var newPwd = $("#newPassword").val();
        if (newPwd == '') {
            alert("新密码不能为空!");
            return false;
        }
        var confirmPwd = $("#confirmPassword").val();
        if (confirmPwd == '') {
            alert("密码确认不能为空!");
            return false;
        }
        if (newPwd != confirmPwd) {
            alert("两次密码输入不一致");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
