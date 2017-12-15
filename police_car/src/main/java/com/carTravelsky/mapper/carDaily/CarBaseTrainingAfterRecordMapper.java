package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarBaseTrainingAfterRecordDO;
import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseTrainingAfterRecordMapper extends BaseMapper<CarBaseTrainingAfterRecordDO> {
	/**
	 * 
	 * @Title: getCarBaseTrainingAfterAllRecordList 显示在岗复训记录
	 * @Description: TODO	显示在岗复训记录
	 * @param carBaseTrainingAfterRecordDO 在岗复训
	 * @param pageBounds 分页
	 * @return: List<CarBaseTrainingAfterRecordDO> 返回集合
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:11:42
	 */
	public List<CarBaseTrainingAfterRecordDO> getCarBaseTrainingAfterAllRecordList(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO, PageBounds pageBounds);
	/**
	 * 
	 * @Title: editReTrainingInfo 新增在岗复训记录
	 * @Description: TODO	新增在岗复训记录
	 * @param carBaseTrainingAfterRecordDO 在岗复训
	 * @return: result 返回影响行数
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:33:39
	 */
	public int editReTrainingInfo(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO);
	/**
	 * 
	 * @Title: updateCarBaseTrainingAfterRecord 修改在岗复训记录
	 * @Description: TODO 修改在岗复训记录
	 * @param carBaseTrainingAfterRecordDO 在岗复训记录
	 * @return: result
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:43:36
	 */
	public int updateCarBaseTrainingAfterRecord(CarBaseTrainingAfterRecordDO carBaseTrainingAfterRecordDO);
	/**
	 * 
	 * @Title: deleteDriverReTrainingRecord 删除在岗复训记录
	 * @Description: TODO 删除在岗复训记录
	 * @param id 选择的id
	 * @return: result 返回结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 下午2:53:59
	 */
	public int deleteDriverReTrainingRecord(Integer id);
}