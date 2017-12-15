package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarRemindFormDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 到期提醒类别 */
    private String type;
    /** 所在区域 */
    private String location;
    /** 提醒数量 */
    private Integer numbers;
    /** 创建时间 */
    private Date createTime;
    /** 创建人员 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人员 */
    private String updateOperator;
    /** 备注 */
    private String remark;

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
     * 获取到期提醒类别
     * @return type
     **/
    public String getType(){
        return this.type;
    }

    /**
     * 设置到期提醒类别
     * @param type
     **/
    public void setType(String type){
        this.type = type;
    }

    /**
     * 获取所在区域
     * @return location
     **/
    public String getLocation(){
        return this.location;
    }

    /**
     * 设置所在区域
     * @param location
     **/
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * 获取提醒数量
     * @return numbers
     **/
    public Integer getNumbers(){
        return this.numbers;
    }

    /**
     * 设置提醒数量
     * @param numbers
     **/
    public void setNumbers(Integer numbers){
        this.numbers = numbers;
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
     * 获取创建人员
     * @return creator
     **/
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置创建人员
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


    public CarRemindFormDO clone(){
        try{
            return (CarRemindFormDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}