package com.carTravelsky.utils;

import com.carTravelsky.common.YSTConstants;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

import net.sf.json.JSONObject;

public class ResponseResult {
	protected final static Logger logger = LoggerFactory.getLogger(ResponseResult.class);

	private String code;
	private static Integer i=228;
	private static Integer j=228;

	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 成功返回
	 * 
	 * @Title: getSuccess
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getSuccess() {
		try {
			ResponseResult responseResult = new ResponseResult();
			responseResult.setCode(YSTConstants.YTXY_SUCCESS);
			responseResult.setMessage("success");
			return JSONObject.fromObject(responseResult).toString();
		} catch (Exception e) {
			logger.error("result{0}", e);
			return null;
		}
	}

	/**
	 * 失败返回
	 * 
	 * @Title: getSuccess
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getError() {
		try {
			ResponseResult responseResult = new ResponseResult();
			responseResult.setCode(YSTConstants.YTXY_FAIL);
			responseResult.setMessage("fail");
			return JSONObject.fromObject(responseResult).toString();
		} catch (Exception e) {
			logger.error("result{0}", e);
			return null;
		}
	}
	
	public static void main(String[] args) {
		int i=20;
		int j=20;
		int k= i+j;
		ResponseResult responseResult = new ResponseResult();
		System.out.println(k);
		responseResult.zhi(k);
		System.out.println(k);
	}

	private  int zhi(int k) {
		return k+2;
	}


}
