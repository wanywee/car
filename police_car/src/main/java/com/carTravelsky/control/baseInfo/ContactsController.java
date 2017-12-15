package com.carTravelsky.control.baseInfo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.carTravelsky.bean.carDaily.CarBaseContactsDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseContactsService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.web.context.SessionContext;

/**
 * 
 * @ClassName: ContactsController
 * @Description: TODO往来单位信息
 * @author: rls
 * @date: 2017年11月29日 上午10:35:40
 */
@Controller
@RequestMapping("/contactInfo")
public class ContactsController {
	protected final Logger logger = LoggerFactory.getLogger(ContactsController.class);
	@Autowired
	private CarBaseContactsService carBaseContactsService;
	/**
	 * 
	 * @Title: getcontactsList
	 * @Description: TODO返回往来单位列表页面
	 * @return
	 * @return: String
	 * @author: rls
	 * @date: 2017年11月29日 上午10:37:19
	 */
	@RequestMapping("/listContactInfo")
	public String getcontactsList() {
		try {
			return "baseInfo/listContactInfo";
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("进入往来单位出错", e);
			return null;
		}
		
		
	}
	@RequestMapping("/getListContacts")
	@ResponseBody
	public Map<String,Object> getListForContact(HttpServletRequest request,CarBaseContactsDO carBaseContactsDo,PageBounds pageBounds,String searchStr){
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			/*if(YSTConstants.IS_CURRENT_DEP_VIEW==userDo.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(userDo.getDeptID());
			}*/
			if(userDo!=null){
				carBaseContactsDo.setMycomID(userDo.getCompanyId());
			}
			if(!StringUtils.isBlank(searchStr)){//全局搜索
				List<CarBaseContactsDO> listcarBaseContactsDo=carBaseContactsService.getsearchCarContacts(searchStr,userDo.getDeptID(),pageBounds);
				System.out.println(listcarBaseContactsDo.size());
				return pageBounds.putResultObj(listcarBaseContactsDo);
			}
			List<CarBaseContactsDO> listcarBaseContactsDo=carBaseContactsService.getCarBaseContactsList(carBaseContactsDo, pageBounds);
			return pageBounds.putResultObj(listcarBaseContactsDo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取往来单位列表出错{0}", e);
			return null;
		}
		
	}
	/**
	 * 
	 * @Title: getbyid
	 * @Description: 根据id获取往来单位详情信息
	 * @return
	 * @return: CarBaseContactsDO
	 * @author: admin  
	 * @date: 2017年11月29日 下午2:32:38
	 */
	@RequestMapping("getContactsDetailsById")
	public ModelAndView getbyid(String id){
		try {
			ModelAndView md = new ModelAndView("baseInfo/contactsDetail");
			CarBaseContactsDO carBaseContactsDo = carBaseContactsService.getCarBaseContactsByID(new Integer(id).intValue());
			md.addObject("carBaseContactsDo", carBaseContactsDo);
			return md;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("获取往来单位详情出错", e);
			return null;
		}
		
	}
	/**
	 * 
	 * @Title: getAddContactsView
	 * @Description: TODO获取增加往来单位的页面
	 * @return
	 * @return: String
	 * @author: admin  
	 * @date: 2017年11月29日 下午5:03:41
	 */
	@RequestMapping("/editContactsRecords")
	public ModelAndView getAddContactsView(String id){
		if(StringUtils.isBlank(id)){
			//新增往来单位
			return new ModelAndView("baseInfo/editContactsRecords");
		}else{
			//编辑往来单位
			CarBaseContactsDO carBaseContactsDo = carBaseContactsService.getCarBaseContactsByID(new Integer(id).intValue());
			return new ModelAndView("baseInfo/editContactsRecords", "carBaseContactsDo", carBaseContactsDo);
		}
	}
	/**
	 * 
	 * @Title: addAndEditContacts
	 * @Description: TODO新增或修改往来单位
	 * @param carBaseContactsDo
	 * @param request
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: admin  
	 * @date: 2017年12月2日 下午11:38:09
	 */
	@RequestMapping("addAndEditContacts")
	@ResponseBody
	public ReturnDataInfo<Object> addAndEditContacts(CarBaseContactsDO carBaseContactsDo,HttpServletRequest request){
		try {
			CarSysUserDO userDo=(CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			carBaseContactsDo.setMycomID(userDo.getCompanyId());
			carBaseContactsService.saveCarBaseContacts(carBaseContactsDo);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("操作往来单位出错", e);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作不成功，请重试");
		}
		
	}
	/**
	 * 
	 * @Title: changeStatus
	 * @Description: TODO改变停用状态
	 * @param ID
	 * @param status
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: admin  
	 * @date: 2017年12月1日 下午2:51:33
	 */
	@RequestMapping("changeStatus")
	@ResponseBody
	public ReturnDataInfo<Object> changeStatus(String ID,String status){
		try {
			carBaseContactsService.updateStatus(new Integer(status).intValue(), new Integer(ID).intValue());
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("success");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		
	}
	@RequestMapping("/deleteContactsByIds")
	@ResponseBody
	public ReturnDataInfo<Object> deleteContactsByIds(String ids){
		try {
			String[] splitIds = ids.split(",");
			boolean reslut = carBaseContactsService.deleteContacts(splitIds);
			if(reslut == true){
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
			}
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除失败");
		} catch (Exception e) {
			logger.error("删除往来单位失败", e);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除失败");
			// TODO: handle exception
		}
		
		
	}
}	
