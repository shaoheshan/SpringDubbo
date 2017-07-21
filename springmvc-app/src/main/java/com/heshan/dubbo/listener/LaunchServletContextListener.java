package com.heshan.dubbo.listener;

import com.heshan.framework.common.constant.AppConfig;
import com.heshan.framework.common.constant.ConstantParams;
import com.heshan.framework.common.entity.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Random;

/**
 * 项目启动时预先设置的参数
 *
 */
public class LaunchServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    /**
     * @Author Frank
     * @Description: 加载日志文件路径
     * @param
     * @Date 14:07 2017/7/21
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Entry<String, Integer> entry = null;
        System.setProperty(ConstantParams.SYSTEM_LOGGING_ROOT, AppConfig.SYSTEM_LOGGING_ROOT);
        System.setProperty(ConstantParams.SYSTRM_LOGGING_LEVEL, AppConfig.SYSTEM_LOGGING_LEVEL);
    }

    private Entry<String, Integer> getPort(ServletContext sc) {
        Entry<String, Integer> entry = new Entry<String, Integer>();
        return entry.put(Entry.ERROR, 10000 + new Random().nextInt(10000));
    }

}
