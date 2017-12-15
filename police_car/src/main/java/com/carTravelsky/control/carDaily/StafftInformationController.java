package com.carTravelsky.control.carDaily;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseDriverService;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@RequestMapping("/baseInfo")
@Controller
public class StafftInformationController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseStaffService carBaseStaffService;
	/**
	 * 进入职员信息记录
	 * @return
	 */
	@RequestMapping("/listStaffInfo")
	public String  listDispatchRecord(){
		try {
			return "baseInfo/listStaffInfo";
		} catch (Exception e) {
			logger.error("进入职员信息记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * list职员信息记录
	 * @param request
	 * @param ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getListStaffInfo")
	public Map<String, Object>  getListDispatchRecord(HttpServletRequest request,
			CarBaseStaffDO carBaseStaffDO,PageBounds pageBounds,String searchStr){
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(userDo.getDeptID());
			}else{
				carBaseStaffDO.setCompanyID(YSTConstants.DRIVER_DEPT);
			}
			if(!StringUtils.isBlank(searchStr)){
				List<CarBaseStaffDO> CarBaseStaffDOList = carBaseStaffService.getsearchBaseStaffInfo(searchStr,carBaseStaffDO,pageBounds);
				return pageBounds.putResultObj(CarBaseStaffDOList);
			}
			List<CarBaseStaffDO> carBaseStaffDOList = carBaseStaffService.getCarBaseAllStaffList(carBaseStaffDO, pageBounds);
			return pageBounds.putResultObj(carBaseStaffDOList);
		} catch (Exception e) {
			logger.error("list职员信息出错{0}", e);
				return null;
			}	
		}
	
	/**
	 * 新增职员信息
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editStaffInformation")
	public String  editStaffInformation(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				return "baseInfo/editStaffInfo";
			}
			CarBaseStaffDO carBaseStaffDOById = carBaseStaffService.getCarBaseStaffByStaffID(ID);
			request.setAttribute("recodeDo", carBaseStaffDOById);
			return "baseInfo/editStaffInfo";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: addStaffInformation 新增跳转页面
	 * @Description: TODO 新增跳转页面
	 * @return: String 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年11月1日 下午2:34:39
	 */
	@RequestMapping("/addStaffInformation")
	public String  addStaffInformation(HttpServletRequest request){
		try {
				return "baseInfo/editStaffInfo";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 *新增/修改职员信息数据
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editStaffInformationData")
	@ResponseBody
	public Map<String,Object>  editStaffInformationData(HttpServletRequest request,CarBaseStaffDO carBaseStaffDO){
		ValidationUtils.validate(carBaseStaffDO);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseStaffDO.getValidateResult();
		boolean result2 = result.getResult();
		List<String> messageList = result.getMessageList();
		String messageLine = result.getMessageLine();
		logger.info(messageList + "" + result2 + messageLine);
		if(result2==false){
			resultMap.put("dataWrong",messageLine);
			return resultMap;
		}
		try {
			// 当前登录用户
			carBaseStaffDO.setCompanyID(carBaseStaffDO.getDeptID());
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if(carBaseStaffDO.getBrevityId()==null||carBaseStaffDO.getBrevityId().equals("")){
				carBaseStaffDO.setCreator(currentUser.getUsername());
				int driverId= carBaseStaffService.addStaffInformation(carBaseStaffDO);
			        resultMap.put("result", driverId);
			        resultMap.put("addDriver", "1");
			        return resultMap;  
			}
			carBaseStaffDO.setId(carBaseStaffDO.getBrevityId());
			carBaseStaffDO.setUpdateOperator(currentUser.getUsername());
			int updateInfoById=carBaseStaffService.updateStaffInformation(carBaseStaffDO);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateDriver", "1");
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入驾驶员数据出错{0}", e);
			return null;
		}	
	}
	

	/**
	 * 
	 * @Title: deleteCarDriverInfoById  删除职员信息
	 * @Description: TODO 删除职员信息
	 * @return: result 删除成功 1存在,2删除
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月16日 下午3:58:23
	 */
	@RequestMapping("/deleteStaffInformation")
	@ResponseBody
	public ReturnDataInfo deleteStaffInformation(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids,
			@RequestParam(value="query")String query){
		try {
			if(query.equals("yes")){
				List<CarBaseDriverDO> carBaseDriverDOs=carBaseStaffService.queryStaffIsDriver(ids);
				if (!carBaseDriverDOs.isEmpty()) {
					return ReturnDataInfo.createSuccessfulSingleExecuteMessage("yes");
				}else{
					return ReturnDataInfo.createSuccessfulSingleExecuteMessage("no");
				}
			}
			carBaseStaffService.deleteStaffInformationInfoById(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除职员信息成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除职员信息失败！");
		}
		
	}
	
	
	@RequestMapping("/showStaffInfoDetial")
	public String  showStaffInfoDetial(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			CarBaseStaffDO carBaseStaffDOById = carBaseStaffService.getCarBaseStaffByStaffID(ID);
			request.setAttribute("recodeDo", carBaseStaffDOById);
			return "baseInfo/detialStaffInfo";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	
	/**
	 * 
	 * @Title: isUseDeptInfo 修改状态
	 * @Description: TODO	修改状态
	 * @param ID 选中ID
	 * @param status 状态改变
	 * @return: ReturnDataInfo<Object> 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:46:52
	 */
	@RequestMapping(value = "/isUseStaffInfoRecords", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> isUseDeptInfo(HttpServletRequest request,
			@RequestParam("StatusId") Integer ID,
			@RequestParam("status") Integer status) {
		try {
			CarBaseStaffDO carBaseStaffDO=new CarBaseStaffDO();
			CarBaseDriverDO carBaseDriverDO=new CarBaseDriverDO();
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			carBaseStaffDO.setId(ID);
			carBaseStaffDO.setStatus(status);
			carBaseStaffDO.setUpdateOperator(currentUser.getUsername());
			carBaseDriverDO.setWorkID(ID);
			carBaseDriverDO.setStatus(status);
			carBaseDriverDO.setUpdateOperator(currentUser.getUsername());
			carBaseStaffService.isUseStaffInfoRecords(carBaseStaffDO,carBaseDriverDO);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("是否停用操作成功！");
		} catch (Exception e) {
			logger.error("职员信息列表操作是否停用出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("是否停用操作失败！");
		}
	}
}
