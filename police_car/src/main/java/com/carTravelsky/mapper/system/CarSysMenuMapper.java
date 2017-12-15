package com.carTravelsky.mapper.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.mapper.BaseMapper;

public interface CarSysMenuMapper extends BaseMapper<CarSysMenuDO>   {

	// 一级菜单
		public List<CarSysMenuDO> getParentMenu();

		// 二级菜单
		public List<CarSysMenuDO> getChildMenu();

		public List<ZTreeVO> getZTreePermissionList();

		public List<ZTreeVO> getRoleZTreePermissions(Integer roleID);

		public List<CarSysMenuDO> getallPermiss(@Param("pId")Integer id);
	
}