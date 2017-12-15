package com.carTravelsky.control.trafficManage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.system.KeyCodeMasterMapper;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyOutRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.web.context.SessionContext;

@Controller
@RequestMapping("/outOfCar")
public class TrafficManageController {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarBaseVehicleService carBaseVehicleService;
	@Autowired
	private CarDailyOutRecordService carDailyOutRecordService;
	@Autowired
	private  KeyCodeMasterService  keyCodeMasterService;

	@RequestMapping("/listOutOfCar")
	public String listOutOfCar() {
		try {
			return "trafficManage/listOutOfCar";
		} catch (Exception e) {
			logger.error("跳转出车列表出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @param request
	 * @param pageBounds
	 * @param searchStr
	 * @return
	 */
	@RequestMapping(value = "/getListVehicleRecords")
	@ResponseBody
	public Map<String, Object> getListVehicleRecordsByCompanyId(HttpServletRequest request, PageBounds pageBounds,
			String searchStr, @RequestParam(value = "All", required = false) String all) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			//车辆出出车申请只正对当前部门车辆和调度中心车辆
				/*pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());*/
			
			List<CarBaseVehicleDO> carBaseVehicleDOs = new ArrayList<>();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if (!StringUtils.isBlank(all)) {
                //查询其他单位车辆
				paraMap.put("id", currentUser.getCompanyId());
				paraMap.put("nowStatus",1);
				//paraMap.put("deptId", currentUser.getDeptID());
			} else {
				//查询本单位和调度中心车辆
				paraMap.put("id", currentUser.getCompanyId());
				List<Integer> nowStatusList=new ArrayList<Integer>();
				nowStatusList.add(YSTConstants.CAR_STATUS_APPLYING);
				nowStatusList.add(YSTConstants.CAR_STATUS_NORMAL);
				paraMap.put("nowStatusList", nowStatusList);
				paraMap.put("deptId", currentUser.getDeptID());
				paraMap.put("dispatchId", YSTConstants.DISPATCH_DEPT_ID);
			}
			if (!StringUtils.isBlank(searchStr)) {
				paraMap.put("searchStr", searchStr);
			}
			carBaseVehicleDOs = carBaseVehicleService.getsearchCarBaseVehicle(paraMap, pageBounds);
			return pageBounds.putResultObj(carBaseVehicleDOs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取当前未出车记录", e);
			return null;
		}
	}

	/**
	 * 跳转用车申请界面，将 carId deptID 带上
	 * 
	 * @param carId
	 * @param deptID
	 * @return
	 */
	@RequestMapping("/toAddOutOfCar")
	public ModelAndView toAddOutOfCar(@RequestParam(value = "carId") String carId,
			@RequestParam(value = "deptID") String deptID) {
		try {
			ModelAndView model = new ModelAndView();
			model.addObject("carId", carId);
			model.addObject("deptID", deptID);
			KeyCodeMasterDO keyCodeMasterDO=new KeyCodeMasterDO();
			keyCodeMasterDO.setKeyType(YSTConstants.PREDICTTIME);
			List<KeyCodeMasterDO> list=keyCodeMasterService.getKeyCodeMasterList(keyCodeMasterDO);
			model.addObject("codeMaster", list.get(0));
			model.setViewName("trafficManage/applyCar");
			return model;
		} catch (Exception e) {
			logger.error("跳转出车出错{0}", e);
			return null;
		}
	}

	/**
	 * 申请用车表单提交
	 * @param request
	 * @param carDailyOutRecordDO
	 * @return
	 */
	@RequestMapping("/applyCar")
	@ResponseBody
	public ReturnDataInfo<Object> applyCar(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			carDailyOutRecordService.applyCar(currentUser,carDailyOutRecordDO);
			
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作出车申请成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("出车申请表提交出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作出车申请失败！");
		}
	}
	/**
	 * 
	 * @Title: getSelect2ListStaff
	 * @Description: TODO省内省外菜单
	 * @param request
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: admin  
	 * @date: 2017年12月7日 下午5:24:39
	 */
	@RequestMapping("/getaddress")
	@ResponseBody
	public List<Select2VO> getaddress() {
		try {
			List<Select2VO> lsc = new LinkedList<Select2VO>();
			Select2VO sc1 = new Select2VO();
			sc1.setId("1");
			sc1.setText("省内");
			Select2VO sc2 = new Select2VO();
			sc2.setId("2");
			sc2.setText("省外");
			lsc.add(sc1);
			lsc.add(sc2);
			return lsc;
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}

}
