package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarRemindFormDO;
import com.carTravelsky.mapper.carDaily.CarRemindFormMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarRemindFormService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarRemindFormMapper carRemindFormMapper;

    /**
     * 保存carRemindFormDO
     *
     */
    public void saveCarRemindForm(CarRemindFormDO carRemindFormDO) throws ServiceException {
        try{
            if(carRemindFormDO.getId() == null){
                carRemindFormMapper.insert(carRemindFormDO);
            }else {
                carRemindFormMapper.update(carRemindFormDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarRemindFormDO getCarRemindFormByID(Integer id) {
        return carRemindFormMapper.getByID(id);
    }

    /**
     * 根据carRemindFormDO查询列表
     *
     */
    public List<CarRemindFormDO> getCarRemindFormList(CarRemindFormDO carRemindFormDO) {
        return carRemindFormMapper.getList(carRemindFormDO);
    }

    /**
     * 根据carRemindFormDO查询分页列表
     *
     */
    public List<CarRemindFormDO> getCarRemindFormList(CarRemindFormDO carRemindFormDO, PageBounds pageBounds) {
        return carRemindFormMapper.getList(carRemindFormDO, pageBounds);
    }

}