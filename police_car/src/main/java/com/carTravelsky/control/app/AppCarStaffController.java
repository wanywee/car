package com.carTravelsky.control.app;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.bean.app.response.CarAndStaffRS;
import com.carTravelsky.service.app.AppCarStaffService;
import com.carTravelsky.utils.app.JsonResponse;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarStaffController
 * @Description: 车辆  警员 信息表
 * @author: wangyi
 * @date: 2017年12月5日 下午4:13:57
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/staffcar")
public class AppCarStaffController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarStaffService appCarStaffService;
	
	
	/**
	 * @Title: getAllStaffInfos
	 * @Description: 警员信息
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value="/getAllStaffInfo",method={RequestMethod.POST})
	public JsonResponse getAllStaffInfos(HttpServletRequest request){
		List<CarAndStaffRS> staffs = appCarStaffService.getAllStaffInfos();
		return JsonResponse.RespSuccess(staffs);
	}
	
	/**
	 * @Title: getAllCarInfos
	 * @Description: 车辆信息
	 * @param request
	 * @return: JsonResponse
	 * @date: 2017年12月5日 下午4:33:47
	 */
	@RequestMapping(value="/getAllCarInfo",method={RequestMethod.POST})
	public JsonResponse getAllCarInfos(HttpServletRequest request){
		List<CarAndStaffRS> listCar = appCarStaffService.getListCar();
		return JsonResponse.RespSuccess(listCar);
	}
}
