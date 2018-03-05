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
    <meta charset="UTF-8">
    <title>供应链金融信息共享平台</title>
    <!--<script src="js/jquery-1.8.3.min.js"></script>-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
    <meta  name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="/static/css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->

    <!-- BEGIN THEME STYLES -->
    <link href="/static/css/style-conquer.css" rel="stylesheet" type="text/css">
    <link href="/static/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="/static/pages/tasks.css" rel="stylesheet" type="text/css"/>
    <link href="/static/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="/static/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="/static/pages/login.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/ui-dialog.css" rel="stylesheet" type="text/css">
    <!-- END PAGE LEVEL SCRIPTS -->

    <!-- BEGIN CORE PLUGINS -->
    <script src="/static/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="/static/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
    <script src="/static/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript" ></script>
    <script src="/static/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript" ></script>
    <script src="/static/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
    <script src="/static/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="/static/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="/static/plugins/jquery.cookie.min.js" type="text/javascript"></script>
    <script src="/static/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
    <script src="/static/js/account.validate.js" type="text/javascript"></script>
    <script src="/static/js/component/dialog-plus.js" type="text/javascript"></script>
    <!-- END CORE PLUGINS -->

    <link rel="shortcut icon" href="favicon.ico"/>
    <style>
      .icon-user{
        /*border: 1px red solid;*/
      }
      body{
        margin: 0;
      }
      *{
        box-sizing: border-box;
      }
      button{
        border: 0;
        padding: 0;
      }
      .container{
        position: absolute;
        top: 50%;
        margin-top: -280px;
        bottom: 0;
        left: 0;
        right: 0;
        max-height: 400px;
        -moz-background-size: 100% 100%;
        background-size: 100% 100%;
      }
      .form {
        border: 1px transparent solid;
        margin:0 auto;
        width: 320px;
        padding: 20px;
        border-radius: 5px;
        background: url(../../static/images/form-background.png) 50% 50% no-repeat;
        background-size: cover;
        height: 224px;
      }
      .form-actions{
        padding: 10px 30px;
        border-top: none;
      }
      .logo-img img{
        display: block;
        margin: 20px auto;
      }
      .form-group{
        position: relative;
        width: 260px;
        margin-left: 30px;
      }
      .form-group label{
        position: absolute;
        top: 10px;
        left: 10px;
        font-size: 18px;
        color: #ccc;
      }
      .form-group input{
        padding-left: 33px;
      }
    </style>
  </head>
  <body>
  <div style="background: none;">
    <div style="background: url(../../static/images/banner.png) 50% 50% no-repeat;background-size: cover;width: 100%;height: 100%;"></div>
  </div>
  <div class="container">
    <div class="logo-img">
      <img src="../../static/images/bank-logo.png" alt="">
      <img src="../../static/images/login-font-logo.png" alt="">
    </div>
    <div class="form">
      <form class="login-form" onsubmit="return false;">
        <div class="form-group" style="margin-top: 40px;">
          <label for="username" class="icon-user"></label>
          <input type="text" id="username" name="username" class="form-control placeholder-no-fix" autocomplete="off" placeholder="用户名" style="width: 220px;">
        </div>
        <div class="form-group">
          <label for="password" class="icon-lock"></label>
          <input type="password" id="password" name="password" class="form-control placeholder-no-fix" autocomplete="off" placeholder="密码" style="width: 220px;">
        </div>
        <div class="form-actions" style="background: transparent;">
          <%--<input type="checkbox" name="remember-me" id="remember-me" class="forget-pwd">--%>
          <%--<label for="remember-me" style="color: #fff;">记住我</label>--%>
          <input type="button"  id="submitBtn" value="登陆" class="btn btn-info pull-right" style="background: #2A78FD;border: 1px solid #2A78FD">
        </div>
      </form>
    </div>
  </div>
  <script type="text/javascript">
$("#submitBtn").click(function () {
  $.ajax({
    type:'POST',
    url:'/login/userLogin.do',
//    dataType:'json',
    contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
    data:{
      username:$("#username").val(),
      password:$("#password").val()
    },
    success: function (response) {
      if(response.code==0){
        window.location.href = response.data.path;
      }else{
        alert(response.message);
      }
    },
    error:function (response){
      console.log(response)
    }
  })

  return false;
})
  </script>
  </body>
</html>
