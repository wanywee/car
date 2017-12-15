package com.carTravelsky.mapper.carDaily;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseContactsDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseContactsMapper extends BaseMapper<CarBaseContactsDO> {
	 /**
     * 
     * @Title: getListFeeUnits 获取所有往来单位
     * @Description: TODO	获取所有往来单位
     * @param select2vos 往来单位下拉框
     * @return: List<Select2VO> 下拉框结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午1:24:51
     */
	public  List<CarBaseContactsDO> getListFeeUnits();

	/**
	 * 
	 * @Title: getListContactCompanyByStr
	 * @Description: 往来单位相关下拉框搜索
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseContactsDO>
	 * @author: yanlinlong  
	 * @date: 2017年11月2日 下午1:14:50
	 */
	public List<CarBaseContactsDO> getListContactCompanyByStr(
			Map<String, Object> paraMap);
	/**
	 * 
	 * @Title: getContactsType
	 * @Description: TODO往来单位所有类型
	 * @return
	 * @return: List<String>
	 * @author: admin  
	 * @date: 2017年11月30日 下午3:12:46
	 */
	public List<String> getContactsType();
	/**
	 * 
	 * @Title: getsearchCarContacts
	 * @Description: TODO全局搜索
	 * @param searchStr
	 * @return
	 * @return: List<CarBaseContactsDO>
	 * @author: rls
	 * @date: 2017年12月1日 下午12:41:41
	 */
	public List<CarBaseContactsDO> getsearchCarContacts(@Param("searchStr") String searchStr,@Param("mycomID") Integer mycomID,PageBounds pageBounds);
	/**
	 * 
	 * @Title: updateStatus
	 * @Description: TODO改变是否停用标志
	 * @param status	是否停用
	 * @param id
	 * @return
	 * @return: List<CarBaseContactsDO>
	 * @author: admin  
	 * @date: 2017年12月1日 下午1:47:24
	 */
	public List<CarBaseContactsDO> updateStatus(@Param("status")Integer status,@Param("id")Integer id);
}