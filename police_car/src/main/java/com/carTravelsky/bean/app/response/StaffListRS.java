/**  
 * Copyright © 2017 wangyi. All rights reserved.
 *
 * @Title: StaffListRS.java
 * @Prject: carFlTravelsky
 * @Package: com.carTravelsky.bean.app.response
 * @Description: TODO
 * @author: wangyi  
 * @date: 2017年12月6日 下午3:04:09
 * @version: V1.0  
 */
package com.carTravelsky.bean.app.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: StaffListRS
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午3:04:09
 */
public class StaffListRS implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7736621931825324277L;
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("code")
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
