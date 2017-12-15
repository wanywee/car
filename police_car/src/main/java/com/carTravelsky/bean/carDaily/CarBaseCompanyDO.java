package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarBaseCompanyDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 公司名称 */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="公司名称")
    private String companyName;
    /** 公司编码 */
    private String companyCode;
    /** 公司区域 */
    private String companyArea;
    /** 公司电话 */
    private String companyPhone;
    /** 负责人 */
    private String principal;
    /** 是否使用1为启用2为停用 */
    private Integer status;
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
     * 获取公司名称
     * @return companyName
     **/
    public String getCompanyName(){
        return this.companyName;
    }

    /**
     * 设置公司名称
     * @param companyName
     **/
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * 获取公司编码
     * @return companyCode
     **/
    public String getCompanyCode(){
        return this.companyCode;
    }

    /**
     * 设置公司编码
     * @param companyCode
     **/
    public void setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }

    /**
     * 获取公司区域
     * @return companyArea
     **/
    public String getCompanyArea(){
        return this.companyArea;
    }

    /**
     * 设置公司区域
     * @param companyArea
     **/
    public void setCompanyArea(String companyArea){
        this.companyArea = companyArea;
    }

    /**
     * 获取公司电话
     * @return companyPhone
     **/
    public String getCompanyPhone(){
        return this.companyPhone;
    }

    /**
     * 设置公司电话
     * @param companyPhone
     **/
    public void setCompanyPhone(String companyPhone){
        this.companyPhone = companyPhone;
    }

    /**
     * 获取负责人
     * @return principal
     **/
    public String getPrincipal(){
        return this.principal;
    }

    /**
     * 设置负责人
     * @param principal
     **/
    public void setPrincipal(String principal){
        this.principal = principal;
    }

    /**
     * 获取是否使用1为启用2为停用
     * @return status
     **/
    public Integer getStatus(){
        return this.status;
    }

    /**
     * 设置是否使用1为启用2为停用
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


    public CarBaseCompanyDO clone(){
        try{
            return (CarBaseCompanyDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}