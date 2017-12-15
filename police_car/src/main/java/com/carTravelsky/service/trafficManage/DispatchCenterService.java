package com.carTravelsky.service.trafficManage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.app.response.CarDetailListRS;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordHisDO;
import com.carTravelsky.bean.carDaily.DepartmentCarDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.app.AppCarApplyMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordHisMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordMapper;
import com.carTravelsky.mapper.trafficManage.DispatchCenterMapper;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;

@Component
public class DispatchCenterService {
	
	@Autowired
	private DispatchCenterMapper centerMapper;
	
	@Autowired
	private CarDailyOutRecordHisMapper carDailyOutRecordHisMapper;
	
	@Autowired
	private CarDailyOutRecordMapper carDailyOutRecordMapper;
	
	@Autowired
	private AppCarApplyMapper appCarApplyMapper;
 
	public List<CarDailyOutRecordDO> getListDispatchCenter(CarDailyOutRecordDO carDailyOutRecordDO,PageBounds pageBounds) {
		return centerMapper.getListDispatchCenter(carDailyOutRecordDO, pageBounds);
	}

	public void dispatchTurnDown(List<String> asList, CarSysUserDO currentUser) {
		List<CarDailyOutRecordDO> downList = centerMapper.dispatchTurnDownList(asList);
		
		List<String> carIdList = new LinkedList<String>();
		for (CarDailyOutRecordDO carDailyOutRecordDO : downList) {
			carIdList.add(carDailyOutRecordDO.getCarID().toString());
		}
		//更新车辆信息
		centerMapper.changeCarNowStatus(carIdList);
		//更新用车记录表
		centerMapper.dispatchTurnDown(asList);
		//插入历史记录
		CarDailyOutRecordHisDO  his=null;
		for (String cdorID : carIdList) {
			if(StringUtils.isBlank(cdorID))
				continue;
			his=new CarDailyOutRecordHisDO();
			his.setCdorID(Integer.parseInt(cdorID));
			his.setProcessStatus(YSTConstants.PROCESS_STATUS_HAVE_BACK_CAR);
			his.setOperator(currentUser.getId());
			his.setOperateTime(new Date());
			carDailyOutRecordHisMapper.insert(his);
		}
		
	}

	/**
	 * @Title: serchDispatchCenter
	 * @Description: TODO  查询调度中心显示信息
	 * @param searchStr
	 * @param carDailyOutRecordDO
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyOutRecordDO>
	 * @author: THINK  
	 * @date: 2017-12-12 下午2:41:56
	 */
	public List<CarDailyOutRecordDO> serchDispatchCenter(String searchStr, CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds) {
		carDailyOutRecordDO.setCarType(YSTConstants.PROCESS_STATUS_WAITING_DISPATCHING_CENTER);
		return centerMapper.serchDispatchCenter(searchStr,carDailyOutRecordDO,pageBounds);
	}

	public List<DepartmentCarDO> getDepartmentCars(Integer outCarId, CarBaseVehicleDO baseVehicleDO) {
		return centerMapper.getDepartmentCars(outCarId,baseVehicleDO);
	}

	public CarDailyOutRecordDO toDispatchRatify(String id, HttpServletRequest request,CarBaseVehicleDO baseVehicleDO) {
					// 根据id获取申请记录
					CarDailyOutRecordDO carDailyOutRecordDO = carDailyOutRecordMapper.getApplyByID(Integer.valueOf(id));
					// 根据车辆id得到封装车辆信息
					CarDetailListRS car = appCarApplyMapper.getCarsById(carDailyOutRecordDO.getCarID(),null);
						String vName = "车牌号:" + car.getLicenseNumber() + " 类型:" + car.getCarType() + " 载人数:"
								+ car.getLoadingNumber() + "\r\n" + "公里数:" + car.getMileNumber() + " 位置:涪陵公安局";
						carDailyOutRecordDO.setLicenseno(vName);
					
					return carDailyOutRecordDO;
		
	}

}
