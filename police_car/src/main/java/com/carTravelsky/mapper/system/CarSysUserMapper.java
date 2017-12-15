package com.carTravelsky.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarSysUserMapper extends BaseMapper<CarSysUserDO>   {
	/**
	 * 
	 * @Title: deleteFindByRoleID
	 * @Description: 删除角色 ，查询角色下是否有用户
	 * @param id
	 * @return
	 * @return: List<CarSysUserDO>
	 * @author: fuxinrong
	 * @date: 2017年10月13日 下午2:19:46
	 */
	public List<CarSysUserDO> deleteFindByRoleID(Integer id);
	
	/**
	 *  全局搜索
	 * @Title: getsearchCarSysRoleList
	 * @Description: TODO
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 * @return: List<CarSysRoleDO>
	 * @author: fuxinrong
	 * @date: 2017年10月18日 下午3:04:46
	 */
	public List<CarSysRoleDO> getsearchCarSysRoleList(String searchStr, PageBounds pageBounds);

	public CarSysUserDO searchByName(@Param("username")String username) ;
	/**
	 * 
	 * @Title: updateSameDeptIdByUserId 更改用户部门
	 * @Description: TODO 更改用户部门
	 * @param id 用户Id
	 * @param deptId 部门Id
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年12月11日 下午4:02:53
	 */
	public void updateSameDeptIdByUserId(Integer id,Integer deptId);
	/**
	 * 
	 * @Title: queryUserIdIsExit 查询该user是否存在
	 * @Description: TODO 查询该user是否存在
	 * @param staffNo 员工ID
	 * @return: CarSysUserDO 对象
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年12月11日 下午4:28:26
	 */
	public CarSysUserDO queryUserIdIsExit(Integer staffNo);
}