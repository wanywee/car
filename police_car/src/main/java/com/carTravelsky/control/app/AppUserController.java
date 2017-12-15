package com.carTravelsky.control.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.excetion.app.ExceptionCode;
import com.carTravelsky.excetion.app.Param;
import com.carTravelsky.service.system.CarSysUserService;
import com.carTravelsky.utils.app.JsonResponse;
import com.carTravelsky.utils.app.constant.CodeCondition;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.Coder;
import com.stopec.common.utils.StringUtils;
import com.stopec.web.context.SessionContext;

/**
 * @ClassName: AUserController
 * @Description: 移动端接口
 * @author: wangyi
 * @date: 2017年10月11日 下午3:07:31
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/app/user")
public class AppUserController {

	@Autowired
	private CarSysUserService carSysUserService;
	
	
	/**
	 * @Title: login
	 * @Description: TODO
	 * @param response
	 * @param request
	 * @param username 登陆名称
	 * @param password 登陆密码
	 * @return
	 * @throws GlobalException
	 * @return: JsonResponse
	 */
	@RequestMapping(value = "/login",method = {RequestMethod.POST})
	public JsonResponse login(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "username",required = true)String username,
			@RequestParam(value = "password",required = true)String password
			){
		CarSysUserDO loginedUser = (CarSysUserDO) request.getSession().getAttribute(YSTConstants.USERINFO);
		if(null != loginedUser){
			if(loginedUser.getUsername().equals(username.trim())){
				return JsonResponse.RespGlobolNullSuccess(CodeCondition.USER_IS_LOGINED);
			}
		}
		Map<String,Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(username)){
			map.put(CodeCondition.CONDITION, Param.USERNAME_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.UserExceptionCode.USERNAME_IS_NULL, 
							Param.USERNAME_IS_NULL.getMessage(),map);
		}
		if(StringUtils.isBlank(password)){
			map.put(CodeCondition.CONDITION, Param.PASSWORD_IS_NULL.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.UserExceptionCode.PASSWORD_IS_NULL, 
							Param.PASSWORD_IS_NULL.getMessage(),map);
		}
		CarSysUserDO user = new CarSysUserDO();
			user.setUsername(username);
			user.setPassword(Coder.MD5.encode(password));
		//判断是否有这个用户
		List<CarSysUserDO> userList=carSysUserService.getCarSysUserList(user);
		if(CollectionUtils.isEmpty(userList)){
			map.put(CodeCondition.CONDITION, Param.NO_USER_INSIDE.getMessage());
			return JsonResponse
					.RespFail(
							ExceptionCode.UserExceptionCode.NO_USER_INSIDE, 
							Param.NO_USER_INSIDE.getMessage(),map);
		}
		CarSysUserDO carSysUserLogin =userList.get(0);
		SessionContext.Attribute.set(request, "carSysUserDOLogin", carSysUserLogin);
		return JsonResponse.RespSuccess(carSysUserLogin);
	} 	
	
	
	/**
	 * @Title: erWei
	 * @Description: 一阶段二维码 列子
	 * @param response
	 * @return: void
	 */
//	@RequestMapping(value="/QRCode",method={RequestMethod.GET})
//	public void QRCode(HttpServletResponse response){
//		response.setContentType("image/png");
//		ServletOutputStream out = null;
//		//查询生成二维码的参数
//		String content = "喊你扫码 你就扫了 你是二货吗？222222 还不快点交钱 不然你手机就要挂了。";
//		try {
//			out = response.getOutputStream();
//			BufferedImage image = QRUtils.generate(content);
//			ImageIO.write(image,"png", out);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally{
//			if(out != null){
//				try {
//					out.close();
//					out.flush();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
}
