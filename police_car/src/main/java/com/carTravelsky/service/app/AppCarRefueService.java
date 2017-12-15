package com.carTravelsky.service.app;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.carDaily.CarDailyAddoilRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarRefueService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:18:56
 */
@Component
public class AppCarRefueService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	@Autowired
	private CarDailyAddoilRecordMapper carDailyAddoilRecordMapper;
	
	/**
	 * @Title: carAddOilInsertOrUpdate
	 * @Description: 加油数据录入
	 * @param oilType
	 * @param addOilNumber
	 * @param addOilMoney
	 * @param currentDate
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int carAddOilInsertOrUpdate(
			String handlerName,Integer handlerId,String username,Integer companyId,
			Integer userId,
			String licenseNumber, String oilType,
			Float addOilNumber, Float addOilMoney, Date currentDate) {
		int result = 0;
		
		//try {
			//检查当前车辆是否存在
			String checkCarNumber = appCarRefueMapper.checkCarNumberIsHave(licenseNumber);
			if(StringUtils.isBlank(checkCarNumber)){
				return result = CodeCondition.FIFTH;
			}
			CarDailyAddoilRecordDO carDailyAddoilRecordDO = new CarDailyAddoilRecordDO();
			carDailyAddoilRecordDO.setAddiolAmount(addOilNumber);
			carDailyAddoilRecordDO.setAddoilTime(currentDate);
			carDailyAddoilRecordDO.setCompanyId(companyId);
			carDailyAddoilRecordDO.setDeleteCode(CodeCondition.SUCCEED);
			carDailyAddoilRecordDO.setCreateTime(DateTools.getCurrentDate());
			carDailyAddoilRecordDO.setHandleid(handlerId);
			carDailyAddoilRecordDO.setPrice(addOilMoney/addOilNumber);
			carDailyAddoilRecordDO.setCreatePeople(username);
			carDailyAddoilRecordDO.setHandlename(handlerName);
			carDailyAddoilRecordDO.setSummoney(addOilMoney);
			//油料标号
			Integer oiType = appCarRefueMapper.searchOilIdByType(oilType);
			oiType = oiType == null?0:oiType;
			carDailyAddoilRecordDO.setOilseedLableID(oiType);
			//车辆ID
			Integer carId = appCarRefueMapper.searchCarIdByNo(licenseNumber);
			carId = carId == null?0:carId;
			carDailyAddoilRecordDO.setCarID(carId);
			
			//查询数据库是够存在当前时间当前车辆的信息
			Integer isHave = appCarRefueMapper.searchCarRecodrIsHave(carId, handlerId,currentDate);
			if(null == isHave){
				//新增
				result = this.carInsert(carDailyAddoilRecordDO);
			}
			if(null != isHave){
				//修改
				carDailyAddoilRecordDO.setId(isHave);
				result  = this.carUpdate(carDailyAddoilRecordDO,username);
			}
//		} catch (Exception e) {
//			logger.error("加油记录或者更新出错//{}....", e);
//		}
		return result;
	}

	
	/**
	 * @Title: carUpdate
	 * @Description: TODO
	 * @param carDailyAddoilRecordDO
	 * @param username
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carUpdate(CarDailyAddoilRecordDO carDailyAddoilRecordDO,String username) {
		int result = 0;
//		try {
			carDailyAddoilRecordDO.setUpdateTime(DateTools.getCurrentDate());
			carDailyAddoilRecordDO.setUpdatePeople(username);
			result = carDailyAddoilRecordMapper.update(carDailyAddoilRecordDO);
//		} catch (Exception e) {
//			logger.error("加油记录更新出错//{}....", e);
//		}
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}
	
	/**
	 * @Title: carInsert
	 * @Description: TODO
	 * @param carDailyAddoilRecordDO
	 * @return
	 * @return: int
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carInsert(CarDailyAddoilRecordDO carDailyAddoilRecordDO) {
		int result = 0;
	//	try {
			result = carDailyAddoilRecordMapper.insert(carDailyAddoilRecordDO);
//		} catch (Exception e) {
//			logger.error("加油记录新增出错//{}....", e);
//		}
		if(result > 0){
		return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

	
	/**
	 * @Title: getOilTypeList
	 * @Description: 获取油料列表
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getOilTypeList(String oilName) {
		List<GeneralRS> oils = appCarRefueMapper.getOilsList(oilName);
		return oils;
	}

}
