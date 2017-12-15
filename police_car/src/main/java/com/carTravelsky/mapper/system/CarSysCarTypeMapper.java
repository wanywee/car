package com.carTravelsky.mapper.system;

import com.carTravelsky.bean.system.CarSysCarTypeDO;
import com.carTravelsky.mapper.BaseMapper;

public interface CarSysCarTypeMapper extends BaseMapper<CarSysCarTypeDO> {

	CarSysCarTypeDO getCarSysCarType(CarSysCarTypeDO carSysCarTypeDO);

}