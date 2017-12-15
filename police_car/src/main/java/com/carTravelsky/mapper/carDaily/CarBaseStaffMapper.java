package com.carTravelsky.mapper.carDaily;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseStaffMapper extends BaseMapper<CarBaseStaffDO> {

	List<CarBaseStaffDO> getListStaffByCompanyId(Map<String, Object> paraMap);
	/**
	 * 
	 * @Title: addDriverStaffInfo 增加驾驶员员工信息
	 * @Description: TODO	增加驾驶员员工信息
	 * @param carBaseDriverRecordDo 驾驶员员工
	 * @return: int 驾驶员工id
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月13日 下午2:32:48
	 */
	public int addDriverStaffInfo(CarBaseDriverRecordDo carBaseDriverRecordDo);
	/**
	 * 
	 * @Title: updateBaseStaffById 更新驾驶员信息
	 * @Description: TODO 更新驾驶员信息
	 * @param carBaseDriverRecordDo 驾驶员员工
	 * @return: result 更新成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月16日 下午2:06:31
	 */
	public int updateBaseStaffById(CarBaseDriverRecordDo carBaseDriverRecordDo);
	
	/**
	 * 
	 * @Title: getListSelect2Driver
	 * @Description: 下拉框--驾驶员
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseStaffDO>
	 */
	List<CarBaseStaffDO> getListSelect2Driver(Map<String, Object> paraMap);
	/**
	 * 
	 * @Title: getCarBaseAllStaffList 获取所有staff列表
	 * @Description: TODO 获取所有staff列表
	 * @param carBaseStaffDO staff对象
	 * @param pageBounds	分页
	 * @return: List<CarBaseStaffDO> 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 上午10:23:19
	 */
	public List<CarBaseStaffDO> getCarBaseAllStaffList(
			CarBaseStaffDO carBaseStaffDO, PageBounds pageBounds);
	/**
	 * 
	 * @Title: addStaffInformation 添加职员信息
	 * @Description: TODO 添加职员信息
	 * @param carBaseStaffDO 职员信息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:29:18
	 */
	public int addStaffInformation(CarBaseStaffDO carBaseStaffDO);
	/**
	 * 
	 * @Title: updateStaffInformation 修改职员信息
	 * @Description: TODO 修改职员信息
	 * @param carBaseStaffDO 职员信息
	 * @return: result 影响行数
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:36:54
	 */
	public int updateStaffInformation(CarBaseStaffDO carBaseStaffDO);
	/**
	 * 
	 * @Title: deleteStaffInformationInfoById 删除职员信息通过id
	 * @Description: TODO	删除职员信息
	 * @param id	选中的ID
	 * @return: result 返回的结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午1:42:33
	 */
	public int deleteStaffInformationInfoById(Integer id);

	
	/**
	 * 
	 * @Title: getListSelect2DriverByStr
	 * @Description: 下拉框--驾驶员搜索
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseStaffDO>
	 */
	List<CarBaseStaffDO> getListSelect2DriverByStr(Map<String, Object> paraMap);

	/**
	 * 
	 * @Title: getSearchDriverRecordList 全局搜索
	 * @Description: TODO	全局搜索
	 * @param searchString 搜索字符串
	 * @param pageBounds	分页
	 * @return: List<CarBaseStaffDO> 显示搜索职员
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月20日 下午3:37:53
	 */
	public List<CarBaseStaffDO> getsearchBaseStaffInfo(
			@Param("searchString")String searchString,@Param("carBaseStaffDO")CarBaseStaffDO carBaseStaffDO, PageBounds pageBounds);
	/**
	 * 
	 * @Title: getCarBaseStaffNo 获取staffNo
	 * @Description: TODO 获取staffNo
	 * @param staffNo 工号
	 * @return: CarBaseStaffDO 员工
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月25日 下午1:00:16
	 */
	public CarBaseStaffDO getCarBaseStaffNo(String staffNo);
	/**
	 * 
	 * @Title: isUseStaffInfoRecords 修改启用状态
	 * @Description: TODO 修改启用状态
	 * @param carBaseStaffDO 修改启用状态
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:12:54
	 */
	public void isUseStaffInfoRecords(CarBaseStaffDO carBaseStaffDO);
	/**
	 * 
	 * @Title: getListStaffByStr
	 * @Description: 下拉框-部门信息-负责人
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseStaffDO>
	 */
	List<CarBaseStaffDO> getListStaffByStr(Map<String, Object> paraMap);
	Integer searchHandlerIdByName(@Param("handlerName")String handlerName);
	String searchHandlerNameById(@Param("handlerId")Integer handlerId);
	/**
	 * @Title: getPhoneByUsername
	 * @Description: TODO
	 * @param name
	 * @return
	 * @return: CarBaseStaffDO
	 */
	CarBaseStaffDO getPhoneByUsername(@Param("name")String name);
}