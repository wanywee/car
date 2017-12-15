package com.carTravelsky.mapper.carDaily;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;


public interface CarDailyOutRecordMapper extends BaseMapper<CarDailyOutRecordDO> {
	/**全局搜索出车记录 根据公司id*/
	public List<CarDailyOutRecordDO> getSearchCarOutList(Map<String, Object> map, PageBounds pageBounds);
	/**全局搜索回车记录 根据公司id 车辆状态不为2*/
	public List<CarDailyOutRecordDO> getSearchCarBackList(Map<String, Object> map, PageBounds pageBounds);
	/**全局搜索回车记录 根据公司id 车辆状态为1 3*/
	public List<CarDailyOutRecordDO> getSearchCarBackListByType(Map<String, Object> map, PageBounds pageBounds);
	
	/**获取回车记录  根据公司id  车辆状态为1 3*/
	public List<CarDailyOutRecordDO> getBackListByType(CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds);
	/**获取回车记录  根据公司id  车辆状态不为2*/
	public List<CarDailyOutRecordDO> getBackList(CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds);
	/**根据车辆id 得到出车记录 最新回车时间的数据*/
	public CarDailyOutRecordDO getBackByCarID(Integer carID);
	/**根据车辆id 得到出车记录 最新出车时间的数据*/
	public CarDailyOutRecordDO getOutByCarID(Integer carID);
	/**新增出车记录*/
	public int saveOutRecord(CarDailyOutRecordDO carDailyOutRecordDO);
	/**获取提醒时间**/
	public int getHour();
	/**修改车辆当前状态为出车申请中*/
	public int changeNowStatus(@Param("id")Integer id);
	/**
	 * @Title: updateBackTimeByCondition
	 * @Description: TODO
	 * @param deptId
	 * @param carId
	 * @param caruser
	 * @param next
	 * @return
	 * @return: int
	 */
	public int updateBackTimeByCondition(
			@Param("deptId")Integer deptId, 
			@Param("carId")Integer carId,
			@Param("caruser")String caruser, 
			@Param("next")Date next,
			@Param("remark")String remark);
	public CarDailyOutRecordDO getApplyByID(Integer id);

}