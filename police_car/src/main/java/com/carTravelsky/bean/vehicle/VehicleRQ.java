package com.carTravelsky.bean.vehicle;

import java.io.Serializable;


/**
 * @ClassName: VehicleRQ
 * @Description: TODO
 * @author: lipengcheng
 * @date: 2017年10月18日 下午3:15:58
 */
public class VehicleRQ implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6834191268935895507L;
	
	/**
	 * 公司ID
	 */
	private int id;
	/**
	 * 车辆状态
	 */
	private String display;
	/**
	 * 车辆类型
	 */
	private String typeName;
	/**
	 * 搜索关键字
	 */
	private String searchStr;
	/* 由PageBean传递给VehicleRS分页的数据 */
	/**
	 * 页码索引
	 */
	private Integer beginIndex;
	/**
	 * 当前页数
	 */
	private Integer currentPage;  
	/**
	 * 每页显示数量
	 */
	private Integer pageSize; 
	
	public Integer getBeginIndex() {
		if (null != currentPage) {
			return (currentPage-1)*pageSize;
		}
		return null;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
