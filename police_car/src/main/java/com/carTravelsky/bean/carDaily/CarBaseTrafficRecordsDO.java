package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseTrafficRecordsDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 驾驶员ID */
    private Integer driverID;
    /** 事故日期 */
    @Validate(rule = "{required}",comment="事故日期")
    private String time;
    /** 情况概述 */
    @Validate(rule = "{required,maxSize[128]}",comment=" 情况概述 ")
    private String context;
    /** 处理情况 */
    @Validate(rule = "{required,maxSize[128]}",comment=" 处理情况 ")
    private String conditions;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人 */
    private String updateOperator;
    /** 备注 */
    private String comments;
    /**临时选中id*/
    private Integer brevityId;
    
    public Integer getBrevityId() {
		return brevityId;
	}

	public void setBrevityId(Integer brevityId) {
		this.brevityId = brevityId;
	}

	/**
     * 获取ID
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * 设置ID
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取驾驶员ID
     * @return driverID
     **/
    public Integer getDriverID(){
        return this.driverID;
    }

    /**
     * 设置驾驶员ID
     * @param driverID
     **/
    public void setDriverID(Integer driverID){
        this.driverID = driverID;
    }

   
    public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	/**
     * 获取情况概述
     * @return context
     **/
    public String getContext(){
        return this.context;
    }

    /**
     * 设置情况概述
     * @param context
     **/
    public void setContext(String context){
        this.context = context;
    }

    /**
     * 获取处理情况
     * @return conditions
     **/
    public String getConditions(){
        return this.conditions;
    }

    /**
     * 设置处理情况
     * @param conditions
     **/
    public void setConditions(String conditions){
        this.conditions = conditions;
    }

    /**
     * 获取创建时间
     * @return createTime
     **/
    public Date getCreateTime(){
        return this.createTime;
    }

    /**
     * 设置创建时间
     * @param createTime
     **/
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
     * 获取修改时间
     * @return updateTime
     **/
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置修改时间
     * @param updateTime
     **/
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * 获取修改人
     * @return updateOperator
     **/
    public String getUpdateOperator(){
        return this.updateOperator;
    }

    /**
     * 设置修改人
     * @param updateOperator
     **/
    public void setUpdateOperator(String updateOperator){
        this.updateOperator = updateOperator;
    }

    /**
     * 获取备注
     * @return comments
     **/
    public String getComments(){
        return this.comments;
    }

    /**
     * 设置备注
     * @param comments
     **/
    public void setComments(String comments){
        this.comments = comments;
    }


    public CarBaseTrafficRecordsDO clone(){
        try{
            return (CarBaseTrafficRecordsDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}