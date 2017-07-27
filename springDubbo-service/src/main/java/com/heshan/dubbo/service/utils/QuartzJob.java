package com.heshan.dubbo.service.utils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.LogUtil;
import com.heshan.framework.log.LogUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName  QuartzJob
 * @Package  com.heshan.dubbo.service.utils
 * @Author Frank
 * @Description: 定时执行 任务串行执行 只是针对同一个job的多个实例 多个job可以并行
 * @Date 11:26 2017/7/24
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJob implements Job {

    /** 任务id */
    private String task_id;

    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(QuartzJob.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void execute(JobExecutionContext context) throws JobExecutionException {
        //执行业务
        execJob(context);
        
    }
    public void execJob(JobExecutionContext context) {
        System.err.println("开始时间"+sdf.format(new Date()));

        LogUtils.error("开始时间:"+sdf.format(new Date())+",毫秒"+System.currentTimeMillis());
        LogUtils.error("执行线程:"+Thread.currentThread().getId());
        try {
            JobDataMap dataMap = context.getJobDetail().getJobDataMap();
            String[] keys=dataMap.getKeys();
            if (!ArrayUtils.isEmpty(keys)){
                for(String key:keys){
                    LogUtils.error("data:{key:"+key+",value:"+dataMap.get(key)+"}");
                }
            }
            /** 获取job信息 */
            /** 消息id */
            task_id = context.getJobDetail().getKey().getName();
            System.out.println("######################################" +System.currentTimeMillis()+",="+task_id);

        }catch (Exception e) {

        }
        System.err.println("结束时间"+sdf.format(new Date()));
        LogUtils.error("结束时间"+sdf.format(new Date())+",毫秒"+System.currentTimeMillis()+",taskId="+task_id);

    }

}
