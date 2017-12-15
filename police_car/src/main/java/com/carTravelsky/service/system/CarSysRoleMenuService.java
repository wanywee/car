package com.carTravelsky.service.system;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysRoleMenuDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.system.CarSysRoleMapper;
import com.carTravelsky.mapper.system.CarSysRoleMenuMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
@Component
public class CarSysRoleMenuService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarSysRoleMenuMapper carSysRoleMenuMapper;
    @Autowired 
    private CarSysRoleMapper carSysRoleMapper;

    /**
     * 保存carSysRoleMenuDO
     *
     */
    public void saveCarSysRoleMenu(CarSysRoleMenuDO carSysRoleMenuDO) throws ServiceException {
        try{
            if(carSysRoleMenuDO.getId() == null){
                carSysRoleMenuMapper.insert(carSysRoleMenuDO);
            }else {
                carSysRoleMenuMapper.update(carSysRoleMenuDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarSysRoleMenuDO getCarSysRoleMenuByID(Integer id) {
        return carSysRoleMenuMapper.getByID(id);
    }

    /**
     * 根据carSysRoleMenuDO查询列表
     *
     */
    public List<CarSysRoleMenuDO> getCarSysRoleMenuList(CarSysRoleMenuDO carSysRoleMenuDO) {
        return carSysRoleMenuMapper.getList(carSysRoleMenuDO);
    }

    /**
     * 根据carSysRoleMenuDO查询分页列表
     *
     */
    public List<CarSysRoleMenuDO> getCarSysRoleMenuList(CarSysRoleMenuDO carSysRoleMenuDO, PageBounds pageBounds) {
        return carSysRoleMenuMapper.getList(carSysRoleMenuDO, pageBounds);
    }
    /**
     * 删除关联表的数据 根据关联表数据
     * @Title: deletePermission
     * @Description: TODO
     * @param permissionDO
     * @return: void
     */
	public void deletePermission(CarSysMenuDO permissionDO) {
		CarSysRoleMenuDO carSysRoleMenuDO = new CarSysRoleMenuDO();
		carSysRoleMenuDO.setMenuID(permissionDO.getId());
		carSysRoleMenuMapper.deletePermission(carSysRoleMenuDO);
		
	}
	/**
	 * 根据角色删除权限
	 * @Title: deletePermissionByrole
	 * @Description: TODO
	 * @param id
	 * @return: void
	 */
	public void deletePermissionByrole(Integer roleID) {
		carSysRoleMenuMapper.deletePermissionByrole(roleID);
	}
	/**
	 * 批量添加权限或者删除权限
	 * @Title: saveRolePermissions
	 * @Description: TODO
	 * @param insertList
	 * @param deleteList
	 * @return: void
	 */
	public void saveRolePermissions(List<CarSysRoleMenuDO> insertList, List<Integer> deleteList) {
		try{
	        if(insertList.size() > 0){
	        	carSysRoleMenuMapper.insertList(insertList);
	        }
	        if(deleteList.size() > 0){
	        	carSysRoleMenuMapper.deleteList(deleteList);
	        }
    	} catch (Exception e) {
			throw new ServiceException(e);
		}
    }
	/**
	 * 修改角色权限
	 * @param elementArr 
	 * @param permissions 
	 * @param carSysRoleDO 
	 * @param permissionIDs 
	 * @Title: updatePermisssion
	 * @Description: TODO
	 * @return: void
	 */
	@Transactional
	public void updatePermisssion(String[] elementArr, List<CarSysRoleMenuDO> permissions, CarSysRoleDO carSysRoleDO, String permissionIDs) {
		try {
			Map<Integer, CarSysRoleMenuDO> map = new HashMap<Integer, CarSysRoleMenuDO>();
			// 存放后台查询出来的element
			for (CarSysRoleMenuDO permission : permissions) {
				map.put(permission.getMenuID(), permission);
			}
			Map<Integer, Integer> newMap = new HashMap<Integer, Integer>();
			// 存放前台传值过来的element
			for (String elementId : elementArr) {
				newMap.put(Integer.parseInt(elementId), Integer.parseInt(elementId));
			}
			// 如果数据库没有前面传过来的element，新增
			List<CarSysRoleMenuDO> insertList = new ArrayList<CarSysRoleMenuDO>();
			for (String elementId : elementArr) {
				if (map.get(Integer.parseInt(elementId)) == null) {
					CarSysRoleMenuDO permission = new CarSysRoleMenuDO();
					permission.setRoleID(carSysRoleDO.getId());
					permission.setMenuID(Integer.parseInt(elementId));
					insertList.add(permission);
				}
			}
			List<Integer> deleteList = new ArrayList<Integer>();
			// 如果前台element里没有后台查询出来的值，删除
			for (CarSysRoleMenuDO permission : permissions) {
				if (newMap.get(permission.getMenuID()) == null) {
					deleteList.add(permission.getId());
				}
			}
			if(StringUtils.isEmpty(permissionIDs)){
				carSysRoleMenuMapper.deletePermissionByrole(carSysRoleDO.getId());
			}
			saveRolePermissions(insertList, deleteList);
		    
			CarSysRoleDO carSysRoleDO2=carSysRoleMapper.getByID(carSysRoleDO.getId());
				carSysRoleDO2.setIsAllView(carSysRoleDO.getIsAllView());
				carSysRoleMapper.update(carSysRoleDO2);
		} catch (Exception e) {
			 throw new ServiceException(e);
		}
	}
}