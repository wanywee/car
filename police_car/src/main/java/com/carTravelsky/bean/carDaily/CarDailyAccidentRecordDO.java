package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarDailyAccidentRecordDO extends com.stopec.common.orm.BasicDO {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer id;
	/** 车牌号 */
	@Validate(rule = "{required}", comment = "车辆ID")
	private Integer carID;
	/** 驾驶员 */
	@Validate(rule = "{required,maxSize[12]}", comment = "驾驶员")
	private String driver;
	/** 事故日期 */
	@Validate(rule = "{required}", comment = "事故日期")
	private Date accidentDate;
	/** 我方承担金额 */
	@Validate(rule = "{required}", regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "我方承担金额")
	private Float webearMoney;
	/** 对方承担金额 */
	@Validate( regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "对方承担金额")
	private Float opbearMoney;
	/** 保险赔偿金额 */
	@Validate(rule = "{required}", regexp = "^(([0-9]+[\\.]?[0-9]+)|[1-9])$", comment = "保险赔偿金额")
	private Float insuranceMoney;
	/** 发生地点 */
	@Validate(rule = "{required,maxSize[64]}", comment = "发生地点")
	private String happenAddress;
	/** 事故说明 */
	@Validate(rule = "{required,maxSize[64]}", comment = "事故说明")
	private String accidentExplain;
	/** 我方情况 */
	@Validate(rule = "{required,maxSize[64]}", comment = "我方情况")
	private String ourSituation;
	/** 对方情况 */
	@Validate(rule = "{required,maxSize[64]}", comment = "对方情况")
	private String otherSituation;
	/** 处理结果 */
	@Validate(rule = "{required,maxSize[64]}", comment = "处理结果")
	private String treatmentResult;
	/** 所属地区 */
	@Validate(rule = "{maxSize[64]}", comment = "所属地区")
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
	@Validate(rule = "{maxSize[128]}", comment = "备注")
	private String remark;
	/** 删除状态码（1、为存在2为删除） */
	private Integer deleteCode;
	
	/**我方损失*/
	private Double webearLoss;
	/**对方损失*/
	private Double opbearLoss;
	//

	/** 车牌号 */
	private String licenseno;

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
	 * 获取ID
	 * 
	 * @return id
	 **/
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 **/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取车辆ID
	 * 
	 * @return carID
	 **/
	public Integer getCarID() {
		return this.carID;
	}

	/**
	 * 设置车辆ID
	 * 
	 * @param carID
	 **/
	public void setCarID(Integer carID) {
		this.carID = carID;
	}

	/**
	 * 获取驾驶员
	 * 
	 * @return driver
	 **/
	public String getDriver() {
		return this.driver;
	}

	/**
	 * 设置驾驶员
	 * 
	 * @param driver
	 **/
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * 获取事故日期
	 * 
	 * @return accidentDate
	 **/
	public Date getAccidentDate() {
		return this.accidentDate;
	}

	/**
	 * 设置事故日期
	 * 
	 * @param accidentDate
	 **/
	public void setAccidentDate(Date accidentDate) {
		this.accidentDate = accidentDate;
	}

	/**
	 * 获取我方承担金额
	 * 
	 * @return webearMoney
	 **/
	public Float getWebearMoney() {
		return this.webearMoney;
	}

	/**
	 * 设置我方承担金额
	 * 
	 * @param webearMoney
	 **/
	public void setWebearMoney(Float webearMoney) {
		this.webearMoney = webearMoney;
	}

	/**
	 * 获取对方承担金额
	 * 
	 * @return opbearMoney
	 **/
	public Float getOpbearMoney() {
		return this.opbearMoney;
	}

	/**
	 * 设置对方承担金额
	 * 
	 * @param opbearMoney
	 **/
	public void setOpbearMoney(Float opbearMoney) {
		this.opbearMoney = opbearMoney;
	}

	/**
	 * 获取保险赔偿金额
	 * 
	 * @return insuranceMoney
	 **/
	public Float getInsuranceMoney() {
		return this.insuranceMoney;
	}

	/**
	 * 设置保险赔偿金额
	 * 
	 * @param insuranceMoney
	 **/
	public void setInsuranceMoney(Float insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	/**
	 * 获取发生地点
	 * 
	 * @return happenAddress
	 **/
	public String getHappenAddress() {
		return this.happenAddress;
	}

	/**
	 * 设置发生地点
	 * 
	 * @param happenAddress
	 **/
	public void setHappenAddress(String happenAddress) {
		this.happenAddress = happenAddress;
	}

	/**
	 * 获取事故说明
	 * 
	 * @return accidentExplain
	 **/
	public String getAccidentExplain() {
		return this.accidentExplain;
	}

	/**
	 * 设置事故说明
	 * 
	 * @param accidentExplain
	 **/
	public void setAccidentExplain(String accidentExplain) {
		this.accidentExplain = accidentExplain;
	}

	/**
	 * 获取我方情况
	 * 
	 * @return ourSituation
	 **/
	public String getOurSituation() {
		return this.ourSituation;
	}

	/**
	 * 设置我方情况
	 * 
	 * @param ourSituation
	 **/
	public void setOurSituation(String ourSituation) {
		this.ourSituation = ourSituation;
	}

	/**
	 * 获取对方情况
	 * 
	 * @return otherSituation
	 **/
	public String getOtherSituation() {
		return this.otherSituation;
	}

	/**
	 * 设置对方情况
	 * 
	 * @param otherSituation
	 **/
	public void setOtherSituation(String otherSituation) {
		this.otherSituation = otherSituation;
	}

	/**
	 * 获取处理结果
	 * 
	 * @return treatmentResult
	 **/
	public String getTreatmentResult() {
		return this.treatmentResult;
	}

	/**
	 * 设置处理结果
	 * 
	 * @param treatmentResult
	 **/
	public void setTreatmentResult(String treatmentResult) {
		this.treatmentResult = treatmentResult;
	}

	/**
	 * 获取所属地区
	 * 
	 * @return region
	 **/
	public String getRegion() {
		return this.region;
	}

	/**
	 * 设置所属地区
	 * 
	 * @param region
	 **/
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return createTime
	 **/
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 **/
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建人
	 * 
	 * @return createPeople
	 **/
	public String getCreatePeople() {
		return this.createPeople;
	}

	/**
	 * 得到车牌号码
	 * 
	 **/
	public String getLicenseno() {
		return licenseno;
	}

	/**
	 * 设置车牌号码
	 * 
	 * @param createPeople
	 **/
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	/**
	 * 设置创建人
	 * 
	 * @param createPeople
	 **/
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return updateTime
	 **/
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param updateTime
	 **/
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取修改人
	 * 
	 * @return updatePeople
	 **/
	public String getUpdatePeople() {
		return this.updatePeople;
	}

	/**
	 * 设置修改人
	 * 
	 * @param updatePeople
	 **/
	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}

	/**
	 * 获取备注
	 * 
	 * @return remark
	 **/
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 设置备注
	 * 
	 * @param remark
	 **/
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 
	 * 获取删除状态码
	 * 
	 */
	public Integer getDeleteCode() {
		return deleteCode;
	}

	public Double getWebearLoss() {
		return webearLoss;
	}

	public void setWebearLoss(Double webearLoss) {
		this.webearLoss = webearLoss;
	}

	public Double getOpbearLoss() {
		return opbearLoss;
	}

	public void setOpbearLoss(Double opbearLoss) {
		this.opbearLoss = opbearLoss;
	}

	/**
	 * 设置删除状态码
	 * 
	 * @param
	 */
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	public CarDailyAccidentRecordDO clone() {
		try {
			return (CarDailyAccidentRecordDO) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}