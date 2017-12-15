package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarDailyOutRecordDO extends com.stopec.common.orm.BasicDO{
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车辆ID */
    private Integer carID;
    /** 驾驶员*/
    private String driver;
    /** 用车人*/
    private String caruser;
    /** 随行人员 */
    private String entourage;
    /** 用车部门 */
    private String vehicleDept;
    /** 出车时间 */
    private Date outcarTime;
    /** 预计回车时间 */
    private Date estimateReturnTime;
    /** 出车里程 */
    private Float outcarMileage;
    /** 目的地 */
    private String desitination;
    /** 电话号*/
    @Validate(rule = "{}", regexp = "^([1][3,4,5,7,8][0-9]{9})$", comment = "电话")
    private String phonenumber;
    /** 区域 */
    private String region;
    /** 创建时间 */
    private Date createTime;
    /**创建人*/
    private String createPeople;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人*/
    private String updatePeople;
    /** 备注 */
    private String outRemark;
    /** 是否删除  1为默认  2为删除 */
    private Integer deleteCode;
    /**回车时间 */
    private Date backTime;
    /**回车里程*/
    private Float backcarMileage;
    /** 本次里程 */
    private Float trip;
    /**停放位置 */
    private String parkPosition;
    /**回车记录   备注*/
    private String backRemark;
    /**出车原因*/
    private String outCause;
    //出车时长
    private Float outTime;
    //超出时长
    //private Float flagTime;
    /**驾驶员id*/
    private Integer stffID;
    /**警号*/
    private String stffNo;
    /**车牌号*/
    private String licenseno;
    /**公司id*/
    private Integer companyID;
    /**车辆状态*/
    private Integer newStatus;
    
    /**部门id*/
    private Integer deptID;
    
    /**车类型  ： 1为可用    2为出车 用于判断getList 出车还是回车  默认为2*/
    private Integer carType;
    
    // 车辆所属部门id 部门name
    private Integer carDeptId;
    private String carDeptName;
    
    /**区域，省内和省外*/
    private String destArea;
    /**车辆类型名字**/
    private String carTypeName;
    
    /*用于方便用车记录中的记录人显示*/
    private String entourageDisplay;
    /*
     * 列表用车人的显示
     */
    private String caruserDisplay;
    
    public String getCaruserDisplay() {
		return caruserDisplay;
	}

	public void setCaruserDisplay(String caruserDisplay) {
		this.caruserDisplay = caruserDisplay;
	}

	public String getDestArea() {
		return destArea;
	}

	public void setDestArea(String destArea) {
		this.destArea = destArea;
	}

	/**驾驶员id*/
    public Integer getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(Integer carDeptId) {
		this.carDeptId = carDeptId;
	}

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	/**
     * ��ȡID
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * ����ID
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * ��ȡ����ID
     * @return carID
     **/
    public Integer getCarID(){
        return this.carID;
    }

    /**
     * ���ó���ID
     * @param carID
     **/
    public void setCarID(Integer carID){
        this.carID = carID;
    }

    /**
     * ��ȡ��ʻԱ
     * @return driver
     **/
    public String getDriver(){
        return this.driver;
    }

    /**
     * ���ü�ʻԱ
     * @param driver
     **/
    public void setDriver(String driver){
        this.driver = driver;
    }

    /**
     * ��ȡ�ó���
     * @return caruser
     **/
    public String getCaruser(){
        return this.caruser;
    }

    /**
     * �����ó���
     * @param caruser
     **/
    public void setCaruser(String caruser){
        this.caruser = caruser;
    }

    /**
     * ��ȡ������Ա
     * @return entourage
     **/
    public String getEntourage(){
        return this.entourage;
    }

    /**
     * ����������Ա
     * @param entourage
     **/
    public void setEntourage(String entourage){
        this.entourage = entourage;
    }

    /**
     * ��ȡ�ó�����
     * @return vehicleDept
     **/
    public String getVehicleDept(){
        return this.vehicleDept;
    }

    /**
     * �����ó�����
     * @param vehicleDept
     **/
    public void setVehicleDept(String vehicleDept){
        this.vehicleDept = vehicleDept;
    }

    /**
     * ��ȡ����ʱ��
     * @return outcarTime
     **/
    public Date getOutcarTime(){
        return this.outcarTime;
    }

    /**
     * ���ó���ʱ��
     * @param outcarTime
     **/
    public void setOutcarTime(Date outcarTime){
        this.outcarTime = outcarTime;
    }

    /**
     * ��ȡԤ�ƻس�ʱ��
     * @return estimateReturnTime
     **/
    public Date getEstimateReturnTime(){
        return this.estimateReturnTime;
    }

    /**
     * ����Ԥ�ƻس�ʱ��
     * @param estimateReturnTime
     **/
    public void setEstimateReturnTime(Date estimateReturnTime){
        this.estimateReturnTime = estimateReturnTime;
    }

    /**
     * ��ȡ�������
     * @return outcarMileage
     **/
    public Float getOutcarMileage(){
        return this.outcarMileage;
    }

    /**
     * ���ó������
     * @param outcarMileage
     **/
    public void setOutcarMileage(Float outcarMileage){
        this.outcarMileage = outcarMileage;
    }

    /**
     * ��ȡĿ�ĵ�
     * @return desitination
     **/
    public String getDesitination(){
        return this.desitination;
    }

    /**
     * ����Ŀ�ĵ�
     * @param desitination
     **/
    public void setDesitination(String desitination){
        this.desitination = desitination;
    }

    /**
     * ��ȡ��ϵ�绰
     * @return phonenumber
     **/
    public String getPhonenumber(){
        return this.phonenumber;
    }

    /**
     * ������ϵ�绰
     * @param phonenumber
     **/
    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    /**
     * ��ȡ��������
     * @return region
     **/
    public String getRegion(){
        return this.region;
    }

    /**
     * ������������
     * @param region
     **/
    public void setRegion(String region){
        this.region = region;
    }

    /**
     * ��ȡ����ʱ��
     * @return createTime
     **/
    public Date getCreateTime(){
        return this.createTime;
    }

    /**
     * ���ô���ʱ��
     * @param createTime
     **/
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * ��ȡ������
     * @return createPeople
     **/
    public String getCreatePeople(){
        return this.createPeople;
    }

    /**
     * ���ô�����
     * @param createPeople
     **/
    public void setCreatePeople(String createPeople){
        this.createPeople = createPeople;
    }

    /**
     * ��ȡ�޸�ʱ��
     * @return updateTime
     **/
    public Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * �����޸�ʱ��
     * @param updateTime
     **/
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * ��ȡ�޸���
     * @return updatePeople
     **/
    public String getUpdatePeople(){
        return this.updatePeople;
    }

    /**
     * �����޸���
     * @param updatePeople
     **/
    public void setUpdatePeople(String updatePeople){
        this.updatePeople = updatePeople;
    }

   

    public String getOutRemark() {
		return outRemark;
	}

	public void setOutRemark(String outRemark) {
		this.outRemark = outRemark;
	}

	public String getBackRemark() {
		return backRemark;
	}

	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}

	/**
     * ��ȡ1����2ɾ��
     * @return deleteCode
     **/
    public Integer getDeleteCode(){
        return this.deleteCode;
    }

    /**
     * ����1����2ɾ��
     * @param deleteCode
     **/
    public void setDeleteCode(Integer deleteCode){
        this.deleteCode = deleteCode;
    }

    /**
     * ��ȡ�س�ʱ��
     * @return backTime
     **/
    public Date getBackTime(){
        return this.backTime;
    }

    /**
     * ���ûس�ʱ��
     * @param backTime
     **/
    public void setBackTime(Date backTime){
        this.backTime = backTime;
    }

    /**
     * ��ȡ�س��г�
     * @return backcarMileage
     **/
    public Float getBackcarMileage(){
        return this.backcarMileage;
    }

    /**
     * ���ûس��г�
     * @param backcarMileage
     **/
    public void setBackcarMileage(Float backcarMileage){
        this.backcarMileage = backcarMileage;
    }

    /**
     * ��ȡ�����г�
     * @return trip
     **/
    public Float getTrip(){
        return this.trip;
    }

    /**
     * ���ñ����г�
     * @param trip
     **/
    public void setTrip(Float trip){
        this.trip = trip;
    }

    /**
     * ��ȡͣ��λ��
     * @return parkPosition
     **/
    public String getParkPosition(){
        return this.parkPosition;
    }

    /**
     * ����ͣ��λ��
     * @param parkPosition
     **/
    public void setParkPosition(String parkPosition){
        this.parkPosition = parkPosition;
    }


    public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	public String getOutCause() {
		return outCause;
	}

	public void setOutCause(String outCause) {
		this.outCause = outCause;
	}

	public Integer getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	public Integer getDeptID() {
		return deptID;
	}

	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getStffID() {
		return stffID;
	}

	public void setStffID(Integer stffID) {
		this.stffID = stffID;
	}

	public CarDailyOutRecordDO clone(){
        try{
            return (CarDailyOutRecordDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	public String getStffNo() {
		return stffNo;
	}

	public void setStffNo(String stffNo) {
		this.stffNo = stffNo;
	}

	public Float getOutTime() {
		return outTime;
	}

	public void setOutTime(Float outTime) {
		this.outTime = outTime;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getEntourageDisplay() {
		return entourageDisplay;
	}

	public void setEntourageDisplay(String entourageDisplay) {
		this.entourageDisplay = entourageDisplay;
	}



	
}