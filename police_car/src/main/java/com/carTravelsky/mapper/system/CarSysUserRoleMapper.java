package com.carTravelsky.mapper.system;

import java.util.List;

import com.carTravelsky.bean.system.CarSysRoleMenuDO;
import com.carTravelsky.bean.system.CarSysUserRoleDO;
import com.carTravelsky.mapper.BaseMapper;

public interface CarSysUserRoleMapper extends BaseMapper<CarSysUserRoleDO>   {
	public void insertList(List<CarSysUserRoleDO> insertList);

	public void deleteList(List<Integer> deleteList);
}