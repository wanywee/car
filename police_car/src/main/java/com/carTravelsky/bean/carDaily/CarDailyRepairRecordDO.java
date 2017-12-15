package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

import com.stopec.common.validate.Validate;

public class CarDailyRepairRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    private Integer carID;
    /** 经手人 */
    @Validate(rule = "{required}", comment = "经手人")
    private String handler;
    /** 送修日期 */
    @Validate(rule = "{required}", comment = "送修日期")
    private Date repairTime;
    /** 预计取车 */
    @Validate(rule = "{required}", comment = "预计取车")
    private Date estimateTackcar;
    /** 送修原因 */
    private String repairReason;
    /** 所属地区 */
    private String region;
    /** 修理厂 */
    private String repairPd;
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
    
    /**
     * 车牌号
     */
    @Validate(rule = "", regexp = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}-[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$", comment = "车牌号")
    private String licenseno;
    /**
     * 删除状态码
     */
    private Integer deleteCode;
    
    //
    /**职员名称*/
    private String staffName;
    
    /**修理厂名称*/
    private String repairPdName;
    
    /** 存贮照片路径 */
	private String photoUrl;
	
	/**
	 * 图片路径数组
	 */
	private List<String> photoUrlList;
	
    
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
     * 
     * @Title: getDeleteCode
     * @Description: 获取删除状态码
     * @return
     * @return: Integer
     */
    public Integer getDeleteCode() {
		return deleteCode;
	}

    /**
     * 
     * @Title: setDeleteCode
     * @Description: 设置删除状态码
     * @param deleteCode
     * @return: void
     */
	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	/**
     * 
     * @Title: getLicenseno
     * @Description: 获取车牌号
     * @return
     * @return: String
     */
    public String getLicenseno() {
		return licenseno;
	}

    /**
     * 
     * @Title: setLicenseno
     * @Description: 设置车牌号
     * @param licenseno
     * @return: void
     */
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
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
     * 获取送修日期
     * @return repairTime
     **/
    public Date getRepairTime(){
        return this.repairTime;
    }

    /**
     * 设置送修日期
     * @param repairTime
     **/
    public void setRepairTime(Date repairTime){
        this.repairTime = repairTime;
    }

    /**
     * 获取预计取车
     * @return estimateTackcar
     **/
    public Date getEstimateTackcar(){
        return this.estimateTackcar;
    }

    /**
     * 设置预计取车
     * @param estimateTackcar
     **/
    public void setEstimateTackcar(Date estimateTackcar){
        this.estimateTackcar = estimateTackcar;
    }

    /**
     * 获取送修原因
     * @return repairReason
     **/
    public String getRepairReason(){
        return this.repairReason;
    }

    /**
     * 设置送修原因
     * @param repairReason
     **/
    public void setRepairReason(String repairReason){
        this.repairReason = repairReason;
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
     * 获取修理厂
     * @return repairPd
     **/
    public String getRepairPd(){
        return this.repairPd;
    }

    /**
     * 设置修理厂
     * @param repairPd
     **/
    public void setRepairPd(String repairPd){
        this.repairPd = repairPd;
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


    public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getRepairPdName() {
		return repairPdName;
	}

	public void setRepairPdName(String repairPdName) {
		this.repairPdName = repairPdName;
	}

	public CarDailyRepairRecordDO clone(){
        try{
            return (CarDailyRepairRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}