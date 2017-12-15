package com.carTravelsky.control.system;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.carTravelsky.bean.pageView.Top;
import com.carTravelsky.bean.system.CarSysMenuDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.EmpDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Page;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.system.CarSysMenuService;
import com.carTravelsky.service.system.CarSysUserService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.CookieUtil;
import com.carTravelsky.utils.CreateImageCode;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.excel.CSVModel;
import com.stopec.common.excel.Converter;
import com.stopec.common.excel.POIExcelModel;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.Coder;
import com.stopec.common.utils.Coder.Base64;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.SessionContext;

@Controller
public class IndexControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private KeyCodeMasterService keyCodeService;
	@Autowired
	private CarSysMenuService carSysMenuService;
	@Autowired
	private CarSysUserService carSysUserService;

	/**
	 * @Title: main
	 * @Description: 访问跳转到系统首页
	 * @param response
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月12日 上午11:17:39
	 */
	@RequestMapping("main")
	public String main(HttpServletResponse response,
			HttpServletRequest request, Model model) {
		return "index";
	}
	@RequestMapping("/toHomePagePrompt")
	public ModelAndView  toHomePagePrompt(HttpServletRequest request){
		try {
			ModelAndView model = new ModelAndView();
			CarSysUserDO loginedUser = (CarSysUserDO) request.getSession().getAttribute(YSTConstants.USERINFO);
			List<Top> topList=carSysMenuService.getTopList(loginedUser);
			model.addObject("topList", topList);
			model.setViewName("common/homePagePrompt");
			return model;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("首页信息获取错误", e);
		}
		return null;
	}

	/**
	 * @Title: 用户登陆
	 * @Description: 访问验证用户登陆信息
	 * @param carSysUserDO
	 *            用户实体
	 * @param validateCode
	 *            验证码
	 * @param remember
	 *            是否记住用户
	 * @param response
	 * @param request
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月12日 上午11:17:39
	 */
	@RequestMapping(value = "home", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String home(CarSysUserDO carSysUserDO, String validateCode,
			String remember, HttpServletResponse response,
			HttpServletRequest request) {
	/*	CarSysUserDO loginedUser = (CarSysUserDO) request.getSession().getAttribute(YSTConstants.USERINFO);
		
		/*if(null != loginedUser){
			if(loginedUser.getUsername().equals( carSysUserDO.getUsername().trim())){
				return YSTConstants.USER_IS_LOGINED;
			}
		}*/
		try {
			// 去掉空格
			carSysUserDO.setPassword(carSysUserDO.getPassword().trim());
			carSysUserDO.setUsername(carSysUserDO.getUsername().trim());
			validateCode = validateCode.trim();
			// 判断用户是否记住信息
			if (StringUtils.equals(remember, "on")) {
				remember = "01";
			}
			// 获取后台存的登陆验证码
			String loginVC = (String) request.getSession().getAttribute(
					YSTConstants.LOGIN_RANDOM_VALIDATION_CODE);

			// 验证是否为null
			if (StringUtils.isBlank(carSysUserDO.getUsername())
					|| StringUtils.isBlank(carSysUserDO.getPassword())) {
				return YSTConstants.USER_ERROR;
			} else if (StringUtils.isBlank(validateCode)) {
				return YSTConstants.VALIDATECODEISNULL;
			}
			// 判断验证码是否输入正确
			if (!StringUtils.equalsIgnoreCase(validateCode, loginVC)) {
				return YSTConstants.VALIDATECODE;
			}
			//赋值
			String password1 = carSysUserDO.getPassword();
				//编码密码
				carSysUserDO.setPassword(Coder.MD5.encode(carSysUserDO.getPassword()));
				//判断是否有这个用户
				List<CarSysUserDO> userList = carSysUserService.getCarSysUserList(carSysUserDO);
				
				if(userList.size()>0){
					CarSysUserDO carSysUserLogin = userList.get(0);
					/*strat 解决涪陵公安权限问题  2017-11-30 by zy*/
					carSysUserLogin.setCompanyId(carSysUserLogin.getDeptID());
					/*end  解决涪陵公安权限问题  2017-11-30 by zy*/
					if(StringUtils.equals(carSysUserLogin.getStatus()+"", "2")){
						return YSTConstants.NO_PERMISSION;
					}
					//获取当前用户的所有权限
					List<CarSysMenuDO> carSysMenuDOs=null;
					if("admin".equalsIgnoreCase(carSysUserDO.getUsername())){
						CarSysMenuDO sysMenuDO=new CarSysMenuDO();
						sysMenuDO.setStatus(YSTConstants.DELETE_CODE_NORMAL);
					    carSysMenuDOs = carSysMenuService.getCarSysMenuList(sysMenuDO);
					}else{
						 carSysMenuDOs = carSysMenuService.getallPermiss(carSysUserLogin.getId());
					}
					if(carSysMenuDOs.size() == 0){
						return YSTConstants.NO_PERMISSION;
					}
					carSysUserLogin.setCarSysMenuDOs(carSysMenuDOs);
					SessionContext.Attribute.set(request, YSTConstants.USERINFO, carSysUserLogin);
					if(StringUtils.equals(remember, YSTConstants.REMENBER)){
						CookieUtil.saveCookie(carSysUserLogin, response);
						//保存cookie						
						Cookie username = new Cookie("username", carSysUserLogin.getUsername()); 
						username.setMaxAge(60 * 60 * 24 * 7);
						username.setPath("/");
						
						//编码密码
						String passwordBase64 = new String(Base64.encode(password1));
						Cookie password = new Cookie("password", passwordBase64);
						password.setMaxAge(60 * 60 * 24 * 7);
						password.setPath("/");
						
						//保存cookie
						response.addCookie(username);
						response.addCookie(password);
					}else{
					Cookie username = new Cookie("username", null);
					username.setMaxAge(0);
					username.setPath("/");
				    response.addCookie(username);
				    
				    Cookie password = new Cookie("password", null);
				    password.setMaxAge(0);
				    password.setPath("/");
				    response.addCookie(password);					
				}
					keyCodeService.refreshCache(carSysUserLogin.getCompanyId());
					return "index";
				}else{
					return YSTConstants.USER_ERROR;
				}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("登陆出错{0}", e);
			return YSTConstants.USER_ERROR;
		}
	}

	/**
	 * 
	 * @Title: loginOut
	 * @Description: 用户退出
	 * @param response
	 * @param request
	 * @return
	 * @return: String
	 * @author: fuxinrong
	 * @date: 2017年10月16日 上午9:22:31
	 */
	@RequestMapping("loginOut")
	public String loginOut(HttpServletResponse response,
			HttpServletRequest request) {
		SessionContext.Attribute.remove(request, YSTConstants.USERINFO);
		request.getSession().invalidate();
		CookieUtil.clearCookie(response);
		return "login";
	}

	/**
	 * 异步加载菜单
	 * 
	 * @Title: getMenu
	 * @Description: TODO
	 * @return
	 * @return: List<CarSysMenuDO>
	 */
	@RequestMapping(value = "getMenu")
	@ResponseBody
	public List<CarSysMenuDO> getMenu(HttpServletRequest request) {
		// 根据当前用户获取菜单
		CarSysUserDO carSysUserLogin = (CarSysUserDO) SessionContext.Attribute
				.get(request, YSTConstants.USERINFO);
		return carSysUserLogin.getPermissionTree(carSysUserLogin
				.getCarSysMenuDOs());
	}

	/**
	 * 注解事务
	 * 
	 * @return
	 */
	@RequestMapping("/TestInsert")
	@ResponseBody
	public ReturnDataInfo<Object> TestInsert() {
		List<KeyCodeMasterDO> codeDOs = new ArrayList<>();
		KeyCodeMasterDO KeyCodeMasterDO1 = new KeyCodeMasterDO();
		KeyCodeMasterDO1.setDisplay("测试事务");
		KeyCodeMasterDO KeyCodeMasterDO = new KeyCodeMasterDO();
		KeyCodeMasterDO
				.setDisplay("测试事务1111111111111111111111111111111111111111111111");
		KeyCodeMasterDO KeyCodeMasterDO2 = new KeyCodeMasterDO();
		KeyCodeMasterDO2.setDisplay("测试事务3");
		codeDOs.add(KeyCodeMasterDO1);
		codeDOs.add(KeyCodeMasterDO);
		codeDOs.add(KeyCodeMasterDO2);
		keyCodeService.TestInsert(codeDOs);
		return ReturnDataInfo.createFailedExecuteResult("注解事务");
	}

	/**
	 * 手动事务
	 * 
	 * @return
	 */
	@RequestMapping("/TestInsertManual")
	@ResponseBody
	public ReturnDataInfo<Object> TestInsertManual() {
		List<KeyCodeMasterDO> codeDOs = new ArrayList<>();
		KeyCodeMasterDO KeyCodeMasterDO1 = new KeyCodeMasterDO();
		KeyCodeMasterDO1.setDisplay("测试事务");
		KeyCodeMasterDO KeyCodeMasterDO = new KeyCodeMasterDO();
		KeyCodeMasterDO
				.setDisplay("测试事务1111111111111111111111111111111111111111111111");
		KeyCodeMasterDO KeyCodeMasterDO2 = new KeyCodeMasterDO();
		KeyCodeMasterDO2.setDisplay("测试事务3");
		codeDOs.add(KeyCodeMasterDO1);
		codeDOs.add(KeyCodeMasterDO);
		codeDOs.add(KeyCodeMasterDO2);
		keyCodeService.TestInsertManual(codeDOs);
		return ReturnDataInfo.createFailedExecuteResult("手动事务");
	}

	@RequestMapping("testDownFileCsv")
	@ResponseBody
	public ReturnDataInfo<Object> testCsv(HttpServletRequest request) {
		try {
			List<KeyCodeMasterDO> KeyCodeMasterDOs = keyCodeService
					.getKeyCodeMasterList(null);
			CSVModel model = new CSVModel();
			model.setConverter(new Converter<KeyCodeMasterDO>() {
				@Override
				public List<Object> convert(KeyCodeMasterDO trans) {
					List<Object> list = new ArrayList<Object>();
					list.add(trans.getKeyID());
					list.add(trans.getKeyType());
					list.add(trans.getCode());
					list.add(trans.getDecode());
					list.add(trans.getDisplay());
					return list;
				}
			});
			String[] headers = { "编号", "类型", "编码", "反编码", "描述" };
			model.writeSheet("", headers, KeyCodeMasterDOs);
			String path = request.getServletContext().getRealPath("/");
			String real = model.writeFile(path + YSTConstants.EXPORT_PATH,
					"日汇总报表csv", "csv");
			real = real.replace(path, "");
			System.out.println(real);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage(Base64
					.encode(real));
		} catch (Exception e) {
			logger.error("导出出错{0}", e);
			return null;
		}

	}

	@RequestMapping("testDownFilePoi")
	@ResponseBody
	public ReturnDataInfo<Object> test(HttpServletRequest request) {
		try {
			String[] headers = { "编号", "类型", "编码", "反编码", "描述" };
			logger.info("使用poi测试");
			List<KeyCodeMasterDO> KeyCodeMasterDOspoi = keyCodeService
					.getKeyCodeMasterList(null);
			POIExcelModel poi = new POIExcelModel();
			poi.setConverter(new Converter<KeyCodeMasterDO>() {
				@Override
				public List<Object> convert(KeyCodeMasterDO trans) {
					List<Object> list = new ArrayList<Object>();
					list.add(trans.getKeyID());
					list.add(trans.getKeyType());
					list.add(trans.getCode());
					list.add(trans.getDecode());
					list.add(trans.getDisplay());
					return list;
				}
			});
			logger.info("返回的地址" + KeyCodeMasterDOspoi);
			poi.writeSheet("", headers, KeyCodeMasterDOspoi);
			String path = request.getServletContext().getRealPath("/");
			System.out.println(path);
			String real = poi.writeFile(path + YSTConstants.EXPORT_PATH,
					"支-日汇总报表poi.xlsx", "");
			real = real.replace(path, "");
			logger.info("返回的地址" + real);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage(Base64
					.encode(real));
		} catch (Exception e) {
			logger.error("导出出错{0}", e);
			return null;
		}
	}

	/**
	 * 获取keyCode
	 * 
	 * @param request
	 * @param kind
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getListKeyCode")
	@ResponseBody
	public List<Select2VO> getListKeyCode(HttpServletRequest request,
			String kind, @RequestParam(value = "q", required = false) String str) {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			logger.info("查询项目keyCode");
			return keyCodeService.getKindCode(kind, str,carSysUser);
		} catch (Exception e) {
			logger.error("查询项目keyCode：{0}", e);
			return null;
		}
	}

	/**
	 * 
	 * @Title: getListDisplay
	 * @Description: 获取的下拉框 key 和 value 都是value的值（显示的值）
	 * @param request
	 * @param kind
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:54:03
	 */
	@RequestMapping("/getListDisplay")
	@ResponseBody
	public List<Select2VO> getListDisplay(HttpServletRequest request,
			String kind, @RequestParam(value = "q", required = false) String str) {
		try {
			CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
			logger.info("查询项目keyCode");
			return keyCodeService.getKindDisplay(kind, str,carSysUser);
		} catch (Exception e) {
			logger.error("查询项目keyCode：{0}", e);
			return null;
		}
	}

	@RequestMapping("index")
	public String index(HttpServletResponse response, HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("test")
	public String test(HttpServletResponse response, HttpServletRequest request) {
		return "TestDataTable/test";
	}

	@RequestMapping("vehicleState")
	public String vehicleState(HttpServletResponse response,
			HttpServletRequest request) {
		return "vehicleState";
	}

	@RequestMapping("/refreshCache")
	public String refreshCache(HttpServletRequest request) {
		try {
			CarSysUserDO carSysUserLogin = (CarSysUserDO) SessionContext.Attribute
					.get(request, YSTConstants.USERINFO);
			keyCodeService.refreshCache(carSysUserLogin.getCompanyId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@RequestMapping("/validateCode")
	@ResponseBody
	public void createValidateCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		CreateImageCode vCode = new CreateImageCode(120, 45, 4);
		request.getSession().setAttribute(
				YSTConstants.LOGIN_RANDOM_VALIDATION_CODE, vCode.getCode()); // 把随机数放在session中，可以在servlet中获取
		vCode.write(response.getOutputStream());
	}

	/**
	 * bootstarp-table 分页返回的数据 获取keyCode
	 * 
	 * @param request
	 * @param kind
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getListKeyCodes")
	@ResponseBody
	public Map<String, Object> getListKeyCodes(Integer pageSize,
			Integer pageNumber, Page pageBounds, HttpServletRequest request,
			KeyCodeMasterDO KeyCodeMasterDO)
			throws UnsupportedEncodingException {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			pageNumber = pageNumber / pageSize + 1;
			pageBounds.setRows(pageSize);
			pageBounds.setPage(pageNumber);
			List<KeyCodeMasterDO> keyCodeList = keyCodeService
					.getKeyCodeMasterList(KeyCodeMasterDO,carSysUser, pageBounds);
			return pageBounds.putResultObj(keyCodeList, keyCodeService
					.getKeyCodeMasterList(KeyCodeMasterDO).size());
		} catch (Exception e) {
			logger.error("加载列表出错：{0}", e);
			return null;
		}
	}

	/**
	 * jqGrid 分页返回的数据 获取keyCode
	 * 
	 * @param request
	 * @param kind
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/getListKeyCodesjqGrid")
	@ResponseBody
	public Map<String, Object> getListKeyCodesjqGrid(Integer pageSize,
			Integer pageNumber, PageBounds pageBounds,
			HttpServletRequest request, KeyCodeMasterDO KeyCodeMasterDO)
			throws UnsupportedEncodingException {
		CarSysUserDO carSysUser = (CarSysUserDO) SessionContext.Attribute.get(request, YSTConstants.USERINFO);
		try {
			List<KeyCodeMasterDO> keyCodeList = keyCodeService
					.getKeyCodeMasterList(KeyCodeMasterDO, carSysUser,pageBounds);
			return pageBounds.putResultObj(keyCodeList);
		} catch (Exception e) {
			logger.error("加载列表出错：{0}", e);
			return null;
		}
	}

	/**
	 * 测试验证数据
	 */
	@RequestMapping("/validateDataCode")
	@ResponseBody
	public void ValidateData() {
		EmpDO empDO = new EmpDO();
		empDO.setEmpNum("121");
		ValidationUtils.validate(empDO);
		ValidateResult result = empDO.getValidateResult();
		boolean result2 = result.getResult();
		List<String> messageList = result.getMessageList();
		String messageLine = result.getMessageLine();
		logger.info(messageList + "" + result2 + messageLine);
	}

}
