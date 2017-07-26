//简单的任务管理类
//QuartzManager.java

package com.heshan.dubbo.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName  QuartzManager
 * @Package  com.heshan.dubbo.service.utils
 * @Author Frank
 * @Description: TODO(用一句话描述该文件做什么)
 * @Date 11:10 2017/7/24
 */
@Component
public class QuartzManager {
	private static SchedulerFactory sf = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "job_group_task_center";
	private static String TRIGGER_GROUP_NAME = "trigger_group_task_center";
    private static String JOb_TRIGGER = "job_trigger_";

	/**创建*/
	public static void addJob(String jobName, QuartzJob job, String time,JobDataMap mapData,SchedulerFactoryBean schedulerFactory) throws SchedulerException, ParseException {
	        /**2.2.1*/
		    try {
		        Scheduler scheduler=schedulerFactory.getScheduler();
		        
		        TriggerKey triggerKey = TriggerKey.triggerKey(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
		        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		        //不存在，创建一个
		        if (null == trigger) {
		            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
		                .withIdentity(jobName, JOB_GROUP_NAME).build();
					if (mapData!=null){
						jobDetail.getJobDataMap().putAll(mapData);
					}
					//SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//Date date=sf.parse((String) mapData.get("endTime"));
					//jobDetail.getJobDataMap().put("jobSays", "Hello World!");
					//jobDetail.getJobDataMap().put("scheduleJob", mapData);
		            //表达式调度构建器
		            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
		            //按新的cronExpression表达式构建一个新的trigger
		            trigger = TriggerBuilder.newTrigger().withIdentity(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME).withSchedule(scheduleBuilder).endAt(null).build();
					scheduler.scheduleJob(jobDetail, trigger);
		        } 
                if (!scheduler.isShutdown())
                    scheduler.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
              /**1.8.5*/
              /*Scheduler sched = schedulerFactory.getScheduler();
              JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// 任务名，任务组，任务执行类
              jobDetail.setJobDataMap(mapData);
              CronTrigger trigger = new CronTrigger(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组
              trigger.setCronExpression(time);// 触发器时间设定
              sched.scheduleJob(jobDetail, trigger);
              sched.deleteJob(jobName, JOB_GROUP_NAME);
              sched.addJob(jobDetail, true);
              sched.scheduleJob(trigger);
              if (!sched.isShutdown())
                  sched.start();*/
	}
	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @param time
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	public static void modifyJobTime(String jobName, String time,SchedulerFactoryBean schedulerFactory) throws SchedulerException, ParseException {
	    /**2.2.1*/
	    Scheduler scheduler = schedulerFactory.getScheduler();
	    TriggerKey triggerKey = TriggerKey.triggerKey(JOb_TRIGGER+jobName,
	            TRIGGER_GROUP_NAME);
	    //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
	    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
	    //表达式调度构建器
	    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);
	    //按新的cronExpression表达式重新构建trigger
	    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
	        .withSchedule(scheduleBuilder).build();
	    //按新的trigger重新设置job执行
	    scheduler.rescheduleJob(triggerKey, trigger);
	    /**1.8.5*/
	    /*Scheduler sched = schedulerFactory.getScheduler();
		Trigger trigger = sched.getTrigger(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
		if (trigger != null) {
			CronTrigger ct = (CronTrigger) trigger;
			ct.setCronExpression(time);
			sched.resumeTrigger(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
			*//**操作缓存数据库*//*
			sched.rescheduleJob(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME, ct);
		}*/
		
	}
	

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * @param jobName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName,SchedulerFactoryBean schedulerFactory) throws SchedulerException {
	    /**2.2.1*/
	    Scheduler scheduler = schedulerFactory.getScheduler();
	    JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
	    scheduler.deleteJob(jobKey);
	    /**1.8.5*/
		/*Scheduler sched = schedulerFactory.getScheduler();
		sched.pauseTrigger(jobName, TRIGGER_GROUP_NAME);// 停止触发器
		sched.unscheduleJob(jobName, TRIGGER_GROUP_NAME);// 移除触发器
		sched.deleteJob(jobName, JOB_GROUP_NAME);// 删除任务*/	
	}

	/**暂停*/
	public static void stopJob(String jobName,SchedulerFactoryBean schedulerFactory) throws SchedulerException {
    	  /**2.2.1*/
	      Scheduler scheduler = schedulerFactory.getScheduler();
    	  JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
    	  scheduler.pauseJob(jobKey);
    	  
	     /* Scheduler scheduler =schedulerFactory.getScheduler();
          scheduler.pauseJob(jobName, JOB_GROUP_NAME);
          scheduler.pauseTrigger(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
          *//**操作缓存数据库*//*
          scheduler.unscheduleJob(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
          scheduler.removeTriggerListener(JOb_TRIGGER+jobName);*/
    }
	
