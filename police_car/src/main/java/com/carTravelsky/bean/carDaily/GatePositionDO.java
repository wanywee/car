package com.carTravelsky.bean.carDaily;

import java.util.Date;

import com.stopec.common.validate.Validate;

public class GatePositionDO extends com.stopec.common.orm.BasicDO{
	

	/** id */
	private Integer id;
	
	/** 公司ID */
	private int companyId;
	
	/** 停机位号 */
	@Validate(rule="{required,maxSize[32]}",comment="停机位号")
	private String gatePositionCode;
	
	/** 坐标 */
	private String coord;
	
	/** 扫描半径 */
	@Validate(rule="{required,maxSize[32]}",comment="扫描半径",regexp="^?\\d+(\\.\\d+)?$")
	private Float scanRadii;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 创建人 */
	private String createPeople;
	
	/** 修改时间 */
	private Date updateTime;
	
	/** 修改人 */
	private String updatePeople;
	
	/** 删除状态码 */
	private String deleteCode;
	
	/** 备注 */
	private String remark;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getGatePositionCode() {
		return gatePositionCode;
	}


	public void setGatePositionCode(String gatePositionCode) {
		this.gatePositionCode = gatePositionCode;
	}


	public String getCoord() {
		return coord;
	}


	public void setCoord(String coord) {
		this.coord = coord;
	}


	public Float getScanRadii() {
		return scanRadii;
	}


	public void setScanRadii(Float scanRadii) {
		this.scanRadii = scanRadii;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCreatePeople() {
		return createPeople;
	}


	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getUpdatePeople() {
		return updatePeople;
	}


	public void setUpdatePeople(String updatePeople) {
		this.updatePeople = updatePeople;
	}


	public String getDeleteCode() {
		return deleteCode;
	}


	public void setDeleteCode(String deleteCode) {
		this.deleteCode = deleteCode;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public GatePositionDO clone(){
        try{
            return (GatePositionDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
	
}
