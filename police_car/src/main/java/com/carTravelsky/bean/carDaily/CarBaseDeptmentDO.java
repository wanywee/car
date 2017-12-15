package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseDeptmentDO extends com.stopec.common.orm.BasicDO {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer id;
	/** 所属区域 */
	private String location;
	/** 部门编码 */
	private String deptCode;
	/** 部门名称 */
	@Validate(rule = "{required}", comment = "部门名称")
	private String deptName;
	/** 助记码 */
	@Validate(rule = "{required}", regexp = "^[a-zA-Z0-9\u4E00-\u9FA5]+$", comment = "部门助记码")
	private String mnemonicCode;
	/** 负责人 */
	private String principal;
	/** 部门电话 */
	@Validate(rule = "{required}", regexp = "^1[3|4|5|7|8][0-9]{9}$|^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$", comment = "部门电话")
	private String deptCall;
	/** 是否使用 1 是启用 2是停用 */
	private Integer status;
	/** 创建时间 */
	private Date createTime;
	/** 创建人员 */
	private String creator;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人员 */
	private String updateOperator;
	/** 备注 */
	private String comments;
	/** 关联公司ID */
	private Integer companyID;
	
	@Validate(rule = "{required}", comment = "上级部门")
	private Integer deptParentID;
	
	private Integer level;
	private Boolean isLeaf;
	
	private  Boolean expanded;
	
	//用于查询排查当前ID记录使用 
	private Integer exceptId;

	
	
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getExpanded() {
		return true;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * 删除状态码（1为存在，2为删除）
	 */
	private Integer deleteCode;
	
	/**
	 * 负责人姓名
	 */
	private String principalName;

	/**
	 * 
	 * @Title: getPrincipalName
	 * @Description: 获取负责人姓名
	 * @return
	 * @return: String
	 * @author: yanlinlong  
	 * @date: 2017年11月3日 上午9:41:09
	 */
	public String getPrincipalName() {
		return principalName;
	}

	/**
	 * 
	 * @Title: setPrincipalName
	 * @Description: 设置负责人姓名
	 * @param principalName
	 * @return: void
	 * @author: yanlinlong  
	 * @date: 2017年11月3日 上午9:41:24
	 */
	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
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
	 * 获取所属区域
	 * 
	 * @return location
	 **/
	public String getLocation() {
		return this.location;
	}

	/**
	 * 设置所属区域
	 * 
	 * @param location
	 **/
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 
	 * @Title: getDeptCode
	 * @Description: 获取部门编码
	 * @return
	 * @return: String
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * 
	 * @Title: setDeptCode
	 * @Description: 设置部门编码
	 * @param deptCode
	 * @return: void
	 */
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	/**
	 * 获取部门名称
	 * 
	 * @return deptName
	 **/
	public String getDeptName() {
		return this.deptName;
	}

	/**
	 * 设置部门名称
	 * 
	 * @param deptName
	 **/
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	 * 获取负责人
	 * 
	 * @return principal
	 **/
	public String getPrincipal() {
		return this.principal;
	}

	/**
	 * 设置负责人
	 * 
	 * @param principal
	 **/
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * 获取部门电话
	 * 
	 * @return deptCall
	 **/
	public String getDeptCall() {
		return this.deptCall;
	}

	/**
	 * 设置部门电话
	 * 
	 * @param deptCall
	 **/
	public void setDeptCall(String deptCall) {
		this.deptCall = deptCall;
	}

	/**
	 * 获取是否使用 1 是启用 2是停用
	 * 
	 * @return status
	 **/
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置是否使用 1 是启用 2是停用
	 * 
	 * @param status
	 **/
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 获取创建人员
	 * 
	 * @return creator
	 **/
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置创建人员
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
	 * 获取修改人员
	 * 
	 * @return updateOperator
	 **/
	public String getUpdateOperator() {
		return this.updateOperator;
	}

	/**
	 * 设置修改人员
	 * 
	 * @param updateOperator
	 **/
	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
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
	 * 获取关联公司ID
	 * 
	 * @return companyID
	 **/
	public Integer getCompanyID() {
		return this.companyID;
	}

	/**
	 * 设置关联公司ID
	 * 
	 * @param companyID
	 **/
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	/**
	 * 
	 * @Title: getDeleteCode
	 * @Description: 获取删除状态码（1为存在，2为删除）
	 * @return
	 * @return: Integer
	 */
	public Integer getDeleteCode() {
		return deleteCode;
	}

	/**
	 * 
	 * @Title: setDeleteCode
	 * @Description: 设置删除状态码（1存在，2删除）
	 * @param deleteCode
	 * @return: void
	 */
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	public CarBaseDeptmentDO clone() {
		try {
			return (CarBaseDeptmentDO) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getDeptParentID() {
		return deptParentID;
	}

	public void setDeptParentID(Integer deptParentID) {
		this.deptParentID = deptParentID;
	}

	public Integer getExceptId() {
		return exceptId;
	}

	public void setExceptId(Integer exceptId) {
		this.exceptId = exceptId;
	}
}