	/**恢复*/
    public static void recoveryJob(String jobName,SchedulerFactoryBean schedulerFactory,String cronExpression) throws SchedulerException {
          /**2.2.1*/
          Scheduler scheduler = schedulerFactory.getScheduler();
          JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
          scheduler.resumeJob(jobKey);
          /**1.8.5*/
          /*Scheduler scheduler =schedulerFactory.getScheduler();
          scheduler.resumeJob(jobName, JOB_GROUP_NAME);*/
        
    }    
    /**一次执行*/
    public static void runJob(String jobName,Job job, String time,JobDataMap mapData,SchedulerFactoryBean schedulerFactory) throws SchedulerException{
          /**2.2.1*/
          Scheduler scheduler =  schedulerFactory.getScheduler();
          
          TriggerKey triggerKey = TriggerKey.triggerKey(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
          CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
          //不存在，创建一个
          if (null == trigger) {
              JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                      .withIdentity(jobName, JOB_GROUP_NAME).storeDurably().build();
                  //表达式调度构建器
                  SimpleTriggerImpl  simpleTriggerImpl = (SimpleTriggerImpl)TriggerBuilder.newTrigger().withIdentity(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME).build();
                  scheduler.scheduleJob(jobDetail, simpleTriggerImpl);
                  scheduler.addJob(jobDetail, true);
          }
         
          //JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
          //scheduler.deleteJob(jobKey);
		   //scheduler.triggerJob(jobKey);
//          if (!scheduler.isShutdown())
//              scheduler.start();
          /**1.8.5*/
          /*Scheduler sched = schedulerFactory.getScheduler();
          JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// 任务名，任务组，任务执行类
          jobDetail.setJobDataMap(mapData);
          Date now = new Date();
          SimpleTrigger simpleTrigger = new SimpleTrigger(JOb_TRIGGER+jobName,TRIGGER_GROUP_NAME, new Date(now.getTime() + 1000L),null,0,0L); 
          sched.scheduleJob(jobDetail, simpleTrigger);
          sched.deleteJob(jobName, JOB_GROUP_NAME);
          sched.addJob(jobDetail, true);
          sched.scheduleJob(simpleTrigger);
          if (!sched.isShutdown())
              sched.start();*/
    }
	/**执行多次*/
	public static void runJobCount(String jobName,Job job, String time,JobDataMap mapData,SchedulerFactoryBean schedulerFactory) throws SchedulerException{
		/**2.2.1*/
		Scheduler scheduler =  schedulerFactory.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//不存在，创建一个
		if (null == trigger) {
			JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
					.withIdentity(jobName, JOB_GROUP_NAME).build();
			//表达式调度构建器
			SimpleTriggerImpl  simpleTriggerImpl = (SimpleTriggerImpl)TriggerBuilder.newTrigger().withIdentity(JOb_TRIGGER+jobName, TRIGGER_GROUP_NAME)
					.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(1,1))//配置重复次数和间隔时间
					.startNow()//设置从当前开始
					.build();
			scheduler.scheduleJob(jobDetail, simpleTriggerImpl);
			// scheduler.addJob(jobDetail, true);
		}

		// JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		//scheduler.deleteJob(jobKey);
		//  scheduler.triggerJob(jobKey);
        //  if (!scheduler.isShutdown())
        // scheduler.start();
		/**1.8.5*/
          /*Scheduler sched = schedulerFactory.getScheduler();
          JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, job.getClass());// 任务名，任务组，任务执行类
          jobDetail.setJobDataMap(mapData);
          Date now = new Date();
          SimpleTrigger simpleTrigger = new SimpleTrigger(JOb_TRIGGER+jobName,TRIGGER_GROUP_NAME, new Date(now.getTime() + 1000L),null,0,0L);
          sched.scheduleJob(jobDetail, simpleTrigger);
          sched.deleteJob(jobName, JOB_GROUP_NAME);
          sched.addJob(jobDetail, true);
          sched.scheduleJob(simpleTrigger);
          if (!sched.isShutdown())
              sched.start();*/
	}
	/**
	 * 移除一个任务
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 * @throws SchedulerException
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName)
			throws SchedulerException {
	    /**2.2.1*/
	    Scheduler scheduler =sf.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,jobName);
        scheduler.deleteJob(jobKey);
        /**1.8.5*/
		/*Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(triggerName, triggerGroupName);// 停止触发器
		sched.unscheduleJob(triggerName, triggerGroupName);// 移除触发器
		sched.deleteJob(jobName, jobGroupName);// 删除任务*/	    
	}
	
	/**验证quartz时间的有效性*/
	public static boolean isValidExpression(final String cronExpression){ 
        CronTriggerImpl trigger = new CronTriggerImpl();        
        try {     
            trigger.setCronExpression(cronExpression);     
            Date date = trigger.computeFirstFireTime(null);       
            return date != null && date.after(new Date());        
        } catch (ParseException e) {     
        }      
        return false;    
            
    }
    public static void main(String[] args){     
        System.out.println(isValidExpression("1 12 12 28 8 ? 2016"));
        System.out.println(isValidExpression("0 0/10 * * * ?"));  
    }     
}
