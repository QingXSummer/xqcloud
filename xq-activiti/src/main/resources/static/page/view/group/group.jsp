<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>机构树菜单树</title>
<link rel="stylesheet" href="<%=path%>/page/resource/tree/tree_files/font-awesome.min.css">
<!-- ace styles -->
<link rel="stylesheet" href="<%=path%>/page/resource/tree/tree_files/ace.min.css">
<link rel="stylesheet" href="<%=path%>/page/resource/layui/css/layui.css"  media="all">
</head>
<body>
<input type="hidden" id="path" value=<%=path %>></input>
<br/>
<br/>
<button class="layui-btn" onclick="save()" style="float:right">保存当前用户信息</button>
<br/>
<br/>
<br/>
<div class="row">
	<div style="width:50%;height:100%;float:left">
		<div class="widget-box">
			<div class="widget-header header-color-blue2">
				<h4 class="lighter smaller">用户</h4>
			</div>
				<div class="widget-body">
					<div class="widget-main padding-8">
						<div id="tree1" class="tree tree-selectable">
						
						</div>
					</div>
				</div>
			</div>
	</div>
 	<div style="width:50%;height:100%;float:right" id ="tree">

	</div> 
</div>
<script src="<%=path%>/page/resource/layui/layui.js" charset="utf-8"></script>
<!--[if !IE]> -->
<script src="<%=path%>/page/resource/tree/tree_files/jquery-2.0.3.min.js"></script>
<!-- <![endif]-->
<script src="<%=path%>/page/resource/tree/tree_files/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->
<script src="<%=path%>/page/resource/tree/tree_files/fuelux.tree.min.js"></script>
<!-- ace scripts -->
<script src="<%=path%>/page/resource/tree/tree_files/ace-elements.min.js"></script>
<script src="<%=path%>/page/resource/tree/tree_files/ace.min.js"></script>
<!-- inline scripts related to this page -->
<script src="<%=path%>/page/view/group/group.js"></script>
</body>
</html>
