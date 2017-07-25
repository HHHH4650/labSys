package com.action;
/**
 * 专业信息管理
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
import com.entity.Zhuanye;

public class zhuanye extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// type 判断对专业管理的类型（增、删、改）
        String type=request.getParameter("type");
		
        // 专业主页
		if("zhuanyeMana".equals(type)){
			zhuanyeMana(request, response);
		}
		
		// 添加专业信息
		if("zhuanyeAdd".equals(type)){
			zhuanyeAdd(request, response);
		}
		
		// 删除专业信息
		if("zhuanyeDel".equals(type)){
			zhuanyeDel(request, response);
		}
		
		// 修改专业信息
		if("zhuanyeEdit".equals(type)) {
			zhuanyeEdit(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*
	 * 添加专业信息
	 */
	public void zhuanyeAdd(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String del="no";
		String sql="insert into zhuanye(name,del) values(?,?)"; // 预编译sql语句
		Object[] params={name,del};	// 封装添加的专业信息
		DB mydb=new DB();
		mydb.doPstm(sql, params);	// 执行预编译sql
		mydb.closed();
		
		request.setAttribute("message", "操作成功");	// 设置返回message信息到request域中
		request.setAttribute("path", "zhuanye?type=zhuanyeMana");	// 设置跳转路径到request域中
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 专业管理主页
	 */
	public void zhuanyeMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List zhuanyeList=new ArrayList();	
		String sql="select * from zhuanye where del='no'";	// 查询存在的专业
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();	// rs存储所有查询到的专业
			while(rs.next()){			// rs.next（）查询是否存在下一个专业
				Zhuanye zhuanye=new Zhuanye();		// 封装专业信息
				zhuanye.setId(rs.getInt("id"));		
				zhuanye.setName(rs.getString("name"));	
				zhuanyeList.add(zhuanye);		// 添加专业到List中
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("zhuanyeList", zhuanyeList);		// 将所有查询到的专业存储到request作用域中，以便于前端调用
		request.getRequestDispatcher("admin/zhuanye/zhuanyeMana.jsp").forward(request, response);	// 转发到jsp页面
	}
	
	/*
	 * 专业修改
	 */
	public void zhuanyeEdit(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		
		String sql="update zhuanye set name=? where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={name};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "zhuanye?type=zhuanyeMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 专业删除
	 */
	public void zhuanyeDel(HttpServletRequest request,HttpServletResponse response) {
		String sql="update zhuanye set del='yes' where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "zhuanye?type=zhuanyeMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 跳转
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
