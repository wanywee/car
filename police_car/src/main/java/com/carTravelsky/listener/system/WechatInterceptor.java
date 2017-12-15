package com.carTravelsky.listener.system;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.xmlbeans.impl.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.CarSysMenuService;
import com.carTravelsky.service.system.CarSysUserService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.CookieUtil;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.web.context.SessionContext;



/**
 * 拦截器拦截一些需要特殊权限的地址
 * 
 * @author Administrator
 *
 */
public class WechatInterceptor extends HandlerInterceptorAdapter {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private List<String> uncheckUrls;

	@Autowired
	private CarSysUserService carSysUserService;
	@Autowired
	private CarSysMenuService carSysMenuService;
	@Autowired
	private KeyCodeMasterService keyCodeService;
	
	public List<String> getUncheckUrls() {
		return uncheckUrls;
	}

	public void setUncheckUrls(List<String> uncheckUrls) {
		this.uncheckUrls = uncheckUrls;
	}

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
		String requestUrl = request.getRequestURI();
		if(requestUrl.contains(YSTConstants.APPURL)||requestUrl.contains(YSTConstants.APPFIURL)||requestUrl.contains(YSTConstants.APPSEURL)
				||requestUrl.contains(YSTConstants.APPTHURL)
				||requestUrl.contains(YSTConstants.ACCEPT)){
			return true;
		}
		requestUrl = requestUrl.substring(requestUrl.lastIndexOf("/"), requestUrl.length());
		if (uncheckUrls.contains(requestUrl)) {
			return true;
		} else {
			// 读取Cookie,自动完成登陆操作
			// 根据cookieName取cookieValue
			Cookie cookies[] = request.getCookies();
			String cookieValue = null;
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++)
					if (YSTConstants.cookieDomainName.equals(cookies[i].getName())) {
						cookieValue = cookies[i].getValue();
						break;
					}
			}
			// 如果cookieValue为空,返回,
			if (cookieValue == null) {
				CarSysUserDO carSysUserDO = (CarSysUserDO) request.getSession().getAttribute("carSysUserDOLogin");
				if(carSysUserDO == null){
					if(requestUrl.contains("index")){
						return true;
					}
					response.sendRedirect(request.getContextPath() + "/index");
					return false;
				}else{
					if (requestUrl.contains("index")) {
						response.sendRedirect(request.getContextPath() + "/main");
					}
					return true;
				}
			}
			// 如果cookieValue不为空,才执行下面的代码
			// 先得到的CookieValue进行Base64解码
			String cookieValueAfterDecode = new String(Base64.decode(cookieValue.getBytes()), "utf-8");
			// 对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
			String cookieValues[] = cookieValueAfterDecode.split(":");
			if (cookieValues.length != 3) {
				if(requestUrl.contains("index")){
					return true;
				}
				response.sendRedirect(request.getContextPath() + "/index");
				return false;
			}
			// 判断是否在有效期内,过期就删除Cookie
			long validTimeInCookie = new Long(cookieValues[1]);
			if (validTimeInCookie < System.currentTimeMillis()) {
				// 删除Cookie
				CookieUtil.clearCookie(response);
				if(requestUrl.contains("index")){
					return true;
				}
				response.sendRedirect(request.getContextPath() + "/index");
				return false;
			}
			// 取出cookie中的用户名,并到数据库中检查这个用户名,
			String username = cookieValues[0];

			// 根据用户名到数据库中检查用户是否存在
			CarSysUserDO carSysUserDO  = new CarSysUserDO();
			carSysUserDO.setUsername(username);
			List<CarSysUserDO> users = carSysUserService.getCarSysUserList(carSysUserDO);
			if (users.isEmpty()) {
				if(requestUrl.contains("index")){
					return true;
				}
				response.sendRedirect(request.getContextPath() + "/index");
				return false;
			}
			// 如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
			else {
				String md5ValueInCookie = cookieValues[2];
				String md5ValueFromUser = CookieUtil.getMD5(users.get(0).getUsername() + ":" + users.get(0).getPassword()
						+ ":" + validTimeInCookie + ":" + YSTConstants.webKey);
				// 将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
				if (md5ValueFromUser.equals(md5ValueInCookie)) {
					List<CarSysMenuDO> carSysMenuDOs = carSysMenuService.getallPermiss(users.get(0).getId());
					if(carSysMenuDOs.size() == 0){
						response.sendRedirect(request.getContextPath() + "/index");
						return false;
					}
					users.get(0).setCarSysMenuDOs(carSysMenuDOs);
					SessionContext.Attribute.set(request, YSTConstants.USERINFO, users.get(0));
					keyCodeService.refreshCache(users.get(0).getCompanyId());
					if(requestUrl.contains("index")){
						response.sendRedirect(request.getContextPath() + "/main");
						return true;
					}
					return true;
				}
			}
		}
		return true;
	}
}
