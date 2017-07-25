package com.heshan.dubbo.test;

import com.heshan.dubbo.service.utils.QuartzJob;
import com.heshan.dubbo.service.utils.QuartzManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2017/7/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext-base.xml" })
public class QuartzTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Test
    public void addJobTest() {
        try {
            String job_name = "1902";
            System.out.println("开始(新增任务)...");
            QuartzJob job  = new QuartzJob();
            //传值
            JobDataMap mapDate = new JobDataMap();
            mapDate.put("accountId", "account_id");
            QuartzManager.addJob(job_name, job, "0 0/1 * * * ?",mapDate,schedulerFactory);
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modifyJobTimeTest() {
        try {
            String job_name = "1901";
            System.out.println("开始");
            QuartzManager.modifyJobTime(job_name, "0 0/2 * * * ?",schedulerFactory);
            //QuartzManager.modifyJobTime("1782", "0 0/20 * * * ?",schedulerFactory);
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**暂停*/
    @Test
    public void stopJobTest(){
        try {
            String job_name = "1785";
            System.out.println("开始(暂停任务)...");
            QuartzManager.stopJob(job_name,schedulerFactory);
            TimeUnit.MINUTES.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**恢复*/
    @Test
    public void recoveryJobTest(){
        try {
            String job_name = "1785";
            System.out.println("【系统启动】开始(每10秒输出一次)...");
            QuartzManager.recoveryJob(job_name,schedulerFactory,"");
            TimeUnit.MINUTES.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void removeJobTest(){
        try {
            String job_name = "1785";
            System.out.println("【系统启动】开始(每10秒输出一次)...");
            QuartzManager.removeJob(job_name,schedulerFactory);
            TimeUnit.MINUTES.sleep(20);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



