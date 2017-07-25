package com.action_tea;

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
import javax.servlet.http.HttpSession;


import com.dao.DB;
import com.entity.Kebiao;
import com.entity.Kecheng;
import com.service.getNameService;
import com.util.Util;

public class kecheng_tea extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type=request.getParameter("type");
		
		if("kechengMana".equals(type)){
			kechengMana(request, response);
		}
		
		if("kechengAdd".equals(type)){
			kechengAdd(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/*
	 * 课程管理页面
	 */
	public void kechengMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List kechengList=new ArrayList();
		String sql="select * from kecheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()) {
				Kecheng kecheng=new Kecheng();
				kecheng.setId(rs.getInt("id"));
				kecheng.setName(rs.getString("name"));
				kechengList.add(kecheng);
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("kechengList", kechengList);
		request.getRequestDispatcher("teacherPage/kecheng/kechengMana.jsp").forward(request, response);
	}
	
	/*
	 * 课程添加
	 */
	public void kechengAdd(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String jieshao=request.getParameter("jieshao");
		String del="no";
		String sql="insert into kecheng(name,del) values(?,?)";
		Object[] params={name,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "kecheng_tea?type=kechengMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	/*
	 * 跳转页面
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
