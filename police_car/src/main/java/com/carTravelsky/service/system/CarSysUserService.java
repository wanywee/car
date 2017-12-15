package com.carTravelsky.service.system;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.system.CarSysUserMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;

@Component
public class CarSysUserService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarSysUserMapper carSysUserMapper;

    /**
     * 保存carSysUserDO
     *
     */
    public void saveCarSysUser(CarSysUserDO carSysUserDO) throws ServiceException {
        try{
            if(carSysUserDO.getId() == null){
                carSysUserMapper.insert(carSysUserDO);
            }else {
                carSysUserMapper.update(carSysUserDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
    
    /**
     * 根据id获取对象
     *
     */
    public CarSysUserDO getCarSysUserByID(Integer id) {
        return carSysUserMapper.getByID(id);
    }

    /**
     * 根据carSysUserDO查询列表
     *
     */
    public List<CarSysUserDO> getCarSysUserList(CarSysUserDO carSysUserDO) {
        return carSysUserMapper.getList(carSysUserDO);
    }

    /**
     * 根据carSysUserDO查询分页列表
     *
     */
    public List<CarSysUserDO> getCarSysUserList(CarSysUserDO carSysUserDO, PageBounds pageBounds) {
        return carSysUserMapper.getList(carSysUserDO, pageBounds);
    }
    /**
     * 
     * @Title: deleteFindByRoleID 删除角色查询下面是否有用户
     * @Description: TODO
     * @param id
     * @return
     * @return: List<CarSysUserDO>
     * @author: fuxinrong
     * @date: 2017年10月13日 下午2:16:46
     */
    public List<CarSysUserDO> deleteFindByRoleID(Integer id){
    	  return carSysUserMapper.deleteFindByRoleID(id);
    }
    /**
     * 批量删除userID
     * @Title: deleteUsersById
     * @Description: TODO
     * @param splitIds
     * @param loginId 
     * @return: List<Integer>
     */
	public void deleteUsersById(String[] splitIds, Integer loginId) {
		try {
			CarSysUserDO carSysUserDO = new CarSysUserDO();
			for (String id : splitIds) {
					carSysUserDO.setId(Integer.parseInt(id));
					carSysUserDO.setDeleteCode(YSTConstants.DISENABLE);
					carSysUserDO.setUpdateOpertor(loginId+"");
					carSysUserDO.setUpdateTime(new Date());
					carSysUserMapper.update(carSysUserDO);
				
			}
		} catch (Exception e) {
			throw new ServiceException("删除多个id出错",e);
		}
	}
	/**
	 * 全局搜索
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 */
	public List<CarSysRoleDO> getsearchCarSysRoleList(String searchStr, PageBounds pageBounds) {
		return carSysUserMapper.getsearchCarSysRoleList(searchStr,pageBounds);
	}
}