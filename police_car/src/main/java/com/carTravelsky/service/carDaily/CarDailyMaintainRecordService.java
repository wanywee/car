package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.mapper.carDaily.CarDailyMaintainRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyMaintainRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyMaintainRecordMapper carDailyMaintainRecordMapper;

    /**
     * 保存carDailyMaintainRecordDO
     *
     */
    public void saveCarDailyMaintainRecord(CarDailyMaintainRecordDO carDailyMaintainRecordDO) throws ServiceException {
        try{
            if(carDailyMaintainRecordDO.getId() == null){
                carDailyMaintainRecordMapper.insert(carDailyMaintainRecordDO);
            }else {
                carDailyMaintainRecordMapper.update(carDailyMaintainRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyMaintainRecordDO getCarDailyMaintainRecordByID(Integer id) {
        return carDailyMaintainRecordMapper.getByID(id);
    }

    /**
     * 根据carDailyMaintainRecordDO查询列表
     *
     */
    public List<CarDailyMaintainRecordDO> getCarDailyMaintainRecordList(CarDailyMaintainRecordDO carDailyMaintainRecordDO) {
        return carDailyMaintainRecordMapper.getList(carDailyMaintainRecordDO);
    }
    /**
     * 根据carDailyMaintainRecordDO统计过期记录
     *
     */
    public int ExpireCount(int companyId,int remindDate){
    	return carDailyMaintainRecordMapper.ExpireCount(companyId, remindDate);
    }
    /**
     * 根据carDailyMaintainRecordDO统计过期记录byB部门
     *
     */
    public int ExpireCountById(int remindDate,PageBounds pageBounds){
    	return carDailyMaintainRecordMapper.ExpireCountById(remindDate,pageBounds);
    }
    /**
     * 
     * @Title: 根据carDailyMaintainRecordDO获取保养到期记录
     * @Description: TODO
     * @param carDailyMaintainRecordDO
     * @param pageBounds
     * @param remindDate
     * @return
     * @return: List<CarDailyMaintainRecordDO>
     * @author: admin  
     * @date: 2017年11月28日 下午4:22:00
     */
    public List<CarDailyMaintainRecordDO> ExpireList(CarDailyMaintainRecordDO carDailyMaintainRecordDO,PageBounds pageBounds,int remindDate){
    	return carDailyMaintainRecordMapper.ExpireList(carDailyMaintainRecordDO,pageBounds,remindDate);
    }
    /**
     * 根据carDailyMaintainRecordDO查询分页列表
     *
     */
    public List<CarDailyMaintainRecordDO> getCarDailyMaintainRecordList(CarDailyMaintainRecordDO carDailyMaintainRecordDO, PageBounds pageBounds) {
        return carDailyMaintainRecordMapper.getList(carDailyMaintainRecordDO, pageBounds);
    }

	public List<CarDailyMaintainRecordDO> getSearchCarDailyMaintainList(String searchStr, PageBounds bounds,Integer companyId) {
		return carDailyMaintainRecordMapper.getSearchCarDailyMaintainList(searchStr,bounds,companyId);
	}
	public List<CarDailyMaintainRecordDO> getSearchCarDailyMaintainExpireList(String searchStr, PageBounds bounds,Integer companyId,Integer remindDate) {
		return carDailyMaintainRecordMapper.getSearchCarDailyMaintainExpireList(searchStr, bounds, companyId, remindDate);
	}

	public void deleteCarDailyMaintain(List<String> idList) {
		carDailyMaintainRecordMapper.deleteCarDailyMaintain(idList);
	}

}