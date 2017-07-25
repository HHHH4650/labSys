<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'middel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
	</style>
  </head>
  
  <body style="overflow:hidden">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="156" id=frmTitle noWrap name="fmTitle" align="center" valign="top">
					<iframe name="I1" height="100%" width="156" src="<%=path %>/admin/left.jsp" border="0" frameborder="0" scrolling="no">
					</iframe>
				</td>
				<td width="4" valign="middle" background="img/middel_1.gif"></td>
				<td align="center" valign="top">
					<iframe name="I2" height="100%" width="100%" border="0" frameborder="0" src="<%=path %>/admin/index/sysPro.jsp">
					</iframe>
				</td>
			</tr>
		</table>
	</body>
</html>
