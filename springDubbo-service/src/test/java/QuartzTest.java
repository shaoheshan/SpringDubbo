import com.heshan.dubbo.service.utils.QuartzJob;
import com.heshan.dubbo.service.utils.QuartzManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2017/7/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-base.xml" })
public class QuartzTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactory;

    @Test
    public void modifyJobTimeTest() {
        try {
            String job_name = "动态任务调度11";
            System.out.println("【系统启动】开始(每10秒输出一次)...");
            QuartzJob job  = new QuartzJob();
            //传值
            JobDataMap mapDate = new JobDataMap();
            mapDate.put("channel", "vh_topic_b2b");
            mapDate.put("accountId", "account_id");
            mapDate.put("runHande", "export1");
            QuartzManager.modifyJobTime(job_name, "0/4 * * * * ?",schedulerFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



