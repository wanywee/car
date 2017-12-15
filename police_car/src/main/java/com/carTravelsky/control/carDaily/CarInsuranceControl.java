package com.carTravelsky.control.carDaily;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyInsuranceRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyInsuranceRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.web.context.AttributeContext;
import com.stopec.web.context.SessionContext;

@Controller
@RequestMapping("/carInsurance")
public class CarInsuranceControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarDailyInsuranceRecordService carDailyInsuranceRecordService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;

	/**
	 * @Title: listCarInsurance 进入保险记录界面
	 * @Description: TODO
	 * @return
	 * @return: String 返回视图名称
	 * @author: 黄进
	 * @date: 2017年11月14日 下午2:04:53
	 */
	@RequestMapping("/listCarInsurance")
	public String listCarInsurance() {
		try {
			return "dailyHandler/listInsurance";
		} catch (Exception e) {
			logger.error("进入保险记录失败", e);
			return null;
		}
	}

	/**
	 * @Title: addCarInsurance 添加保险记录
	 * @Description: TODO
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月14日 下午3:58:41
	 */
	@RequestMapping("/addCarInsurance")
	public String addCarInsurance() {
		return "dailyHandler/editCarInsurance";
	}

	/**
	 * @Title: editCarInsurance 修改保险记录信息
	 * @Description: TODO
	 * @param request
	 * @param id
	 * @return
	 * @return: String * @author: 黄进
	 * @date: 2017年11月15日 上午9:13:36
	 */
	@RequestMapping("/editCarInsurance")
	public String editCarInsurance(HttpServletRequest request,
			@RequestParam(value = "ID") Integer id) {
		try {
			CarDailyInsuranceRecordDO carDailyInsuranceRecordByID = carDailyInsuranceRecordService
					.getCarDailyInsuranceRecordByID(id);
			request.setAttribute("recordDO", carDailyInsuranceRecordByID);
			return "dailyHandler/editCarInsurance";
		} catch (Exception e) {
			logger.error("进入修改保险记录失败", e);
			return null;
		}
	}

	/**
	 * @Title: saveCarInsurance 保存保险记录
	 * @Description: TODO
	 * @param carDailyAddoilRecordDO
	 * @param request
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月15日 下午2:56:26
	 */
	@RequestMapping("/saveCarInsurance")
	@ResponseBody
	public ReturnDataInfo saveCarInsurance(
			CarDailyInsuranceRecordDO carDailyAddoilRecordDO,
			HttpServletRequest request) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(
					request, YSTConstants.USERINFO);
			if (carDailyAddoilRecordDO.getId() == null) {
				carDailyAddoilRecordDO.setCreatePeople(userDO.getUsername());
				carDailyAddoilRecordDO.setCreateTime(new Date());
				carDailyAddoilRecordDO.setCompanyid(userDO.getCompanyId());
			}else {
				String photoUrl = carDailyInsuranceRecordService.getCarDailyInsuranceRecordByID(carDailyAddoilRecordDO.getId()).getPhotoUrl();
					String newPhotoUrl = carDailyAddoilRecordDO.getPhotoUrl() + photoUrl;
					carDailyAddoilRecordDO.setPhotoUrl(newPhotoUrl);
			}
			carDailyAddoilRecordDO.setUpdatePeople(userDO.getUsername());
			carDailyAddoilRecordDO.setUpdateTime(new Date());
			carDailyInsuranceRecordService
					.saveCarDailyInsuranceRecord(carDailyAddoilRecordDO);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("保存保险记录成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存保险记录失败", e);
			return ReturnDataInfo.createFailedExecuteResult("保存保险记录失败！");
		}
	}
	
	/**
	 * @Title: carInsurancefileDelete 修改保单图片
	 * @Description: TODO
	 * @param request
	 * @param id
	 * @param imgPath
	 * @return
	 * @return: ReturnDataInfo<String>
	 * @author: admin  
	 * @throws UnsupportedEncodingException 
	 * @date: 2017年12月11日 下午3:29:45
	 */
	@RequestMapping("/carInsurancefileDelete")
	@ResponseBody
	public ReturnDataInfo<String> carInsurancefileDelete(HttpServletRequest request,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) throws Exception {
		CarDailyInsuranceRecordDO insuranceRecordDO = carDailyInsuranceRecordService.getCarDailyInsuranceRecordByID(Integer.parseInt(id));
		// 得到当前保单的详情照片
		imgPath = new String(imgPath.getBytes("iso8859-1"),"utf-8");
		String imgString = insuranceRecordDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		// 设置新地址
		insuranceRecordDO.setPhotoUrl(newUrl);
		// 更新车辆档案中的photoUrl
		carDailyInsuranceRecordService.saveCarDailyInsuranceRecord(insuranceRecordDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		//FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}

	/**
	 * @Title: deleteCarInsurance 删除保险记录信息
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月15日 上午9:55:54
	 */
	@RequestMapping("/deleteCarInsurance")
	@ResponseBody
	public ReturnDataInfo deleteCarInsurance(
			@RequestParam(value = "ids[]") String[] ids) {
		try {
			List<String> asList = Arrays.asList(ids);
			carDailyInsuranceRecordService.updateDeletecode(asList);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("删除保险记录成功！");
		} catch (Exception e) {
			logger.error("删除保险记录失败", e);
			return ReturnDataInfo.createFailedExecuteResult("删除保险记录失败！");
		}
	}

	/**
	 * @Title: getSelect2ListInsuranceType 获取保险种类下拉菜单
	 * @Description: TODO
	 * @param request
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: admin
	 * @date: 2017年11月15日 上午10:13:25
	 */
	@RequestMapping("/getSelect2ListInsuranceType")
	@ResponseBody
	public List<Select2VO> getSelect2ListInsuranceType(
			HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		try {
			if (StringUtils.isNotBlank(str)) {
				return carDailyInsuranceRecordService
						.getSelect2InsuranceType(str);
			}
			str = "";
			return carDailyInsuranceRecordService.getSelect2InsuranceType(str);
		} catch (Exception e) {
			logger.error("燃油标号下拉框获取出错：{0}", e);
			return null;
		}
	}

	/**
	 * @Title: detailCarInsurance 获取详情信息
	 * @Description: TODO
	 * @param id
	 * @param request
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月15日 下午3:01:30
	 */
	@RequestMapping("/detailCarInsurance")
	public String detailCarInsurance(@RequestParam(value = "ID") Integer id,
			HttpServletRequest request) {
		try {
			CarDailyInsuranceRecordDO carDailyInsuranceRecordByID = carDailyInsuranceRecordService
					.getCarDailyInsuranceRecordByID(id);
			request.setAttribute("recordByID", carDailyInsuranceRecordByID);
			request.setAttribute("insureDate", YSTConstants.sdfs
					.format(carDailyInsuranceRecordByID.getInsureDate()));
			request.setAttribute("endDate", YSTConstants.sdfs
					.format(carDailyInsuranceRecordByID.getEndDate()));
			return "dailyHandler/carInsuranceDetail";
		} catch (Exception e) {
			logger.error("进入保险记录详情失败", e);
			return null;
		}
	}

	/**
	 * @Title: getListAddoilRecord 获取保险记录信息
	 * @Description: TODO
	 * @param request
	 * @param pageBounds
	 * @param carDailyAddoilRecordDO
	 * @param searchStr
	 *            搜索关键字
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年11月14日 下午3:56:15
	 */
	@ResponseBody
	@RequestMapping("/getListCarInsurance")
	public Map<String, Object> getListCarInsurance(HttpServletRequest request,
			PageBounds pageBounds,
			CarDailyInsuranceRecordDO carDailyInsuranceRecordDO,
			String searchStr, String count) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(
					request, YSTConstants.USERINFO);
			KeyCodeMasterDO  kcDo = keyCodeMasterService.getKeyCodeMasterByID(19);
		     count =kcDo.getCode();
			if (YSTConstants.IS_CURRENT_DEP_VIEW == userDO.getIsAllView()) {
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(userDO.getDeptID());
			}
			Integer companyId = userDO.getCompanyId();
			carDailyInsuranceRecordDO.setCompanyid(companyId);
			// 全局搜索
			if (StringUtils.isNotBlank(searchStr)) {
				List<CarDailyInsuranceRecordDO> list = carDailyInsuranceRecordService
						.getListCarInsuranceSearchStr(searchStr, pageBounds,
								companyId);
				if (StringUtils.isNotBlank(count)) {
					List<CarDailyInsuranceRecordDO> expirList = carDailyInsuranceRecordService
							.getExpireList(Integer.valueOf(count), pageBounds);
					for (CarDailyInsuranceRecordDO carDailyInsuranceRecordDO1 : list) {
						for (CarDailyInsuranceRecordDO carDailyInsuranceRecordDO2 : expirList) {
							if (carDailyInsuranceRecordDO1.getId().equals(
									carDailyInsuranceRecordDO2.getId())) {
								carDailyInsuranceRecordDO1.setStatus(1);
							}
						}
					}
				}
				pageBounds.setTotalNumber(list.size());
				return pageBounds.putResultObj(list);
			}
			// 获取状态为未删除的数据
			List<CarDailyInsuranceRecordDO> list = carDailyInsuranceRecordService
					.getCarDailyInsuranceRecordList(carDailyInsuranceRecordDO,
							pageBounds);
			if (StringUtils.isBlank(count)) {
				return pageBounds.putResultObj(list);
			}
			List<CarDailyInsuranceRecordDO> expirList = carDailyInsuranceRecordService
					.getExpireList(Integer.valueOf(count), pageBounds);
			for (CarDailyInsuranceRecordDO carDailyInsuranceRecordDO1 : list) {
				for (CarDailyInsuranceRecordDO carDailyInsuranceRecordDO2 : expirList) {
					if (carDailyInsuranceRecordDO1.getId().equals(
							carDailyInsuranceRecordDO2.getId())) {
						carDailyInsuranceRecordDO1.setStatus(1);
					}
				}
			}

			pageBounds.setTotalNumber(list.size());
			return pageBounds.putResultObj(list);
		} catch (Exception e) {
			logger.error("获取保险记录失败", e);
			return null;
		}

	}

}
