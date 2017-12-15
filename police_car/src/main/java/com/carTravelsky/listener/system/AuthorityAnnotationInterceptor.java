package com.carTravelsky.listener.system;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

/**
 * 权限认证拦截器
 *
 */
public class AuthorityAnnotationInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			Class<?> clazz = hm.getBeanType();
			Method m = hm.getMethod();
			try {
				if (clazz != null && m != null) {
					boolean isClzAnnotation = clazz.isAnnotationPresent(Authority.class);
					boolean isMethondAnnotation = m.isAnnotationPresent(Authority.class);
					Authority authority = null;
					// 如果方法和类声明中同时存在这个注解，那么方法中的会覆盖类中的设定。
					if (isMethondAnnotation) {
						authority = m.getAnnotation(Authority.class);
					} else if (isClzAnnotation) {
						authority = clazz.getAnnotation(Authority.class);
					}
					if (authority != null) {
						
					}else{
					
					}
				}
			} catch (Exception e) {
				logger.error("验证出错{0}",e);
			}
		}
		return false;
	}
}