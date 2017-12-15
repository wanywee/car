package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarFuelMeterDO;
import com.carTravelsky.mapper.carDaily.CarFuelMeterMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarFuelMeterService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarFuelMeterMapper carFuelMeterMapper;

    /**
     * 保存carFuelMeterDO
     *
     */
    public void saveCarFuelMeter(CarFuelMeterDO carFuelMeterDO) throws ServiceException {
        try{
            if(carFuelMeterDO.getId() == null){
                carFuelMeterMapper.insert(carFuelMeterDO);
            }else {
                carFuelMeterMapper.update(carFuelMeterDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarFuelMeterDO getCarFuelMeterByID(Integer id) {
        return carFuelMeterMapper.getByID(id);
    }

    /**
     * 根据carFuelMeterDO查询列表
     *
     */
    public List<CarFuelMeterDO> getCarFuelMeterList(CarFuelMeterDO carFuelMeterDO) {
        return carFuelMeterMapper.getList(carFuelMeterDO);
    }

    /**
     * 根据carFuelMeterDO查询分页列表
     *
     */
    public List<CarFuelMeterDO> getCarFuelMeterList(CarFuelMeterDO carFuelMeterDO, PageBounds pageBounds) {
        return carFuelMeterMapper.getList(carFuelMeterDO, pageBounds);
    }

}