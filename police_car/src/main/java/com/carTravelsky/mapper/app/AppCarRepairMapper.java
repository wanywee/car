package com.carTravelsky.mapper.app;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: AppCarRepairMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 下午5:51:19
 */
public interface AppCarRepairMapper {

	Integer searchRepairIsHave(
			@Param("carID")Integer carID, 
			@Param("handler")String handler, 
			@Param("repairTime")Date repairTime);

}
