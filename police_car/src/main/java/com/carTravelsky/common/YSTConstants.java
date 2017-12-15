package com.carTravelsky.common;

import java.text.SimpleDateFormat;

/**
 * 常量接口
 * 
 * @author admin
 *
 */
public interface YSTConstants {
	/** 通用配置 **/
	public static final String SUCCESS = "操作成功！";// 成功
	public static final String SUCCESS_EN = "SUCCESS";// 成功
	public static final String FAILED = "FAILED";// 失败
	public static final String EXIST = "EXIST";// 已存在
	public static final String USER_IS_LOGINED = "您的账号已经登陆了！";// 已登录
	public static final String EXPORT_PATH = "file";
	public static final String CAR_IMAGE = "file/carImage";
	public static final String NO_PERMISSION = "对不起,你没有权限操作";//
	public static final String CHARACTER_UTF8 = "utf-8";// 编码格式
	public static final String LOGIN_RANDOM_VALIDATION_CODE = "LOGIN_RANDOM_VALIDATION_CODE";// 登录验证码
	public static final Integer DRIVER_DEPT = 53;//驾驶员虚拟部门53 

	/** 用户状态 **/
	public static final String USER_ERROR = "用户名或者密码错误";
	public static final String VALIDATECODEISNULL = "验证码不能为空";
	public static final String VALIDATECODE = "验证码输入错误";
	public static final String REMENBER = "01";// 记住密码
	public static final String DEFULTPASS = "123456";

	// 菜单类型
	public static Integer MENU_TYPE_MENU = 1;
	public static Integer MENU_TYPE_Button = 2;

	//
	public static final Integer ENABLE = 1;// 启用
	public static final Integer DISENABLE = 2;// 禁用

	// app开始请求路径
	public static final String APPURL = "driver";
	public static final String APPSEURL = "scanWeight";
	public static final String APPTHURL = "groundService";
	public static final String APPFIURL = "sorter";
	public static final String ACCEPT = "carInfo";
	// user Session 中的key
	public static final String USERINFO = "carSysUserDOLogin";

	// DELETE_TYPE
	// 删除
	public static final Integer DELETE_CODE_DEL = 2;
	// 正常
	public static final Integer DELETE_CODE_NORMAL = 1;

	// 角色编码前缀
	public static final String ROLECODEPRE = "R-";

	// 角色编码前缀
	public static final String DEPTCODE = "D-";

	// 时间转字符串
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

	public static final String cookieDomainName = "carTravelsky";

	public static final String webKey = "123456";

	// 默认当前页码
	public static final Integer DEFAULT_CURRENT_PAGE = 1;
	// 默认一页显示数据条数
	public static final Integer DEFAULT_PAGESIZE = 5;
	// 车辆状态
	// 可用
	public static final Integer CAR_TYPE_USEABLE = 1;
	// 出车
	public static final Integer CAR_TYPE_DISABLE = 2;
	// 维修
	public static final Integer CAR_TYPE_REPAIR = 3;
	//已回车
	public static final Integer CAR_TYPE_RETURN = 4;
	//接口定义返回的code
	public static final String YTXY_SUCCESS = "0";
	public static final String YTXY_FAIL = "1002";
	
	
	//货物 状态码 1行李  2货物
	public static final Integer  CARGO_TYPE_LUGGAGE = 1;
	public static final Integer  CARGO_TYPE_CARGO = 2;
	//货监 是否交接状态码
	public static final Integer  JOIN_STATUS_YES = 1;
	
	public static final Integer  JOIN_STATUS_NO = 2;
	//行李/平板车是否扫描 	
	public static final Integer LUGGAGE_TYPE_NO =1 ;
	
	public static final Integer LUGGAGE_TYPE_YES =2 ;
	
	
	// 测试日期（因为当前eterm老是日期不确定，所以暂时在数据库中更新一下日期，方便测试的进行）
    public static String CONTROL_DATE = "2017-11-15";

    // 是否是测试日期标识
    public static boolean IS_TESTDATE = false;
    
    //1可见当前部门数据
    public static final Integer IS_CURRENT_DEP_VIEW = 1;
    //2可见全部部门数据
    public static final Integer IS_ALL_DEP_VIEW = 2;
    
    // 调度中心的id(作为一个部门处理)
    public static final Integer DISPATCH_DEPT_ID = 38;
    
    
    //部门根节点
	public static final Integer ROOT_DEPT_ID = 1;
	
	 //是否是叶节点
	public static final Boolean IS_LEAF_TRUE = true;
	public static final Boolean IS_LEAF_FALSE = false;
	//预计回车设计值
	public static final String PREDICTTIME = "PREDICTTIME";
	
	/*车辆调度过程状态*/
	//等待本单位领导审批
	public static final Integer PROCESS_STATUS_WAITING_LEADER_APPROVE = 1;

	//等待调度中心审批
	public static final Integer PROCESS_STATUS_WAITING_DISPATCHING_CENTER = 2;
	
	//已派车
	public static final Integer PROCESS_STATUS_HAVE_SEND_CAR = 3;
	
	//已回车
	public static final Integer PROCESS_STATUS_HAVE_BACK_CAR = 4;
	
	/**车辆状态*/
	//1可用
	public static final Integer CAR_STATUS_NORMAL=1;
	
	//出车中（使用中）
	public static final Integer CAR_STATUS_BE_USE = 2;
	/*3维修*/	
	public static final Integer CAR_STATUS_REPAIRING = 3;	
	/*4其他*/	
	public static final Integer CAR_STATUS_OTHER = 4;
	// 申请中
	public static final Integer CAR_STATUS_APPLYING = 5;
	
	
}
