<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
 <HEAD>
  <TITLE> ZTREE DEMO </TITLE>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="<%=path%>/page/resource/zTree/css/demo.css" type="text/css">
  <link rel="stylesheet" href="<%=path%>/page/resource/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="<%=path%>/page/resource/zTree/js/jquery-1.4.4.min.js"></script>
  <script type="text/javascript" src="<%=path%>/page/resource/zTree/js/jquery.ztree.all.js"></script> 
  <script type="text/javascript" src="<%=path%>/page/view/tree/tree.js"></script> 
 </HEAD>
<BODY>
<input type="hidden" id="path" value=<%=path %>></input>
<div style="margin: 1%">
<div style="width:50%;float:left">
   <ul class="ztree" id="usertree"></ul>
</div>
<div style="width:50%;float:right">
   <ul class="ztree" id="grouptree"></ul>
</div>
</div>
</BODY>
</HTML>