package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

import com.stopec.common.validate.Validate;

public class CarDailyAddoilRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 加油站id */
    private Integer unitID;
    /** 加油站名称 */
    private String petrolstation;
    /** 车辆ID */
    private Integer carID;
    /** 车牌号 */
    private String licenseno;
    /** 油料标号ID */
    @Validate(rule="{required}",comment="油料标号ID")
    private Integer oilseedLableID;
    /** 油料标号名称 */
    private String oilseedLablename;
    /** 单价 */
    @Validate(rule="{required,maxSize[32]}",comment="单价")
    private Float price;
    /** 加油量 */
    @Validate(rule="{required,maxSize[32]}",comment="加油量")
    private Float addiolAmount;
    /** 总金额 */
    @Validate(rule="{required,maxSize[32]}",comment="总金额")
    private Float summoney;
    /** 加油日期 */
    @Validate(rule="{required}",comment="加油日期")
    private Date addoilTime;
    /** 上次里程 */
    @Validate(rule="{required,maxSize[32]}",comment="上次里程")
    private Float lastMileage;
    /** 上次行程 */
    @Validate(rule="{required,maxSize[32]}",comment="上次行程")
    private Float lastTrip;
    /** 上次油量 */
    @Validate(rule="{required,maxSize[32]}",comment="上次油量")
    private Float lastOil;
    /** 上次油耗 */
    @Validate(rule="{required,maxSize[32]}",comment="上次油耗")
    private Float lastOilConsunption;
    /** 本次里程 */
    @Validate(rule="{required,maxSize[32]}",comment="本次里程")
    private Float mileage;
    /** 所属地区 */
    @Validate(rule="{required,maxSize[32]}",comment="所属地区")
    private String region;
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
    /** 备注 */
    private String remark;
    /** 状态 */
    private Integer deleteCode;
    /** 公司id */
    private Integer companyId;
    /**存储图片的路径*/
    private String photoUrl;
    /**多个图片路径的数组*/
    private List<String> photoUrlList;
    

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
     * 获取
     * @return unitID
     **/
    public Integer getUnitID(){
        return this.unitID;
    }

    /**
     * 设置
     * @param unitID
     **/
    public void setUnitID(Integer unitID){
        this.unitID = unitID;
    }

    public String getPetrolstation() {
		return petrolstation;
	}

	public void setPetrolstation(String petrolstation) {
		this.petrolstation = petrolstation;
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
     * 获取油料标号ID
     * @return oilseedLableID
     **/
    public Integer getOilseedLableID(){
        return this.oilseedLableID;
    }

    /**
     * 设置油料标号ID
     * @param oilseedLableID
     **/
    public void setOilseedLableID(Integer oilseedLableID){
        this.oilseedLableID = oilseedLableID;
    }

    public String getOilseedLablename() {
		return oilseedLablename;
	}

	public void setOilseedLablename(String oilseedLablename) {
		this.oilseedLablename = oilseedLablename;
	}

	/**
     * 获取单价
     * @return price
     **/
    public Float getPrice(){
        return this.price;
    }

    /**
     * 设置单价
     * @param price
     **/
    public void setPrice(Float price){
        this.price = price;
    }

    /**
     * 获取加油量
     * @return addiolAmount
     **/
    public Float getAddiolAmount(){
        return this.addiolAmount;
    }

    /**
     * 设置加油量
     * @param addiolAmount
     **/
    public void setAddiolAmount(Float addiolAmount){
        this.addiolAmount = addiolAmount;
    }

    /**
     * 获取总金额
     * @return summoney
     **/
    public Float getSummoney(){
        return this.summoney;
    }

    /**
     * 设置总金额
     * @param summoney
     **/
    public void setSummoney(Float summoney){
        this.summoney = summoney;
    }

    /**
     * 获取上次里程
     * @return lastMileage
     **/
    public Float getLastMileage(){
        return this.lastMileage;
    }

    /**
     * 设置上次里程
     * @param lastMileage
     **/
    public void setLastMileage(Float lastMileage){
        this.lastMileage = lastMileage;
    }

    /**
     * 获取上次行程
     * @return lastTrip
     **/
    public Float getLastTrip(){
        return this.lastTrip;
    }

    /**
     * 设置上次行程
     * @param lastTrip
     **/
    public void setLastTrip(Float lastTrip){
        this.lastTrip = lastTrip;
    }

    /**
     * 获取上次油量
     * @return lastOil
     **/
    public Float getLastOil(){
        return this.lastOil;
    }

    /**
     * 设置上次油量
     * @param lastOil
     **/
    public void setLastOil(Float lastOil){
        this.lastOil = lastOil;
    }

    /**
     * 获取上次油耗
     * @return lastOilConsunption
     **/
    public Float getLastOilConsunption(){
        return this.lastOilConsunption;
    }

    /**
     * 设置上次油耗
     * @param lastOilConsunption
     **/
    public void setLastOilConsunption(Float lastOilConsunption){
        this.lastOilConsunption = lastOilConsunption;
    }

    /**
     * 获取本次里程
     * @return mileage
     **/
    public Float getMileage(){
        return this.mileage;
    }

    /**
     * 设置本次里程
     * @param mileage
     **/
    public void setMileage(Float mileage){
        this.mileage = mileage;
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

    
    public Date getAddoilTime() {
		return addoilTime;
	}

	public void setAddoilTime(Date addoilTime) {
		this.addoilTime = addoilTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteCode() {
		return deleteCode;
	}

	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * 返回图片路径
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * 设置图片路径
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

	public CarDailyAddoilRecordDO clone(){
        try{
            return (CarDailyAddoilRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}