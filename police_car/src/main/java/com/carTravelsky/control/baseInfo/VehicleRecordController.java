package com.carTravelsky.control.baseInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseContactsDO;
import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarSysCarTypeDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseContactsService;
import com.carTravelsky.service.carDaily.CarBaseDeptmentService;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.system.CarSysCarTypeService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

/**
 * 
 * @ClassName: VehicleRecordController
 * @Description: 车辆档案 controller
 * @author: yanlinlung
 * @date: 2017年10月13日 下午11:28:46
 */
@Controller
@RequestMapping("vehicleRecord")
public class VehicleRecordController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarBaseVehicleService carBaseVehicleService;
	@Autowired
	private CarBaseDeptmentService carBaseDeptmentService;
	@Autowired
	private CarBaseStaffService carBaseStaffService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	@Autowired
	private CarSysCarTypeService carSysCarTypeService;
	@Autowired
	private CarBaseContactsService carBaseContactsService;

	/**
	 * 
	 * @Title: getListVehicleRecordsByCompanyId
	 * @Description: 根据公司ID获取车辆档案信息
	 * @param pageBounds
	 * @param searchStr
	 * @return
	 * @return: Map<String,Object>
	 * @author: yanlinlung
	 * @date: 2017年10月13日 下午11:38:41
	 */
	@RequestMapping(value = "/getListVehicleRecords")
	@ResponseBody
	public Map<String, Object> getListVehicleRecordsByCompanyId(
			HttpServletRequest request, PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			if(YSTConstants.IS_CURRENT_DEP_VIEW==currentUser.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}
			List<CarBaseVehicleDO> carBaseVehicleDOs = new ArrayList<>();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", currentUser.getCompanyId());
			if (!StringUtils.isBlank(searchStr)) {
				paraMap.put("searchStr", searchStr);
				carBaseVehicleDOs = carBaseVehicleService.getsearchCarBaseVehicle(paraMap, pageBounds);
				return pageBounds.putResultObj(carBaseVehicleDOs);
			}
			carBaseVehicleDOs = carBaseVehicleService.getListVehicleRecordsByCompanyId(paraMap, pageBounds);
			System.out.println(carBaseVehicleDOs);
			return pageBounds.putResultObj(carBaseVehicleDOs);
		} catch (Exception e) {
			logger.error("根据公司ID,获取车辆档案信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: addVehicleRecords
	 * @Description: 跳转新增车辆信息页面
	 * @return
	 * @return: String
	 * @author: yanlinlong
	 * @date: 2017年11月1日 下午3:06:24
	 */
	@RequestMapping("addVehicleRecords")
	public String addVehicleRecords() {
		try {
			return "baseInfo/editVehicleRecords";
		} catch (Exception e) {
			logger.error("跳转新增车辆档案信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: editVehicleRecords
	 * @Description: 新增或修改车辆档案 controller
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月14日 下午3:32:50
	 */
	@RequestMapping(value = "editVehicleRecords")
	public String editVehicleRecords(
			@RequestParam(value = "show", required = false) String show,
			@RequestParam("ID") Integer id, Model model) {
		try {
			// 根据ID获取车辆档案信息
			CarBaseVehicleDO carBaseVehicleDO = carBaseVehicleService
					.getCarBaseVehicleByID(id);
			// 根据供应商id得到实体
			CarBaseContactsDO carBaseContactsDO = carBaseContactsService.getCarBaseContactsByID(Integer.valueOf(carBaseVehicleDO.getSupply()));
			carBaseVehicleDO.setSupplyName(carBaseContactsDO.getCompanyName());
			// 根据职员id得到职员实体
			CarBaseStaffDO carBaseStaffDO = carBaseStaffService.getCarBaseStaffByID(Integer.valueOf(carBaseVehicleDO.getDreiver()));
			carBaseVehicleDO.setDreiverName(carBaseStaffDO.getStaffName());
			// 根据车辆类型id得到车辆类型实体类
			CarSysCarTypeDO carSysCarTypeDO = carSysCarTypeService
					.getCarSysCarTypeByID(carBaseVehicleDO.getCarDetailID());
			// 封装品牌 型号 类型 颜色
			carBaseVehicleDO.setBrandName(carSysCarTypeDO.getBrandName());
			carBaseVehicleDO.setModelName(carSysCarTypeDO.getModelName());
			carBaseVehicleDO.setTypeName(carSysCarTypeDO.getTypeName());
			carBaseVehicleDO.setColorName(carSysCarTypeDO.getColorName());
			model.addAttribute("carBaseVehicleDO", carBaseVehicleDO);
			// 下拉框回显 --》 部门 状态 单独封装model回去
			// 根据部门id得到部门信息
			CarBaseDeptmentDO carBaseDeptmentDO = carBaseDeptmentService
					.getCarBaseDeptmentByID(carBaseVehicleDO.getDeptID());
			model.addAttribute("deptName", carBaseDeptmentDO.getDeptName());
			// 根据状态code得到状态值
			KeyCodeMasterDO keyCodeMaster = new KeyCodeMasterDO();
			keyCodeMaster.setKeyType("NOW_STATUS");
			keyCodeMaster.setCode(String.valueOf(carBaseVehicleDO
					.getNowStatus()));
			List<KeyCodeMasterDO> keyCodeMasterDOs = keyCodeMasterService
					.getKeyCodeMasterList(keyCodeMaster);
			for (KeyCodeMasterDO keyCodeMasterDO : keyCodeMasterDOs) {
				model.addAttribute("nowStatus", keyCodeMasterDO.getDisplay());
			}
			// show参数不为空 则为基本详细
			if (!StringUtils.isBlank(show)) {
				/* 图片地址字符串转单个数组 */
				String photoUrlString = carBaseVehicleDO.getPhotoUrl().trim();
				if (!photoUrlString.equals("")) {
					String[] photos = photoUrlString.split(",");
					List<String> photoUrlList = new ArrayList<String>();
					Collections.addAll(photoUrlList, photos); 
					carBaseVehicleDO.setPhotoUrlList(photoUrlList);
				}
				return "baseInfo/detialVehicleRecords";
			}
			return "baseInfo/editVehicleRecords";
		} catch (Exception e) {
			logger.error("新增或修改车辆档案信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: updateVehicleRecords
	 * @Description: 车辆档案新增或修改
	 * @param request
	 * @param carBaseVehicleDO
	 * @param timString
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping(value = "/updateVehicleRecords", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> updateVehicleRecords(
			HttpServletRequest request, CarBaseVehicleDO carBaseVehicleDO,
			@RequestParam("buyTimeString") String timString) {
		try {
			ValidationUtils.validate(carBaseVehicleDO);
			ValidateResult result = carBaseVehicleDO.getValidateResult();
			if (result.getResult()) {
				// 当前登录用户
				CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
						.get(request, "carSysUserDOLogin");
				// 前台time是以string格式到后台 string转data
				SimpleDateFormat buyTime = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
				carBaseVehicleDO.setBuyTime(buyTime.parse(timString));
				carBaseVehicleService.saveCarBaseVehicle(currentUser,
						carBaseVehicleDO);
				return ReturnDataInfo
						.createSuccessfulSingleExecuteMessage("操作车辆档案成功！");
			} else {
				return ReturnDataInfo.createFailedExecuteResult(result
						.getMessageList().toString());
			}
		} catch (Exception e) {
			logger.error("添加或者更新车辆档案出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作车辆档案失败！");
		}
	}

	/**
	 * 
	 * @Title: deleteVehicleRecords
	 * @Description: 车辆信息删除状态
	 * @param vehicleID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("/deleteVehicleRecords")
	@ResponseBody
	public ReturnDataInfo<Object> deleteVehicleRecords(
			HttpServletRequest request,
			@RequestParam(value = "vehicleID") String vehicleID) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			String[] splitIds = vehicleID.split(",");
			CarBaseVehicleDO carBaseVehicleDO = new CarBaseVehicleDO();
			for (String id : splitIds) {
				carBaseVehicleDO.setId(Integer.parseInt(id));
				carBaseVehicleDO.setDeleteCode(YSTConstants.DELETE_CODE_DEL);
				carBaseVehicleService.isUseCarBaseVehicle(currentUser,
						carBaseVehicleDO);
			}
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除车辆信息 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}

	/**
	 * 
	 * @Title: isUseDeptInfo
	 * @Description: 车辆信息列表 是否停用操作 Controller
	 * @param request
	 * @param ID
	 * @param status
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping(value = "/isUseVehicleRecords", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> isUseDeptInfo(HttpServletRequest request,
			@RequestParam("ID") Integer ID,
			@RequestParam("status") Integer status) {
		try {
			CarBaseVehicleDO carBaseVehicleDO = new CarBaseVehicleDO();
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			carBaseVehicleDO.setId(ID);
			carBaseVehicleDO.setStatus(status);
			carBaseVehicleService.isUseCarBaseVehicle(currentUser,
					carBaseVehicleDO);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("是否停用操作成功！");
		} catch (Exception e) {
			logger.error("车辆信息列表操作是否停用出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("是否停用操作失败！");
		}
	}

	/**
	 * 
	 * @Title: getListDeptByCompanyId
	 * @Description: 下拉框-- 该公司所有部门
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @date: 2017年10月16日 下午9:21:24
	 */
	@RequestMapping(value = "/getListDeptByCompanyId", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListDeptByCompanyId(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			// select2下拉框加载数据
			return carBaseDeptmentService.getSelect2ListDeptInfo(currentUser,
					str);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有部门信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getListDriverByCompanyId
	 * @Description: 下拉框--驾驶员
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @date: 2017年10月16日 下午10:40:30
	 */
	@RequestMapping(value = "/getListDriverByCompanyId", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListDriverByCompanyId(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			// 这里的驾驶员和职员连表查询 职员id
			return carBaseStaffService.getListDriverStaffByCompanyId(
					currentUser, str);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有部门信息出错{0}", e);
			return null;
		}
	}

}
