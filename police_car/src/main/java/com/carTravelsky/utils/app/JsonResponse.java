package com.carTravelsky.utils.app;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @ClassName: JsonResponse
 * @Description: TODO
 * @author: wangyi
 * @date: 2017年10月13日 上午9:26:36
 */
@JsonSerialize
public class JsonResponse implements Serializable {


    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7941354869234545831L;

	public static final int SUCCESS_CODE=1;
    public static final String SUCCESS_MESSAGE="请求服务器成功！";
    public static final int GLOBOL_BULL_SUCCESS_CODE=-11111;
    public static final String GLOBOL_BULL_SUCCESS_MESSAGE="请求服务器失败！";

    
    private int responseCode;
    private String responseMessage;
    private long responseTime;
    private Object responseData;

    private JsonResponse(int responseCode , String responseMessage, Object responseData){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        if(responseData != null){
            this.responseData = responseData;
        }else{
            this.responseData = new HashMap<String, Object>();
        }
        this.responseTime = DateTools.getCurrentTime();
    }

    public static JsonResponse RespSuccess(Object responseData){
        return new JsonResponse(SUCCESS_CODE,SUCCESS_MESSAGE,responseData);
    }
    
    public static JsonResponse RespSuccess(String responseMessage,Object responseData){
        return new JsonResponse(SUCCESS_CODE,responseMessage,responseData);
    }

    public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	public static JsonResponse RespSuccess(){
        return new JsonResponse(SUCCESS_CODE,SUCCESS_MESSAGE,null);
    }
    public static JsonResponse RespGlobolNullSuccess(){
        return new JsonResponse(GLOBOL_BULL_SUCCESS_CODE,GLOBOL_BULL_SUCCESS_MESSAGE,null);
    }
    public static JsonResponse RespGlobolNullSuccess(Object responseData){
        return new JsonResponse(GLOBOL_BULL_SUCCESS_CODE,GLOBOL_BULL_SUCCESS_MESSAGE,null);
    }
    public static JsonResponse RespFail(int responseCode, String responseMessage,Object responseData){
        return  new JsonResponse(responseCode,responseMessage,responseData);
    }

}
