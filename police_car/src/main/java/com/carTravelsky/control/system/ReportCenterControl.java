package com.carTravelsky.control.system;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.utils.MSExcelUtil;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.web.context.SessionContext;

@Controller
@RequestMapping("/reportCenter")
public class ReportCenterControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseVehicleService carBaseVehicleService;

	/**
	 * 
	 * @Title: listReportCenter 进入报表中心
	 * @Description: TODO 进入报表中心
	 * @return: String 跳转页面
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年11月29日 下午2:13:02
	 */
	@RequestMapping("/listReportCenter")
	public String listReportCenter() {
		try {
			return "reportCenter/listReportCenter";
		} catch (Exception e) {
			logger.error("进入报表中心出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: reportsCarCheck 车辆登记表
	 * @Description: TODO 车辆登记表
	 * @return: String 车辆登记表
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年11月29日 下午3:29:07
	 */
	@RequestMapping("/reportsCarCheck")
	public String reportsCarCheck() {
		try {
			return "reportCenter/reportsCarCheck";
		} catch (Exception e) {
			logger.error("进入车辆登记报出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getReportsCarCheck 获取所有报表车辆信息
	 * @Description: TODO 获取所有报表车辆信息
	 * @param searchStr
	 *            搜索字段
	 * @return: Map<String,Object>
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年11月29日 下午5:12:25
	 */
	@ResponseBody
	@RequestMapping("/getReportsCarCheck")
	public Map<String, Object> getReportsCarCheck(HttpServletRequest request, PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			List<CarBaseVehicleDO> carBaseVehicleDOs = new ArrayList<>();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", currentUser.getCompanyId());
			if (!StringUtils.isBlank(searchStr)) {
				paraMap.put("searchStr", searchStr);
				paraMap.put("exportFlag", "YES");
				carBaseVehicleDOs = carBaseVehicleService.getsearchCarBaseVehicle(paraMap, pageBounds);
				return pageBounds.putResultObj(carBaseVehicleDOs);
			}
			carBaseVehicleDOs = carBaseVehicleService.getListVehicleRecordsByCompanyId(paraMap, pageBounds);
			return pageBounds.putResultObj(carBaseVehicleDOs);
		} catch (Exception e) {
			logger.error("获取车辆档案表出错{0}", e);
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value="/exportCarReport")
	public Map<String, Object> exportCarReport(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "pagenum") Integer pagenum, @RequestParam(value = "searchStr") String searchStr) {
		try {
			searchStr=new String(searchStr.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			List<CarBaseVehicleDO> carBaseVehicleDOs = new ArrayList<>();
			Map<String, Object> paraMap = new HashMap<String, Object>();
			MSExcelUtil msExcelUtil = new MSExcelUtil();
			if (!StringUtils.isBlank(searchStr)) {
				paraMap.put("id", currentUser.getCompanyId());
				paraMap.put("searchStr", searchStr);
				carBaseVehicleDOs = carBaseVehicleService.getListSearchCarReport(searchStr, pagenum);
				boolean result = msExcelUtil.ExportExcel(carBaseVehicleDOs,response);
				paraMap.put("result", result);
				return paraMap;
			}
			carBaseVehicleDOs = carBaseVehicleService.getListCarReport(searchStr, pagenum);
			boolean result = msExcelUtil.ExportExcel(carBaseVehicleDOs,response);
			paraMap.put("result", result);
			return paraMap;
		} catch (Exception e) {
			logger.error("获取车辆登记表出错{0}", e);
			return null;
		}
	}

}
