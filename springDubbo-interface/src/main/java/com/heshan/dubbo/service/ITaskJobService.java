package com.heshan.dubbo.service;


import com.heshan.dubbo.model.TaskJob;

public interface ITaskJobService {

    
    /***
     * 
    * @Title: saveTaskJob
    * @Description: TODO(新增) 
    * @param @param req
    * @param @return    设定文件 
    * @return ServiceResult<TaskJobInsResp>    返回类型 
    * @throws
     */
    void saveTaskJob(TaskJob req);

    void modifiedTaskJobTime(TaskJob req);

    void stopTaskJob(TaskJob req);

    void recoveryTaskJob(TaskJob req);

    void recmoveTaskJob(TaskJob req);

}
