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

import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyMaintainRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.web.context.AttributeContext;
import com.stopec.web.context.SessionContext;


@Controller
@RequestMapping("/carDailyMaintain")
public class CarDailyMaintainControl {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CarDailyMaintainRecordService carDailyMaintainRecordService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	/**
	 * @return 进入保养记录界面
	 */
	@RequestMapping("/carDailyMaintainList")
	public String carDailyMaintainList() {
		 try {
			return "dailyHandler/listCarDailyMaintain";
		} catch (Exception e) {
			logger.error("进入保养记录出错", e);
			return null;
		}
	}
	
	
	
	/**
	 * @Title: editCarDailyMaintain 修改保养记录
	 * @Description: TODO
	 * @param request
	 * @param ID
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:26:00
	 */
	@RequestMapping("/editCarDailyMaintain")
	public String editCarDailyMaintain(HttpServletRequest request,
			@RequestParam(value = "ID")Integer ID) {
		try {
			if (ID == null 	) {
				return "dailyHandler/editCarDailyMaintain";
			}
			CarDailyMaintainRecordDO recordByID = carDailyMaintainRecordService.getCarDailyMaintainRecordByID(ID);
			request.setAttribute("recordByID", recordByID);
			return"dailyHandler/editCarDailyMaintain";
		} catch (Exception e) {
			logger.error("进入编辑保养记录页面失败", e);
			return null;
		}
	}
	
