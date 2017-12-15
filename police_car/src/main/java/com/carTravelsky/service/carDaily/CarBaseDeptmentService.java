package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.app.response.CarDetailListRS;
import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.app.AppCarApplyMapper;
import com.carTravelsky.mapper.carDaily.CarBaseDeptmentMapper;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseDeptmentService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseDeptmentMapper carBaseDeptmentMapper;
	@Autowired
	private CarBaseStaffService carBaseStaffService;
	@Autowired
	private CarBaseVehicleMapper carBaseVehicleMapper;
	@Autowired
	private AppCarApplyMapper appCarApplyMapper;

	/**
	 * 保存carBaseDeptmentDO
	 * 
	 * @param currentUser
	 *
	 */
	public void saveCarBaseDeptment(CarSysUserDO currentUser, CarBaseDeptmentDO carBaseDeptmentDO)
			throws ServiceException {
		try {
			CarBaseDeptmentDO deptParentDo = null;
			// 通过页面传入的父节点ID 查询父节点部门
			deptParentDo = carBaseDeptmentMapper.getByID(carBaseDeptmentDO.getDeptParentID());

			Date initDate = new Date();
			// 不管新增还是修改都需要操作更新人和时间
			carBaseDeptmentDO.setUpdateOperator(currentUser.getUsername());
			carBaseDeptmentDO.setUpdateTime(initDate);

			if (carBaseDeptmentDO.getId() == null) {
				/* 处理新父节点部门 */
				if (deptParentDo != null && deptParentDo.getIsLeaf()) {
					deptParentDo.setIsLeaf(YSTConstants.IS_LEAF_FALSE);
					carBaseDeptmentMapper.update(deptParentDo);
				}
				carBaseDeptmentDO.setCreator(currentUser.getUsername());
				carBaseDeptmentDO.setCreateTime(initDate);
				carBaseDeptmentDO.setCompanyID(carBaseDeptmentDO.getDeptParentID());
				if (deptParentDo != null) {
					carBaseDeptmentDO.setLevel(deptParentDo.getLevel() + 1);
				} else {
					carBaseDeptmentDO.setLevel(0);
				}
				carBaseDeptmentDO.setIsLeaf(true);
				carBaseDeptmentDO.setExpanded(true);
				carBaseDeptmentMapper.insert(carBaseDeptmentDO);
			} else {
				// 获取当前部门
				CarBaseDeptmentDO currentDept = carBaseDeptmentMapper.getByID(carBaseDeptmentDO.getId());
				// Integer currentParentId=currentDept.getCompanyID();

				// 更新当前部门
				if (deptParentDo != null)
					carBaseDeptmentDO.setLevel(deptParentDo.getLevel() + 1);
				carBaseDeptmentDO.setCompanyID(carBaseDeptmentDO.getDeptParentID());
				carBaseDeptmentMapper.update(carBaseDeptmentDO);

				/* 处理新父节点部门 */
				if (deptParentDo != null && deptParentDo.getIsLeaf()) {
					deptParentDo.setIsLeaf(YSTConstants.IS_LEAF_FALSE);
					carBaseDeptmentMapper.update(deptParentDo);
				}

				/** 处理原父节点 */
				CarBaseDeptmentDO eqlevelDeptParam = new CarBaseDeptmentDO();
				eqlevelDeptParam.setCompanyID(currentDept.getCompanyID());
				eqlevelDeptParam.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
				// eqlevelDeptParam.setExceptId(currentDept.getId());
				// 1.查询当前节点的同级子节点
				List<CarBaseDeptmentDO> eqLevelDeptList = carBaseDeptmentMapper.getList(eqlevelDeptParam);
				// 2:无同级节点的情况下，将父节点设为叶节点
				if (eqLevelDeptList == null || eqLevelDeptList.size() == 0) {
					eqlevelDeptParam = new CarBaseDeptmentDO();
					eqlevelDeptParam.setId(currentDept.getCompanyID());
					eqlevelDeptParam.setIsLeaf(YSTConstants.IS_LEAF_TRUE);
					eqlevelDeptParam.setUpdateOperator(String.valueOf(currentUser.getId()));
					eqlevelDeptParam.setUpdateTime(new Date());
					// 3.更新原父节点信息
					carBaseDeptmentMapper.update(eqlevelDeptParam);
				}

			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 保存carBaseDeptmentDO
	 * 
	 * @param currentUser
	 *
	 */
	public void saveCarBaseVehicle(CarBaseDeptmentDO carBaseDeptmentDO) throws ServiceException {
		try {
			if (carBaseDeptmentDO.getId() == null) {
				carBaseDeptmentMapper.insert(carBaseDeptmentDO);
			} else {
				carBaseDeptmentMapper.update(carBaseDeptmentDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarBaseDeptmentDO getCarBaseDeptmentByID(Integer id) {
		return carBaseDeptmentMapper.getByID(id);
	}

	/**
	 * 根据carBaseDeptmentDO查询列表
	 *
	 */
	public List<CarBaseDeptmentDO> getCarBaseDeptmentList(CarBaseDeptmentDO carBaseDeptmentDO) {
		return carBaseDeptmentMapper.getList(carBaseDeptmentDO);
	}

	/**
	 * 根据carBaseDeptmentDO查询分页列表
	 *
	 */
	public List<CarBaseDeptmentDO> getCarBaseDeptmentList(CarBaseDeptmentDO carBaseDeptmentDO, PageBounds pageBounds) {
		return carBaseDeptmentMapper.getList(carBaseDeptmentDO, pageBounds);
	}

	/**
	 * 
	 * @Title: getListDeptInfoByCompanyId
	 * @Description: 根据公司ID获取该公司所有部门信息 Service
	 * @param i
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	public List<CarBaseDeptmentDO> getListDeptInfoByCompanyId(Integer i, PageBounds pageBounds) {
		return carBaseDeptmentMapper.getListDeptInfoByCompanyId(i, pageBounds);
	}

	/**
	 * 
	 * @Title: getsearchCarBaseDeptInfo
	 * @Description: 部门信息的全局搜索
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	public List<CarBaseDeptmentDO> getsearchCarBaseDeptInfo(Map<String, Object> paraMap, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return carBaseDeptmentMapper.getsearchCarBaseDeptInfo(paraMap, pageBounds);
	}

	/**
	 * 
	 * @Title: getSelect2ListDeptInfo
	 * @Description: 部门信息下拉框
	 * @param currentUser
	 *            当前用户对象
	 * @param str
	 *            搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @param str
	 * @date: 2017年10月16日 下午10:06:48
	 */
	public List<Select2VO> getSelect2ListDeptInfo(CarSysUserDO currentUser, String str) {
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		List<CarBaseDeptmentDO> deptmentDOs;
		Select2VO select2vo;
		if (StringUtils.isNotBlank(str)) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", currentUser.getCompanyId());
			paraMap.put("str", str);
			deptmentDOs = carBaseDeptmentMapper.getCarBaseDeptmentByStr(paraMap);
		} else {
			deptmentDOs = carBaseDeptmentMapper.getListSelect2DeptInfo(currentUser.getCompanyId());
		}
		for (CarBaseDeptmentDO carBaseDeptmentDO : deptmentDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseDeptmentDO.getId()));
			select2vo.setText(carBaseDeptmentDO.getDeptName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}

	/**
	 * 
	 * @Title: getListDeptInfo 获取部门名称
	 * @Description: TODO 获取部门名称
	 * @param select2vos
	 *            下拉框
	 * @return: List<Select2VO> 结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月19日 上午10:54:46
	 */
	public List<Select2VO> getListDeptInfo(List<Select2VO> select2vos) {
		List<CarBaseDeptmentDO> deptmentDOs = carBaseDeptmentMapper.getListDeptInfo();
		Select2VO select2vo;
		for (CarBaseDeptmentDO carBaseDeptmentDO : deptmentDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseDeptmentDO.getId()));
			select2vo.setText(carBaseDeptmentDO.getDeptName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}

	/**
	 * 
	 * @param i
	 * @Title: getMaxDeptCode
	 * @Description: 获取最大部门编码
	 * @return
	 * @return: String
	 */
	public String getMaxDeptCode(int companyId) {
		try {
			String maxCode = carBaseDeptmentMapper.getMaxDeptCode(companyId);
			int maxDeptCode = Integer.parseInt(maxCode);
			String deptCode = YSTConstants.DEPTCODE + "" + (++maxDeptCode);
			return deptCode;
		} catch (Exception e) {
			logger.error("获取最大值出错", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: deleteDeptInfo
	 * @Description: 删除部门信息
	 * @param currentUser
	 * @param splitIds
	 * @return
	 * @return: String
	 */
	public String deleteDeptInfo(CarSysUserDO currentUser, String[] splitIds) {
		String resultMsg = "删除成功！";
		List<String> reList = new ArrayList<String>();
		List<CarBaseStaffDO> staffDOs = new ArrayList<CarBaseStaffDO>();
		// 封装查询对象
		CarBaseDeptmentDO carBaseDeptmentDO = null;
		CarBaseDeptmentDO eqlevelDeptParam = null;
		CarBaseStaffDO paraBaseStaffDO = new CarBaseStaffDO();
		paraBaseStaffDO.setCompanyID(currentUser.getCompanyId());
		for (String id : splitIds) {
			carBaseDeptmentDO = new CarBaseDeptmentDO();
			// 条条查询部门是否是空部门
			paraBaseStaffDO.setDeptID(Integer.parseInt(id));
			staffDOs = carBaseStaffService.getCarBaseStaffList(paraBaseStaffDO);
			// 查询当前部门信息
			CarBaseDeptmentDO deptmentDo = this.getCarBaseDeptmentByID(Integer.parseInt(id));

			// 如果有员工 不能删除 返回部门编码
			if (staffDOs != null && staffDOs.size() > 0) {
				// 将不能删除的部门信息放入返回list中
				reList.add(deptmentDo.getDeptCode());
			} else {

				carBaseDeptmentDO.setId(Integer.parseInt(id));
				carBaseDeptmentDO.setDeleteCode(YSTConstants.DELETE_CODE_DEL);
				carBaseDeptmentDO.setUpdateOperator(String.valueOf(currentUser.getId()));
				carBaseDeptmentDO.setUpdateTime(new Date());
				carBaseDeptmentMapper.update(carBaseDeptmentDO);

				// 一.更新父节点信息

				eqlevelDeptParam = new CarBaseDeptmentDO();
				eqlevelDeptParam.setCompanyID(deptmentDo.getCompanyID());
				eqlevelDeptParam.setCompanyID(deptmentDo.getCompanyID());
				eqlevelDeptParam.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
				eqlevelDeptParam.setExceptId(Integer.parseInt(id));
				// 1.查询当前节点的同级子节点
				List<CarBaseDeptmentDO> eqLevelDeptList = carBaseDeptmentMapper.getList(eqlevelDeptParam);
				// 2:无同级节点的情况下，将父节点设为更节点
				if (eqLevelDeptList == null || eqLevelDeptList.size() == 0) {
					eqlevelDeptParam = new CarBaseDeptmentDO();
					eqlevelDeptParam.setId(deptmentDo.getCompanyID());
					eqlevelDeptParam.setIsLeaf(YSTConstants.IS_LEAF_TRUE);
					eqlevelDeptParam.setUpdateOperator(String.valueOf(currentUser.getId()));
					eqlevelDeptParam.setUpdateTime(new Date());
					// 3.更新父节点信息
					carBaseDeptmentMapper.update(eqlevelDeptParam);
				}

			}

		}
		if (reList.size() > 0) {
			resultMsg = resultMsg + "除了" + reList.toString();
		}
		return resultMsg;
	}

	public CarBaseDeptmentDO findDeptByid(Integer selectDeptId) {
		// TODO Auto-generated method stub
		return carBaseDeptmentMapper.getByID(selectDeptId);
	}

	/**
	 * @Title: getListDeptInfotree
	 * @Description: TODO 获取部门结构菜单
	 * @param rootId
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 * @author: THINK
	 * @param searchStr 
	 * @date: 2017-12-4 下午11:03:36
	 */
	public List<CarBaseDeptmentDO> getListDeptInfotree(Integer rootId, String searchStr) {
		// TODO Auto-generated method stub
		List<CarBaseDeptmentDO> returnList=new ArrayList<CarBaseDeptmentDO>();
		List<CarBaseDeptmentDO> depList=carBaseDeptmentMapper.getTreeList(rootId,searchStr);
		dealTreeList(returnList, rootId, depList);

		return returnList;
	}

	private void dealTreeList(List<CarBaseDeptmentDO> returnList, Integer parentId, List<CarBaseDeptmentDO> depList) {

		for (int i = 0; i < depList.size(); i++) {
			CarBaseDeptmentDO carBaseDeptmentDO = depList.get(i);
			if (carBaseDeptmentDO.getCompanyID().intValue() == parentId.intValue()) {
				returnList.add(carBaseDeptmentDO);
				depList.remove(i);
				if (!carBaseDeptmentDO.getIsLeaf()) {
					dealTreeList(returnList, carBaseDeptmentDO.getId(), depList);
				}
				i--;
			}
		}
		/*
		 * Iterator<CarBaseDeptmentDO> iterator=depList.iterator();
		 * synchronized(depList) { while (iterator.hasNext()) {
		 * CarBaseDeptmentDO carBaseDeptmentDO = iterator.next();
		 * if(carBaseDeptmentDO.getCompanyID().intValue()==parentId.intValue()){
		 * returnList.add(carBaseDeptmentDO); iterator.remove();
		 * if(!carBaseDeptmentDO.getIsLeaf()){ dealTreeList(returnList,
		 * carBaseDeptmentDO.getId(), depList); } }
		 * 
		 * } }
		 */

		/*
		 * for (CarBaseDeptmentDO carBaseDeptmentDO : depList) {
		 * if(carBaseDeptmentDO.getCompanyID().intValue()==parentId.intValue()){
		 * returnList.add(carBaseDeptmentDO);
		 * 
		 * if(!carBaseDeptmentDO.getIsLeaf()){ dealTreeList(returnList,
		 * carBaseDeptmentDO.getId(), depList); } } }
		 */

	}

	/**
	 * @Title: updateCarBaseDeptment
	 * @Description: TODO更新部门信息
	 * @param currentUser
	 * @param carBaseDeptmentDO
	 * @return: void
	 * @author: THINK
	 * @date: 2017-12-5 上午9:47:15
	 */
	public void updateCarBaseDeptment(CarSysUserDO currentUser, CarBaseDeptmentDO carBaseDeptmentDO) {
		carBaseDeptmentDO.setUpdateOperator(currentUser.getUsername());
		carBaseDeptmentDO.setUpdateTime(new Date());
		carBaseDeptmentMapper.update(carBaseDeptmentDO);

	}

	/**
	 * @Title: getSelect2ListDeptInfoByLevel
	 * @Description: TODO 获取1级和二级菜单列表
	 * @param currentUser
	 * @param levels
	 * @return
	 * @return: List<Select2VO>
	 * @author: THINK
	 * @date: 2017-12-5 下午11:26:07
	 */
	public List<Select2VO> getSelect2ListDeptInfoByLevel(CarSysUserDO currentUser, String levels) {
		// TODO Auto-generated method stub
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("level", levels);
		List<CarBaseDeptmentDO> deptmentDOs = carBaseDeptmentMapper.getCarBaseDeptmentByStr(paraMap);
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		Select2VO select2vo = null;
		for (CarBaseDeptmentDO carBaseDeptmentDO : deptmentDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseDeptmentDO.getId()));
			select2vo.setText(carBaseDeptmentDO.getDeptName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}

	/**
	 * 获取部门车辆树
	 * 
	 * @param carId
	 * @return
	 */
	public List<ZTreeVO> getZTreeDeptment(String carId) {
		List<ZTreeVO> zTreeVOs = new ArrayList<>();
		// 根据申请车辆id获取车辆对象
		CarBaseVehicleDO carDO = carBaseVehicleMapper.getByID(Integer.valueOf(carId));
		// 获取所有部门
		List<CarBaseDeptmentDO> deptmentDOs = carBaseDeptmentMapper.getListDeptInfo();
		// 将部门封装成ZTreeVO
		ZTreeVO deptZTreeVO;
		String iconPath = "../img/police.png";
		for (CarBaseDeptmentDO deptDo : deptmentDOs) {
			// 部门与选择车辆所在部门相同 加入
			if (deptDo.getId().equals(carDO.getDeptID())) {
				deptZTreeVO = new ZTreeVO();
				deptZTreeVO.setId(String.valueOf(deptDo.getId()));
				deptZTreeVO.setpId("0");
				deptZTreeVO.setName(deptDo.getDeptName());
				deptZTreeVO.setOpen(true);
				deptZTreeVO.setIsParent(true);
				deptZTreeVO.setIcon(iconPath);
				zTreeVOs.add(deptZTreeVO);
			}
		}
		for (CarBaseDeptmentDO deptmentDO : deptmentDOs) {
			// 根据部门id获取车辆list
			List<CarDetailListRS> carList = appCarApplyMapper.searchCarByDeptment(Integer.valueOf(deptmentDO.getId()));
			if (carList.size() > 0) {
				deptZTreeVO = new ZTreeVO();
				deptZTreeVO.setId(String.valueOf(deptmentDO.getId()));
				deptZTreeVO.setpId("0");
				deptZTreeVO.setName(deptmentDO.getDeptName());
				deptZTreeVO.setOpen(true);
				deptZTreeVO.setIsParent(true);
				deptZTreeVO.setIcon(iconPath);
				if (zTreeVOs.get(0).equals(deptZTreeVO)) {
					
				}
				zTreeVOs.add(deptZTreeVO);
			}
		}
		return zTreeVOs;
	}

}