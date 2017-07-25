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

		<script type='text/javascript' src='<%=path %>/dwr/interface/getAllService.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/engine.js'></script>
		<script type='text/javascript' src='<%=path %>/dwr/util.js'></script>
		
        <script type="text/javascript">
           
           //课表添加，通过链接传递到keibiaoAdd.jsp,再有keibiao.jsp做表单处理
           function kebiaoAdd(){
                 var url="<%=path %>/teacherPage/kebiao/kebiaoAdd_tea.jsp";
				 window.location.href=url;
           }
           
           
           function callback(data){
           	   document.getElementById("indicator").style.display="none";
               DWRUtil.addOptions("banji_id",data,"id","name");
               var fl = document.getElementById("nofl").value;
               if( fl == "no"){
               		var banid = ${requestScope.banji_id};
    				document.getElementById("banji_id").value = banid;
				}
           }
           
         
           function callbackJiaoshi(data){
               document.getElementById("indicator").style.display="none";
               DWRUtil.addOptions("banji_id",data,"id","name");
               var fl = document.getElementById("nofl").value;
               if( fl == "no"){
               		var banid = ${requestScope.banji_id};
    				document.getElementById("banji_id").value = banid;
				}
           }
			
			//查询方式选择判断
			function chaxunfangshi(){
			
				var x=document.getElementById("fangshi").selectedIndex;		//selectedIndex 属性可设置或返回下拉列表中被选选项的索引号。
			    var y=document.getElementById("fangshi").options;			//option 集合可返回包含 <select> 元素中所有 <option> 的一个数组。
				document.getElementById("nofl").value = "yes";				//设置隐藏域中nofl的值
				
				//判断查询方式，再选择具体的查询
				if(y[x].value==1){
					for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--){
						document.getElementById("banji_id").options.remove(i);
					}
	                   document.getElementById("indicator").style.display="block";
	                   getAllService.banjiAll(callback);
				}else if(y[x].value==2){
					for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--){
						document.getElementById("banji_id").options.remove(i);
					}
	                   document.getElementById("indicator").style.display="block";
	                   getAllService.jiaoshiAll(callbackJiaoshi);
				}else{
					for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--){
						document.getElementById("banji_id").options.remove(i);
					}
				}
			}
			
			//导出到excel
			function daochu(){
           		var x=document.getElementById("fangshi").selectedIndex;
			    var y=document.getElementById("fangshi").options;
			    
			    var p=document.getElementById("banji_id").selectedIndex;
			    var k=document.getElementById("banji_id").options;
			    
              	var url="<%=path %>/updown/daochukebiaoExc.jsp?fangshi="+y[x].value+"&banji_id="+k[p].value;
               	window.open(url,"_self");
           	}
       </script>
	</head>

	<body leftmargin="2" topmargin="2">
		<form action="<%=path %>/kebiao_tea?type=kebiaoSearch" name="formd" method="post">
			<table width='98%'  border='0' style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td align="left" width="80">
			          查询方式:
			    </td>
			    <td align="left" width="100">
			        <table border="0">
				           <tr> 
				               <td>
				               	  <input type="hidden" id="nofl" name="nofl" value="no" size="20"/>		
				                  <select name="fangshi" id="fangshi" onchange="chaxunfangshi()">
				                  	  <option value="0" <c:if test="${requestScope.fangshi == 0}">selected</c:if>>请选择</option>
						              <option value="1" <c:if test="${requestScope.fangshi == 1}">selected</c:if>>班级</option>
						              <option value="2" <c:if test="${requestScope.fangshi == 2}">selected</c:if>>实验室</option>
						          </select>
				               </td>
				           </tr>
				    </table>
			    </td>
			    
			    <td align="left" width="80">
			          课表查询:
			    </td>
			    <td align="left" width="100">
			        <table border="0">
				           <tr> 
				               <td>  
				                  <select name="banji_id" id="banji_id">
						              <option value="0">请选择</option>
						          </select>
				               </td>
				               <td>
				                  <img id="indicator" src="<%=path %>/img/loading.gif" style="display:none"/>
				               </td>
				           </tr>
				    </table>
			    </td>			    
			    <td align="left" width="60">
			          <input type="submit" value="查询"/>
			    </td>
			    <td align="left">
			    </td>
			  </tr>
		    </table>
		</form>
			<table id="trainPlan" width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="12%">时间</td>
					<td width="12%">星期几</td>
					<td width="12%">第几节课</td>
					<td width="12%">班级</td>
					<td width="12%">课程</td>
					<td width="12%">教师</td>
					<td width="12%">实验室</td>
		        </tr>	
				<c:forEach items="${requestScope.kebiaoList}" var="kebiao">
						<tr align='center' bgcolor="#FFFFFF" height="22">
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.shijian}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.xingqi}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.dijijieke}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.banji_name}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.kecheng_name}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.tea_name}
							</td>
							<td bgcolor="#FFFFFF" align="center">
								${kebiao.jiaoshi_name}
							</td>
						</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="添加" style="width: 80px;" onclick="kebiaoAdd()" />
			      <input type="button" value="导出" style="width: 80px;" onclick="daochu()" />
			    </td>
			  </tr>
		    </table>
	</body>
	
	<script type='text/javascript'>
		if(${requestScope.fangshi == 1})
		{
			for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--)
			{
				document.getElementById("banji_id").options.remove(i);
			}
            document.getElementById("indicator").style.display="block";
            getAllService.banjiAll(callback);
		}else if(${requestScope.fangshi == 2}){
			for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--)
			{
				document.getElementById("banji_id").options.remove(i);
			}
            document.getElementById("indicator").style.display="block";
            getAllService.jiaoshiAll(callbackJiaoshi);
		}else{
			for(var i=document.getElementById("banji_id").options.length-1;i>=1;i--)
			{
				document.getElementById("banji_id").options.remove(i);
			}
		}
	</script>
</html>
