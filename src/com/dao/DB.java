package com.dao;
/**
 * 数据库操作
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	
	
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost:3306/paike";
	
	private Connection conn;
	private PreparedStatement pstm;

	public DB(){
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败！");
			e.printStackTrace();
		}
	}

	/** 创建数据库连接 */
	public Connection getConn() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("创建数据库连接失败！");
			conn = null;
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 通过preparendStatement执行sql
	 */
	public void doPstm(String sql, Object[] params) {
		if (sql != null && !sql.equals("")) {
			if (params == null)
				params = new Object[0];
			
			getConn();
			if (conn != null) {
				try {
					System.out.println(sql);
					pstm = conn.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);//收获集的游标能够上下挪动，当数据库改变时，目前收获集不变;
					for (int i = 0; i < params.length; i++) {
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();
				} catch (SQLException e) {
					System.out.println("doPstm()方法出错！");
					e.printStackTrace();
				}
			}
		}
	}
	
	//创建ResultSet对象
	public ResultSet getRs() throws SQLException {
		return pstm.getResultSet();
	}

	//添加数据的行数
	public int getCount() throws SQLException {
		return pstm.getUpdateCount();
	}

	//关闭资源
	public void closed() {
		try {
			if (pstm != null)
				pstm.close();
		} catch (SQLException e) {
			System.out.println("关闭pstm对象失败！");
			e.printStackTrace();
		}
		
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭conn对象失败！");
			e.printStackTrace();
		}
	}
}
