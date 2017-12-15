package com.carTravelsky.mapper.app;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.app.response.GeneralRS;

/**
 * @ClassName: AppCarRefueMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:19:17
 */
public interface AppCarRefueMapper {

	Integer searchOilIdByType(@Param("oilType")String oilType);

	Integer searchCarIdByNo(@Param("licenseNumber")String licenseNumber);

	Integer searchCarRecodrIsHave(
			@Param("carId")Integer carId, 
			@Param("userId")Integer userId,
			@Param("currentDate")Date currentDate);

	String checkCarNumberIsHave(@Param("licenseNumber")String licenseNumber);

	List<GeneralRS> getOilsList(@Param("oilName")String oilName);

}
