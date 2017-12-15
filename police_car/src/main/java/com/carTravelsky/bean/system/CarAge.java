package com.carTravelsky.bean.system;

public class CarAge {

	private String label;
	private int value;
	public CarAge(String name,int number){
		this.label = name;
		this.value = number;
	}
	public String getName() {
		return label;
	}
	public void setName(String name) {
		this.label = name;
	}
	public int getNumber() {
		return value;
	}
	public void setNumber(int number) {
		this.value = number;
	}
	@Override
	public String toString() {
		return "CarAge [name=" + label + ", number=" + value + "]";
	}
	
}
