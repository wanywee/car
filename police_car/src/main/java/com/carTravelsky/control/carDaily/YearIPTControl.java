package com.carTravelsky.control.carDaily;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyYearIptRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.service.carDaily.CarDailyYearIptRecordService;
import com.carTravelsky.service.system.KeyCodeMasterService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.carTravelsky.utils.app.DateTools;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
import com.stopec.common.validate.ValidateResult;
import com.stopec.common.validate.ValidationUtils;
import com.stopec.web.context.AttributeContext;
import com.stopec.web.context.SessionContext;

@RequestMapping("/yearIPTRecord")
@Controller
public class YearIPTControl {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarDailyYearIptRecordService carDailyYearIptRecordService;
	@Autowired
	private KeyCodeMasterService keyCodeMasterService;

	/**
	 * @Title: listYearIPTRecord
	 * @Description: 进入年检记录页面
	 * @return
	 * @return: 返回年检记录页面
	 * @author: wangyu
	 * @date: 2017年10月31日 上午10:59:32
	 */
	@RequestMapping("/listCarDailyYearIPTRecord")
	public String listYearIPTRecord(String remindDate,
			HttpServletRequest request) {
		try {
			request.setAttribute("remindDate", remindDate);
			return "baseInfo/listCarDailyYearIPTRecord";
		} catch (Exception e) {
			logger.error("进入进入年检记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: editYearIPTRecord
	 * @Description:编辑年检记录
	 * @return
	 * @return: String
	 * @author: wangyu
	 * @date: 2017年10月31日 下午2:31:16
	 */
	@RequestMapping(value = "/editYearIPTRecord")
	public String editYearIPTRecord(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editYearIPTRecord";
			}
			CarDailyYearIptRecordDO yearIptRecordDO = carDailyYearIptRecordService
					.getCarDailyYearIptRecordByID(ID);
			request.setAttribute("recode", yearIptRecordDO);
			return "baseInfo/editYearIPTRecord";
		} catch (UnsupportedEncodingException e) {
			logger.error("进入编辑年检记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: editYearIPTRecord
	 * @Description:新增年检记录
	 * @return
	 * @return: String
	 * @author: wangyu
	 * @date: 2017年10月31日 下午2:31:16
	 */
	@RequestMapping(value = "/insertYearIPTRecord")
	public String insertYearIPTRecord(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editYearIPTRecord";
			}
			return "baseInfo/editYearIPTRecord";
		} catch (UnsupportedEncodingException e) {
			logger.error("进入新增年检记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: saveCarDailyYearIPTRecord
	 * @Description: 存储年检记录
	 * @param request
	 * @param carDailyYearIptRecordDO
	 * @param response
	 * @param accidentTime
	 * @param session
	 * @return
	 * @throws ParseException
	 * @return: ReturnDataInfo<Object>
	 * @author: wangyu
	 * @date: 2017年10月31日 下午2:53:51
	 */
	@RequestMapping(value = "/saveCarDailyYearIPTRecord", method = RequestMethod.POST)
	@ResponseBody
	public ReturnDataInfo<Object> saveCarDailyYearIPTRecord(
			HttpServletRequest request,
			CarDailyYearIptRecordDO carDailyYearIptRecordDO,
			HttpServletResponse response, String yearIPTDate, String EndDate,
			HttpSession session) throws ParseException {
		try {
			request.setCharacterEncoding("utf-8");
			CarSysUserDO sysUserDO = (CarSysUserDO) session
					.getAttribute("carSysUserDOLogin");
			if (StringUtils.isBlank(carDailyYearIptRecordDO.getCreatePeople())) {
				// 初始创建时新增车辆事故创建人
				carDailyYearIptRecordDO
						.setCreatePeople(sysUserDO.getUsername());
			}
			// 修改更新人
			carDailyYearIptRecordDO.setUpdatePeople(sysUserDO.getUsername());
			carDailyYearIptRecordDO.setYearIptDate(DateTools.formatDate(
					yearIPTDate, DateTools.DATE_PATTERN_DEFAULT));
			carDailyYearIptRecordDO.setEndDate(DateTools.formatDate(EndDate,
					DateTools.DATE_PATTERN_DEFAULT));
			// 车管所ID与往来单位ID一致
			carDailyYearIptRecordDO.setUnitID(Integer
					.valueOf(carDailyYearIptRecordDO.getVehicleManage()));
			ValidationUtils.validate(carDailyYearIptRecordDO);
			ValidateResult result = carDailyYearIptRecordDO.getValidateResult();
			if (!result.getResult()) {
				logger.error("验证实体失败 {0}");
				return ReturnDataInfo.createFailedExecuteResult("验证实体失败");
			}
			carDailyYearIptRecordService
					.saveCarDailyYearIptRecord(carDailyYearIptRecordDO);
			return ReturnDataInfo.createSuccessfulSingleExecuteMessage("操作成功");

		} catch (UnsupportedEncodingException e) {
			logger.error("新增年检失败 {0}", e);
			return ReturnDataInfo.createFailedExecuteResult("操作失败");
		}
	}

	/**
	 * @Title: YearIPTRecordDetail
	 * @Description: 查看年检记录详情
	 * @param request
	 * @param ID
	 * @return
	 * @return: String
	 * @author: wangyu
	 * @date: 2017年10月16日 下午2:42:59
	 */
	@RequestMapping(value = "/YearIPTRecordDetail")
	public String CarAccidentDetail(HttpServletRequest request,
			@RequestParam(value = "id") Integer ID,
			@RequestParam(value = "show", required = false) String show) {
		try {
			request.setCharacterEncoding("utf-8");
			if (StringUtils.isBlank(ID + "")) {
				return "baseInfo/editYearIPTRecord";
			}
			CarDailyYearIptRecordDO yearIptRecordDO = carDailyYearIptRecordService
					.getCarDailyYearIptRecordByID(ID);
			if (!StringUtils.isBlank(show)) {
				/* 图片地址字符串转单个数组 */
				if (StringUtils.isNotBlank(yearIptRecordDO.getPhotoUrl())) {
				String photoUrlString = yearIptRecordDO.getPhotoUrl().trim();
					if (!photoUrlString.equals("")) {
						String[] photos = photoUrlString.split(",");
						List<String> photoUrlList = new ArrayList<String>();
						Collections.addAll(photoUrlList, photos);
						yearIptRecordDO.setPhotoUrlList(photoUrlList);
					}
				}

			}
			request.setAttribute("recode", yearIptRecordDO);
			return "baseInfo/detailYearIPTRecord";
		} catch (UnsupportedEncodingException e) {
			logger.error("进入编辑年检记录出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: deleteDailyYearIPT
	 * @Description: 删除年检记录
	 * @param request
	 * @param response
	 * @param ID
	 * @return
	 * @return: ReturnDataInfo<Object>
	 * @author: wangyu
	 * @date: 2017年10月31日 下午3:29:00
	 */
	@RequestMapping(value = "/deleteDailyYearIPT")
	@ResponseBody
	public ReturnDataInfo<Object> deleteDailyYearIPT(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") String ID) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			if (!StringUtils.isBlank(ID)) {
				String[] parms = ID.split(",");
				int i = 0;
				for (String c : parms) {
					i = carDailyYearIptRecordService.deleteIncomplete(Integer
							.valueOf(c));
				}
				if (i == 1) {
					return ReturnDataInfo
							.createSuccessfulSingleExecuteMessage("删除成功");
				}
			} else {
				return ReturnDataInfo.createFailedExecuteResult("删除失败");
			}
		} catch (Exception e) {
			logger.error("进入删除年检记录出错{0}", e);
		}
		return null;
	}

	/**
	 * @Title: getListCarDailyYearIPTRecord
	 * @Description: 得到年检记录的列表
	 * @param request
	 * @param carDailyYearIptRecordDO
	 * @param pageBounds
	 * @param searchStr
	 * @return
	 * @return: Map<String,Object>
	 * @author: wangyu
	 * @date: 2017年10月31日 下午3:59:25
	 */
	@ResponseBody
	@RequestMapping(value = "getListCarDailyYearIPTRecord", method = RequestMethod.POST)
	public Map<String, Object> getListCarDailyYearIPTRecord(
			HttpServletRequest request,
			CarDailyYearIptRecordDO carDailyYearIptRecordDO,
			PageBounds pageBounds, String searchStr, String count) {

		try {
			// 当前登录用户
			CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
					.get(request, "carSysUserDOLogin");
			if (YSTConstants.IS_CURRENT_DEP_VIEW == currentUser.getIsAllView()) {
				pageBounds.setNeedCompany(true);
				pageBounds.setCompanyID(currentUser.getDeptID());
			}
			KeyCodeMasterDO kcDo = keyCodeMasterService
					.getKeyCodeMasterByID(19);
			count = kcDo.getCode();
			if (!StringUtils.isBlank(searchStr)) {
				if (searchStr.contains("/")) {
					// 当出现对日期的查询时，支持yyyyy-MM-dd格式查询 ，不支持yyyyy/MM/dd格式
					searchStr = searchStr.trim().replaceAll("/", "-");
				}
				List<CarDailyYearIptRecordDO> carDailyYearIptRecordList = carDailyYearIptRecordService
						.getsearchCarDailyYearIPTRecord(searchStr, pageBounds);
				if (!StringUtils.isBlank(count)) {// 过期提醒的天数
					List<CarDailyYearIptRecordDO> carDailyYearIptRecordExprireList = carDailyYearIptRecordService
							.expireRecordList(Integer.valueOf(count),
									pageBounds);
					for (CarDailyYearIptRecordDO carDailyYearIptRecordDO2 : carDailyYearIptRecordList) {

						for (CarDailyYearIptRecordDO carDailyYearIptRecordDO21 : carDailyYearIptRecordExprireList) {
							if (carDailyYearIptRecordDO2.getId().equals(
									carDailyYearIptRecordDO21.getId())) {
								carDailyYearIptRecordDO2.setStatus(1);// 将要过期记录的状态码设置为1
							}
						}
					}
				}
				pageBounds.setTotalNumber(carDailyYearIptRecordList.size());
				return pageBounds.putResultObj(carDailyYearIptRecordList);
			}
			List<CarDailyYearIptRecordDO> carDailyYearIptRecordList = carDailyYearIptRecordService
					.getCarDailyYearIptRecordList(carDailyYearIptRecordDO,
							pageBounds);

			if (!StringUtils.isBlank(count)) {// 过期提醒的天数
				List<CarDailyYearIptRecordDO> carDailyYearIptRecordExprireList = carDailyYearIptRecordService
						.expireRecordList(Integer.valueOf(count), pageBounds);
				for (CarDailyYearIptRecordDO carDailyYearIptRecordDO2 : carDailyYearIptRecordList) {

					for (CarDailyYearIptRecordDO carDailyYearIptRecordDO21 : carDailyYearIptRecordExprireList) {
						if (carDailyYearIptRecordDO2.getId().equals(
								carDailyYearIptRecordDO21.getId())) {
							carDailyYearIptRecordDO2.setStatus(1);// 将要过期记录的状态码设置为1
						}
					}
				}
			}
			pageBounds.setTotalNumber(carDailyYearIptRecordList.size());
			return pageBounds.putResultObj(carDailyYearIptRecordList);
		} catch (Exception e) {
			logger.error("得到年检记录的列表出错{0}", e);
			return null;
		}
	}

	/**
	 * @Title: expireRecordList
	 * @Description: 得到到期提醒的年检记录列表
	 * @param remindDate
	 * @param pageBounds
	 * @return
	 * @return: Map<String,Object>
	 * @author: wangyu
	 * @date: 2017年11月2日 上午11:23:45
	 */
	@RequestMapping(value = "expireRecordList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> expireRecordList(
			@RequestParam(value = "remindDate") Integer remindDate,
			PageBounds pageBounds) {
		try {
			List<CarDailyYearIptRecordDO> carDailyYearIptRecordList = carDailyYearIptRecordService
					.expireRecordList(remindDate, pageBounds);
			return pageBounds.putResultObj(carDailyYearIptRecordList);
		} catch (Exception e) {
			logger.error("得到到期提醒的年检记录列表出错{0}", e);
			return null;
		}

	}

	/**
	 * 
	 * @Title: fileDelete
	 * @Description: 年检记录图片删除
	 * @param imgPath
	 * @return
	 * @return: ReturnDataInfo<String>
	 * @throws Exception 
	 */
	@RequestMapping("/fileDelete")
	@ResponseBody
	public ReturnDataInfo<String> fileDelete(HttpServletRequest request,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) throws Exception {
		imgPath =new String(imgPath.getBytes("iso8859-1"),"utf-8");
		// 根据车辆档案id得到车辆档案信息 删除url中的
		CarDailyYearIptRecordDO carDailyYearIptRecordDO = carDailyYearIptRecordService
				.getCarDailyYearIptRecordByID(Integer.parseInt(id));
		String imgString = carDailyYearIptRecordDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		// 设置新地址
		carDailyYearIptRecordDO.setPhotoUrl(newUrl);
		if(StringUtils.isBlank(newUrl)){
			carDailyYearIptRecordDO.setPhotoUrl(" ");
		}
		 if(newUrl.endsWith(",")){
			 newUrl=newUrl.substring(0,newUrl.length()-1);
	     }
	     
		// 更新年检记录中的photoUrl
		carDailyYearIptRecordService
				.saveCarDailyYearIptRecordDo(carDailyYearIptRecordDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		// FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}
}
