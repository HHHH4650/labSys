<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>登录</title>
    <style type="text/css">
    	body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
			background-color: #1D3647;
		}
		.login_top_bg {
			background-color: 336666;
		}
		.body {
			background-color: #EEF2FB;
			left: 0px;
			top: 0px;
			right: 0px;
			bottom: 0px;
		}
		
		.login-buttom-bg {
			background-color: CC99FF;
		}
		.login_txt {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 25px;
			color: #333333;
		}
		.Submit {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			color: #629DAE;
			text-decoration: none;
			background-image: url(<%=path %>/img/Submit_bg.gif);
			background-repeat: repeat-x;
		}
		.login_bg {
			background-image: url(<%=path %>/img/login_bg.jpg);
			background-repeat: repeat-x;
		}
		.login_bg2 {
			background-image: url(<%=path %>/img/login-content-bg.gif);
			background-repeat: no-repeat;
			background-position: right;
		}
		
		.admin_txt {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			color: #FFFFFF;
			text-decoration: none;
			height: 38px;
			width: 100%;
			position: 固定;
			line-height: 38px;
		}
    </style>
	<script type="text/javascript" src="jquery/jquery-1.11.3.js"></script>
    <script type="text/javascript">
       	$(document).ready(function(){
       		$("#Submit").click(function(){
       			 //验证
       			 if(document.ThisForm.userName.value=="") {
				 	alert("请输入用户名");
					document.ThisForm.userName.focus();
					return false;
				 }
				 if(document.ThisForm.userPw.value=="") {
				 	alert("请输入密码");
					document.ThisForm.userPw.focus();
					return false;
				 } 
			 	document.getElementById("indicator").style.display="block";
       			
       			//设置参数
       			var userName = document.ThisForm.userName.value;
			 	var userPw = document.ThisForm.userPw.value;
			 	var obj = document.getElementsByName("loginType");
			  	for(var i=0; i<obj.length; i ++){
					if(obj[i].checked){
		           		userType =  obj[i].value;
		        	}
		     	}
		     	//利用ajax
		     	$.ajax({
		     		url:"${pageContext.request.contextPath}/login",
		     		data:{"userName":userName,"userPw":userPw,"userType":userType},
		     		type:"post",
		     		async: false,
		     		success:function(data){
	     				document.getElementById("indicator").style.display="none";
	     				if(data != 1){
		     				alert("用户名或密码错误");
		     			}else{
		     				alert("通过验证,系统登录成功");
		        			window.location.href="<%=path %>/loginSuccess.jsp";
		     			}
	     			}
		     	});
       		});
       	});
    </script>
</head>
<body>
	<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td height="42" valign="top">
	       <table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
		      <tr>
		        <td width="1%" height="21">&nbsp;</td>
		        <td height="42">&nbsp;</td>
		        <td width="17%">&nbsp;</td>
		      </tr>
	       </table>
	    </td>
	  </tr>
	  <tr>
	    <td valign="top">
	     <table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
	      <tr>
	        <td width="49%" align="right">
	          <table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
	            <tr>
	             <td height="138" valign="top">
	              <table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
		                <tr>
		                  <td height="149">&nbsp;</td>
		                </tr>
		                <tr>
		                  <td height="80" align="right" valign="top"></td>
		                </tr>
					<tr>
						<td height="198" align="right" valign="top">
		                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			                    <tr>
			                      <td width="35%">&nbsp;</td>
			                      <td height="25" colspan="2" ></td>
			                    </tr>
			                    <tr>
			                      <td>&nbsp;</td>
			                      <td height="25" colspan="2"></td>
			                    </tr>
			                    <tr>
			                      <td>&nbsp;</td>
			                      <td height="25" colspan="2" ></td>
			                    </tr>
			                    <tr>
			                      <td>&nbsp;</td>
			                      <td width="30%" height="40"></td>
			                      <td width="35%"></td>
			                	</tr>
		                  	</table>
						</td>
		            </tr>
	              </table>
	             </td>
	           </tr>
	          </table>
	        </td>
	        <td width="1%" >&nbsp;</td>
	        <td width="50%" valign="bottom">
	        <table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
	            <tr>
	              <td width="4%">&nbsp;</td>
	              <td width="96%" height="38"><span class="login_txt_bt">实验室排课系统</span></td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td height="21"><table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
	                  <tr>
	                    <td height="164" colspan="2" align="middle">
	                        <form action="${pageContext.request.contextPath}/login" name="ThisForm" method=post>
	                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
	                          <tr>
	                            <td width="13%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp; </span></td>
	                            <td height="38" colspan="2" class="top_hui_text"><input name="userName" type="text" style="width: 140px;"></td>
	                          </tr>
	                          <tr>
	                            <td width="13%" height="35" class="top_hui_text"><span class="login_txt">密&nbsp;&nbsp;&nbsp;&nbsp;码： &nbsp; </span></td>
	                            <td height="35" colspan="2" class="top_hui_text"><input type="password" style="width: 140px;" name="userPw">
	                            </td>
	                          </tr>
	                          <tr>
	                          	<td height="35" >&nbsp;</td>
	                            <td width="90%" height="35" >
	                            	<input id="login_id" type="radio" name="loginType" value="0"><span class="login_txt">管理员&nbsp;&nbsp;</span>
    								<input id="login_id" type="radio" name="loginType" value="1"><span class="login_txt">教师 </span>
	                            </td>
	                          </tr>
	                          <tr>
	                            <td height="35" >&nbsp;</td>
	                            <td width="90%" height="35" >
	                                <input name="button" type="button" class="button" id="Submit" value="登录">
	                                &nbsp;&nbsp;
	                                <input name="cs" type="reset" class="button" id="cs" value="重 置">
	                                <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
	                            </td>
	                            <td width="10%" class="top_hui_text"></td>
	                          </tr>
	                        </table>
	                        <br>
	                    </form></td>
	                  </tr>
	                  <tr>
	                    <td width="433" height="164" align="right" valign="bottom"></td>
	                    <td width="57" align="right" valign="bottom">&nbsp;</td>
	                  </tr>
	              </table></td>
	            </tr>
	          </table>
	          </td>
	      </tr>
	    </table>
	   </td>
	  </tr>
	 <tr>
	    <td height="42" valign="bottom">
	       <table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login-buttom-bg">
		      <tr>
		      </tr>
	       </table>
	    </td>
	  </tr>
	</table>
</body>
</html>