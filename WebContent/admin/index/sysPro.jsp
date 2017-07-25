<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'sysPro.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <style type="text/css">
		body{
    		font-family: "Microsoft YaHei"
    	}
    	
    	.spanCls{
    		border: solid 1px white;
    		font-size: 30px;
    		margin:5;
    		color: #B7103B;
    	}
	</style>
  
  
  <body>
  	<div align="center" >
  		欢迎你：<sapn class="spanCls">${sessionScope.user.userName }</sapn>登录系统
  	</div>
  </body>
</html>
