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
        
        <script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
        <script type='text/javascript' src='<%=path %>/dwr/interface/getAllService.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
        <script type="text/javascript">
           var i=0;
           function banjiAll(){
               if(i==0)
               {
                   document.getElementById("indicator").style.display="block";
                   getAllService.banjiAll(callback);
                   i=1;
               }
               
           }
           function callback(data){
               document.getElementById("indicator").style.display="none";
               DWRUtil.addOptions("banji_id",data,"id","name");
           }
           
           //获取所有课程
           var j=0;
           function kechengAll(){
               if(j==0)
               {
                   document.getElementById("indicator1").style.display="block";
                   getAllService.kechengAll(callback1);
                   j=1;
               }
           }
           function callback1(data){
               document.getElementById("indicator1").style.display="none";
               DWRUtil.addOptions("kecheng_id",data,"id","name");
           }
           
           //获取所有实验室
           var n=0;
           function jiaoshiAll(){
               if(n==0){
                   document.getElementById("indicator3").style.display="block";
                   getAllService.jiaoshiAll(callback3);
                   n=1;
               }
               
           }
           function callback3(data){
               document.getElementById("indicator3").style.display="none";
               DWRUtil.addOptions("jiaoshi_id",data,"id","name");
           }
           
           
           //检查表单是否填写完整
           function check(){
               if(document.formAdd.shijian.value==""){ 
                   alert("请选择上课时间");
                   return false;
               }
               if(document.formAdd.banji_id.value==0){ 
                   alert("请选择班级");
                   return false;
               }
               if(document.formAdd.kecheng_id.value==0){ 
                   alert("请选择课程");
                   return false;
               }
               if(document.formAdd.jiaoshi_id.value==0){ 
                   alert("请选择实验室");
                   return false;
               }
               return true;
           }
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/img/allbg.gif'>
		<form action="<%=path %>/kebiao_tea?type=kebiaoAdd" name="formAdd" method="post">
		     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         时间：
				    </td>
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
				         班级：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <table border="0">
					           <tr> 
					               <td>  
					                  <select name="banji_id" id="banji_id" onclick="banjiAll()">
							              <option value="0">请选择班级</option>
							          </select>
					               </td>
					               <td>
					                  <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
					               </td>
					           </tr>
					    </table>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         课程：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <table border="0">
					           <tr> 
					               <td>  
					                  <select name="kecheng_id" id="kecheng_id" onclick="kechengAll()">
							              <option value="0">请选择课程</option>
							          </select>
					               </td>
					               <td>
					                  <img id="indicator1" src="<%=path %>/img/loading.gif" style="display:none"/>
					               </td>
					           </tr>
					    </table>
				    </td>
				</tr>
				<tr align='center' bgcolor="#FFFFFF" height="22">
				    <td width="25%" bgcolor="#FFFFFF" align="right">
				         实验室：
				    </td>
				    <td width="75%" bgcolor="#FFFFFF" align="left">
				        <table border="0">
					           <tr> 
					               <td>  
					                  <select name="jiaoshi_id" id="jiaoshi_id" onclick="jiaoshiAll()">
							              <option value="0">请选择实验室</option>
							          </select>
					               </td>
					               <td>
					                  <img id="indicator3" src="<%=path %>/img/loading.gif" style="display:none"/>
					               </td>
					           </tr>
					    </table>
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
