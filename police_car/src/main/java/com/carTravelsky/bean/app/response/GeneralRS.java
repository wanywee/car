package com.carTravelsky.bean.app.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeneralRS implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -5243202812483211318L;
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("type")
	private String type;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
