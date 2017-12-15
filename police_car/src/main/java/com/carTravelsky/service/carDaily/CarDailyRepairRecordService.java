package com.carTravelsky.service.carDaily;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarDailyRepairRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyRepairRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyRepairRecordMapper carDailyRepairRecordMapper;
    @Autowired
    private CarBaseVehicleService carBaseVehicleService;

    /**
     * 保存carDailyRepairRecordDO
     *
     */
    public void saveCarDailyRepairRecord(CarDailyRepairRecordDO carDailyRepairRecordDO) throws ServiceException {
        try{
            if(carDailyRepairRecordDO.getId() == null){
                carDailyRepairRecordMapper.insert(carDailyRepairRecordDO);
            }else {
                carDailyRepairRecordMapper.update(carDailyRepairRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyRepairRecordDO getCarDailyRepairRecordByID(Integer id) {
        return carDailyRepairRecordMapper.getByID(id);
    }

    /**
     * 根据carDailyRepairRecordDO查询列表
     *
     */
    public List<CarDailyRepairRecordDO> getCarDailyRepairRecordList(CarDailyRepairRecordDO carDailyRepairRecordDO) {
        return carDailyRepairRecordMapper.getList(carDailyRepairRecordDO);
    }

    /**
     * 根据carDailyRepairRecordDO查询分页列表
     *
     */
    public List<CarDailyRepairRecordDO> getCarDailyRepairRecordList(CarDailyRepairRecordDO carDailyRepairRecordDO, PageBounds pageBounds) {
        return carDailyRepairRecordMapper.getList(carDailyRepairRecordDO, pageBounds);
    }

    /**
     * 
     * @Title: getsearchRepairRecords
     * @Description: 全局搜索-维修记录
     * @param paraMap
     * @param pageBounds
     * @return
     * @return: List<CarDailyRepairRecordDO>
     */
	public List<CarDailyRepairRecordDO> getsearchRepairRecords(
			Map<String, Object> paraMap, PageBounds pageBounds) {
		return carDailyRepairRecordMapper.getsearchRepairRecords(paraMap, pageBounds);
	}

	/**
	 * 
	 * @Title: getListRepairRecordsByCompanyId
	 * @Description: 获取维修记录表格数据
	 * @param companyId
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyRepairRecordDO>
	 */
	public List<CarDailyRepairRecordDO> getListRepairRecordsByCompanyId(
			int companyId, PageBounds pageBounds) {
		return carDailyRepairRecordMapper.getListRepairRecordsByCompanyId(companyId, pageBounds);
	}

	/**
	 * 
	 * @Title: getCarDailyRepairRecordByKeyId
	 * @Description: 根据主键查询维修记录
	 * @param paraMap
	 * @return
	 * @return: CarDailyRepairRecordDO
	 */
	public CarDailyRepairRecordDO getCarDailyRepairRecordByKeyId(Map<String, Object> paraMap) {
		return carDailyRepairRecordMapper.getCarDailyRepairRecordByKeyId(paraMap);
	}

	/**
	 * 
	 * @Title: isUseCarDailyRepairRecord
	 * @Description: 删除状态码 service
	 * @param currentUser
	 * @param carDailyRepairRecordDO
	 * @return: void
	 */
	public void isUseCarDailyRepairRecord(CarSysUserDO currentUser,
			CarDailyRepairRecordDO carDailyRepairRecordDO) {
		try {
			Date initDate = new Date();
			// 不管新增还是修改都需要操作更新人和时间
			carDailyRepairRecordDO.setUpdatePeople(currentUser.getUsername());
			carDailyRepairRecordDO.setUpdateTime(initDate);
			carDailyRepairRecordMapper.update(carDailyRepairRecordDO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @Title: saveCarDailyRepairRecord
	 * @Description: 新增或修改维修记录 service
	 * @param currentUser 当前用户
	 * @param carDailyRepairRecordDO
	 * @return: void
	 */
	public void saveCarDailyRepairRecord(CarSysUserDO currentUser,
			CarDailyRepairRecordDO carDailyRepairRecordDO) {
		try {
			// 新增和修改都需的操作
			Date initDate = new Date();
			carDailyRepairRecordDO.setUpdatePeople(currentUser.getUsername());
			carDailyRepairRecordDO.setUpdateTime(initDate);
			// 新增操作
			if (carDailyRepairRecordDO.getId() == null) {
				//新增维修记录，将车辆状态改为维修
				CarBaseVehicleDO carBaseVehicleDO =new CarBaseVehicleDO();
				carBaseVehicleDO.setId(carDailyRepairRecordDO.getCarID());
				carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_REPAIR);
				//保存
				carBaseVehicleService.saveCarBaseVehicle(carBaseVehicleDO);
				// 设置创建人 时间 添加
				carDailyRepairRecordDO.setCreatePeople(currentUser.getUsername());
				carDailyRepairRecordDO.setCreateTime(initDate);
				carDailyRepairRecordMapper.insert(carDailyRepairRecordDO);
			} else {
				CarDailyRepairRecordDO photoUrlDO= carDailyRepairRecordMapper.getByID(carDailyRepairRecordDO.getId());
				String url = photoUrlDO.getPhotoUrl();
				String newUrl = url+ carDailyRepairRecordDO.getPhotoUrl();
				carDailyRepairRecordDO.setPhotoUrl(newUrl);
				carDailyRepairRecordMapper.update(carDailyRepairRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}