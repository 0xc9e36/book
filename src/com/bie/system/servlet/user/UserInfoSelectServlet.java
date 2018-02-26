package com.bie.system.servlet.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bie.po.UserInfo;
import com.bie.system.service.UserInfoInsertService;
import com.bie.system.service.impl.UserInfoInsertServiceImpl;

/**
 * 用户查询的Sevlet
 * @author Administrator
 *
 */
//@WebServlet("/system/user/select")
public class UserInfoSelectServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserInfoInsertService UserInfoInsertService = new UserInfoInsertServiceImpl();

		//获取查询的条件,账号或者用户姓名
		UserInfo users = new UserInfo();

		//设置默认查询选项
		String condition = request.getParameter("condition");
		condition = condition == null ? "userAccount" : condition;

		//获取到查询的内容
		String content = request.getParameter("content");

		if (content != null) {
			content = content.trim();
			if ("userName".equals(condition)) {
				users.setUserName(content);
			}
			if ("userAccount".equals(condition)) {
				users.setUserAccount(content);
			}
		}


		List<UserInfo> selectUser = UserInfoInsertService.selectUser(users);
		//将获取的用户信息保存到域中
		request.setAttribute("list", selectUser);

		//保存查询条件
		request.setAttribute("content", content);
		request.setAttribute("condition", condition);

		//转化操作,携带数据
		request.getRequestDispatcher("/view/system/userinfo/userinfo_list.jsp").forward(request, response);
	}

	
}
