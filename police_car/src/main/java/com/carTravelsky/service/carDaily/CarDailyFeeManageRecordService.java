package com.carTravelsky.service.carDaily;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseContactsDO;
import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyFeeManageRecordDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.mapper.carDaily.CarBaseContactsMapper;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.carTravelsky.mapper.carDaily.CarDailyFeeManageRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyFeeManageRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyFeeManageRecordMapper carDailyFeeManageRecordMapper;
    @Autowired
    private CarBaseVehicleMapper carBaseVehicleMapper;
    @Autowired
    private CarBaseContactsMapper carBaseContactsMapper;
    /**
     * 保存carDailyFeeManageRecordDO
     *
     */
    public void saveCarDailyFeeManageRecord(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO) throws ServiceException {
        try{
            if(carDailyFeeManageRecordDO.getId() == null){
                carDailyFeeManageRecordMapper.insert(carDailyFeeManageRecordDO);
            }else {
                carDailyFeeManageRecordMapper.update(carDailyFeeManageRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarDailyFeeManageRecordDO getCarDailyFeeManageRecordByID(Integer id) {
        return carDailyFeeManageRecordMapper.getByID(id);
    }

    /**
     * 根据carDailyFeeManageRecordDO查询列表
     *
     */
    public List<CarDailyFeeManageRecordDO> getCarDailyFeeManageRecordList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO) {
        return carDailyFeeManageRecordMapper.getList(carDailyFeeManageRecordDO);
    }
    /**
     * 根据carDailyFeeManageRecordDO查询分页列表
     *
     */
    public List<CarDailyFeeManageRecordDO> getCarDailyFeeManageRecordList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds) {
        return carDailyFeeManageRecordMapper.getList(carDailyFeeManageRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: getCarDailyFeeManageAllList 获取所有规费管理记录
     * @Description: TODO 获取所有规费管理记录
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: List<CarDailyFeeManageRecordDO> 显示结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月30日 下午2:17:13
     */
    public  List<CarDailyFeeManageRecordDO>  getCarDailyFeeManageAllList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds) {
    	return carDailyFeeManageRecordMapper.getCarDailyFeeManageAllList(carDailyFeeManageRecordDO,pageBounds);
    }
    /**
     * 
     * @Title: getCarDailyFeeManageAllList 统计规费到期
     * @Description: TODO 规费到期统计
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 显示结果
     * @author: 任连松?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年11月24日 下午2:17:13
     */
    public int countExpire(int remindDate,int companyID){
    	return carDailyFeeManageRecordMapper.countExpire(remindDate,companyID);
    }
    /**
     * 
     * @Title: getCarDailyFeeManageAllList 统计规费到期
     * @Description: TODO 规费到期统计
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 显示结果
     * @author:bxl
     * @date: 2017年11月24日 下午2:17:13
     */
    public int countExpireById(int remindDate,PageBounds pageBounds){
    	return carDailyFeeManageRecordMapper.countExpireById(remindDate,pageBounds);
    }
    /**
     * 
     * @Title: getCarDailyFeeManageAllList 获取规费到期记录
     * @Description: TODO 获取规费到期记录
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 显示结果
     * @author: 任连松?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年11月25日 下午10:25:13
     */
    public List<CarDailyFeeManageRecordDO> expireRecordList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds,int remindDate,int companyID){
    	return carDailyFeeManageRecordMapper.expireRecordList(carDailyFeeManageRecordDO,pageBounds,remindDate,companyID);
    };
    /**
     * 
     * @Title: getSearchFeeManageAllList 全局搜索
     * @Description: TODO 全局搜索
     * @param searchStr 所搜的字段
     * @param pageBounds 分页
     * @return: List<CarDailyFeeManageRecordDO> 查询的结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 上午9:56:29
     */
    public   List<CarDailyFeeManageRecordDO>  getSearchFeeManageAllList(String searchStr,CarDailyFeeManageRecordDO carDailyFeeManageRecordDO,PageBounds pageBounds){
    	return carDailyFeeManageRecordMapper.getSearchFeeManageAllList(searchStr,carDailyFeeManageRecordDO,pageBounds);
    }
    public List<CarDailyFeeManageRecordDO> getSearchFeeManageAllListExpire(String searchStr,CarDailyFeeManageRecordDO carDailyFeeManageRecordDO ,PageBounds pageBounds,int remindDate){ 
    	return carDailyFeeManageRecordMapper.getSearchFeeManageAllListExpire(searchStr,carDailyFeeManageRecordDO,pageBounds,remindDate);
    }
    /**
     * 
     * @Title: getListLicenseNo 获取车牌号
     * @Description: TODO 获取车牌号
     * @param select2vos 下拉框
     * @return: List<Select2VO> 返回结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 上午10:55:19
     */
    public List<Select2VO> getListLicenseNo(List<Select2VO> select2vos) {
		List<CarBaseVehicleDO> carBaseVehicleDOs = carBaseVehicleMapper.getLicenseNoAllList();
		Select2VO select2vo;
		for (CarBaseVehicleDO carBaseVehicleDO : carBaseVehicleDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseVehicleDO.getId()));
			select2vo.setText(carBaseVehicleDO.getLicenseno());
			select2vos.add(select2vo);
		}
		return select2vos;
	}
    
    /**
     * 
     * @Title: getListFeeUnits 获取所有往来单位
     * @Description: TODO	获取所有往来单位
     * @param select2vos 往来单位下拉框
     * @return: List<Select2VO> 下拉框结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午1:24:51
     */
    public List<Select2VO> getListFeeUnits(List<Select2VO> select2vos) {
		List<CarBaseContactsDO> carBaseContactsDOs = carBaseContactsMapper.getListFeeUnits();
		Select2VO select2vo;
		for (CarBaseContactsDO carBaseContactsDO : carBaseContactsDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(carBaseContactsDO.getId()));
			select2vo.setText(carBaseContactsDO.getCompanyName());
			select2vos.add(select2vo);
		}
		return select2vos;
	}
    
    /**
     * 
     * @Title: deleteFeesManage 删除规费记录
     * @Description: TODO 删除规费记录
     * @param ids 选中的行
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午1:50:08
     */
    public void deleteFeesManage(String[] ids) {
		List<String> listIds = Arrays.asList(ids);
		Iterator<String> iterator = listIds.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			Integer id = Integer.parseInt(string);
			carDailyFeeManageRecordMapper.deleteFeesManage(id);
		}
	}
    /**
     * 
     * @Title: addFeesManage 增加规费管理
     * @Description: TODO 增加规费管理
     * @param carDailyFeeManageRecordDO 规费管理
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午2:57:27
     */
    public void addFeesManage(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO){
    	carDailyFeeManageRecordDO.setCreateTime(new Date());
    	carDailyFeeManageRecordMapper.addFeesManage(carDailyFeeManageRecordDO);
    }
    /**
     * 
     * @Title: getFeesManageInfoByID 回显规费管理
     * @Description: TODO 回显规费管理
     * @param id 选中的行
     * @return: CarDailyFeeManageRecordDO 回显结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午3:18:12
     */
    public CarDailyFeeManageRecordDO getFeesManageInfoByID(Integer id){
    	return carDailyFeeManageRecordMapper.getFeesManageInfoByID(id);
    }
    /**
     * 
     * @Title: updateFeesManageInfo 修改规费管理
     * @Description: TODO 修改规费管理
     * @param carDailyFeeManageRecordDO 规费管理
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午3:50:58
     */
    public void updateFeesManageInfo(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO){
    	carDailyFeeManageRecordDO.setCreateTime(new Date());
    	carDailyFeeManageRecordMapper.updateFeesManageInfo(carDailyFeeManageRecordDO);
    }
}