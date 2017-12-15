package com.carTravelsky.mapper.carDaily;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.DateUtils.string;

public interface CarBaseDriverMapper extends BaseMapper<CarBaseDriverDO> {
	/**
	 * 驾驶员档案分页显示
	 * 
	 * @param carBaseDriverRecordDo
	 *            驾驶员档案
	 * @param pageBounds
	 *            分页
	 * @return result 驾驶员
	 */
	public List<CarBaseDriverRecordDo> getCarBaseDriverRecordList(
			CarBaseDriverRecordDo carBaseDriverRecordDo, PageBounds pageBounds);

	/**
	 * 驾驶员档案全局搜索
	 * 
	 * @param carBaseDriverRecordDo
	 *            驾驶员档案
	 * @param pageBounds
	 *            分页
	 * @return result 驾驶员搜索结果
	 */
	public List<CarBaseDriverRecordDo> getSearchDriverRecordList(
			@Param("searchString")String searchString,@Param("carBaseDriverRecordDo")CarBaseDriverRecordDo carBaseDriverRecordDo, PageBounds pageBounds);

	/**
	 * 
	 * @Title: addDriverContactInfo 增加驾驶员信息
	 * @Description: TODO 驾驶员信息
	 * @param carBaseDriverRecordDo
	 *            驾驶员
	 * @return: result 驾驶员ID
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月13日 下午2:33:56
	 */
	public int addDriverContactInfo(CarBaseDriverRecordDo carBaseDriverRecordDo);

	/**
	 * 
	 * @Title: updateBaseDriverById 修改驾驶员信息
	 * @Description: TODO 修改驾驶员信息
	 * @param carBaseDriverRecordDo
	 *            驾驶员信息
	 * @return: result 修改成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月16日 下午1:45:47
	 */
	public int updateBaseDriverById(CarBaseDriverRecordDo carBaseDriverRecordDo);

	/**
	 * 
	 * @Title: deleteCarDriverInfoById 删除驾驶员信息
	 * @Description: TODO 删除驾驶员信息
	 * @param id
	 *            驾驶员id
	 * @return result 2删除
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6
	 * @date: 2017年10月16日 下午4:35:37
	 */
	public int deleteCarDriverInfoById(Integer id);

	/**
	 * @Title: getListDriverName
	 * @Description:得到附带驾驶员姓名的驾驶员档案列表
	 * @return
	 * @return: List<CarBaseDriverDO>
	 * @author: wangyu
	 * @date: 2017年10月18日 上午9:36:49
	 */
	public List<CarBaseDriverDO> getListDriverName(@Param("searchStr")String searchStr);
	/**
	 * 
	 * @Title: isUseCarDriverRecords 修改驾驶员状态
	 * @Description: TODO 修改驾驶员状态
	 * @param carBaseDriverDO 驾驶员
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:51:29
	 */
	public void isUseCarDriverRecords(CarBaseDriverDO carBaseDriverDO);
	/**
	 * 
	 * @Title: deleteCarDriverInfoByStaffId 根据员工ID删除驾驶员
	 * @Description: TODO 根据员工ID删除驾驶员
	 * @param id 选中的员工ID删除驾驶员
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年11月10日 下午1:25:29
	 */
	public void deleteCarDriverInfoByStaffId(Integer id);
	/**
	 * 
	 * @Title: queryStaffIsDriver 根据员工ID查询该员工是否是驾驶员
	 * @Description: TODO 根据员工ID查询该员工是否是驾驶员
	 * @param id 员工ID
	 * @return: CarBaseDriverDO 驾驶员档案
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年11月10日 下午1:35:10
	 */
	public CarBaseDriverDO queryStaffIsDriver(Integer id);
	/**
	 * 
	 * @Title: isUseCarDriverRecordsByStaffId 修改驾驶员状态
	 * @Description: TODO 修改驾驶员状态
	 * @param carBaseDriverDO 驾驶员
	 * @author:何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月30日 下午4:51:29
	 */
	public void isUseCarDriverRecordsByStaffId(CarBaseDriverDO carBaseDriverDO);
	/***
	 * @Title: updateCarDriverInfoByWorkID 修改驾驶员状态
	 * @Description: TODO 根据员工id修改驾驶员状态
	 * @param carBaseDriverDO
	 * @date: 2017年11月23日 下午3:11:29
	 */
	public void updateCarDriverInfoByWorkID(CarBaseDriverDO carBaseDriverDO);
	
}