package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.orm.BasicDO;
import com.stopec.common.validate.Validate;

public class CarBaseDriverRecordDo extends BasicDO{
	private static final long serialVersionUID = 1L;
	private Integer brevityId;
	/**员工Id**/
	private Integer staffId;
	/**驾驶证Id**/
	private Integer licenseId;
	/**驾驶证Id1**/
	private Integer licenseId1;
	/**驾驶证Id2**/
	private Integer licenseId2;
    /** 驾驶员id */
    private Integer id;
    /** 编码 */
    private String staffCode;
    /**助记码**/
    @Validate(rule = "{required,maxSize[16]}",comment="助记码")
    private String mnemonicCode;
    /**名字**/
    @Validate(rule = "{required,maxSize[8]}",comment="名字")
    private String staffName;
    /**性别**/
    @Validate(rule = "{required}",comment="性别")
    private String staffSex;
    /**出生日期**/
    @Validate(rule = "{required}",comment="出生日期")
    private String birth;
    /**民族**/
    @Validate(rule = "{required,maxSize[8]}",comment="民族")
    private String nation;
    /**联系电话**/
    private String tel;
    /**籍贯**/
    @Validate(rule = "{required,maxSize[8]}",comment="籍贯")
    private String staffNative;
    /**邮箱**/
    @Validate(rule = "email",comment="邮箱")
    private String email;
    /**所在部门**/
    private String dept;
    /**职务**/
    @Validate(rule = "{required}",comment="职务")
    private String duty;
    /**学历**/
    @Validate(rule = "{required}",comment="学历")
    private String education;
    /**毕业院校**/
    @Validate(rule = "{required}",comment="毕业院校")
    private String graduteSchool;
    /**身份证号**/
    @Validate(rule = "{required,maxSize[18]}",regexp="^[\\d]{6}(19|20)?[\\d]{2}((0[1-9])|(10|11|12))([012][\\d]|(30|31))[\\d]{3}[xX\\d]?$",comment="身份证")
    private String idCard;
    /**家庭住址**/
    @Validate(rule = "{required,maxSize[64]}",comment="家庭住址")
    private String address;
    /**入职时间**/
    private String entryTime;
    /**所在区域**/
    private String location;
    /**专业职称**/
    private String professional;
    /**从业证**/
    private String fromThe;
    /**准驾区域**/
    private String licenseType;
    /**驾驶区域**/
    private String driveArea;
    
