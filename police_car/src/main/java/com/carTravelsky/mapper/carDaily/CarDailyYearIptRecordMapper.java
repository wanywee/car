package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarDailyYearIptRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyYearIptRecordMapper extends
		BaseMapper<CarDailyYearIptRecordDO> {

	/**
	 * @Title: countExpire
	 * @Description: 指定日期统计年检过期数量
	 * @param reminDdate指定剩余的过期天数
	 * @author: wangyu
	 * @date: 2017年10月17日 下午2:56:32
	 */
	int countExpire(int reminDdate);
	/**
	 * @Title: countExpire
	 * @Description: 指定日期统计年检过期数量by部门
	 * @param reminDdate指定剩余的过期天数
	 * @author: bxl
	 * @date: 2017年10月17日 下午2:56:32
	 */
	int countExpireById(int reminDdate,PageBounds pageBounds);
	
	/**
	 * @Title: getsearchCarDailyYearIPTRecord
	 * @Description: 全局搜索
	 * @param searchStr  搜索条件
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyYearIptRecordDO>
	 * @author: wangyu  
	 * @date: 2017年10月31日 上午10:44:10
	 */
	List<CarDailyYearIptRecordDO> getsearchCarDailyYearIPTRecord(String searchStr, PageBounds pageBounds);
	
	
	/**
	 * @Title: deleteIncomplete
	 * @Description: 不完全删除
	 * @param id 
	 * @return
	 * @return: int
	 * @author: wangyu  
	 * @date: 2017年10月31日 上午10:45:42
	 */
	int deleteIncomplete(Integer id);
	
	
	/**
	 * @Title: expireRecordList
	 * @Description: 到期的年检记录列表
	 * @param count
	 * @return
	 * @return: List<CarDailyYearIptRecordDO>
	 * @author: wangyu  
	 * @date: 2017年11月1日 上午10:29:24
	 */
	List<CarDailyYearIptRecordDO> expireRecordList(Integer count,PageBounds pageBounds);
}