package com.carTravelsky.bean.system;
import java.util.HashMap;
import java.util.Map;

import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public class Page extends PageBounds {
	public Map<String, Object> putResultObj(Object obj,int totalSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("total",totalSize);
		result.put("rows", obj);
		return result;
	}
	 public Page(int offset, int limit) {
		super(offset, limit);
	}
	 public Page() {
	}
	public static void main(String[] args) {
		Page page = new Page(0, 10);
		System.out.println(page);
	}
}

