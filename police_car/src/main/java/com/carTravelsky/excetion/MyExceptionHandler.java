/**
 * 
 */
package com.carTravelsky.excetion;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * @ClassName: MyExceptionHandler
 * @Description: TODO
 * @author: zy
 * @date: 2017-10-12 上午9:48:55
 */
public class MyExceptionHandler implements HandlerExceptionResolver
{
	/**
	 * @Title: resolveException
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param ex
	 * @return
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 * @author: zy  
	 * @date: 2017-10-12 上午9:48:46
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception ex)
	{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("ex", ex);
		
		// 根据不同错误转向不同页面  
		if (ex instanceof AppRuntimeException) {
			return new ModelAndView("error/500", model);
		} else if (ex instanceof DataBaseException) {
			return new ModelAndView("error/500", model);
		} else {
			return new ModelAndView("error/500", model);
		}
	}
	
}
