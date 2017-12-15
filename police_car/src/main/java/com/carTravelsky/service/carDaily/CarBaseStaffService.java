package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.mapper.carDaily.CarBaseDeptmentMapper;
import com.carTravelsky.mapper.carDaily.CarBaseDriverMapper;
import com.carTravelsky.mapper.carDaily.CarBaseStaffMapper;
import com.carTravelsky.mapper.system.CarSysUserMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;

@Component
public class CarBaseStaffService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseStaffMapper carBaseStaffMapper;
	@Autowired
	private CarBaseDeptmentMapper carBaseDeptmentMapper;
	@Autowired
	private CarBaseDriverMapper carBaseDriverMapper;
	@Autowired
	private CarSysUserMapper carSysUserMapper;
	/**
	 * 保存carBaseStaffDO
	 *
	 */
	public void saveCarBaseStaff(CarBaseStaffDO carBaseStaffDO)
			throws ServiceException {
		try {
			if (carBaseStaffDO.getId() == null) {
				carBaseStaffMapper.insert(carBaseStaffDO);
			} else {
				carBaseStaffMapper.update(carBaseStaffDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarBaseStaffDO getCarBaseStaffByID(Integer id) {
		CarBaseStaffDO carBaseStaffDO=carBaseStaffMapper.getByID(id);
		//查询部门名称
		//CarBaseDeptmentDO carBaseDeptmentDO=carBaseDeptmentMapper.getByID(carBaseStaffDO.getDeptID());
		//carBaseStaffDO.setDeptName(carBaseDeptmentDO.getDeptName());
		//carBaseStaffDO.setEntryTime(carBaseStaffDO.getEntryTime().substring(0,19));
		return carBaseStaffDO;
	}

	/**
	 * 根据carBaseStaffDO查询列表
	 *
	 */
	public List<CarBaseStaffDO> getCarBaseStaffList(
			CarBaseStaffDO carBaseStaffDO) {
		return carBaseStaffMapper.getList(carBaseStaffDO);
	}

	/**
	 * 根据carBaseStaffDO查询分页列表
	 *
	 */
	public List<CarBaseStaffDO> getCarBaseStaffList(
			CarBaseStaffDO carBaseStaffDO, PageBounds pageBounds) {
		return carBaseStaffMapper.getList(carBaseStaffDO, pageBounds);
	}

	/**
	 * 
	 * @Title: getListStaffByCompanyId
	 * @Description: 根据公司ID查询所有职员信息
	 * @param i
	 * @param select2vos
	 * @param str 
	 * @return
	 * @return: List<Select2VO>
	 */
	public List<Select2VO> getSelect2ListStaff(CarSysUserDO currentUser, String str, Integer deptId) {
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		// 根据公司id加载职工
		List<CarBaseStaffDO> listBaseStaffDOs;
		Select2VO select2vo;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", currentUser.getCompanyId());
		if(deptId != -1000){
			paraMap.put("deptId", deptId);
		}
		if (StringUtils.isNotBlank(str)) {
			paraMap.put("str", str);
			
			listBaseStaffDOs = carBaseStaffMapper.getListStaffByStr(paraMap);
		} else {
			listBaseStaffDOs = carBaseStaffMapper.getListStaffByCompanyId(paraMap);
		}
		// 包装select2 列表对象
		for (CarBaseStaffDO carBaseStaffDO : listBaseStaffDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseStaffDO.getId()));
			select2vo.setText(carBaseStaffDO.getStaffName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}
	/**
	 * 
	 * @Title: getListStaffByCompanyId
	 * @Description: 根据公司ID查询所有职员信息
	 * @param i
	 * @param select2vos
	 * @param str 
	 * @return
	 * @return: List<Select2VO>
	 */
	public List<Select2VO> getSelect2ListStaff(CarSysUserDO currentUser, String str) {
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		// 根据公司id加载职工
		List<CarBaseStaffDO> listBaseStaffDOs;
		Select2VO select2vo;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", currentUser.getCompanyId());
		if (StringUtils.isNotBlank(str)) {
			paraMap.put("str", str);
			
			listBaseStaffDOs = carBaseStaffMapper.getListStaffByStr(paraMap);
		} else {
			listBaseStaffDOs = carBaseStaffMapper.getListStaffByCompanyId(paraMap);
		}
		// 包装select2 列表对象
		for (CarBaseStaffDO carBaseStaffDO : listBaseStaffDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseStaffDO.getId()));
			select2vo.setText(carBaseStaffDO.getStaffName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}


	/**
	 * 
	 * @Title: getListDriverStaffByCompanyId
	 * @Description: 有驾驶证的职员
	 * @param companyId
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @param select2vos 
	 * @param str 
	 * @date: 2017年10月16日 下午10:30:41
	 */
	public List<Select2VO> getListDriverStaffByCompanyId(CarSysUserDO currentUser, String str) {
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		List<CarBaseStaffDO> staffDOs = new ArrayList<CarBaseStaffDO>();
		Select2VO select2vo;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", 53);
		if (StringUtils.isNotBlank(str)) {
			paraMap.put("str", str);
			staffDOs = carBaseStaffMapper.getListSelect2DriverByStr(paraMap);
		} else {
			staffDOs = carBaseStaffMapper.getListSelect2Driver(paraMap);
		}
		for (CarBaseStaffDO carBaseStaffDO : staffDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseStaffDO.getId()));
			select2vo.setText(carBaseStaffDO.getStaffName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}
	/**
	 * 
	 * @Title: getCarBaseAllStaffList 获取所有staff列表
	 * @Description: TODO 获取所有staff列表
	 * @param carBaseStaffDO staff对象
	 * @param pageBounds	分页
	 * @return: List<CarBaseStaffDO> 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 上午10:23:19
	 */
	public List<CarBaseStaffDO> getCarBaseAllStaffList(
			CarBaseStaffDO carBaseStaffDO, PageBounds pageBounds) {
		return carBaseStaffMapper.getCarBaseAllStaffList(carBaseStaffDO, pageBounds);
	}
	/**
	 * 
	 * @Title: addStaffInformation 添加职员信息
	 * @Description: TODO 添加职员信息
	 * @param carBaseStaffDO 职员信息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:29:18
	 */
	public int addStaffInformation(CarBaseStaffDO carBaseStaffDO){
		try {
			if(carBaseStaffDO.getStaffStatus().equals("on")){
				carBaseStaffDO.setStatus(2);
			}else{
				carBaseStaffDO.setStatus(1);
			}
		} catch (Exception e) {
			carBaseStaffDO.setStatus(1);
		}
		// 助记码 部门名字首字母大写 (也是新增才有的操作)
		String mnemonicCode = ChineseLetter.getPinYinHeadChar(
				carBaseStaffDO.getStaffName()).toUpperCase();
		carBaseStaffDO.setMnemonicCode(mnemonicCode);
		 carBaseStaffDO.setCreateTime(new Date());
		return carBaseStaffMapper.addStaffInformation(carBaseStaffDO);
	}
	/**
	 * 
	 * @Title: updateStaffInformation 修改职员信息
	 * @Description: TODO 修改职员信息
	 * @param carBaseStaffDO 职员信息
	 * @return: result 影响行数
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:36:54
	 */
	public int updateStaffInformation(CarBaseStaffDO carBaseStaffDO){
		try {
			if(carBaseStaffDO.getStaffStatus().equals("on")){
				carBaseStaffDO.setStatus(2);
			}else{
				carBaseStaffDO.setStatus(1);
			}
		} catch (Exception e) {
			carBaseStaffDO.setStatus(1);
		}
		 carBaseStaffDO.setUpdateTime(new Date());
		 CarBaseDriverDO carBaseDriverDO=new CarBaseDriverDO();
		 carBaseDriverDO.setWorkID(carBaseStaffDO.getId());
		 carBaseDriverDO.setUpdateTime(new Date());
		 carBaseDriverDO.setStatus(carBaseStaffDO.getStatus());
		 carBaseDriverDO.setUpdateOperator(carBaseStaffDO.getUpdateOperator());
		 carBaseDriverMapper.isUseCarDriverRecordsByStaffId(carBaseDriverDO);
		 CarSysUserDO carSysUserDO =carSysUserMapper.queryUserIdIsExit(carBaseStaffDO.getId());
			if(carSysUserDO!=null){
				carSysUserMapper.updateSameDeptIdByUserId(carBaseStaffDO.getId(),carBaseStaffDO.getDeptID());
			}
		return carBaseStaffMapper.updateStaffInformation(carBaseStaffDO);
	}
	/**
	 * 
	 * @Title: deleteStaffInformationInfoById 删除职员信息通过id
	 * @Description: TODO	删除职员信息
	 * @param ids	选中的ID
	 * @return: result 返回的结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:42:33
	 */
	public int deleteStaffInformationInfoById(String[] ids) {
		List<String> listIds = Arrays.asList(ids);
		Iterator<String> iterator = listIds.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id = Integer.parseInt(string);
			carBaseStaffMapper.deleteStaffInformationInfoById(id);
			carBaseDriverMapper.deleteCarDriverInfoByStaffId(id);
			
		}
		return 1;
	}
	public List<CarBaseDriverDO> queryStaffIsDriver(String[] ids){
		List<String> listIds = Arrays.asList(ids);
		Iterator<String> iterator = listIds.iterator();
		List<CarBaseDriverDO> carBaseDriverDOs=new ArrayList<CarBaseDriverDO>();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id = Integer.parseInt(string);
			CarBaseDriverDO carBaseDriverDO=carBaseDriverMapper.queryStaffIsDriver(id);
			if(carBaseDriverDO!=null){
				carBaseDriverDOs.add(carBaseDriverDO);
			}
		}
		return carBaseDriverDOs;
	}
	/**
	 * 
	 * @Title: getSearchDriverRecordList 全局搜索
	 * @Description: TODO	全局搜索
	 * @param searchString 搜索字符串
	 * @param pageBounds	分页
	 * @return: List<CarBaseStaffDO> 显示搜索职员
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午3:37:53
	 */
	public List<CarBaseStaffDO> getsearchBaseStaffInfo(
			String searchString,CarBaseStaffDO carBaseStaffDO,PageBounds pageBounds){
		return carBaseStaffMapper.getsearchBaseStaffInfo(searchString,carBaseStaffDO,pageBounds);
	}
	/**
	 * 
	 * @Title: isUseStaffInfoRecords 修改启用状态
	 * @Description: TODO 修改启用状态
	 * @param carBaseStaffDO 修改启用状态
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:12:54
	 */
	public void isUseStaffInfoRecords(CarBaseStaffDO carBaseStaffDO,CarBaseDriverDO carBaseDriverDO){
		carBaseStaffDO.setUpdateTime(new Date());
		carBaseDriverDO.setUpdateTime(new Date());
		carBaseStaffMapper.isUseStaffInfoRecords(carBaseStaffDO);
		carBaseDriverMapper.isUseCarDriverRecordsByStaffId(carBaseDriverDO);
		
	}
	/**
	 * 
	 * @Title: getCarBaseStaffByStaffID 查询部门名称
	 * @Description: TODO 查询部门名称
	 * @param id 部门ID
	 * @return: CarBaseStaffDO 部门
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年11月15日 上午9:29:11
	 */
	public CarBaseStaffDO getCarBaseStaffByStaffID(Integer id) {
		CarBaseStaffDO carBaseStaffDO=carBaseStaffMapper.getByID(id);
		//查询部门名称
		CarBaseDeptmentDO carBaseDeptmentDO=carBaseDeptmentMapper.getByID(carBaseStaffDO.getDeptID());
		carBaseStaffDO.setDeptName(carBaseDeptmentDO.getDeptName());
		carBaseStaffDO.setEntryTime(carBaseStaffDO.getEntryTime().substring(0,19));
		return carBaseStaffDO;
	}

	/**
	 * 根据部门id查询驾驶员
	 * @param currentUser 
	 * @param deptId
	 * @param str
	 * @return
	 */
	public List<Select2VO> getListDriverStaffByDeptId(CarSysUserDO currentUser, String deptId, String str) {
		List<Select2VO> select2vos = new ArrayList<Select2VO>();
		List<CarBaseStaffDO> staffDOs = new ArrayList<CarBaseStaffDO>();
		Select2VO select2vo;
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", currentUser.getCompanyId());
		paraMap.put("deptId", deptId);
		if (StringUtils.isNotBlank(str)) {
			paraMap.put("str", str);
			staffDOs = carBaseStaffMapper.getListSelect2DriverByStr(paraMap);
		} else {
			staffDOs = carBaseStaffMapper.getListSelect2Driver(paraMap);
		}
		for (CarBaseStaffDO carBaseStaffDO : staffDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseStaffDO.getId()));
			select2vo.setText(carBaseStaffDO.getStaffName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}
}