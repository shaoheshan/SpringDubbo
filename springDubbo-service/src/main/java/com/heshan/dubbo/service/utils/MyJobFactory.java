package com.heshan.dubbo.service.utils;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * @Author Frank
 * @Description: 将quartz创建的Job和Spring集成起来
 * @Description: 采用AutowireCapableBeanFactory 将job纳入Spring容器中去
 * @Date 10:35 2017/7/24
 */
public class MyJobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;
    
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        //进行注入,这属于Spring的技术.
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
      }

}

