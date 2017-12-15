package com.carTravelsky.bean.carDaily;

/**
 * @ClassName: DepartmentCarDO 部门所拥有的车辆
 * @Description: TODO
 * @author: 黄进
 * @date: 2017年12月5日 下午4:06:32
 */
public class DepartmentCarDO {

	/** 车辆ID */
	private Integer carId;
	/** 车牌号 */
	private String licenseno;
	/** 车辆类型 */
	private String carType;
	/** 装载人数 */
	private Integer loading;
	/** 公里数 */
	private Float mileage;
	/** 位置 */
	private String location;
	
	
	
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getLicenseno() {
		return licenseno;
	}
	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public Integer getLoading() {
		return loading;
	}
	public void setLoading(Integer loading) {
		this.loading = loading;
	}
	public Float getMileage() {
		return mileage;
	}
	public void setMileage(Float mileage) {
		this.mileage = mileage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
}
