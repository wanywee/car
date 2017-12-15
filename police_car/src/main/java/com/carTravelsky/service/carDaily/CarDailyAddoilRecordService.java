package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyYearIptRecordDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarDailyAddoilRecordMapper;
import com.carTravelsky.mapper.system.KeyCodeMasterMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarDailyAddoilRecordService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyAddoilRecordMapper carDailyAddoilRecordMapper;
	@Autowired
	private KeyCodeMasterMapper codeMasterMapper;

	/**
	 * 保存carDailyAddoilRecordDO
	 *
	 */
	public void saveCarDailyAddoilRecord(
			CarDailyAddoilRecordDO carDailyAddoilRecordDO)
			throws ServiceException {
		try {
			if (carDailyAddoilRecordDO.getId() == null) {
				carDailyAddoilRecordMapper.insert(carDailyAddoilRecordDO);
			} else {
				CarDailyAddoilRecordDO cdyDo = carDailyAddoilRecordMapper
						.getByID(carDailyAddoilRecordDO.getId());
				String oldUrl = cdyDo.getPhotoUrl();
				String[] oldArrayUrl = null;
				if (StringUtils.isNotBlank(oldUrl)) {
					oldArrayUrl = oldUrl.split(",");
				}
				String newUrl = carDailyAddoilRecordDO.getPhotoUrl();
				String[] newArrayUrl = newUrl.split(",");
				List<String> list = new ArrayList<String>();
				if (StringUtils.isNotBlank(newUrl)) {
					for (String string : newArrayUrl) {
						if (oldArrayUrl == null) {
							list.add(string);
						} else {
							for (String string1 : oldArrayUrl) {
								if (!string.equals(string1)) {
									if (!list.contains(string)) {
										list.add(string);
									}
								}
							}
						}

					}
				}
				String url = "";
				for (String string : list) {
					url = url + string + ",";

				}
				String urlo = url + oldUrl;
				if (urlo.startsWith(",")) {
					urlo = urlo.substring(1, urlo.length());
				}
				if (urlo.endsWith(",")) {
					urlo = urlo.substring(0, urlo.length() - 1);
				}

				carDailyAddoilRecordDO.setPhotoUrl(urlo);
				carDailyAddoilRecordMapper.update(carDailyAddoilRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 加油记录图片保存carDailyAddoilRecordDO
	 *
	 */
	public void saveCarDailyAddoilRecordDO(
			CarDailyAddoilRecordDO carDailyAddoilRecordDO)
			throws ServiceException {
		try {
			carDailyAddoilRecordMapper.update(carDailyAddoilRecordDO);
		} catch (Exception e) {
			new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarDailyAddoilRecordDO getCarDailyAddoilRecordByID(Integer id) {
		return carDailyAddoilRecordMapper.getByID(id);
	}

	/**
	 * 根据carDailyAddoilRecordDO查询列表
	 *
	 */
	public List<CarDailyAddoilRecordDO> getCarDailyAddoilRecordList(
			CarDailyAddoilRecordDO carDailyAddoilRecordDO) {
		return carDailyAddoilRecordMapper.getList(carDailyAddoilRecordDO);
	}

	/**
	 * 根据carDailyAddoilRecordDO查询分页列表
	 *
	 */
	public List<CarDailyAddoilRecordDO> getCarDailyAddoilRecordList(
			CarDailyAddoilRecordDO carDailyAddoilRecordDO, PageBounds pageBounds) {
		return carDailyAddoilRecordMapper.getList(carDailyAddoilRecordDO,
				pageBounds);
	}

	/**
	 * 根据id删除
	 * 
	 * @param iD
	 */
	public void deleteAddoilRecord(List<String> idList) {
		carDailyAddoilRecordMapper.updateDeleteCode(idList);
	}

	/**
	 * 全局搜索
	 * 
	 * @param searchStr
	 * @param pageBounds
	 */
	public List<CarDailyAddoilRecordDO> getSearchAddoilRecordList(
			String searchStr, PageBounds pageBounds, Integer companyId) {
		return carDailyAddoilRecordMapper.getSearchAddoilRecordList(searchStr,
				pageBounds, companyId);
	}

	/**
	 * 获取燃油标号下拉菜单
	 */
	public List<Select2VO> getSelect2ListFueloil(String str) {
		return codeMasterMapper.getSelect2ListFueloil(str);
	}

}