    /**类型**/
    private String driverType;
    /**电话号码**/
    @Validate(rule = "phone",comment="电话号码")
    private String staffPhone;
    /**准驾车型**/
    private String drivingType;
    /**驾驶证号**/
    private String driverLicense;
    /**初领时间**/
    private String getTime;
    /**有效期**/
    private String validTime;
    /**发证机关**/
    private String licenceIssuing;
    /**年检时间**/
    private String inspectionTime;
    /**准驾车型**/
    private String drivingType1;
    /**驾驶证号**/
    private String driverLicense1;
    /**初领时间**/
    private String getTime1;
    /**有效期**/
    private String validTime1;
    /**发证机关**/
    private String licenceIssuing1;
    /**年检时间**/
    private String inspectionTime1;
    /**准驾车型**/
    private String drivingType2;
    /**驾驶证号**/
    private String driverLicense2;
    /**初领时间**/
    private String getTime2;
    /**有效期**/
    private String validTime2;
    /**年检时间**/
    private String inspectionTime2;
    /**司机当前状态**/
    private int currStatus;
    /**备注**/
    private String comments;
    /**是否启用**/
    private String status;
    /**创建时间**/
    private String createTime;
    /**创建者**/
    private String creator;
    /**更改时间**/
    private String updateTime;
    /**更改者**/
    private String updateOperator;
    /**公司ID**/
    private Integer companyID;
    /***准驾区域**/
    private Integer licenceType;
    /***通行区域*/
    private String trafficArea;
    /**wordID职员ID**/
    private Integer workID;
    /**部门id*/
    private Integer deptmentId;
    /**类型名字**/
    private String drivertypeName;
    /**工号**/
    private String staffNo;
    
	
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getDrivertypeName() {
		return drivertypeName;
	}
	public void setDrivertypeName(String drivertypeName) {
		this.drivertypeName = drivertypeName;
	}
	public Integer getDeptmentId() {
		return deptmentId;
	}
	public void setDeptmentId(Integer deptmentId) {
		this.deptmentId = deptmentId;
	}
	public Integer getWorkID() {
		return workID;
	}
	public void setWorkID(Integer workID) {
		this.workID = workID;
	}
	public String getTrafficArea() {
		return trafficArea;
	}
	public void setTrafficArea(String trafficArea) {
		this.trafficArea = trafficArea;
	}
	public Integer getLicenceType() {
		return licenceType;
	}
	public void setLicenceType(Integer licenceType) {
		this.licenceType = licenceType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public String getMnemonicCode() {
		return mnemonicCode;
	}
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffSex() {
		return staffSex;
	}
	public void setStaffSex(String staffSex) {
		this.staffSex = staffSex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getStaffNative() {
		return staffNative;
	}
	public void setStaffNative(String staffNative) {
		this.staffNative = staffNative;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduteSchool() {
		return graduteSchool;
	}
	public void setGraduteSchool(String graduteSchool) {
		this.graduteSchool = graduteSchool;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getFromThe() {
		return fromThe;
	}
	public void setFromThe(String fromThe) {
		this.fromThe = fromThe;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}
	public String getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
	public String getDrivingType() {
		return drivingType;
	}
	public void setDrivingType(String drivingType) {
		this.drivingType = drivingType;
	}
	public String getDriverType() {
		return driverType;
	}
	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}
	public String getStaffPhone() {
		return staffPhone;
	}
	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public String getLicenceIssuing() {
		return licenceIssuing;
	}
	public void setLicenceIssuing(String licenceIssuing) {
		this.licenceIssuing = licenceIssuing;
	}
	public String getInspectionTime() {
		return inspectionTime;
	}
	public void setInspectionTime(String inspectionTime) {
		this.inspectionTime = inspectionTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDrivingType1() {
		return drivingType1;
	}
	public void setDrivingType1(String drivingType1) {
		this.drivingType1 = drivingType1;
	}
	public String getDriverLicense1() {
		return driverLicense1;
	}
	public void setDriverLicense1(String driverLicense1) {
		this.driverLicense1 = driverLicense1;
	}
	public String getGetTime1() {
		return getTime1;
	}
	public void setGetTime1(String getTime1) {
		this.getTime1 = getTime1;
	}
	public String getValidTime1() {
		return validTime1;
	}
	public void setValidTime1(String validTime1) {
		this.validTime1 = validTime1;
	}
	public String getLicenceIssuing1() {
		return licenceIssuing1;
	}
	public void setLicenceIssuing1(String licenceIssuing1) {
		this.licenceIssuing1 = licenceIssuing1;
	}
	public String getInspectionTime1() {
		return inspectionTime1;
	}
	public void setInspectionTime1(String inspectionTime1) {
		this.inspectionTime1 = inspectionTime1;
	}
	public String getDrivingType2() {
		return drivingType2;
	}
	public void setDrivingType2(String drivingType2) {
		this.drivingType2 = drivingType2;
	}
	public String getDriverLicense2() {
		return driverLicense2;
	}
	public void setDriverLicense2(String driverLicense2) {
		this.driverLicense2 = driverLicense2;
	}
	public String getGetTime2() {
		return getTime2;
	}
	public void setGetTime2(String getTime2) {
		this.getTime2 = getTime2;
	}
	public String getValidTime2() {
		return validTime2;
	}
	public void setValidTime2(String validTime2) {
		this.validTime2 = validTime2;
	}
	public String getInspectionTime2() {
		return inspectionTime2;
	}
	public void setInspectionTime2(String inspectionTime2) {
		this.inspectionTime2 = inspectionTime2;
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}
	public Integer getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(Integer licenseId) {
		this.licenseId = licenseId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateOperator() {
		return updateOperator;
	}
	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
	}
	public Integer getCompanyID() {
		return companyID;
	}
	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}
	public String getDriveArea() {
		return driveArea;
	}
	public void setDriveArea(String driveArea) {
		this.driveArea = driveArea;
	}
	public int getCurrStatus() {
		return currStatus;
	}
	public void setCurrStatus(int currStatus) {
		this.currStatus = currStatus;
	}
	public Integer getLicenseId1() {
		return licenseId1;
	}
	public void setLicenseId1(Integer licenseId1) {
		this.licenseId1 = licenseId1;
	}
	public Integer getLicenseId2() {
		return licenseId2;
	}
	public void setLicenseId2(Integer licenseId2) {
		this.licenseId2 = licenseId2;
	}
	public Integer getBrevityId() {
		return brevityId;
	}
	public void setBrevityId(Integer brevityId) {
		this.brevityId = brevityId;
	}
	
	
	
	
}
