package com.softeem.test;

import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    @Test
    public void queryForPageTotalCount() throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println("integer = " + integer);
    }

    @Test
    public void queryForPageItems() throws SQLException {
        BookDao bookDao = new BookDaoImpl();
        List<Book> books = bookDao.queryForPageItems(0, 5,null,null,new BigDecimal(80),new BigDecimal(90));
        for (Book book : books) {
            System.out.println("book = " + book);
        }
    }
}