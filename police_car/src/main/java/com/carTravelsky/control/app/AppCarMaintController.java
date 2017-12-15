package com.carTravelsky.control.app;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AUserService;
import com.carTravelsky.service.app.AppCarMaintService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarArrangeController
 * @Description: 保养记录
 * @author: wangyi
 * @date: 2017年12月4日 上午10:26:12
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/app/maint")
public class AppCarMaintController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AUserService aUserService;
	@Autowired
	private AppCarMaintService appCarMaintService;
	
	/**
	 * @Title: insertOrUpdateMaint
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param username
	 * @param licenseNumber
	 * @param maintainType
	 * @param maintainDate
	 * @param maintainUnit
	 * @param maintainMileage
	 * @param maintainMoney
	 * @param maintainContent
	 * @param remark
	 * @param invoiceUrl
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value="/insertOrUpdateMaint",method={RequestMethod.POST})
	public JsonResponse insertOrUpdateMaint(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "maintainType",required = true)String maintainType,
			@RequestParam(value = "maintainDate",required = true)String maintainDate,
			@RequestParam(value = "maintainEndDate",required = true)String maintainEndDate,
			@RequestParam(value = "maintainUnit",required = true)String maintainUnit,
			@RequestParam(value = "maintainMileage",required = true)String maintainMileage,
			@RequestParam(value = "maintainMoney",required = true)String maintainMoney,
			@RequestParam(value = "maintainContent",required = true)String maintainContent,
			@RequestParam(value = "remark",required = true)String remark,
			@RequestParam(value = "invoiceUrl",required  = false)String invoiceUrl){
			Map<String,Object> map = new HashMap<String, Object>();
			if(StringUtils.isBlank(licenseNumber)){
				map.put(CodeCondition.CONDITION, Param.LICENSENO_IS_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.LICENSENO_IS_NULL, 
								Param.LICENSENO_IS_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainType)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINTYPE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINTYPE_NULL, 
								Param.MAINTAINTYPE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainDate)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINDATE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINDATE_NULL, 
								Param.MAINTAINDATE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainEndDate)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINENDDATE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINENDDATE_NULL, 
								Param.MAINTAINENDDATE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainUnit)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINUNIT_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINUNIT_NULL, 
								Param.MAINTAINUNIT_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainMileage)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINMILEAGE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINMILEAGE_NULL, 
								Param.MAINTAINMILEAGE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainMoney)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINMONEY_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINMONEY_NULL, 
								Param.MAINTAINMONEY_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(maintainContent)){
				map.put(CodeCondition.CONDITION, Param.MAINTAINCONTENT_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.maintainExceptionCode.MAINTAINCONTENT_NULL, 
								Param.MAINTAINCONTENT_NULL.getMessage(),map);
			}
			//根据登陆名称获取当前用户的ID
			Integer companyId = aUserService.getCompanyId(username);
			//当前登录用户ID
			Integer userId = aUserService.searchByName(username);
			int result = 0;
			try {
				result = appCarMaintService.carAddMaintainInsertOrUpdate(
						username, 
						companyId, 
						userId, 
						licenseNumber, 
						maintainType, 
						DateTools.formatDate(maintainDate, DateTools.DATE_PATTERN_DEFAULT), 
						DateTools.formatDate(maintainEndDate, DateTools.DATE_PATTERN_DEFAULT), 
						Integer.parseInt(maintainUnit), 
						Float.parseFloat(maintainMileage), 
						Float.parseFloat(maintainMoney), 
						maintainContent, remark);
			} catch (NumberFormatException | ParseException e) {
				logger.error("保养记录或者更新更新出错{0}", e);
			}
			switch (result) {
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
				default: 
					map.put(CodeCondition.CONDITION, Param.REQUEST_SERVER_TIME_OUT.getMessage());
					return JsonResponse.RespGlobolNullSuccess(map);
		}
		
	}
	
	
}
