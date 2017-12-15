package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarBaseDriverDO extends com.stopec.common.orm.BasicDO {
	private static final long serialVersionUID = 1L;
	/** 驾驶员编号 */
	private Integer id;
	/** 助记码 */
	private String mnemonicCode;
	/** 创建时间 */
	private Date createTime;
	/** 创建人 */
	private String creator;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人 */
	private String updateOperator;
	/** 驾驶员类型1为兼职 2为专职 */
	private Integer driverType;
	/** 专业职称 */
	private String professional;
	/** 从业证 */
	private String fromThe;
	/** 驾驶区域 */
	private String driveArea;
	/** 是否使用1为启用 2为停用 */
	private Integer status;
	/** 备注 */
	private String comments;
	/** 职员ID */
	private Integer workID;
	/** 公司ID */
	private Integer companyID;
	/** 司机状态：1空闲中 2工作中3休假中 */
	private Integer currStatus;

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
	 * 获取驾驶员编号
	 * 
	 * @return id
	 **/
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置驾驶员编号
	 * 
	 * @param id
	 **/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取助记码
	 * 
	 * @return mnemonicCode
	 **/
	public String getMnemonicCode() {
		return this.mnemonicCode;
	}

	/**
	 * 设置助记码
	 * 
	 * @param mnemonicCode
	 **/
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
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
	 * @return creator
	 **/
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置创建人
	 * 
	 * @param creator
	 **/
	public void setCreator(String creator) {
		this.creator = creator;
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
	 * @return updateOperator
	 **/
	public String getUpdateOperator() {
		return this.updateOperator;
	}

	/**
	 * 设置修改人
	 * 
	 * @param updateOperator
	 **/
	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
	}

	/**
	 * 获取驾驶员类型1为兼职 2为专职
	 * 
	 * @return driverType
	 **/
	public Integer getDriverType() {
		return this.driverType;
	}

	/**
	 * 设置驾驶员类型1为兼职 2为专职
	 * 
	 * @param driverType
	 **/
	public void setDriverType(Integer driverType) {
		this.driverType = driverType;
	}

	/**
	 * 获取专业职称
	 * 
	 * @return professional
	 **/
	public String getProfessional() {
		return this.professional;
	}

	/**
	 * 设置专业职称
	 * 
	 * @param professional
	 **/
	public void setProfessional(String professional) {
		this.professional = professional;
	}

	/**
	 * 获取从业证
	 * 
	 * @return fromThe
	 **/
	public String getFromThe() {
		return this.fromThe;
	}

	/**
	 * 设置从业证
	 * 
	 * @param fromThe
	 **/
	public void setFromThe(String fromThe) {
		this.fromThe = fromThe;
	}

	/**
	 * 获取驾驶区域
	 * 
	 * @return driveArea
	 **/
	public String getDriveArea() {
		return this.driveArea;
	}

	/**
	 * 设置驾驶区域
	 * 
	 * @param driveArea
	 **/
	public void setDriveArea(String driveArea) {
		this.driveArea = driveArea;
	}

	/**
	 * 获取是否使用1为启用 2为停用
	 * 
	 * @return status
	 **/
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置是否使用1为启用 2为停用
	 * 
	 * @param status
	 **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取备注
	 * 
	 * @return comments
	 **/
	public String getComments() {
		return this.comments;
	}

	/**
	 * 设置备注
	 * 
	 * @param comments
	 **/
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 获取职员ID
	 * 
	 * @return workID
	 **/
	public Integer getWorkID() {
		return this.workID;
	}

	/**
	 * 设置职员ID
	 * 
	 * @param workID
	 **/
	public void setWorkID(Integer workID) {
		this.workID = workID;
	}

	/**
	 * 获取公司ID
	 * 
	 * @return companyID
	 **/
	public Integer getCompanyID() {
		return this.companyID;
	}

	/**
	 * 设置公司ID
	 * 
	 * @param companyID
	 **/
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	/**
	 * 获取司机状态：1空闲中 2工作中3休假中
	 * 
	 * @return currStatus
	 **/
	public Integer getCurrStatus() {
		return this.currStatus;
	}

	/**
	 * 设置司机状态：1空闲中 2工作中3休假中
	 * 
	 * @param currStatus
	 **/
	public void setCurrStatus(Integer currStatus) {
		this.currStatus = currStatus;
	}

	public CarBaseDriverDO clone() {
		try {
			return (CarBaseDriverDO) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}