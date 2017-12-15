package com.carTravelsky.mapper.carDaily;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyRepairTackRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyRepairTackRecordMapper extends BaseMapper<CarDailyRepairTackRecordDO> {

	List<CarDailyRepairTackRecordDO> getsearchCarDailyRepairTack(@Param("searchStr")String searchStr,@Param("carDailyRepairTackRecordDO")CarDailyRepairTackRecordDO carDailyRepairTackRecordDO,PageBounds pageBounds);

}