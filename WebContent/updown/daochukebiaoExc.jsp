<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="org.apache.poi.hssf.usermodel.*" %>
<%@ page import="java.io.*" %>

<jsp:directive.page import="com.dao.DB"/>
<jsp:directive.page import="java.sql.ResultSet"/>
<jsp:directive.page import="com.service.getNameService"/>
<jsp:directive.page import="com.entity.Kebiao"/>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body> 
      <%
       	List kebiaoList=new ArrayList();
       	String fangshi = request.getParameter("fangshi");
       	String banji_id = request.getParameter("banji_id");
       	String sql="";
       	
       	if("1".equals(fangshi))
       	{
       		sql="select * from kebiao where del='no' and banji_id="+Integer.parseInt(request.getParameter("banji_id"));
       	}else if("2".equals(fangshi)){
       		sql="select * from kebiao where del='no' and tea_id="+Integer.parseInt(request.getParameter("banji_id"));
       	}else if("3".equals(fangshi)){
       		sql="select * from kebiao where del='no' and jiaoshi_id="+Integer.parseInt(request.getParameter("banji_id"));
       	}else{
       		sql="select * from kebiao where del='no'";
       	}
       	Object[] params={};
       	DB mydb=new DB();
       	try
       	{
       		mydb.doPstm(sql, params);
       		ResultSet rs=mydb.getRs();
       		while(rs.next())
       		{
       			Kebiao kebiao=new Kebiao();
       			kebiao.setId(rs.getInt("id"));
       			kebiao.setShijian(rs.getString("shijian"));
       			kebiao.setXingqi(rs.getString("xingqi"));
       			kebiao.setDijijieke(rs.getString("dijijieke"));
       			
       			kebiao.setBanji_id(rs.getInt("banji_id"));
       			kebiao.setKecheng_id(rs.getInt("kecheng_id"));
       			kebiao.setTea_id(rs.getInt("tea_id"));
       			kebiao.setJiaoshi_id(rs.getInt("jiaoshi_id"));
       			
       			kebiao.setBanji_name(getNameService.getBanjiName(rs.getInt("banji_id")));
       			kebiao.setKecheng_name(getNameService.getKechengName(rs.getInt("kecheng_id")));
       			kebiao.setTea_name(getNameService.getTeaName(rs.getInt("tea_id")));
       			kebiao.setJiaoshi_name(getNameService.getJiaoshiName(rs.getInt("jiaoshi_id")));
       			kebiaoList.add(kebiao);		   
       	  }
       		rs.close();
       	}
       	catch(Exception e)
       	{
       		e.printStackTrace();
       	}
       	mydb.closed();      
             
        HSSFWorkbook wb = new HSSFWorkbook();
       	HSSFSheet sheet = wb.createSheet("new sheet");
       	
       	HSSFRow row = sheet.createRow(0);
       	HSSFCell cell=row.createCell((short)0);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("时间");
       			
       	cell=row.createCell((short)1);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("星期几");
       			
       	cell=row.createCell((short)2);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("第几节课");
       	
       	cell=row.createCell((short)3);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("班级");
       	
       	cell=row.createCell((short)4);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("课程");
       	
       	cell=row.createCell((short)5);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("教师");
       	
       	cell=row.createCell((short)6);
       	cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       	cell.setCellValue("实验室");
       	
       	for(int i=0;i<kebiaoList.size();i++)
       	{
       		Kebiao tkebiao=(Kebiao)kebiaoList.get(i);
       		
       		row=sheet.createRow(i+1);
       		cell=row.createCell((short)0);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getShijian());
       				
       		cell=row.createCell((short)1);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getXingqi());
       		
       		cell=row.createCell((short)2);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getDijijieke());
       		
       		cell=row.createCell((short)3);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getBanji_name());
       		
       		cell=row.createCell((short)4);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getKecheng_name());
       		
       		cell=row.createCell((short)5);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getTea_name());
       		
       		cell=row.createCell((short)6);
       		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
       		cell.setCellValue(tkebiao.getJiaoshi_name());
       	}
       	
       	
       	String fujianPath="G:\\课表信息.xls";
       	try
       	{
       		FileOutputStream fileOut = new FileOutputStream(fujianPath);
       		wb.write(fileOut);
       		fileOut.close();
       	} 
       	catch (Exception e)
       	{
       		e.printStackTrace();
       	}
       	
		             
           
        SmartUpload su = new SmartUpload(); // 新建一个SmartUpload对象 

        su.initialize(pageContext); // 初始化 

        su.setContentDisposition(null); 
        // 设定contentDisposition为null以禁止浏览器自动打开文件， 
        //保证点击链接后是下载文件。若不设定，则下载的文件扩展名为 
        //doc时，浏览器将自动用word打开它。扩展名为pdf时，将用acrobat打开

        //su.downloadFile("/uploadPath/file/liu.doc"); // 下载英文文件
        su.downloadFile(fujianPath, null, new String(java.net.URLDecoder.decode("课表信息.xls","UTF-8").getBytes(), "ISO8859-1")); // 下载中文文件
        //downloadFile(String sourceFilePathName, String contentType, String destFileName)
        out.clear();
        out=pageContext.pushBody();
      %> 
  </body>
</html>
