package com.carTravelsky.mapper.carDaily;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyInsuranceRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyInsuranceRecordMapper extends
		BaseMapper<CarDailyInsuranceRecordDO> {

	/**
	 * @Title: countExpire
	 * @Description: 指定日期统计保险过期数量
	 * @param reminDdate指定剩余的过期天数
	 * @author: wangyu
	 * @date: 2017年10月17日 下午2:56:32
	 */
	int countExpire(int reminDdate);
	/**
	 * @Title: countExpire
	 * @Description: 指定日期统计保险过期数量通过部门
	 * @param reminDdate指定剩余的过期天数
	 * @author: bxl
	 * @date: 2017年10月17日 下午2:56:32
	 */
	int countExpireBycomID(int reminDdate,PageBounds pageBounds);

	void updateDeletecode(List<String> asList);

	List<CarDailyInsuranceRecordDO> getListCarInsuranceSearchStr(@Param("searchStr")String searchStr, PageBounds pageBounds,
			@Param("companyId")Integer companyId);
	
	/**
	 * @Title: getExpireList
	 * @Description: 得到保险到期提醒列表 
	 * @param reminDdate  指定剩余的过期天数
	 * @return
	 * @return: List<CarDailyInsuranceRecordDO>
	 * @author: wangyu  
	 * @date: 2017年12月6日 下午4:16:25
	 */
	List<CarDailyInsuranceRecordDO> getExpireList(Integer reminDdate,PageBounds pageBounds);

}