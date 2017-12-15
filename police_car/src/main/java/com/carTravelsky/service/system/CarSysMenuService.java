package com.carTravelsky.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarDailyAccidentRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyAddoilRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyMaintainRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyViolationRecordDO;
import com.carTravelsky.bean.pageView.Top;
import com.carTravelsky.bean.pageView.TopRow;
import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.ZTreeVO;
import com.carTravelsky.mapper.carDaily.CarDailyAccidentRecordMapper;
import com.carTravelsky.mapper.carDaily.CarDailyAddoilRecordMapper;
import com.carTravelsky.mapper.carDaily.CarDailyMaintainRecordMapper;
import com.carTravelsky.mapper.carDaily.CarDailyRepairRecordMapper;
import com.carTravelsky.mapper.carDaily.CarDailyViolationRecordMapper;
import com.carTravelsky.mapper.system.CarSysMenuMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class CarSysMenuService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarSysMenuMapper carSysMenuMapper;
    @Autowired
    private CarDailyRepairRecordMapper carDailyRepairRecordMapper;
    @Autowired
    private CarDailyMaintainRecordMapper carDailyMaintainRecordMapper;
    @Autowired
    private CarDailyViolationRecordMapper  carDailyViolationRecordMapper;
    @Autowired 
    private CarDailyAddoilRecordMapper carDailyAddoilRecordMapper;
    @Autowired
    private CarDailyAccidentRecordMapper carDailyAccidentRecordMapper;
	/**
	 * 系统菜单
	 * 
	 * @return
	 */
	public List<CarSysMenuDO> getMenus() {
		// 父-子 集合存储
		List<CarSysMenuDO> parentMenu = carSysMenuMapper.getParentMenu();
		List<CarSysMenuDO> childMenu = carSysMenuMapper.getChildMenu();
		// 结果菜单
		List<CarSysMenuDO> resultMenu = new ArrayList<CarSysMenuDO>();
		// 临时存储子集合
		List<CarSysMenuDO> tempMenu = null;
		for (CarSysMenuDO pMenu : parentMenu) {
			tempMenu = new ArrayList<CarSysMenuDO>();
			for (CarSysMenuDO cMenu : childMenu) {
				if (cMenu.getParentID().intValue() == pMenu.getId().intValue()) {
					tempMenu.add(cMenu);
				}
			}
			pMenu.setMenus(tempMenu);
			resultMenu.add(pMenu);
		}
		return resultMenu;
	}

	/**
	 * 保存carSysMenuDO
	 *
	 */
	public void saveCarSysMenu(CarSysMenuDO carSysMenuDO)
			throws ServiceException {
		try {
			if (carSysMenuDO.getId() == null) {
				carSysMenuMapper.insert(carSysMenuDO);
			} else {
				carSysMenuMapper.update(carSysMenuDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据id获取对象
	 *
	 */
	public CarSysMenuDO getCarSysMenuByID(Integer id) {
		return carSysMenuMapper.getByID(id);
	}

	/**
	 * 根据carSysMenuDO查询列表
	 *
	 */
	public List<CarSysMenuDO> getCarSysMenuList(CarSysMenuDO carSysMenuDO) {
		return carSysMenuMapper.getList(carSysMenuDO);
	}

	/**
	 * 根据carSysMenuDO查询分页列表
	 *
	 */
	public List<CarSysMenuDO> getCarSysMenuList(CarSysMenuDO carSysMenuDO,
			PageBounds pageBounds) {
		return carSysMenuMapper.getList(carSysMenuDO, pageBounds);
	}
	/**
	 * 获取当前角色对应的权限
	 * @Title: getRoleZTreePermissions
	 * @Description: TODO
	 * @param roleID
	 * @param userRoleID
	 * @return
	 * @return: List<ZTreeVO>
	 */
	public List<ZTreeVO> getRoleZTreePermissions(Integer roleID) {
		return carSysMenuMapper.getRoleZTreePermissions(roleID);
	}
/**
 * 获取树结构
 * @Title: getZTreePermissionList
 * @Description: TODO
 * @return
 * @return: List<ZTreeVO>
 */
	public List<ZTreeVO> getZTreePermissionList() {
		return carSysMenuMapper.getZTreePermissionList();
	}

	public HashMap<Object, Object> getCarSysMenuByIDZtree(int permissionID) {
		return null;
	}
	/**
	 * 删除权限
	 * @Title: deletePermission
	 * @Description: TODO
	 * @param permissionDO
	 * @return: void
	 */
	public void deletePermission(CarSysMenuDO permissionDO) {
		carSysMenuMapper.delete(permissionDO.getId());
	}
	/**
	 * 获取用户所有权限
	 * @param id
	 * @return
	 */
	public List<CarSysMenuDO> getallPermiss(Integer id) {
		return carSysMenuMapper.getallPermiss(id);
	}

	/**
	 * @Title: getTopList
	 * @Description: TODO 获取排行榜
	 * 车辆维修、保养次数排行榜；
	 * 驾驶员违章、事故、加油排行榜
	 * @param loginedUser
	 * @return
	 * @return: List<Top>
	 * @author: zy  
	 * @date: 2017-12-10 下午9:25:09
	 */
	public List<Top> getTopList(CarSysUserDO loginedUser) {
		PageBounds pageBounds=new PageBounds(0,5);
		pageBounds.setSort("displayCount");
		pageBounds.setOrder("DESC");
		List<Top> topList=new ArrayList<Top>();
		//车辆维修排行榜
		Top top=new Top();
		top.setTabId("carRepair");
		top.setTabName("车辆维修");
		top.setActionFlag(true);
		List<TopRow> repairRowList=carDailyRepairRecordMapper.getTopList(new CarDailyRepairRecordDO(),pageBounds);
		top.setRankRowList(repairRowList);
		
		topList.add(top);
		
		//车辆保养排行榜
		Top top2=new Top();
		top2.setTabId("carMaintain");
		top2.setTabName("车辆保养");
		List<TopRow>   maintainRowlist=carDailyMaintainRecordMapper.getTopList(new CarDailyMaintainRecordDO(),pageBounds);
		top2.setRankRowList(maintainRowlist);
		
		topList.add(top2);
		//驾驶员违章
		 top2=new Top();
		top2.setTabId("diverViolation");
		top2.setTabName("驾驶员违章");
		List<TopRow>   violationList=carDailyViolationRecordMapper.getTopList(new CarDailyViolationRecordDO(),pageBounds);
		top2.setRankRowList(violationList);
		
		topList.add(top2);
		//事故
		     top2=new Top();
			top2.setTabId("diverAccident");
			top2.setTabName("事故");
			List<TopRow>   accidentList=carDailyAccidentRecordMapper.getTopList(new CarDailyAccidentRecordDO(),pageBounds);
			top2.setRankRowList(accidentList);
		
			topList.add(top2);
		//车辆加油
			 top2=new Top();
				top2.setTabId("carAddOil");
				top2.setTabName("加油");
				List<TopRow>   addOilList=carDailyAddoilRecordMapper.getTopList(new CarDailyAddoilRecordDO(),pageBounds);
				top2.setRankRowList(addOilList);
		
		
		
		topList.add(top2);		
		
		
		return topList;
	}

}