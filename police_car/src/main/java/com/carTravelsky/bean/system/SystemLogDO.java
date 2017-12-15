package com.carTravelsky.bean.system;

import java.util.Date;

public class SystemLogDO {
    /**  */
    private Integer logID;
    /**  */
    private Integer userID;
    /**  */
    private String userName;
    /**  */
    private String className;
    /**  */
    private String methodName;
    /**  */
    private String requestData;
    /**  */
    private Date requestTime;
    /**  */
    private String requestIp;
    
    private String starSystemtLogDate;
    private String endSystemLogDate;

    public String getStarSystemtLogDate() {
		return starSystemtLogDate;
	}

	public void setStarSystemtLogDate(String starSystemtLogDate) {
		this.starSystemtLogDate = starSystemtLogDate;
	}

	public String getEndSystemLogDate() {
		return endSystemLogDate;
	}

	public void setEndSystemLogDate(String endSystemLogDate) {
		this.endSystemLogDate = endSystemLogDate;
	}

	/**
     * 获取
     * @return logID
     **/
    public Integer getLogID(){
        return this.logID;
    }

    /**
     * 设置
     * @param logID
     **/
    public void setLogID(Integer logID){
        this.logID = logID;
    }

    /**
     * 获取
     * @return userID
     **/
    public Integer getUserID(){
        return this.userID;
    }

    /**
     * 设置
     * @param userID
     **/
    public void setUserID(Integer userID){
        this.userID = userID;
    }

    /**
     * 获取
     * @return userName
     **/
    public String getUserName(){
        return this.userName;
    }

    /**
     * 设置
     * @param userName
     **/
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * 获取
     * @return className
     **/
    public String getClassName(){
        return this.className;
    }

    /**
     * 设置
     * @param className
     **/
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 获取
     * @return methodName
     **/
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * 设置
     * @param methodName
     **/
    public void setMethodName(String methodName){
        this.methodName = methodName;
    }

    /**
     * 获取
     * @return requestData
     **/
    public String getRequestData(){
        return this.requestData;
    }

    /**
     * 设置
     * @param requestData
     **/
    public void setRequestData(String requestData){
        this.requestData = requestData;
    }

    /**
     * 获取
     * @return requestTime
     **/
    public Date getRequestTime(){
        return this.requestTime;
    }

    /**
     * 设置
     * @param requestTime
     **/
    public void setRequestTime(Date requestTime){
        this.requestTime = requestTime;
    }

    /**
     * 获取
     * @return requestIp
     **/
    public String getRequestIp(){
        return this.requestIp;
    }

    /**
     * 设置
     * @param requestIp
     **/
    public void setRequestIp(String requestIp){
        this.requestIp = requestIp;
    }


    public SystemLogDO clone(){
        try{
            return (SystemLogDO)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}