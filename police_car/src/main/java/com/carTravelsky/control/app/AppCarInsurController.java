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
import com.carTravelsky.service.app.AppCarInsurService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarInsurController
 * @Description: 保险记录
 * @author: wangyi
 * @date: 2017年12月4日 上午10:30:05
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/app/insur")
public class AppCarInsurController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarInsurService appCarInsurService;
	@Autowired
	private AUserService aUserService;
	
	/**
	 * @Title: insertOrUpdateInsur
	 * @Description: 存入或修改保险记录数据
	 * @param request
	 * @param response
	 * @param username					经手人
	 * @param licenseNumber			车牌号
	 * @param policyNumber				保单号
	 * @param insuranceType			保险种类
	 * @param insureDate					投保日期
	 * @param insuranceCompany	保险单位
	 * @param insureMoney				投保金额
	 * @param insuranceRemark		险种备注
	 * @param remark						备注
	 * @param invoiceUrl					发票图片位置
	 * @return
	 * @return: JsonResponse
	 * @author: lipengcheng  
	 * @date: 2017年12月4日 下午2:59:00
	 */
	@RequestMapping(value="/insertOrUpdateInsur",method={RequestMethod.POST})
	public JsonResponse insertOrUpdateInsur(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "policyNumber",required = true)String policyNumber,
			@RequestParam(value = "insuranceType",required = true)String insuranceType,
			@RequestParam(value = "insureDate",required = true)String insureDate,
			@RequestParam(value = "endDate",required = true)String endDate,
			@RequestParam(value = "insuranceCompanyId",required = true)String insuranceCompanyId,
			@RequestParam(value = "insureMoney",required = true)String insureMoney,
			@RequestParam(value = "insuranceRemark",required = true)String insuranceRemark,
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
		if(StringUtils.isBlank(policyNumber)){
			map.put(CodeCondition.CONDITION, Param.PONICYNUMBER_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.PONICYNUMBER_IS_NULL, 
							Param.PONICYNUMBER_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insuranceType)){
			map.put(CodeCondition.CONDITION, Param.TYPE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.TYPE_IS_NULL, 
							Param.TYPE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insureDate)){
			map.put(CodeCondition.CONDITION, Param.INSUREDATE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.INSUREDATE_IS_NULL, 
							Param.INSUREDATE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(endDate)){
			map.put(CodeCondition.CONDITION, Param.ENDDATE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.ENDDATE_IS_NULL, 
							Param.ENDDATE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insuranceCompanyId)){
			map.put(CodeCondition.CONDITION, Param.COMPANY_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.COMPANY_IS_NULL, 
							Param.COMPANY_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insureMoney)){
			map.put(CodeCondition.CONDITION, Param.INSUREMONEY_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.INSUREMONEY_IS_NULL, 
							Param.INSUREMONEY_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insuranceRemark)){
			map.put(CodeCondition.CONDITION, Param.INSURANCEREMARK_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.insuranceExceptionCode.INSURANCEREMARK_IS_NULL, 
							Param.INSURANCEREMARK_IS_NULL.getMessage(),map);
		}
		//根据登陆名称获取当前用户的ID
		Integer companyId = aUserService.getCompanyId(username);
		//当前登录用户ID
		Integer userId = aUserService.searchByName(username);
		int result = 0;
		try {
			result = appCarInsurService.carAddInsureInsertOrUpdate(
					username, 
					userId, 
					companyId,
					licenseNumber, 
					policyNumber,
					Integer.parseInt(insuranceType), 
					DateTools.formatDate(insureDate, DateTools.DATE_PATTERN_DEFAULT), 
					DateTools.formatDate(endDate, DateTools.DATE_PATTERN_DEFAULT), 
					Integer.parseInt(insuranceCompanyId), 
					Float.parseFloat(insureMoney), 
					insuranceRemark, 
					remark);
		} catch (NumberFormatException | ParseException e) {
			logger.error("保险记录或者更新更新出错{0}", e);
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
	 * @Title: insurCompanyList
	 * @Description: 保险公司列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getInsurCompanyList",method={RequestMethod.POST})
	public JsonResponse insurCompanyList(HttpServletRequest request){
		List<GeneralRS> companyList = appCarInsurService.getInsurCompanyList(CodeCondition.COMPANY_TYPE);
		return JsonResponse.RespSuccess(companyList);
	}
	
	
	/**
	 * @Title: insurCompanyList
	 * @Description: 保险种类
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getInsurTypeList",method={RequestMethod.POST})
	public JsonResponse insurTypeList(HttpServletRequest request){
		List<GeneralRS> typeList = appCarInsurService.getInsurTypeList(CodeCondition.INSURANCETYPE);
		return JsonResponse.RespSuccess(typeList);
	}
	
	
	
	/**
	 * @Title: handlersList
	 * @Description: 经手人列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getHandlersList",method={RequestMethod.POST})
	public JsonResponse handlersList(HttpServletRequest request){
		List<GeneralRS> handlerList = appCarInsurService.getHandlersList();
		return JsonResponse.RespSuccess(handlerList);
	}
}
