package com.bie.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bie.po.UserInfo;

/**
 * 用户登录的拦截器
 */

//@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		UserInfo user = (UserInfo)session.getAttribute("userLogin");

		String uri = request.getRequestURI();

		//允许通过的路由
		String[] routes = {
				"/system/user/login",//后台登陆成功允许跳转
				"/system/user/register",//允许注册操作
				"view/system/main/register.jsp",//允许跳转到注册页面
				"/resource/",//允许引用样式
				//"/system/", //允许用户和图书操作
		};

		for (String route:routes) {
			if (uri.contains(route)) {
				//System.out.println("放行" + uri + ":" + route);
				filterChain.doFilter(request, response);
				return ;
			}
		}

		if (null != user) {
			filterChain.doFilter(request, response);
			return ;
		}

		request.getRequestDispatcher("/view/system/main/login.jsp").forward(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
