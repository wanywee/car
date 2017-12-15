package com.carTravelsky.excetion;

import java.sql.SQLException;

/**
 * 
 * @author zhaoyu
 * @since 2011-3-8
 * 功能：完成数据库异常信息
 *
 */
public class DataBaseException extends SQLException {

	private static final long serialVersionUID = 79189728553028671L;

	public DataBaseException(String messageCode){
		super(messageCode);
	}
	
}
