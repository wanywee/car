package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarBaseTrainingRecordDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface CarBaseTrainingRecordMapper extends BaseMapper<CarBaseTrainingRecordDO> {
	/**
	 * 
	 * @Title: getSearchTrainngRecordList显示培训记录
	 * @Description: TODO 显示培训记录
	 * @param carBaseTrainingRecordDO 对象
	 * @param pageBounds 分页
	 * @return: List<CarBaseTrainingRecordDO> 查询结果
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月17日 下午2:36:58
	 */
	 public List<CarBaseTrainingRecordDO> getCarBaseTrainingAllRecordList(CarBaseTrainingRecordDO carBaseTrainingRecordDO, PageBounds pageBounds);
	/**
	 * 
	 * @Title: editTrainingInfo 编辑培训记录
	 * @Description: TODO  编辑培训记录
	 * @param carBaseTrainingRecordDO 培训记录
	 * @return: result 培训记录成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月18日 上午9:04:24
	 */
	 public int editTrainingInfo(CarBaseTrainingRecordDO carBaseTrainingRecordDO);
	 /**
	  * 
	  * @Title: deleteCarBaseTrainingRecordById 删除培训记录
	  * @Description: TODO 删除培训记录
	  * @param id 选择的id
	  * @return: result 返回的结果
	  * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	  * @date: 2017年10月18日 上午9:06:08
	  */
	 public int deleteCarBaseTrainingRecordById(Integer id);
	 /**
	  * 
	  * @Title: updateCarBaseTrainingRecord 修改培训记录
	  * @Description: TODO 修改培训记录
	  * @param carBaseTrainingRecordDO 培训记录
	  * @return: result 返回结果 
	  * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	  * @date: 2017年10月18日 上午9:19:15
	  */
	 public int updateCarBaseTrainingRecord(CarBaseTrainingRecordDO carBaseTrainingRecordDO);
	 
}