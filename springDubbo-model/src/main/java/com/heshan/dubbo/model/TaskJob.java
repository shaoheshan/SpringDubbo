package com.heshan.dubbo.model;

import java.util.HashMap;
import java.util.Map;


public class TaskJob implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 唯一标识       db_column: id 
     */	
	private Long id;
    /**
     * 任务id       db_column: task_id 
     */	
	private String taskId;
    /**
     * 任务名称       db_column: name 
     */	
	private String name;
    /**
     * 任务类型 1、代办任务，2、预警任务、3、查询任务       db_column: job_type 
     */	
	private Integer jobType;
    /**
     * 创建者       db_column: producer_id 
     */	
	private String producerId;
    /**
     * 消费者       db_column: consumer_id 
     */	
	private String consumerId;
    /**
     * 定时时间       db_column: job_quartz_time 
     */	
	private String jobQuartzTime;
    /**
     * 结束时间       db_column: job_end_time 
     */	
	private Long jobEndTime;
    /**
     * 渠道  1b2b,2b2c,3yhrp,4hrp,5health       db_column: channel 
     */	
	private String channel;
    /**
     * 账户id       db_column: account_id 
     */	
	private Long accountId;
    /**
     * 状态 1提起  0睡眠 2删除       db_column: status 
     */	
	private Integer status;
	
	/**运行状态，1、立即执行  2循环执行*/
	private Integer runType;
	/**
	 * 消费主题  db_column: topic 
	 */
	private String topic;
    /**
     * 过滤分类（export  email message）       db_column: task_tag 
     */	
	private String taskTag;
    /**
     * 创建人       db_column: create_user 
     */	
	private Long createUser;
    /**
     * 创建时间       db_column: create_time 
     */	
	private Long createTime;
    /**
     * 创建人       db_column: update_user 
     */	
	private Long updateUser;
    /**
     * 创建时间       db_column: update_time 
     */	
	private Long updateTime;
    /**
     * 描述       db_column: descr 
     */	
	private String descr;
    /**
     * 备注       db_column: remark 
     */	
	private String remark;

	public TaskJob(){
	}

	public TaskJob(Long id){
		this.id = id;
	}


	public TaskJob(TaskJob taskJob){
		if(null != taskJob){
				this.setId(taskJob.getId());
				this.setTaskId(taskJob.getTaskId());
				this.setName(taskJob.getName());
				this.setJobType(taskJob.getJobType());
				this.setProducerId(taskJob.getProducerId());
				this.setConsumerId(taskJob.getConsumerId());
				this.setJobQuartzTime(taskJob.getJobQuartzTime());
				this.setJobEndTime(taskJob.getJobEndTime());
				this.setChannel(taskJob.getChannel());
				this.setAccountId(taskJob.getAccountId());
				this.setRunType(taskJob.getRunType());
				this.setStatus(taskJob.getStatus());
				this.setTopic(taskJob.getTopic());
				this.setTaskTag(taskJob.getTaskTag());
				this.setCreateUser(taskJob.getCreateUser());
				this.setCreateTime(taskJob.getCreateTime());
				this.setUpdateUser(taskJob.getUpdateUser());
				this.setUpdateTime(taskJob.getUpdateTime());
				this.setDescr(taskJob.getDescr());
				this.setRemark(taskJob.getRemark());
		}
	}
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("taskId",this.taskId);
		map.put("name",this.name);
		map.put("jobType",this.jobType);
		map.put("producerId",this.producerId);
		map.put("consumerId",this.consumerId);
		map.put("jobQuartzTime",this.jobQuartzTime);
		map.put("jobEndTime",this.jobEndTime);
		map.put("channel",this.channel);
		map.put("accountId",this.accountId);
		map.put("runType",this.runType);
		map.put("status",this.status);
		map.put("topic",this.topic);
		map.put("taskTag",this.taskTag);
		map.put("createUser",this.createUser);
		map.put("createTime",this.createTime);
		map.put("updateUser",this.updateUser);
		map.put("updateTime",this.updateTime);
		map.put("descr",this.descr);
		map.put("remark",this.remark);
		return map;
	}

	
	
	public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getRunType() {
        return runType;
    }

    public void setRunType(Integer runType) {
        this.runType = runType;
    }

    public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
		
	public void setTaskId(String value) {
		this.taskId = value;
	}
	
	public String getTaskId() {
		return this.taskId;
	}
		
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
		
	public void setJobType(Integer value) {
		this.jobType = value;
	}
	
	public Integer getJobType() {
		return this.jobType;
	}
		
	public void setProducerId(String value) {
		this.producerId = value;
	}
	
	public String getProducerId() {
		return this.producerId;
	}
		
	public void setConsumerId(String value) {
		this.consumerId = value;
	}
	
	public String getConsumerId() {
		return this.consumerId;
	}
		
	public void setJobQuartzTime(String value) {
		this.jobQuartzTime = value;
	}
	
	public String getJobQuartzTime() {
		return this.jobQuartzTime;
	}
		
	public void setJobEndTime(Long value) {
		this.jobEndTime = value;
	}
	
	public Long getJobEndTime() {
		return this.jobEndTime;
	}
		
	public void setChannel(String value) {
		this.channel = value;
	}
	
	public String getChannel() {
		return this.channel;
	}
		
	public void setAccountId(Long value) {
		this.accountId = value;
	}
	
	public Long getAccountId() {
		return this.accountId;
	}
		
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
		
	public void setTaskTag(String value) {
		this.taskTag = value;
	}
	
	public String getTaskTag() {
		return this.taskTag;
	}
		
	public void setCreateUser(Long value) {
		this.createUser = value;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
		
	public void setCreateTime(Long value) {
		this.createTime = value;
	}
	
	public Long getCreateTime() {
		return this.createTime;
	}
		
	public void setUpdateUser(Long value) {
		this.updateUser = value;
	}
	
	public Long getUpdateUser() {
		return this.updateUser;
	}
		
	public void setUpdateTime(Long value) {
		this.updateTime = value;
	}
	
	public Long getUpdateTime() {
		return this.updateTime;
	}
		
	public void setDescr(String value) {
		this.descr = value;
	}
	
	public String getDescr() {
		return this.descr;
	}
		
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
	}
 
}

