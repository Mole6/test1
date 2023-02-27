package com.softeem.service.impl;

import com.softeem.bean.Admin;
import com.softeem.bean.User;
import com.softeem.dao.AdminDao;
import com.softeem.dao.impl.AdminDaoImpl;
import com.softeem.service.AdminService;
import com.softeem.service.UserService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public void registAdmin(Admin admin) throws SQLException {
        adminDao.save(admin);
    }

    @Override
    public Admin login(Admin admin) throws SQLException {
        return adminDao.queryAdminByAdminNameAndPassword(admin.getAdminName(),admin.getPassword());

    }

    @Override
    public boolean existsUsername(String adminName) throws SQLException {
        Admin admin = adminDao.queryAdminByAdminname(adminName);
        return admin!=null;
    }
}
