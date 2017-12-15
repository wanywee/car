package com.carTravelsky.excetion.app;


/**
 * @ClassName: Param
 * @Description: 异常枚举
 * @author: wangyi
 * @date: 2017年10月12日 上午10:23:44
 */
public enum Param {
  
	PASSWORD_IS_NULL(1,"登陆密码不能为空！"),
	USERNAME_IS_NULL(2,"登陆名称不能为空！"),
	NO_USER_INSIDE(3,"当前用户不存在！请重新登陆！"),
	LICENSENO_IS_NULL(4,"车牌号不能为空"), 
	OILTYPE_IS_NULL(5,"油料标号不能为空"), 
	ADDOILNUMBER_IS_NULL(6,"加油数量不能为空"), 
	ADDOILMONEY_IS_NULL(7,"加油金额不能为空"), 
	ADDOILTIME_IS_NULL(8,"加油时间不能为空"), 
	INSERT_DATA_SUCCESSED(9,"新增数据成功！"),
	INSERT_DATA_FAILED(10,"新增数据失败！"),
	UPDATE_DATA_SUCCESSED(11,"修改数据成功！"),
	UPDATE_DATA_FAILED(12,"修改数据失败！"),

	CAR_IS_NOT_BEING(13,"当前车辆不存在！"), 
	DRIVER_NAME_IS_NULL(14,"司机名称不能为空！"), 
	ACCIDENT_TIME_IS_NULL(15,"事故时间不能为空！"), 
	WE_BEAR_IS_NULL(16,"我方承担金额不能为空！"), 
	OP_BEAR_IS_NULL(17,"对方承担金额不能为空！"), 
	INSUR_MONEY_IS_NULL(18,"保险金额不能为空！"), 
	HAPPEN_PLACE_IS_NULL(19,"发生地点不能为空！！"), 
	ACCIDENT_CONTENT_IS_NULL(20,"事故说明不能为空！"), 
	OUR_SITU_IS_NULL(21,"我方情况不能为空！"), 
	OTHER_SITU_IS_NULL(22,"对方情况不能为空！"), 
	TREAT_RESULT_IS_NULL(23,"处理结果不能为空！"),
	REPAIR_TIME_IS_NULL(24,"送修日期不能为空！"), 
	ESTIMATE_IS_NULL(25,"预计取车时间不能为空！"), 
	REPARI_REASON_IS_NULL(26,"送修原因不能为空！"), 
	REPAIR_PD_IS_NULL(27,"修理厂不能为空！"),
	HADNLER_ID_IS_NULL(28,"经手人不存在"),
	
	
	//保险记录param
	PONICYNUMBER_IS_NULL(40,"保单号不能为空!"),
	TYPE_IS_NULL(41,"保险类别不能为空!"),
	INSUREDATE_IS_NULL(42,"投保日期不能为空!"),
	ENDDATE_IS_NULL(42,"到期日期不能为空!"),
	COMPANY_IS_NULL(43,"投保单位不能为空!"),
	INSUREMONEY_IS_NULL(44,"投保金额不能为空!"),
	INSURANCEREMARK_IS_NULL(45,"投保内容不能为空!"),
	
	
	//保养记录param
	MAINTAINTYPE_NULL(50,"保养类型不能为空!"),
	MAINTAINDATE_NULL(51,"保养时间不能为空!"),
	MAINTAINENDDATE_NULL(52,"保养结束时间不能为空!"),
	MAINTAINUNIT_NULL(53,"保养公司不能为空!"),
	MAINTAINMILEAGE_NULL(54,"保养里程不能为空!"),
	MAINTAINMONEY_NULL(55,"保养金额不能为空!"),
	MAINTAINCONTENT_NULL(56,"保养内容不能为空!"), 



	
	
	//规费记录param
	FEES_PEYMENTNAME_NULL(60,"费用名称不能为空!"),
	FEES_PAYMENTMONEY_NULL(61,"缴费金额不能为空!"),
	FEES_PAYMENTDATE_NULL(62,"缴费日期不能为空!"),
	FEES_PAYMENTENDDATE_NULL(63,"结束日期不能为空!"),
	FEES_CHARGEUNIT_NULL(64,"收费单位不能为空!"),
	FEES_PAYMENTTYPE_NULL(65,"缴费方式不能为空!"),
	FEES_PERIOD_NULL(66,"周期月不能为空!"),

	
	//全局错误参数 JsonResponse.RespGlobolNullSuccess()
	REQUEST_SERVER_TIME_OUT(10001,"请求服务器超时！"),
	
	;

	

	

    /**
     * @fieldName: value
     * @fieldType: int
     * @Description: 状态码
     */
    private int value;
    /**
     * @fieldName: msg
     * @fieldType: String
     * @Description: 状态信息
     */
    private String message;

    Param(int value, String message){
        this.value=value;
        this.message=message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
