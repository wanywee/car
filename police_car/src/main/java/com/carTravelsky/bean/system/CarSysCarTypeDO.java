package com.carTravelsky.bean.system;

import java.util.Date;

public class CarSysCarTypeDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 类型名称 */
    private String typeName;
    /** 类型编号 */
    private String typeCode;
    /** 型号名称 */
    private String modelName;
    /** 型号编码 */
    private String modelCode;
    /** 颜色名称 */
    private String colorName;
    /** 颜色编码 */
    private String colorCode;
    /** 品牌名称 */
    private String brandName;
    /** 品牌编码 */
    private String brandCode;
    /** 创建时间 */
    private Date createTime;
    /** 创建人员 */
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
     * 获取类型名称
     * @return typeName
     **/
    public String getTypeName(){
        return this.typeName;
    }

    /**
     * 设置类型名称
     * @param typeName
     **/
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    /**
     * 获取类型编号
     * @return typeCode
     **/
    public String getTypeCode(){
        return this.typeCode;
    }

    /**
     * 设置类型编号
     * @param typeCode
     **/
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }

    /**
     * 获取型号名称
     * @return modelName
     **/
    public String getModelName(){
        return this.modelName;
    }

    /**
     * 设置型号名称
     * @param modelName
     **/
    public void setModelName(String modelName){
        this.modelName = modelName;
    }

    /**
     * 获取型号编码
     * @return modelCode
     **/
    public String getModelCode(){
        return this.modelCode;
    }

    /**
     * 设置型号编码
     * @param modelCode
     **/
    public void setModelCode(String modelCode){
        this.modelCode = modelCode;
    }

    /**
     * 获取颜色名称
     * @return colorName
     **/
    public String getColorName(){
        return this.colorName;
    }

    /**
     * 设置颜色名称
     * @param colorName
     **/
    public void setColorName(String colorName){
        this.colorName = colorName;
    }

    /**
     * 获取颜色编码
     * @return colorCode
     **/
    public String getColorCode(){
        return this.colorCode;
    }

    /**
     * 设置颜色编码
     * @param colorCode
     **/
    public void setColorCode(String colorCode){
        this.colorCode = colorCode;
    }

    /**
     * 获取品牌名称
     * @return brandName
     **/
    public String getBrandName(){
        return this.brandName;
    }

    /**
     * 设置品牌名称
     * @param brandName
     **/
    public void setBrandName(String brandName){
        this.brandName = brandName;
    }

    /**
     * 获取品牌编码
     * @return brandCode
     **/
    public String getBrandCode(){
        return this.brandCode;
    }

    /**
     * 设置品牌编码
     * @param brandCode
     **/
    public void setBrandCode(String brandCode){
        this.brandCode = brandCode;
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


    public CarSysCarTypeDO clone(){
        try{
            return (CarSysCarTypeDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}