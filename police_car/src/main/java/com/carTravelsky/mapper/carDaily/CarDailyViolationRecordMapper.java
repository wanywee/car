package com.carTravelsky.mapper.carDaily;

import java.util.List;  
import java.util.Map;

import com.carTravelsky.bean.carDaily.CarDailyViolationRecordDO;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyViolationRecordMapper extends BaseMapper<CarDailyViolationRecordDO> {

	/**
	 * @Title: getListViolationRecordsByCompanyId
	 * @Description: 根据公司ID获得全部违章记录
	 * @param companyId
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyViolationRecordDO>
	 * @author: lipengcheng  
	 * @date: 2017年11月2日 下午2:23:52
	 */
	public List<CarDailyViolationRecordDO> getListViolationRecordsByCompanyId(Integer companyId,PageBounds pageBounds);

	/**
	 * @Title: getSearchCarDailyAccidentRecord
	 * @Description: 全局搜索
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyViolationRecordDO>
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午9:40:50
	 */
	public List<CarDailyViolationRecordDO> getSearchCarDailyViolationRecord(Map<String, Object> paraMap,PageBounds pageBounds);

	/**
	 * @Title: deleteIncomplete
	 * @Description: 不完全删除
	 * @param id
	 * @return
	 * @return: int
	 * @author: lipengcheng  
	 * @date: 2017年10月31日 上午9:41:14
	 */
	public int deleteIncomplete(Integer id);

	/**
	 * @Title: getTopList
	 * @Description: TODO 首页提醒 违章记录排行榜
	 * @param carDailyViolationRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<TopRow>
	 * @author: zy  
	 * @date: 2017-12-11 上午12:50:48
	 */
	public List<TopRow> getTopList(CarDailyViolationRecordDO carDailyViolationRecordDO,PageBounds pageBounds);
	
}