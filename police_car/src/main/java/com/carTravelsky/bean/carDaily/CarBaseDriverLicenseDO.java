package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarBaseDriverLicenseDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 关联驾驶员档案表ID */
    private Integer driverID;
    /** 驾驶证号 */
    private String driverLicense;
    /** 初领时间 */
    private Date getTime;
    /** 这里写有效期到具体的年 */
    private Date validTime;
    /** 发证机关 */
    private String licenceIssuing;
    /** 准驾车型 */
    private String drivingType;
    /** 年检时间 */
    private Date inspectionTime;
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
    /** 证件类型1是外场 2是内场 3是控制区域 */
    private Integer licenceType;
    /** 通行区域 */
    private String trafficArea;

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
     * 获取关联驾驶员档案表ID
     * @return driverID
     **/
    public Integer getDriverID(){
        return this.driverID;
    }

    /**
     * 设置关联驾驶员档案表ID
     * @param driverID
     **/
    public void setDriverID(Integer driverID){
        this.driverID = driverID;
    }

    /**
     * 获取驾驶证号
     * @return driverLicense
     **/
    public String getDriverLicense(){
        return this.driverLicense;
    }

    /**
     * 设置驾驶证号
     * @param driverLicense
     **/
    public void setDriverLicense(String driverLicense){
        this.driverLicense = driverLicense;
    }

    /**
     * 获取初领时间
     * @return getTime
     **/
    public Date getGetTime(){
        return this.getTime;
    }

    /**
     * 设置初领时间
     * @param getTime
     **/
    public void setGetTime(Date getTime){
        this.getTime = getTime;
    }

    /**
     * 获取这里写有效期到具体的年
     * @return validTime
     **/
    public Date getValidTime(){
        return this.validTime;
    }

    /**
     * 设置这里写有效期到具体的年
     * @param validTime
     **/
    public void setValidTime(Date validTime){
        this.validTime = validTime;
    }

    /**
     * 获取发证机关
     * @return licenceIssuing
     **/
    public String getLicenceIssuing(){
        return this.licenceIssuing;
    }

    /**
     * 设置发证机关
     * @param licenceIssuing
     **/
    public void setLicenceIssuing(String licenceIssuing){
        this.licenceIssuing = licenceIssuing;
    }

    /**
     * 获取准驾车型
     * @return drivingType
     **/
    public String getDrivingType(){
        return this.drivingType;
    }

    /**
     * 设置准驾车型
     * @param drivingType
     **/
    public void setDrivingType(String drivingType){
        this.drivingType = drivingType;
    }

    /**
     * 获取年检时间
     * @return inspectionTime
     **/
    public Date getInspectionTime(){
        return this.inspectionTime;
    }

    /**
     * 设置年检时间
     * @param inspectionTime
     **/
    public void setInspectionTime(Date inspectionTime){
        this.inspectionTime = inspectionTime;
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
     * 获取证件类型1是外场 2是内场 3是控制区域
     * @return licenceType
     **/
    public Integer getLicenceType(){
        return this.licenceType;
    }

    /**
     * 设置证件类型1是外场 2是内场 3是控制区域
     * @param licenceType
     **/
    public void setLicenceType(Integer licenceType){
        this.licenceType = licenceType;
    }

    /**
     * 获取通行区域
     * @return trafficArea
     **/
    public String getTrafficArea(){
        return this.trafficArea;
    }

    /**
     * 设置通行区域
     * @param trafficArea
     **/
    public void setTrafficArea(String trafficArea){
        this.trafficArea = trafficArea;
    }


    public CarBaseDriverLicenseDO clone(){
        try{
            return (CarBaseDriverLicenseDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}