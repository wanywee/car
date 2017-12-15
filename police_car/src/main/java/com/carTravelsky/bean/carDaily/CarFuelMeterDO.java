package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarFuelMeterDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 车牌号 */
    private String licenseno;
    /** 品牌 */
    private String brand;
    /** 型号 */
    private String model;
    /** 车辆类型 */
    private String type;
    /** 里程数 */
    private Float mileage;
    /** 摩托小时 */
    private Float motorHour;
    /** 实际耗油 */
    private Float consumption;
    /** 实际百公里耗油 */
    private Float consumPer;
    /** 实际摩托小时耗油 */
    private Float consumHour;
    /** 所在地区 */
    private String location;
    /** 是否使用1为启用 2为停用 */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
    /** 创建人 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人 */
    private String updateOperator;
    /** 备注 */
    private String comments;

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
     * 获取车牌号
     * @return licenseno
     **/
    public String getLicenseno(){
        return this.licenseno;
    }

    /**
     * 设置车牌号
     * @param licenseno
     **/
    public void setLicenseno(String licenseno){
        this.licenseno = licenseno;
    }

    /**
     * 获取品牌
     * @return brand
     **/
    public String getBrand(){
        return this.brand;
    }

    /**
     * 设置品牌
     * @param brand
     **/
    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * 获取型号
     * @return model
     **/
    public String getModel(){
        return this.model;
    }

    /**
     * 设置型号
     * @param model
     **/
    public void setModel(String model){
        this.model = model;
    }

    /**
     * 获取车辆类型
     * @return type
     **/
    public String getType(){
        return this.type;
    }

    /**
     * 设置车辆类型
     * @param type
     **/
    public void setType(String type){
        this.type = type;
    }

    /**
     * 获取里程数
     * @return mileage
     **/
    public Float getMileage(){
        return this.mileage;
    }

    /**
     * 设置里程数
     * @param mileage
     **/
    public void setMileage(Float mileage){
        this.mileage = mileage;
    }

    /**
     * 获取摩托小时
     * @return motorHour
     **/
    public Float getMotorHour(){
        return this.motorHour;
    }

    /**
     * 设置摩托小时
     * @param motorHour
     **/
    public void setMotorHour(Float motorHour){
        this.motorHour = motorHour;
    }

    /**
     * 获取实际耗油
     * @return consumption
     **/
    public Float getConsumption(){
        return this.consumption;
    }

    /**
     * 设置实际耗油
     * @param consumption
     **/
    public void setConsumption(Float consumption){
        this.consumption = consumption;
    }

    /**
     * 获取实际百公里耗油
     * @return consumPer
     **/
    public Float getConsumPer(){
        return this.consumPer;
    }

    /**
     * 设置实际百公里耗油
     * @param consumPer
     **/
    public void setConsumPer(Float consumPer){
        this.consumPer = consumPer;
    }

    /**
     * 获取实际摩托小时耗油
     * @return consumHour
     **/
    public Float getConsumHour(){
        return this.consumHour;
    }

    /**
     * 设置实际摩托小时耗油
     * @param consumHour
     **/
    public void setConsumHour(Float consumHour){
        this.consumHour = consumHour;
    }

    /**
     * 获取所在地区
     * @return location
     **/
    public String getLocation(){
        return this.location;
    }

    /**
     * 设置所在地区
     * @param location
     **/
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * 获取是否使用1为启用 2为停用
     * @return status
     **/
    public Integer getStatus(){
        return this.status;
    }

    /**
     * 设置是否使用1为启用 2为停用
     * @param status
     **/
    public void setStatus(Integer status){
        this.status = status;
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
     * 获取修改人
     * @return updateOperator
     **/
    public String getUpdateOperator(){
        return this.updateOperator;
    }

    /**
     * 设置修改人
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


    public CarFuelMeterDO clone(){
        try{
            return (CarFuelMeterDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}