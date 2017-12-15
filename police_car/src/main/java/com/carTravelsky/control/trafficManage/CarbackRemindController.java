package com.carTravelsky.control.trafficManage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.control.BaseControl;
import com.carTravelsky.service.trafficManage.CarDailyBackRecordService;
import com.carTravelsky.utils.ReturnDataInfo;
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
@Controller
@RequestMapping("/backCarremind")

public class CarbackRemindController extends BaseControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyBackRecordService carDailyBackRecordService;
	/**
	 * 
	 * @Title: listCarBack
	 * @Description: 回车记录
	 * @return
	 * @return: String
	 * @author: bxl
	 * @date: 2017年10月30日 下午2:18:04
	 */
	@RequestMapping("/listCarBack")
	public String  listCarBack(){
		try {
			return "trafficManage/listCarBackRemind";
		} catch (Exception e) {
			logger.error("进入回车记录出错{0}", e);
			return null;
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
	 * @author: bxl
	 * @date: 2017年11月1日 下午3:17:52
	 */
	@RequestMapping("/getListCarBackById")
	public String  detialDisplayCarOutAndBack(Integer id,HttpServletRequest request,String isOutAndBack){
		try {
			CarDailyOutRecordDO carDailyOutRecordDO=carDailyBackRecordService.getCarDailyOutRecordByID(id);
			request.setAttribute("carOutAndBackDO",carDailyOutRecordDO);
			//isOutAndBack!= null 回车记录
			
				return "dailyHandler/detialDisplayCarBack";
			
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
	 * @author: bxl
	 * @date: 2017年10月30日 下午3:18:51
	 */
	@ResponseBody
	@RequestMapping("/getListCarBack")
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
			// isOutAndBack 为null出车  
			
				//回车记录————全局搜索
				if(!StringUtils.isBlank(searchStr)){
					map.put("searchStr", searchStr.trim());
					map.put("carDailyOutRecordDO", carDailyOutRecordDO);
					return pageBounds.putResultObj(
							carDailyBackRecordService.getSearchCarBackList(map, pageBounds));
				}
			
			List<CarDailyOutRecordDO> lists = carDailyBackRecordService.getCarDailyOutRecordList(
					carDailyOutRecordDO, pageBounds);
			return pageBounds.putResultObj(lists);
		
		} catch (Exception e) {
			logger.error("list回车记录出错{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: addCarBackTime
	 * @Description: TODO回车续时
	 * @return
	 * @return: String
	 * @author: bxl  
	 * @date: 2017年12月4日 下午4:46:28
	 */	
	@RequestMapping("/addCarBackTime")
	public String addCarBackTime(HttpServletRequest request,int id){
		System.out.println("============="+id);	
		CarDailyOutRecordDO carDailyOutRecordDO=carDailyBackRecordService.getCarDailyOutRecordByID(id);		
		request.setAttribute("carBackDO",carDailyOutRecordDO);
		try {
			return "trafficManage/addCarBackTime";
		} catch (Exception e) {
			logger.error("进入回车续时出错{0}", e);
			return null;
		}	
		
		
		
		
	}
	/**
	 * 
	 * @Title: editCarBack
	 * @Description: TODO编辑已回车
	 * @return
	 * @return: String
	 * @author: bxl  
	 * @date: 2017年12月5日 下午12:18:13
	 */
	@RequestMapping("/editCarBack")
	public String editCarBack(HttpServletRequest request,int id){
		System.out.println("============="+id);	
		CarDailyOutRecordDO carDailyOutRecordDO=carDailyBackRecordService.getCarDailyOutRecordByID(id);		
		request.setAttribute("carBackDO",carDailyOutRecordDO);
		try {
			return "trafficManage/editCarBack";
		} catch (Exception e) {
			logger.error("进入回车续时出错{0}", e);
			return null;
		}					
		
	}
	/**
	 * 
	 * @Title: saveCarOutAndBack
	 * @Description: TODO保存已回车记录
	 * @param request
	 * @param carDailyOutRecordDO
	 * @param isOutAndBack
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: liqinqin  
	 * @date: 2017年12月5日 下午3:10:25
	 */
	@RequestMapping("/saveCarBack")
	@ResponseBody
	public ReturnDataInfo<Object> saveCarOutAndBack(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			//校验
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
			carDailyBackRecordService.saveCarDailyOutRecord(carDailyOutRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存出车记录 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存失败");
		}
	}
	/**
	 * 
	 * @Title: addTimeFor
	 * @Description: TODO续时
	 * @param request
	 * @param carDailyOutRecordDO
	 * @param day
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: bxl  
	 * @date: 2017年12月5日 下午4:49:33
	 */
	@RequestMapping("/addTimeFor")
	@ResponseBody
	public ReturnDataInfo<Object> addTimeFor(HttpServletRequest request, CarDailyOutRecordDO carDailyOutRecordDO,int day){
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		
		try {
			//校验
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
			carDailyBackRecordService.saveCaraddTime(carDailyOutRecordDO,day);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存出车记录 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存失败");
		}
		
	}

}
