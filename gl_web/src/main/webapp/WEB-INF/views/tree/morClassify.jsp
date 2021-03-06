<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/5 0005
  Time: 下午 2:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--ztree--%>
<link rel="stylesheet" href="/static/ztree/css/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="/static/css/cloud-admin.css" >
<script type="text/javascript" src="/static/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/static/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/static/public/public.js"></script>
<html>
<head>
    <title>Title</title>
    <style>
        .ztree *{
            font-size: 14px;
        }
        .treeDiv ul li{
            line-height: 18px;
        }
    </style>
</head>
<body>
<div id="treeDiv" style="position: absolute;top: 10px;left: 30px;overflow-y: auto">
    <ul id="morClassify" class="ztree"></ul>
</div>
<div style="margin: 0 auto;position: absolute;bottom: 20px;left: 100px;">
    <button type="button" class="btn btn-primary" id="saveBtn" >保存</button>
    <button type="button" class="btn btn-default" id="cancleBtn">关闭</button>
</div>
<script>
    var index = parent.layer.getFrameIndex(window.name);
    //ztree
    var zTreeObj;
    var treeNodeId;
    var selectTreeNode;
    function zTreeOnClick(event, treeId, treeNode) {
    if(treeNode.children == null){
//        $("#morClassifyInput").val(treeNode.name);
        selectTreeNode=treeNode.name;
    }
    };
    var setting = {
    callback: {
        onClick: zTreeOnClick
    },
    view: {
//    showIcon: false
    }
    };
    $(document).ready(function(){
        var zNodes;
        ajaxPart("post","/business/getbuclassfy",'application/json;charset=UTF-8',"json","", function (response) {
            console.log(response.data)
            zNodes=(response.data);
            $("#treeDiv").show();
            zTreeObj = $.fn.zTree.init($("#morClassify"), setting, zNodes);
    })
    })
//    保存
    $("#saveBtn").click(function () {
        parent.$("#morClassifyInput").val(selectTreeNode);
        parent.layer.close(index);
    })
//    关闭
    $("#cancleBtn").click(function () {
        parent.layer.close(index);
    })
</script>
</body>
</html>
