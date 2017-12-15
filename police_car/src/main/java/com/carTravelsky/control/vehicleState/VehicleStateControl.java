package com.carTravelsky.control.vehicleState;

import java.util.List;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.PageBean;
import com.carTravelsky.bean.vehicle.VehicleRQ;
import com.carTravelsky.bean.vehicle.VehicleRS;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.control.system.CodeMasterControl;
import com.carTravelsky.control.system.DictionaryControl;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.service.vehicleState.VehicleStateService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.keycode.CodeMaster;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.web.context.SessionContext;

/**
 * @ClassName: VehicleStateControl
 * @Description: TODO
 * @author: lipengcheng
 * @date: 2017年10月12日 上午10:01:13
 */
@Controller
public class VehicleStateControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private VehicleStateService vehicleService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	
	/**
	 * 
	 * @Title: to_vehicleState
	 * @Description: 初始化车辆状态页面
	 * @param request
	 * @return
	 * @throws Exception
	 * @return: String
	 * @author: lipengcheng  
	 * @date: 2017年10月20日 下午1:44:34
	 */
	@RequestMapping("/vehicle")
	public String  to_vehicleState(HttpServletRequest request,KeyCodeMasterDO keyCodeMasterDO) throws Exception{
		VehicleRQ vehicleRQ = new VehicleRQ();
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			vehicleRQ.setId(currentUser.getCompanyId());
			Integer totalCount = vehicleService.getTotalCount(vehicleRQ);
			PageBean pageBean = new PageBean(YSTConstants.DEFAULT_CURRENT_PAGE, totalCount, YSTConstants.DEFAULT_PAGESIZE);
			vehicleRQ.setCurrentPage(pageBean.getCurrentPage());
			vehicleRQ.setPageSize(pageBean.getPageSize());
			List<VehicleRS> vehicleStateList = vehicleService.getVehicleStateList(vehicleRQ);
			pageBean.setBeginIndex(vehicleRQ.getBeginIndex());
			pageBean.setCurrentCount(vehicleStateList.size());
			
			List<KeyCodeMasterDO> carTypeList=keyCodeMasterService.getKeyCodeMasterList(keyCodeMasterDO,currentUser);
			
			request.setAttribute("vehicleRQ", vehicleRQ);
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("vehicleStateList", vehicleStateList);
			request.setAttribute("carTypeList", carTypeList);
			
			return "/vehicleState/vehicleState";
	} catch (Exception e) {
			logger.error("加载车辆状态错误{0}", e);
			return null;
		}
	}
	
	
	/**
	 * 
	 * @Title: to_vehicleStates
	 * @Description: 嵌套分页模糊查询
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: ReturnDataInfo<VehicleRS>
	 * @author: lipengcheng  
	 * @date: 2017年10月20日 下午1:43:22
	 */
	@RequestMapping("/selectorVehicles")
	@ResponseBody
	public ReturnDataInfo<VehicleRS> to_vehicleStates(HttpServletRequest request,HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		VehicleRQ vehicleRQ = new VehicleRQ();
		// 当前登录用户
		CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
		vehicleRQ.setId(currentUser.getCompanyId());
		String display = request.getParameter("state");
		String typeName = request.getParameter("type");
		String searchStr = request.getParameter("searchStr");
		Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
		Integer current_page = Integer.parseInt(request.getParameter("current_page"));
		if (!StringUtils.isBlank(display)) {
			vehicleRQ.setDisplay(display);
		}
		if (!StringUtils.isBlank(typeName)) {
			vehicleRQ.setTypeName(typeName);
		}
		if (!StringUtils.isBlank(searchStr)) {
			vehicleRQ.setSearchStr(searchStr);
		}
		Integer totalCount = vehicleService.getTotalCount(vehicleRQ);
		PageBean pageBean = new PageBean(current_page, totalCount, pageSize);
		vehicleRQ.setCurrentPage(pageBean.getCurrentPage());
		vehicleRQ.setPageSize(pageBean.getPageSize());
		List<VehicleRS> vehicleStateList = vehicleService.getVehicleStateList(vehicleRQ);
		pageBean.setBeginIndex(vehicleRQ.getBeginIndex());
		pageBean.setCurrentCount(vehicleStateList.size());
        return ReturnDataInfo.createSuccessfulExecuteResults(vehicleStateList,pageBean);
	}
	
}
