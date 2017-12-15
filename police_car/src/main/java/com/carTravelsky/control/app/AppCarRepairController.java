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

import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AppCarAccidentService;
import com.carTravelsky.service.app.AppCarRepairService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarRepairController
 * @Description: 维修记录
 * @author: wangyi
 * @date: 2017年12月4日 下午5:51:40
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/repair")
public class AppCarRepairController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarRepairService appCarRepairService;
	@Autowired
	private AppCarAccidentService appCarAccidentService;
	
	/**
	 * @Title: maintDataInsertOrUpdate
	 * @Description: 维修记录存入数据
	 * @param username 登陆用户名称
	 * @param handlerName 经手人名称
	 * @param licenseNumber 车牌号
	 * @param repairTime 维修时间
	 * @param estimateTackcar  预计取车时间
	 * @param repairReason 维修原因
	 * @param region 区域
	 * @param repairPd 修理厂
	 * @param remark 备注
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/repairDataInsertOrUpdate",method={RequestMethod.POST})
	public JsonResponse maintDataInsertOrUpdate(
			@RequestParam(value = "username",required = true)String username,
			@RequestParam(value = "handlerName",required = true)String handlerName,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber,
			@RequestParam(value = "repairTime",required = true)String repairTime,
			@RequestParam(value = "estimateTackcar",required = true)String estimateTackcar,
			@RequestParam(value = "repairReason",required = true)String repairReason,
			@RequestParam(value = "region",required = true)String region,
			@RequestParam(value = "repairPd",required = true)String repairPd,
			@RequestParam(value = "remark",required = true)String remark
			){
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(licenseNumber)){
			map.put(CodeCondition.CONDITION, Param.LICENSENO_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.LICENSENO_IS_NULL, 
							Param.LICENSENO_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(repairTime)){
			map.put(CodeCondition.CONDITION, Param.REPAIR_TIME_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.REPAIR_TIME_IS_NULL, 
							Param.REPAIR_TIME_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(estimateTackcar)){
			map.put(CodeCondition.CONDITION, Param.ESTIMATE_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.ESTIMATE_IS_NULL, 
							Param.ESTIMATE_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(repairReason)){
			map.put(CodeCondition.CONDITION, Param.REPARI_REASON_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.REPARI_REASON_IS_NULL, 
							Param.REPARI_REASON_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(repairPd)){
			map.put(CodeCondition.CONDITION, Param.REPAIR_PD_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.CarExceptionCode.REPAIR_PD_IS_NULL, 
							Param.REPAIR_PD_IS_NULL.getMessage(),map);
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
			CarDailyRepairRecordDO carDailyRepairRecordDO = new CarDailyRepairRecordDO();
			carDailyRepairRecordDO.setCarID(carId);
			carDailyRepairRecordDO.setDeleteCode(CodeCondition.SUCCEED);
			carDailyRepairRecordDO.setEstimateTackcar(DateTools.stringToDate(estimateTackcar, DateTools.DATE_PATTERN_DEFAULT));
			carDailyRepairRecordDO.setHandler(handlerName);
			carDailyRepairRecordDO.setRegion(region);
			carDailyRepairRecordDO.setRemark(remark);
			carDailyRepairRecordDO.setRepairPd(repairPd);
			carDailyRepairRecordDO.setRepairReason(repairReason);
			carDailyRepairRecordDO.setRepairTime(DateTools.stringToDate(repairTime, DateTools.DATE_PATTERN_DEFAULT));
			
			result = appCarRepairService.repairRecodrSave(carDailyRepairRecordDO,username);
		} catch (Exception e) {
			logger.error("维修记录存入数据错误出错//{}....", e);
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
