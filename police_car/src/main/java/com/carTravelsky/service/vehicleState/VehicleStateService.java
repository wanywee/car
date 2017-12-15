package com.carTravelsky.service.vehicleState;

import java.util.List;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.vehicle.VehicleRQ;
import com.carTravelsky.bean.vehicle.VehicleRS;
import com.carTravelsky.mapper.vehicleState.VehicleStateMapper;

@Transactional(propagation=Propagation.REQUIRED)
@Service("vehicleStateService")
public class VehicleStateService {
	
	@Autowired
	private VehicleStateMapper vehicleStateMapper;
	/**
	 * 
	 * @Title: getVehicleStateList
	 * @Description: 根据参数返回车辆状态数据list
	 * @param selectVehicleData
	 * @return
	 * @throws Exception
	 * @return: List<VehicleRS>
	 * @author: THINK  
	 * @date: 2017年10月19日 下午2:40:54
	 */
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<VehicleRS> getVehicleStateList(VehicleRQ selectVehicleData) throws Exception{
		return vehicleStateMapper.getVehicleStateList(selectVehicleData);
	}
	
	/**
	 * 
	 * @Title: getTotalCount
	 * @Description: 根据参数返回车辆状态的计数
	 * @param vehicleRQ
	 * @return
	 * @throws Exception
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年10月19日 下午2:42:02
	 */
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public int getTotalCount(VehicleRQ vehicleRQ)throws Exception{
		return vehicleStateMapper.getTotalCount(vehicleRQ);
	}
	
}
