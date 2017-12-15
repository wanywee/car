package com.carTravelsky.service.carDaily;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverLicenseDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.mapper.carDaily.CarBaseDeptmentMapper;
import com.carTravelsky.mapper.carDaily.CarBaseDriverLicenseMapper;
import com.carTravelsky.mapper.carDaily.CarBaseDriverMapper;
import com.carTravelsky.mapper.carDaily.CarBaseStaffMapper;
import com.carTravelsky.mapper.system.CarSysUserMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;

@Component
public class CarBaseDriverService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseDriverMapper carBaseDriverMapper;
	@Autowired
	private CarBaseStaffMapper carBaseStaffMapper;
	@Autowired
	private CarBaseDriverLicenseMapper carBaseDriverLicenseMapper;
	@Autowired
	private CarBaseDeptmentMapper carBaseDeptmentMapper;
	@Autowired
	private CarSysUserMapper carSysUserMapper;
	/**
	 * 保存carBaseDriverDO
	 *
	 */
	public void saveCarBaseDriver(CarBaseDriverDO carBaseDriverDO)
			throws ServiceException {
		try {
			if (carBaseDriverDO.getId() == null) {
				carBaseDriverMapper.insert(carBaseDriverDO);
			} else {
				carBaseDriverMapper.update(carBaseDriverDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarBaseDriverDO getCarBaseDriverByID(Integer id) {
		return carBaseDriverMapper.getByID(id);
	}

	/**
	 * 根据carBaseDriverDO查询列表
	 *
	 */
	public List<CarBaseDriverDO> getCarBaseDriverList(
			CarBaseDriverDO carBaseDriverDO) {
		return carBaseDriverMapper.getList(carBaseDriverDO);
	}

	/**
	 * 根据carBaseDriverDO查询分页列表
	 *
	 */
	public List<CarBaseDriverDO> getCarBaseDriverList(
			CarBaseDriverDO carBaseDriverDO, PageBounds pageBounds) {
		return carBaseDriverMapper.getList(carBaseDriverDO, pageBounds);
	}

	/**
	 * 
	 * @Title: getCarBaseDriverRecordList 驾驶员档案记录分页显示
	 * @Description: TODO 驾驶员档案记录分页显示
	 * @param carBaseDriverRecordDo
	 *            驾驶员档案bean
	 * @param pageBounds
	 *            分页
	 * @return: List<CarBaseDriverRecordDo> 驾驶员档案
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月13日 上午10:07:46
	 */
	public List<CarBaseDriverRecordDo> getCarBaseDriverRecordList(
			CarBaseDriverRecordDo carBaseDriverRecordDo, PageBounds pageBounds) {
		return carBaseDriverMapper.getCarBaseDriverRecordList(
				carBaseDriverRecordDo, pageBounds);
	}

	/**
	 * 
	 * @Title: getSearchDriverRecordList 全局搜索
	 * @Description: TODO 搜索全局
	 * @param searchStr
	 *            搜索字段
	 * @param pageBounds
	 *            分页
	 * @return: List<CarBaseDriverRecordDo> 搜索结果展示
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月13日 上午10:09:51
	 */
	public List<CarBaseDriverRecordDo> getSearchDriverRecordList(
			String searchStr,CarBaseDriverRecordDo carBaseDriverRecordDo,PageBounds pageBounds) {
		return carBaseDriverMapper.getSearchDriverRecordList(searchStr,carBaseDriverRecordDo,
				pageBounds);
	}

	/**
	 * 
	 * @Title: editCarDriverInfo 保存驾驶员档案信息
	 * @Description: TODO 录入驾驶员档案
	 * @param carBaseDriverRecordDo
	 *            驾驶员信息
	 * @return: result 驾驶员ID
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月13日 下午12:07:54
	 */
	@Transactional
	public int editCarDriverInfo(CarBaseDriverRecordDo carBaseDriverRecordDo)
			throws Exception {
		try {
			if(carBaseDriverRecordDo.getStatus().equals("on")){
				carBaseDriverRecordDo.setStatus("2");
			}else{
				carBaseDriverRecordDo.setStatus("1");
			}
		} catch (Exception e) {
			carBaseDriverRecordDo.setStatus("1");
		}
		// 助记码 部门名字首字母大写 (也是新增才有的操作)
		String mnemonicCode = ChineseLetter.getPinYinHeadChar(
				carBaseDriverRecordDo.getStaffName()).toUpperCase();
		carBaseDriverRecordDo.setMnemonicCode(mnemonicCode);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CarBaseStaffDO carBaseStaffDO= getCarBaseStaffNo(carBaseDriverRecordDo.getStaffNo());
		if(carBaseStaffDO!=null){
			String updateTime = sdf.format(new Date());
			carBaseDriverRecordDo.setUpdateTime(updateTime);
			carBaseDriverRecordDo.setStaffId(carBaseStaffDO.getId());
			carBaseDriverRecordDo.setUpdateOperator(carBaseDriverRecordDo.getCreator());
			carBaseStaffMapper.updateBaseStaffById(carBaseDriverRecordDo);
			
		}else {
			String createTime = sdf.format(new Date());
			carBaseDriverRecordDo.setCreateTime(createTime);
			// 增加驾驶员工并返回ID
			carBaseStaffMapper.addDriverStaffInfo(carBaseDriverRecordDo);
		}
		// 增加驾驶员并返回ID
		carBaseDriverMapper.addDriverContactInfo(carBaseDriverRecordDo);
		if (carBaseDriverRecordDo.getDriverLicense() != null
				&& !carBaseDriverRecordDo.getDriverLicense().equals("")) {
			carBaseDriverLicenseMapper
					.addDriverLicenseContactInfo(carBaseDriverRecordDo);
		}
		if (carBaseDriverRecordDo.getDriverLicense1() != null
				&& !carBaseDriverRecordDo.getDriverLicense1().equals("")) {
			carBaseDriverLicenseMapper
					.addDriverLicenseContactInfoOne(carBaseDriverRecordDo);
		}
		if (carBaseDriverRecordDo.getDriverLicense2() != null
				&& !carBaseDriverRecordDo.getDriverLicense2().equals("")) {
			carBaseDriverLicenseMapper
					.addDriverLicenseContactInfoTwo(carBaseDriverRecordDo);
		}
		return carBaseDriverRecordDo.getId();
		// return carBaseDriverMapper.editCarDriverInfo(carBaseDriverRecordDo);
	}

	/**
	 * 
	 * @Title: getCarDriverEditInfoByID 通过ID查询出信息
	 * @Description: TODO ID查询
	 * @param id
	 *            驾驶员ID
	 * @return: CarBaseDriverRecordDo 查询的结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月16日 上午9:17:13
	 */
	public CarBaseDriverRecordDo getCarDriverEditInfoByID(Integer id) {
		CarBaseDriverRecordDo carBaseDriverRecordDo = new CarBaseDriverRecordDo();
		try {
			
			// 查询驾驶员通过ID
			CarBaseDriverDO carBaseDriverDO = carBaseDriverMapper.getByID(id);
			// 查询职员信息通过WORKID
			CarBaseStaffDO carBaseStaffDO = carBaseStaffMapper
					.getByID(carBaseDriverDO.getWorkID());
			// 查询驾照信息通过ID
			List<CarBaseDriverLicenseDO> carBaseDriverLicenseDOs = carBaseDriverLicenseMapper
					.getCarBaseDriverLicenseByID(id);
			//查询部门名称
			CarBaseDeptmentDO carBaseDeptmentDO=carBaseDeptmentMapper.getByID(carBaseStaffDO.getDeptID());
			carBaseDriverRecordDo.setDept(carBaseDeptmentDO.getDeptName());
			carBaseDriverRecordDo.setDeptmentId(carBaseDeptmentDO.getId());
			carBaseDriverRecordDo.setId(carBaseDriverDO.getId());
			carBaseDriverRecordDo.setStaffNo(carBaseStaffDO.getStaffNo());
			carBaseDriverRecordDo
			.setMnemonicCode(carBaseDriverDO.getMnemonicCode());
			if(carBaseDriverDO.getDriverType()==1){
				carBaseDriverRecordDo.setDrivertypeName("兼职");
			}else if (carBaseDriverDO.getDriverType()==2) {
				carBaseDriverRecordDo.setDrivertypeName("专职");
			}
			carBaseDriverRecordDo.setDriverType(carBaseDriverDO.getDriverType().toString());
			carBaseDriverRecordDo
			.setProfessional(carBaseDriverDO.getProfessional());
			carBaseDriverRecordDo.setFromThe(carBaseDriverDO.getFromThe());
			carBaseDriverRecordDo.setStatus(carBaseDriverDO.getStatus().toString());
			carBaseDriverRecordDo.setComments(carBaseDriverDO.getComments());
			Iterator<CarBaseDriverLicenseDO> it = carBaseDriverLicenseDOs
					.iterator();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDateTime = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			
			while (it.hasNext()) {
				CarBaseDriverLicenseDO carBaseDriverLicenseDO = (CarBaseDriverLicenseDO) it
						.next();
				if (carBaseDriverLicenseDO.getLicenceType() == 1) {
					carBaseDriverRecordDo.setLicenseId(carBaseDriverLicenseDO
							.getId());
					carBaseDriverRecordDo.setDriverLicense(carBaseDriverLicenseDO
							.getDriverLicense());
					carBaseDriverRecordDo.setGetTime(sdf
							.format(carBaseDriverLicenseDO.getGetTime()));
					carBaseDriverRecordDo.setValidTime(sdf
							.format(carBaseDriverLicenseDO.getValidTime()));
					carBaseDriverRecordDo.setLicenceIssuing(carBaseDriverLicenseDO
							.getLicenceIssuing());
					carBaseDriverRecordDo.setDrivingType(carBaseDriverLicenseDO
							.getDrivingType());
					carBaseDriverRecordDo.setInspectionTime(sdf
							.format(carBaseDriverLicenseDO.getInspectionTime()));
				}
				if (carBaseDriverLicenseDO.getLicenceType() == 2) {
					carBaseDriverRecordDo.setLicenseId1(carBaseDriverLicenseDO
							.getId());
					carBaseDriverRecordDo.setDriverLicense1(carBaseDriverLicenseDO
							.getDriverLicense());
					carBaseDriverRecordDo.setGetTime1(sdf
							.format(carBaseDriverLicenseDO.getGetTime()));
					carBaseDriverRecordDo.setValidTime1(sdf
							.format(carBaseDriverLicenseDO.getValidTime()));
					carBaseDriverRecordDo.setLicenceIssuing1(carBaseDriverLicenseDO
							.getLicenceIssuing());
					carBaseDriverRecordDo.setDrivingType1(carBaseDriverLicenseDO
							.getDrivingType());
					carBaseDriverRecordDo.setInspectionTime1(sdf
							.format(carBaseDriverLicenseDO.getInspectionTime()));
				}
				if (carBaseDriverLicenseDO.getLicenceType() == 3) {
					carBaseDriverRecordDo.setLicenseId2(carBaseDriverLicenseDO
							.getId());
					carBaseDriverRecordDo.setDriverLicense2(carBaseDriverLicenseDO
							.getDriverLicense());
					carBaseDriverRecordDo.setGetTime2(sdf
							.format(carBaseDriverLicenseDO.getGetTime()));
					carBaseDriverRecordDo.setValidTime2(sdf
							.format(carBaseDriverLicenseDO.getValidTime()));
					// carBaseDriverRecordDo.setLicenceIssuing(carBaseDriverLicenseDO.getLicenceIssuing());
					carBaseDriverRecordDo.setDrivingType2(carBaseDriverLicenseDO
							.getDrivingType());
					carBaseDriverRecordDo.setInspectionTime2(sdf
							.format(carBaseDriverLicenseDO.getInspectionTime()));
				}
			}
			carBaseDriverRecordDo.setStaffName(carBaseStaffDO.getStaffName());
			carBaseDriverRecordDo.setStaffCode(carBaseStaffDO.getStaffCode());
			carBaseDriverRecordDo.setBirth(carBaseStaffDO.getStaffBirthday());
			carBaseDriverRecordDo.setStaffSex(carBaseStaffDO.getStaffSex()
					.toString());
			carBaseDriverRecordDo.setStaffNative(carBaseStaffDO.getStaffNative());
			carBaseDriverRecordDo.setStaffPhone(carBaseStaffDO.getStaffPhone());
			carBaseDriverRecordDo.setNation(carBaseStaffDO.getStaffNation());
			carBaseDriverRecordDo.setEmail(carBaseStaffDO.getStaffEmail());
			carBaseDriverRecordDo.setDuty(carBaseStaffDO.getStaffDuty());
//		carBaseDriverRecordDo.setDept(carBaseStaffDO.getDeptID().toString());
			carBaseDriverRecordDo.setAddress(carBaseStaffDO.getAddress());
			carBaseDriverRecordDo.setEducation(carBaseStaffDO.getEducation());
			carBaseDriverRecordDo.setEntryTime(carBaseStaffDO
					.getEntryTime().substring(0,19));
			carBaseDriverRecordDo.setIdCard(carBaseStaffDO.getIdcard());
			carBaseDriverRecordDo.setGraduteSchool(carBaseStaffDO
					.getGraduateSchool());
			carBaseDriverRecordDo.setLocation(carBaseStaffDO.getLocation());
			carBaseDriverRecordDo.setStaffId(carBaseStaffDO.getId());
		} catch (Exception e) {
			logger.info("驾驶员显示错误");
		}
		return carBaseDriverRecordDo;
	}

	/**
	 * 
	 * @Title: updateCarDriverInfo 修改驾驶员信息
	 * @Description: TODO 修改驾驶员信息
	 * @param carBaseDriverRecordDo
	 *            驾驶员信息
	 * @return: result 修改成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月16日 下午1:44:27
	 */
	public int updateCarDriverInfo(CarBaseDriverRecordDo carBaseDriverRecordDo) {
		try {
			if(carBaseDriverRecordDo.getStatus().equals("on")){
				carBaseDriverRecordDo.setStatus("2");
			}else{
				carBaseDriverRecordDo.setStatus("1");
			}
		} catch (Exception e) {
			carBaseDriverRecordDo.setStatus("1");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateTime = sdf.format(new Date());
		carBaseDriverRecordDo.setUpdateTime(updateTime);
		carBaseDriverMapper.updateBaseDriverById(carBaseDriverRecordDo);
		if (carBaseDriverRecordDo.getLicenseId() != null
				&& !carBaseDriverRecordDo.getLicenseId().equals("")) {
			carBaseDriverLicenseMapper
					.updateCarBaseDriverLicenseById(carBaseDriverRecordDo);
		} else {
			if (carBaseDriverRecordDo.getDriverLicense() != null
					&& carBaseDriverRecordDo.getDriverLicense().equals("")) {
				carBaseDriverLicenseMapper
						.addDriverLicenseContactInfo(carBaseDriverRecordDo);
			}
		}
		if (carBaseDriverRecordDo.getLicenseId1() != null
				&& !carBaseDriverRecordDo.getLicenseId1().equals("")) {
			carBaseDriverLicenseMapper
					.updateCarBaseDriverLicenseById(carBaseDriverRecordDo);
		} else {
			if (carBaseDriverRecordDo.getDriverLicense1() != null
					&& !carBaseDriverRecordDo.getDriverLicense1().equals("")) {
				carBaseDriverLicenseMapper
						.addDriverLicenseContactInfoOne(carBaseDriverRecordDo);
			}
		}
		if (carBaseDriverRecordDo.getLicenseId2() != null
				&& !carBaseDriverRecordDo.getLicenseId2().equals("")) {
			carBaseDriverRecordDo.setLicenseId(carBaseDriverRecordDo
					.getLicenseId2());
			carBaseDriverLicenseMapper
					.updateCarBaseDriverLicenseById(carBaseDriverRecordDo);
		} else {
			if (carBaseDriverRecordDo.getDriverLicense2() != null
					&& !carBaseDriverRecordDo.getDriverLicense2().equals("")) {
				carBaseDriverLicenseMapper
						.addDriverLicenseContactInfoTwo(carBaseDriverRecordDo);
			}
		}
		carBaseStaffMapper.updateBaseStaffById(carBaseDriverRecordDo);
	
		return carBaseDriverRecordDo.getId();
	}

	/**
	 * 
	 * @Title: deleteCarDriverInfoById 删除驾驶员信息
	 * @Description: TODO 删除驾驶员信息记录
	 * @param ids
	 *            删除的选中行数
	 * @return: result 删除的结果:1成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月16日 下午4:08:00
	 */
	public int deleteCarDriverInfoById(String[] ids) {
		List<String> listIds = Arrays.asList(ids);
		Iterator<String> iterator = listIds.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id = Integer.parseInt(string);
			carBaseDriverMapper.deleteCarDriverInfoById(id);
		}
		return 1;
	}

	/**
	 * @Title: getListDriverName
	 * @Description:得到附带驾驶员姓名的驾驶员档案列表
	 * @return
	 * @return: List<CarBaseDriverDO>
	 * @author: wangyu
	 * @date: 2017年10月18日 上午9:39:24
	 */
	@Transactional
	public List<CarBaseDriverDO> getListDriverName(@Param("searchStr")String searchStr) {
		return carBaseDriverMapper.getListDriverName(searchStr);
	}
	/**
	 * 
	 * @Title: getCarBaseStaffNo 工号唯一性
	 * @Description: TODO 工号唯一性
	 * @param staffNo 工号
	 * @return: CarBaseDriverRecordDo 工号唯一性
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月24日 下午3:47:14
	 */
	public CarBaseStaffDO getCarBaseStaffNo(String staffNo){
		return carBaseStaffMapper.getCarBaseStaffNo(staffNo);
	}
	/**
	 * 
	 * @Title: isUseCarDriverRecords 修改驾驶员状态
	 * @Description: TODO 修改驾驶员状态
	 * @param carBaseDriverDO 驾驶员
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:51:29
	 */
	public void isUseCarDriverRecords(CarBaseDriverDO carBaseDriverDO){
		carBaseDriverDO.setUpdateTime(new Date());
		carBaseDriverMapper.isUseCarDriverRecords(carBaseDriverDO);
	}
}