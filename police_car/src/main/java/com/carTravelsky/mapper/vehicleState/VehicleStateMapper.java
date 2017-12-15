package com.carTravelsky.mapper.vehicleState;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.carTravelsky.bean.vehicle.VehicleRQ;
import com.carTravelsky.bean.vehicle.VehicleRS;

/**
 * 
 * @ClassName: VehicleState
 * @Description: TODO
 * @author: lipengcheng
 * @date: 2017年10月12日 上午10:10:19
 */
@Repository("vehicleStateMapper")
public interface VehicleStateMapper {
	
	/**
	 * 
	 * @Title: getVehicleStateList
	 * @Description: 根据参数返回车辆状态数据list
	 * @param vehicle
	 * @return
	 * @throws Exception
	 * @return: List<VehicleRS>
	 * @author: lipengcheng  
	 * @date: 2017年10月19日 下午2:38:47
	 */
	public List<VehicleRS> getVehicleStateList(VehicleRQ vehicle)throws Exception;
	
	/**
	 * 
	 * @Title: getTotalCount
	 * @Description: 根据参数返回车辆数据的计数
	 * @param vehicleRQ
	 * @return
	 * @throws Exception
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年10月20日 下午1:46:06
	 */
	public int getTotalCount(VehicleRQ vehicleRQ)throws Exception;

	public String getVehicleByCarId(@Param("carDetailID")Integer carDetailID);
}
