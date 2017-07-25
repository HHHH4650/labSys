package com.action_common;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Remove;

import com.dao.DB;
import com.entity.Jiaoshi;
import com.service.getNameService;
import com.util.Util;

public class remainJiaoshi extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Jiaoshi> jiaoshiList=new ArrayList<Jiaoshi>();
		List<Jiaoshi> jiaoshiList1 = new ArrayList<Jiaoshi>();	

		/**
		 * 获取所有实验室
		 */
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
				jiaoshi.setDel("no");
				jiaoshiList.add(jiaoshi);
		    }
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydb.closed();
		
		
		
		/**
		 * 获取已占用的实验室
		 */
		//获取日期、第几节课参数
		String shijian=request.getParameter("shijian");
		String dijijieke=request.getParameter("dijijieke");
		
//		String sqlQ1="SELECT jiaoshi.id, jiaoshi.name FROM jiaoshi INNER JOIN kebiao ON shijian=? and dijijieke=?";
		String sqlQ1="select * from kebiao where shijian=? and dijijieke=?";
		Object[] paramsQ1={shijian,dijijieke};
		DB mydbQ1=new DB();
		try {	
			mydbQ1.doPstm(sqlQ1, paramsQ1);
			ResultSet rs1=mydbQ1.getRs();
			if(rs1.next()==true) {
				Jiaoshi jiaoshi=new Jiaoshi();
				jiaoshi.setId(rs1.getInt("jiaoshi_id"));
				//获取实验室名字
				jiaoshi.setName(getNameService.getJiaoshiName(rs1.getInt("jiaoshi_id")));
				jiaoshi.setDel("no");
				jiaoshiList1.add(jiaoshi);
				
				System.out.println(jiaoshi.toString());
			}
			rs1.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		mydbQ1.closed();
		
		//去掉已占有的实验室
//		jiaoshiList.removeAll(jiaoshiList1);
		
		if(jiaoshiList1 != null){
			Iterator iterator = jiaoshiList.iterator();		// 通过迭代遍历、删除jiaoshiList集合中的元素
			while(iterator.hasNext()){						// iterator.hasNext()查询是否存在下一个元素
				String name = ((Jiaoshi)iterator.next()).getName();
				for (Jiaoshi jiaoshi : jiaoshiList1) {		// 去除jiaoshiList中和jiaoshiList1相同元素
					if(name.equals(jiaoshi.getName())){
						iterator.remove();
					}
				}
			}
		}
		
		
//		for (Jiaoshi list : jiaoshiList) {
//			String name = list.getName();
//			for (Jiaoshi list1 : jiaoshiList1) {
//				if(name.equals(list1.getName())){
//					jiaoshiList.remove(list);
//				}
//			}
//		}
		
//		System.out.println("***********************");
//		for (Jiaoshi list : jiaoshiList1) {
//			String name = list.getName();
//			System.out.println("name:"+name);
//			System.out.println(i);
//			System.out.println(list);
//		}
		
		request.setAttribute("jiaoshiList", jiaoshiList);
		request.getRequestDispatcher("common/RemainJiaoshi.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
