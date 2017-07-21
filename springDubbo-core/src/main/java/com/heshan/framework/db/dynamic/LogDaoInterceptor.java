package com.heshan.framework.db.dynamic;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:23
 */

@Intercepts({@Signature(
        type= Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(
                type= Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class})
})
public class LogDaoInterceptor implements Interceptor {

    private static Logger logger = Logger.getLogger(Logger.class);

    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object [] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement)args[MAPPED_STATEMENT_INDEX];
        Object parameter=null;
        if (invocation.getArgs().length > 1) {
            parameter= args[PARAMETER_INDEX];
        }
        BoundSql boundSql = ms.getBoundSql(parameter);
        Configuration configuration = ms.getConfiguration();
        File file = new File(ms.getResource());
        String sqlMapName = file.getName();
        LogUtils.perfLog("sqlMapName:"+sqlMapName);
        LogUtils.perfLog("statementId:"+ms.getId());
        LogUtils.perfLog("sql:"+boundSql.getSql().replaceAll("[\\s]+", " "));

        LogUtils.perfLog("params:"+showSql(configuration, boundSql));
        long startTime = System.currentTimeMillis();
        Object resObj =  invocation.proceed();
        long endTime = System.currentTimeMillis();
        long difTime = endTime - startTime;
        LogUtils.perfLog(String.format("执行时间%s毫秒",difTime));
        return resObj;
    }
    private static String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    public static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        String sql="";
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                //sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
                sql+=getParameterValue(parameterObject);
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql+="["+propertyName+"="+getParameterValue(obj)+"]";
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql+="["+propertyName+"="+getParameterValue(obj)+"]";
                    }
                }
            }
        }
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}

