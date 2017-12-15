package com.carTravelsky.service.carDaily;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordHisMapper;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordHisDO;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyOutRecordHisService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyOutRecordHisMapper carDailyOutRecordHisMapper;

    /**
     * 保存carDailyOutRecordHisDO
     *
     */
    public void saveCarDailyOutRecordHis(CarDailyOutRecordHisDO carDailyOutRecordHisDO) throws ServiceException {
        try{
            if(carDailyOutRecordHisDO.getId() == null){
                carDailyOutRecordHisMapper.insert(carDailyOutRecordHisDO);
            }else {
                carDailyOutRecordHisMapper.update(carDailyOutRecordHisDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyOutRecordHisDO getCarDailyOutRecordHisByID(Integer id) {
        return carDailyOutRecordHisMapper.getByID(id);
    }

    /**
     * 根据carDailyOutRecordHisDO查询列表
     *
     */
    public List<CarDailyOutRecordHisDO> getCarDailyOutRecordHisList(CarDailyOutRecordHisDO carDailyOutRecordHisDO) {
        return carDailyOutRecordHisMapper.getList(carDailyOutRecordHisDO);
    }

    /**
     * 根据carDailyOutRecordHisDO查询分页列表
     *
     */
    public List<CarDailyOutRecordHisDO> getCarDailyOutRecordHisList(CarDailyOutRecordHisDO carDailyOutRecordHisDO, PageBounds pageBounds) {
        return carDailyOutRecordHisMapper.getList(carDailyOutRecordHisDO, pageBounds);
    }

}