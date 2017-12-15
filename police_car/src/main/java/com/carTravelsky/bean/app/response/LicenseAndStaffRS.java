/**  
 * Copyright © 2017 wangyi. All rights reserved.
 *
 * @Title: LicenseAndStaffRS.java
 * @Prject: carFlTravelsky
 * @Package: com.carTravelsky.bean.app.response
 * @Description: TODO
 * @author: wangyi  
 * @date: 2017年12月6日 下午3:34:28
 * @version: V1.0  
 */
package com.carTravelsky.bean.app.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: LicenseAndStaffRS
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午3:34:28
 */
public class LicenseAndStaffRS implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4808613212873971797L;
	
	@JsonProperty("id")
	private Integer id;
	

	@JsonProperty("deptmentName")
	private String deptmentName;
	
	@JsonProperty("detailList")
	private List<GeneralRS> detailList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptmentName() {
		return deptmentName;
	}

	public void setDeptmentName(String deptmentName) {
		this.deptmentName = deptmentName;
	}

	public List<GeneralRS> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<GeneralRS> detailList) {
		this.detailList = detailList;
	}


	

}
