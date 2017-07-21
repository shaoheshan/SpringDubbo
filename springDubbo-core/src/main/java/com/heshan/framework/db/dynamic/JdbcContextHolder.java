package com.heshan.framework.db.dynamic;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:15
 */
public class JdbcContextHolder {

    private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

    public final String MASTER = "master";
    public final String SLAVE = "slave";

    private void setJdbcType(Object jdbcType) {
        contextHolder.set(jdbcType);
    }

    public void setSlave() {
        clearJdbcType();
        setJdbcType(SLAVE);
    }

    public void setMaster() {
        clearJdbcType();
        setJdbcType(MASTER);
    }

    public Object getJdbcType() {
        return contextHolder.get();
    }

    public Object getMasterJdbcType() {
        this.setMaster();
        return contextHolder.get();
    }

    public void clearJdbcType() {
        contextHolder.remove();
    }

}
