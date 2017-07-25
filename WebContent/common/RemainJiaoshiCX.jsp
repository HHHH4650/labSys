<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'RemainJiaoshiCX.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
	<script type='text/javascript' src='<%=path %>/dwr/interface/getAllService.js'></script>
	<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
	<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
	<script type="text/javascript">
        //检查表单是否填写完整
        function check(){
            if(document.formJiaoshi.shijian.value==""){ 
                alert("请选择上课时间");
                return false;
            }
	</script>
  </head>
  
  <body leftmargin="2" topmargin="9">
	<form action="<%=path %>/remainJiaoshi" name="formJiaoshi" method="post">
		<table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
			<tr align='center' bgcolor="#FFFFFF" height="22">
				<td width="25%" bgcolor="#FFFFFF" align="right">
				         时间：
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <input name="shijian" readonly="readonly" class="Wdate"  type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
				    </td>
			</tr>
			<tr align='center' bgcolor="#FFFFFF" height="22">
			    <td width="25%" bgcolor="#FFFFFF" align="right">
			         第几节课：
			    </td>
			    <td width="75%" bgcolor="#FFFFFF" align="left">
			        <select name="dijijieke">
			            <option value="第一节课">第一节课</option>
			            <option value="第二节课">第二节课</option>
			            <option value="第三节课">第三节课</option>
			            <option value="第四节课">第四节课</option>
			        </select>
				</td>
			</tr>
			<tr align='center' bgcolor="#FFFFFF" height="22">
			    <td width="25%" bgcolor="#FFFFFF" align="right">
			        &nbsp;
			    </td>
			    <td width="75%" bgcolor="#FFFFFF" align="left">
			       <input type="submit" value="提交" onclick="return check()"/>&nbsp; 
			       <input type="reset" value="重置"/>&nbsp;
			    </td>
			</tr>
		</table>
	</form>
  </body>
</html>
