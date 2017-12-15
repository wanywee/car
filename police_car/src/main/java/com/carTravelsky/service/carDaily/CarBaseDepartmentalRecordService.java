package com.carTravelsky.service.carDaily;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseDepartmentalRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseTrafficRecordsDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingAfterRecordDO;
import com.carTravelsky.mapper.carDaily.CarBaseDepartmentalRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarBaseDepartmentalRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseDepartmentalRecordMapper carBaseDepartmentalRecordMapper;

    /**
     * 保存carBaseDepartmentalRecordDO
     *
     */
    public void saveCarBaseDepartmentalRecord(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO) throws ServiceException {
        try{
            if(carBaseDepartmentalRecordDO.getId() == null){
                carBaseDepartmentalRecordMapper.insert(carBaseDepartmentalRecordDO);
            }else {
                carBaseDepartmentalRecordMapper.update(carBaseDepartmentalRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseDepartmentalRecordDO getCarBaseDepartmentalRecordByID(Integer id) {
        return carBaseDepartmentalRecordMapper.getByID(id);
    }

    /**
     * 根据carBaseDepartmentalRecordDO查询列表
     *
     */
    public List<CarBaseDepartmentalRecordDO> getCarBaseDepartmentalRecordList(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO) {
        return carBaseDepartmentalRecordMapper.getList(carBaseDepartmentalRecordDO);
    }

    /**
     * 根据carBaseDepartmentalRecordDO查询分页列表
     *
     */
    public List<CarBaseDepartmentalRecordDO> getCarBaseDepartmentalRecordList(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO, PageBounds pageBounds) {
        return carBaseDepartmentalRecordMapper.getList(carBaseDepartmentalRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: getCarBaseDepartmentalAllRecordList 显示部门变更记录列表
     * @Description: TODO 显示部门变更记录列表
     * @param carBaseDepartmentalRecordDO 部门变更记录
     * @param pageBounds	分页
     * @return: List<CarBaseDepartmentalRecordDO> 部门记录
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月19日 上午9:33:38
     */
    public List<CarBaseDepartmentalRecordDO> getCarBaseDepartmentalAllRecordList(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO, PageBounds pageBounds) {
        return carBaseDepartmentalRecordMapper.getCarBaseDepartmentalAllRecordList(carBaseDepartmentalRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: editDeptChangeInfo 新增部门变更记录
     * @Description: TODO	新增部门变更记录
     * @param carBaseDepartmentalRecordDO	部门变更记录对象
     * @return: result 返回插入的ID
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月19日 上午9:50:38
     */
    public int editDeptChangeInfo(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO){
    	carBaseDepartmentalRecordDO.setCreateTime(new Date());
    	carBaseDepartmentalRecordMapper.editDeptChangeInfo(carBaseDepartmentalRecordDO);
    	return carBaseDepartmentalRecordDO.getId();
    }
    /**
     * 
     * @Title: updateCarBaseDeptChangeRecord 修改部门变更记录
     * @Description: TODO	修改部门变更记录
     * @param carBaseDepartmentalRecordDO 修改部门变更记录
     * @return: result 返回结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月19日 上午9:52:06
     */
    public int updateCarBaseDeptChangeRecord(CarBaseDepartmentalRecordDO carBaseDepartmentalRecordDO) {
    	carBaseDepartmentalRecordDO.setUpdateTime(new Date());
    	return carBaseDepartmentalRecordMapper.updateCarBaseDeptChangeRecord(carBaseDepartmentalRecordDO);
	}
    public int deleteDeptChangeRecord(String[] ids){
    	List<String> listIds=Arrays.asList(ids);
    	Iterator<String> iterator=listIds.iterator();
    	while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id=Integer.parseInt(string);
			carBaseDepartmentalRecordMapper.deleteDeptChangeRecord(id);
		}
    	return 1;
    }
    
    
}