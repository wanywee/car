package com.carTravelsky.listener.system;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * @author ALVIN
 * @version 1.0
 */
/* 
 * 文件名: com.mobile.coupon.listener.ApplicationStartupListener.java
 *
 * 修改记录
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 修改日期				修改者						备注信息
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 2013-12-16			ALVIN				初始化版本	
 * 
 */
@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			logger.info("初始化缓存");
		} catch (Exception e) {
			logger.error("初始化缓存Exception: {0}", e);
		}	
	}

}

