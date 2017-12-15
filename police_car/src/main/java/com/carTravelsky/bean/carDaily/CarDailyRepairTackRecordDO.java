package com.carTravelsky.bean.carDaily;

import java.util.Date;
import com.stopec.common.validate.Validate;

public class CarDailyRepairTackRecordDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    @Validate(rule = "{required}", comment = "车辆标识")
    private Integer carId;
    /** 维修类型 */
    @Validate(rule = "{required}", comment = "维修类型 ")
    private String repairtype;
    /** 取车日期 */
    @Validate(rule = "{required}", comment = "取车日期 ")
    private Date tackcarTime;
    /** 维修费用 */
    @Validate(rule = "{required,min[0],max[1000000]}", comment = "维修费用",regexp="^\\d+(\\.\\d+)?$")
    private Float repairMoney;
    /** 维修项目 */
    @Validate(rule = "{required,maxSize[100]}", comment = "维修项目")
    private String repairProject;
    /** 使用材料 */
    @Validate(rule = "{required,maxSize[100]}", comment = "使用材料 ")
    private String usedmaterial;
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
    @Validate(rule = "{maxSize[100]}", comment = "备注 ")
    private String remark;
    /** 删除标志 */
    private Integer deleteCode;
    
    /*veiw 使用*/
    /**
     * @fieldName: licenseno
     * @fieldType: String
     * @Description: 车牌号
     */
    private String licenseno;
    /**
     * 获取ID
     * @return id
     **/
    
    /*查询使用*/
    private Integer companyId;
    
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


    /**
     * 获取维修类型
     * @return repairtype
     **/
    public String getRepairtype(){
        return this.repairtype;
    }

    public Integer getCarId()
	{
		return carId;
	}

	public void setCarId(Integer carId)
	{
		this.carId = carId;
	}

	/**
     * 设置维修类型
     * @param repairtype
     **/
    public void setRepairtype(String repairtype){
        this.repairtype = repairtype;
    }

    /**
     * 获取取车日期
     * @return tackcarTime
     **/
    public Date getTackcarTime(){
        return this.tackcarTime;
    }

    /**
     * 设置取车日期
     * @param tackcarTime
     **/
    public void setTackcarTime(Date tackcarTime){
        this.tackcarTime = tackcarTime;
    }

    /**
     * 获取维修费用
     * @return repairMoney
     **/
    public Float getRepairMoney(){
        return this.repairMoney;
    }

    /**
     * 设置维修费用
     * @param repairMoney
     **/
    public void setRepairMoney(Float repairMoney){
        this.repairMoney = repairMoney;
    }

    /**
     * 获取维修项目
     * @return repairProject
     **/
    public String getRepairProject(){
        return this.repairProject;
    }

    /**
     * 设置维修项目
     * @param repairProject
     **/
    public void setRepairProject(String repairProject){
        this.repairProject = repairProject;
    }

    /**
     * 获取使用材料
     * @return usedmaterial
     **/
    public String getUsedmaterial(){
        return this.usedmaterial;
    }

    /**
     * 设置使用材料
     * @param usedmaterial
     **/
    public void setUsedmaterial(String usedmaterial){
        this.usedmaterial = usedmaterial;
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


    public CarDailyRepairTackRecordDO clone(){
        try{
            return (CarDailyRepairTackRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	public Integer getDeleteCode()
	{
		return deleteCode;
	}

	public void setDeleteCode(Integer deleteCode)
	{
		this.deleteCode = deleteCode;
	}

	public String getLicenseno()
	{
		return licenseno;
	}

	public void setLicenseno(String licenseno)
	{
		this.licenseno = licenseno;
	}

	public Integer getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(Integer companyId)
	{
		this.companyId = companyId;
	}


	
}