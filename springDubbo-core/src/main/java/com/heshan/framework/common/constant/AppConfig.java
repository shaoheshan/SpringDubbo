package com.heshan.framework.common.constant;

import com.heshan.framework.log.LogUtils;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;
import java.util.ResourceBundle;
/**
 * @Author Frank
 * @Description: TODO(用一句话描述该文件做什么)
 * @Date 13:50 2017/7/21
 */
public class AppConfig {

    private static Object lock = new Object();
    private static AppConfig config = null;
    private static ResourceBundle rb = null;
    private static final String CONFIG_FILE = "conf/common";

    private AppConfig() {
        rb = ResourceBundle.getBundle(CONFIG_FILE);
    }

    /**
     * 服务ID
     */
    public static String SERVICE_ID = "";
    /**
     * 服务版本号
     */
    public static String SERVICE_VERSION = "";
    /**
     * 服务类型
     */
    public static String SERVICE_TYPE = "";
    /**
     * 服务域名
     */
    public static String SERVICE_DOMAIN = "";

    /**
     * 项目运行端口
     */
    public static Integer PROJECT_RUNTIME_PORT = Integer.MAX_VALUE;
    /**
     * 项目运行IP
     */
    public static String PROJECT_RUNTIME_IP = "";

    /**
     * 默认登录在线时间
     */
    public static Integer DEFALUT_SCOPETIME = 60 * 60 * 24;

    /**
     * 根域
     */
    public static String SSO_DOMAIN = "";

    public static Charset CHARSET = Charset.forName("UTF-8");

    public static String STATIC_RESOURSE_DISK_PATH;
    public static String STATIC_RESOURSE_HTTP_URL = "/res";

    public static String SYSTEM_LOGGING_ROOT = "/data/logs/configmgr";
    public static String SYSTEM_LOGGING_LEVEL = "debug";
    /**消费者id*/
    public static String TASK_PRODUCER_ID;
    public static String TASK_CONSUMER_ID;
    public static String TASK_ACCESS_KEY;
    public static String TASK_SECRETKEY;
    public static String TASK_TOPIC;
    public static String TASK_TAG;
    
    public static String WEB_IS_HTTPS;
    
    public static String ANALYSIS_URL;
    public static String ANALYSIS_JS;
    public static String ANALYSIS_IP;
    public static String ANALYSIS_BZ;
    
    
    private static void init() {

        AppConfig conf = getInstance();

        try {

            String str = conf.getValue("logging.root");
            if (StringUtils.isNotBlank(str)) {
                SYSTEM_LOGGING_ROOT = str;
            }
            
            String tmp = conf.getValue("logging.level");
            if (StringUtils.isNotBlank(tmp)) {
                SYSTEM_LOGGING_LEVEL = tmp;
            }
        } catch (Exception e) {
            LogUtils.error(e.getMessage(),new Throwable(e));
        }

    }

    static {
        init();
    }

    /**
     * 初始化本地文件
     */
    public static void initLocalConf() {
        ResourceBundle.clearCache();
        rb = ResourceBundle.getBundle(CONFIG_FILE);
        config = null;
        init();
    }

    public static AppConfig getInstance() {
        synchronized (lock) {
            if (null == config) {
                config = new AppConfig();
            }
        }
        return (config);
    }

    public String getValue(String key) {
        return (rb.getString(key));
    }

}
