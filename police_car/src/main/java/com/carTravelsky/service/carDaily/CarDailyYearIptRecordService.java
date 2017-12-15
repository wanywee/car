package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.carTravelsky.bean.carDaily.CarDailyYearIptRecordDO;
import com.carTravelsky.mapper.carDaily.CarDailyYearIptRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;


@Component
public class CarDailyYearIptRecordService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyYearIptRecordMapper carDailyYearIptRecordMapper;

	/**
	 * 保存carDailyYearIptRecordDO
	 *
	 */
	@Transactional
	public void saveCarDailyYearIptRecord(
			CarDailyYearIptRecordDO carDailyYearIptRecordDO)
			throws ServiceException {
		try {
			if (carDailyYearIptRecordDO.getId() == null) {
				carDailyYearIptRecordDO.setCreateTime(new Date());
				carDailyYearIptRecordMapper.insert(carDailyYearIptRecordDO);
			} else {
				carDailyYearIptRecordDO.setUpdateTime(new Date());
				CarDailyYearIptRecordDO cdyDo = carDailyYearIptRecordMapper.getByID(carDailyYearIptRecordDO.getId());
			     String oldUrl = cdyDo.getPhotoUrl();
			     oldUrl = new String(oldUrl.getBytes("iso8859-1"),"utf-8");
			     String[] oldArrayUrl = null;
					if (StringUtils.isNotBlank(oldUrl)) {
						oldArrayUrl = oldUrl.split(",");
					}
			     String newUrl =carDailyYearIptRecordDO.getPhotoUrl();
			     newUrl = new String(newUrl.getBytes("iso8859-1"),"utf-8");
			     String[] newArrayUrl=newUrl.split(",");
			    List<String> list =new ArrayList<String>();
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
			     String url="";
			     for (String string : list) {
			    	 url=url+string+",";
					
				}
			     String  urlo=url+oldUrl;
			     if(urlo.startsWith(",")){
			    	 urlo=urlo.substring(1,urlo.length());
			     }
			     if(urlo.endsWith(",")){
			    	 urlo=urlo.substring(0,urlo.length()-1);
			     }
			     
			     carDailyYearIptRecordDO.setPhotoUrl(urlo);
				carDailyYearIptRecordMapper.update(carDailyYearIptRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * @Title: saveCarDailyYearIptRecordDo
	 * @Description: 图片删除时保存其对象
	 * @param carDailyYearIptRecordDO
	 * @throws ServiceException
	 * @return: void
	 * @author: wangyu  
	 * @date: 2017年12月12日 上午10:21:15
	 */
	@Transactional
	public void saveCarDailyYearIptRecordDo(CarDailyYearIptRecordDO carDailyYearIptRecordDO)throws ServiceException {
		
		carDailyYearIptRecordMapper.update(carDailyYearIptRecordDO);
		
	}
	
	
	
	/**
	 * 根据id获取对象
	 *
	 */
	@Transactional
	public CarDailyYearIptRecordDO getCarDailyYearIptRecordByID(Integer id) {
		return carDailyYearIptRecordMapper.getByID(id);
	}

	/**
	 * 根据carDailyYearIptRecordDO查询列表
	 *
	 */
	@Transactional
	public List<CarDailyYearIptRecordDO> getCarDailyYearIptRecordList(
			CarDailyYearIptRecordDO carDailyYearIptRecordDO) {
		return carDailyYearIptRecordMapper.getList(carDailyYearIptRecordDO);
	}

	/**
	 * 根据carDailyYearIptRecordDO查询分页列表
	 *
	 */
	@Transactional
	public List<CarDailyYearIptRecordDO> getCarDailyYearIptRecordList(
			CarDailyYearIptRecordDO carDailyYearIptRecordDO,
			PageBounds pageBounds) {
		return carDailyYearIptRecordMapper.getList(carDailyYearIptRecordDO,
				pageBounds);
	}

	/**
	 * 根据指定剩余天数统计年检过期数量
	 * 
	 */
	@Transactional
	public int countExpire(int reminDdate) {
		return carDailyYearIptRecordMapper.countExpire(reminDdate);
	}
	/**
	 * 根据指定剩余天数统计年检过期数量by部门
	 * 
	 */
	public int countExpireById(int reminDdate,PageBounds pageBounds) {
		return carDailyYearIptRecordMapper.countExpireById(reminDdate,pageBounds);
	}
	/**
	 * @Title: deleteIncomplete
	 * @Description: 不完全删除
	 * @param id
	 * @return
	 * @return: int
	 * @author: wangyu  
	 * @date: 2017年10月31日 上午10:52:51
	 */
	@Transactional
	public int  deleteIncomplete(Integer id){
		return carDailyYearIptRecordMapper.deleteIncomplete(id);
	}
	
	/**
	 * @Title: getsearchCarDailyYearIPTRecord
	 * @Description: 全局搜索
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 * @return: List<CarDailyYearIptRecordDO>
	 * @author: wangyu  
	 * @date: 2017年10月31日 下午3:35:46
	 */
	@Transactional
	public  List<CarDailyYearIptRecordDO> getsearchCarDailyYearIPTRecord(String searchStr, PageBounds pageBounds){
		return carDailyYearIptRecordMapper.getsearchCarDailyYearIPTRecord(searchStr, pageBounds);
	} 
	
	/**
	 * @Title: expireRecordList
	 * @Description: 到期的年检记录列表
	 * @param count
	 * @return
	 * @return: List<CarDailyYearIptRecordDO>
	 * @author: wangyu  
	 * @date: 2017年11月1日 上午10:30:45
	 */
	@Transactional
	public List<CarDailyYearIptRecordDO> expireRecordList(Integer count,PageBounds pageBounds){
		return carDailyYearIptRecordMapper.expireRecordList(count,pageBounds);
	}
	
}