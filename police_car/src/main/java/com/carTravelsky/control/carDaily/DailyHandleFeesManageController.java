package com.carTravelsky.control.carDaily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarDailyFeeManageRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyFeeManageRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@RequestMapping("/dailyHandle")
@Controller
public class DailyHandleFeesManageController {
		protected final Logger logger = LoggerFactory.getLogger(getClass());
		@Autowired
		private CarDailyFeeManageRecordService carDailyFeeManageRecordService;
		@Autowired
		private KeyCodeMasterService keyCodeMasterService;
		/**
		 * 进入规费管理
		 * @return 
		 */
		@RequestMapping("/listFeesManage")
		public String  listCarDriverRecord(){
			try {
				return "dailyHandler/listFeesManagement";
			} catch (Exception e) {
				logger.error(" 进入规费管理出错{0}", e);
				return null;
			}	
		}
		
		/**
		 * 
		 * @Title: getListDispatchRecord 规费管理显示
		 * @Description: TODO 规费管理显示
		 * @param carDailyFeeManageRecordDO 规费管理
		 * @param pageBounds 分页
		 * @param searchStr 搜索条件
		 * @return: Map<String,Object> 返回结果
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年10月30日 下午2:37:12
		 */
		@ResponseBody
		@RequestMapping("/getListFeesMangement")
		public Map<String, Object>  getListFeesMangement(HttpServletRequest request,
				CarDailyFeeManageRecordDO carDailyFeeManageRecordDO,PageBounds pageBounds,String searchStr,String count){
			try {
				CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
				KeyCodeMasterDO  kcDo = keyCodeMasterService.getKeyCodeMasterByID(19);
			     count =kcDo.getCode();
				if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
					pageBounds.setNeedCompany(true);
					pageBounds.setCompanyID(userDo.getDeptID());
				}
				if(userDo!=null){
					carDailyFeeManageRecordDO.setCompanyID(userDo.getCompanyId());
				}
				if(!StringUtils.isBlank(searchStr)){
					List<CarDailyFeeManageRecordDO> carDailyFeeManageRecordDOList = carDailyFeeManageRecordService.getSearchFeeManageAllList(searchStr,carDailyFeeManageRecordDO,pageBounds);
					if(!StringUtils.isBlank(count)){//查询到期的天数，添加到期提醒
						List<CarDailyFeeManageRecordDO> carDailyFeeManageRecordDOListExpire = carDailyFeeManageRecordService.getSearchFeeManageAllListExpire(searchStr,carDailyFeeManageRecordDO,pageBounds,new Integer(count).intValue());
						for (CarDailyFeeManageRecordDO carDailyFeeManageRecordDO2 : carDailyFeeManageRecordDOList) {
							for (CarDailyFeeManageRecordDO carDailyFeeManageRecordDO21 : carDailyFeeManageRecordDOListExpire) {
								if(carDailyFeeManageRecordDO21.getId() == carDailyFeeManageRecordDO2.getId()){
									carDailyFeeManageRecordDO2.setStatus(1);
								}
								
							}
						}
					}
					return pageBounds.putResultObj(carDailyFeeManageRecordDOList);
				}
				List<CarDailyFeeManageRecordDO> carDailyFeeManageRecordDOList = carDailyFeeManageRecordService.getCarDailyFeeManageAllList(carDailyFeeManageRecordDO, pageBounds);
				if(!StringUtils.isBlank(count)){//查询到期提醒的天数
					List<CarDailyFeeManageRecordDO> carDailyFeeManageExpireDOList = carDailyFeeManageRecordService.expireRecordList(carDailyFeeManageRecordDO, pageBounds, new Integer(count).intValue(),userDo.getCompanyId());
					for (CarDailyFeeManageRecordDO carDailyFeeManageRecordDO21 : carDailyFeeManageRecordDOList) {
						for (CarDailyFeeManageRecordDO carDailyFeeManageRecordDO2 : carDailyFeeManageExpireDOList) {
							if(carDailyFeeManageRecordDO2.getId()==carDailyFeeManageRecordDO21.getId()){
								carDailyFeeManageRecordDO21.setStatus(1);
							}
						}
					}
				}
				return pageBounds.putResultObj(carDailyFeeManageRecordDOList);
			} catch (Exception e) {
				logger.error("list规费管理出错{0}", e);
				return null;
			}	
		}
		
		
		/**
		 * 编辑规费管理
		 * @param request	请求
		 * @param ID 选中的规费管理ID
		 * @return	result 若抛异常为空，否则跳转编辑页面
		 */
		@RequestMapping("/editFeesManage")
		public String  editFeesManage(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
			try {
				if(StringUtils.isBlank(ID+"")||ID==0){
					return "dailyHandler/editFeesManage";
				}
				CarDailyFeeManageRecordDO carDailyFeeManageRecordDO = carDailyFeeManageRecordService.getFeesManageInfoByID(ID);
				request.setAttribute("recodeDo", carDailyFeeManageRecordDO);
				return "dailyHandler/editFeesManage";
			} catch (Exception e) {
				logger.error("进入规费管理出错{0}", e);
				return null;
			}	
		}
		
		
		/**
		 * 
		 * @Title: addFeesManage 新增规费管理跳转
		 * @Description: TODO 新增规费管理跳转
		 * @param ID 新增规费管理
		 * @return: String 返回结果
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年11月1日 下午1:01:40
		 */
		@RequestMapping("/addFeesManage")
		public String  addFeesManage(HttpServletRequest request){
			try {
					return "dailyHandler/editFeesManage";
			} catch (Exception e) {
				logger.error("进入规费管理出错{0}", e);
				return null;
			}	
		}
		
		/**
		 *新增/修改驾驶员数据
		 * @param request	请求
		 * @param ID 选中的档案ID
		 * @return	result 若抛异常为空，否则跳转编辑页面
		 */
		@RequestMapping("/editFeesManageInfo")
		@ResponseBody
		public Map<String,Object>  editFeesManageInfo(HttpServletRequest request,CarDailyFeeManageRecordDO carDailyFeeManageRecordDO){
			ValidationUtils.validate(carDailyFeeManageRecordDO);
			Map<String,Object> resultMap = new HashMap<String, Object>(); 
			ValidateResult result = carDailyFeeManageRecordDO.getValidateResult();
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
				if(carDailyFeeManageRecordDO.getBrevityId()==null||carDailyFeeManageRecordDO.getBrevityId().equals("")){
					carDailyFeeManageRecordDO.setCreatePeople(currentUser.getUsername());
					carDailyFeeManageRecordService.addFeesManage(carDailyFeeManageRecordDO);
				        resultMap.put("addFees", 1);
				        return resultMap;  
				}
				carDailyFeeManageRecordDO.setId(carDailyFeeManageRecordDO.getBrevityId());
				carDailyFeeManageRecordDO.setUpdatePeople(currentUser.getUsername());
				carDailyFeeManageRecordService.updateFeesManageInfo(carDailyFeeManageRecordDO);
				 resultMap.put("updateFees", "1");
			      return resultMap;
			} catch (Exception e) {
				logger.error("录入驾驶员数据出错{0}", e);
				return null;
			}
		}
		
		

		
		/**
		 * 
		 * @Title: deleteCarDriverInfoById  删除规费管理记录
		 * @Description: TODO 删除规费管理通过选中行
		 * @return: result 删除成功 1存在,2删除
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年10月16日 下午3:58:23
		 */
		@RequestMapping("/deleteFeesManage")
		@ResponseBody
		public ReturnDataInfo deleteFeesManage(HttpServletRequest request,@RequestParam(value="ids[]")String[] ids){
			try {
				carDailyFeeManageRecordService.deleteFeesManage(ids);
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除驾规费管理记录成功！");
			} catch (Exception e) {
				return ReturnDataInfo.createFailedExecuteResult("删除规费管理记录失败！");
			}
			
		}
		
		/**
		 * 
		 * @Title: getListLicenseNo 获取下拉框车牌号
		 * @Description: TODO 获取下拉框车牌号
		 * @return: List<Select2VO> 返回结果
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年10月31日 上午11:16:38
		 */
		@RequestMapping(value = "/getListLicenseNo", method = RequestMethod.POST)
		@ResponseBody
		public List<Select2VO> getListLicenseNo(HttpServletRequest request) {
			try {
				// select2下拉框加载数据
				List<Select2VO> select2vos = new ArrayList<Select2VO>();
				// 根据公司id加载职工
				return carDailyFeeManageRecordService.getListLicenseNo(select2vos);
			} catch (Exception e) {
				logger.error("获取所有车牌号出错{0}", e);
				return null;
			}
		}
		/**
		 * 
		 * @Title: getListFeeUnits 获取下拉往来单位
		 * @Description: TODO 获取下拉往来单位
		 * @return: List<Select2VO> 获取下拉往来单位
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年11月1日 上午11:06:55
		 */
		@RequestMapping(value = "/getListFeeUnits", method = RequestMethod.POST)
		@ResponseBody
		public List<Select2VO> getListFeeUnits(HttpServletRequest request) {
			try {
				// select2下拉框加载数据
				List<Select2VO> select2vos = new ArrayList<Select2VO>();
				// 根据公司id加载职工
				return carDailyFeeManageRecordService.getListFeeUnits(select2vos);
			} catch (Exception e) {
				logger.error("获取所有往来单位出错{0}", e);
				return null;
			}
		}
		/**
		 * 
		 * @Title: showFeesManageDetial 规费管理详情显示
		 * @Description: TODO 规费管理详情显示
		 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
		 * @date: 2017年11月1日 上午11:07:36
		 */
		@RequestMapping("/showFeesManageDetial")
		public String  showFeesManageDetial(HttpServletRequest request,@RequestParam(value="ID")Integer ID){
			try {
				CarDailyFeeManageRecordDO carDailyFeeManageRecordDO = carDailyFeeManageRecordService.getFeesManageInfoByID(ID);
				request.setAttribute("recodeDo", carDailyFeeManageRecordDO);
				return "dailyHandler/detialFeesManage";
			} catch (Exception e) {
				logger.error("进入驾驶员档案记录出错{0}", e);
				return null;
			}	
		}
		
}
