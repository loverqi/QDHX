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
<div >
        <div id="treeDiv" style="position: absolute;top: 10px;left: 30px;overflow-y: auto">
            <ul id="postClassify" class="ztree" style="height:400px;"></ul>
        </div>
        <div style="position: fixed;bottom: 20px;left: 120px;">
            <button type="button" class="btn btn-primary" id="saveBtn" >保存</button>
            <button type="button" class="btn btn-default" id="cancleBtn">关闭</button>
        </div>
</div>
<script>
    var index = parent.layer.getFrameIndex(window.name);
    //ztree
    var zTreeObj;
    var treeNodeId;
    var selectTreeNode;
    var selectTreeNodeId;
    function zTreeOnClick(event, treeId, treeNode) {
        if(treeNode.children == null){
//            $("#postClassifyInput").val(treeNode.name);
            selectTreeNode=treeNode.name;
            selectTreeNodeId=treeNode.id;
        }
    };

    var setting = {
        callback: {
            onClick: zTreeOnClick
        }
    };
    $(document).ready(function(){
        var zNodes;
        ajaxPart("post","/level/getPostLevelNotUser.do",'application/json;charset=UTF-8',"json","", function (response) {
            zNodes=(response.data);
            $("#treeDiv").show();
            zTreeObj = $.fn.zTree.init($("#postClassify"), setting, zNodes);
        })
    })
    //    保存
    $("#saveBtn").click(function () {
        parent.$("#addPostId").val(selectTreeNodeId);
        parent.$("#addPost").val(selectTreeNode);
        parent.layer.close(index);
    })
    //    关闭
    $("#cancleBtn").click(function () {
        parent.layer.close(index);
    })
</script>
</body>
</html>
