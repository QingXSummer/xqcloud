<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
  <head>
  <%-- <base href="<%=path%>"> --%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>深度云分析平台</title>

    <!-- Bootstrap -->
    <link href="<%=path%>/page/resource/bootstrap3.3.5/css/bootstrap.min.css" rel="stylesheet">
    
    <link href="<%=path%>/page/view/login/login.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body >

<input type="hidden" id="path" value=<%=path %>></input>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=path%>/page/resource/bootstrap3.3.5/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=path%>/page/resource/bootstrap3.3.5/js/bootstrap.js"></script>
    <script src="<%=path%>/page/resource/bootstrap3.3.5/bootstrapvalidator/js/bootstrapValidator.js"></script>
<!--     <script src="resource/bootstrap3.3.5/js/val.js"></script> -->
    <script src="<%=path%>/page/view/login/login.js"></script>
    

    
    
        <div class="container">
        <h2 class="form-signin-heading" style="color: DodgerBlue;font-weight:bolder;position: absolute; left:30%; top:20%;font-size: 45px">中国移动深度分析云系统</h2>
       <div class="width-1000">
      <form class="form-inline" action="login/login.do" method="post" id="loginForm">

         <div class="form-group" >
        <label for="username" class="sr-only">用户名</label>
        <input type="text" id="username" name="userName" class="form-control" placeholder="用户名" >
         </div>
         
         <div class="form-group">
        <label for="password" class="sr-only">密码</label>
        <input type="password" id="password" name="userPass" class="form-control" placeholder="密码" >
         </div>         

<!--          <div class="form-group">
        <label for="verificationcode" class="sr-only">验证码</label>
        <input type="text" id="verificationcode" name="verificationcode" class="form-control" placeholder="验证码" >
         </div> -->
<!--         <div class="form-group">
        <img src="validate.jpg" class="img-responsive" >
        </div> -->
        <button class="btn btn-primary" type="submit" id="submit">登录</button>
      </form>
       </div>
      

    </div> <!-- /container -->
       
  </body>
</html>