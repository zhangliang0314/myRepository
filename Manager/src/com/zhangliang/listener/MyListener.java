package com.zhangliang.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener,ServletContextListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		//获取ServletContext对象
		ServletContext sc = se.getSession().getServletContext();
		//获取统计在线人数的变量
		int count = (int) sc.getAttribute("count");
		//自增存储
		sc.setAttribute("count", ++count);
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//获取ServletContext对象
		ServletContext sc = se.getSession().getServletContext();
		//获取统计在线人数的变量
		int count = (int) sc.getAttribute("count");
		//自增存储
		sc.setAttribute("count", --count);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//获取application对象
		ServletContext sc = sce.getServletContext();
		//在application对象中存储变量用来统计在线人数
		sc.setAttribute("count", 0);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
