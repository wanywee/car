package com.carTravelsky.service.app;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.app.response.CarAndStaffRS;
import com.carTravelsky.mapper.app.AppCarStaffMapper;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarStaffService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月5日 下午4:15:14
 */
@Component
public class AppCarStaffService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarStaffMapper appCarStaffMapper;
	
	public List<CarAndStaffRS> getListCar(){
		return appCarStaffMapper.getListCar();
	}

	public List<CarAndStaffRS> getAllStaffInfos() {
		return appCarStaffMapper.getAllStaffList();
	}
}
