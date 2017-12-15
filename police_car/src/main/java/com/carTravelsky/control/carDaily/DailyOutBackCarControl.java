package com.carTravelsky.control.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairTackRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.control.BaseControl;
import com.carTravelsky.service.carDaily.CarDailyOutRecordService;
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
@RequestMapping("/dailyOutBackCar")
@Controller
public class DailyOutBackCarControl extends BaseControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyOutRecordService carDailyOutRecordService;
	/**
	 * 
	 * @Title: listCarBack
	 * @Description: 回车记录
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月30日 下午2:18:04
	 */
	@RequestMapping("/listCarBack")
	public String  listCarBack(){
		try {
			return "dailyHandler/listCarBack";
		} catch (Exception e) {
			logger.error("进入回车记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * 
	 * @Title: listCarOut
	 * @Description: 跳转出车记录
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午3:07:20
	 */
	@RequestMapping("/listCarOut")
	public String  listCarOut(){
		try {
			return "dailyHandler/listCarOut";
		} catch (Exception e) {
			logger.error("进入出车记录出错{0}", e);
			return null;
		}	
	}
	/**
	 * 
	 * @Title: edidCarOutOrBack  回车，出车记录
	 * @Description: 编辑 跳转  根据id
	 * @param id
	 * @param request
	 * @param isOutAndBack
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午6:05:48
	 */
	@RequestMapping("/edidCarOut")
	public String  edidCarOut(Integer id,HttpServletRequest request){
		try {
			CarDailyOutRecordDO carDailyOutRecordDO=carDailyOutRecordService.getCarDailyOutRecordByID(id);
			if(carDailyOutRecordDO==null){
					return "dailyHandler/editCarOut";
			}
			request.setAttribute("carOutAndBackDO",carDailyOutRecordDO);
			return "dailyHandler/editCarOut";
		} catch (Exception e) {
			logger.error("跳转出车记录-编辑 {0}", e);
			return "dailyHandler/editCarOut";
		}	
	}
	/**
	 * 
	 * @Title: addCarOut
	 * @Description: 跳转出车记录 新增
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午6:24:15
	 */
	@RequestMapping("/addCarOut")
	public String  addCarOut(HttpServletRequest request){
		try {
			return "dailyHandler/editCarOut";
		} catch (Exception e) {
			logger.error("跳转出车记录新增 {0}", e);
			return "dailyHandler/editCarOut";
		}	
	}
	/**
	 * 
	 * @Title: edidCarBack
	 * @Description: 跳转回车记录 编辑
	 * @param id
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午6:18:49
	 */
	@RequestMapping("/edidCarBack")
	public String  edidCarBack(Integer id,HttpServletRequest request){
		try {
			CarDailyOutRecordDO carDailyOutRecordDO=carDailyOutRecordService.getCarDailyOutRecordByID(id);
			if(carDailyOutRecordDO==null){
					return "dailyHandler/editCarBack";
			}
			request.setAttribute("carBackDO",carDailyOutRecordDO);
			return "dailyHandler/editCarBack";
		} catch (Exception e) {
			logger.error("跳转回车记录 编辑{0}", e);
			return "dailyHandler/editCarBack";
		}	
	}
	/**
	 * 
	 * @Title: addCarBack
	 * @Description: 跳转回车记录 新增
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午6:22:32
	 */
	@RequestMapping("/addCarBack")
	public String  addCarBack(HttpServletRequest request){
		try {
			return "dailyHandler/editCarBack";
		} catch (Exception e) {
			logger.error("跳转回车记录 -新增 {0}", e);
			return "dailyHandler/editCarBack";
		}	
	}
	/**
	 * 
	 * @Title: editDisplayCarOutAndBack
	 * @Description: 出车 回车记录——查看详情，， 根据id 
	 * @param id
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午3:17:52
	 */
	@RequestMapping("/detialDisplayCarOutAndBack")
	public String  detialDisplayCarOutAndBack(Integer id,HttpServletRequest request,String isOutAndBack){
		try {
			CarDailyOutRecordDO carDailyOutRecordDO=carDailyOutRecordService.getCarDailyOutRecordByID(id);
			request.setAttribute("carOutAndBackDO",carDailyOutRecordDO);
			//isOutAndBack!= null 回车记录
			if(!StringUtils.isBlank(isOutAndBack)){
				return "dailyHandler/detialDisplayCarBack";
			}//出车记录
			return "dailyHandler/detialDisplayCarOut";
		} catch (Exception e) {
			logger.error("编辑回车记录 {0}", e);
			return null;
		}	
	}
	/**
	 * 
	 * @Title: getListCarBack
	 * @Description: 所有回车记录集合
	 * @param request
	 * @param carDailyBackRecordDO
	 * @param pageBounds
	 * @param searchStr
	 * isOutAndBack  判断是  回车 还是出车 
	 * @return
	 * @return: Map<String,Object>
	 * @author: fuxinrong
	 * @date: 2017年10月30日 下午3:18:51
	 */
	@ResponseBody
	@RequestMapping("/getListCarOutOrBack")
	public Map<String, Object> getListCarBack(HttpServletRequest request,CarDailyOutRecordDO carDailyOutRecordDO
			,PageBounds pageBounds,String searchStr){
		CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
			pageBounds.setNeedCompany(true);
			pageBounds.setCompanyID(userDo.getDeptID());
		}
		carDailyOutRecordDO.setCompanyID(userDo.getCompanyId());
		try {
			Map<String, Object> map =new  HashMap<String, Object>();
			//全局搜索
				if(!StringUtils.isBlank(searchStr)){
					map.put("searchStr", searchStr.trim());
					map.put("carDailyOutRecordDO", carDailyOutRecordDO);
					return pageBounds.putResultObj(
							carDailyOutRecordService.getSearchCarOutList(map, pageBounds));
				}
			
			return pageBounds.putResultObj(carDailyOutRecordService.getCarDailyOutRecordList(
					carDailyOutRecordDO, pageBounds));
		
		} catch (Exception e) {
			logger.error("list回车记录出错{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: deleteCarBack
	 * @Description: 删除 回车记录
	 * @param request
	 * @param carID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年11月1日 下午4:11:50
	 */
	@RequestMapping("/deleteCarBack")
	@ResponseBody
	public ReturnDataInfo<Object> deleteCarBack(HttpServletRequest request, String ids,String isOutAndBack) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			//获取多个要删除的id
			String[] splitIds = ids.split(",");
			//判断 是出车记录 删除还是回车记录删除   isOutAndBack ==null 出车
			if(StringUtils.isBlank(isOutAndBack)){
				List<String> NoDeleteCar =new ArrayList<String>();
				//出车记录删除
				NoDeleteCar=carDailyOutRecordService.deleteCarOutById(splitIds, NoDeleteCar, carSysUser.getId());
				if(NoDeleteCar.size()>0){
					return ReturnDataInfo.createFailedExecuteResult("不能删除"+NoDeleteCar);
				}
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
			}
			//出车删除 直接删除
			carDailyOutRecordService.deleteCarBacksById(splitIds, carSysUser.getId());
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除回车or出车记录记录 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}
	/**
	 * 
	 * @Title: saveCarOutAndBack
	 * @Description: 保存回车记录和出车记录
	 * @param request
	 * @param carDailyOutRecordDO
	 * isOutAndBack  判断是出车保存还是回车  为空为出车  不为空为回车
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年11月2日 上午10:31:39
	 */
	@RequestMapping("/saveCarOutAndBack")
	@ResponseBody
	public ReturnDataInfo<Object> saveCarOutAndBack(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO,String isOutAndBack) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			//校验
			carDailyOutRecordDO.setCompanyID(carSysUser.getCompanyId());
			if(carDailyOutRecordDO.getDestArea().equals("1")){
				carDailyOutRecordDO.setDestArea("省内");
			}else if(carDailyOutRecordDO.getDestArea().equals("2")){
				carDailyOutRecordDO.setDestArea("省外");
			}
			ValidationUtils.validate(carDailyOutRecordDO);
			ValidateResult result = carDailyOutRecordDO.getValidateResult();
			if (!result.getResult()) {
				return ReturnDataInfo.createFailedExecuteResult("保存失败"+result.getMessageLine());
			}
			//判断id是否为null  新增
			if(carDailyOutRecordDO.getId()==null){
				carDailyOutRecordDO.setCreatePeople(carSysUser.getId()+"");
				carDailyOutRecordDO.setCreateTime(new Date());
			}//编辑
			carDailyOutRecordDO.setUpdatePeople(carSysUser.getId()+"");
			carDailyOutRecordDO.setUpdateTime(new Date());
			//保存
			carDailyOutRecordService.saveCarDailyOutRecord(carDailyOutRecordDO,isOutAndBack);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存出车记录 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存失败");
		}
	}
	/**
	 * 
	 * @Title: getTrip
	 * @Description: 获取本次行程
	 * @param request
	 * @param getTrip
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月31日 下午1:32:29
	 */
	@RequestMapping("/getTrip")
	@ResponseBody 
	public ReturnDataInfo<CarDailyOutRecordDO> getTrip(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO) {
		try {
			//根据车id 查询 出车记录 里程
			CarDailyOutRecordDO outDO = carDailyOutRecordService.getOutByCarID(carDailyOutRecordDO.getCarID());
			//本次行程计算  本次行程= 出车行程 - 回车行程			
 			outDO.setTrip(carDailyOutRecordDO.getBackcarMileage() - outDO.getOutcarMileage() );
			return ReturnDataInfo.createSuccessfulSingleExecuteResult(outDO);
		} catch (Exception e) {
			logger.error("保存回车记录 {0}", e);
			return null;
		}
		
	}
	
	@RequestMapping("/getOutcarMileage")
	@ResponseBody 
	public ReturnDataInfo<CarDailyOutRecordDO> getOutcarMileage(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO) {
		try {
			//根据车id 查询 出车记录 里程
			CarDailyOutRecordDO outDO = carDailyOutRecordService.getBackByCarID(carDailyOutRecordDO.getCarID());
			//本次行程计算  本次行程= 出车行程 - 回车行程			
 			outDO.setTrip(carDailyOutRecordDO.getBackcarMileage());
			return ReturnDataInfo.createSuccessfulSingleExecuteResult(outDO);
		} catch (Exception e) {
			logger.error("保存回车记录 {0}", e);
			return null;
		}
	}
}
