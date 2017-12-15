package com.carTravelsky.control.system;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseCompanyDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseCompanyService;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
import com.carTravelsky.service.system.CarSysUserRoleService;
import com.carTravelsky.service.system.CarSysUserService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;
import com.stopec.common.utils.Coder;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;
@RequestMapping("/user")
@Controller
public class UserControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private	CarSysUserService carSysUserService;
	@Autowired
	private CarSysUserRoleService carSysUserRoleService;
	@Autowired
	private CarBaseCompanyService carBaseCompanyService;
	@Autowired
	private CarBaseStaffService carBaseStaffService;
	/**
	 * 
	 * @Title: userRole
	 * @Description: 跳转用户管理
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月16日 下午2:22:17
	 */
	@RequestMapping("/userRole")
	public String userRole(HttpServletRequest request){
			return "system/listUser";
	}
	/**
	 * 
	 * @Title: editCompany
	 * @Description: 跳转到公司信息
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月20日 上午10:25:30
	 */
	@RequestMapping("/editCompany")
	public String editCompany(HttpServletRequest request){
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			CarSysUserDO carSysUserDO =carSysUserService.getCarSysUserByID(carSysUser.getId());
			request.setAttribute("carSysUser",carSysUserDO);
				return "system/editCompany";
		} catch (Exception e) {
			logger.error("登陆用户为null {0}", e);
				return null;
		}
	}


	/**
	 *用户管理：  编辑或者新增角色
	 * @Title: editUserRole
	 * @Description: TODO
	 * @param roleID
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editUserRole")
	public String editUserRole(Integer userID,HttpServletRequest request){
		try {
			CarSysUserDO carSysUserDO =carSysUserService.getCarSysUserByID(userID);
			if(carSysUserDO==null){
				return "system/editUserRole";
			}
			CarBaseStaffDO carBaseStaffDO = carBaseStaffService.getCarBaseStaffByID(carSysUserDO.getStaffID());
			carSysUserDO.setStaffName(carBaseStaffDO.getStaffName());
			request.setAttribute("carSysUser",carSysUserDO);
			return "system/editUserRole";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editUserRole";
		}
	}
	/**
	 *用户管理：  编辑或者新增角色
	 * @Title: editUserRole
	 * @Description: TODO
	 * @param roleID
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addUser")
	public String addUser(Integer userID,HttpServletRequest request){
		try {
			CarSysUserDO carSysUserDO =carSysUserService.getCarSysUserByID(userID);
			if(carSysUserDO==null){
				return "system/editUserRole";
			}
			request.setAttribute("carSysUser",carSysUserDO);
			return "system/editUserRole";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editUserRole";
		}
	}
	/**
	 *用户管理：修改密码
	 * @Title: editUserPass
	 * @Description: TODO
	 * @param roleID
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editUserPass")
	public String editUserPass(HttpServletRequest request){
			
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		request.setAttribute("carSysUser", carSysUser);
			return "system/editUserPass";
	}
	/**
	 * 删除用户
	 * @Title: deleteUser
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/deleteUser")
	@ResponseBody
	public ReturnDataInfo<Object> deleteUser(HttpServletRequest request, String userID) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			String[] splitIds = userID.split(",");
			carSysUserService.deleteUsersById(splitIds, carSysUser.getId());
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
		} catch (Exception e) {
			logger.error("删除用户 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}
	/**
	 * 保存用户
	 * @Title: saveUserRole
	 * @Description: 新增用户和修改用户
	 * @param request
	 * @param carSysRoleDO
	 * @param check
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月16日 下午4:30:17
	 */
	@RequestMapping("/saveUserRole")
	@ResponseBody
	public ReturnDataInfo<Object> saveUserRole(HttpServletRequest request, CarSysUserDO carSysUserDO,String userRole,String status){
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		CarSysUserDO userDO= new CarSysUserDO();
	
		//根据职员id 查询 该职员是否已有账号 ====新增用户
		if(carSysUserDO.getId()==null){
		userDO.setStaffID(carSysUserDO.getStaffID());
	
		List<CarSysUserDO> stffDo = carSysUserService.getCarSysUserList(userDO);
		if(stffDo.size()>0){
			return ReturnDataInfo.createFailedExecuteResult("保存用户失败：{该用户已拥有账号}");
			
		}
		}
		
		userDO.setUsername(carSysUserDO.getUsername());
		
		
		//得到所有选中的角色
		String[] splitIds = StringUtils.split(userRole, ",");
		//如果用户id为null  那么就新增
		String isNull= "update";
		try {
			List<CarSysUserDO> userList = carSysUserService.getCarSysUserList(userDO);
				if(userList.size()>0){
					if(!StringUtils.equals(userList.get(0).getId()+"", carSysUserDO.getId()+"")){
						return ReturnDataInfo.createFailedExecuteResult("用户名已存在！");
					}
			}
			// 默认状态
			if(carSysUserDO.getStatus() == null){
				carSysUserDO.setStatus(YSTConstants.ENABLE);
			}
			if(carSysUserDO.getId()==null){
				
				// 根据职员id得到职员实体
				CarBaseStaffDO carBaseStaffDO = carBaseStaffService.getCarBaseStaffByID(carSysUserDO.getStaffID());
				
				String userName = carSysUserDO.getUsername();
				// 设置角色部门id
				carSysUserDO.setDeptID(carBaseStaffDO.getDeptID());
				carSysUserDO.setCompanyId(empDO.getCompanyId());
				carSysUserDO.setPassword(Coder.MD5.encode(YSTConstants.DEFULTPASS));
				carSysUserDO.setCreateTime(new Date());
				carSysUserDO.setCreator(empDO.getId()+"");
				carSysUserDO.setMnemonicCode(ChineseLetter.getPinYinHeadChar(userName).toUpperCase());
				isNull= "";
			}
			carSysUserDO.setUpdateOpertor(empDO.getId()+"");
			carSysUserDO.setUpdateTime(new Date());
			//校验
			ValidationUtils.validate(carSysUserDO);
			ValidateResult result = carSysUserDO.getValidateResult();
			if (!result.getResult()) {
				return ReturnDataInfo.createFailedExecuteResult("保存用户失败"+result.getMessageLine());
			}
			//新增 编辑 保存角色
			carSysUserService.saveCarSysUser(carSysUserDO);//保存用户信息===》 返回该用户id
			carSysUserRoleService.saveCarSysUserRole(splitIds,isNull,carSysUserDO.getId(),userRole);//保存用户角色信息+  增加
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存角色 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存角色失败");
		}
	}
	@RequestMapping("/saveUserStatus")
	@ResponseBody
	public ReturnDataInfo<Object> saveUserStatus(HttpServletRequest request,CarSysUserDO carSysUserDO){
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			carSysUserDO.setUpdateOpertor(empDO.getId()+"");
			carSysUserDO.setUpdateTime(new Date());
			carSysUserService.saveCarSysUser(carSysUserDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存角色 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存停用失败");
		}
	}
	/**
	 * 
	 * @Title: saveCompany
	 * @Description: 保存用户公司 信息
	 * @param request
	 * @param carBaseCompanyDO
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月20日 上午10:34:42
	 */
	@RequestMapping("/saveCompany")
	@ResponseBody
	public ReturnDataInfo<Object> saveCompany(HttpServletRequest request,CarBaseCompanyDO carBaseCompanyDO){
		CarSysUserDO carSysUserDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			//校验
			ValidationUtils.validate(carBaseCompanyDO);
			ValidateResult result = carBaseCompanyDO.getValidateResult();
			if (!result.getResult()) {
				return ReturnDataInfo.createFailedExecuteResult("保存失败"+result.getMessageLine());
			}
			carBaseCompanyDO.setId(carSysUserDO.getCompanyId());
			carBaseCompanyDO.setUpdateOperator(carSysUserDO.getId()+"");
			carBaseCompanyDO.setUpdateTime(new Date());
			carBaseCompanyService.saveCarBaseCompany(carBaseCompanyDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存公司 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存公司失败");
		}
	}
	
	
	/**
	 * 
	 * @Title: getUserList
	 * @Description: 获取列表
	 * @param searchStr
	 * @param request
	 * @param carSysUserDO
	 * @param pageBounds
	 * @return
	 * @return: Map<String,Object>
	 * @author: fuxinrong
	 * @date: 2017年10月16日 下午8:44:25
	 */
	@RequestMapping("/getUserList")
	@ResponseBody
	public Map<String, Object> getUserList(String searchStr,HttpServletRequest request, CarSysUserDO carSysUserDO,PageBounds pageBounds){
		try {
			if(!StringUtils.isBlank(searchStr)){
				List<CarSysRoleDO> searchCarSysRoleList = carSysUserService.getsearchCarSysRoleList(searchStr, pageBounds);
				return pageBounds.putResultObj(searchCarSysRoleList);
			}
			return pageBounds.putResultObj(carSysUserService.getCarSysUserList(carSysUserDO, pageBounds));
		} catch (Exception e) {
			logger.error("查询角色列表出错：{0}", e);
			return null;
		}
	}
	@RequestMapping("/modifyPass")
	@ResponseBody
	public ReturnDataInfo<Object> modifyPass(String newPass,CarSysUserDO carSysUserDO,HttpServletRequest request){
		try {
			
			//验证输入原密码是否匹配
			CarSysUserDO userDO=carSysUserService.getCarSysUserByID(carSysUserDO.getId());
			if(!StringUtils.equals(userDO.getPassword(),Coder.MD5.encode(carSysUserDO.getPassword()))){
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("error");
			}
				carSysUserDO.setPassword(Coder.MD5.encode(newPass));
				carSysUserDO.setUpdateTime(new Date());
				carSysUserDO.setUpdateOpertor(carSysUserDO.getId()+"");
				carSysUserService.saveCarSysUser(carSysUserDO);
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("修改成功");
		} catch (Exception e) {
			logger.error("修改密码出错 : {0}", e);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("修改失败");
		}
	}
	
}
