package com.carTravelsky.mapper.carDaily;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyMaintainRecordMapper extends BaseMapper<CarDailyMaintainRecordDO> {

	List<CarDailyMaintainRecordDO> getSearchCarDailyMaintainList(@Param("searchStr")String searchStr, PageBounds bounds,@Param("companyId")Integer companyId);
	List<CarDailyMaintainRecordDO> getSearchCarDailyMaintainExpireList(@Param("searchStr")String searchStr, PageBounds bounds,@Param("companyId")Integer companyId,@Param("remindDate")int remindDate);
	void deleteCarDailyMaintain(List<String> idList);
	//统计保养到期记录
	int ExpireCount(@Param("companyId")int companyId,@Param("remindDate")int remindDate);
	//统计保养到期记录by部门
	int ExpireCountById(@Param("remindDate")int remindDate,PageBounds pageBounds);
	List<CarDailyMaintainRecordDO> ExpireList(@Param("carDailyMaintainRecordDO")CarDailyMaintainRecordDO carDailyMaintainRecordDO,PageBounds pageBounds,@Param("remindDate")int remindDate);
	/**
	 * @Title: getTopList
	 * @Description: TODO  获取保养记录排行榜
	 * @param carDailyMaintainRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<TopRow>
	 * @author: zy  
	 * @date: 2017-12-10 下午11:42:17
	 */
	List<TopRow> getTopList(CarDailyMaintainRecordDO carDailyMaintainRecordDO,PageBounds pageBounds);


}