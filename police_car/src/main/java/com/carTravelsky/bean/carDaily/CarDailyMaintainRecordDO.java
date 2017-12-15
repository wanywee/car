package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

public class CarDailyMaintainRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    private Integer carID;
    /** 车辆号 */
    private String licenseno;
    /** 保养类型 */
    private String maintainType;
    /** 保养日期 */
    private Date maintainDate;
    /** 到期日期 */
    private Date endDate;
    /**到期状态标志*/
    private int status;
    /** 图片路径数组 */
    private List<String> photoUrlList;
    /** 存贮照片路径 */
	private String photoUrl;
    

	/** 保养单位ID */
    private Integer maintainUnit;
    /** 保养单位 */
    private String maintainUnitname;
    /** 保养里程 */
    private Float maintainMileage;
    /** 所属地区 */
    private String region;
    /** 保养费用 */
    private Float maintainMoney;
    /** 保养内容 */
    private String maintainContent;
    /** 经手人id */
    private Integer handleid ;
    /** 经手人姓名 */
    private String handlename;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String createPeople;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人 */
    private String updatePeople;
    /** 状态 */
    private String deleteCode;
    /** 备注 */
    private String remark;
    /** 公司id */
    private Integer companyId;

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

    public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	/**
     * 获取保养类型
     * @return maintainType
     **/
    public String getMaintainType(){
        return this.maintainType;
    }

    /**
     * 设置保养类型
     * @param maintainType
     **/
    public void setMaintainType(String maintainType){
        this.maintainType = maintainType;
    }

    /**
     * 获取保养日期
     * @return maintainDate
     **/
    public Date getMaintainDate(){
        return this.maintainDate;
    }

    /**
     * 设置保养日期
     * @param maintainDate
     **/
    public void setMaintainDate(Date maintainDate){
        this.maintainDate = maintainDate;
    }

	public Integer getMaintainUnit() {
		return maintainUnit;
	}

	public void setMaintainUnit(Integer maintainUnit) {
		this.maintainUnit = maintainUnit;
	}

	public String getMaintainUnitname() {
		return maintainUnitname;
	}

	public void setMaintainUnitname(String maintainUnitname) {
		this.maintainUnitname = maintainUnitname;
	}

	public List<String> getPhotoUrlList() {
		return photoUrlList;
	}

	public void setPhotoUrlList(List<String> photoUrlList) {
		this.photoUrlList = photoUrlList;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
     * 获取保养里程
     * @return maintainMileage
     **/
    public Float getMaintainMileage(){
        return this.maintainMileage;
    }

    /**
     * 设置保养里程
     * @param maintainMileage
     **/
    public void setMaintainMileage(Float maintainMileage){
        this.maintainMileage = maintainMileage;
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
     * 获取保养费用
     * @return maintainMoney
     **/
    public Float getMaintainMoney(){
        return this.maintainMoney;
    }

    /**
     * 设置保养费用
     * @param maintainMoney
     **/
    public void setMaintainMoney(Float maintainMoney){
        this.maintainMoney = maintainMoney;
    }

    /**
     * 获取保养内容
     * @return maintainContent
     **/
    public String getMaintainContent(){
        return this.maintainContent;
    }

    /**
     * 设置保养内容
     * @param maintainContent
     **/
    public void setMaintainContent(String maintainContent){
        this.maintainContent = maintainContent;
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
    
    public Integer getHandleid() {
		return handleid;
	}

	public void setHandleid(Integer handleid) {
		this.handleid = handleid;
	}

	public String getHandlename() {
		return handlename;
	}

	public void setHandlename(String handlename) {
		this.handlename = handlename;
	}

	/**
     * 设置备注
     * @param remark
     **/
    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getDeleteCode() {
		return deleteCode;
	}

	public void setDeleteCode(String deleteCode) {
		this.deleteCode = deleteCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public CarDailyMaintainRecordDO clone(){
        try{
            return (CarDailyMaintainRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}