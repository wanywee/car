package com.carTravelsky.mapper.carDaily;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.CarDailyFeeManageRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarDailyFeeManageRecordMapper extends BaseMapper<CarDailyFeeManageRecordDO> {
	/**
     * 
     * @Title: getCarDailyFeeManageAllList 获取所有规费管理记录
     * @Description: TODO 获取所有规费管理记录
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: List<CarDailyFeeManageRecordDO> 显示结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月30日 下午2:17:13
     */
	public  List<CarDailyFeeManageRecordDO>  getCarDailyFeeManageAllList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds);
	
	/**
     * 
     * @Title: getCarDailyFeeManageAllList 规费管理到期统计
     * @Description: TODO 获取所有规费管理记录
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 统计结果
     * @author: 任连松?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年11月324下午15:16
     */
	public int countExpire(@Param("remindDate")int remindDate,@Param("companyID")int companyID);
	/**
     * 
     * @Title: getCarDailyFeeManageAllList 规费管理到期统计
     * @Description: TODO 获取所有规费管理记录
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 统计结果
     * @author: bxl
     * @date: 2017年11月324下午15:16
     */
	public int countExpireById(@Param("remindDate")int remindDate, PageBounds pageBounds);
	/**
     * 
     * @Title: getCarDailyFeeManageAllList 获取所有规费管理到期数据
     * @Description: TODO 获取所有规费管理到期数据
     * @param carDailyFeeManageRecordDO 规费管理记录
     * @param pageBounds 分页
     * @return: int 统计结果
     * @author: 任连松?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年11月324下午15:16
     */
	public List<CarDailyFeeManageRecordDO> expireRecordList(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds,@Param("remindDate")int remindDate,@Param("companyID")int companyID);
	 /**
     * 
     * @Title: getSearchFeeManageAllList 全局搜索
     * @Description: TODO 全局搜索
     * @param searchStr 所搜的字段
     * @param pageBounds 分页
     * @return: List<CarDailyFeeManageRecordDO> 查询的结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 上午9:56:29
     */
	public  List<CarDailyFeeManageRecordDO>  getSearchFeeManageAllList(@Param("searchStr")String searchStr,@Param("carDailyFeeManageRecordDO")CarDailyFeeManageRecordDO carDailyFeeManageRecordDO, PageBounds pageBounds);
	/**
     * 
     * @Title: getSearchFeeManageAllList 全局搜索+到期提醒
     * @Description: TODO 全局搜索+到期提醒
     * @param searchStr 所搜的字段
     * @param pageBounds 分页
     * @param reminDate 提醒期限
     * @return: List<CarDailyFeeManageRecordDO> 查询的结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 上午9:56:29
     */
	public List<CarDailyFeeManageRecordDO>   getSearchFeeManageAllListExpire (@Param("searchStr")String searchStr,@Param("carDailyFeeManageRecordDO")CarDailyFeeManageRecordDO carDailyFeeManageRecordDO,PageBounds pageBounds,@Param("remindDate")int remindDate);
	/**
     * 
     * @Title: deleteFeesManage 删除规费记录
     * @Description: TODO 删除规费记录
     * @param ids 选中的行
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午1:50:08
     */
	public void deleteFeesManage(Integer id);
	 /**
     * 
     * @Title: addFeesManage 增加规费管理
     * @Description: TODO 增加规费管理
     * @param carDailyFeeManageRecordDO 规费管理
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午2:57:27
     */
    public void addFeesManage(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO);
    /**
     * 
     * @Title: getFeesManageInfoByID 回显规费管理
     * @Description: TODO 回显规费管理
     * @param id 选中的行
     * @return: CarDailyFeeManageRecordDO 回显结果
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午3:18:12
     */
    public CarDailyFeeManageRecordDO getFeesManageInfoByID(Integer id);
    /**
     * 
     * @Title: updateFeesManageInfo 修改规费管理
     * @Description: TODO 修改规费管理
     * @param carDailyFeeManageRecordDO 规费管理
     * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
     * @date: 2017年10月31日 下午3:50:58
     */
    public void updateFeesManageInfo(CarDailyFeeManageRecordDO carDailyFeeManageRecordDO);
}