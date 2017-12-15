package com.carTravelsky.bean.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.carTravelsky.common.YSTConstants;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.Validate;

public class CarSysUserDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** 本表ID */
    private Integer id;
    /** 部门ID */
    private Integer deptID;
    /** 登录名称 */
    @Validate(rule = "{required,maxSize[64]}",regexp="[a-zA-Z0-9]{1}[a-zA-Z0-9_]{1,15}",comment="登录名称")
    private String username;
    /** 登陆密码 */
    private String password;
    /** 助记码 */
    @Validate(rule = "{required,maxSize[64]}",regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$",comment="助记码")
    private String mnemonicCode;
    /** 所属区域 */
    private String area;
    /** 是否停用1.为启用 2为停用 */
    private Integer status;
    /** 创建时间 */
    private Date createTime;
    /** 参加人员 */
    private String creator;
    /** 修改时间 */
    private Date updateTime;
    /** 修改人员 */
    private String updateOpertor;
    /** 备注 */
    private String comments;
    /** 生成密码的盐值 */
    private String salt;
    /** 客户端类型1.管理系统账号2是APP */
    private Integer clientType;
    /** 登陆工作人员类型1.地服2是司机3是货监4是平台5.分拣人员6.称重员 */
    private Integer loginWorkType;
    //删除状态
    private Integer deleteCode;
    
	//
    /**公司id*/
    private int companyId;
    /**公司名称*/
    private String companyName;
    /**部门名称*/
    private String deptName;
    /**角色编码*/
    private String roleCode;
    /**职位*/
    private String roleName;
    
    private String roleID;
    
    
    private Integer isAllView;
    
    /**
     * 职工id
     */
    private Integer staffID;
    private String staffName;
    
    public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getStaffID() {
		return staffID;
	}

	public void setStaffID(Integer staffID) {
		this.staffID = staffID;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	private List<CarSysMenuDO> carSysMenuDOs;
     
    public List<CarSysMenuDO> getCarSysMenuDOs() {
		return carSysMenuDOs;
	}

	public void setCarSysMenuDOs(List<CarSysMenuDO> carSysMenuDOs) {
		this.carSysMenuDOs = carSysMenuDOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getDeleteCode() {
		return deleteCode;
	}

	public void setDeleteCode(Integer deleteCode) {
		this.deleteCode = deleteCode;
	}

	/**
     * 获取本表ID
     * @return id
     **/
    public Integer getId(){
        return this.id;
    }

    /**
     * 设置本表ID
     * @param id
     **/
    public void setId(Integer id){
        this.id = id;
    }

    /**
     * 获取部门ID
     * @return deptID
     **/
    public Integer getDeptID(){
        return this.deptID;
    }

    /**
     * 设置部门ID
     * @param deptID
     **/
    public void setDeptID(Integer deptID){
        this.deptID = deptID;
    }

    /**
     * 获取登录名称
     * @return username
     **/
    public String getUsername(){
        return this.username;
    }

    /**
     * 设置登录名称
     * @param username
     **/
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * 获取登陆密码
     * @return password
     **/
    public String getPassword(){
        return this.password;
    }

    /**
     * 设置登陆密码
     * @param password
     **/
    public void setPassword(String password){
        this.password = password;
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
     * 获取所属区域
     * @return area
     **/
    public String getArea(){
        return this.area;
    }

    /**
     * 设置所属区域
     * @param area
     **/
    public void setArea(String area){
        this.area = area;
    }

    /**
     * 获取是否停用1.为启用 2为停用
     * @return status
     **/
    public Integer getStatus(){
        return this.status;
    }

    /**
     * 设置是否停用1.为启用 2为停用
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
     * @return updateOpertor
     **/
    public String getUpdateOpertor(){
        return this.updateOpertor;
    }

    /**
     * 设置修改人员
     * @param updateOpertor
     **/
    public void setUpdateOpertor(String updateOpertor){
        this.updateOpertor = updateOpertor;
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
     * 获取生成密码的盐值
     * @return salt
     **/
    public String getSalt(){
        return this.salt;
    }

    /**
     * 设置生成密码的盐值
     * @param salt
     **/
    public void setSalt(String salt){
        this.salt = salt;
    }

    /**
     * 获取客户端类型1.管理系统账号2是APP
     * @return clientType
     **/
    public Integer getClientType(){
        return this.clientType;
    }

    /**
     * 设置客户端类型1.管理系统账号2是APP
     * @param clientType
     **/
    public void setClientType(Integer clientType){
        this.clientType = clientType;
    }

    /**
     * 获取登陆工作人员类型1.地服2是司机3是货监4是平台5.分拣人员5称重员
     * @return loginWorkType
     **/
    public Integer getLoginWorkType(){
        return this.loginWorkType;
    }

    
    
    
    public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
     * 设置登陆工作人员类型1.地服2是司机3是货监4是平台5.分拣人员5称重员
     * @param loginWorkType
     **/
    public void setLoginWorkType(Integer loginWorkType){
        this.loginWorkType = loginWorkType;
    }


    public CarSysUserDO clone(){
        try{
            return (CarSysUserDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	@Override
	public String toString() {
		return "CarSysUserDO [id=" + id + ", deptID=" + deptID + ", username=" + username + ", password=" + password
				+ ", mnemonicCode=" + mnemonicCode + ", area=" + area + ", status=" + status + ", createTime="
				+ createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updateOpertor="
				+ updateOpertor + ", comments=" + comments + ", salt=" + salt + ", clientType=" + clientType
				+ ", loginWorkType=" + loginWorkType + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", deptName=" + deptName + "]";
	}
	/**
	 * 匹配权限
	 * @param uri
	 * @param type
	 * @return
	 */
	public boolean matches(String uri, String type) {
		boolean matches = false;
		for (CarSysMenuDO p : this.carSysMenuDOs) {
			if ((StringUtils.equals(p.getMenuUrl(), uri))
					&& ((StringUtils.isBlank(type)) || (StringUtils.equalsIgnoreCase(type, p.getMenuType()+"")))) {
				matches = true;
				break;
			}
		}
		return matches;
	}
	/**
	 * 生成权限
	 * @param list 
	 * @return
	 */
	public List<CarSysMenuDO> getPermissionTree(List<CarSysMenuDO> permissions) {
		List<CarSysMenuDO> tempList = new ArrayList<>();
		//一级菜单
		for (CarSysMenuDO p : permissions) {
			if(p.getMenuType() == YSTConstants.MENU_TYPE_Button )
				continue;
			if ((p.getParentID() == null) || (p.getParentID().intValue() == 0)) {
				tempList.add(p);
			}
		}
		
		//二级菜单
		for (int i = 0; i < tempList.size(); i++) {
			CarSysMenuDO p1=tempList.get(i);
			for (CarSysMenuDO p : permissions) {
				if (p1.getId().intValue() == p.getParentID().intValue()) {
					if(!isExit(p1.getMenus(),p)){
						p1.getMenus().add(p);
					}
				}	
			}
			if(p1.getMenus()==null||p1.getMenus().size()==0){
				tempList.remove(p1);
				i--;
			}
		}
		return tempList;
	}
	/**
	 * 判断是否存在
	 * @Title: isExit
	 * @Description: TODO
	 * @param menus
	 * @param p 
	 * @return
	 * @return: boolean
	 */
	private boolean isExit(List<CarSysMenuDO> menus, CarSysMenuDO p) {
		for (CarSysMenuDO carSysMenuDO : menus) {
			if(carSysMenuDO.getId()==p.getId()){
				return true;
			}
		}
		return false;
	}

	public Integer getIsAllView() {
		return isAllView;
	}

	public void setIsAllView(Integer isAllView) {
		this.isAllView = isAllView;
	}
}