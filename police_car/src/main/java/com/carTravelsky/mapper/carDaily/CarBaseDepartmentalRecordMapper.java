package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarBaseDepartmentalRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseTrafficRecordsDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseDepartmentalRecordMapper extends BaseMapper<CarBaseDepartmentalRecordDO> {
	 public List<CarBaseDepartmentalRecordDO> getCarBaseDepartmentalAllRecordList(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO, PageBounds pageBounds);
	 public int editDeptChangeInfo(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO);
	 public int updateCarBaseDeptChangeRecord(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO);
	 public int deleteDeptChangeRecord(Integer id);
}