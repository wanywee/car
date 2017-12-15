package com.carTravelsky.service.carDaily;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.carDaily.CarBaseTrainingRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseTrainingRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseTrainingRecordMapper carBaseTrainingRecordMapper;

    /**
     * 保存carBaseTrainingRecordDO
     *
     */
    public void saveCarBaseTrainingRecord(CarBaseTrainingRecordDO carBaseTrainingRecordDO) throws ServiceException {
        try{
            if(carBaseTrainingRecordDO.getId() == null){
                carBaseTrainingRecordMapper.insert(carBaseTrainingRecordDO);
            }else {
                carBaseTrainingRecordMapper.update(carBaseTrainingRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseTrainingRecordDO getCarBaseTrainingRecordByID(Integer id) {
        return carBaseTrainingRecordMapper.getByID(id);
    }

    /**
     * 根据carBaseTrainingRecordDO查询列表
     *
     */
    public List<CarBaseTrainingRecordDO> getCarBaseTrainingRecordList(CarBaseTrainingRecordDO carBaseTrainingRecordDO) {
        return carBaseTrainingRecordMapper.getList(carBaseTrainingRecordDO);
    }

    /**
     * 根据carBaseTrainingRecordDO查询分页列表
     *
     */
    public List<CarBaseTrainingRecordDO> getCarBaseTrainingRecordList(CarBaseTrainingRecordDO carBaseTrainingRecordDO, PageBounds pageBounds) {
        return carBaseTrainingRecordMapper.getList(carBaseTrainingRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: getCarBaseTrainingAllRecordList 显示培训记录
     * @Description: TODO 显示培训记录
     * @param carBaseTrainingRecordDO 培训记录
     * @param pageBounds	分页
     * @return: List<CarBaseTrainingRecordDO> 返回的对象
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 上午9:15:34
     */
    public List<CarBaseTrainingRecordDO> getCarBaseTrainingAllRecordList(CarBaseTrainingRecordDO carBaseTrainingRecordDO, PageBounds pageBounds){
    	 return carBaseTrainingRecordMapper.getCarBaseTrainingAllRecordList(carBaseTrainingRecordDO, pageBounds);
    }
  /*  *//**
     * 
     * @Title: getSearchTrainngRecordList 显示培训记录
     * @Description: TODO 显示培训记录
     * @param searchStr 搜索的字符串
     * @param pageBounds 分页
     * @return: List<CarBaseTrainingRecordDO>
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月17日 下午2:35:23
     *//*
    public List<CarBaseTrainingRecordDO> getSearchTrainngRecordList(String  searchStr, PageBounds pageBounds) {
        return carBaseTrainingRecordMapper.getSearchTrainngRecordList(searchStr, pageBounds);
    }*/
    /**
     * 
     * @Title: editTrainingInfo 编辑培训记录
     * @Description: TODO 编辑培训记录
     * @param carBaseTrainingRecordDO 培训记录
     * @return: result
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 上午9:16:20
     */
    public int editTrainingInfo(CarBaseTrainingRecordDO carBaseTrainingRecordDO){
    	carBaseTrainingRecordDO.setCreateTime(new Date());
    	carBaseTrainingRecordMapper.editTrainingInfo(carBaseTrainingRecordDO);
    	return carBaseTrainingRecordDO.getId();
    }
    /**
     * 
     * @Title: deleteCarBaseTrainingRecordById 删除培训记录通过id
     * @Description: TODO 删除培训记录通过id
     * @param ids 选择的id
     * @return: result 返回结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 上午9:14:37
     */
    public int deleteCarBaseTrainingRecordById(String[] ids){
    	List<String> listIds=Arrays.asList(ids);
    	Iterator<String> iterator=listIds.iterator();
    	while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id=Integer.parseInt(string);
			carBaseTrainingRecordMapper.deleteCarBaseTrainingRecordById(id);
		}
    	return 1;
    }
    /**
     * 
     * @Title: updateCarBaseTrainingRecord 修改培训记录
     * @Description: TODO 修改培训记录
     * @param carBaseTrainingRecordDO  培训记录
     * @return: result 返回结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 上午9:12:42
     */
    public int updateCarBaseTrainingRecord(CarBaseTrainingRecordDO carBaseTrainingRecordDO) {
    	carBaseTrainingRecordDO.setUpdateTime(new Date());
    	return carBaseTrainingRecordMapper.updateCarBaseTrainingRecord(carBaseTrainingRecordDO);
	}
    
}