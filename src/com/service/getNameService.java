package com.service;

import java.sql.ResultSet;

import com.dao.DB;

public class getNameService{
	public static String getZhuanyeName(int id){
		String zhuanye_name="";
		
		String sql="select * from zhuanye where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			zhuanye_name=rs.getString("name");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return zhuanye_name;
	}
	
	public static String getBanjiName(int id){
		String name="";
		
		String sql="select * from banji where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("name");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}
	
	public static String getKechengName(int id) {
		String name="";
		
		String sql="select * from kecheng where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("name");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}
	
	public static String getTeaName(int id){
		String name="";
		
		String sql="select * from teacher where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("name");
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}

	public static String getJiaoshiName(int id){
		String name="";
		
		String sql="select * from jiaoshi where id="+id;
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			rs.next();
			name=rs.getString("name");
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return name;
	}
}
