package com.heshan.dubbo.service.impl;

import java.text.ParseException;

import com.heshan.dubbo.dao.TaskDao;
import com.heshan.dubbo.service.ITaskJobService;
import com.heshan.dubbo.service.utils.QuartzJob;
import com.heshan.dubbo.service.utils.QuartzManager;
import com.heshan.dubbo.model.TaskJob;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class TaskJobServiceImpl  implements ITaskJobService {

	@Autowired
    private TaskDao taskDao;
    @Autowired
    private SchedulerFactoryBean schedulerFactory;



    @Override
    public void insertTaskJob(TaskJob taskJob) {

        if (!checkTaskJobParam(taskJob,"INS")) {
            System.out.println("检查失败");
        }
        /**操作quartz*/
        taskDao.save(taskJob);
        /**立即执行*/
        if(taskJob.getRunType()==1){
            try {
                synchronized (this) {
                    QuartzJob job  = new QuartzJob();
                    JobDataMap mapDate = new JobDataMap();
                    mapDate.put("topic", taskJob.getTopic());
                    QuartzManager.runJob(taskJob.getId()+"",job,taskJob.getJobQuartzTime(),mapDate,schedulerFactory);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
         /**循环执行*/
        }else if(taskJob.getRunType()==2){
            try {
                synchronized (this) {
                    QuartzJob job  = new QuartzJob();
                    JobDataMap mapDate = new JobDataMap();
                    mapDate.put("topic", taskJob.getTopic());
                    QuartzManager.addJob(taskJob.getId()+"", job,taskJob.getJobQuartzTime(),mapDate,schedulerFactory);
                }
            } catch (SchedulerException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }   
        }
    }

    @Override
    public void modifiedTaskJobTime(TaskJob req) {

    }

    @Override
    public void stopTaskJob(TaskJob req) {

    }

    @Override
    public void recoveryTaskJob(TaskJob req) {

    }

    @Override
    public void recmoveTaskJob(TaskJob req) {

    }

    private boolean checkTaskJobParam(TaskJob taskJob, String Type) {
        if(!StringUtils.isBlank(taskJob.getJobQuartzTime())){
            if(!QuartzManager.isValidExpression(taskJob.getJobQuartzTime())){
                System.out.println("时间格式非法");
                return false;
            }
        }
        return true;
    }

}
