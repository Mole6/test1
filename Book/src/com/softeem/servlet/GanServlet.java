package com.softeem.servlet;

import com.softeem.utils.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GanServlet", value = "/GanServlet")
public class GanServlet extends BaseServlet {

    protected void mytest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GanServlet.mytest方法执行完成...");
    }

}
