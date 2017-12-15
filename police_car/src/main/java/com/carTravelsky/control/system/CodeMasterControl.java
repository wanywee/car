package com.carTravelsky.control.system;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.CustomSysPram;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.web.context.SessionContext;

@Controller
@RequestMapping("/codeMaster")
public class CodeMasterControl {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private KeyCodeMasterService codeMasterService;
	
	
	/**
	 * @return 进入系统参数的设置
	 */
	@RequestMapping("/editSysPram")
	public String editSysPram(KeyCodeMasterDO keyCodeMasterDO,HttpServletRequest request) {
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO); 
			//String[] keyTypes = {"IDLECARTIME","WINDOW","EXPIRETIME","PREDICTTIME","REMINDTIME"}; 
			String[] keyTypes = {"EXPIRETIME","PREDICTTIME","REMINDTIME"}; 
			List<String> asList = Arrays.asList(keyTypes);
			List<KeyCodeMasterDO> codeMasterDOs = codeMasterService.getSysPramList(asList,userDo);
			CustomSysPram sysPram = new CustomSysPram();
			for (KeyCodeMasterDO list : codeMasterDOs) {
				/*if (list.getKeyType().equals("IDLECARTIME")) {
					sysPram.setIdleCarTime(list.getCode());
				}else */
				if(list.getKeyType().equals("EXPIRETIME")){
					sysPram.setExpireTime(list.getCode());
				}else if(list.getKeyType().equals("PREDICTTIME")){
					sysPram.setPredictTime(list.getCode());
				}else if(list.getKeyType().equals("REMINDTIME")){
					sysPram.setRemindTime(list.getCode());
				}
			}
			request.setAttribute("sysPram", sysPram);
			return "/system/editSysPram";
		} catch (Exception e) {
			logger.error("进入系统参数设置出错", e);
			return null;
		}
		
		
	}
	
	
	/**
	 * @param label 是否打印行车记录单
	 * @param window 启动时是否显示到期提示窗体
	 * @param expiryDate 到期提醒提示天数
	 * @return
	 */
	@RequestMapping("/updateSysPram")
	@ResponseBody
	public ReturnDataInfo updateSysPram(HttpServletRequest request,CustomSysPram sysPram) {
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			/*if (StringUtils.isBlank(sysPram.getWindow())) {
				sysPram.setWindow("2");
			}*/
			codeMasterService.updateSysPram(sysPram,userDo);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("保存失败！");
		}
	}
	

}
