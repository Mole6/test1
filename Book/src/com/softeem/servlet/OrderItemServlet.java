package com.softeem.servlet;

import com.softeem.bean.OrderItem;
import com.softeem.service.OrderItemService;
import com.softeem.service.impl.OrderItemServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.Page;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "OrderItemServlet", value = "/OrderItemServlet")
public class OrderItemServlet extends BaseServlet {
    private OrderItemService orderItemService = new OrderItemServiceImpl();

    /**
     * 查找改订单号的所有订单详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllOrderByOrderid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  获取orderId
        String orderId = request.getParameter("orderId");
        System.out.println("orderId = " + orderId);
        try {
            int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);//取不到默认显示第1页
            int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);//O取到默认每页显示4条数据
            Page<OrderItem> page = orderItemService.page(orderId,pageNo,pageSize);
            page.setUrl("OrderItemServlet?action=findAllOrderByOrderid&orderId="+orderId+"&");
            request.setAttribute("page", page);
            request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}