package com.carTravelsky.control.trafficManage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordHisDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.app.AppCarApplyService;
import com.carTravelsky.service.carDaily.CarBaseDeptmentService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyOutRecordHisService;
import com.carTravelsky.service.carDaily.CarDailyOutRecordService;
import com.carTravelsky.service.trafficManage.DispatchCenterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.web.context.SessionContext;

/**
 * @ClassName: dispatchCenter 调度中心
 * @Description: TODO
 * @author: 黄进
 * @date: 2017年12月4日 下午1:57:53
 */

@Controller
@RequestMapping("/dispatchCenter")
public class DispatchCenterControl {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DispatchCenterService centerService;
	@Autowired
	private CarBaseDeptmentService carBaseDeptmentService;
	@Autowired
	private CarBaseVehicleService carBaseVehicleService;
	@Autowired
	private CarDailyOutRecordService carDailyOutRecordService;
	@Autowired
	private AppCarApplyService appCarApplyService;
	@Autowired
	private CarDailyOutRecordHisService carDailyOutRecordHisService;
	
	@RequestMapping("/listDispatchCenter")
	public String listDispatchCenter() {
		try {
			return "/trafficManage/listDispatchCenter";
		} catch (Exception e) {
			logger.error("进入调度管中心理页面失败!", e);
			return null;
		}
	}
	
	/**
	 * @Title: getListDispatchCenter 获取未审批的出车记录
	 * @Description: TODO
	 * @param searchStr 关键字
	 * @param pageBounds 分页
	 * @param carDailyOutRecordDO
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年12月4日 下午3:47:56
	 */
	@RequestMapping("/getListDispatchCenter")
	@ResponseBody
	public Map<String, Object> getListDispatchCenter(PageBounds pageBounds,CarDailyOutRecordDO carDailyOutRecordDO,String searchStr) {
		try {
			List<CarDailyOutRecordDO>  dispatchCenterList;
			
				dispatchCenterList = centerService.serchDispatchCenter(searchStr,carDailyOutRecordDO,pageBounds);
			
			return pageBounds.putResultObj(dispatchCenterList);
		} catch (Exception e) {
			logger.error("获取未审批出车记录失败!",e);
			return null;
		}
	}
	
	
	/**
	 * @Title: toDispatchRatify 跳转调度审批页面
	 * @Description: TODO
	 * @param id 需要审批的出车记录ID
	 * @return
	 * @return: String
	 * @author: admin  
	 * @date: 2017年12月5日 下午1:35:11
	 */
	@RequestMapping("/toDispatchRatify")
	public String toDispatchRatify(String id,HttpServletRequest request,CarBaseVehicleDO baseVehicleDO) {
		try {
			CarDailyOutRecordDO carDailyOutRecordDO=centerService.toDispatchRatify(id, request, baseVehicleDO);
			request.setAttribute("carDailyOutRecordDO", carDailyOutRecordDO);
			return "/trafficManage/dispatchRatify";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("进入调度审批页面失败!{0}", e);
			return null;
		}
	}
	
	/**
	 * @Title: getDepartmentCars 获取当前部门所拥有的车辆信息
	 * @Description: TODO
	 * @param baseVehicleDO
	 * @param pageBounds
	 * @param request
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年12月5日 下午4:15:14
	 */
	@ResponseBody
	@RequestMapping("/getDepartmentCars")
	public Map<String, Object> getDepartmentCars(CarBaseVehicleDO baseVehicleDO,PageBounds pageBounds,HttpServletRequest request) {
		try {
			return pageBounds.putResultObj(null);
		} catch (Exception e) {
			logger.error("获取当前申请人所在派出所车辆列表", e);
			return null;
		}
	}
	
	/**
	 * 批准用车申请
	 * @return
	 */
	@RequestMapping(value = "/approveApplyCar", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<String> approveApplyCar(CarDailyOutRecordDO carDailyOutRecordDO,HttpServletRequest request) {
		try {
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			
			System.out.println(carDailyOutRecordDO);
			// 出车记录更新状态
			CarDailyOutRecordDO carOutRecordDO = carDailyOutRecordService.getApplyCarDailyOutRecordByID(carDailyOutRecordDO.getId());
			
			//
			CarBaseVehicleDO carveh=carBaseVehicleService.getCarBaseVehicleByID(carOutRecordDO.getCarID());
			if(YSTConstants.CAR_STATUS_BE_USE.equals(carveh.getNowStatus())){
				return ReturnDataInfo.createFailedExecuteResult("车辆出厂中，请选择其他车辆！");
			}
			
			carOutRecordDO.setCarID(carDailyOutRecordDO.getCarID());
			carOutRecordDO.setDeptID(carDailyOutRecordDO.getDeptID());
			carOutRecordDO.setLicenseno(carDailyOutRecordDO.getLicenseno());
			carOutRecordDO.setDriver(carDailyOutRecordDO.getDriver());
			carOutRecordDO.setCarType(YSTConstants.PROCESS_STATUS_HAVE_SEND_CAR);
			carOutRecordDO.setDeleteCode(1);
			// 更新车辆信息 车辆信息状态更新 根据车辆id查询车辆实体
			CarBaseVehicleDO carBaseVehicleDO = carBaseVehicleService.getCarBaseVehicleByID(carOutRecordDO.getCarID());
			carBaseVehicleDO.setNowStatus(YSTConstants.CAR_STATUS_BE_USE);
			carBaseVehicleService.saveCarBaseVehicle(carBaseVehicleDO);
			
			carDailyOutRecordService.updateOutCar(carOutRecordDO);
			
			//历史记录
			CarDailyOutRecordHisDO  his=new CarDailyOutRecordHisDO();
			his.setCdorID(carDailyOutRecordDO.getId());
			his.setProcessStatus(carDailyOutRecordDO.getCarType());
			his.setOperator(currentUser.getId());
			his.setOperateTime(new Date());
			carDailyOutRecordHisService.saveCarDailyOutRecordHis(his);
			
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("批准成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("批准用车失败{0}", e);
			return null;
		}
	}
	
	
	/**
	 * @Title: dispatchTurnDown 拒绝出车申请
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年12月5日 上午11:13:19
	 */
	@ResponseBody
	@RequestMapping("/dispatchTurnDown")
	public ReturnDataInfo<String> dispatchTurnDown(@RequestParam(value = "ids[]")String[] ids,HttpServletRequest request) {
		try {
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			List<String> asList = Arrays.asList(ids);
			centerService.dispatchTurnDown(asList,currentUser);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("拒绝出车申请成功！");
		} catch (Exception e) {
			logger.error("拒绝出车申请失败 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("拒绝出车申请失败！");
		}
		
	}
	
	/**
	 * 获取部门及部门下车辆的树
	 * @return
	 */
	@RequestMapping("/getZTreeDeptment")
	@ResponseBody
	public List<ZTreeVO> getAllDeptment(String carId) {
		try {
			return carBaseDeptmentService.getZTreeDeptment(carId);
		} catch (Exception e) {
			logger.error("获取部门树失败{0}", e);
			return null;
		}
		
	}
	
	/**
	 * 获取部门及部门下车辆的树
	 * @return
	 */
	@RequestMapping("/getZTreeCarList")
	@ResponseBody
	public List<ZTreeVO> getCarListByDeptId(String deptId) {
		try {
			return carBaseVehicleService.getZTreeCarListByDeptId(deptId);
		} catch (Exception e) {
			logger.error("获取部门树失败{0}", e);
			return null;
		}
		
	}
	
}
