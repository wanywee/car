package com.carTravelsky.listener.system;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.SystemLogDO;
import com.carTravelsky.service.system.SystemLogService;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.DateUtils;
import com.stopec.web.context.SessionContext;
import com.stopec.web.utils.WebUtils;


public class SystemLogInterceptor {  
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SystemLogService systemLogBiz;
	
	public Object recordLog(ProceedingJoinPoint jp) throws Throwable { 
		try {
			{   //兼容 spring 3.1
				Signature signature2 = jp.getSignature();
				Method method = signature2.getClass().getMethod("getMethod");
				method.setAccessible(true);
			}
			/*IgnoreLog annotation = invoke.getAnnotation(IgnoreLog.class);//判断是否忽略记录日志
			if(annotation != null){
				return jp.proceed();
			}*/
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			CarSysUserDO userInfo = (CarSysUserDO) SessionContext.Attribute.get(request, "carSysUserDOLogin");
			if(userInfo == null){//未登录不记录日志
				return jp.proceed();
			}
			String className = jp.getTarget().getClass().getSimpleName();
			String methodName = jp.getSignature().getName();
			String ip = WebUtils.getIpAddr(request);
			Object[] args = jp.getArgs();
			JSONObject argsJson = new JSONObject();
			if(args != null){
				for(int i = 0; i < args.length; i++){
					Object arg = args[i];
					if (arg != null && !(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse)){
						argsJson.put(arg.getClass().getSimpleName(), arg);
					}
				}
			}
			SystemLogDO logDO = new SystemLogDO();
			logDO.setUserID(userInfo.getId());
			logDO.setUserName(userInfo.getUsername());
			logDO.setClassName(className);
			logDO.setMethodName(methodName);
			logDO.setRequestData(argsJson.toJSONString());
			logDO.setRequestTime(DateUtils.date.systemDate());
			logDO.setRequestIp(ip);
			try {
				systemLogBiz.saveSystemLog(logDO);
				logger.info("UserName:{0}, Class:{1}, Method:{2}, Arguments:{3}", userInfo.getUsername(), className, methodName, logDO.getRequestData());
			} catch (Exception e) {
				logger.error("保存系统日志异常，保存数据：{0}, Exception: {1}", logDO, e.getMessage());
			}
		} catch (Exception e) {
			logger.error("recordLog Exception: {0}", e);
		}
		return jp.proceed();
	}
	
}  