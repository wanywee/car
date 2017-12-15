package com.carTravelsky.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
public interface CarSysRoleMapper extends BaseMapper<CarSysRoleDO>
{
	public List<CarSysRoleDO> getListForGlobal(CarSysRoleDO carSysRoleDO, PageBounds pageBounds);

	public List<CarSysUserDO> deleteFindByRoleID(Integer idInt);

	public List<CarSysRoleDO> getsearchCarSysRoleList(String searchStr, PageBounds pageBounds);

	/**角色下拉框*/
	public List<CarSysRoleDO> getRoleLists(CarSysRoleDO carSysRoleDOs);
	
	/**角色下拉框 搜索*/
	public List<CarSysRoleDO> getRoleListStr(String str);

	public String getMaxRoleCode(@Param("companyID")int companyID);
}