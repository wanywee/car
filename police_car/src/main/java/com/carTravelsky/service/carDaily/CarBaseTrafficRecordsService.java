package com.carTravelsky.service.carDaily;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseTrafficRecordsDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.carDaily.CarBaseTrafficRecordsMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseTrafficRecordsService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseTrafficRecordsMapper carBaseTrafficRecordsMapper;

    /**
     * 保存carBaseTrafficRecordsDO
     *
     */
    public void saveCarBaseTrafficRecords(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO) throws ServiceException {
        try{
            if(carBaseTrafficRecordsDO.getId() == null){
                carBaseTrafficRecordsMapper.insert(carBaseTrafficRecordsDO);
            }else {
                carBaseTrafficRecordsMapper.update(carBaseTrafficRecordsDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseTrafficRecordsDO getCarBaseTrafficRecordsByID(Integer id) {
        return carBaseTrafficRecordsMapper.getByID(id);
    }

    /**
     * 根据carBaseTrafficRecordsDO查询列表
     *
     */
    public List<CarBaseTrafficRecordsDO> getCarBaseTrafficRecordsList(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO) {
        return carBaseTrafficRecordsMapper.getList(carBaseTrafficRecordsDO);
    }

    /**
     * 根据carBaseTrafficRecordsDO查询分页列表
     *
     */
    public List<CarBaseTrafficRecordsDO> getCarBaseTrafficRecordsList(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO, PageBounds pageBounds) {
        return carBaseTrafficRecordsMapper.getList(carBaseTrafficRecordsDO, pageBounds);
    }
    /**
     * 
     * @Title: getCarBaseTrafficAllRecordList 显示交通事故违规记录
     * @Description: TODO	显示交通事故违规记录
     * @param carBaseTrafficRecordsDO 交通事故违规记录
     * @param pageBounds 分页
     * @return: List<CarBaseTrafficRecordsDO> 交通事故违规记录
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午4:11:28
     */
    public List<CarBaseTrafficRecordsDO> getCarBaseTrafficAllRecordList(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO, PageBounds pageBounds) {
        return carBaseTrafficRecordsMapper.getCarBaseTrafficAllRecordList(carBaseTrafficRecordsDO, pageBounds);
    }
    /**
     * 
     * @Title: editTrafficInfo 新增交通事故违规处理
     * @Description: TODO	新增交通事故违规处理
     * @param carBaseTrafficRecordsDO 交通事故违规处理
     * @return: result 插入得id
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午4:34:49
     */
    public int editTrafficInfo(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO){
    	carBaseTrafficRecordsDO.setCreateTime(new Date());
    	carBaseTrafficRecordsMapper.editTrafficInfo(carBaseTrafficRecordsDO);
    	return carBaseTrafficRecordsDO.getId();
    }
    /**
     * 
     * @Title: updateCarBaseTrafficRecord 修改交通事故违规处理记录
     * @Description: TODO 修改交通事故违规处理记录
     * @param carBaseTrafficRecordsDO 交通事故违规处理
     * @return: result 违规处理
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午4:48:05
     */
    public int updateCarBaseTrafficRecord(CarBaseTrafficRecordsDO carBaseTrafficRecordsDO){
    	carBaseTrafficRecordsDO.setUpdateTime(new Date());
    	return carBaseTrafficRecordsMapper.updateCarBaseTrafficRecord(carBaseTrafficRecordsDO);
    	
    }
    /**
     * 
     * @Title: deleteDriverReTrainingRecord 删除交通事故违规处理
     * @Description: TODO	删除交通事故违规处理
     * @param ids 选择得ID
     * @return: result 返回结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午4:49:28
     */
    public int deleteTrafficRecord(String[] ids){
    	List<String> listIds=Arrays.asList(ids);
    	Iterator<String> iterator=listIds.iterator();
    	while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id=Integer.parseInt(string);
			carBaseTrafficRecordsMapper.deleteTrafficRecord(id);
		}
    	return 1;
    }
    
}