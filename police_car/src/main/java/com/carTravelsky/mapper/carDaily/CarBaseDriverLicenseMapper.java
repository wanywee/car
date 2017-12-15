package com.carTravelsky.mapper.carDaily;

import java.util.List;

import com.carTravelsky.bean.carDaily.CarBaseDriverLicenseDO;
import com.carTravelsky.bean.carDaily.CarBaseDriverRecordDo;
import com.carTravelsky.mapper.BaseMapper;

public interface CarBaseDriverLicenseMapper extends BaseMapper<CarBaseDriverLicenseDO> {
	/**
	 * 
	 * @Title: addDriverLicenseContactInfo 增加驾照内场
	 * @Description: TODO 增加驾驶本本
	 * @param carBaseDriverRecordDo 驾驶员
	 * @return: result 返回增加id
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月13日 下午3:34:28
	 */
	public int addDriverLicenseContactInfo(CarBaseDriverRecordDo carBaseDriverRecordDo); 
	/**
	 * 
	 * @Title: addDriverLicenseContactInfo 增加驾照外场
	 * @Description: TODO 增加驾驶本本
	 * @param carBaseDriverRecordDo 驾驶员
	 * @return: result 返回增加id
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月13日 下午3:34:28
	 */
	public int addDriverLicenseContactInfoOne(CarBaseDriverRecordDo carBaseDriverRecordDo); 
	/**
	 * 
	 * @Title: addDriverLicenseContactInfo 增加驾照控制区域
	 * @Description: TODO 增加驾驶本本
	 * @param carBaseDriverRecordDo 驾驶员
	 * @return: result 返回增加id
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月13日 下午3:34:28
	 */
	public int addDriverLicenseContactInfoTwo(CarBaseDriverRecordDo carBaseDriverRecordDo);
	/**
	 * 
	 * @Title: getCarBaseDriverLicenseByID 显示驾照信息通过ID
	 * @Description: TODO 显示驾照信息通过ID
	 * @param id 驾照ID
	 * @return: List<CarBaseDriverLicenseDO> 驾照信息
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月16日 上午11:04:06
	 */
	public List<CarBaseDriverLicenseDO> getCarBaseDriverLicenseByID(Integer id);
	/**
	 * 
	 * @Title: updateCarBaseDriverLicenseById 更新驾照
	 * @Description: TODO 更新驾照信息
	 * @param carBaseDriverLicenseDO 驾照信息
	 * @return: result 更改成功
	 * @author: 何应林?-Dosgi.requiredJavaVersion=1.6  
	 * @date: 2017年10月16日 下午1:53:24
	 */
	public int updateCarBaseDriverLicenseById(CarBaseDriverRecordDo carBaseDriverRecordDo);
	
}