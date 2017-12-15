package com.carTravelsky.service.carDaily;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.app.response.CarDetailListRS;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.CarSysCarTypeDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.app.AppCarApplyMapper;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.carTravelsky.service.system.CarSysCarTypeService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

import sun.print.resources.serviceui;

@Component
public class CarBaseVehicleService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseVehicleMapper carBaseVehicleMapper;
	@Autowired
	private AppCarApplyMapper appCarApplyMapper;
	@Autowired
	private CarSysCarTypeService carSysCarTypeService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;	
	/**
	 * 
	 * @Title: saveCarBaseVehicle
	 * @Description: 自定义保存
	 * @param currentUser
	 * @param carBaseVehicleDO
	 * @throws ServiceException
	 * @return: void
	 */
	public void saveCarBaseVehicle(CarSysUserDO currentUser, CarBaseVehicleDO carBaseVehicleDO)
			throws ServiceException {
		try {
			// 新增和修改都需的操作
			Date initDate = new Date();
			carBaseVehicleDO.setUpdateOperator(currentUser.getUsername());
			carBaseVehicleDO.setUpdateTime(initDate);
			// 设置默认状态（前台没有选择则为1 选择则不用设置）
			if (carBaseVehicleDO.getStatus() == null) {
				carBaseVehicleDO.setStatus(YSTConstants.ENABLE);
			}

			// 新增车辆档案 需要将车辆的类型表有一个随着新增的操作（成功后返回新增车辆类型表的主键id，设置到车辆档案的相关属性中）
			CarSysCarTypeDO carSysCarTypeDO = new CarSysCarTypeDO();
			carSysCarTypeDO.setTypeName(carBaseVehicleDO.getTypeName());
			carSysCarTypeDO.setModelName(carBaseVehicleDO.getModelName());
			carSysCarTypeDO.setColorName(carBaseVehicleDO.getColorName());
			carSysCarTypeDO.setBrandName(carBaseVehicleDO.getBrandName());
			// 根据这四个参数去查找车辆类型
			CarSysCarTypeDO carTypeDO = carSysCarTypeService.getCarSysCarType(carSysCarTypeDO);
			if (carTypeDO != null) {
				// 有数据则将id设置到车辆档案实体类
				carBaseVehicleDO.setCarDetailID(carTypeDO.getId());
			} else {// 没有数据则向车辆类型表中插入数据
				// 根据四个车辆类型参数 从字典表中查出各自的code
				KeyCodeMasterDO typeKeyCodeMasterDO = keyCodeMasterService
						.getKeyCodeByDisplay(carSysCarTypeDO.getTypeName());
				KeyCodeMasterDO modelKeyCodeMasterDO = keyCodeMasterService
						.getKeyCodeByDisplay(carSysCarTypeDO.getModelName());
				KeyCodeMasterDO colorKeyCodeMasterDO = keyCodeMasterService
						.getKeyCodeByDisplay(carSysCarTypeDO.getColorName());
				KeyCodeMasterDO brandKeyCodeMasterDO = keyCodeMasterService
						.getKeyCodeByDisplay(carSysCarTypeDO.getBrandName());
				carSysCarTypeDO.setTypeCode(typeKeyCodeMasterDO.getCode());
				carSysCarTypeDO.setModelCode(modelKeyCodeMasterDO.getCode());
				carSysCarTypeDO.setColorCode(colorKeyCodeMasterDO.getCode());
				carSysCarTypeDO.setBrandCode(brandKeyCodeMasterDO.getCode());
				carSysCarTypeDO.setCreator(currentUser.getUsername());
				carSysCarTypeDO.setCreateTime(initDate);
				carSysCarTypeDO.setUpdateOperator(currentUser.getUsername());
				carSysCarTypeDO.setUpdateTime(initDate);
				// 保存车辆类型
				carSysCarTypeService.saveCarSysCarType(carSysCarTypeDO);
				// 设置车辆档案实体的车辆类型id
				carBaseVehicleDO.setCarDetailID(carSysCarTypeDO.getId());
			}

			// 新增操作
			if (carBaseVehicleDO.getId() == null) {
				carBaseVehicleDO.setCreator(currentUser.getUsername());
				carBaseVehicleDO.setCreateTime(initDate);
				carBaseVehicleDO.setCompanyID(carBaseVehicleDO.getDeptID());
				carBaseVehicleMapper.insert(carBaseVehicleDO);
			} else {
				// 根据id查询出老的车辆档案实体
				CarBaseVehicleDO carVehicleDO = getCarBaseVehicleByID(carBaseVehicleDO.getId());
				// 取出之前的图片地址拼接上新地址
				String imgString = carVehicleDO.getPhotoUrl();
				imgString += carBaseVehicleDO.getPhotoUrl();
				carBaseVehicleDO.setPhotoUrl(imgString);
				carBaseVehicleDO.setCompanyID(carBaseVehicleDO.getDeptID());
				carBaseVehicleMapper.update(carBaseVehicleDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @Title: saveCarBaseVehicle
	 * @Description: 保存carBaseVehicleDO
	 * @param carBaseVehicleDO
	 * @throws ServiceException
	 * @return: void
	 */
	public void saveCarBaseVehicle(CarBaseVehicleDO carBaseVehicleDO) throws ServiceException {
		try {
			if (carBaseVehicleDO.getId() == null) {
				carBaseVehicleMapper.insert(carBaseVehicleDO);
			} else {
				carBaseVehicleMapper.update(carBaseVehicleDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarBaseVehicleDO getCarBaseVehicleByID(Integer id) {
		return carBaseVehicleMapper.getByID(id);
	}

	/**
	 * 根据carBaseVehicleDO查询列表
	 *
	 */
	public List<CarBaseVehicleDO> getCarBaseVehicleList(CarBaseVehicleDO carBaseVehicleDO) {
		return carBaseVehicleMapper.getList(carBaseVehicleDO);
	}

	/**
	 * 根据carBaseVehicleDO查询分页列表
	 *
	 */
	public List<CarBaseVehicleDO> getCarBaseVehicleList(CarBaseVehicleDO carBaseVehicleDO, PageBounds pageBounds) {
		return carBaseVehicleMapper.getList(carBaseVehicleDO, pageBounds);
	}

	/**
	 * 
	 * @Title: getsearchCarBaseVehicle
	 * @Description: 车辆档案全局搜索
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlung
	 * @date: 2017年10月13日 下午11:45:51
	 */
	public List<CarBaseVehicleDO> getsearchCarBaseVehicle(Map<String, Object> paraMap, PageBounds pageBounds) {
		return carBaseVehicleMapper.getsearchCarBaseVehicle(paraMap, pageBounds);
	}

	/**
	 * 
	 * @Title: getListVehicleRecordsByCompanyId
	 * @Description: 根据公司ID获取车辆档案信息 Service
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlung
	 * @date: 2017年10月13日 下午11:51:29
	 */
	public List<CarBaseVehicleDO> getListVehicleRecordsByCompanyId(Map<String, Object> paraMap, PageBounds pageBounds) {
		List<CarBaseVehicleDO> carBaseVehicleDOs = carBaseVehicleMapper.getListVehicleRecordsByCompanyId(paraMap,
				pageBounds);
		/*
		 * CarSysCarTypeDO carSysCarTypeDO = new CarSysCarTypeDO(); for
		 * (CarBaseVehicleDO carBaseVehicleDO : carBaseVehicleDOs) { //
		 * 根据车辆档案中的车辆类型ID获取车辆类型实体 carSysCarTypeDO =
		 * carSysCarTypeService.getCarSysCarTypeByID
		 * (carBaseVehicleDO.getCarDetailID()); // 封装车辆档案的车辆类型和型号
		 * carBaseVehicleDO.setCarTypeName(carSysCarTypeDO.getTypeName());
		 * carBaseVehicleDO.setCarModelName(carSysCarTypeDO.getModelName()); }
		 */
		return carBaseVehicleDOs;
	}

	/**
	 * 
	 * @Title: isUseCarBaseVehicle
	 * @Description: 车辆档案是否停用
	 * @param currentUser
	 * @param carBaseVehicleDO
	 * @return: void
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午10:50:10
	 */
	public void isUseCarBaseVehicle(CarSysUserDO currentUser, CarBaseVehicleDO carBaseVehicleDO) {
		try {
			Date initDate = new Date();
			// 不管新增还是修改都需要操作更新人和时间
			carBaseVehicleDO.setUpdateOperator(currentUser.getUsername());
			carBaseVehicleDO.setUpdateTime(initDate);
			carBaseVehicleMapper.update(carBaseVehicleDO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Title: getLicensenosList
	 * @Description: 得到车辆ID对应的车牌号码列表
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: wangyu
	 * @date: 2017年10月26日 上午9:18:31
	 */
	@Transactional
	public List<CarBaseVehicleDO> getLicensenosList(@Param("searchStr") String searchStr,
			@Param("deptId") Integer deptId) {
		return carBaseVehicleMapper.getLicensenosList(searchStr, deptId);
	}

	/**
	 * 
	 * @Title: getCarBaseVehicleByLicenseno
	 * @Description: 根据车牌号得到车辆档案实体
	 * @param licenseno
	 * @return
	 * @return: CarBaseVehicleDO
	 */
	public CarBaseVehicleDO getCarBaseVehicleByLicenseno(String licenseno) {
		// TODO Auto-generated method stub
		return carBaseVehicleMapper.getCarBaseVehicleByLicenseno(licenseno);
	}

	/**
	 * 
	 * @Title: getLicensenosListOut
	 * @Description: 获得车牌号，车状态为出车
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: fuxinrong
	 * @date: 2017年10月31日 上午11:18:17
	 */

	public  List<CarBaseVehicleDO> getLicensenosListOut(CarBaseVehicleDO carBaseVehicleDO,String isOutAndBack,String searchStr){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("companyID", carBaseVehicleDO.getCompanyID());
		paraMap.put("searchStr", searchStr);
		
		//出车  下拉框
		if(StringUtils.isBlank(isOutAndBack)){//车辆状态为 可用
			return carBaseVehicleMapper.getLicensenosListUseable(paraMap);
		}//回车  下拉框
		return carBaseVehicleMapper.getLicensenosListOut(paraMap);
	}
	/**
	 * @Title: getCarBaseVehicleList
	 * @Description: 获取车辆的下拉菜单
	 * @param carBaseVehicleDO
	 * @param searchStr
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: zy
	 * @param userDo
	 * @date: 2017-10-17 下午6:08:30
	 */
	public List<Select2VO> getCarBaseVehicleListForDropdown(CarBaseVehicleDO carBaseVehicleDO, String searchStr,
			CarSysUserDO userDo) {
		carBaseVehicleDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
		carBaseVehicleDO.setCompanyID(userDo.getCompanyId());
		carBaseVehicleDO.setStatus(YSTConstants.ENABLE);
		return carBaseVehicleMapper.getCarBaseVehicleListForDropdown(carBaseVehicleDO, searchStr);
	}

	/**
	 * 
	 * @Title: getCarBaseVehicleThirdParty
	 * @Description: 获取平板车和拖头车数据供第三方使用
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlong
	 * @date: 2017年11月15日 上午10:49:39
	 */
	public List<CarBaseVehicleDO> getCarBaseVehicleThirdParty(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return carBaseVehicleMapper.getCarBaseVehicleThirdParty(paraMap);
	}

	/**
	 * 
	 * @Title: getListCarReport 获取当前页的报表数据
	 * @Description: TODO 获取当前页的报表数据
	 * @param searchStr
	 *            搜索条件
	 * @param pagenum
	 *            条数
	 * @return: List<CarBaseVehicleDO>
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年12月1日 下午12:46:32
	 */
	public List<CarBaseVehicleDO> getListCarReport(String searchStr, int pagenum) {
		return carBaseVehicleMapper.getListCarReport(searchStr, pagenum);

	}

	/**
	 * 
	 * @Title: getListSearchCarReport 获取当前页的搜索报表数据
	 * @Description: TODO 获取当前页的搜索报表数据
	 * @param searchStr
	 *            搜索条件
	 * @param pagenum
	 *            条数
	 * @return: List<CarBaseVehicleDO>
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年12月1日 下午12:46:32
	 */
	public List<CarBaseVehicleDO> getListSearchCarReport(String searchStr, int pagenum) {
		return carBaseVehicleMapper.getListSearchCarReport(searchStr, pagenum);

	}

	/**
	 * 根据部门id获取部门车辆
	 * 
	 * @param deptId
	 * @return
	 */
	public List<ZTreeVO> getZTreeCarListByDeptId(String deptId) {
		List<ZTreeVO> zTreeVOs = new ArrayList<>();
		// 根据部门id获取车辆list
		List<CarDetailListRS> carList = appCarApplyMapper.searchCarByDeptment(Integer.valueOf(deptId));
		ZTreeVO carZTree;
		String iconPath = "../img/car.png";
		for (CarDetailListRS car : carList) {
			carZTree = new ZTreeVO();
			carZTree.setId(String.valueOf(car.getId()));
			String vName = "车牌号:" + car.getLicenseNumber() + " 类型:" + car.getCarType() + " 载人数:"
					+ car.getLoadingNumber() + "\r\n" + "公里数:" + car.getMileNumber() + " 位置:涪陵公安局";
			carZTree.setName(vName);
			carZTree.setpId(deptId);
			carZTree.setOpen(false);
			carZTree.setIsParent(false);
			carZTree.setIcon(iconPath);
			zTreeVOs.add(carZTree);
		}
		return zTreeVOs;
	}

	/**
	 * 
	 * @Title: getCarListall
	 * @Description: TODO获取所有车辆购入时间
	 * @return
	 * @return: List<Date>
	 * @author: bxl  
	 * @date: 2017年12月6日 下午4:52:36
	 */
	public List<CarBaseVehicleDO> getLicenseNoAllList(){
		return carBaseVehicleMapper.getLicenseNoAllList();
		
	}

	/**
	 * 根据部门id查询该部门的车辆信息
	 * @param deptId
	 * @return
	 */
	public List<Select2VO> getSelect2ListCarByDeptId(String deptId) {
		List<Select2VO> select2vos = new ArrayList<>();
		// 根据部门id获取车辆list
		List<CarDetailListRS> carList = appCarApplyMapper.searchCarByDeptment(Integer.valueOf(deptId));
		Select2VO select2vo;
		for (CarDetailListRS car : carList) {
			select2vo = new Select2VO();
			String vName = "车牌号:" + car.getLicenseNumber() + " 类型:" + car.getCarType() + " 载人数:"
					+ car.getLoadingNumber() + "\r\n" + "公里数:" + car.getMileNumber() + " 位置:涪陵公安局";
			select2vo.setId(String.valueOf(car.getId()));
			select2vo.setText(vName);
			select2vos.add(select2vo);
		}
		return select2vos;
	}
	
}