package com.carTravelsky.bean.system;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stopec.common.validate.Validate;

public class CarSysMenuDO extends com.stopec.common.orm.BasicDO {
	private static final long serialVersionUID = 1L;
	/** ID */
	private Integer id;
	/** 菜单名称 */
	@Validate(rule = "{required,maxSize[64]}", regexp="^[\u4E00-\u9FA5A-Za-z0-9-_]+$", comment="菜单名称 ")
	private String menuName;
	/** 菜单类型1为菜单 2为按钮 */
	private Integer menuType;
	/** 上级ID 关联本表ID */
	private Integer parentID;
	/** 上级名称 */
	private String parentName;
	/** 菜单路径 */
	@Validate(rule = "{maxSize[64]}", regexp="^[\u4E00-\u9FA5A-Za-z0-9-_/]+$", comment="菜单路径 ")
	private String menuUrl;
	/** 创建时间 */
	private Date createTime;
	/** 创建者 */
	private String creator;
	/** 修改时间 */
	private Date updateTime;
	/** 修改人员 */
	private String updateOperator;
	/** 是否使用1为启用 2为停用 */
	private Integer status;
	/** 备注 */
	private String comments;
	/** 1无、2跳转、3弹窗 */
	private Integer openType;
	/** 菜单图标 */
	private String menuIcon;
	/** 菜单排序 */
	private String menuSort;

	// 新增属性
	private List<CarSysMenuDO> menus;

	/**
	 * 获取ID
	 * 
	 * @return id
	 **/
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置ID
	 * 
	 * @param id
	 **/
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取菜单名称
	 * 
	 * @return menuName
	 **/
	public String getMenuName() {
		return this.menuName;
	}

	/**
	 * 设置菜单名称
	 * 
	 * @param menuName
	 **/
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * 获取菜单类型1为菜单 2为按钮
	 * 
	 * @return menuType
	 **/
	public Integer getMenuType() {
		return this.menuType;
	}

	/**
	 * 设置菜单类型1为菜单 2为按钮
	 * 
	 * @param menuType
	 **/
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	/**
	 * 获取上级ID 关联本表ID
	 * 
	 * @return parentID
	 **/
	public Integer getParentID() {
		return this.parentID;
	}

	/**
	 * 设置上级ID 关联本表ID
	 * 
	 * @param parentID
	 **/
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	/**
	 * 获取上级名称
	 * 
	 * @return parentName
	 **/
	public String getParentName() {
		return this.parentName;
	}

	/**
	 * 设置上级名称
	 * 
	 * @param parentName
	 **/
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 获取菜单路径
	 * 
	 * @return menuUrl
	 **/
	public String getMenuUrl() {
		return this.menuUrl;
	}

	/**
	 * 设置菜单路径
	 * 
	 * @param menuUrl
	 **/
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return createTime
	 **/
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createTime
	 **/
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取创建者
	 * 
	 * @return creator
	 **/
	public String getCreator() {
		return this.creator;
	}

	/**
	 * 设置创建者
	 * 
	 * @param creator
	 **/
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return updateTime
	 **/
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param updateTime
	 **/
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取修改人员
	 * 
	 * @return updateOperator
	 **/
	public String getUpdateOperator() {
		return this.updateOperator;
	}

	/**
	 * 设置修改人员
	 * 
	 * @param updateOperator
	 **/
	public void setUpdateOperator(String updateOperator) {
		this.updateOperator = updateOperator;
	}

	/**
	 * 获取是否使用1为启用 2为停用
	 * 
	 * @return status
	 **/
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * 设置是否使用1为启用 2为停用
	 * 
	 * @param status
	 **/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取备注
	 * 
	 * @return comments
	 **/
	public String getComments() {
		return this.comments;
	}

	/**
	 * 设置备注
	 * 
	 * @param comments
	 **/
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 获取1无、2跳转、3弹窗
	 * 
	 * @return openType
	 **/
	public Integer getOpenType() {
		return this.openType;
	}

	/**
	 * 设置1无、2跳转、3弹窗
	 * 
	 * @param openType
	 **/
	public void setOpenType(Integer openType) {
		this.openType = openType;
	}

	/**
	 * 获取菜单图标
	 * 
	 * @return menuIcon
	 **/
	public String getMenuIcon() {
		return this.menuIcon;
	}

	/**
	 * 设置菜单图标
	 * 
	 * @param menuIcon
	 **/
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/**
	 * 获取菜单排序
	 * 
	 * @return menuSort
	 **/
	public String getMenuSort() {
		return this.menuSort;
	}

	/**
	 * 设置菜单排序
	 * 
	 * @param menuSort
	 **/
	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

	public CarSysMenuDO clone() {
		try {
			return (CarSysMenuDO) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	// 二级菜单
	public List<CarSysMenuDO> getMenus() {
		if (this.menus == null) {
			this.menus = new ArrayList<CarSysMenuDO>();
		}
		return menus;
	}

	public void setMenus(List<CarSysMenuDO> menus) {
		this.menus = menus;
	}
}