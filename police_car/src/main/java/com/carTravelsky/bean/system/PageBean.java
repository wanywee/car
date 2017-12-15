package com.carTravelsky.bean.system;

import java.util.ArrayList; 
import java.util.List;

import com.carTravelsky.bean.vehicle.VehicleRS;

/**
 * @ClassName: 分页Bean
 * @Description: TODO
 * @author: lipengcheng
 * @date: 2017年10月19日 下午1:50:44
 */
public class PageBean {

	//当前页码
	private Integer currentPage = 1;
	//总数量
	private Integer totalCount = 0;
	//每页显示数量
	private Integer pageSize = 3;
	//首页(第一页)
	private Integer firstPage = 1;
	//上一页索引
	private Integer previousPage;
	//下一页索引
	private Integer nextPage;
	//尾页(最后一页)
	private Integer lastPage;
	//总页数
	private Integer totalPage;
	//当前索引
	private Integer beginIndex;
	//当前页数量
	private Integer currentCount;
	//当前分页的数据
	private List<VehicleRS> list = new ArrayList<>();
	
	
	public PageBean(Integer currentPage, Integer totalCount, Integer pageSize) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		
		//计算总页数
		this.totalPage = totalCount%pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;
//		System.out.println("一共有："+totalPage+"页");
		//尾页
		lastPage = totalPage;
		//上一页(索引):当前页-1<1?1:当前页-1
		previousPage = currentPage - 1 < 1 ? 1 :currentPage - 1;
		//下一页(索引):当前页+1>尾页?尾页:当前页+1
		nextPage = currentPage +1 > lastPage ? lastPage :currentPage + 1;
	}
	
	@Override
	public String toString() {
		return "当前第"+currentPage+"页/共"+totalPage+"页";
	}
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}
	public Integer getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(Integer previousPage) {
		this.previousPage = previousPage;
	}
	public Integer getNextPage() {
		return nextPage;
	}
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	public Integer getLastPage() {
		return lastPage;
	}
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<VehicleRS> getList() {
		return list;
	}
	public void setList(List<VehicleRS> list) {
		this.list = list;
	}

	public Integer getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}
	
}
