package com.carTravelsky.control.app;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
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

import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AUserService;
import com.carTravelsky.service.app.AppCarFeesService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarFeesController
 * @Description: 规费
 * @author: wangyi
 * @date: 2017年12月4日 上午10:28:24
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/app/fees")
public class AppCarFeesController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AUserService aUserService;
	@Autowired
	private AppCarFeesService appCarFeesService;
	
	/**
	 * @Title: insertOrUpdateFees
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param username			经手人
	 * @param licenseNumber	车牌号
	 * @param moneyName		费用名称
	 * @param paymentMoney	费用金额
	 * @param paymentDate		缴费时间
	 * @param chargeUnit			收费单位
	 * @param paymentType		缴费方式
	 * @param remark				备注
	 * @param peiod					周期月
	 * @return
	 * @return: JsonResponse
	 * @author: THINK  
	 * @date: 2017年12月4日 下午5:55:43
	 */
	@RequestMapping(value="/insertOrUpdateFees",method={RequestMethod.POST})
	public JsonResponse insertOrUpdateFees(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "moneyName",required = true)String moneyName,
			@RequestParam(value = "paymentMoney",required = true)String paymentMoney,
			@RequestParam(value = "paymentDate",required = true)String paymentDate,
			@RequestParam(value = "endDate",required = true)String endDate,
			@RequestParam(value = "chargeUnit",required = true)String chargeUnitID,
			@RequestParam(value = "paymentType",required = true)String paymentType,
			@RequestParam(value = "remark",required = true)String remark,
			@RequestParam(value = "peiod",required = true)String period){
		Map<String,Object> map = new HashMap<String, Object>();
			if(StringUtils.isBlank(licenseNumber)){
				map.put(CodeCondition.CONDITION, Param.LICENSENO_IS_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.LICENSENO_IS_NULL, 
								Param.LICENSENO_IS_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(moneyName)){
				map.put(CodeCondition.CONDITION, Param.FEES_PEYMENTNAME_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.MONEYNAME_NULL, 
								Param.FEES_PEYMENTNAME_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(paymentMoney)){
				map.put(CodeCondition.CONDITION, Param.FEES_PAYMENTMONEY_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.PAYMENTMONEY_NULL, 
								Param.FEES_PAYMENTMONEY_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(paymentDate)){
				map.put(CodeCondition.CONDITION, Param.FEES_PAYMENTDATE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.PAYMENTDATE_NULL, 
								Param.FEES_PAYMENTDATE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(endDate)){
				map.put(CodeCondition.CONDITION, Param.FEES_PAYMENTENDDATE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.PAYMENTENDDATE_NULL, 
								Param.FEES_PAYMENTENDDATE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(chargeUnitID)){
				map.put(CodeCondition.CONDITION, Param.FEES_CHARGEUNIT_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.CHARGEUNIT_NULL, 
								Param.FEES_CHARGEUNIT_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(paymentType)){
				map.put(CodeCondition.CONDITION, Param.FEES_PAYMENTTYPE_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.PAYMENTTYPE_NULL, 
								Param.FEES_PAYMENTTYPE_NULL.getMessage(),map);
			}
			if(StringUtils.isBlank(period)){
				map.put(CodeCondition.CONDITION, Param.FEES_PERIOD_NULL.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.FeesExceptionCode.PERIOD_NULL, 
								Param.FEES_PERIOD_NULL.getMessage(),map);
			}
			//根据登陆名称获取当前用户的ID
			Integer companyId = aUserService.getCompanyId(username);
			//当前登录用户ID
			Integer userId = aUserService.searchByName(username);
			int result = 0;
			try {
				result = appCarFeesService.carAddFeesInsertOrUpdate(
						username, 
						companyId, 
						userId, 
						licenseNumber, 
						moneyName, 
						Float.parseFloat(paymentMoney), 
						DateTools.formatDate(paymentDate, DateTools.DATE_PATTERN_DEFAULT), 
						DateTools.formatDate(endDate, DateTools.DATE_PATTERN_DEFAULT), 
						Integer.parseInt(chargeUnitID), 
						paymentType,
						remark, 
						period);
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
	
	
	/**
	 * @Title: feesNameList
	 * @Description: 规费费用名称列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getFeesNameList",method={RequestMethod.POST})
	public JsonResponse feesNameList(HttpServletRequest request){
		List<GeneralRS> feesNameList  = appCarFeesService.getFeesNameList(CodeCondition.FEES_NAME);
		return JsonResponse.RespSuccess(feesNameList);
	}
	
	
	/**
	 * @Title: feesUnitList
	 * @Description: 缴费单位列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getFeesUnitList",method={RequestMethod.POST})
	public JsonResponse feesUnitList(HttpServletRequest request){
		List<GeneralRS> feesUnitsList  = appCarFeesService.getFeesUnitsList(CodeCondition.UNITS_NAME);
		return JsonResponse.RespSuccess(feesUnitsList);
	}
	
	
	/**
	 * @Title: feesTypeList
	 * @Description: 缴费方式列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getFeesTypeList",method={RequestMethod.POST})
	public JsonResponse feesTypeList(HttpServletRequest request){
		List<GeneralRS> feesTypeList  = appCarFeesService.getFeesTypeList(CodeCondition.FEES_NAME);
		return JsonResponse.RespSuccess(feesTypeList);
	}
}
