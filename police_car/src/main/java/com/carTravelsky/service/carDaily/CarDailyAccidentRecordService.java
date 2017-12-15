package com.carTravelsky.service.carDaily;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.mapper.carDaily.CarDailyAccidentRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyAccidentRecordService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarDailyAccidentRecordMapper carDailyAccidentRecordMapper;

	/**
	 * 保存carDailyAccidentRecordDO
	 *
	 */
	@Transactional
	public void saveCarDailyAccidentRecord(
			CarDailyAccidentRecordDO carDailyAccidentRecordDO)
			throws ServiceException {
		try {
			if (carDailyAccidentRecordDO.getId() == null) {
				carDailyAccidentRecordDO.setCreateTime(new Date());
				carDailyAccidentRecordMapper.insert(carDailyAccidentRecordDO);
			} else {
				carDailyAccidentRecordDO.setUpdateTime(new Date());
				carDailyAccidentRecordMapper.update(carDailyAccidentRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Title: 插入车辆事故记录
	 * @Description: TODO
	 * @param carDailyAccidentRecordDO
	 * @return
	 * @throws ServiceException
	 * @return: int
	 * @author: wangyu
	 * @date: 2017年10月12日 下午1:25:13
	 */
	@Transactional
	public int insert(CarDailyAccidentRecordDO carDailyAccidentRecordDO)
			throws ServiceException {
		try {
			return carDailyAccidentRecordMapper
					.insert(carDailyAccidentRecordDO);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * @Title: 根据指定id删除车辆记录
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: int
	 * @author: wangyu
	 * @date: 2017年10月12日 下午3:58:19
	 */
	@Transactional
	public int delete(Integer id) {
		return carDailyAccidentRecordMapper.delete(id);
	}

	/**
	 * 根据id获取对象
	 *
	 */
	@Transactional
	public CarDailyAccidentRecordDO getCarDailyAccidentRecordByID(Integer id) {
		return carDailyAccidentRecordMapper.getByID(id);
	}

	/**
	 * @Title: deleteIncomplete
	 * @Description: 不完全删除,将删除状态码设置为2
	 * @param id
	 * @author: wangyu
	 * @date: 2017年10月16日 下午2:12:54
	 */
	@Transactional
	public int deleteIncomplete(Integer id) {
		return carDailyAccidentRecordMapper.deleteIncomplete(id);
	}

	/**
	 * 根据carDailyAccidentRecordDO查询列表
	 *
	 */
	@Transactional
	public List<CarDailyAccidentRecordDO> getCarDailyAccidentRecordList(
			CarDailyAccidentRecordDO carDailyAccidentRecordDO) {
		return carDailyAccidentRecordMapper.getList(carDailyAccidentRecordDO);
	}

	/**
	 * 根据carDailyAccidentRecordDO查询分页列表
	 *
	 */
	@Transactional
	public List<CarDailyAccidentRecordDO> getCarDailyAccidentRecordList(
			CarDailyAccidentRecordDO carDailyAccidentRecordDO,
			PageBounds pageBounds) {
		return carDailyAccidentRecordMapper.getList(carDailyAccidentRecordDO,
				pageBounds);
	}

	/**
	 * @Title: 全局搜索
	 * @Description: TODO
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyAccidentRecordDO>
	 * @author: wangyu
	 * @date: 2017年10月12日 上午10:55:15
	 */
	@Transactional
	public List<CarDailyAccidentRecordDO> getsearchCarDailyAccidentRecord(
			String searchStr, PageBounds pageBounds) {
		return carDailyAccidentRecordMapper.getsearchCarDailyAccidentRecord(
				searchStr, pageBounds);
	}
}