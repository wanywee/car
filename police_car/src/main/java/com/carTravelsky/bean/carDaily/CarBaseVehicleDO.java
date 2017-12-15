package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

import com.stopec.common.validate.Validate;

public class CarBaseVehicleDO extends com.stopec.common.orm.BasicDO {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer id;
	/** 车牌号 */
	// regexp = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}-[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$
	@Validate(rule = "{required}", comment = "车牌号")
	private String licenseno;
	/** 载人 */
	@Validate(rule = "{required,min[0]}", regexp="^\\d+$", comment="载人")
	private Integer loading;
	/** 座位数 */
	//@Validate(rule = "{required,min[1]}", regexp="^[0-9]*[1-9][0-9]*$", comment="车辆座位数")
	private Integer seats;
	/** 初始里程 */
	@Validate(rule = "{required},min[0]", regexp="^?\\d+(\\.\\d+)?$", comment="车辆初始里程")
	private Float startMile;
	/** 保养里程 */
	@Validate(rule = "{required},min[0]}", regexp="^?\\d+(\\.\\d+)?$", comment="车辆保养里程")
	private Float mileage;
	/** 保养周期 */
	@Validate(rule = "{required,min[30]}", regexp="^?\\d+(\\.\\d+)?$", comment="车辆保养周期")
	private Float period;
	/** 油耗 */
	@Validate(rule = "{required,min[0]}", regexp="^?\\d+(\\.\\d+)?$", comment="车辆油耗")
	private Float consumption;
	/** 发动机号 */
	@Validate(rule = "{required}", regexp="^[a-zA-Z0-9]{8}$", comment="车辆发动机号")
	private String engineno;
	/** 车架号 */
	@Validate(rule = "{required}", regexp="^[a-zA-Z0-9]{17}$", comment="车辆车架号")
	private String chassisno;
	/** 供应商 */
	private String supply;
	/** 购入价格 */
	@Validate(rule = "{required,min[0]}", regexp="^?\\d+(\\.\\d+)?$", comment="车辆购入价格")
	private Float buyPrice;
	/** 购入时间 */
	private Date buyTime;
	/** 驾驶员 */
	@Validate(rule = "{required}")
	private String dreiver;
	/** 所属部门ID */
	private Integer deptID;
	/** 当前状态1可用2出车3维修4其他 */
	private Integer nowStatus;
	/** 存贮照片路径 */
	private String photoUrl;
	/** 所在地区 */
	private String location;
	/** 是否使用 1 为启用 2为停用 */
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
	/** 车辆类型D */
	private Integer carDetailID;
	/** 公司ID */
	private Integer companyID;
	/**车辆自重*/
	@Validate(rule = "{required},min[0]", regexp="^?\\d+(\\.\\d+)?$", comment="车辆自重")
	private Float carWeight;
	/**当前里程数*/
	private Float currentMileage;
	

	/**
	 * 删除状态码（1为存在，2为删除）
	 */
	private Integer deleteCode;

	/**
	 * 车辆类型属性
	 */
	private String typeName;
	private String modelName;
	private String brandName;
	private String colorName;
	private String deptName;

	/**
	 * 图片路径数组
	 */
	private List<String> photoUrlList;

	//
	/**驾驶员name	 */
	private String dreiverName;
	
	/**
	 * 供应商名字
	 */
	private String supplyName;
	
	/*显示的车辆状态*/
	private String displayStatus;
	/**
	 * 
	 * @Title: getSupplyName
	 * @Description: 获取供应商名字
	 * @return
	 * @return: String
	 * @author: yanlinlong  
	 * @date: 2017年11月3日 下午3:22:56
	 */
	public String getSupplyName() {
		return supplyName;
	}

	/**
	 * 
	 * @Title: setSupplyName
	 * @Description: 设置供应商名字
	 * @param supplyName
	 * @return: void
	 * @author: yanlinlong  
	 * @date: 2017年11月3日 下午3:23:12
	 */
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
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
	 * 获取车牌号
	 * 
	 * @return licenseno
	 **/
	public String getLicenseno() {
		return this.licenseno;
	}

