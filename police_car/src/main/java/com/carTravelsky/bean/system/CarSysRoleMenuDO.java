package com.carTravelsky.bean.system;


public class CarSysRoleMenuDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /**  */
    private Integer id;
    /** 角色ID */
    private Integer roleID;
    /** 菜单ID */
    private Integer menuID;

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
     * 获取角色ID
     * @return roleID
     **/
    public Integer getRoleID(){
        return this.roleID;
    }

    /**
     * 设置角色ID
     * @param roleID
     **/
    public void setRoleID(Integer roleID){
        this.roleID = roleID;
    }

    /**
     * 获取菜单ID
     * @return menuID
     **/
    public Integer getMenuID(){
        return this.menuID;
    }

    /**
     * 设置菜单ID
     * @param menuID
     **/
    public void setMenuID(Integer menuID){
        this.menuID = menuID;
    }


    public CarSysRoleMenuDO clone(){
        try{
            return (CarSysRoleMenuDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}