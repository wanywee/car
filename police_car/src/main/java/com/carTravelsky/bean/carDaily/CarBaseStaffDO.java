package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseStaffDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** 职员编号 */
    private Integer id;
    /** 职员编码 */
    private String staffCode;
    /** 职员姓名 */
    @Validate(rule = "{required,maxSize[8]}",comment="职员姓名")
    private String staffName;
    /** 职员手机 */
    @Validate(rule = "phone",comment="职员手机")
    private String staffPhone;
    /** 职员生日 */
    @Validate(rule = "{required}",comment="职员生日 ")
    private String staffBirthday;
    /** 性别1为男  2为女 */
    @Validate(rule = "{required}")
    private Integer staffSex;
    /** 助记码 */
    @Validate(rule = "{required,maxSize[16]}")
    private String mnemonicCode;
    /** 职员民族 */
    @Validate(rule = "{required,maxSize[8]}")
    private String staffNation;
    /** 职员籍贯 */
    @Validate(rule = "{required,maxSize[8]}")
    private String staffNative;
    /** 邮箱 */
    @Validate(rule = "email",comment="邮箱 ")
    private String staffEmail;
    /** 部门ID */
    private Integer deptID;
    /** 职务 */
    @Validate(rule = "{required}")
    private String staffDuty;
    /** 学历 */
    @Validate(rule = "{required}")
    private String education;
    /** 毕业院校 */
    @Validate(rule = "{required}")
    private String graduateSchool;
    /** 身份证 */
    @Validate(rule = "{required,maxSize[18]}",regexp="^[\\d]{6}(19|20)?[\\d]{2}((0[1-9])|(10|11|12))([012][\\d]|(30|31))[\\d]{3}[xX\\d]?$",comment="身份证")
    private String idcard;
    /** 居住地址 */
    @Validate(rule = "{required,maxSize[64]}",comment="居住地址")
    private String address;
    /** 入职时间 */
    private String entryTime;
    /** 所在地方 */
    private String location;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人员 */
    private String updateOperator;
    /** 备注 */
    private String comments;
    /** 是否使用 1为启用 2为停用 */
    private Integer status;
    /** 公司ID */
    private Integer companyID;
    /**部门名字**/
    private String deptName;
    /***临时id**/
    private Integer brevityId;
    /***是否启用**/
    private String staffStatus;
    /**工号**/
    private String staffNo;
    
    public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}

	/**
     * 删除状态码
     */
    private Integer deleteCode;
    
   
    
	public Integer getBrevityId() {
		return brevityId;
	}

	public void setBrevityId(Integer brevityId) {
		this.brevityId = brevityId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
     * 获取职员编号
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * 设置职员编号
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取职员编码
     * @return staffCode
     **/
    public String getStaffCode(){
        return this.staffCode;
    }

    /**
     * 设置职员编码
     * @param staffCode
     **/
    public void setStaffCode(String staffCode){
        this.staffCode = staffCode;
    }

    /**
     * 获取职员姓名
     * @return staffName
     **/
    public String getStaffName(){
        return this.staffName;
    }

    /**
     * 设置职员姓名
     * @param staffName
     **/
    public void setStaffName(String staffName){
        this.staffName = staffName;
    }

    /**
     * 获取职员手机
     * @return staffPhone
     **/
    public String getStaffPhone(){
        return this.staffPhone;
    }

    /**
     * 设置职员手机
     * @param staffPhone
     **/
    public void setStaffPhone(String staffPhone){
        this.staffPhone = staffPhone;
    }

    /**
     * 获取职员生日
     * @return staffBirthday
     **/
    public String getStaffBirthday(){
        return this.staffBirthday;
    }

    /**
     * 设置职员生日
     * @param staffBirthday
     **/
    public void setStaffBirthday(String staffBirthday){
        this.staffBirthday = staffBirthday;
    }

    /**
     * 获取性别1为男  2为女
     * @return staffSex
     **/
    public Integer getStaffSex(){
        return this.staffSex;
    }

    /**
     * 设置性别1为男  2为女
     * @param staffSex
     **/
    public void setStaffSex(Integer staffSex){
        this.staffSex = staffSex;
    }

    /**
     * 获取助记码
     * @return mnemonicCode
     **/
    public String getMnemonicCode(){
        return this.mnemonicCode;
    }

    /**
     * 设置助记码
     * @param mnemonicCode
     **/
    public void setMnemonicCode(String mnemonicCode){
        this.mnemonicCode = mnemonicCode;
    }

    /**
     * 获取职员民族
     * @return staffNation
     **/
    public String getStaffNation(){
        return this.staffNation;
    }

    /**
     * 设置职员民族
     * @param staffNation
     **/
    public void setStaffNation(String staffNation){
        this.staffNation = staffNation;
    }

    /**
     * 获取职员籍贯
     * @return staffNative
     **/
    public String getStaffNative(){
        return this.staffNative;
    }

    /**
     * 设置职员籍贯
     * @param staffNative
     **/
    public void setStaffNative(String staffNative){
        this.staffNative = staffNative;
    }

    /**
     * 获取邮箱
     * @return staffEmail
     **/
    public String getStaffEmail(){
        return this.staffEmail;
    }

    /**
     * 设置邮箱
     * @param staffEmail
     **/
    public void setStaffEmail(String staffEmail){
        this.staffEmail = staffEmail;
    }

    /**
     * 获取部门ID
     * @return deptID
     **/
    public Integer getDeptID(){
        return this.deptID;
    }

    /**
     * 设置部门ID
     * @param deptID
     **/
    public void setDeptID(Integer deptID){
        this.deptID = deptID;
    }

    /**
     * 获取职务
     * @return staffDuty
     **/
    public String getStaffDuty(){
        return this.staffDuty;
    }

    /**
     * 设置职务
     * @param staffDuty
     **/
    public void setStaffDuty(String staffDuty){
        this.staffDuty = staffDuty;
    }

    /**
     * 获取学历
     * @return education
     **/
    public String getEducation(){
        return this.education;
    }

    /**
     * 设置学历
     * @param education
     **/
    public void setEducation(String education){
        this.education = education;
    }

    /**
     * 获取毕业院校
     * @return graduateSchool
     **/
    public String getGraduateSchool(){
        return this.graduateSchool;
    }

    /**
     * 设置毕业院校
     * @param graduateSchool
     **/
    public void setGraduateSchool(String graduateSchool){
        this.graduateSchool = graduateSchool;
    }

    /**
     * 获取身份证
     * @return idcard
     **/
    public String getIdcard(){
        return this.idcard;
    }

    /**
     * 设置身份证
     * @param idcard
     **/
    public void setIdcard(String idcard){
        this.idcard = idcard;
    }

    /**
     * 获取居住地址
     * @return address
     **/
    public String getAddress(){
        return this.address;
    }

    /**
     * 设置居住地址
     * @param address
     **/
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	/**
     * 获取所在地方
     * @return location
     **/
    public String getLocation(){
        return this.location;
    }

    /**
     * 设置所在地方
     * @param location
     **/
    public void setLocation(String location){
        this.location = location;
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
     * @return creator
     **/
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建人
     * @param creator
     **/
    public void setCreator(String creator){
        this.creator = creator;
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
     * 获取修改人员
     * @return updateOperator
     **/
    public String getUpdateOperator(){
        return this.updateOperator;
    }

    /**
     * 设置修改人员
     * @param updateOperator
     **/
    public void setUpdateOperator(String updateOperator){
        this.updateOperator = updateOperator;
    }

    /**
     * 获取备注
     * @return comments
     **/
    public String getComments(){
        return this.comments;
    }

    /**
     * 设置备注
     * @param comments
     **/
    public void setComments(String comments){
        this.comments = comments;
    }

    /**
     * 获取是否使用 1为启用 2为停用
     * @return status
     **/
    public Integer getStatus(){
        return this.status;
    }

    /**
     * 设置是否使用 1为启用 2为停用
     * @param status
     **/
    public void setStatus(Integer status){
        this.status = status;
    }

    /**
     * 获取公司ID
     * @return companyID
     **/
    public Integer getCompanyID(){
        return this.companyID;
    }

    /**
     * 设置公司ID
     * @param companyID
     **/
    public void setCompanyID(Integer companyID){
        this.companyID = companyID;
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

	public CarBaseStaffDO clone(){
        try{
            return (CarBaseStaffDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}