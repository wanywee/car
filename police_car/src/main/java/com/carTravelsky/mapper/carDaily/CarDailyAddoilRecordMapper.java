package com.carTravelsky.mapper.carDaily;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyAddoilRecordMapper extends BaseMapper<CarDailyAddoilRecordDO> {

	void updateDeleteCode(List<String> idList);

	List<CarDailyAddoilRecordDO> getSearchAddoilRecordList(@Param("searchStr")String searchStr, PageBounds pageBounds,@Param("companyId")Integer companyId);

	/**
	 * @Title: getTopList
	 * @Description: TODO 首页提醒 获取车辆加油记录排行榜
	 * @param carDailyAddoilRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<TopRow>
	 * @author: THINK  
	 * @date: 2017-12-11 上午1:04:55
	 */
	List<TopRow> getTopList(CarDailyAddoilRecordDO carDailyAddoilRecordDO,PageBounds pageBounds);
}