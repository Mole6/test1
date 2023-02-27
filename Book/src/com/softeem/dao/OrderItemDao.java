package com.softeem.dao;


import com.softeem.bean.CartItem;
import com.softeem.bean.OrderItem;
import com.softeem.utils.BaseInterface;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDao extends BaseInterface<OrderItem> {
    public Integer queryForPageTotalCount(String orderId) throws SQLException;

    List<OrderItem> queryForPageItems(String orderId, int begin, int pageSize) throws SQLException;
}
