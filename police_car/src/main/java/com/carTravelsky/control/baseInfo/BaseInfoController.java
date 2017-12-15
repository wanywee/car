package com.carTravelsky.control.baseInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基本信息的公用跳转
 * 
 * @author yanlinglong
 *
 */
@Controller
@RequestMapping("/baseInfo")
public class BaseInfoController {

	@RequestMapping(value = "/{pagePath}")
	public String toPage(@PathVariable("pagePath") String pagePath) {
		String pagePathString = "baseInfo/" + pagePath;
		return pagePathString;
	}
	
	@RequestMapping(value = "/detialVehicleRecords/{pagePath}")
	public String toPage2(@PathVariable("pagePath") String pagePath) {
		String pagePathString = "baseInfo/detialVehicleRecords/" + pagePath;
		return pagePathString;
	}

}
