<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=path%>"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源申请</title>
<%-- <script type="text/javascript" src="<%=path%>/page/common/base.js"></script> --%>
<!-- 新 Bootstrap 核心 CSS 文件 -->

<link rel="stylesheet" href="<%=path%>/page/resource/bootstrap3.3.5/css/bootstrap.min.css">
<link href="<%=path%>/page/resource/bootstrap3.3.5/table/bootstrap-table.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/page/resource/bootstrap3.3.5/datetimepicker/jquery.datetimepicker.css">
<link href="<%=path%>/page/resource/bootstrap3.3.5/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/page/view/processList/processList.css" rel="stylesheet">


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=path%>/page/resource/bootstrap3.3.5/js/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
      
<script src="<%=path%>/page/resource/bootstrap3.3.5/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=path%>/page/resource/bootstrap3.3.5/table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=path%>/page/resource/bootstrap3.3.5/table/bootstrap-table-zh-CN.js"></script>
<script src="<%=path%>/page/resource/bootstrap3.3.5/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path%>/page/resource/bootstrap3.3.5/datetimepicker/bootstrap-datetimepicker.zh-CN.js"></script>

<link rel="stylesheet" href="<%=path%>/page/resource/layui/css/layui.css"
	media="all">
<script type="text/javascript" src="<%=path%>/page/resource/layui/layui.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
a{
    color: #337ab7;
}
</style>

</head>
<body  >
<input type="hidden" id="path" value=<%=path %>></input>
   
<div class="container" style="width: 100%;margin-top: 10px">
   
		<div class="row" id="searchRow">
			<div class="col-xs-6 col-sm-3 ">
				<div class="input-group">
					<span class="input-group-addon" >申请单类型</span> 
					<select class="form-control"  id="processType">
						<option value="">请选择</option>
						<option value="1">数据定制</option>
					</select>					
<!-- 					<input id="processName" type="text" class="form-control" placeholder="流程名称" value=""> -->
				</div>
			</div>
<!-- 			<div class="col-xs-6 col-sm-2 ">
				<div class="input-group">
					<span class="input-group-addon" >创建人</span> <input id="searchTwo"
						type="text" class="form-control" placeholder="创建人">
				
				</div>
			</div> -->
			<div class="col-xs-6 col-sm-3">
				<div class="input-group">
				<span class="input-group-addon" >起始日期</span>
					<input id="startTime"  type="text" class="form-control" placeholder="起始日期">
					
				</div>
			</div>
			<div class="col-xs-6 col-sm-3 ">
				<div class="input-group">
				<span class="input-group-addon" >结束日期</span>
					<input id="endTime"  type="text" class="form-control" placeholder="结束日期">
				</div>
				</div>
<!-- 				<div class="col-xs-6 col-sm-2 ">
					<select class="form-control" id="processType">
						<option value="0">申请单</option>
						<option value="1">待审批</option>
						<option value="2">已完成</option>
					</select>

				<button class="btn btn-primary" style="color:#ffffff;background:#199ed8;font-size:12px;border:1px solid #199ed8" type="button" id="searchButton">Go!</button>
				</div> -->
 				<div class="col-xs-6 col-sm-2 ">

				<button class="btn btn-primary" style="color:#ffffff;background:#199ed8;font-size:12px;border:1px solid #199ed8" type="button" id="searchButton">查询</button>
				</div>

		</div>
<div class="row col-md-12">
		<table class="table table-hover" id="table"> </table>
</div>
         
		

 

  
  
  

 
 
</div>

</body>

<script type="text/javascript" src="<%=path%>/page/view/processList/processList.js"></script>

</html>


