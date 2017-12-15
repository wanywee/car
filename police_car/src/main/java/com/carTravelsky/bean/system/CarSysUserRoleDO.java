package com.carTravelsky.bean.system;


public class CarSysUserRoleDO extends com.stopec.common.orm.BasicDO {
    private static final long serialVersionUID = 1L;
    /** 本表ID */
    private Integer id;
    /** 用户ID */
    private Integer userID;
    /** 角色ID */
    private Integer roleID;

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
     * 获取用户ID
     * @return userID
     **/
    public Integer getUserID(){
        return this.userID;
    }

    /**
     * 设置用户ID
     * @param userID
     **/
    public void setUserID(Integer userID){
        this.userID = userID;
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


    public CarSysUserRoleDO clone(){
        try{
            return (CarSysUserRoleDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}