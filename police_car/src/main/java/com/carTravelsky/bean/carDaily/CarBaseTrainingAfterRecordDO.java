package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseTrainingAfterRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 驾驶员ID */
    private Integer driverID;
    /** 培训时间 */
    @Validate(rule = "{required}",comment="培训时间")
    private String time;
    /** 培训内容 */
    @Validate(rule = "{required,maxSize[255]}",comment="培训内容")
    private String context;
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
     * 获取培训内容
     * @return context
     **/
    public String getContext(){
        return this.context;
    }

    /**
     * 设置培训内容
     * @param context
     **/
    public void setContext(String context){
        this.context = context;
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

    /**
     * 获取创建人
     * @return creator
     **/
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建人
     * @param creator
     **/
    public void setCreator(String creator){
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

    public CarBaseTrainingAfterRecordDO clone(){
        try{
            return (CarBaseTrainingAfterRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}