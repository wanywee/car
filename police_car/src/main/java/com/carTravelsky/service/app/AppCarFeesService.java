package com.carTravelsky.service.app;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.carDaily.CarDailyFeeManageRecordDO;
import com.carTravelsky.mapper.app.AppCarFeesMapper;
import com.carTravelsky.mapper.app.AppCarInsurMapper;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.carDaily.CarDailyFeeManageRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarFeesService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:29:04
 */
@Component
public class AppCarFeesService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarInsurMapper appCarInsurMapper;
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	@Autowired
	private AppCarFeesMapper appCarFeesMapper;
	@Autowired
	private CarDailyFeeManageRecordMapper carDailyFeeManageRecordMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int carAddFeesInsertOrUpdate(
			String username,
			Integer companyId,
			Integer userId,
			String licenseNumber,
			String moneyName,
			Float paymentMoney,
			Date paymentDate,
			Date endDate,
			Integer chargeUnitID,
			String paymentType,
			String remark,
			String period)
	{
		int result = 0;
			CarDailyFeeManageRecordDO carDailyFeeManageRecordDO = new CarDailyFeeManageRecordDO();
			carDailyFeeManageRecordDO.setHandler(username);
			
			carDailyFeeManageRecordDO.setMoneyname(moneyName);
			carDailyFeeManageRecordDO.setParmentMoney(paymentMoney);
			carDailyFeeManageRecordDO.setPaymentDate(paymentDate);
			carDailyFeeManageRecordDO.setEndDate(endDate);
			
			carDailyFeeManageRecordDO.setPaymentType(paymentType);
			carDailyFeeManageRecordDO.setRemark(remark);
			carDailyFeeManageRecordDO.setPeriod(period);
			carDailyFeeManageRecordDO.setCreateTime(DateTools.getCurrentDate());
			carDailyFeeManageRecordDO.setCreatePeople(username);
			
			//车辆ID
			Integer carId = appCarRefueMapper.searchCarIdByNo(licenseNumber);
			carId = carId == null ? 0 : carId;
			carDailyFeeManageRecordDO.setCarID(carId);
			//收费单位
			Integer companyIDByName = appCarInsurMapper.getCompanyIDByName(chargeUnitID,CodeCondition.COMPANY_TYPE_SFDW);
			companyIDByName = companyIDByName == null ? 0 : companyIDByName;
			carDailyFeeManageRecordDO.setChargeUnitID(companyIDByName);
			
			//查询数据库是够存在当前时间当前车辆的信息
			Integer isHave = appCarFeesMapper.searchFeesainRecodrIsHave(carId,paymentDate,username);
			if(null == isHave){
				//新增
				result = this.carInsert(carDailyFeeManageRecordDO);
			}
			if(null != isHave){
				//修改
				result  = this.carUpdate(carDailyFeeManageRecordDO,username,isHave);
			}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carUpdate(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO,String username,Integer id) {
		int result = 0;
		carDailyFeeManageRecordDO.setId(id);
		carDailyFeeManageRecordDO.setUpdateTime(DateTools.getCurrentDate());
		carDailyFeeManageRecordDO.setUpdatePeople(username);
		result = carDailyFeeManageRecordMapper.update(carDailyFeeManageRecordDO);
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carInsert(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO) {
		int result = 0;
		result = carDailyFeeManageRecordMapper.insert(carDailyFeeManageRecordDO);
		if(result > 0){
			return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

	
	/**
	 * @Title: getFeesNameList
	 * @Description: 规费费用名称列表
	 * @param feesName
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getFeesNameList(String feesName) {
		List<GeneralRS> list = appCarFeesMapper.getFeesList(feesName);
		return list;
	}

	/**
	 * @Title: getFeesUnitsList
	 * @Description: 缴费单位列表
	 * @param unitsName
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getFeesUnitsList(String unitsName) {
		List<GeneralRS> list = appCarFeesMapper.getFeesList(unitsName);
		return list;
	}
	
	/**
	 * @Title: getFeesTypeList
	 * @Description: 缴费方式列表
	 * @param typeName
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getFeesTypeList(String typeName) {
		List<GeneralRS> list = appCarFeesMapper.getFeesList(typeName);
		return list;
	}
	
}
