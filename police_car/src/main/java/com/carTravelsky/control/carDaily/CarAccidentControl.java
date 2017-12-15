package com.carTravelsky.control.carDaily;

import java.util.ArrayList;
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
import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseDriverService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyAccidentRecordService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.carTravelsky.utils.app.DateTools;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@RequestMapping("/carAccident")
@Controller
public class CarAccidentControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyAccidentRecordService accidentRecordService;
	@Autowired
	private CarBaseDriverService carBaseDriverService;

	@Autowired
	private CarBaseVehicleService vehicleService;

	/**
	 * @Title: 进入车辆事故记录
	 * @return
	 */
	@RequestMapping(value = "/listCarAccidentRecord", method = RequestMethod.GET)
	public String listCarAccident() {
		try {
			return "baseInfo/listCarAccidentRecord";
		} catch (Exception e) {
			logger.error("进入进入维修取车记录出错{0}", e);
			return null;
		}

	}

	/**
	 * @Title: 编辑车辆事故记录
	 * @Description: TODO
	 * @param request
	 * @param ID
	 *            事故车辆ID
	 * @return
	 */
	@RequestMapping(value = "/editCarAccident")
	public String editCarAccident(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			
			CarDailyAccidentRecordDO carDailyAccidentRecordDO = accidentRecordService
					.getCarDailyAccidentRecordByID(ID);
			request.setAttribute("recode", carDailyAccidentRecordDO);
			return "baseInfo/editCarAccidentRecord";
		} catch (Exception e) {
			logger.error("进入编辑车辆事故记录出错{0}", e);
			return null;
		}

	}
	
	/**
	 * @Title: 新增车辆事故记录
	 * @Description: TODO
	 * @param request
	 * @param ID
	 *            事故车辆ID
	 * @return
	 */
	@RequestMapping(value = "/insertCarAccident")
	public String insertCarAccident(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editCarAccidentRecord";
			}
			return "baseInfo/editCarAccidentRecord";
		} catch (Exception e) {
			logger.error("进入新增车辆事故记录出错{0}", e);
			return null;
		}

	}

	/**
	 * @Title: 储存事故记录
	 * @Description: TODO
	 * @param request
	 * @param carDailyAccidentRecordDO
	 * @return
	 * @return: String
	 * @author: wangyu
	 * @date: 2017年10月12日 下午1:27:20
	 */
	@RequestMapping(value = "/saveCarDailyAccidentRecord", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> saveCarDailyAccidentRecord(
			HttpServletRequest request,
			CarDailyAccidentRecordDO carDailyAccidentRecordDO,
			HttpServletResponse response, String accidentTime,
			HttpSession session) {
		try {
			request.setCharacterEncoding("utf-8");
			CarSysUserDO sysUserDO = (CarSysUserDO) session
					.getAttribute("carSysUserDOLogin");
			if (carDailyAccidentRecordDO.getCreatePeople() == null) {// 初始创建时新增车辆事故创建人
				carDailyAccidentRecordDO.setCreatePeople(sysUserDO
						.getUsername());
			}
			carDailyAccidentRecordDO.setUpdatePeople(sysUserDO.getUsername());// 进行更新时修改本次的修改人员
			carDailyAccidentRecordDO.setAccidentDate(DateTools.formatDate(
					accidentTime, DateTools.DATE_PATTERN_DEFAULT));
			ValidationUtils.validate(carDailyAccidentRecordDO);// 对实体进行验证
			ValidateResult result = carDailyAccidentRecordDO
					.getValidateResult();
			boolean result2 = result.getResult();
			if (result2 == false) {
				logger.error("验证实体失败 {0}");
				return ReturnDataInfo.createFailedExecuteResult("验证实体失败");
			}
			accidentRecordService
					.saveCarDailyAccidentRecord(carDailyAccidentRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作成功");
		} catch (Exception e) {
			logger.error("新增车辆事故失败 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作失败");
		}

	}

	/**
	 * @Title: CarAccidentDetail
	 * @Description: 查看车辆事故详情
	 * @param request
	 * @param ID
	 * @return
	 * @return: String
	 * @author: wangyu
	 * @date: 2017年10月16日 下午2:42:59
	 */
	@RequestMapping(value = "/CarAccidentDetail")
	public String CarAccidentDetail(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editCarAccidentRecord";
			}
			CarDailyAccidentRecordDO carDailyAccidentRecordDO = accidentRecordService
					.getCarDailyAccidentRecordByID(ID);
			request.setAttribute("recode", carDailyAccidentRecordDO);
			return "baseInfo/carAcctidentDetail";
		} catch (Exception e) {
			logger.error("进入查看车辆事故记录出错{0}", e);
			return null;
		}

	}

	/**
	 * @Title: 根据指定id删除车辆事故记录
	 * @Description: TODO
	 * @param request
	 * @param ID
	 * @return
	 * @return: int
	 * @author: wangyu
	 * @date: 2017年10月12日 下午4:01:00
	 */
	@RequestMapping("deleteCarAccident")
	@ResponseBody
	public ReturnDataInfo<Object> deleteCarAccidentByID(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") String ID) {

		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if(!StringUtils.isBlank(ID)){
				String[] parms = ID.split(",");
				int i = 0;
				for (String c : parms) {
					i = accidentRecordService.deleteIncomplete(Integer.valueOf(c));
				}
				if (i == 1) {
					return ReturnDataInfo
							.createSuccessfulSingleExecuteMessage("删除成功");
				} 
			}else {
				return ReturnDataInfo.createFailedExecuteResult("删除失败");
			}

		} catch (Exception e) {
			logger.error("进入删除车辆事故记录出错{0}", e);
		}
		return null;
	}

	/**
	 * @Title:得到所有的车辆事故记录
	 * @Description: TODO
	 * @param request
	 * @param carDailyAccidentRecord
	 * @param pageBounds
	 * @param searchStr
	 *            当其不为空时为全局搜素
	 * @author: wangyu
	 * @date: 2017年10月12日 上午10:57:30
	 */
	@ResponseBody
	@RequestMapping(value = "getListCarAccidentRecord", method = RequestMethod.POST)
	public Map<String, Object> getListDispatchRecord(
			HttpServletRequest request,
			CarDailyAccidentRecordDO carDailyAccidentRecord,
			PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
								.get(request, "carSysUserDOLogin");
			if(YSTConstants.IS_CURRENT_DEP_VIEW==currentUser.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}
			
			if (!StringUtils.isBlank(searchStr)) {
				if (searchStr.contains("/")) {
					// 当出现对日期的查询时，支持yyyyy-MM-dd格式查询 ，不支持yyyyy/MM/dd格式
					searchStr = searchStr.trim().replaceAll("/", "-");
				}
				List<CarDailyAccidentRecordDO> carDailyAccidentRecordList = accidentRecordService
						.getsearchCarDailyAccidentRecord(searchStr.trim(),
								pageBounds);
				return pageBounds.putResultObj(carDailyAccidentRecordList);
			}
			List<CarDailyAccidentRecordDO> carDailyAccidentRecordList = accidentRecordService
					.getCarDailyAccidentRecordList(carDailyAccidentRecord,
							pageBounds);
			return pageBounds.putResultObj(carDailyAccidentRecordList);
		} catch (Exception e) {
			logger.error("list车辆事故记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: getListLicenseno
	 * @Description: 得到所有的车辆ID对应的车牌号
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: wangyu
	 * @date: 2017年10月17日 下午3:49:49
	 */
	@RequestMapping(value = "/getListLicenseno", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListLicenseno(HttpServletRequest request,HttpSession session,@RequestParam(value="q",required=false)String searchStr) {
		try {

			CarSysUserDO sysUserDO = (CarSysUserDO) session
					.getAttribute("carSysUserDOLogin");
			Integer deptId =sysUserDO.getDeptID();
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			List<CarBaseVehicleDO> carBaseVehicleDOs = vehicleService
					.getLicensenosList(searchStr,deptId);
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
	 * 
	 * @Title: getListLicensenoOut
	 * @Description: 回车记录 车牌号下拉框 筛选条件  车状态为出车，未停用，未删除
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: fuxinrong
	 * @date: 2017年10月31日 上午11:15:32
	 */
	@RequestMapping(value = "/getListLicensenoOut", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListLicensenoOut(HttpServletRequest request,String isOutAndBack
			,@RequestParam(value = "q", required = false) String str) {
		try {//获取登陆用户  公司id 根据公司id查询车牌号
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			
			CarBaseVehicleDO BaseVehicleDO=new CarBaseVehicleDO();
			BaseVehicleDO.setCompanyID(userDo.getCompanyId());
			
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			List<CarBaseVehicleDO> carBaseVehicleDOs = vehicleService
					.getLicensenosListOut(BaseVehicleDO,isOutAndBack,str);
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
			logger.error("根据公司id获取车牌号出错{0}", e);
			return null;
		}
			
			
	}

	/**
	 * @Title: getListDriver
	 * @Description: 得到所有的司机名称
	 * @param request
	 * @return
	 * @return: List<Select2VO>
	 * @author: wangyu
	 * @date: 2017年10月17日 下午3:49:49
	 */
	@RequestMapping(value = "/getListDriver", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListDriver(HttpServletRequest request,@RequestParam(value="q",required=false)String searchStr) {
		try {

			
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			List<CarBaseDriverDO> carBaseDriverDOs = carBaseDriverService
					.getListDriverName(searchStr);
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
