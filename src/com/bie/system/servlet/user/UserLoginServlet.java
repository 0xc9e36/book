package com.bie.system.servlet.user;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bie.po.UserInfo;
import com.bie.system.service.UserInfoInsertService;
import com.bie.system.service.impl.UserInfoInsertServiceImpl;
import com.bie.utils.HelpUtils;

//@WebServlet("/system/user/login")
public class UserLoginServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String userAccount = request.getParameter("userAccount");
        String userPw = request.getParameter("userPw");

        //封装到UserInfo中
        UserInfo user = new UserInfo();
        user.setUserAccount(userAccount);

        String pass = null;
        try {
            pass = HelpUtils.str2Md5(userPw);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setUserPw(pass);

        UserInfoInsertService UserInfoInsertService = new UserInfoInsertServiceImpl();
        UserInfo userLogin = UserInfoInsertService.login(user);

        if (userLogin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", userLogin);
            request.setAttribute("userLogin", userLogin);
        }


        //如果返回的不会空,就说明有这个用户,登录成功
        if (userLogin != null && userAccount != null && userPw != null && !"".equals(userAccount) && !"".equals(userPw)) {
            //转化操作,携带数据
            request.getRequestDispatcher("/view/system/main/index.jsp").forward(request, response);
        } else {
            //重定向操作,不携带数据
            //response.sendRedirect("/view/system/main/login.jsp");
            //跳转页面
            request.getRequestDispatcher("/view/system/main/login.jsp").forward(request, response);
        }
    }


}
