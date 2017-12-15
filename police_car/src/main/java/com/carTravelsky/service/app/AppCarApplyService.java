package com.carTravelsky.service.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.app.response.CarAndDeptListRS;
import com.carTravelsky.bean.app.response.CarDetailListRS;
import com.carTravelsky.bean.app.response.DeptAndStaffRS;
import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.app.response.LicenseAndStaffRS;
import com.carTravelsky.bean.app.response.StaffListRS;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.mapper.app.AppCarApplyMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarApplyService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午2:09:58
 */
@Component
public class AppCarApplyService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarApplyMapper appCarApplyMapper;
	@Autowired
	private CarDailyOutRecordMapper carDailyOutRecordMapper;
	
	/**
	 * @Title: getCarsListByDeptment
	 * @Description: 部门车辆
	 * @return
	 * @return: CarAndDeptListRS>
	 */
	public List<CarAndDeptListRS> getCarsListByDeptment() {
		List<CarAndDeptListRS> list =  new ArrayList<CarAndDeptListRS>();
		//所有部门集合
		List<GeneralRS> deptments = this.getAllDeptments();
		if(CollectionUtils.isNotEmpty(deptments)){
			for(GeneralRS dept : deptments){
				CarAndDeptListRS carAndDeptListRS = new CarAndDeptListRS();
				carAndDeptListRS.setId(dept.getId());
				carAndDeptListRS.setDeptmentName(dept.getType());
				List<CarDetailListRS> detailList = appCarApplyMapper.searchCarByDeptment(dept.getId());
				if(CollectionUtils.isNotEmpty(detailList)){
					carAndDeptListRS.setDetailList(detailList);
				}
				list.add(carAndDeptListRS);
			}
		}
		return list;
	}

	/**
	 * @Title: getStaffsListByDeptment
	 * @Description: TODO
	 * @return
	 * @return: List<DeptAndStaffRS>
	 */
	public List<DeptAndStaffRS> getStaffsListByDeptment() {
		List<DeptAndStaffRS> list =  new ArrayList<DeptAndStaffRS>();
		//所有部门集合
		List<GeneralRS> deptments = this.getAllDeptments();
		if(CollectionUtils.isNotEmpty(deptments)){
			for(GeneralRS dept : deptments){
				DeptAndStaffRS deptAndStaffRS = new DeptAndStaffRS();
				deptAndStaffRS.setId(dept.getId());
				deptAndStaffRS.setDeptmentName(dept.getType());
				List<StaffListRS> detailList = appCarApplyMapper.searchStaffByDeptment(dept.getId());
				if(CollectionUtils.isNotEmpty(detailList)){
					deptAndStaffRS.setDetailList(detailList);
				}
				list.add(deptAndStaffRS);
			}
		}
		return list;
	}

	/**
	 * @Title: getDriverInfoByLicense
	 * @Description: TODO
	 * @param licenseNumber
	 * @return
	 * @return: List<LicenseAndStaffRS>
	 */
	public List<LicenseAndStaffRS> getDriverInfoByLicense(String licenseNumber) {
		List<LicenseAndStaffRS> list =  new ArrayList<LicenseAndStaffRS>();
		//所有部门集合
		List<GeneralRS> deptments = this.getAllDeptments();
		if(CollectionUtils.isNotEmpty(deptments)){
			for(GeneralRS dept : deptments){
				LicenseAndStaffRS licenseAndStaffRS = new LicenseAndStaffRS();
				licenseAndStaffRS.setId(dept.getId());
				licenseAndStaffRS.setDeptmentName(dept.getType());
				List<GeneralRS> detailList = appCarApplyMapper.searchDriverInfoByLicense(dept.getId(),licenseNumber);
				if(CollectionUtils.isNotEmpty(detailList)){
					licenseAndStaffRS.setDetailList(detailList);
				}
				list.add(licenseAndStaffRS);
			}
		}
		return list;
	}
	
	
	/**
	 * @Title: getAllDeptment
	 * @Description: 所有部门集合
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getAllDeptments(){
		//所有部门集合
		List<GeneralRS> deptments = appCarApplyMapper.getAllDeptments();
		return deptments;
	}

	/**
	 * @Title: applyCarSaveData
	 * @Description: TODO
	 * @param carDailyOutRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int applyCarSaveData(CarDailyOutRecordDO carDailyOutRecordDO,String username) {
		int result = 0;
		try {
			Integer userCarID = appCarApplyMapper
									.checkCurrentIsHave(
											carDailyOutRecordDO.getCaruser(),
											carDailyOutRecordDO.getCarID(),
											carDailyOutRecordDO.getDeleteCode(),
											carDailyOutRecordDO.getCarType(),
											carDailyOutRecordDO.getDeptID());
			if(null == userCarID){
				carDailyOutRecordDO.setCreatePeople(username);
				carDailyOutRecordDO.setCreateTime(DateTools.getCurrentDate());
				return result = this.insertApplyData(carDailyOutRecordDO);
			}
			{
				carDailyOutRecordDO.setId(userCarID);
				carDailyOutRecordDO.setUpdateTime(DateTools.getCurrentDate());
				carDailyOutRecordDO.setUpdatePeople(username);
				return result = this.updateApplyData(carDailyOutRecordDO);
			}
		} catch (Exception e) {
			logger.error("申请用车记录存入数据错误出错//{}....", e);
		}
		return result;
	}

	/**
	 * @Title: updateApplyData
	 * @Description: 申请用车修改
	 * @param carDailyOutRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int updateApplyData(CarDailyOutRecordDO carDailyOutRecordDO) {
		int result=0;
		try {
			result = carDailyOutRecordMapper.update(carDailyOutRecordDO);
		} catch (Exception e) {
			logger.error("申请用车记录修改数据错误出错//{}....", e);
		}
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}

	/**
	 * @Title: insertApplyData
	 * @Description: 申请用车新增
	 * @param carDailyOutRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int insertApplyData(CarDailyOutRecordDO carDailyOutRecordDO) {
		int result=0;
		try {
			 result = carDailyOutRecordMapper.insert(carDailyOutRecordDO);
		} catch (Exception e) {
			logger.error("申请用车故记录存入数据错误出错//{}....", e);
		}
		if(result > 0){
		return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

	/**
	 * @Title: updateRentCarTime
	 * @Description: 续车时间
	 * @param stringToDate
	 * @param valueOf
	 * @param valueOf2
	 * @param caruser
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int updateRentCarTime(Integer hour, Integer deptId,
			Integer carId, String caruser) {
		//获取回车时间
		Date date = appCarApplyMapper.getBackTime(deptId,carId,caruser);
		Date next = DateTools.timeAddHour(DateTools.DATE_PATTERN_DEFAULT, date, hour);
		//System.out.println("----------------"+next);
		int result = carDailyOutRecordMapper
						.updateBackTimeByCondition(
							deptId,
							carId,
							caruser,
							next,
							CodeCondition.POLICE_STATUS);
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}

	/**
	 * 根据车辆id查询车辆信息（重装的）
	 * @param carID
	 * @return
	 */
	public CarDetailListRS getCarsById(Integer carID) {
		// TODO Auto-generated method stub
		return appCarApplyMapper.getCarsById(carID,null);
	}
}
