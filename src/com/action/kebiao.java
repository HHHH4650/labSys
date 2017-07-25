package com.action;
/**
 * 课表信息管理
 */
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.entity.Kebiao;
import com.service.getNameService;
import com.util.Util;

public class kebiao extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type=request.getParameter("type");
			
		if("kebiaoMana".equals(type)){
			kebiaoMana(request, response);
		}
		
		if("kebiaoAdd".equals(type)){
			kebiaoAdd(request, response);
		}
		
		if("kebiaoDel".equals(type)){
			kebiaoDel(request, response);
		}
		
		if("kebiaoSearch".equals(type)){
			kebiaoSearch(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 课表添加
	 */
	public void kebiaoAdd(HttpServletRequest request,HttpServletResponse response) {
		//获取日期
		String shijian=request.getParameter("shijian");		
		//获取星期几
		String xingqi="";
		try {
			xingqi = Util.getWeekStr(request.getParameter("shijian"));	
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//第几节课、班级id、课程id、教室id、教师id
		String dijijieke=request.getParameter("dijijieke");				
		int banji_id=Integer.parseInt(request.getParameter("banji_id"));
		int kecheng_id=Integer.parseInt(request.getParameter("kecheng_id"));
		int tea_id=Integer.parseInt(request.getParameter("tea_id"));
		int jiaoshi_id=Integer.parseInt(request.getParameter("jiaoshi_id"));
		String del="no";
		
		/**
		 * 1) 先检测冲突
		 * 插入课表
		 */
		/**
		 * 1.1 检测实验室是否冲突
		 */
		boolean b1=false;
		String sqlQ1="select * from kebiao where shijian=? and dijijieke=?and jiaoshi_id=? and del=?";
		Object[] paramsQ1={shijian,dijijieke,jiaoshi_id,"no"};
		DB mydbQ1=new DB();
		try {	
			mydbQ1.doPstm(sqlQ1, paramsQ1);
			ResultSet rsQ1=mydbQ1.getRs();
			if(rsQ1.next()==true) {
				b1=true;
			}
			rsQ1.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydbQ1.closed();
		System.out.println("b1:"+b1);
		/**
		 * 1.2 检测班级是否在这个时间已经有其他课程
		 */
		boolean b2=false;
		String sqlQ2="select * from kebiao where shijian=? and dijijieke=? and banji_id=? and del=?";
		Object[] paramsQ2={shijian,dijijieke,banji_id,"no"};
		DB mydbQ2=new DB();
		try {	
			mydbQ2.doPstm(sqlQ2, paramsQ2);
			ResultSet rsQ2=mydbQ2.getRs();
			if(rsQ2.next()==true) {
				b2=true;
			}
			rsQ2.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydbQ2.closed();
		System.out.println("b2:"+b2);
		
		/**
		 * 1.3 检测教师是否冲突
		 */
		boolean b3=false;
		String sqlQ3="select * from kebiao where shijian=? and dijijieke=? and tea_id=? and del=?";
		Object[] paramsQ3={shijian,dijijieke,tea_id,"no"};
		DB mydbQ3=new DB();
		try {	
			mydbQ3.doPstm(sqlQ3, paramsQ3);
			ResultSet rsQ3=mydbQ3.getRs();
			if(rsQ3.next()==true) {
				b3=true;
			}
			rsQ3.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydbQ3.closed();
		System.out.println("b3:"+b3);
		
		
		/**
		 * 判断是否插入课表
		 */
		if(b1==true || b2==true || b3==true) {
			if(b1==true){
				if(b2==true){
					request.setAttribute("message", "实验室，班级有冲突，请重新录入");
					if(b3==true){
						request.setAttribute("message", "实验室，班级，教师有冲突，请重新录入");
					}
				}else{
					request.setAttribute("message", "实验室有冲突，请重新录入");
				}	
				request.setAttribute("path", "admin/kebiao/kebiaoAdd.jsp");
			}else if(b2==true){
				if(b3==true){
					request.setAttribute("message", "班级和教师有冲突，请重新录入");
				}else{
					request.setAttribute("message", "班级在这个时间段已经安排课程，请重新录入");
				}
				request.setAttribute("path", "admin/kebiao/kebiaoAdd.jsp");
			}else if(b3==true){
				request.setAttribute("message", "教师冲突，请重新录入");
				request.setAttribute("path", "admin/kebiao/kebiaoAdd.jsp");
			}
		}else{
			String sql="insert into kebiao(shijian,xingqi,dijijieke,banji_id,kecheng_id,tea_id,jiaoshi_id,del) values(?,?,?,?,?,?,?,?)";
			Object[] params={shijian,xingqi,dijijieke,banji_id,kecheng_id,tea_id,jiaoshi_id,del};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			request.setAttribute("message", "操作成功");
			request.setAttribute("path", "kebiao?type=kebiaoMana");
		}
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 课表删除
	 */
	public void kebiaoDel(HttpServletRequest request,HttpServletResponse response) {
		String sql="update kebiao set del='yes' where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "kebiao?type=kebiaoMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}

	/*
	 * 课表管理
	 */
	public void kebiaoMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List kebiaoList=new ArrayList();
		String fangshi = "0";
		String banji_id = "0";
		String sql="select * from kebiao where del='no'";
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
		System.out.println(kebiaoList.size());
		request.setAttribute("kebiaoList", kebiaoList);
		request.setAttribute("fangshi", fangshi);
		request.setAttribute("banji_id", banji_id);
		request.getRequestDispatcher("admin/kebiao/kebiaoMana.jsp").forward(request, response);
	}
	
	//课表查询
	public void kebiaoSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
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
			fangshi = "0";
		}
		
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()) {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		System.out.println(kebiaoList.size());
		request.setAttribute("kebiaoList", kebiaoList);
		request.setAttribute("fangshi", fangshi);
		request.setAttribute("banji_id", banji_id);
		request.getRequestDispatcher("admin/kebiao/kebiaoMana.jsp").forward(request, response);
	}
	
	/*
	 * 跳转页面
	 */
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try {
		    dispatch.forward(request, response);
		    return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
