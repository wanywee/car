package com.carTravelsky.bean.system;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class CarSysRoleDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** ID */
    private Integer id;
    /** 所在区域 */
    private String roleArea;
    /** 角色名称 */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="角色名称")
    private String roleName;
    /** 助记码 */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="助记码")
    private String mnemonicCode;
    /** 是否使用 */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
    /** 参加人员 */
    private String creator;
    /** 修改时间 */
    private Date uodateTime;
    /** 修改人员 */
    private String updateOperator;
    /** 备注 */
    private String comments;
    /** 角色编码*/
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="角色编码")
    private String roleCode;
    /**角色状态: 0 为删除 1未删除*/
    private Integer deleteCode;
    
    private Integer isAllView;
    
  
    
    
    
    //
    private Integer companyID;//公司id
    
	public Integer getCompanyID() {
		return companyID;
	}

	public void setCompanyID(Integer companyID) {
		this.companyID = companyID;
	}

	private String  proGlobalSeach;
    /**
     * 获取ID
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }
    

    public String getRoleCode() {
		return roleCode;
	}


	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}


	/**
     * 设置ID
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取所在区域
     * @return roleArea
     **/
    public String getRoleArea(){
        return this.roleArea;
    }

    /**
     * 设置所在区域
     * @param roleArea
     **/
    public void setRoleArea(String roleArea){
        this.roleArea = roleArea;
    }

    /**
     * 获取角色名称
     * @return roleName
     **/
    public String getRoleName(){
        return this.roleName;
    }

    /**
     * 设置角色名称
     * @param roleName
     **/
    public void setRoleName(String roleName){
        this.roleName = roleName;
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
     * 获取是否使用
     * @return status
     **/
    public Integer getStatus(){
        return this.status;
    }

    /**
     * 设置是否使用
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
     * 获取参加人员
     * @return creator
     **/
    public String getCreator(){
        return this.creator;
    }

    /**
     * 设置参加人员
     * @param creator
     **/
    public void setCreator(String creator){
        this.creator = creator;
    }

    /**
     * 获取修改时间
     * @return uodateTime
     **/
    public Date getUodateTime(){
        return this.uodateTime;
    }

    /**
     * 设置修改时间
     * @param uodateTime
     **/
    public void setUodateTime(Date uodateTime){
        this.uodateTime = uodateTime;
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
     * 角色删除状态
     * @Title: getRoleType
     * @Description: TODO
     * @return
     * @return: Integer
     * @author: fuxinrong
     * @date: 2017年10月13日 下午3:19:22
     */
    public Integer getDeleteCode() {
		return deleteCode;
	}


	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}


	public CarSysRoleDO clone(){
        try{
            return (CarSysRoleDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	public String getProGlobalSeach()
	{
		return "%"+proGlobalSeach+"%";
	}

	public void setProGlobalSeach(String proGlobalSeach)
	{
		this.proGlobalSeach = proGlobalSeach;
	}

	public Integer getIsAllView() {
		return isAllView;
	}

	public void setIsAllView(Integer isAllView) {
		this.isAllView = isAllView;
	}

}