	/**
	 * 设置车牌号
	 * 
	 * @param licenseno
	 **/
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	

	public Integer getLoading() {
		return loading;
	}

	public void setLoading(Integer loading) {
		this.loading = loading;
	}

	/**
	 * 获取座位数
	 * 
	 * @return seats
	 **/
	public Integer getSeats() {
		return this.seats;
	}

	/**
	 * 设置座位数
	 * 
	 * @param seats
	 **/
	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	/**
	 * 获取初始里程
	 * 
	 * @return startMile
	 **/
	public Float getStartMile() {
		return this.startMile;
	}

	/**
	 * 设置初始里程
	 * 
	 * @param startMile
	 **/
	public void setStartMile(Float startMile) {
		this.startMile = startMile;
	}

	/**
	 * 获取保养里程
	 * 
	 * @return mileage
	 **/
	public Float getMileage() {
		return this.mileage;
	}

	/**
	 * 设置保养里程
	 * 
	 * @param mileage
	 **/
	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}

	/**
	 * 获取保养周期
	 * 
	 * @return period
	 **/
	public Float getPeriod() {
		return this.period;
	}

	/**
	 * 设置保养周期
	 * 
	 * @param period
	 **/
	public void setPeriod(Float period) {
		this.period = period;
	}

	/**
	 * 获取油耗
	 * 
	 * @return consumption
	 **/
	public Float getConsumption() {
		return this.consumption;
	}

	/**
	 * 设置油耗
	 * 
	 * @param consumption
	 **/
	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	/**
	 * 获取发动机号
	 * 
	 * @return engineno
	 **/
	public String getEngineno() {
		return this.engineno;
	}

	/**
	 * 设置发动机号
	 * 
	 * @param engineno
	 **/
	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}

	/**
	 * 获取车架号
	 * 
	 * @return chassisno
	 **/
	public String getChassisno() {
		return this.chassisno;
	}

	/**
	 * 设置车架号
	 * 
	 * @param chassisno
	 **/
	public void setChassisno(String chassisno) {
		this.chassisno = chassisno;
	}

	/**
	 * 获取供应商
	 * 
	 * @return supply
	 **/
	public String getSupply() {
		return this.supply;
	}

	/**
	 * 设置供应商
	 * 
	 * @param supply
	 **/
	public void setSupply(String supply) {
		this.supply = supply;
	}

	/**
	 * 获取购入价格
	 * 
	 * @return buyPrice
	 **/
	public Float getBuyPrice() {
		return this.buyPrice;
	}

	/**
	 * 设置购入价格
	 * 
	 * @param buyPrice
	 **/
	public void setBuyPrice(Float buyPrice) {
		this.buyPrice = buyPrice;
	}

	/**
	 * 获取购入时间
	 * 
	 * @return buyTime
	 **/
	public Date getBuyTime() {
		return this.buyTime;
	}

	/**
	 * 设置购入时间
	 * 
	 * @param buyTime
	 **/
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	/**
	 * 获取驾驶员
	 * 
	 * @return dreiver
	 **/
	public String getDreiver() {
		return this.dreiver;
	}

	/**
	 * 设置驾驶员
	 * 
	 * @param dreiver
	 **/
	public void setDreiver(String dreiver) {
		this.dreiver = dreiver;
	}

	/**
	 * 获取所属部门ID
	 * 
	 * @return deptID
	 **/
	public Integer getDeptID() {
		return this.deptID;
	}

	/**
	 * 设置所属部门ID
	 * 
	 * @param deptID
	 **/
	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	/**
	 * 获取当前状态1可用2出车3维修4其他
	 * 
	 * @return nowStatus
	 **/
	public Integer getNowStatus() {
		return this.nowStatus;
	}

	/**
	 * 设置当前状态1可用2出车3维修4其他
	 * 
	 * @param nowStatus
	 **/
	public void setNowStatus(Integer nowStatus) {
		this.nowStatus = nowStatus;
	}

	/**
	 * 获取存贮照片路径
	 * 
	 * @return photoUrl
	 **/
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	/**
	 * 设置存贮照片路径
	 * 
	 * @param photoUrl
	 **/
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * 获取所在地区
	 * 
	 * @return location
	 **/
	public String getLocation() {
		return this.location;
	}

	/**
	 * 设置所在地区
	 * 
	 * @param location
	 **/
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 获取是否使用 1 为启用 2为停用
	 * 
	 * @return status
	 **/
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置是否使用 1 为启用 2为停用
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
	 * 获取车辆类型D
	 * 
	 * @return carDetailID
	 **/
	public Integer getCarDetailID() {
		return this.carDetailID;
	}

	/**
	 * 设置车辆类型D
	 * 
	 * @param carDetailID
	 **/
	public void setCarDetailID(Integer carDetailID) {
		this.carDetailID = carDetailID;
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
	 * 
	 * @Title: getCarTypeName
	 * @Description: 获取车辆类型
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月13日 下午11:43:41
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 
	 * @Title: setCarTypeName
	 * @Description: 设置车辆类型
	 * @param carTypeName
	 * @return: void
	 * @author: yanlinlung
	 * @date: 2017年10月13日 下午11:44:06
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 
	 * @Title: getCarModelName
	 * @Description: 获取车辆型号
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月14日 下午12:03:57
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * 
	 * @Title: setCarModelName
	 * @Description: 设置车辆型号你
	 * @param carModelName
	 * @return: void
	 * @author: yanlinlung
	 * @date: 2017年10月14日 下午12:04:22
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * 
	 * @Title: getBrandName
	 * @Description: 获取品牌
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:22:27
	 */
	public String getBrandName() {
		return brandName;
	}

	/**
	 * 
	 * @Title: setBrandName
	 * @Description: 设置品牌
	 * @param brandName
	 * @return: void
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:22:44
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	/**
	 * 
	 * @Title: getColorName
	 * @Description: 获取车辆颜色
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:30:52
	 */
	public String getColorName() {
		return colorName;
	}

	/**
	 * 
	 * @Title: setColorName
	 * @Description: 设置车辆颜色
	 * @param colorName
	 * @return: void
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:31:04
	 */
	public void setColorName(String colorName) {
		this.colorName = colorName;
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
	 * @Description: 设置删除状态码（1为存在，2删除）
	 * @param deleteCode
	 * @return: void
	 */
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	/**
	 * 
	 * @Title: getPhotoUrlList
	 * @Description: 获取图片数组
	 * @return
	 * @return: List<String>
	 */
	public List<String> getPhotoUrlList() {
		return photoUrlList;
	}

	/**
	 * 
	 * @Title: setPhotoUrlList
	 * @Description: 设置图片数组
	 * @param photoUrlList
	 * @return: void
	 */
	public void setPhotoUrlList(List<String> photoUrlList) {
		this.photoUrlList = photoUrlList;
	}

	public String getDreiverName() {
		return dreiverName;
	}

	public void setDreiverName(String dreiverName) {
		this.dreiverName = dreiverName;
	}

	public Float getCarWeight() {
		return carWeight;
	}

	public void setCarWeight(Float carWeight) {
		this.carWeight = carWeight;
	}
	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public CarBaseVehicleDO clone() {
		try {
			return (CarBaseVehicleDO) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String getDisplayStatus() {
		return displayStatus;
	}

	public void setDisplayStatus(String displayStatus) {
		this.displayStatus = displayStatus;
	}

	public Float getCurrentMileage() {
		return currentMileage;
	}

	public void setCurrentMileage(Float currentMileage) {
		this.currentMileage = currentMileage;
	}

	
}