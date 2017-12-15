package com.carTravelsky.mapper.app;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.app.response.GeneralRS;

/**
 * @ClassName: AppCarFeesMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:28:48
 */
public interface AppCarFeesMapper {

	Integer searchFeesainRecodrIsHave(
			@Param("carId")Integer carId, 
			@Param("currentDate")Date insureDate,
			@Param("username")String username);

	List<GeneralRS> getFeesList(@Param("name")String name);
}
