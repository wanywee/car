package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseCompanyDO;
import com.carTravelsky.mapper.carDaily.CarBaseCompanyMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseCompanyService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseCompanyMapper carBaseCompanyMapper;

    /**
     * 保存carBaseCompanyDO
     *
     */
    public void saveCarBaseCompany(CarBaseCompanyDO carBaseCompanyDO) throws ServiceException {
        try{
            if(carBaseCompanyDO.getId() == null){
                carBaseCompanyMapper.insert(carBaseCompanyDO);
            }else {
                carBaseCompanyMapper.update(carBaseCompanyDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseCompanyDO getCarBaseCompanyByID(Integer id) {
        return carBaseCompanyMapper.getByID(id);
    }

    /**
     * 根据carBaseCompanyDO查询列表
     *
     */
    public List<CarBaseCompanyDO> getCarBaseCompanyList(CarBaseCompanyDO carBaseCompanyDO) {
        return carBaseCompanyMapper.getList(carBaseCompanyDO);
    }

    /**
     * 根据carBaseCompanyDO查询分页列表
     *
     */
    public List<CarBaseCompanyDO> getCarBaseCompanyList(CarBaseCompanyDO carBaseCompanyDO, PageBounds pageBounds) {
        return carBaseCompanyMapper.getList(carBaseCompanyDO, pageBounds);
    }

}