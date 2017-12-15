package com.carTravelsky.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.system.CarSysCarTypeDO;
import com.carTravelsky.mapper.system.CarSysCarTypeMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarSysCarTypeService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarSysCarTypeMapper carSysCarTypeMapper;

	/**
	 * 保存carSysCarTypeDO
	 *
	 */
	public void saveCarSysCarType(CarSysCarTypeDO carSysCarTypeDO)
			throws ServiceException {
		try {
			if (carSysCarTypeDO.getId() == null) {
				carSysCarTypeMapper.insert(carSysCarTypeDO);
			} else {
				carSysCarTypeMapper.update(carSysCarTypeDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarSysCarTypeDO getCarSysCarTypeByID(Integer id) {
		return carSysCarTypeMapper.getByID(id);
	}

	/**
	 * 根据carSysCarTypeDO查询列表
	 *
	 */
	public List<CarSysCarTypeDO> getCarSysCarTypeList(
			CarSysCarTypeDO carSysCarTypeDO) {
		return carSysCarTypeMapper.getList(carSysCarTypeDO);
	}

	/**
	 * 根据carSysCarTypeDO查询分页列表
	 *
	 */
	public List<CarSysCarTypeDO> getCarSysCarTypeList(
			CarSysCarTypeDO carSysCarTypeDO, PageBounds pageBounds) {
		return carSysCarTypeMapper.getList(carSysCarTypeDO, pageBounds);
	}

	/**
	 * 
	 * @Title: getCarSysCarType
	 * @Description: 根据CarSysCarType类的typeName、modelName、colorName、brandName
	 *               查询数据
	 * @param carSysCarTypeDO
	 * @return
	 * @return: CarSysCarTypeDO
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午9:59:56
	 */
	public CarSysCarTypeDO getCarSysCarType(CarSysCarTypeDO carSysCarTypeDO) {
		return carSysCarTypeMapper.getCarSysCarType(carSysCarTypeDO);
	}

}