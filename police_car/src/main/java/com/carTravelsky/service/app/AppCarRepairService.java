package com.carTravelsky.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.app.AppCarRepairMapper;
import com.carTravelsky.mapper.carDaily.CarDailyRepairRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarRepairService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 下午5:51:32
 */
@Component
public class AppCarRepairService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarRepairMapper appCarRepairMapper;
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	@Autowired
	private CarDailyRepairRecordMapper carDailyRepairRecordMapper;
	/**
	 * @Title: repairRecodrSave
	 * @Description: TODO
	 * @param carDailyRepairRecordDO
	 * @param username
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int repairRecodrSave(CarDailyRepairRecordDO carDailyRepairRecordDO,
			String username) {
		int result = 0;
		
		try {
		Integer isHave  = appCarRepairMapper
					.searchRepairIsHave(
							carDailyRepairRecordDO.getCarID(),
							carDailyRepairRecordDO.getHandler(),
							carDailyRepairRecordDO.getRepairTime());
		if(null == isHave){
			carDailyRepairRecordDO.setCreatePeople(username);
			carDailyRepairRecordDO.setCreateTime(DateTools.getCurrentDate());
			return result = this.insertRepair(carDailyRepairRecordDO);
		}
		{
			carDailyRepairRecordDO.setId(isHave);
			carDailyRepairRecordDO.setUpdateTime(DateTools.getCurrentDate());
			carDailyRepairRecordDO.setUpdatePeople(username);
			return result = this.updateRepair(carDailyRepairRecordDO);
		}
		} catch (Exception e) {
			logger.error("维修记录存入数据错误出错//{}....", e);
		}
		return result;
	}
	
	/**
	 * @Title: updateRepair
	 * @Description: TODO
	 * @param carDailyRepairRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int updateRepair(CarDailyRepairRecordDO carDailyRepairRecordDO) {
		int result=0;
		try {
			result = carDailyRepairRecordMapper.update(carDailyRepairRecordDO);
		} catch (Exception e) {
			logger.error("维修记录修改数据错误出错//{}....", e);
		}
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}
	
	/**
	 * @Title: insertRepair
	 * @Description: TODO
	 * @param carDailyRepairRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int insertRepair(CarDailyRepairRecordDO carDailyRepairRecordDO) {
		int result=0;
		try {
			 result = carDailyRepairRecordMapper.insert(carDailyRepairRecordDO);
		} catch (Exception e) {
			logger.error("维修记录存入数据错误出错//{}....", e);
		}
		if(result > 0){
		return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

}
