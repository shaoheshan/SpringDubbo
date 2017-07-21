package com.heshan.framework.db.dynamic;

/**
 * Created by Administrator on 2016/5/24.
 */

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源，用户读写分离
 * 事务内的读操作会走主库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    JdbcContextHolder jdbcContextHolder;

    @Override
    protected Object determineCurrentLookupKey() {

        Object name = jdbcContextHolder.getJdbcType();

        if (null == name || StringUtils.isBlank(name.toString())) {// 默认必须为主库，用于事务的控制
            name = jdbcContextHolder.getMasterJdbcType();
        }

        System.out.println("current db : " + name);
        return name;
    }

    public JdbcContextHolder getJdbcContextHolder() {
        return jdbcContextHolder;
    }

    public void setJdbcContextHolder(JdbcContextHolder jdbcContextHolder) {
        this.jdbcContextHolder = jdbcContextHolder;
    }

}

