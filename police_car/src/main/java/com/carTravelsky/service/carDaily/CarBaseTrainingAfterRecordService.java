package com.carTravelsky.service.carDaily;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseTrainingAfterRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.carDaily.CarBaseTrainingAfterRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseTrainingAfterRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseTrainingAfterRecordMapper carBaseTrainingAfterRecordMapper;

    /**
     * 保存carBaseTrainingAfterRecordDO
     *
     */
    public void saveCarBaseTrainingAfterRecord(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO) throws ServiceException {
        try{
            if(carBaseTrainingAfterRecordDO.getId() == null){
                carBaseTrainingAfterRecordMapper.insert(carBaseTrainingAfterRecordDO);
            }else {
                carBaseTrainingAfterRecordMapper.update(carBaseTrainingAfterRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseTrainingAfterRecordDO getCarBaseTrainingAfterRecordByID(Integer id) {
        return carBaseTrainingAfterRecordMapper.getByID(id);
    }

    /**
     * 根据carBaseTrainingAfterRecordDO查询列表
     *
     */
    public List<CarBaseTrainingAfterRecordDO> getCarBaseTrainingAfterRecordList(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO) {
        return carBaseTrainingAfterRecordMapper.getList(carBaseTrainingAfterRecordDO);
    }

    /**
     * 根据carBaseTrainingAfterRecordDO查询分页列表
     *
     */
    public List<CarBaseTrainingAfterRecordDO> getCarBaseTrainingAfterRecordList(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO, PageBounds pageBounds) {
        return carBaseTrainingAfterRecordMapper.getList(carBaseTrainingAfterRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: getCarBaseTrainingAfterAllRecordList 显示在岗复训记录
     * @Description: TODO	显示在岗复训记录
     * @param carBaseTrainingAfterRecordDO	在岗复训
     * @param pageBounds	分页
     * @return: List<CarBaseTrainingAfterRecordDO> 在岗复训集合
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午2:40:54
     */
    public List<CarBaseTrainingAfterRecordDO> getCarBaseTrainingAfterAllRecordList(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO, PageBounds pageBounds) {
        return carBaseTrainingAfterRecordMapper.getCarBaseTrainingAfterAllRecordList(carBaseTrainingAfterRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: editReTrainingInfo 新增在岗复训记录
     * @Description: TODO	新增在岗复训记录
     * @param carBaseTrainingAfterRecordDO 新增在岗复训记录
     * @return: result 返回新增的id
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午2:31:20
     */
    public int editReTrainingInfo(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO){
    	carBaseTrainingAfterRecordDO.setCreateTime(new Date());
    	carBaseTrainingAfterRecordMapper.editReTrainingInfo(carBaseTrainingAfterRecordDO);
    	return carBaseTrainingAfterRecordDO.getId();
    }
    /**
     * 
     * @Title: updateCarBaseTrainingAfterRecord 修改在岗复训记录
     * @Description: TODO	修改在岗复训记录
     * @param carBaseTrainingAfterRecordDO 在岗复训记录
     * @return: result
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月18日 下午2:51:44
     */
    public int updateCarBaseTrainingAfterRecord(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO) {
    	carBaseTrainingAfterRecordDO.setUpdateTime(new Date());
    	return carBaseTrainingAfterRecordMapper.updateCarBaseTrainingAfterRecord(carBaseTrainingAfterRecordDO);
	}
    /**
     * 
     * @Title: deleteDriverReTrainingRecord 删除在岗复训记录
     * @Description: TODO 删除在岗复训记录
     * @param ids 选择的id
     * @return: result 返回的结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月19日 上午9:22:10
     */
    public int deleteDriverReTrainingRecord(String[] ids){
    	List<String> listIds=Arrays.asList(ids);
    	Iterator<String> iterator=listIds.iterator();
    	while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id=Integer.parseInt(string);
			carBaseTrainingAfterRecordMapper.deleteDriverReTrainingRecord(id);
		}
    	return 1;
    }
    
}