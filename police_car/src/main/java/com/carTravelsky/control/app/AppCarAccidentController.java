package com.carTravelsky.control.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AppCarAccidentService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarAccidentController
 * @Description: 事故记录
 * @author: wangyi
 * @date: 2017年12月4日 上午10:20:47
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/accident")
public class AppCarAccidentController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarAccidentService appCarAccidentService;
	
	/**
	 * @Title: accidentInsertOrUpdate
	 * @Description: 事故记录
	 * @param username 登陆用户名
	 * @param licenseNumber 车牌号
	 * @param driverName 司机名称
	 * @param accidentDate 事故时间
	 * @param webearMoney 我方承担金额
	 * @param opbearMoney 对方承担金额
	 * @param region 区域
	 * @param insuranceMoney 保险金额
	 * @param happenAddress 事故发生地点
	 * @param accidentExplain 事故说明
	 * @param ourSituation 我方情况
	 * @param otherSituation 对方情况
	 * @param treatmentResult 处理结果
	 * @param remark备注
	 * @param URL 图片
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/accidentInsertOrUpdate",method={RequestMethod.POST})
	public JsonResponse accidentInsertOrUpdate(
			@RequestParam(value = "username",required = true)String username,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "driverName",required = true)String driverName,
			@RequestParam(value = "accidentDate",required = true)String accidentDate,
			@RequestParam(value = "webearMoney",required = true)String webearMoney,
			@RequestParam(value = "opbearMoney",required = true)String opbearMoney,
			@RequestParam(value = "region",required = false)String region,
			@RequestParam(value = "insuranceMoney",required = true)String insuranceMoney,
			@RequestParam(value = "happenAddress",required = true)String happenAddress,
			@RequestParam(value = "accidentExplain",required = true)String accidentExplain,
			@RequestParam(value = "ourSituation",required = true)String ourSituation,
			@RequestParam(value = "otherSituation",required = true)String otherSituation,
			@RequestParam(value = "treatmentResult",required = true)String treatmentResult,
			@RequestParam(value = "remark",required = true)String remark,
			@RequestParam(value = "URL",required = false)String URL
			){
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(licenseNumber)){
			map.put(CodeCondition.CONDITION, Param.LICENSENO_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.LICENSENO_IS_NULL, 
							Param.LICENSENO_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(driverName)){
			map.put(CodeCondition.CONDITION, Param.DRIVER_NAME_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.DRIVER_NAME_IS_NULL, 
							Param.DRIVER_NAME_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(accidentDate)){
			map.put(CodeCondition.CONDITION, Param.ACCIDENT_TIME_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ACCIDENT_TIME_IS_NULL, 
							Param.ACCIDENT_TIME_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(webearMoney)){
			map.put(CodeCondition.CONDITION, Param.WE_BEAR_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.WE_BEAR_IS_NULL, 
							Param.WE_BEAR_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(opbearMoney)){
			map.put(CodeCondition.CONDITION, Param.OP_BEAR_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.OP_BEAR_IS_NULL, 
							Param.OP_BEAR_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(insuranceMoney)){
			map.put(CodeCondition.CONDITION, Param.INSUR_MONEY_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.INSUR_MONEY_IS_NULL, 
							Param.INSUR_MONEY_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(happenAddress)){
			map.put(CodeCondition.CONDITION, Param.HAPPEN_PLACE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.HAPPEN_PLACE_IS_NULL, 
							Param.HAPPEN_PLACE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(accidentExplain)){
			map.put(CodeCondition.CONDITION, Param.ACCIDENT_CONTENT_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ACCIDENT_CONTENT_IS_NULL, 
							Param.ACCIDENT_CONTENT_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(ourSituation)){
			map.put(CodeCondition.CONDITION, Param.OUR_SITU_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.OUR_SITU_IS_NULL, 
							Param.OUR_SITU_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(otherSituation)){
			map.put(CodeCondition.CONDITION, Param.OTHER_SITU_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.OTHER_SITU_IS_NULL, 
							Param.OTHER_SITU_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(treatmentResult)){
			map.put(CodeCondition.CONDITION, Param.TREAT_RESULT_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.TREAT_RESULT_IS_NULL, 
							Param.TREAT_RESULT_IS_NULL.getMessage(),map);
		}
		int result = 0;
		try {
			//根据车牌号获取车辆Id
			Integer carId  = appCarAccidentService.searchCarIdByNumber(licenseNumber);
			if(CodeCondition.NULL == carId){
				map.put(CodeCondition.CONDITION, Param.CAR_IS_NOT_BEING.getMessage());
				return JsonResponse
						.RespFail(
								ExceptionCode.CarExceptionCode.CAR_IS_NOT_BEING, 
								Param.CAR_IS_NOT_BEING.getMessage(),map);
			}
			CarDailyAccidentRecordDO carDailyAccidentRecordDO = new CarDailyAccidentRecordDO();
			carDailyAccidentRecordDO.setCarID(carId);
			carDailyAccidentRecordDO.setAccidentDate(DateTools.stringToDate(accidentDate, DateTools.DATE_PATTERN_DAY));
			carDailyAccidentRecordDO.setAccidentExplain(accidentExplain);
			carDailyAccidentRecordDO.setDeleteCode(CodeCondition.SUCCEED);
			carDailyAccidentRecordDO.setDriver(driverName);
			carDailyAccidentRecordDO.setHappenAddress(happenAddress);
			carDailyAccidentRecordDO.setInsuranceMoney(Float.valueOf(insuranceMoney));
			carDailyAccidentRecordDO.setLicenseno(licenseNumber);
			carDailyAccidentRecordDO.setOpbearMoney(Float.valueOf(opbearMoney));
			carDailyAccidentRecordDO.setOtherSituation(otherSituation);
			carDailyAccidentRecordDO.setOurSituation(ourSituation);
			carDailyAccidentRecordDO.setRemark(remark);
			carDailyAccidentRecordDO.setWebearMoney(Float.valueOf(webearMoney));
			carDailyAccidentRecordDO.setRegion(region);
			carDailyAccidentRecordDO.setTreatmentResult(treatmentResult);
			result = appCarAccidentService.saveDataInside(carDailyAccidentRecordDO,username);
		} catch (NumberFormatException e) {
			logger.error("事故记录存入数据错误出错//{}....", e);
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
}
