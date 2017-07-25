<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>教师主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <frameset rows="70,*,30" cols="*" framespacing="0" frameborder="no" border="0">
	  <frame src="<%=path %>/teacherPage/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
	  <frame src="<%=path %>/teacherPage/center.jsp" name="mainFrame" id="mainFrame" />
	  <frame src="<%=path %>/teacherPage/down.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
  </frameset>
</html>
