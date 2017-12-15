package com.carTravelsky.control.carDaily;

import java.util.ArrayList;  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyViolationRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseDriverService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyViolationRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.carTravelsky.utils.app.DateTools;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@RequestMapping("/carViolation")
@Controller
public class CarViolationControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyViolationRecordService carDailyViolationRecordService;
	@Autowired
	private CarBaseDriverService carBaseDriverService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;

	@Autowired
	private CarBaseVehicleService vehicleService;

	/**
	 * @Title: listCarViolation
	 * @Description: 进入车辆违章记录
	 * @return
	 * @return: String
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:20:43
	 */
	@RequestMapping(value = "/listCarViolationRecord", method = RequestMethod.POST)
	public String listCarViolation() {
		try {
			return "baseInfo/listCarViolationRecord";
		} catch (Exception e) {
			logger.error("进入进入维修取车记录出错{0}", e);
			return null;
		}

	}

	/**
	 * @Title: editCarViolation
	 * @Description: 编辑车辆违章记录
	 * @param request
	 * @param ID	车辆违章ID
	 * @return
	 * @return: String
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:20:03
	 */
	@RequestMapping(value = "/editCarViolationRecord")
	public String editCarViolation(HttpServletRequest request,@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			CarDailyViolationRecordDO carDailyViolationRecordDO = carDailyViolationRecordService.getCarDailyViolationRecordByID(ID);
			request.setAttribute("recode", carDailyViolationRecordDO);
			return "baseInfo/editCarViolationRecord";
		} catch (Exception e) {
			logger.error("进入编辑车辆违章记录出错{0}", e);
			return null;
		}

	}

	/**
	 * @Title: saveCarDailyViolationRecord
	 * @Description: 新增违章记录
	 * @param request
	 * @param carDailyViolationRecordDO
	 * @param response
	 * @param ViolationTime
	 * @param session
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:19:34
	 */
	@RequestMapping(value = "/addCarViolationRecord", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> saveCarDailyViolationRecord(HttpServletRequest request,CarDailyViolationRecordDO carDailyViolationRecordDO,HttpServletResponse response, String violationTime,HttpSession session) {
		try {
			request.setCharacterEncoding("utf-8");
			CarSysUserDO sysUserDO = (CarSysUserDO) session.getAttribute("carSysUserDOLogin");
			// 初始创建时新增车辆违章创建人
			if (carDailyViolationRecordDO.getCreatePeople() == null) {		
				carDailyViolationRecordDO.setCreatePeople(sysUserDO.getUsername());
			}
			// 进行更新时修改本次的修改人员
			carDailyViolationRecordDO.setUpdatePeople(sysUserDO.getUsername());
			carDailyViolationRecordDO.setViolationDate(DateTools.formatDate(violationTime, DateTools.DATE_PATTERN_DEFAULT));
			// 对实体进行验证
			ValidationUtils.validate(carDailyViolationRecordDO);
			ValidateResult result = carDailyViolationRecordDO.getValidateResult();
			boolean result2 = result.getResult();
			if (result2 == false) {
				logger.error("验证实体失败 {0}");
				return ReturnDataInfo.createFailedExecuteResult("验证实体失败");
			}
			carDailyViolationRecordService.saveCarDailyViolationRecord(carDailyViolationRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作成功");
		} catch (Exception e) {
			logger.error("新增车辆违章记录失败 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作失败");
		}
	}

	/**
	 * @Title: CarViolationDetail
	 * @Description: 查看车辆违章详情
	 * @param request
	 * @param ID
	 * @return
	 * @return: String
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:19:04
	 */
	@RequestMapping(value = "/CarViolationDetail")
	public String CarViolationDetail(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editCarViolationRecord";
			}
			CarDailyViolationRecordDO carDailyViolationRecordDO = carDailyViolationRecordService.getCarDailyViolationRecordByID(ID);
			request.setAttribute("recode", carDailyViolationRecordDO);
			return "baseInfo/carViolationDetail";
		} catch (Exception e) {
			logger.error("进入查看车辆违章记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: deleteCarViolationByID
	 * @Description: 根据指定id删除车辆违章记录
	 * @param request
	 * @param response
	 * @param ID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:17:49
	 */
	@RequestMapping("deleteCarViolationRecord")
	@ResponseBody
	public ReturnDataInfo<Object> deleteCarViolationByID(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "id") String ID) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if(!StringUtils.isBlank(ID)){
				String[] parms = ID.split(",");
				int i = 0;
				for (String c : parms) {
					i = carDailyViolationRecordService.deleteIncomplete(Integer.valueOf(c));
				}
				if (i == 1) {
					return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
				} 
			}else {
				return ReturnDataInfo.createFailedExecuteResult("删除失败");
			}
		} catch (Exception e) {
			logger.error("进入删除车辆违章记录出错{0}", e);
		}
		return null;
	}

	/**
	 * @Title: getListDispatchRecord
	 * @Description: 得到所有的车辆违章记录
	 * @param request
	 * @param carDailyViolationRecord
	 * @param pageBounds
	 * @param searchStr		当其等于空时就全局搜索
	 * @return
	 * @return: Map<String,Object>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:16:33
	 */
	@ResponseBody
	@RequestMapping(value = "getListCarViolationRecord", method = RequestMethod.POST)
	public Map<String, Object> getListDispatchRecord(
			HttpServletRequest request,
			PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if(YSTConstants.IS_CURRENT_DEP_VIEW==currentUser.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}
			List<CarDailyViolationRecordDO> carDailyViolationRecordList = new ArrayList<>();
			if (!StringUtils.isBlank(searchStr)) {
				if (searchStr.contains("/")) {
					// 当出现对日期的查询时，支持yyyyy-MM-dd格式查询 ，不支持yyyyy/MM/dd格式
					searchStr = searchStr.trim().replaceAll("/", "-");
				}
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("id", currentUser.getCompanyId());
				paraMap.put("searchStr", searchStr.trim());
				carDailyViolationRecordList = carDailyViolationRecordService.getSearchCarDailyViolationRecord(paraMap,pageBounds);
				return pageBounds.putResultObj(carDailyViolationRecordList);
			}
			carDailyViolationRecordList = carDailyViolationRecordService.getListViolationRecordsByCompanyId(currentUser.getCompanyId(),pageBounds);
			return pageBounds.putResultObj(carDailyViolationRecordList);
		} catch (Exception e) {
			logger.error("list车辆违章记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: getListLicenseno
	 * @Description: 得到所有的车辆ID对应的车牌号
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:15:44
	 */
	@RequestMapping(value = "/getListLicenseno", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListLicenseno(HttpServletRequest request,HttpSession session,@RequestParam(value="q",required=false)String searchStr) {
		try {
			CarSysUserDO sysUserDO = (CarSysUserDO) session.getAttribute("carSysUserDOLogin");
			Integer deptID = sysUserDO.getDeptID();
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			List<CarBaseVehicleDO> carBaseVehicleDOs = vehicleService.getLicensenosList(searchStr,deptID);
			for (CarBaseVehicleDO carBaseVehicleDO : carBaseVehicleDOs) {
				Select2VO select2vo = new Select2VO();

				select2vo.setId(String.valueOf(carBaseVehicleDO.getId()));
				select2vo.setText(carBaseVehicleDO.getLicenseno());
				if (select2vos.contains(select2vo) == false) {
					select2vos.add(select2vo);
				} else {
					select2vos.add(null);
				}
			}
			return select2vos;
		} catch (Exception e) {
			logger.error("根据车辆id获取对应车牌信息信息出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: getListDriver
	 * @Description: 得到所有的司机名称
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午11:15:10
	 */
	@RequestMapping(value = "/getListDriver", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListDriver(HttpServletRequest request,@RequestParam(value="q",required=false)String searchStr) {
		try {
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			List<CarBaseDriverDO> carBaseDriverDOs = carBaseDriverService.getListDriverName(searchStr);
			for (CarBaseDriverDO carBaseDriverDO : carBaseDriverDOs) {
				Select2VO select2vo = new Select2VO();
				if (carBaseDriverDO.getStaffName() != null) {
					select2vo.setId(carBaseDriverDO.getStaffName());
					select2vo.setText(carBaseDriverDO.getStaffName());
					select2vos.add(select2vo);
				} else {
					select2vos.add(null);
				}
			}
			return select2vos;
		} catch (Exception e) {
			logger.error("获取所有司机信息出错{0}", e);
			return null;
		}
	}

}
