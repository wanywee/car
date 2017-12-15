package com.carTravelsky.mapper.app;

import java.util.List;

import com.carTravelsky.bean.app.response.CarAndStaffRS;

/**
 * @ClassName: AppCarStaffMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月5日 下午4:15:00
 */
public interface AppCarStaffMapper {

	List<CarAndStaffRS> getListCar();
	
	List<CarAndStaffRS> getAllStaffList();
}
