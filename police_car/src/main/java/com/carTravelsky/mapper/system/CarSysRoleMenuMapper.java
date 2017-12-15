package com.carTravelsky.mapper.system;
import java.util.List;

import com.carTravelsky.bean.system.CarSysRoleMenuDO;
import com.carTravelsky.mapper.BaseMapper;
public interface CarSysRoleMenuMapper extends BaseMapper<CarSysRoleMenuDO>   {

	public void deletePermission(CarSysRoleMenuDO carSysRoleMenuDO);

	public void deletePermissionByrole(Integer roleID);

	public void insertList(List<CarSysRoleMenuDO> insertList);

	public void deleteList(List<Integer> deleteList);
  
}