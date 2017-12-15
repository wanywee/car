package com.carTravelsky.mapper.trafficManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.carDaily.DepartmentCarDO;
import com.carTravelsky.mapper.BaseMapper;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;


public interface DispatchCenterMapper extends BaseMapper<CarDailyOutRecordDO>{

	void dispatchTurnDown(List<String> asList);
	/**
	 * 
	 * @Title: dispatchTurnDownList
	 * @Description: TODO根据出车拒绝的记录id列表获取要拒绝的车辆的carId信息
	 * @param asList
	 * @return: void
	 * @author: admin  
	 * @date: 2017年12月8日 上午9:41:02
	 */
	List<CarDailyOutRecordDO> dispatchTurnDownList(List<String> asList);
	/**
	 * 
	 * @Title: changeCarNowStatus
	 * @Description: TODO修改被拒绝出车的车辆状态为可用
	 * @param asList被拒绝出车的id列表
	 * @return: void
	 * @author: admin  
	 * @date: 2017年12月8日 上午9:59:12
	 */
	void changeCarNowStatus(List<String> asList);
	/**
	 * @Title: getListDispatchCenter
	 * @Description: TODO 调度中心查询
	 * @param carDailyOutRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyOutRecordDO>
	 * @author: THINK  
	 * @date: 2017-12-12 下午2:32:53
	 */
	List<CarDailyOutRecordDO> getListDispatchCenter(CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds);

	List<CarDailyOutRecordDO> serchDispatchCenter(@Param("searchStr")String searchStr,@Param("carDailyOutRecordDO")CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds);

	List<DepartmentCarDO> getDepartmentCars(@Param("outCarId")Integer outCarId, CarBaseVehicleDO baseVehicleDO);

}
