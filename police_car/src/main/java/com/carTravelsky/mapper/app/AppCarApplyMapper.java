package com.carTravelsky.mapper.app;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.app.response.CarDetailListRS;
import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.app.response.StaffListRS;

/**
 * @ClassName: AppCarApplyMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午2:10:42
 */
public interface AppCarApplyMapper {

	/**
	 * @Title: getAllDeptments
	 * @Description: TODO
	 * @return
	 * @return: List<String>
	 */
	List<GeneralRS> getAllDeptments();

	/**
	 * @Title: searchCarByDeptment
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<CarDetailListRS>
	 */
	List<CarDetailListRS> searchCarByDeptment(@Param("id")Integer id);

	/**
	 * @Title: searchStaffByDeptment
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: List<StaffListRS>
	 */
	List<StaffListRS> searchStaffByDeptment(@Param("id")Integer id);

	/**
	 * @Title: searchDriverInfoByLicense
	 * @Description: TODO
	 * @param id
	 * @param licenseNumber
	 * @return
	 * @return: List<GeneralRS>
	 */
	List<GeneralRS> searchDriverInfoByLicense(
			@Param("id")Integer id, 
			@Param("licenseNumber")String licenseNumber);

	/**
	 * @Title: checkCurrentIsHave
	 * @Description: TODO
	 * @param caruser
	 * @param carID
	 * @param deleteCode
	 * @param carType
	 * @return
	 * @return: String
	 */
	Integer checkCurrentIsHave(
			@Param("caruser")String caruser, 
			@Param("carID")Integer carID,
			@Param("deleteCode")Integer deleteCode, 
			@Param("carType")Integer carType,
			@Param("deptId")Integer deptId);

	/**
	 * @Title: getBackTime
	 * @Description: TODO
	 * @param deptId
	 * @param carId
	 * @param caruser
	 * @return
	 * @return: Date
	 */
	Date getBackTime(
			@Param("deptId")Integer deptId, 
			@Param("carId")Integer carId, 
			@Param("caruser")String caruser);

	CarDetailListRS getCarsById(@Param("id")Integer id,@Param("carStatusApplying") Integer carStatusApplying);

}
