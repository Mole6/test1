package com.softeem.servlet;

import com.softeem.bean.Admin;
import com.softeem.bean.User;
import com.softeem.service.AdminService;
import com.softeem.service.UserService;
import com.softeem.service.impl.AdminServiceImpl;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        AdminService adminService = new AdminServiceImpl();
        Admin admin = new Admin();
        Map<String, String[]> entry = request.getParameterMap();
        WebUtils.copyParamToBean(entry, admin);

        try {
            if (adminService.existsUsername(admin.getAdminName())) {   //检查用户名是否存在

                if (adminService.login(admin) != null) {//密码正确
                    admin = adminService.login(admin);
                    HttpSession session = request.getSession();     //会话作用域,保存用户信息
                    session.setAttribute("admin", admin);        //用户登录成功后
                    request.setAttribute("msg", "欢迎回来");
                    request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
                } else {      //密码错误
                    request.setAttribute("msg", "密码错误");    //回显数据
                    request.setAttribute("adminName", admin.getAdminName());
                    request.setAttribute("password", admin.getPassword());
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {           //用户名错误
                request.setAttribute("msg", "用户名错误");    //回显数据
                request.setAttribute("adminName", admin.getAdminName());
                request.setAttribute("password", admin.getPassword());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

