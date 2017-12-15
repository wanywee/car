package com.carTravelsky.service.app;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.mapper.app.AppCarAccidentMapper;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.carDaily.CarDailyAccidentRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarAccidentService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:21:12
 */
@Component
public class AppCarAccidentService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarAccidentMapper accidentMapper;
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	@Autowired
	private CarDailyAccidentRecordMapper carDailyAccidentRecordMapper;
	/**
	 * @Title: searchCarIdByNumber
	 * @Description: TODO
	 * @param licenseNumber
	 * @return
	 * @return: Integer
	 */
	public Integer searchCarIdByNumber(String licenseNumber) {
		//检查当前车辆是否存在
		String checkCarNumber = appCarRefueMapper.checkCarNumberIsHave(licenseNumber);
		if(StringUtils.isBlank(checkCarNumber)){
			return CodeCondition.NULL;
		}
		Integer carId = accidentMapper.searchCarIdByNumber(licenseNumber);
		carId = carId == null?0:carId;
		return carId;
	}
	

	/**
	 * @Title: saveDataInside
	 * @Description: TODO
	 * @param carDailyAccidentRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int saveDataInside(CarDailyAccidentRecordDO carDailyAccidentRecordDO,String username) {
		int result = 0;
		
		try {
		Integer isHave  = accidentMapper
					.searchAccidentIsHave(
							carDailyAccidentRecordDO.getCarID(),
							carDailyAccidentRecordDO.getDriver(),
							carDailyAccidentRecordDO.getAccidentDate());
		if(null == isHave){
			carDailyAccidentRecordDO.setCreatePeople(username);
			carDailyAccidentRecordDO.setCreateTime(DateTools.getCurrentDate());
			return result = this.insertAccident(carDailyAccidentRecordDO);
		}
		{
			carDailyAccidentRecordDO.setId(isHave);
			carDailyAccidentRecordDO.setUpdateTime(DateTools.getCurrentDate());
			carDailyAccidentRecordDO.setUpdatePeople(username);
			return result = this.updateAccident(carDailyAccidentRecordDO);
		}
		} catch (Exception e) {
			logger.error("事故记录存入数据错误出错//{}....", e);
		}
		return result;
	}

	
	/**
	 * @Title: updateAccident
	 * @Description: TODO
	 * @param carDailyAccidentRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int updateAccident(
			CarDailyAccidentRecordDO carDailyAccidentRecordDO) {
		int result=0;
		try {
			result = carDailyAccidentRecordMapper.update(carDailyAccidentRecordDO);
		} catch (Exception e) {
			logger.error("事故记录修改数据错误出错//{}....", e);
		}
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}


	/**
	 * @Title: insertAccident
	 * @Description: TODO
	 * @param carDailyAccidentRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int insertAccident(CarDailyAccidentRecordDO carDailyAccidentRecordDO) {
		int result=0;
		try {
			 result = carDailyAccidentRecordMapper.insert(carDailyAccidentRecordDO);
		} catch (Exception e) {
			logger.error("事故记录存入数据错误出错//{}....", e);
		}
		if(result > 0){
		return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

}
