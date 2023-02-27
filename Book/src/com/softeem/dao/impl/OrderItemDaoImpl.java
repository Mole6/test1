package com.softeem.dao.impl;

import com.softeem.bean.CartItem;
import com.softeem.bean.OrderItem;
import com.softeem.dao.OrderItemDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao{

    @Override
    public Integer queryForPageTotalCount(String orderId) throws SQLException{
        String sql = "select count(*) from t_order_item where order_id=?";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query(sql,handler,orderId);
        return query.intValue();
    }

    @Override
    public List<OrderItem> queryForPageItems(String orderId, int begin, int pageSize) throws SQLException {
        String sql = "select * from t_order_item where order_id=? order by order_id desc limit ?,?";
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        BeanListHandler<OrderItem> handler = new BeanListHandler<>(OrderItem.class, processor);
        List<OrderItem> query = queryRunner.query(sql, handler,orderId,begin,pageSize);
        return query;
    }

    @Override
    public List<OrderItem> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        queryRunner.update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public void updateById(OrderItem orderItem) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public OrderItem findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderItem> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}