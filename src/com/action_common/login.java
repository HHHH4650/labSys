package com.action_common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.dao.DB;
import com.entity.Admin;
import com.entity.Teacher;

public class login extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 用户类型 "0"代表管理员  "1"代表教师
		int userType = Integer.parseInt(request.getParameter("userType"));

		// 判断用户有类型
		if(userType==0){
			System.out.println("管理员登陆");
		}else{
			System.out.println("教师登陆");
		}
		System.out.println("userType："+userType);
		try{
			Thread.sleep(700);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		int result = 1;
		
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		
		//系统管理员登陆
		if(userType==0){
			// 准备预编译sql
			String sql="select * from admin where userName=? and userPw=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			// 执行预编译sql
			mydb.doPstm(sql, params);
			try{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false){
					 result = 0;
				}
				else{
					 result = 1;
					 Admin admin=new Admin();
					 admin.setUserId(rs.getInt("userId"));
					 admin.setUserName(rs.getString("userName"));
					 admin.setUserPw(rs.getString("userPw"));
					 HttpSession session = request.getSession();
					 session.setAttribute("userType", 0);
		             session.setAttribute("user", admin);
				}
				rs.close();
			} 
			catch (SQLException e){
				System.out.println("登录失败！");
				e.printStackTrace();
			}finally{
				mydb.closed();
			}
		}

		System.out.println(result);
		
		//教师用户登录
		if(userType==1){
			String sql="select * from teacher where bianhao=? and password=?";
			Object[] params={userName,userPw};
			DB mydb=new DB();
			mydb.doPstm(sql, params);
			try{
				ResultSet rs=mydb.getRs();
				boolean mark=(rs==null||!rs.next()?false:true);
				if(mark==false){
					 result = 0;
				}
				else{
					 result = 1;
					 Teacher t=new Teacher();
					 t.setId(rs.getInt("id"));
					 t.setBianhao(rs.getString("bianhao"));
					 t.setName(rs.getString("name"));
					 t.setPassword(rs.getString("password"));
					 t.setAge(rs.getInt("age"));

					 HttpSession session = request.getSession();
					 session.setAttribute("tea_id", t.getId());
					 session.setAttribute("userType", 1);
		             session.setAttribute("teacher", t);
				}
				rs.close();
			} 
			catch (SQLException e){
				System.out.println("登录失败！");
				e.printStackTrace();
			}finally{
				mydb.closed();
			}
		}
		
		System.out.println(result);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
