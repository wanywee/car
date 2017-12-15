package com.carTravelsky.service.carDaily;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyInsuranceRecordDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.mapper.carDaily.CarDailyInsuranceRecordMapper;
import com.carTravelsky.mapper.system.KeyCodeMasterMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyInsuranceRecordService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyInsuranceRecordMapper carDailyInsuranceRecordMapper;
	@Autowired
    private KeyCodeMasterMapper codeMasterMapper;

	/**
	 * 保存carDailyInsuranceRecordDO
	 *
	 */
	public void saveCarDailyInsuranceRecord(
			CarDailyInsuranceRecordDO carDailyInsuranceRecordDO)
			throws ServiceException {
		try {
			if (carDailyInsuranceRecordDO.getId() == null) {
				carDailyInsuranceRecordMapper.insert(carDailyInsuranceRecordDO);
			} else {
				carDailyInsuranceRecordMapper.update(carDailyInsuranceRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarDailyInsuranceRecordDO getCarDailyInsuranceRecordByID(Integer id) {
		return carDailyInsuranceRecordMapper.getByID(id);
	}

	/**
	 * 根据carDailyInsuranceRecordDO查询列表
	 *
	 */
	public List<CarDailyInsuranceRecordDO> getCarDailyInsuranceRecordList(
			CarDailyInsuranceRecordDO carDailyInsuranceRecordDO) {
		return carDailyInsuranceRecordMapper.getList(carDailyInsuranceRecordDO);
	}

	/**
	 * 根据carDailyInsuranceRecordDO查询分页列表
	 *
	 */
	public List<CarDailyInsuranceRecordDO> getCarDailyInsuranceRecordList(
			CarDailyInsuranceRecordDO carDailyInsuranceRecordDO,
			PageBounds pageBounds) {
		return carDailyInsuranceRecordMapper.getList(carDailyInsuranceRecordDO,
				pageBounds);
	}

	/**
	 * 根据指定剩余天数统计保险过期数量
	 * 
	 */
	@Transactional
	public int countExpire(int reminDdate) {
		return carDailyInsuranceRecordMapper.countExpire(reminDdate);
	}
	/**
	 * 根据指定剩余天数统计保险过期数各个部门
	 * 
	 */
	@Transactional
	public int countExpireBycomID(int reminDdate, PageBounds pageBounds) {
		return carDailyInsuranceRecordMapper.countExpireBycomID(reminDdate,pageBounds);
	}
	
	/**
	 *  批量删除
	 */
	public void updateDeletecode(List<String> asList) {
		carDailyInsuranceRecordMapper.updateDeletecode(asList);
	}

	public List<Select2VO> getSelect2InsuranceType(String str) {
		return codeMasterMapper.getSelect2InsuranceType(str);
	}

	public List<CarDailyInsuranceRecordDO> getListCarInsuranceSearchStr(String searchStr, PageBounds pageBounds,
			Integer companyId) {
		return carDailyInsuranceRecordMapper.getListCarInsuranceSearchStr(searchStr,pageBounds,companyId);
	}
	
	/**
	 * @Title: getExpireList
	 * @Description: 得到保险到期提醒列表
	 * @param reminDdate 指定剩余的过期天数
	 * @return
	 * @return: List<CarDailyInsuranceRecordDO>
	 * @author: wangyu  
	 * @date: 2017年12月6日 下午4:17:51
	 */
	public List<CarDailyInsuranceRecordDO> getExpireList(Integer reminDdate,PageBounds pageBounds){
		return carDailyInsuranceRecordMapper.getExpireList(reminDdate,pageBounds);
	}
}