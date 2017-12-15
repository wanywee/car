package com.carTravelsky.service.carDaily;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairTackRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.carTravelsky.mapper.carDaily.CarDailyRepairTackRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyRepairTackRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyRepairTackRecordMapper carDailyRepairTackRecordMapper;
    @Autowired
    private CarBaseVehicleMapper  carBaseVehicleMapper;

    /**
     * 保存carDailyRepairTackRecordDO
     *
     */
    public void saveCarDailyRepairTackRecord(CarDailyRepairTackRecordDO carDailyRepairTackRecordDO) throws ServiceException {
        try{
            if(carDailyRepairTackRecordDO.getId() == null){
                carDailyRepairTackRecordMapper.insert(carDailyRepairTackRecordDO);
            }else {
                carDailyRepairTackRecordMapper.update(carDailyRepairTackRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyRepairTackRecordDO getCarDailyRepairTackRecordByID(Integer id) {
        return carDailyRepairTackRecordMapper.getByID(id);
    }

    /**
     * 根据carDailyRepairTackRecordDO查询列表
     *
     */
    public List<CarDailyRepairTackRecordDO> getCarDailyRepairTackRecordList(CarDailyRepairTackRecordDO carDailyRepairTackRecordDO) {
        return carDailyRepairTackRecordMapper.getList(carDailyRepairTackRecordDO);
    }

    /**
     * 根据carDailyRepairTackRecordDO查询分页列表
     *
     */
    public List<CarDailyRepairTackRecordDO> getCarDailyRepairTackRecordList(CarDailyRepairTackRecordDO carDailyRepairTackRecordDO, PageBounds pageBounds) {
        return carDailyRepairTackRecordMapper.getList(carDailyRepairTackRecordDO, pageBounds);
    }
	/**
	 * 全局搜索
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 */
	public List<CarDailyRepairTackRecordDO> getsearchCarDailyRepairTack(String searchStr,CarDailyRepairTackRecordDO carDailyRepairTackRecordDO,PageBounds pageBounds) {
		return carDailyRepairTackRecordMapper.getsearchCarDailyRepairTack(searchStr,carDailyRepairTackRecordDO,pageBounds);
	}

	/**
	 * @Title: delDispatchRecord
	 * @Description: TODO
	 * @param ids
	 * @param userDo
	 * @return: void
	 * @author: zy  
	 * @date: 2017-10-17 上午10:26:03
	 */
	@Transactional
	public void delDispatchRecord(String ids, CarSysUserDO userDo)
	{
		CarDailyRepairTackRecordDO repairTack=null;
		String [] idArr=ids.split(",");
		for (String id : idArr) {
			repairTack=new CarDailyRepairTackRecordDO();
			repairTack.setId(Integer.parseInt(id));
			repairTack.setDeleteCode(YSTConstants.DELETE_CODE_DEL);
			repairTack.setUpdatePeople(String.valueOf(userDo.getId()));
			repairTack.setUpdateTime(new Date());
			
			carDailyRepairTackRecordMapper.update(repairTack);
		}
		
	}

	/**
	 * @Title: saveCarDailyRepairTackRecord
	 * @Description: TODO
	 * @param carDailyRepairTackRecordDO
	 * @param userDo
	 * @return: void
	 * @author: zy  
	 * @param carBaseVehicleDO 
	 * @date: 2017-10-18 下午3:29:52
	 */
	public void saveCarDailyRepairTackRecord(CarDailyRepairTackRecordDO carDailyRepairTackRecordDO, CarSysUserDO userDo)
	{
		 try{
	            if(carDailyRepairTackRecordDO.getId() == null){
	            	carDailyRepairTackRecordDO.setCreatePeople(userDo.getId().toString());
	            	carDailyRepairTackRecordDO.setCreateTime(new Date());
	            	carDailyRepairTackRecordDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
	            	saveCarDailyRepairTackRecord(carDailyRepairTackRecordDO);
	            }else {
	            	carDailyRepairTackRecordDO.setUpdatePeople(userDo.getId().toString());
	            	carDailyRepairTackRecordDO.setUpdateTime(new Date());
	            	saveCarDailyRepairTackRecord(carDailyRepairTackRecordDO);
	            }
	               CarBaseVehicleDO carBaseVehicleDO=new CarBaseVehicleDO();
		            carBaseVehicleDO.setId(carDailyRepairTackRecordDO.getCarId());
		            carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_USEABLE);
		            carBaseVehicleDO.setUpdateTime(new Date());
		            carBaseVehicleDO.setUpdateOperator(userDo.getId().toString());
		            carBaseVehicleMapper.update(carBaseVehicleDO);
	           
	            
	            
	        } catch (Exception e) {
	            throw new ServiceException(e);
	        }
		
		
		
	}

}