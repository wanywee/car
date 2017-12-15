package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarDailyOutRecordHisDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 用车记录ID */
    private Integer cdorID;
    /**  */
    private Integer operator;
    /** 操作时间 */
    private Date operateTime;
    /** 状态 */
    private Integer processStatus;

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
     * 获取用车记录ID
     * @return cdorID
     **/
    public Integer getCdorID(){
        return this.cdorID;
    }

    /**
     * 设置用车记录ID
     * @param cdorID
     **/
    public void setCdorID(Integer cdorID){
        this.cdorID = cdorID;
    }

    /**
     * 获取
     * @return operator
     **/
    public Integer getOperator(){
        return this.operator;
    }

    /**
     * 设置
     * @param operator
     **/
    public void setOperator(Integer operator){
        this.operator = operator;
    }

    /**
     * 获取操作时间
     * @return operateTime
     **/
    public Date getOperateTime(){
        return this.operateTime;
    }

    /**
     * 设置操作时间
     * @param operateTime
     **/
    public void setOperateTime(Date operateTime){
        this.operateTime = operateTime;
    }

    /**
     * 获取状态
     * @return processStatus
     **/
    public Integer getProcessStatus(){
        return this.processStatus;
    }

    /**
     * 设置状态
     * @param processStatus
     **/
    public void setProcessStatus(Integer processStatus){
        this.processStatus = processStatus;
    }


    public CarDailyOutRecordHisDO clone(){
        try{
            return (CarDailyOutRecordHisDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}