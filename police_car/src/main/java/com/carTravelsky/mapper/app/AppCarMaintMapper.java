package com.carTravelsky.mapper.app;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AppCarMaintMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:23:58
 */
public interface AppCarMaintMapper {

	Integer searchMaintainRecodrIsHave(
			@Param("carId")Integer carId, 
			@Param("maintDate")Date maintDate,
			@Param("username")String username);
}
