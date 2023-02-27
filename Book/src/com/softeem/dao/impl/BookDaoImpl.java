package com.softeem.dao.impl;

import com.softeem.bean.Book;
import com.softeem.dao.BookDao;
import com.softeem.utils.BaseDao;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    /**
     * 查询book总记录数
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Integer queryForPageTotalCount() throws SQLException {
        String sql = "select count(*) from t_book";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler);
        return i.intValue();

    }

    /**
     * @param begin    起始值
     * @param pageSize 每次查询几条数据
     * @return
     * @throws SQLException
     */
    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        String sql = "select * from t_book order by id desc limit ?,?";
        List<Book> bookList = queryRunner.query(sql, new BeanListHandler<>(Book.class, processor), begin, pageSize);
        return bookList;
    }

    @Override
    public Integer queryForPageTotalCount(String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuilder sql = new StringBuilder("select count(*) from t_book where 1 = 1 ");
        ArrayList<Object> list = new ArrayList<>();

        if (name != null && !"".equals(name)) {
            sql.append(" and name like ? ");
            list.add("%" + name + "%");
        }
        if (author != null && !"".equals(author)) {
            sql.append(" and author like ? ");
            list.add("%" + author + "%");
        }
        if ((min != null && min.signum() == 1) && (max != null && max.signum() == 1)) {
            //min值大于max值
            if (min.compareTo(max) == 1) { //进行两值交换
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        } else if (min != null && min.signum() == 1) {
            sql.append(" and price > ? ");
            list.add(min);
        } else if (max != null && max.signum() == 1) {
            sql.append(" and price < ? ");
            list.add(max);
        }
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql.toString(), handler, list.toArray());
        return i.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize, String name, String author, BigDecimal min, BigDecimal max) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from t_book  where 1 = 1 ");
        ArrayList<Object> list = new ArrayList<>();

        if (name != null && !"".equals(name)) {
            sql.append(" and name like ? ");
            list.add("%" + name + "%");
        }
        if (author != null && !"".equals(author)) {
            sql.append(" and author like ? ");
            list.add("%" + author + "%");
        }
        if ((min != null && min.signum() == 1) && (max != null && max.signum() == 1)) {
            //min值大于max值
            if (min.compareTo(max) == 1) { //进行两值交换
                BigDecimal temp = min;
                min = max;
                max = temp;
            }
            sql.append(" and price between ? and ? ");
            list.add(min);
            list.add(max);
        } else if (min != null && min.signum() == 1) {
            sql.append(" and price > ? ");
            list.add(min);
        } else if (max != null && max.signum() == 1) {
            sql.append(" and price < ? ");
            list.add(max);
        }

        StringBuilder end = new StringBuilder(" order by price desc limit ?,?");
        sql.append(end);
        list.add(begin);
        list.add(pageSize);
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        List<Book> bookList = queryRunner.query(sql.toString(), new BeanListHandler<>(Book.class, processor), list.toArray());
        return bookList;

    }

    /*@Override
    public Integer queryForPriceCount(int min, int max) throws SQLException {
        String sql = "select count(*) from t_book where price between ? and ?";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long i = queryRunner.query(sql, handler,min,max);
        return i.intValue();
    }

    @Override
    public List<Book> queryForPrice(int min, int max, int pageNo, int pageSize) throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        String sql = "select * from t_book where price between ? and ? order by price desc limit ?,?";
        List<Book> query = queryRunner.query(sql, new BeanListHandler<>(Book.class, processor), min, max, pageNo, pageSize);
        return query;
    }*/

    @Override
    public List<Book> findAll() throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        String sql = "select * from t_book order by id desc";
        BeanListHandler<Book> handler = new BeanListHandler<>(Book.class, processor);
        List<Book> query = queryRunner.query(sql, handler);
        return query;
    }

    @Override
    public void save(Book book) throws SQLException {
        queryRunner.update("insert into t_book values(null,?,?,?,?,?,?)",
                book.getName(), book.getPrice(),
                book.getAuthor(), book.getSales(),
                book.getStock(), book.getImgPath());
    }

    @Override
    public void updateById(Book book) throws SQLException {
        String sql = "update t_book set name=?, price=?, author=?, sales=?, stock=?, img_path=? where id = ?";
        queryRunner.update(sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        queryRunner.update("delete from t_book where id = ?", id);
    }

    @Override
    public Book findById(Integer id) throws SQLException {
        //开启下划线->驼峰转换所用 - strat
        BeanProcessor bean = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bean);
        //开启下划线->驼峰转换所用 - end
        BeanHandler<Book> handler = new BeanHandler<>(Book.class, processor);
        Book query = queryRunner.query("select * from t_book where id = ?", handler, id);
        return query;
    }

    @Override
    public List<Book> page(Integer pageNumber) throws SQLException {
        String sql = "select * from t_book limit ?,?";
        BeanListHandler<Book> hander = new BeanListHandler<>(Book.class);
        List<Book> deptList = queryRunner.query(sql, hander, (pageNumber - 1) * pageSize, pageSize);
        return deptList;
    }

    @Override
    public Integer pageRecord() throws SQLException {
        String sql = "select count(*) from t_book";
        ScalarHandler<Long> handler = new ScalarHandler<>();
        Long query = queryRunner.query(sql, handler);
        return query.intValue();
    }


}
