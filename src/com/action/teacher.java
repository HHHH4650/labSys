package com.action;
/**
 * 教室信息管理
 */
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.entity.Teacher;

public class teacher extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		
		if("teaMana".equals(type)){
			teaMana(request, response);
		}
		
		if("teaAdd".equals(type)){
			teaAdd(request, response);
		}
		
		if("teaDel".equals(type)){
			teaDel(request, response);
		}
		
		if("teaEdit".equals(type)){
			teaEdit(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*
	 * 教师添加
	 */
	public void teaAdd(HttpServletRequest request,HttpServletResponse response) {
		String bianhao=request.getParameter("bianhao");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String password = request.getParameter("password");
		System.out.println(request.getParameter("age").trim());
		int age=Integer.parseInt(request.getParameter("age"));
		String del="no";
		
		/**
		 * 检测教师编号是否冲突
		 */
		boolean b = false;
		String sqlQ="select * from teacher where bianhao=? and del=?";
		Object[] paramsQ = {bianhao,"no"};
		DB mydbQ=new DB();
		try {	
			mydbQ.doPstm(sqlQ, paramsQ);
			ResultSet rsQ=mydbQ.getRs();
			if(rsQ.next()==true) {
				b=true;
			}
			rsQ.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydbQ.closed();
		System.out.println("b1:"+b);
		
		/**
		 * 如果不冲突插入教师信息
		 */
		if(b==true){
			request.setAttribute("message", "教师编号冲突，请重新输入");
			request.setAttribute("path", "admin/teacher/teaAdd.jsp");
		}else{
			String sql="insert into teacher(bianhao,name,sex,age,password,del) values(?,?,?,?,?,?)";
			Object[] params={bianhao,name,sex,age,password,del};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			mydb.closed();
			
			request.setAttribute("message", "操作成功");
			request.setAttribute("path", "teacher?type=teaMana");
		}
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 教师删除
	 */
	public void teaDel(HttpServletRequest request,HttpServletResponse response) {
		String sql="update teacher set del='yes' where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "teacher?type=teaMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}

	/*
	 * 教师管理
	 */
	public void teaMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List teaList=new ArrayList();
		String sql="select * from teacher where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()){
				Teacher tea=new Teacher();
				tea.setId(rs.getInt("id"));
				tea.setBianhao(rs.getString("bianhao"));
				tea.setName(rs.getString("name"));
				tea.setSex(rs.getString("sex"));
				tea.setAge(rs.getInt("age"));
				tea.setPassword(rs.getString("password"));
				teaList.add(tea);
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("teaList", teaList);
		request.getRequestDispatcher("admin/teacher/teaMana.jsp").forward(request, response);
	}
	
	/*
	 * 教师修改
	 */
	public void teaEdit(HttpServletRequest request,HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));
		
		String bianhao=request.getParameter("bianhao");
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		int age=Integer.parseInt(request.getParameter("age"));
		
		String sql="update teacher set bianhao=?,name=?,sex=?,age=? where id=?";
		Object[] params={bianhao,name,sex,age,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "teacher?type=teaMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	/*
	 * 跳转
	 */
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) {
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try {
		    dispatch.forward(request, response);
		    return;
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
