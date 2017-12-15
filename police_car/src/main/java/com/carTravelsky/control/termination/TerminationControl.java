package com.carTravelsky.control.termination;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.bean.carDaily.CarRemindFormDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarDailyMaintainRecordMapper;
import com.carTravelsky.service.carDaily.CarDailyFeeManageRecordService;
import com.carTravelsky.service.carDaily.CarDailyInsuranceRecordService;
import com.carTravelsky.service.carDaily.CarDailyMaintainRecordService;
import com.carTravelsky.service.carDaily.CarDailyYearIptRecordService;
import com.carTravelsky.service.carDaily.CarRemindFormService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.StringUtils;
import com.stopec.web.context.SessionContext;
import com.stopec.web.user.object.UserInfo;

/**
 * @ClassName: TerminationControl
 * @Description: 到期提醒
 * @author: wangyu
 * @date: 2017年10月17日 下午2:00:58
 */
@RequestMapping("/termination")
@Controller
public class TerminationControl {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarRemindFormService remindFormService;
	@Autowired
	private CarDailyInsuranceRecordService insuranceRecordService;
	@Autowired
	private CarDailyYearIptRecordService yearIptRecordService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	@Autowired
	private CarDailyFeeManageRecordService carDailyFeeManageRecordService;
	@Autowired
	private CarDailyMaintainRecordService carDailyMaintainRecordService;
	@RequestMapping("/listTerminationInfo")
	public String listTerminationInfo() {

		return "termination/listTerminationInfo";

	}

	/**
	 * @Title: getRemindDate
	 * @Description: 得到默认的到期提醒天数
	 * @param keyID
	 * @return
	 * @return: KeyCodeMasterDO
	 * @author: wangyu
	 * @date: 2017年10月19日 下午4:15:55
	 */
	@RequestMapping("/getRemindDate")
	@ResponseBody
	public KeyCodeMasterDO getRemindDate(HttpServletRequest request,
			@RequestParam(value = "keyID") Integer keyID) {

		KeyCodeMasterDO keyCodeMasterDO = keyCodeMasterService
				.getKeyCodeMasterByID(keyID);
		request.setAttribute("kcm", keyCodeMasterDO);
		return keyCodeMasterDO;
	}

	
	/**
	 * @Title: dealRemindDate
	 * @Description: 到期提醒处理
	 * @param number
	 * @return
	 * @return: String
	 * @author: wangyu  
	 * @date: 2017年11月3日 上午11:27:58
	 */
	@RequestMapping("/dealRemindDate")
	public  String dealRemindDate (HttpServletRequest request,@RequestParam(value="remindDate")  Integer number,Integer dealid){
		
		request.setAttribute("remindDate", number);
		if(dealid==1){//保险到期提醒
			return "/dailyHandler/listInsurance";
		}
		else if(dealid==2){//年检到期提醒
			return "/baseInfo/listCarDailyYearIPTRecord";
		}else if(dealid==3){//规费管理到期提醒
			return "/dailyHandler/listFeesManagement";
		}else if(dealid==4){//保养到期提醒
			return "/dailyHandler/listCarDailyMaintain";
		}
		return "baseInfo/listCarDailyYearIPTRecord";
	}
	
	/**
	 * @Title: getListRemindForm
	 * @Description: 得到到期提醒的种类和数量
	 * @param request
	 * @param carRemindFormDO
	 * @param remindDate
	 * @return
	 * @return: List<CarRemindFormDO>
	 * @author: wangyu  
	 * @date: 2017年11月3日 上午11:22:51
	 */
	@RequestMapping(value="/getListRemindForm" ,method=RequestMethod.POST)
	@ResponseBody
	public List<CarRemindFormDO> getListRemindForm(HttpServletRequest request,
			CarRemindFormDO carRemindFormDO,HttpSession session,
			@RequestParam(value = "remindDate") String remindDate) {
		CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			if (StringUtils.contains(remindDate, ",")) {
				remindDate = remindDate.substring(2);
			}
			KeyCodeMasterDO  kcDo = keyCodeMasterService.getKeyCodeMasterByID(19);
			if(!kcDo.getCode().equals(remindDate)){
				kcDo.setCode(remindDate);
				CarSysUserDO sysUserDO = (CarSysUserDO) session
						.getAttribute("carSysUserDOLogin");
				keyCodeMasterService.saveKeyCodeMaster(kcDo, sysUserDO);//修改默认天数值
			}
			int insuranceCount = insuranceRecordService.countExpire(Integer
					.valueOf(remindDate));// 得到保险到期数量
			int yearIptCount = yearIptRecordService.countExpire(Integer
					.valueOf(remindDate));// 得到年检到期数量
			int feemanagerCount = carDailyFeeManageRecordService.countExpire(Integer
					.valueOf(remindDate),userDo.getCompanyId());//得到规费到期数量
			int maintaincount = carDailyMaintainRecordService.ExpireCount(userDo.getCompanyId(),new Integer(remindDate).intValue());
			List<CarRemindFormDO> carRemindFormDOList = remindFormService
					.getCarRemindFormList(carRemindFormDO);//得到所有的过期类型
			for (CarRemindFormDO carRemindFormDO2 : carRemindFormDOList) {
				if (carRemindFormDO2.getType().trim().equals("保险到期")) {
					carRemindFormDO2.setNumbers(insuranceCount);
				}
				if (carRemindFormDO2.getType().trim().equals("年检到期")) {
					carRemindFormDO2.setNumbers(yearIptCount);
				}
				if (carRemindFormDO2.getType().trim().equals("规费到期")) {
					carRemindFormDO2.setNumbers(feemanagerCount);
				}
				if(carRemindFormDO2.getType().trim().equals("保养到期")){
					carRemindFormDO2.setNumbers(maintaincount);
				}
			}
			return carRemindFormDOList;
		} catch (NumberFormatException e) {
			logger.error("进入到期提醒出错{0}", e);
			return null;
		}
	}
	
	

}