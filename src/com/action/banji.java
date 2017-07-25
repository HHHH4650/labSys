package com.action;
/**
 * 班级信息管理
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
import com.entity.Banji;
import com.service.getNameService;

public class banji extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String type= request.getParameter("type");
			
			if("banjiMana".equals(type)) {
				banjiMana(request, response);
			}
			
			if("banjiAdd".equals(type)) {
				banjiAdd(request, response);
			}
			
			if("banjiDel".equals(type)) {
				banjiDel(request, response);
			}
			
			if("banjiEdit".equals(type)) {
				banjiEdit(request, response);
			}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/*
	 * 班级添加
	 */
	public void banjiAdd(HttpServletRequest request,HttpServletResponse response) {
		
		String name=request.getParameter("name");
		String zhuanye_id=request.getParameter("zhuanye_id");
		String del="no";
		String sql="insert into banji(name,zhuanye_id,del) values(?,?,?)";
		Object[] params={name,zhuanye_id,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "banji?type=banjiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 班级删除
	 */
	public void banjiDel(HttpServletRequest request,HttpServletResponse response) {
		String sql="update banji set del='yes' where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "banji?type=banjiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}

	/*
	 * 班级信息管理
	 */
	public void banjiMana(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		List banjiList=new ArrayList();
		String sql="select * from banji where del='no' order by zhuanye_id";
		Object[] params={};
		DB mydb=new DB();
		try {
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()) {
				Banji banji=new Banji();
				banji.setId(rs.getInt("id"));
				banji.setName(rs.getString("name"));
				banji.setZhuanye_id(rs.getInt("zhuanye_id"));
				banji.setZhuanye_name(getNameService.getZhuanyeName(rs.getInt("zhuanye_id")));
				banjiList.add(banji);
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		request.setAttribute("banjiList", banjiList);
		request.getRequestDispatcher("admin/banji/banjiMana.jsp").forward(request, response);
	}
	
	/*
	 * 班级信息修改
	 */
	public void banjiEdit(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		String zhuanye_id=request.getParameter("zhuanye_id");
		
		
		String sql="update banji set name=?,zhuanye_id=? where id="+Integer.parseInt(request.getParameter("id"));
		Object[] params={name,zhuanye_id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		request.setAttribute("message", "操作成功");
		request.setAttribute("path", "banji?type=banjiMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, request, response);
	}
	
	/*
	 * 成功跳转
	 */
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response)  {
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try {
		    dispatch.forward(request, response);
		    return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
