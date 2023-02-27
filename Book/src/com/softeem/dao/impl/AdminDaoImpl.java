package com.softeem.dao.impl;

import com.softeem.bean.Admin;
import com.softeem.bean.User;
import com.softeem.dao.AdminDao;
import com.softeem.dao.UserDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl extends BaseDao implements AdminDao {

    @Override
    public Admin queryAdminByAdminname(String adminName) throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        String sql = "select * from t_admin where adminname = ?";
        return queryRunner.query(sql,new BeanHandler<>(Admin.class,processor),adminName);
    }

    @Override
    public Admin queryAdminByAdminNameAndPassword(String adminName, String password) throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        String sql = "select * from t_admin where adminname = ? and password=?";
        return queryRunner.query(sql,new BeanHandler<>(Admin.class,processor),adminName,password);
    }

    @Override
    public List<Admin> findAll() throws SQLException {
        return null;
    }

    @Override
    public void save(Admin admin) throws SQLException {
        String sql = "insert into t_admin values(null,?,?,?)";
        //queryRunner.update(sql,user.getUsername(),user.getPassword(),user.getEmail());
        Long id = queryRunner.insert(sql,new ScalarHandler<Long>(),admin.getAdminName(),admin.getPassword(),admin.getEmail());
        admin.setId(id.intValue());
    }

    @Override
    public void updateById(Admin admin) throws SQLException {

    }

    @Override
    public void deleteById(Integer id) throws SQLException {

    }

    @Override
    public Admin findById(Integer id) throws SQLException {
        return null;
    }

    @Override
    public List<Admin> page(Integer pageNumber) throws SQLException {
        return null;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        return null;
    }
}
