package com.carTravelsky.bean.app.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName: CarDetailListRS
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年12月6日 下午2:16:55
 */
public class CarDetailListRS implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -324645254696769046L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("licenseNumber")
	private String licenseNumber;
	
	@JsonProperty("carType")
	private String carType;
	
	@JsonProperty("loadingNumber")
	private Integer loadingNumber;
	
	@JsonProperty("mileNumber")
	private Float mileNumber;
	
	@JsonProperty("location")
	private String location;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Integer getLoadingNumber() {
		return loadingNumber;
	}

	public void setLoadingNumber(Integer loadingNumber) {
		this.loadingNumber = loadingNumber;
	}

	public Float getMileNumber() {
		return mileNumber;
	}

	public void setMileNumber(Float mileNumber) {
		this.mileNumber = mileNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	

}
