/**
 * 
 */
package com.carTravelsky.control.comon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarAge;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.control.BaseControl;
import com.carTravelsky.service.carDaily.CarBaseContactsService;
import com.carTravelsky.service.carDaily.CarBaseDeptmentService;
import com.carTravelsky.service.carDaily.CarBaseDriverService;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyFeeManageRecordService;
import com.carTravelsky.service.carDaily.CarDailyInsuranceRecordService;
import com.carTravelsky.service.carDaily.CarDailyMaintainRecordService;
import com.carTravelsky.service.carDaily.CarDailyYearIptRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.DateUtil;
import com.google.gson.Gson;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.web.context.SessionContext;

/**
 * @author zy
 *
 */
@Controller
@RequestMapping("/common")
public class CommonControl extends BaseControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseVehicleService carBaseVehicleService;
	@Autowired
	private CarBaseContactsService carBaseContactsService;
	@Autowired
	private CarBaseDeptmentService carBaseDeptmentService;
	@Autowired
	private CarBaseStaffService carBaseStaffService;
	@Autowired
	private CarBaseDriverService carBaseDriverService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	@Autowired
	private CarDailyInsuranceRecordService insuranceRecordService;
	@Autowired
	private CarDailyYearIptRecordService yearIptRecordService;
	@Autowired
	private CarDailyFeeManageRecordService carDailyFeeManageRecordService;
	@Autowired
	private CarDailyMaintainRecordService carDailyMaintainRecordService;
	@RequestMapping("/toNoFindPage")
	public String toNoFindPage() {
		return "error/404";
	}

	@RequestMapping("/toErrorPage")
	public String toErrorPage() {
		return "error/500";
	}

	@RequestMapping("/toDevPage")
	public String toDevPage() {
		return "common/noDev";
	}

	
	/**
	 * 根据部门id查询该部门的车辆
	 * @return
	 */
	@RequestMapping("/getSelect2ListCarByDeptId")
	@ResponseBody
	public List<Select2VO> getSelect2ListCarByDeptId(String deptId) {
		try {
			return carBaseVehicleService.getSelect2ListCarByDeptId(deptId);
		} catch (Exception e) {
			logger.error("获取部门树失败{0}", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: getSelect2ListLicenseno
	 * @Description: 车牌号相关 下拉框
	 * @param request
	 * @param carBaseVehicleDO
	 *            车辆档案对象
	 * @param str
	 *            查询字符串
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @date: 2017年11月5日 上午11:48:37
	 */
	@RequestMapping("/getSelect2ListLicenseno")
	@ResponseBody
	public List<Select2VO> getSelect2ListLicenseno(HttpServletRequest request, CarBaseVehicleDO carBaseVehicleDO,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			return carBaseVehicleService.getCarBaseVehicleListForDropdown(carBaseVehicleDO, str, currentUser);
		} catch (Exception e) {
			logger.error("车牌号下拉框获取出错：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getSelect2ListDriver
	 * @Description: 驾驶员下拉框列表
	 * @param request
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @date: 2017年11月2日 下午3:48:58
	 */
	@RequestMapping("/getSelect2ListDriver")
	@ResponseBody
	public List<Select2VO> getSelect2ListDriver(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str,
			@RequestParam(value = "deptId", required = false) String deptId) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if (!StringUtils.isBlank(deptId)) {
				return carBaseStaffService.getListDriverStaffByDeptId(currentUser,deptId, str);
			}
			return carBaseStaffService.getListDriverStaffByCompanyId(currentUser, str);
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getSelect2ListStaff
	 * @Description: 职员的相关下拉框列表
	 * @param request
	 * @param str
	 *            搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @date: 2017年11月2日 下午2:04:10
	 */
	@RequestMapping("/getSelect2ListStaff")
	@ResponseBody
	public List<Select2VO> getSelect2ListStaff(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			return carBaseStaffService.getSelect2ListStaff(currentUser, str);
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: getSelect2ListStaffGoAlong
	 * @Description: TODO本部门职员的相关下拉框列表
	 * @param request
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: admin  
	 * @date: 2017年12月6日 下午2:35:43
	 */
	@RequestMapping("/getSelect2ListStaffGoAlong")
	@ResponseBody
	public List<Select2VO> getSelect2ListStaffGoAlong(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			List<Select2VO>  lt = carBaseStaffService.getSelect2ListStaff(currentUser, str,currentUser.getDeptID());
			return lt;
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getSelect2ListDeptment
	 * @Description: 获取部门下拉框列表
	 * @param request
	 * @param str
	 *            搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @date: 2017年11月2日 下午1:38:30
	 */
	@RequestMapping("/getSelect2ListDeptment")
	@ResponseBody
	public List<Select2VO> getSelect2ListDeptment(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			return carBaseDeptmentService.getSelect2ListDeptInfo(currentUser, str);
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getSelect2ListContactCompany
	 * @Description: 往来单位相关的下拉框
	 * @param kind
	 *            查询类型（车管所、修理厂、车辆供应商等）
	 * @param searchStr
	 *            查询参数
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @date: 2017年11月2日 上午10:52:53
	 */
	@RequestMapping("/getSelect2ListContactCompany")
	@ResponseBody
	public List<Select2VO> getSelect2ListContactCompany(HttpServletRequest request, String kind,
			@RequestParam(value = "q", required = false) String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			return carBaseContactsService.getSelect2ListContactCompany(currentUser, kind, searchStr);
		} catch (Exception e) {
			logger.error("相关往来单位查询出错：{0}", e);
			return null;
		}
	}

	/**
	 * @Title: getLicenseno
	 * @Description: 查询车牌号的下拉菜单
	 * @return: void
	 * @author: zy
	 * @date: 2017-10-17 下午4:01:49
	 */
	@RequestMapping("/getLicenseno")
	@ResponseBody
	public List<Select2VO> getLicenseno(HttpServletRequest request, CarBaseVehicleDO carBaseVehicleDO,
			@RequestParam(value = "q", required = false) String searchStr) {
		CarSysUserDO userDo = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		carBaseVehicleDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
		// return commonService.getCarBaseVehicleList(carBaseVehicleDO,
		// searchStr);
		return carBaseVehicleService.getCarBaseVehicleListForDropdown(carBaseVehicleDO, searchStr, userDo);
	}

	/**
	 * @Title: getListKeyCode
	 * @Description: 查询系统字典表中的下拉菜单
	 * @param request
	 * @param kind
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: zy
	 * @date: 2017-10-18 下午2:33:52
	 */
	@RequestMapping("/getListKeyCode")
	@ResponseBody
	public List<Select2VO> getListKeyCode(HttpServletRequest request, KeyCodeMasterDO keyCodeMasterDO, String kind,
			@RequestParam(value = "q", required = false) String searchStr) {
		CarSysUserDO userDo = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			return keyCodeMasterService.getKindCodeForDropdown(kind, searchStr, userDo, keyCodeMasterDO);
		} catch (Exception e) {
			logger.error("查询项目keyCode：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getListFeeUnitsValue
	 * @Description: 往来单位下拉框 key-value 相等
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @date: 2017年11月1日 上午11:17:37
	 */
	@RequestMapping("getListFeeUnitsValue")
	@ResponseBody
	public List<Select2VO> getListFeeUnitsValue() {
		try {
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			// 加载外来单位下拉框
			// return commonService.getListFeeUnitsValue(select2vos);
			return null;
		} catch (Exception e) {
			logger.error("获取所有往来单位出错{0}", e);
			return null;
		}
	}
	
	@RequestMapping("/getSelect2ListDeptmentByLevel")
	@ResponseBody
	public List<Select2VO> getSelect2ListDeptmentByLevel(HttpServletRequest request,
			 String levels) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			return carBaseDeptmentService.getSelect2ListDeptInfoByLevel(currentUser, levels);
		} catch (Exception e) {
			logger.error("部门下拉框查询出错：{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: getcarAge
	 * @Description: TODO饼图数据
	 * @return
	 * @return: String
	 * @author: bxl  
	 * @date: 2017年12月7日 上午9:22:54
	 */
	@RequestMapping("/getcarAge")
	@ResponseBody
	public String getcarAge(){		
		List<CarBaseVehicleDO> carList = carBaseVehicleService.getLicenseNoAllList();
		List<CarAge> listage = new ArrayList<CarAge>();
		java.util.Date now = new Date();
		int j = 0;//0-3
		int k = 0;//3-5
		int l = 0;//5-7
		int z = 0;//10以上
		int h = 0;//7-10
		for (int i = 0 ; i<carList.size() ; i++) {			
			
			int time = (int) Math.ceil((DateUtil.year(now) - DateUtil.year(carList.get(i).getBuyTime())));
			if(1<time&&time < 3){
				j++;
			}else if(3< time&&time < 5){
				k++;
			}else if(5< time&&time < 7){
				l++;
			}else if(7< time&&time < 10){
				h++;
			}else if(time>10){
				z++;
			}
			
			
		}
		float sum = j+k+l+z;
		CarAge age1 = new CarAge("1年以上3年以下",(int) Math.rint((j/sum)*100));
		listage.add(age1);
		CarAge age2 = new CarAge("3年以上5年以下",(int) Math.rint((k/sum)*100));
		listage.add(age2);
		CarAge age3 = new CarAge("5年以上7年以下",(int) Math.rint((l/sum)*100));
		listage.add(age3);
		CarAge age5 = new CarAge("7年以上10年以下",(int) Math.rint((h/sum)*100));
		listage.add(age5);
		CarAge age4 = new CarAge("10年以上",(int) Math.rint((z/sum)*100));
		listage.add(age4);
		
		Gson gson = new Gson();  
		return gson.toJson(listage);
		
	}
	/**
	 * 
	 * @Title: getDailySum
	 * @Description: TODO得到页面到期提醒数量
	 * @param request
	 * @return
	 * @return: String
	 * @author: bxl  
	 * @date: 2017年12月8日 上午11:03:10
	 */
	@RequestMapping("/getDailySum")
	@ResponseBody
	public String getDailySum(HttpServletRequest request,PageBounds pageBounds){
		CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
			pageBounds.setNeedCompany(true);
			pageBounds.setCompanyID(userDo.getDeptID());
		}
		List<CarAge> listage = new ArrayList<CarAge>();
		int insuranceCount = insuranceRecordService.countExpireBycomID(Integer
				.valueOf(3),pageBounds);// 得到保险到期数量
		int yearIptCount = yearIptRecordService.countExpireById(Integer
				.valueOf(3),pageBounds);// 得到年检到期数量
		int feemanagerCount = carDailyFeeManageRecordService.countExpireById(Integer
				.valueOf(3),pageBounds);//得到规费到期数量
		int maintaincount = carDailyMaintainRecordService.ExpireCountById(new Integer(3).intValue(),pageBounds);
		// 得到保险到期数量
		CarAge age1 = new CarAge("保险到期",insuranceCount);
		listage.add(age1);
		// 得到年检到期数量
		CarAge age2 = new CarAge("年检到期",yearIptCount);
		listage.add(age2);
		//得到规费到期数量
		CarAge age3 = new CarAge("规费到期",feemanagerCount);
		listage.add(age3);
		//得到保养到期数量
		CarAge age5 = new CarAge("保养到期",maintaincount);
		listage.add(age5);
		Gson gson = new Gson();  
		return gson.toJson(listage);
		
		
	}
}
