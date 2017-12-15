package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarBaseTrafficRecordsDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseTrafficRecordsMapper extends BaseMapper<CarBaseTrafficRecordsDO> {
	 public List<CarBaseTrafficRecordsDO> getCarBaseTrafficAllRecordList(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO, PageBounds pageBounds);
	 public int editTrafficInfo(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO);
	 public int updateCarBaseTrafficRecord(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO);
	 public int deleteTrafficRecord(Integer id);
}