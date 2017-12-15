package com.carTravelsky.listener.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.CarSysMenuService;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.StringUtils;

/**
 * 权限拦截器
 * 
 * @author dg
 *
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private List<String> uncheckUrls;

	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

	@Autowired
	private CarSysMenuService permissionBiz;

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		logger.info("回调执行");
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		logger.info("调用controller后进入");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 获取请求的URL
		// 获取请求的URL
		String context = request.getContextPath();
		String requestUrl = request.getRequestURI();
		if(requestUrl.contains(YSTConstants.APPURL)||requestUrl.contains(YSTConstants.APPFIURL)
				||requestUrl.contains(YSTConstants.APPSEURL)
				||requestUrl.contains(YSTConstants.APPTHURL)
				||requestUrl.contains(YSTConstants.ACCEPT)){
			return true;
		}
		requestUrl = requestUrl.substring(requestUrl.lastIndexOf("/"), requestUrl.length());
		if (uncheckUrls.contains(requestUrl)) {
			return true;
		}
		 requestUrl = request.getRequestURI();
		// 首先判断这个连接是否是权限列表中的
		CarSysUserDO empDO = (CarSysUserDO) request.getSession().getAttribute("carSysUserDOLogin");
		if (empDO == null) {
			response.sendRedirect(request.getContextPath() + "/index");
			return false;
		}
		List<CarSysMenuDO> permissionList = permissionBiz.getCarSysMenuList(new CarSysMenuDO());
		boolean haveNeedPermiss = havePermiss(context,permissionList, requestUrl);
		if (haveNeedPermiss) {
			List<CarSysMenuDO> permisssionByRole = permissionBiz.getallPermiss(empDO.getId());
			boolean havePermiss = havePermiss(context,permisssionByRole, requestUrl);
			if (!havePermiss) {
				logger.info("没有权限操作");
				String json = JSONObject.toJSONString(YSTConstants.NO_PERMISSION);
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().write(json);
				return false;
			} else {
				logger.info("有权限操作");
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 判断是否有权限
	 * @param context 
	 * @Title: havePermiss
	 * @Description: TODO
	 * @param permisssionByRole
	 * @param requestUrl
	 * @return
	 * @return: boolean
	 */
	private boolean havePermiss(String context, List<CarSysMenuDO> permisssionByRole, String requestUrl) {
		for (CarSysMenuDO permissionDO : permisssionByRole) {
			if(permissionDO.getMenuUrl().equals("/")){
				continue;
			}
			if (StringUtils.equals(requestUrl, context+permissionDO.getMenuUrl())) {
				return true;
			}
		}
		return false;
	}
}
