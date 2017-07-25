package com.heshan.dubbo.dao;

import com.heshan.dubbo.model.TaskJob;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @description
 * @date 2016/5/24 15:56
 */
@Repository
public interface TaskDao {

    public int  save(TaskJob task);

}
