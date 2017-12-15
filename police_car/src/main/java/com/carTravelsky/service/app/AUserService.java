package com.carTravelsky.service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.mapper.carDaily.CarBaseStaffMapper;
import com.carTravelsky.mapper.system.CarSysUserMapper;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @ClassName: AUserService
 * @Description: 登陆
 * @author: wangyi
 * @date: 2017年10月11日 下午4:13:26
 */
@Component
public class AUserService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CarSysUserMapper carSysUserMapper;
	@Autowired
	private CarBaseStaffMapper carBaseStaffMapper;

	/**
	 * @Title: searchByName
	 * @Description: 根据用户名称获取当前用户
	 * @param username
	 * @return
	 * @throws GlobalException
	 * @return: CarSysUserDO
	 */
	public Integer searchByName(String username) {
		CarSysUserDO user = carSysUserMapper.searchByName(username);
		if (null == user) {
			return 0;
		}
		return user.getId();
	}

	/**
	 * @Title: searchWorkType
	 * @Description: 根据ID获取登录人员工作类型
	 * @param id
	 * @return
	 * @throws GlobalException
	 * @return: Integer
	 */
	public Integer searchWorkType(Integer id) {
		CarSysUserDO us = carSysUserMapper.getByID(id);
		if (null == us) {
			return 0;
		} else {
			return us.getLoginWorkType();
		}
	}

	/**
	 * @Title: searchUsername
	 * @Description: 根据ID获取登录人员名称
	 * @param id
	 * @return
	 * @throws GlobalException
	 * @return: String
	 */
	public String searchUsername(Integer id) {
		CarSysUserDO us = carSysUserMapper.getByID(id);
		if (null == us) {
			return null;
		} else {
			return us.getUsername();
		}
	}

	/**
	 * @Title: searchDeptID
	 * @Description: 根据用户名获取公司ID
	 * @param id
	 * @return
	 * @throws GlobalException
	 * @return: Integer
	 * @author: wangyu
	 * @date: 2017年11月15日 上午11:05:26
	 */
	public Integer getCompanyId(String username) {
		CarSysUserDO user = carSysUserMapper.searchByName(username);
		if (null == user) {
			return null;
		} else {
			return user.getCompanyId();
		}
	}

	/**
	 * @Title: getUser
	 * @Description: 根据用户名获取用户
	 * @param username
	 * @return
	 * @return: CarSysUserDO
	 * @author: wangyu  
	 * @date: 2017年11月15日 下午4:55:36
	 */
	public CarSysUserDO getUser(String username) {
		CarSysUserDO user = carSysUserMapper.searchByName(username);
		if (null == user) {
			return null;
		} else {
			return user;
		}
	}
	
	/**
	 * @Title: getHandlerIdByName
	 * @Description: 根据经手人名字查询ID
	 * @param handlerName
	 * @return
	 * @return: Integer
	 */
	public Integer getHandlerIdByName(String handlerName){
		Integer handlerId = carBaseStaffMapper.searchHandlerIdByName(handlerName);
		handlerId = handlerId == null?-1:handlerId;
		return handlerId;
	}
	
	/**
	 * @Title: getHandlerNameById
	 * @Description: 根据经手人id查询名称
	 * @param handlerId
	 * @return
	 * @return: String
	 */
	public String getHandlerNameById(Integer handlerId){
		return carBaseStaffMapper.searchHandlerNameById(handlerId);
	}
	
	
	/**
	 * @Title: getPhoneByUsername
	 * @Description: 根据员工名称获取电话号码
	 * @param name
	 * @return
	 * @return: String
	 */
	public CarBaseStaffDO getPhoneByUsername(String name){
		return carBaseStaffMapper.getPhoneByUsername(name);
	};
}
