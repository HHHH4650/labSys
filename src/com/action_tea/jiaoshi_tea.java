package com.action_tea;

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
import com.entity.Jiaoshi;

public class jiaoshi_tea extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type=request.getParameter("type");
		
		if("jiaoshiMana".equals(type)){
			jiaoshiMana(request, response);
		}
		
		if("jiaoshiAdd".equals(type)){
			jiaoshiAdd(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	/*
	 * 教室添加
	 */
	public void jiaoshiAdd(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String del="no";
		String sql="insert into jiaoshi(name,del) values(?,?)";
		Object[] params={name,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "jiaoshi?type=jiaoshiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}

	/*
	 * 实验室信息管理
	 */
	public void jiaoshiMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List jiaoshiList=new ArrayList();
		String sql="select * from jiaoshi where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()) {
				Jiaoshi jiaoshi=new Jiaoshi();
				jiaoshi.setId(rs.getInt("id"));
				jiaoshi.setName(rs.getString("name"));
				jiaoshiList.add(jiaoshi);
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("jiaoshiList", jiaoshiList);
		request.getRequestDispatcher("teacherPage/jiaoshi/jiaoshiMana.jsp").forward(request, response);
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
		} 
		catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
}
