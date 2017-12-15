package com.carTravelsky.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carTravelsky.bean.system.PageBean;

/**
 * @author admin
 * 
 *         返回参数封装pojo
 */
public class ReturnDataInfo<T> {

	private boolean m_flag = false;
	private String m_message;
	private Integer statusCode = 200;
	private T result;
	private Long totalcount;
	private List<T> results;
	private Integer offset;
	private Integer limit;
	private Long timestamp = new Date().getTime();
	private PageBean pageBean;
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();

	   ReturnDataInfo.createSuccessfulSingleExecuteResult(map);
	}

	public ReturnDataInfo() {

	}

	public ReturnDataInfo(boolean flag, String message) {
		m_flag = flag;
		m_message = message;
	}

	public boolean isFlag() {
		return m_flag;
	}

	public void setFlag(boolean flag) {
		m_flag = flag;
	}

	public String getMessage() {
		return m_message;
	}

	public void setMessage(String message) {
		m_message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public static <T> ReturnDataInfo<T> createSuccessfulSingleExecuteResult(T entry) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(true);
		info.setResult(entry);
		info.setTotalcount(1l);
		return info;
	}

	public static <T> ReturnDataInfo<T> createSuccessfulSingleExecuteMessage(String message) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(true);
		info.setMessage(message);
		info.setTotalcount(1l);
		return info;
	}

	public static <T> ReturnDataInfo<T> createSuccessfulExecuteResults(List<T> entry) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(true);
		info.setResults(entry);
		info.setTotalcount(Long.valueOf(entry.size()));
		return info;
	}
	
	public static <T> ReturnDataInfo<T> createSuccessfulExecuteResults(List<T> entry,PageBean pageBean) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(true);
		info.setResults(entry);
		info.setPageBean(pageBean);
		info.setTotalcount(Long.valueOf(entry.size()));
		return info;
	}

	public static <T> ReturnDataInfo<T> createSuccessfulExecuteResults(List<T> entry, Integer offset, Integer limit,
			Long totalcount) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(true);
		info.setResults(entry);
		info.setLimit(limit);
		info.setOffset(offset);
		info.setTotalcount(totalcount);
		return info;
	}

	public static <T> ReturnDataInfo<T> createFailedExecuteResult(String message) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(false);
		info.setStatusCode(500);
		info.setMessage(message);
		return info;
	}

	public static <T> ReturnDataInfo<T> createFailedExecuteResult(Integer statusCode, String message) {
		ReturnDataInfo<T> info = new ReturnDataInfo<T>();
		info.setFlag(false);
		info.setStatusCode(statusCode);
		info.setMessage(message);
		return info;
	}

	public Long getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

}
