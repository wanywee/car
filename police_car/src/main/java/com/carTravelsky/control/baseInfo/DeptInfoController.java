package com.carTravelsky.control.baseInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarBaseDeptmentService;
import com.carTravelsky.service.carDaily.CarBaseStaffService;
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
 * 
 * @ClassName: DeptInfoController
 * @Description: 部门信息 Controller 类
 * @author: yanlinlong
 * @date: 2017年10月12日 上午9:37:16
 */
@Controller
@RequestMapping("/deptInfo")
public class DeptInfoController {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseDeptmentService carBaseDeptmentService;
	@Autowired
	private CarBaseStaffService carBaseStaffService;

	/**
	 * 
	 * @Title: getListDeptInfo
	 * @Description: 根据公司ID 获取所有部门信息
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/getListDeptInfo")
	@ResponseBody
	public Map<String, Object> getListDeptInfoByCompanyId(
			HttpServletRequest request, CarBaseDeptmentDO carBaseDeptmentDO,
			PageBounds pageBounds, String searchStr) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			/*if(YSTConstants.IS_CURRENT_DEP_VIEW==currentUser.getIsAllView()){
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}*/

			List<CarBaseDeptmentDO> deptmentDOs = new ArrayList<>();
			if (!StringUtils.isBlank(searchStr)) {
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("id", currentUser.getCompanyId());
				paraMap.put("searchStr", searchStr);
				deptmentDOs = carBaseDeptmentService.getsearchCarBaseDeptInfo(paraMap, pageBounds);
				return pageBounds.putResultObj(deptmentDOs);
			}
			deptmentDOs = carBaseDeptmentService.getListDeptInfoByCompanyId(currentUser.getCompanyId(), pageBounds);
			return pageBounds.putResultObj(deptmentDOs);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有部门信息出错{0}", e);
			return null;
		}
	}

	
	
	
	/**
	 * @Title: getListDeptInfotree
	 * @Description: TODO 获取树形结构列表
	 * @param request
	 * @param carBaseDeptmentDO
	 * @param pageBounds
	 * @param searchStr
	 * @return
	 * @return: Map<String,Object>
	 * @author: THINK  
	 * @date: 2017-12-4 下午10:49:48
	 */
	@RequestMapping(value = "/getListDeptInfotree")
	@ResponseBody
	public Map<String, Object> getListDeptInfotree(
			HttpServletRequest request, CarBaseDeptmentDO carBaseDeptmentDO,
			PageBounds pageBounds, String searchStr) {
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		Integer rootId=empDO.getDeptID();
		 if(YSTConstants.IS_ALL_DEP_VIEW==empDO.getIsAllView()){
			 rootId= YSTConstants.ROOT_DEPT_ID;
		 }
		try {
			List<CarBaseDeptmentDO> deptmentDOs=null;
			deptmentDOs = carBaseDeptmentService.getListDeptInfotree(rootId,searchStr);
			return pageBounds.putResultObj(deptmentDOs);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有部门信息出错{0}", e);
			return null;
		}
	}
	/**
	 * 
	 * @Title: getDeptListSelect
	 * @Description: 获取部门信息集合-下拉框
	 * @return
	 * @return: List<Select2VO>
	 * @author: fuxinrong
	 * @date: 2017年10月18日 下午12:36:48
	 */
	@RequestMapping("/getDeptListSelect")
	@ResponseBody
	public List<Select2VO> getDeptListSelect(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str) {
		CarSysUserDO empDO = (CarSysUserDO) SessionContext.Attribute.get(
				request, YSTConstants.USERINFO);
		try {
			return carBaseDeptmentService.getSelect2ListDeptInfo(empDO, str);
		} catch (Exception e) {
			logger.error("查询所有角色出错：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getListStaff
	 * @Description: 新增部门-负责人下拉框
	 * @return
	 * @return: List<CarBaseStaffDO>
	 */
	@RequestMapping(value = "/getListStaff", method = RequestMethod.POST)
	@ResponseBody
	public List<Select2VO> getListStaffByCompanyId(HttpServletRequest request,
			@RequestParam(value = "q", required = false) String str, @RequestParam(value = "deptId", required = false) Integer deptId) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			// 根据公司id加载职工
			return carBaseStaffService.getSelect2ListStaff(currentUser, str, deptId);
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有职员信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: addDeptInfo
	 * @Description: 部门信息-跳转新增部门信息
	 * @return
	 * @return: String
	 * @author: yanlinlong
	 * @date: 2017年11月1日 下午3:03:17
	 */
	@RequestMapping("addDeptInfo")
	public String addDeptInfo(Integer selectDeptId,Model model) {
		try {
		 CarBaseDeptmentDO carBaseDeptmentDO=carBaseDeptmentService.findDeptByid(selectDeptId);
			model.addAttribute("deptParent", carBaseDeptmentDO);
			return "baseInfo/editDeptInfo";
		} catch (Exception e) {
			logger.error("跳转新增部门信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: editDeptInfo
	 * @Description: 部门新增和编辑处理
	 * @param id
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/editDeptInfo")
	public String editDeptInfo(HttpServletRequest request,
			@RequestParam("ID") Integer id, Model model) {
		try {
			// 根据部门id查询部门信息
			CarBaseDeptmentDO carBaseDeptmentDO = carBaseDeptmentService.getCarBaseDeptmentByID(id);
			// 根据职员id查询职员信息
			CarBaseStaffDO carBaseStaffDO = carBaseStaffService.getCarBaseStaffByID(Integer.valueOf(carBaseDeptmentDO.getPrincipal()));
			if(carBaseStaffDO!=null){
			carBaseDeptmentDO.setPrincipalName(carBaseStaffDO.getStaffName());
			}
			model.addAttribute("carBaseDeptment", carBaseDeptmentDO);
			 CarBaseDeptmentDO deptParent=carBaseDeptmentService.findDeptByid(carBaseDeptmentDO.getCompanyID());
			model.addAttribute("deptParent", deptParent);
			return "baseInfo/editDeptInfo";
		} catch (Exception e) {
			logger.error("根据公司ID,获取所有职员信息出错{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: updateDeptInfo
	 * @Description: 部门新增和编辑
	 * @param carBaseDeptmentDO
	 * @return
	 * @return: String
	 */
	@RequestMapping(value = "/updateDeptInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> updateDeptInfo(HttpServletRequest request,
			CarBaseDeptmentDO carBaseDeptmentDO) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			// 设置部门助记码
			if (carBaseDeptmentDO.getId() == null) {
				if (StringUtils.isBlank(carBaseDeptmentDO.getDeptCode())) {
					// 获取部门编码最大值
					String maxCode = carBaseDeptmentService
							.getMaxDeptCode(currentUser.getCompanyId());
					// 设置部门编码
					carBaseDeptmentDO.setDeptCode(maxCode);
				} else {
					// 根据部门编码查询 看是否已有该部门编码
					CarBaseDeptmentDO newBaseDeptmentDO = new CarBaseDeptmentDO();
					newBaseDeptmentDO.setDeptCode(carBaseDeptmentDO
							.getDeptCode());
					List<CarBaseDeptmentDO> resultDeptmen = carBaseDeptmentService
							.getCarBaseDeptmentList(newBaseDeptmentDO);
					if (resultDeptmen != null && resultDeptmen.size() > 0) {
						return ReturnDataInfo
								.createFailedExecuteResult("部门编码已经存在！");
					}
				}
				// 助记码 部门名字首字母大写 (也是新增才有的操作)
				String mnemonicCode = ChineseLetter.getPinYinHeadChar(
						carBaseDeptmentDO.getDeptName()).toUpperCase();
				carBaseDeptmentDO.setMnemonicCode(mnemonicCode);
			}
			ValidationUtils.validate(carBaseDeptmentDO);
			ValidateResult result = carBaseDeptmentDO.getValidateResult();
			if (result.getResult()) {
				// 设置默认状态（前台没有选择则为1 选择则不用设置）
				if (carBaseDeptmentDO.getStatus() == null) {
					carBaseDeptmentDO.setStatus(YSTConstants.ENABLE);
				}
				carBaseDeptmentService.saveCarBaseDeptment(currentUser,
						carBaseDeptmentDO);
				return ReturnDataInfo
						.createSuccessfulSingleExecuteMessage("操作部门信息成功！");
			} else {
				return ReturnDataInfo.createFailedExecuteResult(result
						.getMessageList().toString());
			}
		} catch (Exception e) {
			logger.error("添加或者更新部门信息出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作部门信息失败！");
		}
	}

	/**
	 * 
	 * @Title: deleteDeptInfo
	 * @Description: 部门信息状态删除
	 * @param roleID
	 * @param deleteType
	 * @param request
	 * @return
	 * @return: ReturnDataInfo<Object>
	 */
	@RequestMapping("/deleteDeptInfo")
	@ResponseBody
	public ReturnDataInfo<Object> deleteDeptInfo(HttpServletRequest request,
			@RequestParam(value = "deptmentID") String deptmentID) {
		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			String[] splitIds = deptmentID.split(",");
			String reslut = carBaseDeptmentService.deleteDeptInfo(currentUser,splitIds);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage(reslut);
		} catch (Exception e) {
			logger.error("删除部门信息 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("删除失败");
		}
	}

	/**
	 * 
	 * @Title: isUseDeptInfo
	 * @Description: 部门列表操作是否停用
	 * @param request
	 * @param status
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: yanlinlung
	 * @date: 2017年10月14日 下午11:56:12
	 */
	@RequestMapping(value = "/isUseDeptInfo", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> isUseDeptInfo(HttpServletRequest request,
			@RequestParam("ID") Integer ID,
			@RequestParam("status") Integer status) {
		try {
			CarBaseDeptmentDO carBaseDeptmentDO = new CarBaseDeptmentDO();
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			carBaseDeptmentDO.setId(ID);
			carBaseDeptmentDO.setStatus(status);
			carBaseDeptmentService.updateCarBaseDeptment(currentUser,
					carBaseDeptmentDO);
			return ReturnDataInfo
					.createSuccessfulSingleExecuteMessage("是否停用操作成功！");
		} catch (Exception e) {
			logger.error("部门列表操作是否停用出错{0}", e);
			return ReturnDataInfo.createFailedExecuteResult("是否停用操作失败！");
		}
	}

}
