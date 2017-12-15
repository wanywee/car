package com.carTravelsky.control.carDaily;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyAddoilRecordService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.AttributeContext;
import com.stopec.web.context.SessionContext;



@RequestMapping("/addoilRecord")
@Controller
public class AddoilRecordControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CarDailyAddoilRecordService addoilRecordService;

	/**
	 * 进入加油记录
	 * @return
	 */
	@RequestMapping("/listFuelDiary")
	public String listFuelDiary() {
		try {
			return "dailyHandler/listFuelDiary";
		} catch (Exception e) {
			logger.error("进入加油记录出错", e);
			return null;
		}
	}
	
	
	
	/**
	 * @Title: editAddoilRecord 修改加油记录
	 * @Description: TODO
	 * @param request
	 * @param ID 需要修改的数据id
	 * @return
	 * @return: String
	 * @author: admin  
	 * @date: 2017年11月7日 上午10:01:17
	 */
	@RequestMapping("/editAddoilRecord")
	public String editAddoilRecord(HttpServletRequest request,
			@RequestParam(value = "ID")Integer ID) {
		try {
			CarDailyAddoilRecordDO recordDO = addoilRecordService.getCarDailyAddoilRecordByID(ID);
			request.setAttribute("recordDO", recordDO);
			return "dailyHandler/editFuelDiary";
		} catch (Exception e) {
			logger.error("进入修改加油记录页面失败", e);
			return null;
		}
	}
	
	/**
	 * @Title: addAddoilRecord 修改加油记录
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 * @author: admin  
	 * @date: 2017年11月7日 上午10:08:30
	 */
	@RequestMapping("/addAddoilRecord")
	public String addAddoilRecord(HttpServletRequest request) {
		try {
			return "dailyHandler/editFuelDiary";
		} catch (Exception e) {
			logger.error("进入添加油记录页面失败", e);
			return null;
		}
	}
	
	
	
	/**
	 * @Title: saveAddoilRecord 保存记录
	 * @Description: TODO
	 * @param carDailyAddoilRecordDO
	 * @param request
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:10:54
	 */
	@RequestMapping("/saveAddoilRecord")
	@ResponseBody
	public ReturnDataInfo saveAddoilRecord(CarDailyAddoilRecordDO carDailyAddoilRecordDO,HttpServletRequest request) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if (carDailyAddoilRecordDO.getId() == null) {
				carDailyAddoilRecordDO.setCreatePeople(userDO.getUsername());
				carDailyAddoilRecordDO.setCreateTime(new Date());
				carDailyAddoilRecordDO.setCompanyId(userDO.getCompanyId());
			}
			carDailyAddoilRecordDO.setUpdatePeople(userDO.getUsername());
			carDailyAddoilRecordDO.setUpdateTime(new Date());
			addoilRecordService.saveCarDailyAddoilRecord(carDailyAddoilRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存加油记录成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ReturnDataInfo.createFailedExecuteResult("保存加油记录失败！");
		}
	}
	
	
	
	/**
	 * @Title: deleteAddoilRecord 删除记录
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:10:35
	 */
	@RequestMapping("/deleteAddoilRecord")
	@ResponseBody
	public ReturnDataInfo deleteAddoilRecord(@RequestParam(value = "ids[]") String[] ids) {
		try {
			List<String> idList = Arrays.asList(ids);
			addoilRecordService.deleteAddoilRecord(idList);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除失败！");
		}
	}
	
	
	/**
	 * @Title: addoilRecordDetail 详情列表
	 * @Description: TODO
	 * @param id
	 * @param request
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:10:15
	 */
	@RequestMapping("/addoilRecordDetail")
	public String addoilRecordDetail(@RequestParam(value = "ID")Integer id,HttpServletRequest request,@RequestParam(value = "show", required = false) String show) {
		try {
			CarDailyAddoilRecordDO recordByID = addoilRecordService.getCarDailyAddoilRecordByID(id);
			if (!StringUtils.isBlank(show)) {
				/* 图片地址字符串转单个数组 */
				if (StringUtils.isNotBlank(recordByID.getPhotoUrl())) {
				String photoUrlString = recordByID.getPhotoUrl().trim();
					if (!photoUrlString.equals("")) {
						String[] photos = photoUrlString.split(",");
						List<String> photoUrlList = new ArrayList<String>();
						Collections.addAll(photoUrlList, photos);
						recordByID.setPhotoUrlList(photoUrlList);
					}
				}

			}
			request.setAttribute("recordByID", recordByID);
			request.setAttribute("addoilTime", YSTConstants.sdfs.format(recordByID.getAddoilTime()));
			return "dailyHandler/addoilRecordDetail";
		} catch (Exception e) {
			logger.error("进入加油记录详情页面失败", e);
			return null;
		}
	}
	
	
	/**
	 * @Title: getListAddoilRecord 获取加油记录列表
	 * @Description: TODO
	 * @param request
	 * @param pageBounds 分页对象
	 * @param carDailyAddoilRecordDO 
	 * @param searchStr 搜索字段
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:08:51
	 */
	@ResponseBody
	@RequestMapping("/getListAddoilRecord")
	public Map<String, Object> getListAddoilRecord(HttpServletRequest request,PageBounds pageBounds,
			CarDailyAddoilRecordDO carDailyAddoilRecordDO,String searchStr) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if(YSTConstants.IS_CURRENT_DEP_VIEW==userDO.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(userDO.getDeptID());
			}
			Integer companyId = userDO.getCompanyId();
			carDailyAddoilRecordDO.setCompanyId(companyId);
			// 全局搜索
			if (StringUtils.isNotBlank(searchStr)) {
				List<CarDailyAddoilRecordDO> List = addoilRecordService.getSearchAddoilRecordList(searchStr,pageBounds,companyId);
				return pageBounds.putResultObj(List);
			}
			// 获取状态为未删除的数据
			List<CarDailyAddoilRecordDO> addoilRecordList = addoilRecordService.getCarDailyAddoilRecordList(carDailyAddoilRecordDO, pageBounds);
			return pageBounds.putResultObj(addoilRecordList);
		} catch (Exception e) {
			logger.error("获取加油记录失败", e);
			return null;
		}
		
	}
	
	
	/**
	 * @Title: getSelect2ListFueloil 获取燃油标号下拉菜单
	 * @Description: TODO
	 * @param request
	 * @param str 关键字
	 * @return
	 * @return: List<Select2VO>
	 * @author: 黄进
	 * @date: 2017年11月8日 下午2:42:35
	 */
	@RequestMapping("/getSelect2ListFueloil")
	@ResponseBody
	public List<Select2VO> getSelect2ListFueloil(HttpServletRequest request,@RequestParam(value = "q", required = false) String str) {
		try {
			if (StringUtils.isNotBlank(str)) {
				return addoilRecordService.getSelect2ListFueloil(str);
			}
			str = "";
			return addoilRecordService.getSelect2ListFueloil(str);
		} catch (Exception e) {
			logger.error("燃油标号下拉框获取出错：{0}", e);
			return null;
		}
	}
	
	
	/**
	 * 
	 * @Title: fileDelete
	 * @Description: 加油图片删除
	 * @param imgPath
	 * @return
	 * @return: ReturnDataInfo<String>
	 * @throws Exception 
	 */
	@RequestMapping("/fileDelete")
	@ResponseBody
	public ReturnDataInfo<String> fileDelete(HttpServletRequest request,@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) throws Exception {
		imgPath = new String(imgPath.getBytes("iso8859-1"),"utf-8");
		// 根据车辆档案id得到车辆档案信息 删除url中的
		CarDailyAddoilRecordDO carDailyAddoilRecordDO = addoilRecordService.getCarDailyAddoilRecordByID(Integer.parseInt(id));
		String imgString = carDailyAddoilRecordDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		carDailyAddoilRecordDO.setPhotoUrl(newUrl);
		if(StringUtils.isBlank(newUrl)){
			carDailyAddoilRecordDO.setPhotoUrl(" ");
		}
		 if(newUrl.endsWith(",")){
			 newUrl=newUrl.substring(0,newUrl.length()-1);
	     }
		// 设置新地址
		// 更新车辆档案中的photoUrl
		addoilRecordService.saveCarDailyAddoilRecordDO(carDailyAddoilRecordDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		//FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}
	
	
}
