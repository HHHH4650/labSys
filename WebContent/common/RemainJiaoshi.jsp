<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'RemainJiaoshi.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	


  </head>
  
  <body leftmargin="2" topmargin="2">
		<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
			<tr align="center" bgcolor="#FAFAF1" height="22">
				<td width="33%">实验室名称</td>
	        </tr>	
			<c:forEach items="${requestScope.jiaoshiList}" var="jiaoshi">
			<tr align='center' bgcolor="#FFFFFF" height="22">
				<td bgcolor="#FFFFFF" align="center">
					${jiaoshi.name}
				</td>
			</tr>
			</c:forEach>
		</table>
  </body>
</html>
