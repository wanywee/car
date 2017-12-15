package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarDailyViolationRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** 本表ID */
    private Integer id;
    /** 车辆ID */
	@Validate(rule = "{required}", comment = "车辆ID")
    private Integer carID;
    /** 驾驶员 */
	@Validate(rule = "{required,maxSize[12]}", comment = "驾驶员")
    private String driver;
    /** 违章项目 */
	@Validate(rule = "{required,maxSize[12]}", comment = "违章项目")
    private String violationProject;
    /** 罚款 */
	@Validate(rule = "{required}", regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "罚款")
    private Float fine;
    /** 扣分 */
	@Validate(rule = "{required}", regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "扣分")
    private String points;
    /** 违章日期 */
	@Validate(rule = "{required}", comment = "违章日期")
    private Date violationDate;
    /** 所属地区 */
//	@Validate(rule = "{required,maxSize[64]}", comment = "所属地区")
    private String region;
    /** 违章地点 */
	@Validate(rule = "{required,maxSize[64]}", comment = "违章地点")
    private String violationAddress;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createPeople;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人 */
    private String updatePeople;
    /** 备注 */
	@Validate(rule = "{maxSize[128]}", comment = "备注")
    private String remark;
	/** 删除状态码（1、为存在2为删除） */
    private Integer deleteCode;
	/** 车牌号 */
	private String licenseno;
	/** 职员姓名 */
	private String staffName;
    /**
     * 获取本表ID
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * 设置本表ID
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取车辆ID
     * @return carID
     **/
    public Integer getCarID(){
        return this.carID;
    }

    /**
     * 设置车辆ID
     * @param carID
     **/
    public void setCarID(Integer carID){
        this.carID = carID;
    }

    /**
     * 获取驾驶员
     * @return driver
     **/
    public String getDriver(){
        return this.driver;
    }

    /**
     * 设置驾驶员
     * @param driver
     **/
    public void setDriver(String driver){
        this.driver = driver;
    }

    /**
     * 获取违章项目
     * @return violationProject
     **/
    public String getViolationProject(){
        return this.violationProject;
    }

    /**
     * 设置违章项目
     * @param violationProject
     **/
    public void setViolationProject(String violationProject){
        this.violationProject = violationProject;
    }

    /**
     * 获取罚款
     * @return fine
     **/
    public Float getFine(){
        return this.fine;
    }

    /**
     * 设置罚款
     * @param fine
     **/
    public void setFine(Float fine){
        this.fine = fine;
    }

    /**
     * 获取扣分
     * @return points
     **/
    public String getPoints(){
        return this.points;
    }

    /**
     * 设置扣分
     * @param points
     **/
    public void setPoints(String points){
        this.points = points;
    }

    /**
     * 获取违章日期
     * @return violationDate
     **/
    public Date getViolationDate(){
        return this.violationDate;
    }

    /**
     * 设置违章日期
     * @param violationDate
     **/
    public void setViolationDate(Date violationDate){
        this.violationDate = violationDate;
    }

    /**
     * 获取所属地区
     * @return region
     **/
    public String getRegion(){
        return this.region;
    }

    /**
     * 设置所属地区
     * @param region
     **/
    public void setRegion(String region){
        this.region = region;
    }

    /**
     * 获取违章地点
     * @return violationAddress
     **/
    public String getViolationAddress(){
        return this.violationAddress;
    }

    /**
     * 设置违章地点
     * @param violationAddress
     **/
    public void setViolationAddress(String violationAddress){
        this.violationAddress = violationAddress;
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
     * @return createPeople
     **/
    public String getCreatePeople(){
        return this.createPeople;
    }

    /**
     * 设置创建人
     * @param createPeople
     **/
    public void setCreatePeople(String createPeople){
        this.createPeople = createPeople;
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
     * @return getUpdatePeople
     **/
    public String getUpdatePeople(){
        return this.updatePeople;
    }

    /**
     * 设置修改人
     * @param setUpdatePeople
     **/
    public void setUpdatePeople(String updatePeople){
        this.updatePeople = updatePeople;
    }

    /**
     * 获取备注
     * @return remark
     **/
    public String getRemark(){
        return this.remark;
    }

    /**
     * 设置备注
     * @param remark
     **/
    public void setRemark(String remark){
        this.remark = remark;
    }

    /**
     * 获取删除状态码
     * @param deleteCode
     **/
	public Integer getDeleteCode() {
		return deleteCode;
	}

    /**
     * 设置删除状态码
     * @param deleteCode
     **/
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}
	
	/**
	 * 得到车牌号码
	 * @param licenseno
	 **/
	public String getLicenseno() {
		return licenseno;
	}

	/**
	 * 设置车牌号码
	 * @param licenseno
	 **/
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	
	/**
	 * 获取驾驶员姓名
	 * @return staffName
	 **/
	public String getStaffName() {
		return staffName;
	}
	/**
	 * 设置驾驶员姓名
	 *@return staffName
	 **/
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
    public CarDailyViolationRecordDO clone(){
        try{
            return (CarDailyViolationRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}