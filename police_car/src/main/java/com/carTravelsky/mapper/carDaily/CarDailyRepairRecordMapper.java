package com.carTravelsky.mapper.carDaily;

import java.util.List;
import java.util.Map;

import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyRepairRecordMapper extends
		BaseMapper<CarDailyRepairRecordDO> {

	List<CarDailyRepairRecordDO> getsearchRepairRecords(
			Map<String, Object> paraMap, PageBounds pageBounds);

	List<CarDailyRepairRecordDO> getListRepairRecordsByCompanyId(int companyId,
			PageBounds pageBounds);

	CarDailyRepairRecordDO getCarDailyRepairRecordByKeyId(
			Map<String, Object> paraMap);

	List<TopRow> getTopList(CarDailyRepairRecordDO carDailyRepairRecordDO,PageBounds pageBounds);

}