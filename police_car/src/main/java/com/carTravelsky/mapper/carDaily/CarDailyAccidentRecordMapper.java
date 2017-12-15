package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyAccidentRecordMapper extends
		BaseMapper<CarDailyAccidentRecordDO> {

	List<CarDailyAccidentRecordDO> getsearchCarDailyAccidentRecord(
			String searchStr, PageBounds pageBounds);

	/**
	 * @Title: 不完全删除
	 * 
	 */
	int deleteIncomplete(Integer id);

	/**
	 * @Title: getTopList
	 * @Description: TODO 首页提醒  获取驾驶员事故排行榜
	 * @param carDailyAccidentRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<TopRow>
	 * @author: THINK  
	 * @date: 2017-12-11 上午1:01:09
	 */
	List<TopRow> getTopList(CarDailyAccidentRecordDO carDailyAccidentRecordDO,PageBounds pageBounds);
}