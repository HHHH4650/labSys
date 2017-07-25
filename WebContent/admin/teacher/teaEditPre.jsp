<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %> 

<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
                
        <script type="text/javascript">
           function check()
           {
              if(document.formAdd.age.value=="")
              {
                 alert("请输入年龄");return false;
              }
              return true;
           }
        </script>
	</head>

	<body leftmargin="2" topmargin="9">
		<form action="<%=path %>/teacher?type=teaEdit" name="formAdd" method="post">
		     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         教师号：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input type="text" name="bianhao" size="20" value="<%=request.getParameter("bianhao") %>"/>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         姓名：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input type="text" name="name" size="20" value="<%=request.getParameter("name") %>"/>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         性别：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				         <input type="radio" name="sex" value="男" checked="checked"/>男
				         &nbsp;&nbsp;
				         <input type="radio" name="sex" value="女"/>女
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         年龄：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input type="text" value="<%=request.getParameter("age") %>" name="age" size="20" onpropertychange="onchange1(this.value)" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				        登陆密码：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input type="text" name="password" size="20" value="<%=request.getParameter("password") %>"/>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				        &nbsp;
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
                             <input type="hidden" name="id"  value="<%=request.getParameter("id") %>"/>
				       <input type="submit" value="提交" onclick="return check()"/>&nbsp; 
				       <input type="reset" value="重置"/>&nbsp;
				    </td>
				</tr>
			 </table>
		</form>
   </body>
</html>
