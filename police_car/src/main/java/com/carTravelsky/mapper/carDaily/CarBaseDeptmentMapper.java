package com.carTravelsky.mapper.carDaily;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseDeptmentMapper extends BaseMapper<CarBaseDeptmentDO> {

	/**
	 * 
	 * @Title: getListDeptInfoByCompanyId
	 * @Description: 根据公司ID获取该公司所有部门信息
	 * @param i
	 * @param pageBounds 
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	List<CarBaseDeptmentDO> getListDeptInfoByCompanyId(Integer i, PageBounds pageBounds);

	/**
	 * 
	 * @Title: getsearchCarBaseDeptInfo
	 * @Description: 部门信息全局搜索
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	List<CarBaseDeptmentDO> getsearchCarBaseDeptInfo(Map<String, Object> paraMap,
			PageBounds pageBounds);

	/**
	 * 
	 * @Title: getListDeptInfoByCompanyId
	 * @Description: 部门信息下拉框
	 * @param companyId
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 * @author: yanlinlung  
	 * @date: 2017年10月16日 下午10:07:55
	 */
	List<CarBaseDeptmentDO> getListSelect2DeptInfo(int companyId);
	/**
	 * 
	 * @Title: getListDeptInfo 获取部门名称
	 * @Description: TODO	获取部门名称
	 * @return: List<CarBaseDeptmentDO> 部门对象
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月19日 上午10:56:11
	 */
	List<CarBaseDeptmentDO> getListDeptInfo();

	/**
	 * 
	 * @param paraMap 
	 * @param companyId 
	 * @Title: getCarBaseDeptmentByStr
	 * @Description: 下拉框--部门信息搜索
	 * @param str
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	List<CarBaseDeptmentDO> getCarBaseDeptmentByStr(Map<String, Object> paraMap);

	/**
	 * 
	 * @param id 
	 * @Title: getMaxDeptCode
	 * @Description: 获取部门编码最大值
	 * @return
	 * @return: String
	 */
	String getMaxDeptCode(Integer id);

	
	/**
	 * @Title: getTreeList
	 * @Description: 获取树形列表
	 * @param rootId
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 * @author: THINK  
	 * @param searchStr 
	 * @date: 2017-12-4 下午9:55:48
	 */
	List<CarBaseDeptmentDO> getTreeList(@Param("rootId")Integer rootId,@Param("searchStr")String searchStr);
}