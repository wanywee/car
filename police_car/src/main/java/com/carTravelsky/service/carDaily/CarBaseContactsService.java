package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseContactsDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarBaseContactsMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.ChineseLetter;
import com.stopec.common.utils.StringUtils;

@Component
public class CarBaseContactsService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarBaseContactsMapper carBaseContactsMapper;
    
    /**
	 * 
	 * @Title: getSelect2ListContactCompany
	 * @Description: 往来单位下拉框
	 * @param kind 查询类型（kind）
	 * @param searchStr 搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong
	 * @param currentUser 
	 * @date: 2017年11月2日 上午10:59:16
	 */
	public List<Select2VO> getSelect2ListContactCompany(CarSysUserDO currentUser, String kind, String searchStr) {
		// 封装查询对象
		CarBaseContactsDO carBaseContactsDO = new CarBaseContactsDO();
		carBaseContactsDO.setCompanyType(kind);
		carBaseContactsDO.setMycomID(currentUser.getCompanyId());
		// 结果下拉列表 Select2VO
		List<Select2VO> reslutList = new ArrayList<Select2VO>();
		Select2VO select2vo = new Select2VO();
		List<CarBaseContactsDO> carBaseContactsDOs;
		if (!StringUtils.isBlank(searchStr)) { //不为空 查询下拉框
			// 封装查询map 做下拉框查询
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", currentUser.getCompanyId());
			paraMap.put("companyType", kind);
			paraMap.put("str", searchStr);
			carBaseContactsDOs = carBaseContactsMapper.getListContactCompanyByStr(paraMap);
		} else {
			carBaseContactsDO.setStatus(YSTConstants.ENABLE);
			carBaseContactsDOs = carBaseContactsMapper.getList(carBaseContactsDO);
		}
		for (CarBaseContactsDO contactsDO : carBaseContactsDOs) {
			select2vo = new Select2VO();
			select2vo.setId(String.valueOf(contactsDO.getId()));
			select2vo.setText(contactsDO.getCompanyName());
			reslutList.add(select2vo);
		}
		return reslutList;
	}

    /**
     * 保存carBaseContactsDO
     *
     */
    public void saveCarBaseContacts(CarBaseContactsDO carBaseContactsDO) throws ServiceException {
        try{
            if(carBaseContactsDO.getId() == null){
            	String mnemonicCode = ChineseLetter.getPinYinHeadChar(
            			carBaseContactsDO.getCompanyName()).toUpperCase();
            	carBaseContactsDO.setMemonicCode(mnemonicCode);
            	carBaseContactsDO.setCreateTime(new Date());
                carBaseContactsMapper.insert(carBaseContactsDO);
            }else {
            	String mnemonicCode = ChineseLetter.getPinYinHeadChar(
            			carBaseContactsDO.getCompanyName()).toUpperCase();
            	carBaseContactsDO.setMemonicCode(mnemonicCode);
            	carBaseContactsDO.setUpdateTime(new Date());
                carBaseContactsMapper.update(carBaseContactsDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据id获取对象
     *
     */
    public CarBaseContactsDO getCarBaseContactsByID(Integer id) {
        return carBaseContactsMapper.getByID(id);
    }

    /**
     * 根据carBaseContactsDO查询列表
     *
     */
    public List<CarBaseContactsDO> getCarBaseContactsList(CarBaseContactsDO carBaseContactsDO) {
        return carBaseContactsMapper.getList(carBaseContactsDO);
    }

    /**
     * 根据carBaseContactsDO查询分页列表
     *
     */
    public List<CarBaseContactsDO> getCarBaseContactsList(CarBaseContactsDO carBaseContactsDO, PageBounds pageBounds) {
        return carBaseContactsMapper.getList(carBaseContactsDO, pageBounds);
    }
    /**
     * 
     * @Title: getContactsType
     * @Description: TODO获取往来单位类型列表
     * @return
     * @return: List<String>
     * @author: admin  
     * @date: 2017年12月1日 下午12:42:32
     */
    public List<String> getContactsType(){
    	return carBaseContactsMapper.getContactsType();
    }
    /**
     * 
     * @Title: getsearchCarContacts
     * @Description: TODO全局搜索
     * @param searchStr
     * @return
     * @return: List<CarBaseContactsDO>
     * @author: admin  
     * @date: 2017年12月1日 下午12:44:22
     */
    public List<CarBaseContactsDO> getsearchCarContacts(String searchStr,Integer mycomID,PageBounds pageBounds){
    	return carBaseContactsMapper.getsearchCarContacts(searchStr,mycomID,pageBounds);
    }
    /**
     * 
     * @Title: updateStatus
     * @Description: TODO改变是否停用
     * @param status 停用标识 2为停用
     * @param id 往来单位id
     * @return
     * @return: List<CarBaseContactsDO>
     * @author: admin  
     * @date: 2017年12月1日 下午1:48:49
     */
    public List<CarBaseContactsDO> updateStatus(Integer status,Integer id){
    	return carBaseContactsMapper.updateStatus(status, id);
    }
    public boolean deleteContacts(String[] ids){
    	for (String id : ids) {
			carBaseContactsMapper.delete(new Integer(id).intValue());
		}
    	return true;
    }
}