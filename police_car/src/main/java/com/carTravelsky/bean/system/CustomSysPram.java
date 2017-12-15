package com.carTravelsky.bean.system;

public class CustomSysPram {
	
	/** 在外车辆闲置时间  */
	private String idleCarTime;
	/**  是否显示到期提示窗体 */
	// private String window;
	/**  设置到期提示时间 */
	private String expireTime;
	/** 预计回车时间  */
	private String predictTime;
	/**  回车提醒时间 */
	private String remindTime;
	
	
	public String getIdleCarTime() {
		return idleCarTime;
	}
	public void setIdleCarTime(String idleCarTime) {
		this.idleCarTime = idleCarTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
	public String getPredictTime() {
		return predictTime;
	}
	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}
	public String getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
	
}
