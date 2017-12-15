package com.carTravelsky.control.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.KeyCodeMasterService;
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
 * 
 * @ClassName: DictionaryControl
 * @Description: 系统字典
 * @author: fuxinrong
 * @date: 2017年10月19日 下午2:12:03
 */
@RequestMapping("/dictay")
@Controller
public class DictionaryControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;
	
	/**
	 * 
	 * @Title: codeMaster
	 * @Description:  跳转到系统字典界面 
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月19日 下午2:53:53
	 */
	@RequestMapping("/codeMaster")
	public String codeMaster(HttpServletRequest request){
			return "system/listCodeMaster";
	}
	
	
	@RequestMapping("/getCodeMasterList")
	@ResponseBody
	public Map<String, Object> getDtyList(String searchStr,HttpServletRequest request, KeyCodeMasterDO keyCodeMasterDO,PageBounds pageBounds){
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		//角色是否可见所有部门数据
		/*if(YSTConstants.IS_CURRENT_DEP_VIEW==carSysUser.getIsAllView()){
			pageBounds.setNeedCompany(true);
			pageBounds.setCompanyID(carSysUser.getDeptID());
		}*/
		try {
			if(!StringUtils.isBlank(searchStr)){
				List<KeyCodeMasterDO> keyCodeList=keyCodeMasterService.getSearchCodeMasterList(searchStr,carSysUser,pageBounds);
				 return pageBounds.putResultObj(keyCodeList);
			}
			
			return pageBounds.putResultObj(keyCodeMasterService.getKeyCodeMasterList(keyCodeMasterDO,carSysUser, pageBounds));
		} catch (Exception e) {
			logger.error("查询角色列表出错：{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: editCodeMaster
	 * @Description: 字典  编辑，新增
	 * @param userID
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月19日 下午2:55:11
	 */
	@RequestMapping("/editCodeMaster")
	public String editCodeMaster(Integer keyID,HttpServletRequest request){
		try {
			KeyCodeMasterDO keyCodeMasterDO =keyCodeMasterService.getKeyCodeMasterByID(keyID);
			if(keyCodeMasterDO==null){
				return "system/editCodeMaster";
			}
			request.setAttribute("keyCodeMasterDO",keyCodeMasterDO);
			return "system/editCodeMaster";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editUserRole";
		}
	}
	
	/**
	 * 
	 * @Title: editCodeMaster
	 * @Description: 字典  编辑，新增
	 * @param userID
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月19日 下午2:55:11
	 */
	@RequestMapping("/addCodeMaster")
	public String addCodeMaster(Integer keyID,HttpServletRequest request){
		try {
			KeyCodeMasterDO keyCodeMasterDO =keyCodeMasterService.getKeyCodeMasterByID(keyID);
			if(keyCodeMasterDO==null){
				return "system/editCodeMaster";
			}
			request.setAttribute("keyCodeMasterDO",keyCodeMasterDO);
			return "system/editCodeMaster";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editUserRole";
		}
	}
	
	/**
	 * 删除字典
	 * @Title: deleteCodeMaster
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/deleteCodeMaster")
	@ResponseBody
	public ReturnDataInfo<Object> deleteCodeMaster(HttpServletRequest request, String keyID) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			String[] splitIds = keyID.split(",");
			keyCodeMasterService.deleteCodeMasterByID(splitIds, carSysUser.getId());
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除用户 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}
	/**
	 * 
	 * @Title: saveCodeMaster
	 * @Description: 保存
	 * @param request
	 * @param keyID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月19日 下午3:20:22
	 */
	@RequestMapping("/saveCodeMaster")
	@ResponseBody
	public ReturnDataInfo<Object> saveCodeMaster(HttpServletRequest request,KeyCodeMasterDO keyCodeMasterDO) {
		
		try {
			//校验
			ValidationUtils.validate(keyCodeMasterDO);
			ValidateResult result = keyCodeMasterDO.getValidateResult();
			if (!result.getResult()) {
				return ReturnDataInfo.createFailedExecuteResult("保存字典失败"+result.getMessageLine());
			}
			KeyCodeMasterDO	keyType =new KeyCodeMasterDO();
			keyType.setKeyType(keyCodeMasterDO.getKeyType());
			CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			//查询字典类型
			String flag=keyCodeMasterService.KeyType(keyType,keyCodeMasterDO,carSysUser);
				if(!StringUtils.equals(flag, "")){
					return ReturnDataInfo.createFailedExecuteResult(flag);
				}
			if(keyCodeMasterDO.getKeyID()==null){
				keyCodeMasterDO.setCreateBy(carSysUser.getId()+"");
				keyCodeMasterDO.setCreateDate(new Date());
			}
			keyCodeMasterDO.setUpdateBy(carSysUser.getId()+"");
			keyCodeMasterDO.setLastModified(new Date());
			keyCodeMasterService.saveKeyCodeMaster(keyCodeMasterDO,carSysUser);
			return ReturnDataInfo.createFailedExecuteResult("保存字典成功");
		} catch (Exception  e) {
			logger.error("保存字典 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存字典失败");
		}
		
	}
	/**
	 * 
	 * @Title: saveCodeMasterStatus
	 * @Description: 保存停用
	 * @param request
	 * @param keyCodeMasterDO
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月19日 下午4:54:47
	 */
	@RequestMapping("/saveCodeMasterStatus")
	@ResponseBody
	public ReturnDataInfo<Object> saveCodeMasterStatus(HttpServletRequest request,KeyCodeMasterDO keyCodeMasterDO){
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			keyCodeMasterDO.setUpdateBy(carSysUser.getId()+"");
			keyCodeMasterDO.setLastModified(new Date());
			keyCodeMasterService.saveKeyCodeMaster(keyCodeMasterDO,carSysUser);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存角色 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存停用失败");
		}
	}
}
