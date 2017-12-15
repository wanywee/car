package com.carTravelsky.mapper.carDaily;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarBaseDeptmentDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseVehicleMapper extends BaseMapper<CarBaseVehicleDO> {

	/**
	 * 
	 * @Title: getsearchCarBaseVehicle
	 * @Description: 全局搜索
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlung  
	 * @date: 2017年10月13日——————11:46:56
	 */
	List<CarBaseVehicleDO> getsearchCarBaseVehicle(Map<String, Object> paraMap, PageBounds pageBounds);

	/**
	 * 
	 * @Title: getListVehicleRecordsByCompanyId
	 * @Description: 根据公司id搜索车辆信息 Mapper
	 * @param paraMap
	 * @param pageBounds
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlung  
	 * @date: 2017年10月13日--11:52:59
	 */
	List<CarBaseVehicleDO> getListVehicleRecordsByCompanyId(Map<String, Object> paraMap, PageBounds pageBounds);

    
    /**
     * @Title: getLicensenosList
     * @Description: 根据车辆ID得到对应的车牌号码
     * @return
     * @return: List<CarBaseVehicleDO>
     * @author: wangyu 
     * @date: 2017年10月26日 上午9:14:03
     */
    List<CarBaseVehicleDO> getLicensenosList(@Param("searchStr")String searchStr,@Param("deptId")Integer deptId);
    /**
     * 
     * @Title: getLicenseNoAllList 获取所有车牌号
     * @Description: TODO  获取所有车牌号
     * @return: List<CarBaseVehicleDO> 车牌号
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午4:26:15
     */
    public  List<CarBaseVehicleDO> getLicenseNoAllList();

	CarBaseVehicleDO getCarBaseVehicleByLicenseno(String licenseno);

    /**
     * 
     * @Title: getLicensenosList
     * @Description: 得到车牌号，车状态为，出车  回车记录车牌号下拉框
     * @return
     * @return: List<CarBaseVehicleDO>
     * @author: fuxinrong
     * @date: 2017年10月31日 上午11:17:15
     */
    List<CarBaseVehicleDO> getLicensenosListOut(Map<String, Object> paraMap);
    /**
     * 
     * @Title: getLicensenosListUseable
     * @Description: 得到车牌号 车辆状态为 可用   出车记录  车牌号下来框
     * @param carBaseVehicleDO
     * @return
     * @return: List<CarBaseVehicleDO>
     * @author: fuxinrong
     * @date: 2017年11月2日 下午12:12:40
     */
    List<CarBaseVehicleDO> getLicensenosListUseable(Map<String, Object> paraMap);

    /**
	 * 
	 * @param paraMap 
	 * @param companyId 
	 * @Title: getLicensenosListBystr
	 * @Description: 下拉框--车牌号
	 * @param str
	 * @return
	 * @return: List<CarBaseDeptmentDO>
	 */
	List<CarBaseVehicleDO> getLicensenosListBystr(Map<String, Object> paraMap);
    
	/**
	 * @Title: getCarBaseVehicleListForDropdown
	 * @Description: 获取当前公司中 正常使用车辆的信息
	 * @param carBaseVehicleDO
	 * @param searchStr
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: zy  
	 * @date: 2017-10-17 下午6:09:01
	 */
	public List<Select2VO> getCarBaseVehicleListForDropdown(@Param("carBaseVehicleDO")CarBaseVehicleDO carBaseVehicleDO, @Param("searchStr")String searchStr);

	/**
	 * 
	 * @Title: getCarBaseVehicleThirdParty
	 * @Description: 第三方需求接口：获取平板车和拖头车
	 * @param paraMap
	 * @return
	 * @return: List<CarBaseVehicleDO>
	 * @author: yanlinlong  
	 * @date: 2017年11月15日 上午10:51:57
	 */
	List<CarBaseVehicleDO> getCarBaseVehicleThirdParty(
			Map<String, Object> paraMap);
	/**
	 * 
	 * @Title: getListCarReport 获取当前页的报表数据
	 * @Description: TODO 获取当前页的报表数据
	 * @param searchStr 搜索条件
	 * @param pagenum 条数
	 * @return: List<CarBaseVehicleDO>
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年12月1日 下午12:46:32
	 */
	public List<CarBaseVehicleDO> getListCarReport(String searchStr,int pagenum);
	/**
	 * 
	 * @Title: getListSearchCarReport 获取当前页所搜的报表数据
	 * @Description: TODO 获取当前页所搜的报表数据
	 * @param searchStr 搜索条件
	 * @param pagenum 条数
	 * @return: List<CarBaseVehicleDO>
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年12月1日 下午12:46:32
	 */
	public List<CarBaseVehicleDO> getListSearchCarReport(String searchStr,int pagenum);
	/**
	 * 
	 * @Title: getCarListall
	 * @Description: TODO获取车辆购入日期
	 * @return
	 * @return: List<Date>
	 * @author: bxl  
	 * @date: 2017年12月6日 下午4:50:38
	 */
	public List<Date> getCarListall();
}