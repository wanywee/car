package com.carTravelsky.mapper.carDaily;

import java.util.List;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordHisDO;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyOutRecordHisMapper extends com.carTravelsky.mapper.BaseMapper {

    public int insert(CarDailyOutRecordHisDO carDailyOutRecordHisDO);

    public int update(CarDailyOutRecordHisDO carDailyOutRecordHisDO);

    public int delete(Integer ID);

    public CarDailyOutRecordHisDO getByID(Integer ID);

    public List<CarDailyOutRecordHisDO> getList(CarDailyOutRecordHisDO carDailyOutRecordHisDO);

    public List<CarDailyOutRecordHisDO> getList(CarDailyOutRecordHisDO carDailyOutRecordHisDO, PageBounds pageBounds);

}