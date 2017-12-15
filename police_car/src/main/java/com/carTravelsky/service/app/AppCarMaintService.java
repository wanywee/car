package com.carTravelsky.service.app;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.mapper.app.AppCarInsurMapper;
import com.carTravelsky.mapper.app.AppCarMaintMapper;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.carDaily.CarDailyMaintainRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarMaintService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:23:33
 */
@Component
public class AppCarMaintService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarInsurMapper appCarInsurMapper;
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	@Autowired
	private AppCarMaintMapper appCarMaintMapper;
	@Autowired
	private CarDailyMaintainRecordMapper carDailyMaintainRecordMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int carAddMaintainInsertOrUpdate(
			String username,
			Integer companyId,
			Integer userId,
			String licenseNumber,
			String maintainType,
			Date maintainDate,
			Date endDate,
			Integer maintainUnit,
			Float maintainMileage,
			Float maintainMoney,
			String maintainContent,
			String remark)
	{
		int result = 0;
			CarDailyMaintainRecordDO carDailyMaintainRecordDO = new CarDailyMaintainRecordDO();
			carDailyMaintainRecordDO.setHandleid(userId);
			carDailyMaintainRecordDO.setCompanyId(companyId);
			carDailyMaintainRecordDO.setMaintainType(maintainType);
			carDailyMaintainRecordDO.setMaintainDate(maintainDate);
			carDailyMaintainRecordDO.setEndDate(endDate);
			carDailyMaintainRecordDO.setMaintainMileage(maintainMileage);
			carDailyMaintainRecordDO.setMaintainMoney(maintainMoney);
			carDailyMaintainRecordDO.setRemark(remark);
			carDailyMaintainRecordDO.setMaintainContent(maintainContent);
			carDailyMaintainRecordDO.setCreatePeople(username);
			carDailyMaintainRecordDO.setCreateTime(DateTools.getCurrentDate());
			
			//车辆ID
			Integer carId = appCarRefueMapper.searchCarIdByNo(licenseNumber);
			carId = carId == null ? 0 : carId;
			carDailyMaintainRecordDO.setCarID(carId);
			//保险公司ID
			Integer companyIDByName = appCarInsurMapper.getCompanyIDByName(maintainUnit,CodeCondition.COMPANY_TYPE_BYZ);
			companyIDByName = companyIDByName == null ? 0 : companyIDByName;
			carDailyMaintainRecordDO.setMaintainUnit(companyIDByName);
			
			//查询数据库是够存在当前时间当前车辆的信息
			Integer isHave = appCarMaintMapper.searchMaintainRecodrIsHave(carId,maintainDate,username);
			if(null == isHave){
				//新增
				result = this.carInsert(carDailyMaintainRecordDO);
			}
			if(null != isHave){
				//修改
				result  = this.carUpdate(carDailyMaintainRecordDO,username,isHave);
			}
		return result;
	}
	
	/**
	 * @Title: carUpdate
	 * @Description: TODO
	 * @param carDailyMaintainRecordDO
	 * @param username
	 * @return
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年12月4日 下午5:07:15
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carUpdate(CarDailyMaintainRecordDO carDailyMaintainRecordDO,String username,Integer id) {
		int result = 0;
			carDailyMaintainRecordDO.setId(id);
			carDailyMaintainRecordDO.setUpdateTime(DateTools.getCurrentDate());
			carDailyMaintainRecordDO.setUpdatePeople(username);
			result = carDailyMaintainRecordMapper.update(carDailyMaintainRecordDO);
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}
	
	/**
	 * @Title: carInsert
	 * @Description: TODO
	 * @param carDailyMaintainRecordDO
	 * @return
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年12月4日 下午5:07:13
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carInsert(CarDailyMaintainRecordDO carDailyMaintainRecordDO) {
		int result = 0;
			result = carDailyMaintainRecordMapper.insert(carDailyMaintainRecordDO);
		if(result > 0){
		return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}
}
