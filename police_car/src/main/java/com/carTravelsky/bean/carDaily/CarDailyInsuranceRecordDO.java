package com.carTravelsky.bean.carDaily;

import java.util.Date;
import java.util.List;

public class CarDailyInsuranceRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    private Integer carID;
    /** 车牌号 */
    private String licenseno;
    /** 保单号 */
    private String policyNumber;
    /** 经手人 */
    private Integer handler;
    /** 经手人姓名 */
    private String handlename;
    /** 投保金额 */
    private Float insureMoney;
    /** 投保日期 */
    private Date insureDate;
    /** 到期日期 */
    private Date endDate;
    /** 保险种类 */
    private Integer insuranceType;
    /** 保险种类名称 */
    private String insuranceTypeNm;
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
    /** 险种备注 */
    private String insuranceRemark;
    /** 保险公司(往来单位ID) */
    private Integer insuranceCpID;
    /** 保险公司名称 */
    private String insuranceCpNm;
    /** 所属公司id */
    private Integer companyid;
    
    /** 过期提醒状态码 */
	private   Integer status;
	 /** 图片路径数组 */
    private List<String> photoUrlList;
    /** 存贮照片路径 */
	private String photoUrl;
    

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
     * 获取保单号
     * @return policyNumber
     **/
    public String getPolicyNumber(){
        return this.policyNumber;
    }

    /**
     * 设置保单号
     * @param policyNumber
     **/
    public void setPolicyNumber(String policyNumber){
        this.policyNumber = policyNumber;
    }

    /**
     * 获取经手人
     * @return handler
     **/
    public Integer getHandler(){
        return this.handler;
    }

    /**
     * 设置经手人
     * @param handler
     **/
    public void setHandler(Integer handler){
        this.handler = handler;
    }

    /**
     * 获取投保金额
     * @return insureMoney
     **/
    public Float getInsureMoney(){
        return this.insureMoney;
    }

    /**
     * 设置投保金额
     * @param insureMoney
     **/
    public void setInsureMoney(Float insureMoney){
        this.insureMoney = insureMoney;
    }

    /**
     * 获取投保日期
     * @return insureDate
     **/
    public Date getInsureDate(){
        return this.insureDate;
    }

    /**
     * 设置投保日期
     * @param insureDate
     **/
    public void setInsureDate(Date insureDate){
        this.insureDate = insureDate;
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
     * 获取保险种类
     * @return insuranceType
     **/
    public Integer getInsuranceType(){
        return this.insuranceType;
    }

    /**
     * 设置保险种类
     * @param insuranceType
     **/
    public void setInsuranceType(Integer insuranceType){
        this.insuranceType = insuranceType;
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
     * 获取保险公司(往来单位ID)
     * @return insuranceCpID
     **/
    public Integer getInsuranceCpID(){
        return this.insuranceCpID;
    }

    /**
     * 设置保险公司(往来单位ID)
     * @param insuranceCpID
     **/
    public void setInsuranceCpID(Integer insuranceCpID){
        this.insuranceCpID = insuranceCpID;
    }
    
    public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getHandlename() {
		return handlename;
	}

	public void setHandlename(String handlename) {
		this.handlename = handlename;
	}

	public String getInsuranceTypeNm() {
		return insuranceTypeNm;
	}

	public void setInsuranceTypeNm(String insuranceTypeNm) {
		this.insuranceTypeNm = insuranceTypeNm;
	}

	public String getInsuranceCpNm() {
		return insuranceCpNm;
	}

	public void setInsuranceCpNm(String insuranceCpNm) {
		this.insuranceCpNm = insuranceCpNm;
	}

	public String getInsuranceRemark() {
		return insuranceRemark;
	}

	public void setInsuranceRemark(String insuranceRemark) {
		this.insuranceRemark = insuranceRemark;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	/**
	 * 设置过期状态码
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 返回过期状态码
	 */
	public void setStatus(Integer status) {
		this.status = status;
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

	public CarDailyInsuranceRecordDO clone(){
        try{
            return (CarDailyInsuranceRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}