package com.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.dao.DB;
import com.entity.Banji;
import com.entity.Jiaoshi;
import com.entity.Kecheng;
import com.entity.Teacher;
import com.entity.Zhuanye;

public class getAllService{
	
    public List zhuanyeAll(){
    	
    	try{
			Thread.sleep(700);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
    	
    	List zhuanyeList=new ArrayList();
		String sql="select * from zhuanye where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()){
				Zhuanye zhuanye=new Zhuanye();
				zhuanye.setId(rs.getInt("id"));
				zhuanye.setName(rs.getString("name"));
				zhuanyeList.add(zhuanye);
		    }
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return zhuanyeList;
    }
    
    
    public List banjiAll(){
    	try{
			Thread.sleep(700);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
    	
    	List banjiList=new ArrayList();
		String sql="select * from banji where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Banji banji=new Banji();
				banji.setId(rs.getInt("id"));
				banji.setName(rs.getString("name"));
				banjiList.add(banji);
		    }
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return banjiList;
    }

    public List kechengAll(){
    	try{
			Thread.sleep(700);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
    	
    	List kechengList=new ArrayList();
		String sql="select * from kecheng where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Kecheng kecheng=new Kecheng();
				kecheng.setId(rs.getInt("id"));
				kecheng.setName(rs.getString("name"));
				kechengList.add(kecheng);
		    }
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return kechengList;
    }
    
    public List teaAll() {
    	try{
			Thread.sleep(700);
		}catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	List teaList=new ArrayList();
		String sql="select * from teacher where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()){
				Teacher tea=new Teacher();
				tea.setId(rs.getInt("id"));
				tea.setName(rs.getString("name"));
				teaList.add(tea);
		    }
			rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mydb.closed();
		return teaList;
    }
    
    public List jiaoshiAll(){
    	try{
			Thread.sleep(700);
		}catch (InterruptedException e)
		{
			e.printStackTrace();
		}
    	
    	List jiaoshiList=new ArrayList();
		String sql="select * from jiaoshi where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next()){
				Jiaoshi jiaoshi=new Jiaoshi();
				jiaoshi.setId(rs.getInt("id"));
				jiaoshi.setName(rs.getString("name"));
				jiaoshiList.add(jiaoshi);
		    }
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		return jiaoshiList;
    }
}
