package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseDepartmentalRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 驾驶员ID */
    private Integer driverID;
    /** 原部门 */
    @Validate(rule = "{required,maxSize[12]}",comment="原部门")
    private String original;
    /** 接收部门 */
    @Validate(rule = "{required,maxSize[12]}",comment="接收部门")
    private String reception;
    /** 变更时间 */
    @Validate(rule = "{required}",comment="变更时间 ")
    private String changeTime;
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
    
    public String getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

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

    /**
     * 获取原部门
     * @return original
     **/
    public String getOriginal(){
        return this.original;
    }

    /**
     * 设置原部门
     * @param original
     **/
    public void setOriginal(String original){
        this.original = original;
    }

    /**
     * 获取接收部门
     * @return reception
     **/
    public String getReception(){
        return this.reception;
    }

    /**
     * 设置接收部门
     * @param reception
     **/
    public void setReception(String reception){
        this.reception = reception;
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


    public CarBaseDepartmentalRecordDO clone(){
        try{
            return (CarBaseDepartmentalRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}