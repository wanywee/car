package com.carTravelsky.control.carDaily;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyRepairRecordService;
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
 * @ClassName: RepairRecordController
 * @Description: 维修记录Controller
 * @author: yanlinlong
 * @date: 2017年10月30日 下午3:52:12
 */
@Controller
@RequestMapping("repairRecord")
public class RepairRecordController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarDailyRepairRecordService carDailyRepairRecordService;

	/**
	 * 
	 * @Title: listRepairRecords
	 * @Description: 跳转到维修记录页面
	 * @return
	 * @return: String
	 */
	@RequestMapping("listRepairRecords")
	public String listRepairRecords() {
		try {
			return "dailyHandler/listRepairRecords";
		} catch (Exception e) {
			logger.error("进入加油记录出错", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getListRepairRecordsByCompanyId
	 * @Description: 维修记录列表展示和全局搜索
	 * @param request
	 * @param pageBounds
	 * @param searchStr
	 * @return
	 * @return: Map<String,Object>
	 */
	@RequestMapping(value = "getListRepairRecords")
	@ResponseBody
	public Map<String, Object> getListRepairRecordsByCompanyId(
			HttpServletRequest request, PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			if(YSTConstants.IS_CURRENT_DEP_VIEW==currentUser.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}
			List<CarDailyRepairRecordDO> carRepairRecordDOs = new ArrayList<>();
			if (!StringUtils.isBlank(searchStr)) {
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("id", currentUser.getCompanyId());
				paraMap.put("searchStr", searchStr);
				carRepairRecordDOs = carDailyRepairRecordService
						.getsearchRepairRecords(paraMap, pageBounds);
				return pageBounds.putResultObj(carRepairRecordDOs);
			}
			carRepairRecordDOs = carDailyRepairRecordService
					.getListRepairRecordsByCompanyId(
							currentUser.getCompanyId(), pageBounds);
			return pageBounds.putResultObj(carRepairRecordDOs);
		} catch (Exception e) {
			logger.error("根据公司ID,获取车辆档案信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: addRepairRecords
	 * @Description: 跳转新增维修记录页面
	 * @return
	 * @return: String
	 * @author: yanlinlong  
	 * @date: 2017年11月1日 下午2:55:59
	 */
	@RequestMapping("addRepairRecords")
	public String addRepairRecords() {
		try {
			return "dailyHandler/editRepairRecords";
		} catch (Exception e) {
			logger.error("跳转新增维修记录出错{0}", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: editRepairRecords
	 * @Description: 维修记录的编辑、详情
	 * @param show
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "editRepairRecords")
	public String editRepairRecords(HttpServletRequest request,
			@RequestParam(value = "show", required = false) String show,
			@RequestParam("ID") Integer id, Model model) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", id);
			paraMap.put("companyId", currentUser.getCompanyId());
			// 根据id查询维修记录实体
			CarDailyRepairRecordDO carDailyRepairRecordByID = carDailyRepairRecordService
					.getCarDailyRepairRecordByKeyId(paraMap);
			model.addAttribute("carDailyRepairRecord", carDailyRepairRecordByID);
			// show参数不为空 则为详情页面
			if (!StringUtils.isBlank(show)) {
				
				/* 图片地址字符串转单个数组 */
				String photoUrlString = carDailyRepairRecordByID.getPhotoUrl().trim();
				if (!photoUrlString.equals("")) {
					String[] photos = photoUrlString.split(",");
					List<String> photoUrlList = new ArrayList<String>();
					Collections.addAll(photoUrlList, photos); 
					carDailyRepairRecordByID.setPhotoUrlList(photoUrlList);
				}
				return "dailyHandler/detialRepairRecords";
			}
			return "dailyHandler/editRepairRecords";
		} catch (Exception e) {
			logger.error("操作维修记录出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: updateRepairRecord
	 * @Description: 新增和修改 维修记录
	 * @param request
	 * @param carBaseVehicleDO
	 * @param repairTime 送修时间
	 * @param estimateTackcar 预计取车时间
	 * @return
	 * @return: ReturnDataInfo<String>
	 */
	@RequestMapping("updateRepairRecord")
	@ResponseBody
	public ReturnDataInfo<String> updateRepairRecord(
			HttpServletRequest request, CarDailyRepairRecordDO carDailyRepairRecordDO,
			@RequestParam("repairTimeString") String repairTime,
			@RequestParam("estimateTackcarString") String estimateTackcar) {
		try {
			// 前台time是以string格式到后台 string转data
			SimpleDateFormat buyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
			carDailyRepairRecordDO.setRepairTime(buyTime.parse(repairTime));
			carDailyRepairRecordDO.setEstimateTackcar(buyTime.parse(estimateTackcar));
			ValidationUtils.validate(carDailyRepairRecordDO);
			ValidateResult result = carDailyRepairRecordDO.getValidateResult();
			if (result.getResult()) {
				// 当前登录用户
				CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
						.get(request, "carSysUserDOLogin");
				carDailyRepairRecordService.saveCarDailyRepairRecord(currentUser,
						carDailyRepairRecordDO);
				return ReturnDataInfo
						.createSuccessfulSingleExecuteMessage("操作维修记录成功！");
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
	 * @Title: deleteRepairRecords
	 * @Description: 删除维修记录 只做状态删除
	 * @param request
	 * @param repairID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("/deleteRepairRecords")
	@ResponseBody
	public ReturnDataInfo<Object> deleteRepairRecords(
			HttpServletRequest request,
			@RequestParam(value = "repairID") String repairID) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			String[] splitIds = repairID.split(",");
			CarDailyRepairRecordDO carDailyRepairRecordDO = new CarDailyRepairRecordDO();
			for (String id : splitIds) {
				carDailyRepairRecordDO.setId(Integer.parseInt(id));
				carDailyRepairRecordDO.setDeleteCode(YSTConstants.DISENABLE);
				carDailyRepairRecordService.isUseCarDailyRepairRecord(
						currentUser, carDailyRepairRecordDO);
			}
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除维修记录 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}

}
