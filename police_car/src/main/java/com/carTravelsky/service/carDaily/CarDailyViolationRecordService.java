package com.carTravelsky.service.carDaily;

import java.util.Date;  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyViolationRecordDO;
import com.carTravelsky.mapper.carDaily.CarDailyViolationRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyViolationRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private CarDailyViolationRecordMapper carDailyViolationRecordMapper;

    /**
     * 保存carDailyViolationRecordDO
     *
     */
    @Transactional
    public void saveCarDailyViolationRecord(CarDailyViolationRecordDO carDailyViolationRecordDO) throws ServiceException {
        try{
            if(carDailyViolationRecordDO.getId() == null){
            	carDailyViolationRecordDO.setCreateTime(new Date());
                carDailyViolationRecordMapper.insert(carDailyViolationRecordDO);
            }else {
            	carDailyViolationRecordDO.setCreateTime(new Date());
                carDailyViolationRecordMapper.update(carDailyViolationRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyViolationRecordDO getCarDailyViolationRecordByID(Integer id) {
        return carDailyViolationRecordMapper.getByID(id);
    }

    /**
     * 根据carDailyViolationRecordDO查询列表
     *
     */
    public List<CarDailyViolationRecordDO> getCarDailyViolationRecordList(CarDailyViolationRecordDO carDailyViolationRecordDO) {
        return carDailyViolationRecordMapper.getList(carDailyViolationRecordDO);
    }

    /**
     * 根据carDailyViolationRecordDO查询分页列表
     *
     */
    public List<CarDailyViolationRecordDO> getCarDailyViolationRecordList(CarDailyViolationRecordDO carDailyViolationRecordDO, PageBounds pageBounds) {
        return carDailyViolationRecordMapper.getList(carDailyViolationRecordDO, pageBounds);
    }

    /**
     * @Title: getListViolationRecordsByCompanyId
     * @Description: 根据公司ID获得全部违章记录
     * @param companyId	公司ID
     * @param pageBounds
     * @return
     * @return: List<CarDailyViolationRecordDO>
     * @author: THINK  
     * @date: 2017年11月2日 下午2:25:06
     */
    public List<CarDailyViolationRecordDO> getListViolationRecordsByCompanyId(Integer companyId,PageBounds pageBounds){
    	return carDailyViolationRecordMapper.getListViolationRecordsByCompanyId(companyId, pageBounds);
    }
    /**
     * 全局搜索
     * @Title: getSearchCarDailyViolationRecord
     * @Description: TODO
     * @param paraMap
     * @param pageBounds
     * @return
     * @return: List<CarDailyViolationRecordDO>
     * @author: THINK  
     * @date: 2017年10月31日 上午9:41:54
     */
    @Transactional
    public List<CarDailyViolationRecordDO> getSearchCarDailyViolationRecord(Map<String, Object> paraMap,PageBounds pageBounds) {
		return carDailyViolationRecordMapper.getSearchCarDailyViolationRecord(paraMap, pageBounds);
	}
    
    /**
     * 插入车辆违章记录
     * @Title: insert
     * @Description: TODO
     * @param carDailyViolationRecordDO
     * @return
     * @throws ServiceException
     * @return: int
     * @author: THINK  
     * @date: 2017年10月31日 上午9:47:48
     */
    @Transactional
    public int insert(CarDailyViolationRecordDO carDailyViolationRecordDO) throws ServiceException{
    	try {
    		return carDailyViolationRecordMapper.insert(carDailyViolationRecordDO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
    }
    
    /**
     * 根据指定id删除车辆违章记录
     * @Title: delete
     * @Description: TODO
     * @param id
     * @return
     * @return: int
     * @author: THINK  
     * @date: 2017年10月31日 上午9:49:06
     */
    @Transactional
    public int delete(Integer id){
    	return carDailyViolationRecordMapper.delete(id);
    }
    
    /**
     * @Title: deleteIncomplete
     * @Description: 不完全删除，将删除状态码设置为2
     * @param id
     * @return
     * @return: int
     * @author: THINK  
     * @date: 2017年10月31日 上午9:50:41
     */
    @Transactional
    public int deleteIncomplete(Integer id){
    	return carDailyViolationRecordMapper.deleteIncomplete(id);
    }
    
}