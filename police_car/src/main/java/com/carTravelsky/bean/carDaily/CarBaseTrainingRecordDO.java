package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseTrainingRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 驾驶员ID */
    private Integer driverID;
    /** 培训时间 */
    @Validate(rule = "{required}",comment="培训时间 ")
    private String traniningTime;
    /** 培训内容 */
    @Validate(rule = "{required,maxSize[256]}",comment="培训内容")
    private String content;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人员 */
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

    

    public String getTraniningTime() {
		return traniningTime;
	}

	public void setTraniningTime(String traniningTime) {
		this.traniningTime = traniningTime;
	}

	/**
     * 获取培训内容
     * @return content
     **/
    public String getContent(){
        return this.content;
    }

    /**
     * 设置培训内容
     * @param content
     **/
    public void setContent(String content){
        this.content = content;
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
     * 获取修改人员
     * @return updateOperator
     **/
    public String getUpdateOperator(){
        return this.updateOperator;
    }

    /**
     * 设置修改人员
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


    public CarBaseTrainingRecordDO clone(){
        try{
            return (CarBaseTrainingRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}