	/**
	 * @Title: addCarDailyMaintain 新增保养记录
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:24:15
	 */
	@RequestMapping("/addCarDailyMaintain")
	public String addCarDailyMaintain(HttpServletRequest request) {
		try {
				return "dailyHandler/editCarDailyMaintain";
		} catch (Exception e) {
			logger.error("进入添加保养记录页面失败", e);
			return null;
		}
	}
	
	
	/**
	 * @Title: saveCarDailyMaintain 保存保养记录
	 * @Description: TODO
	 * @param maintainRecordDO
	 * @param request
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:24:31
	 */
	@RequestMapping("/saveCarDailyMaintain")
	@ResponseBody
	public ReturnDataInfo saveCarDailyMaintain(CarDailyMaintainRecordDO maintainRecordDO,HttpServletRequest request) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if (maintainRecordDO.getId() == null) {
				maintainRecordDO.setCreatePeople(userDO.getUsername());
				maintainRecordDO.setCreateTime(new Date());
				maintainRecordDO.setCompanyId(userDO.getCompanyId());
			}else{
				CarDailyMaintainRecordDO maintainRecordByID = carDailyMaintainRecordService.getCarDailyMaintainRecordByID(maintainRecordDO.getId());
				String photoUrl = maintainRecordByID.getPhotoUrl();
					String newPhotoUrl = maintainRecordDO.getPhotoUrl() + photoUrl;
					maintainRecordDO.setPhotoUrl(newPhotoUrl);
			}
			maintainRecordDO.setUpdatePeople(userDO.getUsername());
			maintainRecordDO.setUpdateTime(new Date());
			carDailyMaintainRecordService.saveCarDailyMaintainRecord(maintainRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存保养记录成功");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("保存保养记录失败！");
		}
	}
	
	/**
	 * @Title: carDailyMaintainfileDelete 删除车辆保养记录图片
	 * @Description: TODO
	 * @param request
	 * @param id
	 * @param imgPath
	 * @return
	 * @return: ReturnDataInfo<String>
	 * @author: admin  
	 * @throws Exception 
	 * @date: 2017年12月11日 下午2:22:29
	 */
	@RequestMapping("/carDailyMaintainfileDelete")
	@ResponseBody
	public ReturnDataInfo<String> carDailyMaintainfileDelete(HttpServletRequest request,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) throws Exception {
		CarDailyMaintainRecordDO maintainRecordDO = carDailyMaintainRecordService.getCarDailyMaintainRecordByID(Integer.parseInt(id));
		// 根据车辆档案id得到车辆档案信息 删除url中的
		imgPath = new String(imgPath.getBytes("iso8859-1"),"utf-8");
		String imgString = maintainRecordDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		// 设置新地址
		maintainRecordDO.setPhotoUrl(newUrl);
		// 更新车辆档案中的photoUrl
		carDailyMaintainRecordService.saveCarDailyMaintainRecord(maintainRecordDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		//FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}
	
	/**
	 * @Title: deleteCarDailyMaintain 删除保养记录
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:25:40
	 */
	@RequestMapping("/deleteCarDailyMaintain")
	@ResponseBody
	public ReturnDataInfo deleteCarDailyMaintain(@RequestParam(value = "ids[]") String[] ids) {
		try {
			List<String> idList = Arrays.asList(ids);
			carDailyMaintainRecordService.deleteCarDailyMaintain(idList);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除失败！");
		}
	}
	
	
	/**
	 * @Title: carDailyMaintainDetail 详情页面
	 * @Description: TODO
	 * @param id
	 * @param request
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:24:47
	 */
	@RequestMapping("/carDailyMaintainDetail")
	public String carDailyMaintainDetail(@RequestParam(value = "ID")Integer id,HttpServletRequest request) {
		try {
			CarDailyMaintainRecordDO recordByID = carDailyMaintainRecordService.getCarDailyMaintainRecordByID(id);
			String photoUrl = recordByID.getPhotoUrl().trim();
			if (StringUtils.isNotBlank(photoUrl)) {
				String[] photos = photoUrl.split(",");
				List<String> photoUrlList = new ArrayList<String>();
				Collections.addAll(photoUrlList, photos);
				recordByID.setPhotoUrlList(photoUrlList);
			}
			request.setAttribute("recordByID", recordByID);
			request.setAttribute("maintainDate", YSTConstants.sdfs.format(recordByID.getMaintainDate()));
			return "dailyHandler/carDailyMaintainDetail";
		} catch (Exception e) {
			logger.error("进入保养记录详情页面失败", e);
			return null;
		}
	}
	
	/**
	 * @Title: getCarDailyMaintainList 保养记录列表
	 * @Description: TODO
	 * @param request
	 * @param searchStr 关键字搜索
	 * @param bounds 分页列表
	 * @param recordDO
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:25:09
	 */
	@ResponseBody
	@RequestMapping("/getCarDailyMaintainList")
	public Map<String, Object> getCarDailyMaintainList(HttpServletRequest request,String searchStr,PageBounds bounds,
			CarDailyMaintainRecordDO recordDO,String count){
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			KeyCodeMasterDO  kcDo = keyCodeMasterService.getKeyCodeMasterByID(19);
		     count =kcDo.getCode();
			if(YSTConstants.IS_CURRENT_DEP_VIEW==userDO.getIsAllView()){
				bounds.setNeedCompany(true);
				bounds.setCompanyID(userDO.getDeptID());
			}
			
			Integer companyId = userDO.getCompanyId();
			recordDO.setCompanyId(companyId);
			if (StringUtils.isNotBlank(searchStr)) {
				if(StringUtils.isBlank(count)){
					List<CarDailyMaintainRecordDO> List = carDailyMaintainRecordService.getSearchCarDailyMaintainList(searchStr,bounds,companyId);
					return bounds.putResultObj(List);
				}
				List<CarDailyMaintainRecordDO> List = carDailyMaintainRecordService.getSearchCarDailyMaintainList(searchStr,bounds,companyId);
				List<CarDailyMaintainRecordDO> ExpireList = carDailyMaintainRecordService.getSearchCarDailyMaintainExpireList(searchStr, bounds, companyId, new Integer(count).intValue());
				for (CarDailyMaintainRecordDO carDailyMaintainRecordDO : List) {
					for (CarDailyMaintainRecordDO carDailyMaintainRecordDO1 : ExpireList) {
						if(carDailyMaintainRecordDO.getId() == carDailyMaintainRecordDO1.getId()){
							carDailyMaintainRecordDO.setStatus(1);
						}
					}
					
				}
				return bounds.putResultObj(List);
			}
			recordDO.setDeleteCode("1");
			List<CarDailyMaintainRecordDO> recordList = carDailyMaintainRecordService.getCarDailyMaintainRecordList(recordDO, bounds);
			if(!StringUtils.isBlank(count)){
				List<CarDailyMaintainRecordDO> ExpireList = carDailyMaintainRecordService.ExpireList(recordDO,bounds,new Integer(count).intValue());
				for (CarDailyMaintainRecordDO carDailyMaintainRecordDO : recordList) {
					for (CarDailyMaintainRecordDO carDailyMaintainRecordDO1 : ExpireList) {
						if(carDailyMaintainRecordDO1.getId()==carDailyMaintainRecordDO.getId()){
							carDailyMaintainRecordDO.setStatus(1);
						}
					}
				}
			}
			return bounds.putResultObj(recordList);
		} catch (Exception e) {
			logger.error("获取保养记录出错", e);
			e.printStackTrace();
			return null;
		}
	}

}
