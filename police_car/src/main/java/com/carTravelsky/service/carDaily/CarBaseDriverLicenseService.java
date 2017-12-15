package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseDriverLicenseDO;
import com.carTravelsky.mapper.carDaily.CarBaseDriverLicenseMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseDriverLicenseService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseDriverLicenseMapper carBaseDriverLicenseMapper;

    /**
     * 保存carBaseDriverLicenseDO
     *
     */
    public void saveCarBaseDriverLicense(CarBaseDriverLicenseDO carBaseDriverLicenseDO) throws ServiceException {
        try{
            if(carBaseDriverLicenseDO.getId() == null){
                carBaseDriverLicenseMapper.insert(carBaseDriverLicenseDO);
            }else {
                carBaseDriverLicenseMapper.update(carBaseDriverLicenseDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseDriverLicenseDO getCarBaseDriverLicenseByID(Integer id) {
        return carBaseDriverLicenseMapper.getByID(id);
    }

    /**
     * 根据carBaseDriverLicenseDO查询列表
     *
     */
    public List<CarBaseDriverLicenseDO> getCarBaseDriverLicenseList(CarBaseDriverLicenseDO carBaseDriverLicenseDO) {
        return carBaseDriverLicenseMapper.getList(carBaseDriverLicenseDO);
    }

    /**
     * 根据carBaseDriverLicenseDO查询分页列表
     *
     */
    public List<CarBaseDriverLicenseDO> getCarBaseDriverLicenseList(CarBaseDriverLicenseDO carBaseDriverLicenseDO, PageBounds pageBounds) {
        return carBaseDriverLicenseMapper.getList(carBaseDriverLicenseDO, pageBounds);
    }

}