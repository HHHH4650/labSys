package com.util;
/**
 * 参数乱码问题的过滤器
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	/**
	 * 执行过滤任务
	 */
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		
		//解决post提交乱码
		request.setCharacterEncoding("utf-8");
		
		//创建一个HttpServletRequest实现类的装饰者类,重写getParameter方法
		MyHttpRequest myRequest = new MyHttpRequest(request);
		
		/**
		 * 放行
		 * 注意：
		 * 这里放行的应该是装饰后的request对象，这样在业务的servlet调用getParameter才是新的重写后的方法。
		 */
		chain.doFilter(myRequest , response );
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	public void destroy() {

	}
}
/**
 * 1) 装饰者类
 */
class MyHttpRequest extends HttpServletRequestWrapper{
	/**
	 * 2) 声明一个被装饰者类型的成员变量
	 */
	private HttpServletRequest request;
	
	/**
	 * 3) 接收被装饰者类的对象
	 */
	public MyHttpRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	/**
	 * 4) 重写getParameter方法
	 */
	@Override
	public String getParameter(String name) {
		try {
			/**
			 * 对get提交的参数处理
			 */
			//4.1  得到原来的参数
			String value = request.getParameter(name);
			//4.2 手动解码
			if("GET".equals(request.getMethod())){
				value = new String(value.getBytes("iso-8859-1"),"utf-8");
			}
			return value;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}