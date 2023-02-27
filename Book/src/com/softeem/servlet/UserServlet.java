package com.softeem.servlet;

import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.CookieUtils;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //不用自己手动的获取用户提交上来的参数A
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);

        UserService userService = new UserServiceImpl();
        //User user = new User(null, username, password, null);

        try {
            User myuser = userService.login(user);
            if (myuser!=null) {
                Cookie nameCookie = new Cookie("username", myuser.getUsername());
                Cookie passCookie = new Cookie("password", myuser.getPassword());
                nameCookie.setMaxAge(60 * 60 * 24 * 7);
                passCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();//会话作用域
                session.setAttribute("user", myuser);//用户登录成功后的个人信息保存到session会话作用域中
                request.setAttribute("msg", "欢迎回来!");
                if (request.getParameter("myurl") !=null && !request.getParameter("myurl").equals("")){
                    request.getRequestDispatcher(request.getParameter("myurl")).forward(request, response);
                }else {
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "账号名或登陆密码不正确");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取Session 中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        /*String username = request.getParameter("username");
        String password = request.getParameter("password");
        *//*String repwd = request.getParameter("repwd");*//*
        String email = request.getParameter("email");


        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("email", email);*/
        String code = request.getParameter("code");
        /*request.setAttribute("code", code);*/

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap, user);
        request.setAttribute("user", user);
        UserService userService = new UserServiceImpl();
        try {
            if (token.equalsIgnoreCase(code)) {
                if (!userService.existsUsername(user.getUsername())) {
                    /*User user = new User(null, username, password, email);*/
                    userService.registUser(user);
                    session.setAttribute("user", user);
                    request.setAttribute("msg", "注册成功！");
                    request.getRequestDispatcher("pages/user/success.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "用户名已存在请更换");
                    request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "验证码不正确");
                request.getRequestDispatcher("pages/user/regist.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();//session立刻注销
        Cookie nameCookie = CookieUtils.findCookie("username", request.getCookies());
        Cookie passCookie = CookieUtils.findCookie("password", request.getCookies());
        if (nameCookie!=null){
            nameCookie.setMaxAge(0);//立刻失效
            response.addCookie(nameCookie);
        }
        if (passCookie!=null){
            passCookie.setMaxAge(0);//立刻失效
            response.addCookie(passCookie);
        }
        response.sendRedirect("index.jsp");
    }
}
