package com.carTravelsky.control.carDaily;

import java.util.ArrayList;
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

import com.carTravelsky.bean.carDaily.CarBaseDepartmentalRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseTrafficRecordsDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingAfterRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairTackRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseDepartmentalRecordService;
import com.carTravelsky.service.carDaily.CarBaseDeptmentService;
import com.carTravelsky.service.carDaily.CarBaseDriverService;
import com.carTravelsky.service.carDaily.CarBaseTrafficRecordsService;
import com.carTravelsky.service.carDaily.CarBaseTrainingAfterRecordService;
import com.carTravelsky.service.carDaily.CarBaseTrainingRecordService;
import com.carTravelsky.service.carDaily.CarDailyRepairTackRecordService;
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
 * @ClassName: CarDriverRecordControl
 * @Description: 驾驶员档案控制器
 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
 * @date: 2017年10月12日 上午11:15:40
 */
@RequestMapping("/baseInfo")
@Controller
public class CarDriverRecordControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseDriverService carBaseDriverService;
	@Autowired
	private CarBaseTrainingRecordService carBaseTrainingRecordService;
	@Autowired
	private CarBaseTrainingAfterRecordService carBaseTrainingAfterRecordService;
	@Autowired
	private CarBaseTrafficRecordsService carBaseTrafficRecordsService;
	@Autowired
	private CarBaseDepartmentalRecordService carBaseDepartmentalRecordService; 
	@Autowired
	private CarBaseDeptmentService carBaseDeptmentService;
	/**
	 * 进入驾驶员档案记录
	 * @return 
	 */
	@RequestMapping("/listCarDriverRecord")
	public String  listCarDriverRecord(){
		try {
			return "baseInfo/listCarDriverRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * 修改驾驶员记录
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editCarDriverRecord")
	public String  editCarDriverRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				return "baseInfo/editCarDriverRecord";
			}
			CarBaseDriverRecordDo carBaseDriverRecordDoByID = carBaseDriverService.getCarDriverEditInfoByID(ID);
			request.setAttribute("recodeDo", carBaseDriverRecordDoByID);
			return "baseInfo/editCarDriverRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: addCarDriverRecord 新增驾驶员档案跳转
	 * @Description: TODO 新增驾驶员档案跳转
	 * @return: String 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年11月1日 下午2:32:06
	 */
	@RequestMapping("/addCarDriverRecord")
	public String  addCarDriverRecord(HttpServletRequest request){
		try {
		/*	CarBaseDriverRecordDo carBaseDriverRecordDoByID=new CarBaseDriverRecordDo();
			carBaseDriverRecordDoByID.setDept("涉外");
			carBaseDriverRecordDoByID.setDeptmentId(53);
			request.setAttribute("recodeDo", carBaseDriverRecordDoByID);*/
				return "baseInfo/editCarDriverRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 *新增/修改驾驶员数据
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editCarDriverInfo")
	@ResponseBody
	public Map<String,Object>  editCarDriverInfo(HttpServletRequest request,CarBaseDriverRecordDo carBaseDriverRecordDo){
		ValidationUtils.validate(carBaseDriverRecordDo);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseDriverRecordDo.getValidateResult();
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
			if(carBaseDriverRecordDo.getDept().equals("涉外")){
				carBaseDriverRecordDo.setDept("53");
			}
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if(carBaseDriverRecordDo.getBrevityId()==null||carBaseDriverRecordDo.getBrevityId().equals("")){
				carBaseDriverRecordDo.setCreator(currentUser.getUsername());
				carBaseDriverRecordDo.setCompanyID(Integer.parseInt(carBaseDriverRecordDo.getDept()));
				int driverId= carBaseDriverService.editCarDriverInfo(carBaseDriverRecordDo);
			        resultMap.put("result", driverId);
			        resultMap.put("addDriver", "1");
			        return resultMap;  
			}
			carBaseDriverRecordDo.setId(carBaseDriverRecordDo.getBrevityId());
			carBaseDriverRecordDo.setUpdateOperator(currentUser.getUsername());
			int updateInfoById=carBaseDriverService.updateCarDriverInfo(carBaseDriverRecordDo);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateDriver", "1");
			 resultMap.put("driverID", carBaseDriverRecordDo.getId());
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入驾驶员数据出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * list驾驶员档案记录
	 * @param request 显示档案记录请求
	 * @param ID 选中的ID
	 * @return	result 若抛异常为空，否则跳转显示分页
	 */
	@ResponseBody
	@RequestMapping("/getListCarDriverRecord")
	public Map<String, Object>  getListDispatchRecord(HttpServletRequest request,
			CarBaseDriverRecordDo carBaseDriverRecordDo,PageBounds pageBounds,String searchStr){
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
				if(userDo.getDeptID()==YSTConstants.DRIVER_DEPT||userDo.getDeptID()==YSTConstants.DISPATCH_DEPT_ID){
					carBaseDriverRecordDo.setCompanyID(200001);//显示驾驶员
				}else {
					carBaseDriverRecordDo.setCompanyID(200000);//不显示驾驶员
				}
			}else{
				carBaseDriverRecordDo.setCompanyID(200001);//显示驾驶员
			}
			if(!StringUtils.isBlank(searchStr)){
				List<CarBaseDriverRecordDo> carDailyRepairRecordList = carBaseDriverService.getSearchDriverRecordList(searchStr,carBaseDriverRecordDo,pageBounds);
				return pageBounds.putResultObj(carDailyRepairRecordList);
			}
			List<CarBaseDriverRecordDo> carDailyRepairRecordList = carBaseDriverService.getCarBaseDriverRecordList(carBaseDriverRecordDo, pageBounds);
			return pageBounds.putResultObj(carDailyRepairRecordList);
		} catch (Exception e) {
			logger.error("list驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: deleteCarDriverInfoById  删除驾驶员档案记录
	 * @Description: TODO 删除驾驶员档案通过选中行
	 * @return: result 删除成功 1存在,2删除
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月16日 下午3:58:23
	 */
	@RequestMapping("/deleteCarDriverRecord")
	@ResponseBody
	public ReturnDataInfo deleteCarDriverInfoById(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
		try {
			carBaseDriverService.deleteCarDriverInfoById(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除驾驶员信息记录成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除驾驶员信息记录失败！");
		}
		
	}
	
	
	/**
	 * 进入在岗转岗培训记录
	 * @return 
	 */
	@RequestMapping("/listWorkOnRecord")
	public String  listWorkOnRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			request.setAttribute("ID", ID);
			return "baseInfo/listWorkOnRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * list上岗转岗培训记录
	 * @param request 显示培训记录请求
	 * @param ID 选中的ID
	 * @return	result 若抛异常为空，否则跳转显示分页
	 */
	@ResponseBody
	@RequestMapping("/getListWorkOnRecord")
	public Map<String, Object>  getListWorkOnRecord(HttpServletRequest request,@RequestParam(value="driverID")Integer ID,
			CarBaseTrainingRecordDO carBaseTrainingRecordDO,PageBounds pageBounds,String searchStr){
		try {
//			if(!StringUtils.isBlank(searchStr)){
//				List<CarBaseTrainingRecordDO> CarBaseTrainingRecordList = carBaseTrainingRecordService.getSearchTrainngRecordList(searchStr, pageBounds);
//				return pageBounds.putResultObj(CarBaseTrainingRecordList);
//			}
			carBaseTrainingRecordDO.setDriverID(ID);
			List<CarBaseTrainingRecordDO> CarBaseTrainingRecordList = carBaseTrainingRecordService.getCarBaseTrainingAllRecordList(carBaseTrainingRecordDO, pageBounds);
			return pageBounds.putResultObj(CarBaseTrainingRecordList);
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 编辑上岗转岗培训记录
	 * @param request	请求
	 * @param ID 选中的培训ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editWorkOnRecord")
	public String  editWorkOnRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,@RequestParam(value="driverID")Integer driverID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				request.setAttribute("driverID", driverID);
				return "baseInfo/editWorkOnRecord";
			}
			CarBaseTrainingRecordDO carBaseTrainingRecordDOById = carBaseTrainingRecordService.getCarBaseTrainingRecordByID(ID);
			String timeString=carBaseTrainingRecordDOById.getTraniningTime();
			carBaseTrainingRecordDOById.setTraniningTime(timeString.substring(0,19));
			request.setAttribute("recodeDo", carBaseTrainingRecordDOById);
			request.setAttribute("driverID", driverID);
			return "baseInfo/editWorkOnRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 *新增/修改上岗转岗培训记录数据
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editWorkOnRecordInfo")
	@ResponseBody
	public Map<String,Object>  editWorkOnRecordInfo(HttpServletRequest request,CarBaseTrainingRecordDO carBaseTrainingRecordDO){
		ValidationUtils.validate(carBaseTrainingRecordDO);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseTrainingRecordDO.getValidateResult();
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
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if(carBaseTrainingRecordDO.getBrevityId()==null||carBaseTrainingRecordDO.getBrevityId().equals("")){
				carBaseTrainingRecordDO.setCreator(currentUser.getUsername());
				int driverId= carBaseTrainingRecordService.editTrainingInfo(carBaseTrainingRecordDO);
			        resultMap.put("result", driverId);
			        resultMap.put("addRecord", "1");
			        return resultMap;  
			}
			carBaseTrainingRecordDO.setId(carBaseTrainingRecordDO.getBrevityId());
			carBaseTrainingRecordDO.setUpdateOperator(currentUser.getUsername());
		    int updateInfoById=carBaseTrainingRecordService.updateCarBaseTrainingRecord(carBaseTrainingRecordDO);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateRecord", "1");
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入培训数据出错{0}", e);
			resultMap.put("message", "录入培训数据出错");
			return resultMap;
		}	
	}
	
	/**
	 * 
	 * @Title: deleteDriverTrainningRecord 删除上岗转岗培训记录
	 * @Description: TODO 删除上岗转岗培训记录
	 * @param request  请求
	 * @param ids 选择的id
	 * @return: ReturnDataInfo 返回处理消息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 上午8:59:01
	 */
	@RequestMapping("/deleteDriverTrainningRecord")
	@ResponseBody
	public ReturnDataInfo deleteDriverTrainningRecord(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
		try {
			carBaseTrainingRecordService.deleteCarBaseTrainingRecordById(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除上岗转岗记录成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除上岗转岗记录失败！");
		}
	}
	
	
	/**
	 * 进入在岗复训记录
	 * @return 跳转到在岗复训记录
	 */
	@RequestMapping("/listTrainingRecord")
	public String  listTrainingRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			request.setAttribute("ID", ID);
			return "baseInfo/listReTrainingRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: getListReTrainingRecord 显示在岗复训记录
	 * @Description: TODO 显示在岗复训记录
	 * @param request 请求
	 * @param ID 驾驶员ID
	 * @param carBaseTrainingAfterRecordDO  在岗复训
	 * @param pageBounds 分页
	 * @return: Map<String,Object> 结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:00:20
	 */
	@ResponseBody
	@RequestMapping("/getListReTrainingRecord")
	public Map<String, Object>  getListReTrainingRecord(HttpServletRequest request,@RequestParam(value="driverID")Integer ID,
			CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO,PageBounds pageBounds,String searchStr){
		try {
			carBaseTrainingAfterRecordDO.setDriverID(ID);
			List<CarBaseTrainingAfterRecordDO> CarBaseTrainingAfterRecordDOList = carBaseTrainingAfterRecordService.getCarBaseTrainingAfterAllRecordList(carBaseTrainingAfterRecordDO, pageBounds);
			return pageBounds.putResultObj(CarBaseTrainingAfterRecordDOList);
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: editReTrainingRecord 编辑在岗复训记录
	 * @Description: TODO	编辑在岗复训记录
	 * @param request 请求
	 * @param ID	 新增
	 * @param driverID 驾驶员ID
	 * @return: String 返回的页面
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:19:56
	 */
	@RequestMapping("/editReTrainingRecord")
	public String  editReTrainingRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,@RequestParam(value="driverID")Integer driverID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				request.setAttribute("driverID", driverID);
				return "baseInfo/editReTrainingRecord";
			}
			CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDOById = carBaseTrainingAfterRecordService.getCarBaseTrainingAfterRecordByID(ID);
			request.setAttribute("recodeDo", carBaseTrainingAfterRecordDOById);
			request.setAttribute("driverID", driverID);
			return "baseInfo/editReTrainingRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: editReTrainingInfo 编辑在岗复训数据
	 * @Description: TODO	编辑在岗复训数据
	 * @param request 请求
	 * @param carBaseTrainingAfterRecordDO 在岗复训记录
	 * @return: Map<String,Object> 在岗复训记录数据
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:49:33
	 */
	@RequestMapping("/editReTrainingInfo")
	@ResponseBody
	public Map<String,Object>  editReTrainingInfo(HttpServletRequest request,CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO){
		ValidationUtils.validate(carBaseTrainingAfterRecordDO);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseTrainingAfterRecordDO.getValidateResult();
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
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			carBaseTrainingAfterRecordDO.setCreator(currentUser.getUsername());
			if(carBaseTrainingAfterRecordDO.getBrevityId()==null||carBaseTrainingAfterRecordDO.getBrevityId().equals("")){
				int driverId= carBaseTrainingAfterRecordService.editReTrainingInfo(carBaseTrainingAfterRecordDO);
			        resultMap.put("result", driverId);
			        resultMap.put("addRecord", "1");
			        return resultMap;  
			}
			carBaseTrainingAfterRecordDO.setUpdateOperator(currentUser.getUsername());
			carBaseTrainingAfterRecordDO.setId(carBaseTrainingAfterRecordDO.getBrevityId());
		    int updateInfoById=carBaseTrainingAfterRecordService.updateCarBaseTrainingAfterRecord(carBaseTrainingAfterRecordDO);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateRecord", "1");
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入培训数据出错{0}", e);
			resultMap.put("message", "录入培训数据出错");
			return resultMap;
		}	
	}
	/**
	 * 
	 * @Title: deleteDriverReTrainingRecord 删除在岗复训记录
	 * @Description: TODO	删除在岗复训记录
	 * @param request 请求
	 * @param ids	选择的行
	 * @return: ReturnDataInfo 返回信息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:50:46
	 */
	@RequestMapping("/deleteDriverReTrainingRecord")
	@ResponseBody
	public ReturnDataInfo deleteDriverReTrainingRecord(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
		try {
			carBaseTrainingAfterRecordService.deleteDriverReTrainingRecord(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除在岗复训记录成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除在岗复训记录失败！");
		}
	}
	
	
	
	
	
	/**
	 * 进入部门变更记录
	 * @return  listDeptRecord 进入交通事故页面
	 */
	@RequestMapping("/listDeptRecord")
	public String  listDeptRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			request.setAttribute("ID", ID);
			return "baseInfo/listDeptChangeRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * list部门变更记录
	 * @param request 显示部门变更请求
	 * @param ID 选中的ID
	 * @return	result 若抛异常为空，否则跳转显示分页
	 */
	@ResponseBody
	@RequestMapping("/getListDeptChangeRecord")
	public Map<String, Object>  getListDeptChangeRecord(HttpServletRequest request,@RequestParam(value="driverID")Integer ID,
			CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO,PageBounds pageBounds,String searchStr){
		try {
//			if(!StringUtils.isBlank(searchStr)){
//				List<CarBaseTrainingRecordDO> CarBaseTrainingRecordList = carBaseTrainingRecordService.getSearchTrainngRecordList(searchStr, pageBounds);
//				return pageBounds.putResultObj(CarBaseTrainingRecordList);
//			}
			carBaseDepartmentalRecordDO.setDriverID(ID);
			List<CarBaseDepartmentalRecordDO> carBaseDepartmentalRecordDOList = carBaseDepartmentalRecordService.getCarBaseDepartmentalAllRecordList(carBaseDepartmentalRecordDO, pageBounds);
			return pageBounds.putResultObj(carBaseDepartmentalRecordDOList);
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 编辑部门变更记录
	 * @param request	请求
	 * @param ID 选中的部门ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editDeptChangeRecord")
	public String  editDeptChangeRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,@RequestParam(value="driverID")Integer driverID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				request.setAttribute("driverID", driverID);
				return "baseInfo/editDeptChangeRecord";
			}
			CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDOById = carBaseDepartmentalRecordService.getCarBaseDepartmentalRecordByID(ID);
			String timeString=carBaseDepartmentalRecordDOById.getChangeTime();
			carBaseDepartmentalRecordDOById.setChangeTime(timeString.substring(0,19));
			request.setAttribute("recodeDo", carBaseDepartmentalRecordDOById);
			request.setAttribute("driverID", driverID);
			return "baseInfo/editDeptChangeRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * 
	 * @Title: editTrafficInfo 编辑部门变更记录数据
	 * @Description: TODO	编辑部门变更记录数据
	 * @param request 请求
	 * @param carBaseDepartmentalRecordDO 部门变更对象
	 * @return: Map<String,Object> 返回的对象
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月19日 上午9:41:04
	 */
	@RequestMapping("/editDeptChangeRecordInfo")
	@ResponseBody
	public Map<String,Object>  editDeptChangeRecordInfo(HttpServletRequest request,CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO){
		ValidationUtils.validate(carBaseDepartmentalRecordDO);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseDepartmentalRecordDO.getValidateResult();
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
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			carBaseDepartmentalRecordDO.setCreator(currentUser.getUsername());
			if(carBaseDepartmentalRecordDO.getBrevityId()==null||carBaseDepartmentalRecordDO.getBrevityId().equals("")){
				int driverId= carBaseDepartmentalRecordService.editDeptChangeInfo(carBaseDepartmentalRecordDO);
			        resultMap.put("result", driverId);
			        resultMap.put("addRecord", "1");
			        return resultMap;  
			}
			carBaseDepartmentalRecordDO.setUpdateOperator(currentUser.getUsername());
			carBaseDepartmentalRecordDO.setId(carBaseDepartmentalRecordDO.getBrevityId());
		    int updateInfoById=carBaseDepartmentalRecordService.updateCarBaseDeptChangeRecord(carBaseDepartmentalRecordDO);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateRecord", "1");
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入培训数据出错{0}", e);
			resultMap.put("message", "录入培训数据出错");
			return resultMap;
		}	
	}
	
	/**
	 * 
	 * @Title: deleteDriverTrainningRecord 删除部门变更记录
	 * @Description: TODO 删除部门变更记录
	 * @param request  请求
	 * @param ids 选择的id
	 * @return: ReturnDataInfo 返回处理消息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 上午8:59:01
	 */
	@RequestMapping("/deleteDeptChangeRecord")
	@ResponseBody
	public ReturnDataInfo deleteDeptChangeRecord(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
		try {
			carBaseDepartmentalRecordService.deleteDeptChangeRecord(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除部门变更记录成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除部门变更记录失败！");
		}
	}
	
	
	
	/**
	 * 进入交通事故违规记录
	 * @return  listTrafficRecord 进入交通事故页面
	 */
	@RequestMapping("/listTrafficRecord")
	public String  listTrafficRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			request.setAttribute("ID", ID);
			return "baseInfo/listTrafficRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * list交通事故记录
	 * @param request 显示交通事故请求
	 * @param ID 选中的ID
	 * @return	result 若抛异常为空，否则跳转显示分页
	 */
	@ResponseBody
	@RequestMapping("/getListTrafficRecord")
	public Map<String, Object>  getListTrafficRecord(HttpServletRequest request,@RequestParam(value="driverID")Integer ID,
			CarBaseTrafficRecordsDO carBaseTrafficRecordsDO,PageBounds pageBounds,String searchStr){
		try {
//			if(!StringUtils.isBlank(searchStr)){
//				List<CarBaseTrainingRecordDO> CarBaseTrainingRecordList = carBaseTrainingRecordService.getSearchTrainngRecordList(searchStr, pageBounds);
//				return pageBounds.putResultObj(CarBaseTrainingRecordList);
//			}
			carBaseTrafficRecordsDO.setDriverID(ID);
			List<CarBaseTrafficRecordsDO> carBaseTrafficRecordsDOList = carBaseTrafficRecordsService.getCarBaseTrafficAllRecordList(carBaseTrafficRecordsDO, pageBounds);
			return pageBounds.putResultObj(carBaseTrafficRecordsDOList);
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 编辑交通事故违规处理记录
	 * @param request	请求
	 * @param ID 选中的培训ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editTrafficRecord")
	public String  editTrafficRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,@RequestParam(value="driverID")Integer driverID){
		try {
			if(StringUtils.isBlank(ID+"")||ID==0){
				request.setAttribute("driverID", driverID);
				return "baseInfo/editTrafficRecord";
			}
			CarBaseTrafficRecordsDO carBaseTrafficRecordsDOById = carBaseTrafficRecordsService.getCarBaseTrafficRecordsByID(ID);
			String timeString=carBaseTrafficRecordsDOById.getTime();
			carBaseTrafficRecordsDOById.setTime(timeString.substring(0,19));
			request.setAttribute("recodeDo", carBaseTrafficRecordsDOById);
			request.setAttribute("driverID", driverID);
			return "baseInfo/editTrafficRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	/**
	 *交通事故违规处理记录数据
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/editTrafficInfo")
	@ResponseBody
	public Map<String,Object>  editTrafficInfo(HttpServletRequest request,CarBaseTrafficRecordsDO carBaseTrafficRecordsDO){
		ValidationUtils.validate(carBaseTrafficRecordsDO);
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		ValidateResult result = carBaseTrafficRecordsDO.getValidateResult();
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
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			carBaseTrafficRecordsDO.setCreator(currentUser.getUsername());			
			if(carBaseTrafficRecordsDO.getBrevityId()==null||carBaseTrafficRecordsDO.getBrevityId().equals("")){
				int driverId= carBaseTrafficRecordsService.editTrafficInfo(carBaseTrafficRecordsDO);
			        resultMap.put("result", driverId);
			        resultMap.put("addRecord", "1");
			        return resultMap;  
			}
			carBaseTrafficRecordsDO.setUpdateOperator(currentUser.getUsername());
			carBaseTrafficRecordsDO.setId(carBaseTrafficRecordsDO.getBrevityId());
		    int updateInfoById=carBaseTrafficRecordsService.updateCarBaseTrafficRecord(carBaseTrafficRecordsDO);
			 resultMap.put("result", updateInfoById);
			 resultMap.put("updateRecord", "1");
		      return resultMap;
		} catch (Exception e) {
			logger.error("录入培训数据出错{0}", e);
			resultMap.put("message", "录入培训数据出错");
			return resultMap;
		}	
	}
	
	/**
	 * 
	 * @Title: deleteDriverTrainningRecord 删除交通事故记录
	 * @Description: TODO 删除交通事故记录
	 * @param request  请求
	 * @param ids 选择的id
	 * @return: ReturnDataInfo 返回处理消息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 上午8:59:01
	 */
	@RequestMapping("/deleteTrafficRecord")
	@ResponseBody
	public ReturnDataInfo deleteTrafficRecord(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
		try {
			carBaseTrafficRecordsService.deleteTrafficRecord(ids);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除交通事故违规处理记录成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除交通事故违规处理记录失败！");
		}
	}
	
	
	/**
	 * 
	 * @Title: getListDept
	 * @Description: 新增部门下拉框
	 * @return
	 * @return: List<CarBaseStaffDO>
	 */
	@RequestMapping(value = "/getListDept", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListDeptByCompanyId(HttpServletRequest request) {
		try {
			// select2下拉框加载数据
			List<Select2VO> select2vos = new ArrayList<Select2VO>();
			// 根据公司id加载职工
			return carBaseDeptmentService.getListDeptInfo(select2vos);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有职员信息出错{0}", e);
			return null;
		}
	}
	
	
	/**
	 *唯一工号
	 * @param request	请求
	 * @param ID 选中的档案ID
	 * @return	result 若抛异常为空，否则跳转编辑页面
	 */
	@RequestMapping("/validateStaffNo")
	@ResponseBody
	public Map<String,Object>  validateStaffNo(HttpServletRequest request,@RequestParam(value="staffNo")String  staffNo){
		Map<String,Object> resultMap = new HashMap<String, Object>(); 
		if(staffNo.equals("")||staffNo==null){
			resultMap.put("resultStaff", 1);
			return resultMap;
		}
		CarBaseStaffDO carBaseStaffDO= carBaseDriverService.getCarBaseStaffNo(staffNo);
		if(carBaseStaffDO!=null){
			resultMap.put("result", false);
		}else{
			resultMap.put("result", true);
		}
		return resultMap;
		
	}
	
	/**
	 * 
	 * @Title: showCarDriverDetial 显示详情
	 * @Description: TODO 显示详情
	 * @param request 请求
	 * @param ID 详情
	 * @return: String 回显
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午2:51:33
	 */
	@RequestMapping("/showCarDriverDetial")
	public String  showCarDriverDetial(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
		try {
			CarBaseDriverRecordDo carBaseDriverRecordDoByID = carBaseDriverService.getCarDriverEditInfoByID(ID);
			request.setAttribute("recodeDo", carBaseDriverRecordDoByID);
			return "baseInfo/detialCarDriverRecord";
		} catch (Exception e) {
			logger.error("进入驾驶员档案记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * 
	 * @Title: showTrainingRecord 上岗转岗记录
	 * @Description: TODO	上岗转岗记录
	 * @param carBaseTrainingRecordDO 上岗转岗记录
	 * @return: String 上岗转岗记录
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午4:39:05
	 */
	@RequestMapping("/showTrainingRecord")
	public String  showTrainingRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,
			CarBaseTrainingRecordDO carBaseTrainingRecordDO,PageBounds pageBounds,String searchStr){
		try {
			request.setAttribute("driverID", ID);
			return "baseInfo/detialTrainingRecord";
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: showTrainingAfterRecord 在岗复训记录
	 * @Description: TODO 在岗复训记录
	 * @param request 在岗复训记录
	 * @param carBaseTrainingRecordDO 在岗复训记录
	 * @return: String 在岗复训记录
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午4:40:21
	 */
	@RequestMapping("/showTrainingAfterRecord")
	public String  showTrainingAfterRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,
			CarBaseTrainingRecordDO carBaseTrainingRecordDO,PageBounds pageBounds,String searchStr){
		try {
			request.setAttribute("driverID", ID);
			return "baseInfo/detialTrainingAfterRecord";
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: showTrafficRecord 交通事故违规记录详情
	 * @Description: TODO 交通事故违规记录详情
	 * @param carBaseTrainingRecordDO 交通事故违规记录详情
	 * @return: String 交通事故违规记录详情
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午4:47:26
	 */
	@RequestMapping("/showTrafficRecord")
	public String  showTrafficRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,
			CarBaseTrainingRecordDO carBaseTrainingRecordDO,PageBounds pageBounds,String searchStr){
		try {
			request.setAttribute("driverID", ID);
			return "baseInfo/detialTrafficRecord";
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @Title: showDeptChangeRecord 显示部门变更详情
	 * @Description: TODO 显示部门变更详情
	 * @param carBaseTrainingRecordDO 显示部门变更详情
	 * @return: String 显示部门变更详情
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午4:51:19
	 */
	@RequestMapping("/showDeptChangeRecord")
	public String  showDeptChangeRecord(HttpServletRequest request,@RequestParam(value="ID")Integer ID,
			CarBaseTrainingRecordDO carBaseTrainingRecordDO,PageBounds pageBounds,String searchStr){
		try {
			request.setAttribute("driverID", ID);
			return "baseInfo/detialDeptChangeRecord";
		} catch (Exception e) {
			logger.error("list培训记录出错{0}", e);
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
	@RequestMapping(value = "/isUseCarDriverRecords", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> isUseCarDriverRecords(HttpServletRequest request,
			@RequestParam("StatusId") Integer ID,
			@RequestParam("status") Integer status) {
		try {
			CarBaseDriverDO carBaseDriverDO=new CarBaseDriverDO();
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			carBaseDriverDO.setId(ID);
			carBaseDriverDO.setStatus(status);
			carBaseDriverDO.setUpdateOperator(currentUser.getUsername());
			carBaseDriverService.isUseCarDriverRecords(carBaseDriverDO);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("是否停用操作成功！");
		} catch (Exception e) {
			logger.error("驾驶员档案列表操作是否停用出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("是否停用操作失败！");
		}
	}
	
}
