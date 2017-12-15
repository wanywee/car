package com.carTravelsky.control.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.bean.app.response.CarAndDeptListRS;
import com.carTravelsky.bean.app.response.DeptAndStaffRS;
import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.app.response.LicenseAndStaffRS;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.app.AUserService;
import com.carTravelsky.service.app.AppCarApplyService;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarApplyController
 * @Description: 申请用车
 * @author: wangyi
 * @date: 2017年12月6日 下午2:08:25
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/applycar")
public class AppCarApplyController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarApplyService appCarApplyService;
	@Autowired
	private AUserService aUserService;
	
	/**
	 * @Title: getAllDeptments
	 * @Description: 所有部门
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getAllDeptments",method={RequestMethod.POST})
	public JsonResponse getAllDeptments(HttpServletRequest request){
		List<GeneralRS> list = appCarApplyService.getAllDeptments();
		return JsonResponse.RespSuccess(list);
	}
	
	/**
	 * @Title: getCarListByDeptment
	 * @Description: 部门车辆列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getCarListsByDeptment",method={RequestMethod.POST})
	public JsonResponse getCarListByDeptment(HttpServletRequest request){
		List<CarAndDeptListRS> list = appCarApplyService.getCarsListByDeptment();
		return JsonResponse.RespSuccess(list);
	}
	
	
	/**
	 * @Title: getCarListByDeptment
	 * @Description: 部门人员列表
	 * @param request
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getStaffListsByDeptment",method={RequestMethod.POST})
	public JsonResponse getStaffListByDeptment(HttpServletRequest request){
		List<DeptAndStaffRS> list = appCarApplyService.getStaffsListByDeptment();
		return JsonResponse.RespSuccess(list);
	}
	

	/**
	 * @Title: getDriverInfoByLicense
	 * @Description: 根据车牌号查询 每个部门的司机
	 * @param request
	 * @param licenseNumber
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/getDriverInfoByLicense",method={RequestMethod.POST})
	public JsonResponse getDriverInfoByLicense(HttpServletRequest request,
			@RequestParam(value = "licenseNumber",required = true)String licenseNumber){
		List<LicenseAndStaffRS> list = appCarApplyService.getDriverInfoByLicense(licenseNumber);
		return JsonResponse.RespSuccess(list);
	}
	
	/**
	 * @Title: applyCarSaveData
	 * @Description: TODO
	 * @param startTime  预计用车时间
	 * @param endTime 结束时间
	 * @param location目的地
	 * @param entourage 随行人
	 * @param carId 车辆ID
	 * @param username 登陆人员名称
	 * @param outcause 出车原因
	 * @param driverName 司机名称
	 * @param driverId  司机ID
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/applyCarSaveData",method={RequestMethod.POST})
	public JsonResponse applyCarSaveData(
		    @RequestParam(value = "startTime",required = true)String startTime,
		    @RequestParam(value = "endTime",required = true)String endTime,
		    @RequestParam(value = "location",required = true)String location,
		    @RequestParam(value = "entourage",required = true)String entourage,
		    @RequestParam(value = "carId",required = true)String carId,
		    //@RequestParam(value = "deptId",required = true)String deptId,
		    //@RequestParam(value = "caruser",required = true)String caruser,
		    @RequestParam(value = "username",required = true)String username,
		    @RequestParam(value = "outcause",required = true)String outcause,
		    @RequestParam(value = "driverName",required = true)String driverName,
		    @RequestParam(value = "driverId",required = true)String driverId
			){
		//
		CarBaseStaffDO carBaseStaffDO = aUserService.getPhoneByUsername(username);
		//公司ID
		Integer companyId = aUserService.getCompanyId(username);
		CarDailyOutRecordDO carDailyOutRecordDO = new CarDailyOutRecordDO();
		carDailyOutRecordDO.setCarID(Integer.valueOf(carId));
		carDailyOutRecordDO.setCarType(CodeCondition.FAIL);
		carDailyOutRecordDO.setCaruser(username);
		carDailyOutRecordDO.setDeleteCode(3);
		carDailyOutRecordDO.setDeptID(carBaseStaffDO.getDeptID());
		carDailyOutRecordDO.setDesitination(location);
		carDailyOutRecordDO.setDriver(driverName);
		carDailyOutRecordDO.setEntourage(entourage);
		carDailyOutRecordDO.setEstimateReturnTime(DateTools.stringToDate(endTime, DateTools.DATE_PATTERN_DEFAULT));
		carDailyOutRecordDO.setOutcarTime(DateTools.stringToDate(startTime, DateTools.DATE_PATTERN_DEFAULT));
		carDailyOutRecordDO.setPhonenumber(carBaseStaffDO.getStaffPhone());
		carDailyOutRecordDO.setBackTime(DateTools.stringToDate(endTime, DateTools.DATE_PATTERN_DEFAULT));
		carDailyOutRecordDO.setOutCause(outcause);
		carDailyOutRecordDO.setStffID(Integer.valueOf(driverId));
		carDailyOutRecordDO.setRegion(CodeCondition.POLICE_PLACE);
		carDailyOutRecordDO.setCompanyID(companyId);
		int result = 0;
		try {
			result = appCarApplyService.applyCarSaveData(carDailyOutRecordDO,username);
		} catch (Exception e) {
			logger.error("申请用车记录存入数据错误出错//{}....", e);
		}
		Map<String,Object> map = new HashMap<String, Object>();
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
	 * @Title: continueToRentCar
	 * @Description: TODO
	 * @param rentTime 续车时长
	 * @param carId 车辆ID
	 * @param username 用车人
	 * @return
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/continueToRentCar",method={RequestMethod.POST})
	public JsonResponse continueToRentCar(
			 @RequestParam(value = "rentTime",required = true)String rentTime,
			 @RequestParam(value = "carId",required = true)String carId,
			 @RequestParam(value = "username",required = true)String username
			){
		CarBaseStaffDO carBaseStaffDO = aUserService.getPhoneByUsername(username);
		int result  = appCarApplyService
						.updateRentCarTime(
								Integer.valueOf(rentTime),
								carBaseStaffDO.getDeptID(),
								Integer.valueOf(carId),
								carBaseStaffDO.getStaffNo()
								);
		Map<String,Object> map = new HashMap<String, Object>();
		switch(result){
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
