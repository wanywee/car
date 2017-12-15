package com.carTravelsky.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.system.CarSysUserRoleDO;
import com.carTravelsky.mapper.system.CarSysUserRoleMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;

@Component
public class CarSysUserRoleService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarSysUserRoleMapper carSysUserRoleMapper;

    /**
     * 保存carSysUserRoleDO
     *
     */
    public void saveCarSysUserRole(CarSysUserRoleDO carSysUserRoleDO) throws ServiceException {
        try{
            if(carSysUserRoleDO.getId() == null){
                carSysUserRoleMapper.insert(carSysUserRoleDO);
            }else {
                carSysUserRoleMapper.update(carSysUserRoleDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
   
    /**
     * 
     * @Title: saveCarSysUserRole 保存用户角色
     * @Description:  通过isNull 判断 是否新增或是编辑
     * @param splitIds  角色列表
     * @param isNull  新增 编辑
     * @param userID   用户id
     * @throws ServiceException
     * @return: void
     * @author: fuxinrong
     * @date: 2017年10月18日 上午11:00:03
     */
    public void saveCarSysUserRole(String[] splitIds,String isNull,Integer userID,String userRoles) throws ServiceException {
        try{
        	CarSysUserRoleDO carSysUserRoleDO =new CarSysUserRoleDO();
        	//如果isNull 为空 （新增）
        	if(StringUtils.equals(isNull, "")){
        	for (String id : splitIds) {
        		 Integer roleID = Integer.parseInt(id);
        		 carSysUserRoleDO.setRoleID(roleID);
        		 carSysUserRoleDO.setUserID(userID);
        		 carSysUserRoleMapper.insert(carSysUserRoleDO);
			}
        	}else{
        		// 如果isNull 不为空（编辑） 根据userID 查出所有角色
        		carSysUserRoleDO.setUserID(userID);
        		List<CarSysUserRoleDO> userRole=carSysUserRoleMapper.getList(carSysUserRoleDO);
        		//存放后台所有角色
        		Map<Integer, CarSysUserRoleDO> map = new HashMap<Integer, CarSysUserRoleDO>();
        		for (CarSysUserRoleDO role : userRole) {
        			map.put(role.getRoleID(), role);
				}
        		//存放前台角色
        		Map<Integer, Integer> newMap = new HashMap<Integer, Integer>();
        		for (String role : splitIds) {
					newMap.put(Integer.parseInt(role), Integer.parseInt(role));
				}
            	// 循环判断 编辑后的角色  是否匹配原有数据
        		// 如果数据库没有前面传过来的element，新增
    			List<CarSysUserRoleDO> insertList = new ArrayList<CarSysUserRoleDO>();
        		for(String role : splitIds){
        			if(map.get(Integer.parseInt(role))==null){
        				CarSysUserRoleDO uRole =new CarSysUserRoleDO();
        				uRole.setRoleID(Integer.parseInt(role));
        				uRole.setUserID(userID);
        				insertList.add(uRole);
        			}
        		}
        		//如果 前台 没有，数据库有，那么就删除
        		List<Integer> deleteList = new ArrayList<Integer>();
        		for (CarSysUserRoleDO role : userRole) {
        			if(newMap.get(role.getRoleID())==null){
        				deleteList.add(role.getId());
        			}
				}
        		//
        		if(StringUtils.isEmpty(userRoles)){
        			carSysUserRoleMapper.delete(carSysUserRoleDO.getUserID());
        			
        		}
        		saveUserRoleList(insertList,deleteList);
        	}
        	
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    /**
     * 
     * @Title: saveUserRoleList 保存用户角色集合
     * @Description: TODO
     * @param insertList
     * @param deleteList
     * @return: void
     * @author: fuxinrong
     * @date: 2017年10月18日 下午12:16:52
     */
    public void saveUserRoleList(List<CarSysUserRoleDO> insertList, List<Integer> deleteList) {
    	try {
			if(insertList.size()>0){
				carSysUserRoleMapper.insertList(insertList);
			}
			if(deleteList.size()>0){
				carSysUserRoleMapper.deleteList(deleteList);
			}
		} catch (Exception e) {
			logger.error("添加删除出错：{0}", e);			
			
		}
    	
    	
    }
    /**
     * 根据id获取对象
     *
     */
    public CarSysUserRoleDO getCarSysUserRoleByID(Integer id) {
        return carSysUserRoleMapper.getByID(id);
    }

    /**
     * 根据carSysUserRoleDO查询列表
     *
     */
    public List<CarSysUserRoleDO> getCarSysUserRoleList(CarSysUserRoleDO carSysUserRoleDO) {
        return carSysUserRoleMapper.getList(carSysUserRoleDO);
    }

    /**
     * 根据carSysUserRoleDO查询分页列表
     *
     */
    public List<CarSysUserRoleDO> getCarSysUserRoleList(CarSysUserRoleDO carSysUserRoleDO, PageBounds pageBounds) {
        return carSysUserRoleMapper.getList(carSysUserRoleDO, pageBounds);
    }

}