package com.carTravelsky.bean.app.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: CarAndDeptList
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午2:14:47
 */
public class CarAndDeptListRS implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5404535172436474561L;
	
	@JsonProperty("id")
	private Integer id;
	

	@JsonProperty("deptmentName")
	private String deptmentName;
	
	@JsonProperty("detailList")
	private List<CarDetailListRS> detailList;

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

	public List<CarDetailListRS> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<CarDetailListRS> detailList) {
		this.detailList = detailList;
	}
	

}
