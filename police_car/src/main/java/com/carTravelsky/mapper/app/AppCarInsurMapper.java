package com.carTravelsky.mapper.app;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.app.response.GeneralRS;

/**
 * @ClassName: AppCarInsurMapper
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月4日 上午10:30:21
 */
public interface AppCarInsurMapper {

	Integer getCompanyIDByName(
			@Param("id")Integer id,
			@Param("companyType")String companyType);
	
	Integer searchInsurRecodrIsHave(
			@Param("carId")Integer carId, 
			@Param("insureDate")Date insureDate,
			@Param("username")String username);

	List<GeneralRS> getInsurCompanyList(@Param("companyType")String companyType);

	List<GeneralRS> getInsurTypeList(@Param("insurancetype")String insurancetype);

	List<GeneralRS> getHandlersList();
}
