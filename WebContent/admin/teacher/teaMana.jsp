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
           function teaDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/teacher?type=teaDel&id="+id;
               }
           }
           
           function teaAdd()
           {
                 var url="<%=path %>/admin/teacher/teaAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2">
			<table width="98%" border="0" cellpadding="5" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="15%">教师号</td>
					<td width="15%">姓名</td>
					<td width="15%">性别</td>
					<td width="15%">年龄</td>
					<td width="15%">密码</td>
					<td width="15%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.teaList}" var="tea">
				<tr align='center' bgcolor="#FFFFFF" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${tea.bianhao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tea.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tea.sex}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tea.age}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tea.password}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<form action="<%=path %>/admin/teacher/teaEditPre.jsp" method="post" name="form1">
						    <input type="button" value="删除" onclick="teaDel(${tea.id})"/>
						    
						    <input type="hidden" name="bianhao" value="${tea.bianhao}"/>
						    <input type="hidden" name="name" value="${tea.name}"/>
						    <input type="hidden" name="sex" value="${tea.sex}"/>
						    <input type="hidden" name=age value="${tea.age}"/>
						    <input type="hidden" name="id" value="${tea.id}"/>
						    <input type="hidden" name="password" value="${tea.password}"/>
						    
						    <input type="submit" value="修改"/>
						</form>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="teaAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
