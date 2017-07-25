package com.action;

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
import com.entity.Admin;

public class admin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		
		
		if("adminMana".equals(type)){
			adminMana(request,response);
		}
		
		if("adminAdd".equals(type)){
			adminAdd(request,response);
		}
		
		if("adminDel".equals(type)){
			adminDel(request,response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void adminMana(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List adminList = new ArrayList();
		
		String sql = "select * from admin";
		Object[] params = {};
		DB mydb= new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Admin admin=new Admin();
				admin.setUserId(rs.getInt("userId"));
				admin.setUserName(rs.getString("userName"));
				admin.setUserPw(rs.getString("userPw"));
				adminList.add(admin);
		    }
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("adminList", adminList);
		request.getRequestDispatcher("admin/admin/adminMana.jsp").forward(request, response);
	}
	
	private void adminAdd(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		String sql = "insert into admin(userName,userPw) values(?,?)";
		Object[] params={userName,userPw};
		
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message","操作成功");
		request.setAttribute("path", "admin?type=adminMana");
		
		String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	private void adminDel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("userId"));
		
		String sql = "delete from admin where userId="+Integer.parseInt(request.getParameter("userId"));
		Object[] params = {};
		DB mydb = new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "admin?type=adminMana");
		
		String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}

	
	public void dispatch(String targetURL,HttpServletRequest request,HttpServletResponse response) {
		
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURL);
		try {
		    dispatch.forward(request, response);
		    return;
		} catch (ServletException e) {
                    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
