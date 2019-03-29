package com.zhangliang.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zhangliang.pojo.User;
import com.zhangliang.service.UserService;
import com.zhangliang.service.impl.UserServiceImpl;
/**
 * Servlet重定向路径总结：
 * 	相对路径：从当前请求的路径查找资源的路径
 * 		相对路径如果servlet的别名中包含目录，会造成重定向资源查找失败。
 * 	绝对路径：第一个/表示服务器根目录
 * 		/虚拟项目名/资源路径
 * 
 * Servlet请求转发：
 * 		/表示项目根目录。
 * 		req.getRequestDispatcher("/资源路径").forward(req, resp);
 * 
 * @author MyPC
 *
 */
public class UserServlet extends HttpServlet {
	//获取service层对象
	UserService us = new UserServiceImpl();
	//获取日志对象
	Logger logger = Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//设置请求编码格式
		req.setCharacterEncoding("utf-8");
		//设置响应编码格式
		res.setContentType("text/html;charset=utf-8");
		//获取操作符
		String oper = req.getParameter("oper");
		if ("login".equals(oper)) {
			//调用登录处理方法
			checkUserLogin(req,res);
		}else if("out".equals(oper)){
			//调用退出处理方法
			userOut(req,res);
		}else if("pwd".equals(oper)){
			//调用密码修改处理方法
			userChangePwd(req,res);
		}else if("show".equals(oper)){
			//调用显示用户信息处理方法
			userShow(req,res);
		}else if("reg".equals(oper)){
			//调用注册处理方法
			userReg(req,res);
		}else{
			logger.debug("没有找到对应的操作符："+oper);
		}
		
		
	}
	//注册用户
	private void userReg(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		int age = req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth = req.getParameter("birth");
		String[] bs;
		if (birth!=null) {
			bs = birth.split("/");
			birth = bs[2]+"-"+bs[0]+"-"+bs[1];
		}
		
		User u = new User(0, uname, pwd, sex, age, birth);
		//处理请求信息
		int index = us.userRegService(u);
		//响应处理结果
		if (index>0) {
			HttpSession hs = req.getSession();
			hs.setAttribute("reg", "true");
			res.sendRedirect("login.jsp");
		}
		
	}

	//显示所以用户信息
	private void userShow(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//处理请求
			//调用service 
		List<User> lu = us.userShowService();
		if (lu!=null) {
			req.setAttribute("lu", lu);
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, res);
			return ;
		}
		
		
	}
	//用户修改密码
	private void userChangePwd(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取新密码数据
		String newPwd = req.getParameter("newPwd");
		//从session中获得用户信息
		User u = (User) req.getSession().getAttribute("user");
		int uid = u.getUid();
		//处理请求
			//调用service处理
		int index = us.userChangePwdService(newPwd,uid);
		if (index>0) {
			//获取session信息
			HttpSession hs = req.getSession();
			hs.setAttribute("pwd", "true");
			//重定向到登录页面
			res.sendRedirect("login.jsp");
		}
		
	}

	//用户退出
	private void userOut(HttpServletRequest req, HttpServletResponse res) throws IOException {
		//获取session对象
		HttpSession hs = req.getSession();
		//强制销毁sessiono
		hs.invalidate();
		//重定向到登陆页面
		res.sendRedirect("login.jsp");
	}
	//处理登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		//获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		//处理请求信息
			
			//校验
		User u = us.checkUserLoginService(uname, pwd);
		if (u!=null) {
			//获取session对象
			HttpSession hs = req.getSession();
			//将用户数据存储到session中
			hs.setAttribute("user", u);
			//重定向
			res.sendRedirect("/Manager/main/main.jsp");
			return ;
		}else{
			//添加标识符到request中
			req.setAttribute("flag", 0);
			//请求转发
			req.getRequestDispatcher("/login.jsp").forward(req, res);
			return ;
		}
		//响应处理结果
			//直接响应
			//请求转发
			
		
	}
}
