<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
  <%-- <base href="<%=path%>"> --%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <title>深度云分析平台</title>

    <link href="<%=path%>/page/resource/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/page/resource/frame.css" rel="stylesheet">


  </head>
  <body >
  

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
   <script src="<%=path%>/page/resource/bootstrap3.3.5/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path%>/page/resource/bootstrap3.3.5/js/bootstrap.js"></script>

    
    
<div class=box>

    
    


<div style="background-color: rgb(239, 239, 239)">
      <ul class="nav nav-pills">
            <li role="presentation" ><a href="<%=request.getContextPath() %>/page/view/processDefination/processDefination.jsp" target="page">流程定义</a></li>              
            <li role="presentation" ><a href="<%=request.getContextPath() %>/page/view/processList/processList.jsp" target="page">申请单</a></li>              
            <li role="presentation" ><a href="<%=request.getContextPath() %>/page/view/processList/ProcessApprove.jsp" target="page">审批单</a></li>              
      </ul>
</div>


<iframe name="page"  src="<%=path%>/page/view/processDefination/processDefination.jsp"   frameborder="0"  width="100%" height="92%"></iframe>



    
</div>

    
    
  </body>
</html>