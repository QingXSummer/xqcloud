<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<script src="<%=path%>/page/resource/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/page/resource/bootstrap3.3.5/js/jquery.min.js"></script>
<script src="<%=path%>/page/view/processDefination/processDefination.js"></script>
<link rel="stylesheet" href="<%=path%>/page/resource/layui/css/layui.css"  media="all">
<title>审批流程页面展示</title>
</head>
<style type="text/css">
 .layui-btn{
    background-color: #1e9fff;
} 
.layui-btn-primary {
    background-color: #1e9fff;
    color: #f8f8f8;
}
.layui-btn-danger {
   /*  background-color: #FF5722; */
    background-color: #f51805;
}
.layui-form-checked[lay-skin=primary] i {
    border-color: #1e9fff;
    background-color: #1e9fff;
}
.layui-laypage .layui-laypage-curr .layui-laypage-em {
    background-color: #1e9fff;
}
.layui-btn-mini {
    height: 25px;
    line-height: 25px;
    padding: 0 25px;
    font-size: 13px;
</style>

<body>
<input type="hidden" id="path" value=<%=path %>></input>

 <ins class="adsbygoogle" style="display:inline-block;width:100%;height:20px" data-ad-client="ca-pub-6111334333458862" data-ad-slot="3820120620"></ins>

 <div id="layer-photos-demo" class="layer-photos-demo" ></div>
 <div class="layui-btn-group demoTable">
	<button class="layui-btn" data-type="add">新增</button>
	<button class="layui-btn" data-type="refresh">刷新</button>
	<button class="layui-btn" onclick="role()">授权</button>
 </div> 

 
<table id="demo" lay-filter="demo"></table>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="publish">部署</a>
</script>

<script>
var path=$('#path').val();
function role(){
	layer.open({
		  type: 2,
		  content: '../tree/tree.jsp',
		  area: ['40%', '80%'],
		  maxmin: true
		});
}
</script>

</body>
</html>