package com.carTravelsky.control.carDaily;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairTackRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.control.BaseControl;
import com.carTravelsky.service.carDaily.CarDailyRepairTackRecordService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;
/**
 * 日常处理
 * @author admin
 *
 */
@RequestMapping("/dailyHandle")
@Controller
public class DailyHandleControl extends BaseControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyRepairTackRecordService repairRecordService;
	/**
	 * 进入维修取车记录
	 * @return
	 */
	@RequestMapping("/listDispatchRecord")
	public String  listDispatchRecord(){
		try {
			return "dailyHandler/listDispatchRecord";
		} catch (Exception e) {
			logger.error("进入进入维修取车记录出错{0}", e);
			return null;
		}	
	}
	
	
	/**
	 * @Title: addDispatchRecord
	 * @Description: 进入编辑页面
	 * @param request
	 * @param ID
	 * @return
	 * @return: String
	 * @author: zy  
	 * @date: 2017-11-2 下午4:05:05
	 */
	@RequestMapping("/addDispatchRecord")
	public String  addDispatchRecord(HttpServletRequest request,@RequestParam(value="ID",required=false)Integer ID){
		try {
			if(StringUtils.isBlank(ID+"")){
				return "dailyHandler/editDispatchRecord";
			}
			CarDailyRepairTackRecordDO carDailyRepairRecordByID = repairRecordService.getCarDailyRepairTackRecordByID(ID);
			request.setAttribute("recode", carDailyRepairRecordByID);
			return "dailyHandler/editDispatchRecord";
		} catch (Exception e) {
			logger.error("进入维修取车记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * 进入编辑取车记录
	 * @param request
	 * @param ID
	 * @return
	 */
	@RequestMapping("/editDispatchRecord")
	public String  editDispatchRecord(HttpServletRequest request,@RequestParam(value="ID",required=false)Integer ID){
		try {
			if(StringUtils.isBlank(ID+"")){
				return "dailyHandler/editDispatchRecord";
			}
			CarDailyRepairTackRecordDO carDailyRepairRecordByID = repairRecordService.getCarDailyRepairTackRecordByID(ID);
			request.setAttribute("recode", carDailyRepairRecordByID);
			return "dailyHandler/editDispatchRecord";
		} catch (Exception e) {
			logger.error("进入维修取车记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * list维修取车记录
	 * @param request
	 * @param ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getListDispatchRecord")
	public Map<String, Object>  getListDispatchRecord(HttpServletRequest request,
			CarDailyRepairTackRecordDO dailyRepairRecord,PageBounds pageBounds,String searchStr){
		CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
			pageBounds.setNeedCompany(true);
			pageBounds.setCompanyID(userDo.getDeptID());
		}
		dailyRepairRecord.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
		if(userDo!=null){
			dailyRepairRecord.setCompanyId(userDo.getCompanyId());
		}
		try {
				List<CarDailyRepairTackRecordDO> carDailyRepairRecordList = repairRecordService.getsearchCarDailyRepairTack(searchStr,dailyRepairRecord, pageBounds);
				return pageBounds.putResultObj(carDailyRepairRecordList);
		} catch (Exception e) {
			logger.error("list维修取车记录出错{0}", e);
			return null;
		}	
	}
	
	/**
	 * @Title: delDispatchRecord
	 * @Description: "删除取车记录"
	 * @param request
	 * @param ids
	 * @return
	 * @return: Map<String,Object>
	 * @author: zy  
	 * @date: 2017-10-17 上午9:33:54
	 */
	@ResponseBody
	@RequestMapping("/delDispatchRecord")
	public ReturnDataInfo<Object>  delDispatchRecord(HttpServletRequest request,@RequestParam(value="ids",required=true)String ids){
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			repairRecordService.delDispatchRecord(ids,userDo);
		  return	ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作成功");
		} catch (Exception e) {
			logger.error("list维修取车记录出错{0}", e);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作失败");
		}	
	}
	
	
	/**
	 * @Title: saveDispatchRecord
	 * @Description: 保存取车记录
	 * @return: void
	 * @author: zy  
	 * @date: 2017-10-18 下午3:23:21
	 */
	@ResponseBody
	@RequestMapping("/saveDispatchRecord")
	public ReturnDataInfo<Object> saveDispatchRecord(HttpServletRequest request,CarDailyRepairTackRecordDO carDailyRepairTackRecordDO)
	{
		ValidationUtils.validate(carDailyRepairTackRecordDO);
		ValidateResult result = carDailyRepairTackRecordDO.getValidateResult();
		boolean flag = result.getResult();
		if(!flag){
			return ReturnDataInfo.createFailedExecuteResult(result.getMessageLine());
		}
		CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			repairRecordService.saveCarDailyRepairTackRecord(carDailyRepairTackRecordDO,userDo);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作成功");
		} catch (ServiceException e) {
			logger.error("更新取车记录出错{0}", e);
			e.printStackTrace();
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作失败");
		}
		
	}
}
