package com.softeem.utils;

import org.apache.commons.dbutils.*;

public abstract class BaseDao {
    public QueryRunner queryRunner;
    public int pageSize = 4;

    public BaseDao() {
        queryRunner = new QueryRunner(MyDataSource.getDataSource());
    }

}
