package com.carTravelsky.utils.app;
import java.util.List;

/**
 * @ClassName: Page
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年3月20日 上午10:57:38
 * @param <T>
 */
public class AppPage<T>{

	/**当前页数*/
	private int ipage ;
	/**每页显示大小*/
	private int pageSize ;
	/**总页数*/
	private int pageCount;
	/**总行数*/
	private int rowCount;
	/**当前行数据*/
	private int localRow ;
	/**排序类型*/
	private String sortType ;
	/**排序字段名*/
	private String sortField ;
	/**参数集*/
	private T param ;
	/**参数集*/
	private List<T> listParam ;
	
	/***/
	private Object params;
	
	private List<String> pid ;

	public int getIpage() {
		return ipage;
	}

	public void setIpage(int ipage) {
		this.ipage = ipage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getLocalRow() {
		this.localRow = (ipage - 1) * pageSize;
		return localRow ;
	}

	public void setLocalRow(int localRow) {
		this.localRow = localRow;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}


	public List<T> getListParam() {
		return listParam;
	}

	public void setListParam(List<T> listParam) {
		this.listParam = listParam;
	}

	public AppPage(int ipage, int pageSize, int pageCount,
			int rowCount, int localRow, String sortType,
			String sortField, T param, List<T> listParam) {
		super();
		this.ipage = ipage;
		this.pageSize = pageSize;
		this.pageCount = pageCount;
		this.rowCount = rowCount;
		this.localRow = localRow;
		this.sortType = sortType;
		this.sortField = sortField;
		this.param = param;
		this.listParam = listParam;
	}

	public List<String> getPid() {
		return pid;
	}

	public void setPid(List<String> pid) {
		this.pid = pid;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

}