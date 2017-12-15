package com.carTravelsky.control.carDaily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.GatePositionDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.GatePositionService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@Controller
@RequestMapping("/gateposition")
public class GatePositionControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GatePositionService gatePositionService;
	
	/**
	 * @return 跳转到停机位信息展示界面
	 */
	@RequestMapping("/listGatePosition")
	public String  listGatePosition() {
		return "/dailyHandler/listGatePosition";
	}
	
	
	
	/**
	 * @Title: addAndEditGatePosition 修改界面
	 * @Description: TODO
	 * @param ID
	 * @param request
	 * @return
	 * @return: String
	 * @author: admin  
	 * @date: 2017年11月7日 上午10:13:13
	 */
	@RequestMapping("/editGatePosition")
	public String  editGatePosition(@RequestParam(value = "ID")Integer ID,HttpServletRequest request){
		try {
			GatePositionDO gatePosition = gatePositionService.getGatePositionByID(ID);
			String str = gatePosition.getCoord();
			String[] split = str.split(",");
			Matcher matcher = Pattern.compile("(\\d){1,3}").matcher(str);
			List<String> snew = new ArrayList<>();
			while(matcher.find()){
				snew.add(matcher.group());
			}
			request.setAttribute("zLimit", snew.get(0));
			request.setAttribute("zMinute", snew.get(1));
			request.setAttribute("zSecond", snew.get(2));
			request.setAttribute("latitude", split[0].substring(split[0].length()-1, split[0].length()));
			request.setAttribute("hLimit", snew.get(3));
			request.setAttribute("hMinute", snew.get(4));
			request.setAttribute("hSecond", snew.get(5));
			request.setAttribute("longitude", split[1].substring(split[1].length()-1, split[1].length()));
			request.setAttribute("gatePosition", gatePosition);
			return "/dailyHandler/editGatePosition";
		} catch (Exception e) {
			logger.error("进入修改油记录页面失败", e);
			return null;
		}
	}
	
	@RequestMapping("/addGatePosition")
	public String  addGatePosition(HttpServletRequest request){
		try {
			return "/dailyHandler/editGatePosition";
		} catch (Exception e) {
			logger.error("进入添加油记录页面失败", e);
			return null;
		}
	}
	
	/**
	 * @Title: saveGatePosition 保存停机位信息
	 * @Description: TODO
	 * @param gatepositiondo
	 * @param request
	 * @param zLimit 度
	 * @param zMinute 分
	 * @param zSecond 秒
	 * @param latitude 经度
	 * @param hLimit
	 * @param hMinute
	 * @param hSecond
	 * @param longitude 纬度
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:17:48
	 */
	@RequestMapping("/saveGatePosition")
	@ResponseBody
	public ReturnDataInfo saveGatePosition(GatePositionDO gatepositiondo,HttpServletRequest request,
			String zLimit,String zMinute,String zSecond,String latitude,
			String hLimit,String hMinute,String hSecond,String longitude) {
		try {
			ValidationUtils.validate(gatepositiondo);
			ValidateResult result = gatepositiondo.getValidateResult();
			boolean result2 = result.getResult();
			if (result2 == false) {
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage(result.getMessageList().toString());
			}
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if (gatepositiondo.getId() == null ) {
				gatepositiondo.setCompanyId(userDO.getCompanyId());
				gatepositiondo.setCreatePeople(userDO.getUsername());
				gatepositiondo.setCreateTime(new Date());
			}
			gatepositiondo.setUpdatePeople(userDO.getUsername());
			gatepositiondo.setUpdateTime(new Date());
			gatepositiondo.setCoord(zLimit+"°"+zMinute+"′"+zSecond+"″"+latitude+","+hLimit+"°"+hMinute+"′"+hSecond+"″"+longitude);
			gatePositionService.saveGatePosition(gatepositiondo);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存停机位信息成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("保存停机位信息失败！");
		}
	}
	
	
	
	
	/**
	 * @Title: deletGatePositions 删除停机位信息
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @return: ReturnDataInfo 
	 * @author: 黄进  
	 * @date: 2017年11月7日 上午10:18:50
	 */
	@ResponseBody
	@RequestMapping("/deletGatePositions")
	public ReturnDataInfo deletGatePositions(@RequestParam(value = "ids[]")String[] ids) {
		try {
			List<String> asList = Arrays.asList(ids);
			gatePositionService.updateDeleteCode(asList);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除停机位信息成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("删除停机位信息失败！");
		}
		
		
	}
	
	
	/**
	 * @Title: updateScanRadii 修改以停机位为中心司机可扫描的范围
	 * @Description: TODO
	 * @param scanRadiiNumber
	 * @param ids 
	 * @return
	 * @return: ReturnDataInfo
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:19:21
	 */
	@ResponseBody
	@RequestMapping("/updateScanRadii")
	public ReturnDataInfo updateScanRadii(String scanRadiiNumber,@RequestParam(value = "ids[]")String[] ids) {
		try {
			List<String> asList = Arrays.asList(ids);
			HashMap<Object,Object> hashMap = new HashMap<>();
			hashMap.put("scanRadiiNumber", scanRadiiNumber);
			hashMap.put("list", asList);
			gatePositionService.updateScanRadii(hashMap);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("修改可扫描距离范围成功！");
		} catch (Exception e) {
			return ReturnDataInfo.createFailedExecuteResult("修改可扫描距离范围失败！");
		}
		
	}
	
	
	/**
	 * @Title: getGatePositionList  获取停机位列表
	 * @param request
	 * @param domain
	 * @param pageBounds 分页列表
	 * @param searchStr 关键字搜索
	 * @return
	 * @return: Map<String,Object>
	 * @author: 黄进
	 * @date: 2017年11月7日 上午10:20:01
	 */
	@ResponseBody
	@RequestMapping("/getGatePositionList")
	public Map<String, Object> getGatePositionList(HttpServletRequest request,GatePositionDO domain,PageBounds pageBounds,String searchStr) {
		try {
			CarSysUserDO userDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			if(YSTConstants.IS_CURRENT_DEP_VIEW==userDO.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(userDO.getDeptID());
			}
			if (StringUtils.isNotBlank(searchStr)) {
				HashMap<Object, Object> hashMap = new HashMap<>();
				hashMap.put("searchStr", searchStr);
				hashMap.put("companyId", userDO.getCompanyId());
				List<GatePositionDO> list = gatePositionService.getSearchGatePositionList(hashMap, pageBounds);
				return pageBounds.putResultObj(list);
			}
			domain.setDeleteCode("1");
			domain.setCompanyId(userDO.getCompanyId());
			List<GatePositionDO> positionList = gatePositionService.getGatePositionList(domain, pageBounds);
			return pageBounds.putResultObj(positionList);
		} catch (Exception e) {
			logger.error("报错{0}", e);
			return null;
		}
		
	}

	/**
	 * @Title: carLocation 车辆位置查看
	 * @Description: TODO
	 * @return
	 * @return: String
	 * @author: 黄进
	 * @date: 2017年12月4日 下午1:25:04
	 */
	@RequestMapping("/carLocation")
	public String carLocation() {
		try {
			System.err.println("666");
			return "/dailyHandler/carLocation";
		} catch (Exception e) {
			logger.error("进入车辆位置查找失败!", e);
			return null;
		}
	}
}
