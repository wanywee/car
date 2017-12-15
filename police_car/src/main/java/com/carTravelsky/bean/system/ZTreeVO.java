package com.carTravelsky.bean.system;

/**
 * @author ALVIN
 * @version 1.0
 */
/* 
 * 文件名: com.cmcc.coupon.vo.ZTreeVO.java
 *
 * 修改记录
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 修改日期				修改者						备注信息
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 2013-12-18			ALVIN				初始化版本	
 * 
 */
public class ZTreeVO {
	private String id;
	private String pId;
	private String name;
	private Boolean open;
	private Boolean checked;
	private Boolean isParent;
	private String iconClass;
	private String path;
	private Integer labelID;
	private Integer status;
	private String endTime;	
	
	private String icon;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getpId(){
		return pId;
	}
	public void setpId(String pId){
		this.pId = pId;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public Boolean getOpen(){
		return open;
	}
	public void setOpen(Boolean open){
		this.open = open;
	}
	public Boolean getChecked(){
		return checked;
	}
	public void setChecked(Boolean checked){
		this.checked = checked;
	}
	public Boolean getIsParent(){
		return isParent;
	}
	public void setIsParent(Boolean isParent){
		this.isParent = isParent;
	}
	public String getIconClass(){
		return iconClass;
	}
	public void setIconClass(String iconClass){
		this.iconClass = iconClass;
	}
	public String getPath(){
		return path;
	}
	public void setPath(String path){
		this.path = path;
	}
	public Integer getLabelID() {
		return labelID;
	}
	public void setLabelID(Integer labelID) {
		this.labelID = labelID;
	}
	
	
	
}

