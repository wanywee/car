package com.carTravelsky.bean.system;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class KeyCodeMasterDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /**  */
    @Validate(rule = "{required,maxSize[64]}",comment="字典类型")
    private String keyType;
    /**  */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="字典编码")
    private String code;
    /**  */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="字典解码")
    private String decode;
    /**  */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="字典名称")
    private String display;
    /**  */
    private String defaultInd;
    /**  */
    private String editableInd;
    /**  */
    @Validate(rule = "{maxSize[128]}",comment="字典描述")
    private String description;
    /**  */
    private Integer priority;
    /**  */
    private String createBy;
    /**  */
    private Date createDate;
    /**  */
    private String updateBy;
    /**  */
    private Date lastModified;
    /**  */
    private Integer keyID;
    //删除状态
    private Integer deleteCode;
    //公司ID
    private Integer companyId;

    //搜索字符串
    private String searchStr;
    /**
     * 获取
     * @return keyType
     **/
    public String getKeyType(){
        return this.keyType;
    }

    /**
     * 设置
     * @param keyType
     **/
    public void setKeyType(String keyType){
        this.keyType = keyType;
    }

    /**
     * 获取
     * @return code
     **/
    public String getCode(){
        return this.code;
    }

    /**
     * 设置
     * @param code
     **/
    public void setCode(String code){
        this.code = code;
    }

    /**
     * 获取
     * @return decode
     **/
    public String getDecode(){
        return this.decode;
    }

    /**
     * 设置
     * @param decode
     **/
    public void setDecode(String decode){
        this.decode = decode;
    }

    /**
     * 获取
     * @return display
     **/
    public String getDisplay(){
        return this.display;
    }

    /**
     * 设置
     * @param display
     **/
    public void setDisplay(String display){
        this.display = display;
    }

    /**
     * 获取
     * @return defaultInd
     **/
    public String getDefaultInd(){
        return this.defaultInd;
    }

    /**
     * 设置
     * @param defaultInd
     **/
    public void setDefaultInd(String defaultInd){
        this.defaultInd = defaultInd;
    }

    /**
     * 获取
     * @return editableInd
     **/
    public String getEditableInd(){
        return this.editableInd;
    }

    /**
     * 设置
     * @param editableInd
     **/
    public void setEditableInd(String editableInd){
        this.editableInd = editableInd;
    }

    /**
     * 获取
     * @return description
     **/
    public String getDescription(){
        return this.description;
    }

    /**
     * 设置
     * @param description
     **/
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * 获取
     * @return priority
     **/
    public Integer getPriority(){
        return this.priority;
    }

    /**
     * 设置
     * @param priority
     **/
    public void setPriority(Integer priority){
        this.priority = priority;
    }

    /**
     * 获取
     * @return createBy
     **/
    public String getCreateBy(){
        return this.createBy;
    }

    /**
     * 设置
     * @param createBy
     **/
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * 获取
     * @return createDate
     **/
    public Date getCreateDate(){
        return this.createDate;
    }

    /**
     * 设置
     * @param createDate
     **/
    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    /**
     * 获取
     * @return updateBy
     **/
    public String getUpdateBy(){
        return this.updateBy;
    }

    /**
     * 设置
     * @param updateBy
     **/
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * 获取
     * @return lastModified
     **/
    public Date getLastModified(){
        return this.lastModified;
    }

    /**
     * 设置
     * @param lastModified
     **/
    public void setLastModified(Date lastModified){
        this.lastModified = lastModified;
    }

    /**
     * 获取
     * @return keyID
     **/
    public Integer getKeyID(){
        return this.keyID;
    }

    /**
     * 设置
     * @param keyID
     **/
    public void setKeyID(Integer keyID){
        this.keyID = keyID;
    }


    public KeyCodeMasterDO clone(){
        try{
            return (KeyCodeMasterDO)super.clone();
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

	public Integer getCompanyId()
	{
		return companyId;
	}

	public void setCompanyId(Integer companyId)
	{
		this.companyId = companyId;
	}

	public String getSearchStr()
	{
		return searchStr;
	}

	public void setSearchStr(String searchStr)
	{
		this.searchStr = searchStr;
	}
}