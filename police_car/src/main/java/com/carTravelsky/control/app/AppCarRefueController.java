package com.carTravelsky.control.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AUserService;
import com.carTravelsky.service.app.AppCarRefueService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarRefueController
 * @Description: 加油记录
 * @author: wangyi
 * @date: 2017年12月4日 上午10:17:00
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/addOil")
public class AppCarRefueController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired 
	private AppCarRefueService appCarRefueService;
	@Autowired
	private AUserService aUserService;
	
	

	/**
	 * @Title: insertOrUpdateCarRefue
	 * @Description: TODO
	 * @param username 登陆用户名
	 * @param licenseNumber 车牌号
	 * @param oilType 油料类型
	 * @param addOilNumber 油量
	 * @param addOilMoney 金额
	 * @param addOilTime 加油时间
	 * @param invoiceUrl 图片
	 * @param handlerName 经手人名称
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/insertOrUpdateCarRefue",method={RequestMethod.POST})
	public JsonResponse insertOrUpdateCarRefue(
			@RequestParam(value = "username",required = true)String username,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "oilType",required = true)String oilType,
			@RequestParam(value = "addOilNumber",required = true)String addOilNumber,
			@RequestParam(value = "addOilMoney",required = true)String addOilMoney,
			@RequestParam(value = "addOilTime",required = true)String addOilTime,
			@RequestParam(value = "handlerName",required  = false)String handlerName,
			@RequestParam(value = "invoiceUrl",required  = false)String invoiceUrl
			){
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(licenseNumber)){
			map.put(CodeCondition.CONDITION, Param.LICENSENO_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.LICENSENO_IS_NULL, 
							Param.LICENSENO_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(oilType)){
			map.put(CodeCondition.CONDITION, Param.OILTYPE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.OILTYPE_IS_NULL, 
							Param.OILTYPE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(addOilNumber)){
			map.put(CodeCondition.CONDITION, Param.ADDOILNUMBER_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ADDOILNUMBER_IS_NULL, 
							Param.ADDOILNUMBER_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(addOilMoney)){
			map.put(CodeCondition.CONDITION, Param.ADDOILMONEY_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ADDOILMONEY_IS_NULL, 
							Param.ADDOILMONEY_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(addOilTime)){
			map.put(CodeCondition.CONDITION, Param.ADDOILTIME_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ADDOILTIME_IS_NULL, 
							Param.ADDOILTIME_IS_NULL.getMessage(),map);
		}
		//根据登陆名称获取当前用户的ID
		Integer companyId = aUserService.getCompanyId(username);
		//当前登录用户ID
		Integer userId = aUserService.searchByName(username);
		//获取经手人名称
		Integer handlerId = aUserService.getHandlerIdByName(handlerName);
		if(CodeCondition.ERRO == handlerId){
			map.put(CodeCondition.CONDITION, Param.HADNLER_ID_IS_NULL.getMessage());
			return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.HADNLER_ID_IS_NULL, 
								Param.HADNLER_ID_IS_NULL.getMessage(),map);
		}
		int result = 0;
		try {
			result = appCarRefueService
							.carAddOilInsertOrUpdate(
									handlerName,
									handlerId,
									username,
									companyId,
									userId,
									licenseNumber,
									oilType,
									Float.valueOf(addOilNumber),
									Float.valueOf(addOilMoney),
									DateTools.stringToDate(addOilTime, DateTools.DATE_PATTERN_DEFAULT));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		switch(result){
		case CodeCondition.FIRST:
			map.put(CodeCondition.CONDITION, Param.INSERT_DATA_SUCCESSED.getMessage());
				return JsonResponse.RespSuccess(map);
		case CodeCondition.SECOND:
			map.put(CodeCondition.CONDITION, Param.INSERT_DATA_FAILED.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.INSERT_DATA_FAILED, 
								Param.INSERT_DATA_FAILED.getMessage(),map);
		case CodeCondition.THIRD:
			map.put(CodeCondition.CONDITION, Param.UPDATE_DATA_SUCCESSED.getMessage());
				return JsonResponse.RespSuccess(map);
		case CodeCondition.FOUTH:
			map.put(CodeCondition.CONDITION, Param.UPDATE_DATA_FAILED.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.UPDATE_DATA_FAILED, 
								Param.UPDATE_DATA_FAILED.getMessage(),map);
		case CodeCondition.FIFTH:
			map.put(CodeCondition.CONDITION, Param.CAR_IS_NOT_BEING.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.CAR_IS_NOT_BEING, 
								Param.CAR_IS_NOT_BEING.getMessage(),map);
		default: 
			map.put(CodeCondition.CONDITION, Param.REQUEST_SERVER_TIME_OUT.getMessage());
			return JsonResponse.RespGlobolNullSuccess(map);
		}
	}
	
	
	/**
	 * @Title: getOilTypeList
	 * @Description: 油料列表
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getAllOilTypeList",method={RequestMethod.POST})
	public JsonResponse getOilTypeList(HttpServletRequest request){
		List<GeneralRS> oils = appCarRefueService.getOilTypeList(CodeCondition.OIL_NAME);
		return JsonResponse.RespSuccess(oils);
		
	}
}
