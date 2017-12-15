package com.carTravelsky.utils.app;

import java.util.List;

/**
 * @ClassName: Results
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年3月20日 上午10:57:58
 * @param <T>
 */
public class Results<T> {
	
	public static final int SUCCESS_CODE=1;
    public static final String SUCCESS_MSG="success";

	/**当前页数*/
	private int ipage ;
	/**每页显示大小*/
	private int pageSize ;
	/**总页数*/
	private int pageCount;
	/**总行数*/
	private int rowCount;
	/**结果集*/
	private List<T> result;
	
	public Results() {
		super();
	}

	public Results(int ipage, int pageSize, int rowCount, List<T> result) {
		this.ipage = ipage;
		this.pageSize = pageSize;
		this.rowCount = rowCount;
		this.result = result;
	}

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

	public void setPageCount(int rowCount) {
		if (rowCount > 0) {
			this.rowCount = rowCount;
			int count = rowCount / pageSize;
			if (rowCount % pageSize > 0)
				count++;
			this.pageCount = count;
		} else {
			this.pageCount = 0;
		}
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}


}