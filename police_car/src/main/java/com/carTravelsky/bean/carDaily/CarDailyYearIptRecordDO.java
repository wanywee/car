package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

import com.stopec.common.validate.Validate;

public class CarDailyYearIptRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    @Validate(rule = "{required,maxSize[11]}", comment = "车辆ID")
    private Integer carID;
    /** 年检号 */
    @Validate(rule = "{required,maxSize[32]}", comment = "年检号")
    private String yearIptNumber;
    /** 经手人 */
    @Validate(rule = "{required,maxSize[12]}", comment = "经手人")
    private String handler;
    /** 年检费用 */
    @Validate(rule = "{required}",regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "年检费用")
    private Float yearIptMoney;
    /** 年检日期 */
    @Validate(rule = "{required}", comment = "年检日期")
    private Date yearIptDate;
    /** 到期日期 */
    @Validate(rule = "{required}", comment = "到期日期")
    private Date endDate;
    /** 所属地区 */
    private String region;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    @Validate(rule = "{maxSize[8]}", comment = "创建人")
    private String createPeople;
    
    /** 修改时间 */
    private Date updateTime;
    
    /** 修改人 */
    @Validate(rule = "{maxSize[8]}", comment = "修改人")
    private String updatePeople;
    
    /** 备注 */
    private String remark;
    
    /** 往来单位ID */
    @Validate(rule = "{required,maxSize[11]}", comment = "往来单位ID")
    private Integer unitID;
    
	/** 删除状态码（1、为存在2为删除） */
	private Integer deleteCode;
	
	/** 存贮照片路径 */
	private String photoUrl;
	
	/**
	 * 图片路径数组
	 */
	private List<String> photoUrlList;
	
	
   
	/** 车管所 */
	@Validate(rule = "{required,maxSize[20]}", comment = "车管所")
    private  String vehicleManage;
	
	/** 过期提醒状态码 */
	private   Integer status;
	
    /** 车牌号码 */
    private  String licenseno;
    
    /** 往来单位名称 */
    private String companyName;
    
    /** 职员姓名 */
	private String staffName;

	/**
	 * 获取驾驶员姓名
	 * 
	 * @return staffName
	 **/
	public String getStaffName() {
		return staffName;
	}

	/**
	 * 设置驾驶员姓名
	 * 
	 **/
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	/**
	 * 获取往来单位名称
	 * 
	 **/
    public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置往来单位名称
	 * 
	 **/
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
     * 获取年检号
     * @return yearIptNumber
     **/
    public String getYearIptNumber(){
        return this.yearIptNumber;
    }

    /**
     * 设置年检号
     * @param yearIptNumber
     **/
    public void setYearIptNumber(String yearIptNumber){
        this.yearIptNumber = yearIptNumber;
    }

    /**
     * 获取经手人
     * @return handler
     **/
    public String getHandler(){
        return this.handler;
    }

    /**
     * 设置经手人
     * @param handler
     **/
    public void setHandler(String handler){
        this.handler = handler;
    }

    /**
     * 获取年检费用
     * @return yearIptMoney
     **/
    public Float getYearIptMoney(){
        return this.yearIptMoney;
    }

    /**
     * 设置年检费用
     * @param yearIptMoney
     **/
    public void setYearIptMoney(Float yearIptMoney){
        this.yearIptMoney = yearIptMoney;
    }

    /**
     * 获取年检日期
     * @return yearIptDate
     **/
    public Date getYearIptDate(){
        return this.yearIptDate;
    }

    /**
     * 设置年检日期
     * @param yearIptDate
     **/
    public void setYearIptDate(Date yearIptDate){
        this.yearIptDate = yearIptDate;
    }

    /**
     * 获取到期日期
     * @return endDate
     **/
    public Date getEndDate(){
        return this.endDate;
    }

    /**
     * 设置到期日期
     * @param endDate
     **/
    public void setEndDate(Date endDate){
        this.endDate = endDate;
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
     * @return updatePeople
     **/
    public String getUpdatePeople(){
        return this.updatePeople;
    }

    /**
     * 设置修改人
     * @param updatePeople
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
     * 获取往来单位ID
     * @return unitID
     **/
    public Integer getUnitID(){
        return this.unitID;
    }

    /**
     * 设置往来单位ID
     * @param unitID
     **/
    public void setUnitID(Integer unitID){
        this.unitID = unitID;
    }

    /**
	 * 
	 * 获取删除状态码
	 * 
	 */
	public Integer getDeleteCode() {
		return deleteCode;
	}

	/**
	 * 设置删除状态码
	 * 
	 * @param
	 */
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}
    
    
	 /**
	 * 返回图片存储路径
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * 设置图片存储路径
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * 返回图片路径数组
	 */
	public List<String> getPhotoUrlList() {
		return photoUrlList;
	}

	/**
	 * 设置图片路径数组
	 */
	public void setPhotoUrlList(List<String> photoUrlList) {
		this.photoUrlList = photoUrlList;
	}

	/**
     * 获取车牌号码
     * @param unitID
     **/
	public String getLicenseno() {
		return licenseno;
	}
	 /**
     * 设置车牌号码
     * @param unitID
     **/
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	/**
     * 获取车管所
     * @param unitID
     **/
	public String getVehicleManage() {
		return vehicleManage;
	}
	/**
     * 设置车管所
     * @param unitID
     **/
	public void setVehicleManage(String vehicleManage) {
		this.vehicleManage = vehicleManage;
	}
	/**
     * 设置过期提醒状态码
     * 
     **/
	public Integer getStatus() {
		return status;
	}

	/**
     * 设置过期提醒状态码
     * @param status
     * 
     **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	public CarDailyYearIptRecordDO clone(){
        try{
            return (CarDailyYearIptRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}