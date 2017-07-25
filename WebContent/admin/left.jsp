<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
		.STYLE1 {
			color: #FFFFFF;
			font-weight: bold;
			font-size: 12px;
		}
		.STYLE2 {
			font-size: 12px;
			color: #03515d;
		}
		a:link {font-size:12px; text-decoration:none; color:#03515d;}
		a:visited{font-size:12px; text-decoration:none; color:#03515d;}
	</style>
  </head>
  
  <body>
		<table width="156" height="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" valign="top">
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
						<tr align="center">
							<td height="33" bgcolor="FF33FF">
								功能菜单
							</td>
						</tr>
						<tr>
							<td align="center" valign="top">
								<table width="145" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td>
											<table width="130" border="0" align="center" cellpadding="0" cellspacing="0">
												<tr height="10">
												   <td></td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr	align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	
																	<span class="STYLE2">
																	    <a href='<%=path %>/kebiao?type=kebiaoMana' target='I2'>课表信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<span class="STYLE2">
																    	<a href='<%=path %>/zhuanye?type=zhuanyeMana' target='I2'>专业信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<div align="center">
																		<span class="STYLE2">
																	   		<a href='<%=path %>/banji?type=banjiMana' target='I2'>班级信息管理</a>
																		</span>
																	</div>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<span class="STYLE2">
																	    <a href='<%=path %>/kecheng?type=kechengMana' target='I2'>课程信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<span class="STYLE2">
																		    <a href='<%=path %>/teacher?type=teaMana' target='I2'>教师信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<span class="STYLE2">
																		    <a href='<%=path %>/jiaoshi?type=jiaoshiMana' target='I2'>实验室信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td width="89" height="35">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr align="center">
																<td height="23" style="cursor:hand"
																	onMouseOver="this.style.borderStyle='solid';this.style.borderWidth='1';borderColor='#adb9c2'; "
																	onmouseout="this.style.backgroundImage='url()';this.style.borderStyle='none'">
																	<span class="STYLE2">
																	    	<a href='<%=path %>/admin?type=adminMana' target='I2'>管理员信息管理</a>
																	</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
