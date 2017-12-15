package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarDailyFeeManageRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    private Integer carID;
    /** 费用名称 */
    @Validate(rule = "{required,maxSize[20]}",comment="费用名称")
    private String moneyname;
    /** 交费金额 */
    @Validate(rule = "{required}", regexp="^?\\d+(\\.\\d+)?$", comment="交费金额")
    private Float parmentMoney;
    /** 交费日期 */
    @Validate(rule = "{required}",comment="交费日期")
    private Date paymentDate;
    /**到期日期*/
    @Validate(rule = "{required}",comment="到期日期")
    private Date endDate;
    /** 收费单位(往来单位ID) */
    private Integer chargeUnitID;
    /** 交费方式 */
    @Validate(rule = "{required}",comment="交费方式")
    private String paymentType;
    /** 所属地区 */
    private String region;
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
    /**往来单位名称**/
    private String chargeUnitName;
    /**车辆名称**/
    private String licenseNo;
    /***经办人**/
    @Validate(rule = "{required}",comment="经办人")
    private String handler;
    /***周期**/
    private String period;
    /**临时Id**/
    private Integer brevityId;
    /**公司ID**/
    private Integer companyID;
    /**到期状态提醒码*/
    private Integer status;
    
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}


	/**
     * 经手人姓名
     */
    private String staffName;
    
    /**
     * 
     * @Title: getStaffName
     * @Description: 获取经手人name
     * @return
     * @return: String
     * @author: yanlinlong  
     * @date: 2017年11月13日 下午3:44:41
     */
    public String getStaffName() {
		return staffName;
	}

    /**
     * 
     * @Title: setStaffName
     * @Description: 设置经手人name
     * @param staffName
     * @return: void
     * @author: yanlinlong  
     * @date: 2017年11月13日 下午3:44:57
     */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getBrevityId() {
		return brevityId;
	}

	public void setBrevityId(Integer brevityId) {
		this.brevityId = brevityId;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getChargeUnitName() {
		return chargeUnitName;
	}

	public void setChargeUnitName(String chargeUnitName) {
		this.chargeUnitName = chargeUnitName;
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
     * 获取费用名称
     * @return moneyname
     **/
    public String getMoneyname(){
        return this.moneyname;
    }

    /**
     * 设置费用名称
     * @param moneyname
     **/
    public void setMoneyname(String moneyname){
        this.moneyname = moneyname;
    }

    /**
     * 获取交费金额
     * @return parmentMoney
     **/
    public Float getParmentMoney(){
        return this.parmentMoney;
    }

    /**
     * 设置交费金额
     * @param parmentMoney
     **/
    public void setParmentMoney(Float parmentMoney){
        this.parmentMoney = parmentMoney;
    }

   

    public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
     * 获取收费单位(往来单位ID)
     * @return chargeUnitID
     **/
    public Integer getChargeUnitID(){
        return this.chargeUnitID;
    }

    /**
     * 设置收费单位(往来单位ID)
     * @param chargeUnitID
     **/
    public void setChargeUnitID(Integer chargeUnitID){
        this.chargeUnitID = chargeUnitID;
    }

    /**
     * 获取交费方式
     * @return paymentType
     **/
    public String getPaymentType(){
        return this.paymentType;
    }

    /**
     * 设置交费方式
     * @param paymentType
     **/
    public void setPaymentType(String paymentType){
        this.paymentType = paymentType;
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


    public CarDailyFeeManageRecordDO clone(){
        try{
            return (CarDailyFeeManageRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}