<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String target = request.getParameter("target");
	String insCode =  request.getParameter("insCode");
	target += "&type=1";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
  <%-- <base href="<%=path%>"> --%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <title>深度云分析平台</title>

    <link href="<%=path%>/page/resource/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
   <%--  <link href="<%=path%>/page/resource/frame.css" rel="stylesheet"> --%>
 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <script src="<%=path%>/page/resource/bootstrap3.3.5/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path%>/page/resource/bootstrap3.3.5/js/bootstrap.js"></script>
    <script src="<%=path%>/page/resource/layui/layui.js" charset="utf-8"></script>
	<link rel="stylesheet" href="<%=path%>/page/resource/layui/css/layui.css"  media="all">


  </head>
  <body >

<div style="visibility: hidden;">
<input id="path"  value="<%=path %>">
<input id="target" value="<%=target %>">
<input id="insCode" value="<%=insCode %>">
<input id="target" value="<%=target %>">
</div>

<div class="container" style="width: 100%; margin-top: 10px">

  
		<div class="row">
			<div class="col-md-2" style="padding-left: 65px;">
				<span>申请人： </span> <span id="username"></span>
			</div>
			<div class="col-md-3">
				<span>所属租户： </span><span id="tenantName"></span>
			</div>
			<div class="col-md-3">
				<span>申请时间： </span><span id="createTime"></span>
			</div>
			<div class="col-md-2">
				<span>订单状态： </span><span id="comment"></span> <span id="state"></span>
			</div>
		</div>
  

  <div>
<div id="iframe">


</div>
</div>
		<div class="row col-md-12">
			<table class="table table-bordered" id="tableHistory">
			<thead>
			<tr>
			<th style="text-align: center; ">提交人员</th>
			<th style="text-align: center; ">审批时间</th>
			<th style="text-align: center; ">提交人员</th>
			<th style="text-align: center; ">处理意见</th>
			</tr>
			</thead>
			<tbody id="tbody">
			</tbody>
			</table>
		</div>
</div>


<script type="text/javascript">  
<%-- $(function(){ 
	  $('#content').attr('src','<%=target%>');

})   --%>
</script>  
<script language="javascript">
//输入你希望根据页面高度自动调整高度的iframe的名称的列表
//用逗号把每个iframe的ID分隔. 例如: ["myframe1", "myframe2"]，可以只有一个窗体，则不用逗号。
//定义iframe的ID
/* var iframeids=["header","content","footer"];
//如果用户的浏览器不支持iframe是否将iframe隐藏 yes 表示隐藏，no表示不隐藏
var iframehide="yes";

function dyniframesize() {
		var dyniframe = new Array()
		for (i = 0; i < iframeids.length; i++) {
			if (document.getElementById) {
				//自动调整iframe高度
				dyniframe[dyniframe.length] = document.getElementById(iframeids[i]);
				if (dyniframe[i] && !window.opera) {
					dyniframe[i].style.display = "block";
					if (dyniframe[i].contentDocument && dyniframe[i].contentDocument.body.offsetHeight) //如果用户的浏览器是NetScape
						{dyniframe[i].height = dyniframe[i].contentDocument.body.offsetHeight;
					     console.log(dyniframe[i].height);}
					else if (dyniframe[i].Document && dyniframe[i].Document.body.scrollHeight) //如果用户的浏览器是IE
						dyniframe[i].height = dyniframe[i].Document.body.scrollHeight;
				}
			}
			//根据设定的参数来处理不支持iframe的浏览器的显示问题
			if ((document.all || document.getElementById) && iframehide == "no") {
				var tempobj = document.all ? document.all[iframeids[i]] : document.getElementById(iframeids[i]);
				tempobj.style.display = "block";
			}
		}
}
	if (window.addEventListener)
		window.addEventListener("load", dyniframesize, false);
	else if (window.attachEvent)
		window.attachEvent("onload", dyniframesize);
	else
		window.onload = dyniframesize; */
</script> 
    <script src="<%=path%>/page/view/taskInfo/commonTranfer.js"></script>
    <script src="<%=path%>/page/view/taskInfo/index.js"></script>

  </body>
</html>