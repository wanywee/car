package com.carTravelsky.bean.system;

import com.stopec.common.orm.BasicDO;
import com.stopec.common.validate.Validate;

public class EmpDO extends BasicDO {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**  */
    @Validate(rule = "{max[100]}", comment = "编号")
    private Integer empID;
    /**  */
    private String empNum;
    /**  */
    private String empName;
    /**  */
    private Integer empRole;
    /**  */
    private String empRank;
    /**  */
    private String empPass;
    
   // 
    private Integer roleID;
    
    public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	/**
     * 获取
     * @return empID
     **/
    public Integer getEmpID(){
        return this.empID;
    }

    /**
     * 设置
     * @param empID
     **/
    public void setEmpID(Integer empID){
        this.empID = empID;
    }

    /**
     * 获取
     * @return empNum
     **/
    public String getEmpNum(){
        return this.empNum;
    }

    /**
     * 设置
     * @param empNum
     **/
    public void setEmpNum(String empNum){
        this.empNum = empNum;
    }

    /**
     * 获取
     * @return empName
     **/
    public String getEmpName(){
        return this.empName;
    }

    /**
     * 设置
     * @param empName
     **/
    public void setEmpName(String empName){
        this.empName = empName;
    }

    /**
     * 获取
     * @return empRole
     **/
    public Integer getEmpRole(){
        return this.empRole;
    }

    /**
     * 设置
     * @param empRole
     **/
    public void setEmpRole(Integer empRole){
        this.empRole = empRole;
    }

    /**
     * 获取
     * @return empRank
     **/
    public String getEmpRank(){
        return this.empRank;
    }

    /**
     * 设置
     * @param empRank
     **/
    public void setEmpRank(String empRank){
        this.empRank = empRank;
    }

    /**
     * 获取
     * @return empPass
     **/
    public String getEmpPass(){
        return this.empPass;
    }

    /**
     * 设置
     * @param empPass
     **/
    public void setEmpPass(String empPass){
        this.empPass = empPass;
    }

    public EmpDO clone(){
        try{
            return (EmpDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

	@Override
	public String toString() {
		return "EmpDO [empID=" + empID + ", empNum=" + empNum + ", empName=" + empName + ", empRole=" + empRole
				+ ", empRank=" + empRank + ", empPass=" + empPass + ", roleID=" + roleID + "]";
	}
 
}