package com.carTravelsky.mapper.app;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AppCarAccidentMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:21:34
 */
public interface AppCarAccidentMapper {

	Integer searchCarIdByNumber(@Param("licenseNumber")String licenseNumber);

	Integer searchAccidentIsHave(
			@Param("carID")Integer carID, 
			@Param("driver")String driver, 
			@Param("accidentDate")Date accidentDate);
 
}
