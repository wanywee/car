package com.carTravelsky.bean.carDaily;

import java.util.Date;

public class CarBaseContactsDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /**往来单位编码*/
    private String companyCode;
    /**往来单位助记码*/
    private String memonicCode;
    /** 往来单位名称 */
    private String companyName;
	/** 往来单位类型 */
    private String companyType;
    /**类型名字，用于显示*/
    private String companyTypeName;
    /** 联系人 */
    private String linkman;
    /** 联系电话 */
    private String phone;
    /** 邮编 */
    private String postcode;
    /** 传真 */
    private String fax;
    /** 邮箱 */
    private String email;
    /** 公司账户 */
    private String companyAccount;
    /** 开户行 */
    private String depositBank;
    /** 公司网站 */
    private String webSite;
    /** 公司地址 */
    private String address;
    /** 所在地区 */
    private String locatio;
    /** 是否使用 1为启用 2为停用 */
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
    /** 公司ID */
    private Integer mycomID;
    /**
     * 获取
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * 设置
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取往来单位名称
     * @return companyName
     **/
    public String getCompanyName(){
        return this.companyName;
    }

    /**
     * 设置往来单位名称
     * @param companyName
     **/
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * 获取往来单位类型
     * @return companyType
     **/
    public String getCompanyType(){
        return this.companyType;
    }

    /**
     * 设置往来单位类型
     * @param companyType
     **/
    public void setCompanyType(String companyType){
        this.companyType = companyType;
    }

    /**
     * 获取联系人
     * @return linkman
     **/
    public String getLinkman(){
        return this.linkman;
    }

    /**
     * 设置联系人
     * @param linkman
     **/
    public void setLinkman(String linkman){
        this.linkman = linkman;
    }

    /**
     * 获取联系电话
     * @return phone
     **/
    public String getPhone(){
        return this.phone;
    }

    /**
     * 设置联系电话
     * @param phone
     **/
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	/**
     * 获取邮编
     * @return postcode
     **/
    public String getPostcode(){
        return this.postcode;
    }

    /**
     * 设置邮编
     * @param postcode
     **/
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }

    /**
     * 获取传真
     * @return fax
     **/
    public String getFax(){
        return this.fax;
    }

    /**
     * 设置传真
     * @param fax
     **/
    public void setFax(String fax){
        this.fax = fax;
    }

    /**
     * 获取邮箱
     * @return email
     **/
    public String getEmail(){
        return this.email;
    }

    /**
     * 设置邮箱
     * @param email
     **/
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * 获取公司账户
     * @return companyAccount
     **/
    public String getCompanyAccount(){
        return this.companyAccount;
    }

    /**
     * 设置公司账户
     * @param companyAccount
     **/
    public void setCompanyAccount(String companyAccount){
        this.companyAccount = companyAccount;
    }

    /**
     * 获取开户行
     * @return depositBank
     **/
    public String getDepositBank(){
        return this.depositBank;
    }

    /**
     * 设置开户行
     * @param depositBank
     **/
    public void setDepositBank(String depositBank){
        this.depositBank = depositBank;
    }

    /**
     * 获取公司网站
     * @return webSite
     **/
    public String getWebSite(){
        return this.webSite;
    }

    /**
     * 设置公司网站
     * @param webSite
     **/
    public void setWebSite(String webSite){
        this.webSite = webSite;
    }

    /**
     * 获取公司地址
     * @return address
     **/
    public String getAddress(){
        return this.address;
    }

    /**
     * 设置公司地址
     * @param address
     **/
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * 获取所在地区
     * @return locatio
     **/
    public String getLocatio(){
        return this.locatio;
    }

    /**
     * 设置所在地区
     * @param locatio
     **/
    public void setLocatio(String locatio){
        this.locatio = locatio;
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

    /**
     * 获取公司ID
     * @return mycomID
     **/
    public Integer getMycomID(){
        return this.mycomID;
    }

    /**
     * 设置公司ID
     * @param mycomID
     **/
    public void setMycomID(Integer mycomID){
        this.mycomID = mycomID;
    }
    public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getMemonicCode() {
		return memonicCode;
	}

	public void setMemonicCode(String memonicCode) {
		this.memonicCode = memonicCode;
	}

    public CarBaseContactsDO clone(){
        try{
            return (CarBaseContactsDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}