package com.carTravelsky.excetion.app;


/**
 * @ClassName: ExceptionCode
 * @Description: 异常代码
 * @author: wangyi
 * @date: 2017年10月12日 上午10:24:23
 */
public interface ExceptionCode {
    /**
     * 服务器处理相关异常
     * <p>服务器定义的相关错误编码,,10000开始 </p>
     *wy
     * @author wee
     */
    public static interface GlobalExceptionCode {
        /**
         * 服务器处理错误
         */
        public static final int SERVER_ERROR = 10000;
		/**
		 *参数为空 
		 */
		public static final int PARAMS_IS_EMPTY = 10002;
    }
    
    /**
     * 用户异常模块 
     * 
     * */
    public static interface UserExceptionCode {
        /**
         *密码不能为空 
         */
    	public static final int PASSWORD_IS_NULL = 20001;
    	/**
    	 *用户名不能为空 
    	 */
    	public static final int USERNAME_IS_NULL = 20002;
    	/**
    	 *你没有登陆此平台 
    	 */
		public static final int NO_LOGINED_INSIDE = 20003;
		/**
    	 *不存在当前用户
    	 */
		public static final int NO_USER_INSIDE = 20004;
    }
    /**
     * 用户异常模块 
     * 
     * */
    public static interface CarExceptionCode {
        /**
         *车牌号不能为空
         */
    	public static final int LICENSENO_IS_NULL = 30001;
    	 /**
         *油料标号不能为空
         */
		public static final int OILTYPE_IS_NULL = 30002;
		 /**
         *加油数量不能为空
         */
		public static final int ADDOILNUMBER_IS_NULL = 30003;
		 /**
         *加油金额不能为空
         */
		public static final int ADDOILMONEY_IS_NULL = 30004;
		 /**
         *加油时间不能为空
         */
		public static final int ADDOILTIME_IS_NULL = 30005;
		 /**
         *新增数据失败
         */
		public static final int INSERT_DATA_FAILED = 30006;
		 /**
         *修改数据失败
         */
		public static final int UPDATE_DATA_FAILED = 30007;
		 /**
         *当前车辆不存在
         */
		public static final int CAR_IS_NOT_BEING = 30008;
		/**
         *司机名称不能为空
         */
		public static final int DRIVER_NAME_IS_NULL = 30009;
		/**
         *事故时间不能为空
         */
		public static final int ACCIDENT_TIME_IS_NULL = 30010;
		/**
         *我方承担金额不能为空
         */
		public static final int WE_BEAR_IS_NULL = 30011;
		/**
         *对方承担金额不能为空
         */
		public static final int OP_BEAR_IS_NULL = 30012;
		/**
         *保险赔偿金额 不能为空
         */
		public static final int INSUR_MONEY_IS_NULL = 30013;
		/**
         *发生地点不能为空
         */
		public static final int HAPPEN_PLACE_IS_NULL = 30014;
		/**
         *事故说明不能为空
         */
		public static final int ACCIDENT_CONTENT_IS_NULL = 30015;
		/**
         *我方情况不能为空
         */
		public static final int OUR_SITU_IS_NULL = 30016;
		/**
         *对方情况不能为空
         */
		public static final int OTHER_SITU_IS_NULL = 30017;
		/**
         *处理结果不能为空
         */
		public static final int TREAT_RESULT_IS_NULL = 30018;
		/**
         *送修日期不能为空
         */
		public static final int REPAIR_TIME_IS_NULL = 30019;
		/**
         *预计取车时间不能为空
         */
		public static final int ESTIMATE_IS_NULL = 30020;
		/**
         *送修原因不能为空
         */
		public static final int REPARI_REASON_IS_NULL = 30021;
		/**
         *修理厂不能为空
         */
		public static final int REPAIR_PD_IS_NULL = 30022;
		/**
         *经手人不能为空
         */
		public static final int HADNLER_ID_IS_NULL = 30023;
    }
    
    //保险记录ExceptionCode
    public static interface insuranceExceptionCode{
    	/**
        *保单号不能为空
        */
		public static final int PONICYNUMBER_IS_NULL = 40000;
		/**
		 *保险类别不能为空
		 */
		public static final int TYPE_IS_NULL = 40001;
		/**
		 *投保日期不能为空
		 */
		public static final int INSUREDATE_IS_NULL = 40002;
		/**
		 *到期日期不能为空
		 */
		public static final int ENDDATE_IS_NULL = 40003;
		/**
		 *投保单位不能为空
		 */
		public static final int COMPANY_IS_NULL = 40004;
		 /**
        *投保金额不能为空
        */
		public static final int INSUREMONEY_IS_NULL = 40005;
		 /**
        *投保内容不能为空
        */
		public static final int INSURANCEREMARK_IS_NULL = 40006;

    } 

    //保养记录ExceptionCode
    public static interface maintainExceptionCode{
    	/**
         *保养类型不能为空
         */
 		public static final int MAINTAINTYPE_NULL = 50000;
 		/**
 		 *保养时间不能为空
 		 */
 		public static final int MAINTAINDATE_NULL = 50001;
 		/**
 		 *保养结束时间不能为空
 		 */
 		public static final int MAINTAINENDDATE_NULL = 50002;
 		/**
 		 *保养公司不能为空
 		 */
 		public static final int MAINTAINUNIT_NULL = 50003;
 		/**
 		 *保养里程不能为空
 		 */
 		public static final int MAINTAINMILEAGE_NULL = 50004;
 		/**
 		 *保养金额不能为空
 		 */
 		public static final int MAINTAINMONEY_NULL = 50005;
 		/**
 		 *保养内容不能为空
 		 */
 		public static final int MAINTAINCONTENT_NULL = 50006;
    	
    }
    
    //规费记录ExceptionCode
    public static interface FeesExceptionCode{
    	/**
    	 *费用名称不能为空
    	 */
    	public static final int MONEYNAME_NULL = 60000;
    	/**
    	 *缴费金额不能为空
    	 */
    	public static final int PAYMENTMONEY_NULL = 60001;
    	/**
    	 *缴费日期不能为空
    	 */
    	public static final int PAYMENTDATE_NULL = 60002;
    	/**
    	 *结束日期不能为空
    	 */
    	public static final int PAYMENTENDDATE_NULL = 60003;
    	/**
    	 *收费单位不能为空
    	 */
    	public static final int CHARGEUNIT_NULL = 60004;
    	/**
    	 *缴费方式不能为空
    	 */
    	public static final int PAYMENTTYPE_NULL = 60005;
    	/**
    	 *周期月不能为空
    	 */
    	public static final int PERIOD_NULL = 60006;
    	
    }
}
