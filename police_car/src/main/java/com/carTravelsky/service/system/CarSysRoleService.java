package com.carTravelsky.service.system;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.system.CarSysRoleDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.system.CarSysRoleMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;

@Component
public class CarSysRoleService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarSysRoleMapper carSysRoleMapper;

  
    /**
     * 保存carSysRoleDO
     *
     */
    public void saveCarSysRole(CarSysRoleDO carSysRoleDO) throws ServiceException {
        try{
            if(carSysRoleDO.getId() == null){
                carSysRoleMapper.insert(carSysRoleDO);
            }else {
                carSysRoleMapper.update(carSysRoleDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarSysRoleDO getCarSysRoleByID(Integer id) {
        return carSysRoleMapper.getByID(id);
    }

    /**
     * 根据carSysRoleDO查询列表
     *
     */
    public List<CarSysRoleDO> getCarSysRoleList(CarSysRoleDO carSysRoleDO) {
        return carSysRoleMapper.getList(carSysRoleDO);
    }
    /**
     * 根据carSysRoleDO查询列表  返回select2 集合
     *
     */
    public List<Select2VO> getCarSysRoleList(CarSysRoleDO carSysRoleDOs,List<Select2VO> sList,String str) {
        		try {
        			List<CarSysRoleDO> roleDOs= new ArrayList<CarSysRoleDO>();
        			//先判断str不为null 根据str搜索
        			if(StringUtils.isNotBlank(str)){
        				roleDOs = carSysRoleMapper.getRoleListStr(str);
        			}else{
        			//查询角色集合
					 roleDOs = carSysRoleMapper.getRoleLists(carSysRoleDOs);
        			}
        			//循环遍历 添加到select2List
					for (CarSysRoleDO carSysRoleDO : roleDOs) {
						Select2VO select2vo = new Select2VO();
						select2vo.setId(carSysRoleDO.getId()+"");
						select2vo.setText(carSysRoleDO.getRoleName());
						sList.add(select2vo);
					}
					return sList;
				} catch (Exception e) {
					logger.error("获取角色集合select2 出错 ：{0}", e);
					 throw new ServiceException(e);
				}
    }

    
    /**
     * 根据carSysRoleDO查询分页列表
     *
     */
    public List<CarSysRoleDO> getCarSysRoleList(CarSysRoleDO carSysRoleDO, PageBounds pageBounds) {
        return carSysRoleMapper.getList(carSysRoleDO, pageBounds);
    }
    /**
     * 根据carSysRoleDO查询分页列表全局搜索
     *
     */
    public List<CarSysRoleDO> getCarSysRoleListForGlobal(CarSysRoleDO carSysRoleDO, PageBounds pageBounds) {
        return carSysRoleMapper.getListForGlobal(carSysRoleDO, pageBounds);
    }
    
    /**
     * 批量删除多个ids
     * @param splitIds
     * @param notDeleteIds
     * @param integer 
	 * @return 
     */
	public List<Integer> deleteRolesById(String[] splitIds, List<Integer> notDeleteIds, Integer loginID) {
		try {
			CarSysRoleDO carSysRoleDO = new CarSysRoleDO();
			for (String id : splitIds) {
				 Integer idInt = Integer.parseInt(id); 
				List<CarSysUserDO> userList = carSysRoleMapper.deleteFindByRoleID(idInt);
				if(userList.size() > 0){
					notDeleteIds.add(idInt);
				}else{
					carSysRoleDO.setId(idInt);
					carSysRoleDO.setDeleteCode(YSTConstants.DISENABLE);
					carSysRoleDO.setUpdateOperator(loginID+"");
					carSysRoleDO.setUodateTime(new Date());
					carSysRoleMapper.update(carSysRoleDO);
				}
			}
		} catch (Exception e) {
			throw new ServiceException("删除多个id出错",e);
		}
		return notDeleteIds;
	}
	/**
	 * 全局搜索
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 */
	public List<CarSysRoleDO> getsearchCarSysRoleList(String searchStr, PageBounds pageBounds) {
		return carSysRoleMapper.getsearchCarSysRoleList(searchStr,pageBounds);
	}
	/**
	 * 获取编码最大值
	 * @param companyID 
	 * @Title: getMaxRoleCode
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public Integer getMaxRoleCode(int companyID) {
		try {
		
			String maxCode = carSysRoleMapper.getMaxRoleCode(companyID);
			String num = maxCode.replaceAll(YSTConstants.ROLECODEPRE, "");
			return Integer.parseInt(num);
		} catch (Exception e) {
			 logger.error("获取最大值出错", e);
			 return null;
		}
		
	}
/**
 * 判断是否存在code
 * @param  
 * @Title: isexitCode
 * @Description: TODO
 * @param id
 * @param roleCode
 * @param carSysRoleDO2 
 * @param string 
 * @return
 * @return: boolean
 */
	public boolean isexitCode(String field,String roleCode, CarSysRoleDO carSysRoleDO2) {
		try {
			CarSysRoleDO carSysRoleDO = new CarSysRoleDO();
			 Field f = carSysRoleDO.getClass().getDeclaredField(field);
		        f.setAccessible(true);
		        f.set(carSysRoleDO, roleCode);
		        List<CarSysRoleDO> list = carSysRoleMapper.getList(carSysRoleDO);
		        if(list.size()==0){
		        	return false;
		        }
		     // 判断
        		CarSysRoleDO carSysRoleDO3 = list.get(0);
		        if(!(carSysRoleDO3.getId()== carSysRoleDO2.getId())){
		        	return true;
		        }
		      return false;
		} catch (Exception e) {
			logger.error("赋值出错{0}",e);
			return false;
		}
	
	}
}