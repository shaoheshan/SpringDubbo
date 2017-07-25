package com.heshan.dubbo.service.utils;

import com.alibaba.dubbo.common.utils.LogUtil;
import com.heshan.framework.log.LogUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName  QuartzJob
 * @Package  com.heshan.dubbo.service.utils
 * @Author Frank
 * @Description: 定时执行
 * @Date 11:26 2017/7/24
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJob implements Job {


    /** 信息发送类 */
    private Properties properties;

    /** 任务id */
    private String task_id;
    /** 队列主题 */
    private String topic;
    /** 队列传参 */
    private String accountId;
    /** 队列过滤 */
    private String taskTag;
    /** 当前时间 */
    private Long currDate;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuartzJob.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void execute(JobExecutionContext context) throws JobExecutionException {
        /**业务逻辑*/     
        //execJobTest(context);
        execJob(context);
        
    }
    public void execJob(JobExecutionContext context) {
        System.err.println("开始时间"+sdf.format(new Date()));

        LogUtils.error("开始时间"+sdf.format(new Date()));
        try {
            // JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            /** 获取job信息 */
            /** 消息id */
            task_id = context.getJobDetail().getKey().getName();
            System.out.println("######################################" +System.currentTimeMillis()+",="+task_id);

        }catch (Exception e) {

        }
        System.err.println("结束时间"+sdf.format(new Date()));
        LogUtils.error("结束时间"+sdf.format(new Date())+",taskId="+task_id);

    }

}
