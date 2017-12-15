package com.carTravelsky.service.app;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.app.response.GeneralRS;
import com.carTravelsky.bean.carDaily.CarDailyInsuranceRecordDO;
import com.carTravelsky.mapper.app.AppCarInsurMapper;
import com.carTravelsky.mapper.app.AppCarRefueMapper;
import com.carTravelsky.mapper.carDaily.CarDailyInsuranceRecordMapper;
import com.carTravelsky.utils.app.DateTools;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AppCarInsurService
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:30:40
 */
@Component
public class AppCarInsurService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AppCarInsurMapper appCarInsurMapper;
	@Autowired
	private AppCarRefueMapper appCarRefueMapper;
	
	@Autowired
	private CarDailyInsuranceRecordMapper carDailyInsuranceRecordMapper;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public int carAddInsureInsertOrUpdate(String username,Integer userId,Integer companyId,String licenseNumber,
			String policyNumber,Integer insuranceType,Date insureDate,Date endDate,Integer insuranceCompanyId,Float insureMoney,
			String insuranceRemark,String remark)
	{
		int result = 0;
			CarDailyInsuranceRecordDO carDailyInsuranceRecordDO = new CarDailyInsuranceRecordDO();
			carDailyInsuranceRecordDO.setHandler(userId);
			carDailyInsuranceRecordDO.setPolicyNumber(policyNumber);
			carDailyInsuranceRecordDO.setInsuranceType(insuranceType);
			carDailyInsuranceRecordDO.setInsureDate(insureDate);
			carDailyInsuranceRecordDO.setEndDate(endDate);
			carDailyInsuranceRecordDO.setInsureMoney(insureMoney);
			carDailyInsuranceRecordDO.setInsuranceRemark(insuranceRemark);
			carDailyInsuranceRecordDO.setInsuranceRemark(remark);
			carDailyInsuranceRecordDO.setCreateTime(DateTools.getCurrentDate());
			carDailyInsuranceRecordDO.setCreatePeople(username);
			carDailyInsuranceRecordDO.setCompanyid(companyId);
			//车辆ID
			Integer carId = appCarRefueMapper.searchCarIdByNo(licenseNumber);
			carId = carId == null ? 0 : carId;
			carDailyInsuranceRecordDO.setCarID(carId);
			//保险公司ID
			Integer companyIDByName = appCarInsurMapper.getCompanyIDByName(insuranceCompanyId,CodeCondition.COMPANY_TYPE);
			companyIDByName = companyIDByName == null ? 0 : companyIDByName;
			carDailyInsuranceRecordDO.setInsuranceCpID(companyIDByName);
			//查询数据库是够存在当前时间当前车辆的信息
			Integer isHave = appCarInsurMapper.searchInsurRecodrIsHave(carId,insureDate,username);
			if( null == isHave){
				//新增
				result = this.carInsert(carDailyInsuranceRecordDO);
			}
			if(null != isHave){
				//修改
				result  = this.carUpdate(carDailyInsuranceRecordDO,username,isHave);
			}
		return result;
	}
	
	/**
	 * @Title: carUpdate
	 * @Description: TODO
	 * @param carDailyInsuranceRecordDO
	 * @param username
	 * @return
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年12月4日 下午3:53:27
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carUpdate(CarDailyInsuranceRecordDO carDailyInsuranceRecordDO,String username,Integer id) {
		int result = 0;
			carDailyInsuranceRecordDO.setId(id);
			carDailyInsuranceRecordDO.setUpdateTime(DateTools.getCurrentDate());
			carDailyInsuranceRecordDO.setUpdatePeople(username);
			result = carDailyInsuranceRecordMapper.update(carDailyInsuranceRecordDO);
		if(result > 0){
			return result = CodeCondition.THIRD;
		}
		return result = CodeCondition.FOUTH;
	}
	
	/**
	 * @Title: carInsert
	 * @Description: TODO
	 * @param carDailyInsuranceRecordDO
	 * @return
	 * @return: int
	 * @author: THINK  
	 * @date: 2017年12月4日 下午3:55:51
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	private int carInsert(CarDailyInsuranceRecordDO carDailyInsuranceRecordDO) {
		int result = 0;
			result = carDailyInsuranceRecordMapper.insert(carDailyInsuranceRecordDO);
		if(result > 0){
			return result = CodeCondition.FIRST;	
		}
		return result = CodeCondition.SECOND;
	}

	
	/**
	 * @Title: getinsurCompanyList
	 * @Description: 保险公司列表
	 * @param companyType
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getInsurCompanyList(String companyType) {
		List<GeneralRS> compnayList = appCarInsurMapper.getInsurCompanyList(companyType);
		return compnayList;
	}
	
	
	/**
	 * @Title: getInsurTypeList
	 * @Description: 保险种类
	 * @param insurancetype
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getInsurTypeList(String insurancetype) {
		List<GeneralRS> typeList = appCarInsurMapper.getInsurTypeList(insurancetype);
		return typeList;
	}

	/**
	 * @Title: getHandlersList
	 * @Description: 经手人列表
	 * @return
	 * @return: List<GeneralRS>
	 */
	public List<GeneralRS> getHandlersList() {
		List<GeneralRS> handlerList = appCarInsurMapper.getHandlersList();
		return handlerList;
	}
}
