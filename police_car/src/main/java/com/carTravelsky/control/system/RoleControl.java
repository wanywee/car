package com.carTravelsky.control.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysRoleMenuDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.CarSysMenuService;
import com.carTravelsky.service.system.CarSysRoleMenuService;
import com.carTravelsky.service.system.CarSysRoleService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;
/**
 * 权限管理
 * @ClassName: RoleControl
 * @Description: TODO
 * @author: dg
 * @date: 2017年10月17日 上午8:50:52
 */
@RequestMapping("/role")
@Controller
public class RoleControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarSysRoleService roleBiz;
	@Autowired
	private CarSysMenuService permissionBiz;
	@Autowired
	private CarSysRoleMenuService rolePermissionBiz;
	/**
	 * 进入页面
	 * @Title: listRole
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/listRole")
	public String listRole(HttpServletRequest request){
		try {
			return "system/listRole";
		} catch (Exception e) {
			logger.error("进入角色列表 {0}", e);
			return "system/listRole";
		}
	}
	/**
	 * 编辑或者新增角色
	 * @Title: editRole
	 * @Description: TODO
	 * @param roleID
	 * @return
	 * @return: String
	 */
	@RequestMapping("/editRole")
	public String editRole(Integer roleID,HttpServletRequest request){
		try {
			CarSysRoleDO carSysRoleDO = roleBiz.getCarSysRoleByID(roleID);
			if(carSysRoleDO == null){
				return "system/editRole";
			}
			request.setAttribute("carSysRole",carSysRoleDO);
			return "system/editRole";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editRole";
		}
	}
	/**
	 * 编辑或者新增角色
	 * @Title: editRole
	 * @Description: TODO
	 * @param roleID
	 * @return
	 * @return: String
	 */
	@RequestMapping("/addRole")
	public String addRole(Integer roleID,HttpServletRequest request){
		try {
			CarSysRoleDO carSysRoleDO = roleBiz.getCarSysRoleByID(roleID);
			if(carSysRoleDO == null){
				return "system/editRole";
			}
			request.setAttribute("carSysRole",carSysRoleDO);
			return "system/editRole";
		} catch (Exception e) {
			logger.error("编辑角色 {0}", e);
			return "system/editRole";
		}
	}
	
	/**删除role
	 * @Title: deleteRole
	 * @Description: TODO
	 * @param roleID
	 * @param deleteType
	 * @param request
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("/deleteRole")
	@ResponseBody
	public ReturnDataInfo<Object> deleteRole(@RequestParam(value ="roleID",required =false)String roleID,String deleteType,HttpServletRequest request){
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		List<Integer> notDeleteIds = new ArrayList<>();
		try {
			String[] splitIds = roleID.split(",");
			notDeleteIds = roleBiz.deleteRolesById(splitIds,notDeleteIds,carSysUser.getId());
			if(notDeleteIds.size() == 0){
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功");
			}else{
				return ReturnDataInfo.createFailedExecuteResult("不能删除"+notDeleteIds); 
			}
		} catch (Exception e) {
			logger.error("删除角色 {0}", e);	
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}
	/**
	 * 保存角色
	 * @Title: saveRole
	 * @Description: TODO
	 * @param carSysRoleDO
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("/saveRole")
	@ResponseBody
	public ReturnDataInfo<Object> saveRole(HttpServletRequest request, CarSysRoleDO carSysRoleDO,String check){
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		carSysRoleDO.setCompanyID(empDO.getCompanyId());
		try {
			if(StringUtils.equals(check, "on")){
				carSysRoleDO.setStatus(YSTConstants.DISENABLE);
			}else{
				carSysRoleDO.setStatus(YSTConstants.ENABLE);
			}
			//查询RoleName是否存在
			if(roleBiz.isexitCode("roleName",carSysRoleDO.getRoleName(),carSysRoleDO)){
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage("角色名字已经存在");
			}
			if(carSysRoleDO.getId()==null){
				// 获取编码最大的值
				Integer maxCode = roleBiz.getMaxRoleCode(empDO.getCompanyId());
				if(maxCode==null){
					maxCode=0;
				}
				++maxCode;
				carSysRoleDO.setRoleCode(YSTConstants.ROLECODEPRE+""+(maxCode));
				carSysRoleDO.setCompanyID(empDO.getCompanyId());
				String roleName = carSysRoleDO.getRoleName();
				carSysRoleDO.setCreateTime(new Date());
				carSysRoleDO.setCreator(empDO.getId()+"");
				carSysRoleDO.setMnemonicCode(ChineseLetter.getPinYinHeadChar(roleName).toUpperCase());	
			}else{
				//查询RoleCode1是否存在
				if(roleBiz.isexitCode("roleCode",carSysRoleDO.getRoleCode(),carSysRoleDO)){
					return ReturnDataInfo.createSuccessfulSingleExecuteMessage("角色编码已经存在");
				}
			}
			//校验
			ValidationUtils.validate(carSysRoleDO);
			ValidateResult result = carSysRoleDO.getValidateResult();
			if (!result.getResult()) {
				return ReturnDataInfo.createFailedExecuteResult("保存角色失败"+result.getMessageLine());
			}
			carSysRoleDO.setUpdateOperator(empDO.getId()+"");
			carSysRoleDO.setUodateTime(new Date());
			roleBiz.saveCarSysRole(carSysRoleDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存角色 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存角色失败");
		}
	}
	/**
	 * 
	 * @Title: saveRoleStatus
	 * @Description: 保存角色停用信息
	 * @param request
	 * @param carSysRoleDO
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: fuxinrong
	 * @date: 2017年10月19日 上午9:52:32
	 */
	@RequestMapping("/saveRoleStatus")
	@ResponseBody
	public ReturnDataInfo<Object> saveRoleStatus(HttpServletRequest request,CarSysRoleDO carSysRoleDO){
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			carSysRoleDO.setUpdateOperator(empDO.getId()+"");
			carSysRoleDO.setUodateTime(new Date());
			roleBiz.saveCarSysRole(carSysRoleDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("保存成功");
		} catch (Exception e) {
			logger.error("保存角色 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("保存停用失败");
		}
	}
	
	/**
	 * 判断code是否存在
	 * @Title: isexitCode
	 * @Description: TODO
	 * @param roleCode
	 * @return
	 * @return: boolean
	 */
	@RequestMapping("/getRoleList")
	@ResponseBody
	public Map<String, Object> getRoleList(String searchStr,HttpServletRequest request, CarSysRoleDO carSysRoleDO,PageBounds pageBounds){
		try {
			CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			carSysRoleDO.setCompanyID(empDO.getCompanyId());
			if(!StringUtils.isBlank(searchStr)){
			//	searchStr =  URLDecoder.decode(searchStr,"utf-8");
				List<CarSysRoleDO> searchCarSysRoleList = roleBiz.getsearchCarSysRoleList(searchStr, pageBounds);
				return pageBounds.putResultObj(searchCarSysRoleList);
			}
			return pageBounds.putResultObj(roleBiz.getCarSysRoleList(carSysRoleDO, pageBounds));
		} catch (Exception e) {
			logger.error("查询角色列表出错：{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: getRoleListSelect
	 * @Description: 角色下拉框
	 * @param request
	 * @param str 搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: fuxinrong
	 * @date: 2017年11月8日 下午1:12:58
	 */
	@RequestMapping("/getRoleListSelect")
	@ResponseBody
	public List<Select2VO> getRoleListSelect(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str){
		try {
			List<Select2VO> sList =new ArrayList<Select2VO>();
			return roleBiz.getCarSysRoleList(null,sList,str);
		} catch (Exception e) {
			logger.error("查询所有角色出错：{0}", e);
			return null;
		}
	}
	/**
	 * 获取权限树结构
	 * @Title: getRoleZTreePermissions
	 * @Description: TODO
	 * @param request
	 * @param roleID
	 * @return
	 * @return: List<ZTreeVO>
	 */
	@RequestMapping("/getRoleZTreePermissions")
	@ResponseBody
	public List<ZTreeVO> getRoleZTreePermissions(HttpServletRequest request, Integer roleID) {
		try {
			return permissionBiz.getRoleZTreePermissions(roleID);
		} catch (Exception e) {
			logger.error("查询角色树形权限出错：{0}", e);
			return null;
		}
	}
	
	/**
	 * 进入权限管理
	 * @Title: listPermission
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	@RequestMapping("/listPermission")
	public String listPermission(HttpServletRequest request) {
		List<CarSysRoleDO> CarSysRoleDOs=roleBiz.getCarSysRoleList(null);
		request.setAttribute("role", CarSysRoleDOs);
		return "system/listPermission";
	}
	
	
	/**
	 * 获取所有权限
	 * @param request
	 * @return
	 */
	@RequestMapping("/getZTreePermissionList")
	@ResponseBody
	public List<ZTreeVO> getZTreePermissionList(HttpServletRequest request) {
		try {
			return permissionBiz.getZTreePermissionList();
		} catch (Exception e) {
			logger.error("查询所有权限出错：{0}", e);
			return null;
		}
	}
	
	
	/**
	 * 获取单个权限
	 * @param permissionID
	 * @return
	 */
	@RequestMapping("getPermissionByID")
	@ResponseBody
	public CarSysMenuDO getPermissionByID(int permissionID){
		return permissionBiz.getCarSysMenuByID(permissionID);
	}
	
	/**
	 * 
	 * @Title: savePermission
	 * @Description: TODO
	 * @param permissionDO
	 * @param CarSysRoleDO
	 * @param roleName
	 * @param permissionIDs
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "savePermission",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String savePermission(CarSysMenuDO permissionDO,CarSysRoleDO CarSysRoleDO,String roleName,String permissionIDs,
			HttpServletRequest request){
		try {
			CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			//先要判断菜单的类型
			//判断是否是新增还是修改
			if(permissionDO.getId() == null){
				permissionDO.setStatus(YSTConstants.DELETE_CODE_NORMAL);
				permissionDO.setCreateTime(new Date());
				permissionDO.setCreator(empDO.getId()+"");
			}
			permissionDO.setUpdateOperator(empDO.getId()+"");
			permissionDO.setUpdateTime(new Date());
			//校验
			ValidationUtils.validate(permissionDO);
			ValidateResult result = permissionDO.getValidateResult();
			if (!result.getResult()) {
				return YSTConstants.FAILED+result.getMessageLine();
			}
			permissionBiz.saveCarSysMenu(permissionDO);
			return permissionDO.getId()+"";
		} catch (Exception e) {
			logger.error("修改权限出错：{0}", e);
			return YSTConstants.FAILED;
		}
	}
	/**
	 * 保存权限
	 * @Title: savePermissions
	 * @Description: TODO
	 * @param permissionDO
	 * @param CarSysRoleDO
	 * @param roleName
	 * @param permissionIDs
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("savePermissions")
	@ResponseBody
	public ReturnDataInfo<Object> savePermissions( CarSysMenuDO permissionDO,CarSysRoleDO CarSysRoleDO,String roleName,String permissionIDs){
		try {	
				//修改角色的权限，多个id
				String[] elementArr = StringUtils.split(permissionIDs, ",");
				CarSysRoleMenuDO rolePermissionDO = new CarSysRoleMenuDO();
				rolePermissionDO.setRoleID(CarSysRoleDO.getId());
				List<CarSysRoleMenuDO> permissions = rolePermissionBiz.getCarSysRoleMenuList(rolePermissionDO);
				rolePermissionBiz.updatePermisssion(elementArr,permissions,CarSysRoleDO,permissionIDs);
				return ReturnDataInfo.createSuccessfulSingleExecuteMessage(YSTConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("设置权限出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult(YSTConstants.FAILED);
		}
	}
	/**
	 * 删除权限
	 * @Title: deletePermissions
	 * @Description: TODO
	 * @param permissionDO
	 * @param CarSysRoleDO
	 * @return
	 * @return: String
	 */
	@RequestMapping("deletePermissions")
	@ResponseBody
	public ReturnDataInfo<Object> deletePermissions( CarSysMenuDO permissionDO,CarSysRoleDO CarSysRoleDO){
		try {
			//首先删除权限表中的
			//在删除关联表中的数据
			permissionBiz.deletePermission(permissionDO);
			rolePermissionBiz.deletePermission(permissionDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage(YSTConstants.SUCCESS);
		} catch (Exception e) {
			logger.error("删除权限出错{0}",e);
			return ReturnDataInfo.createFailedExecuteResult(YSTConstants.FAILED);
		}	
	}
}

