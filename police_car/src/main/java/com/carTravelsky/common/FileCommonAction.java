package com.carTravelsky.common;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyRepairRecordDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.service.carDaily.CarBaseVehicleService;
import com.carTravelsky.service.carDaily.CarDailyRepairRecordService;
import com.carTravelsky.utils.ReturnDataInfo;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.utils.Coder.Base64;
import com.stopec.common.utils.DateUtils;
import com.stopec.web.context.AttributeContext;
import com.stopec.web.context.SessionContext;

/**
 * @author Administrator
 * @version 1.0
 */
/*
 * 文件名: com.sprite.plugin.weixin.action.FileUploadAction.java
 * 
 * 修改记录 +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 修改日期 修改者 备注信息
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 2013-11-11 Administrator 初始化版本
 */
@Controller
@RequestMapping("/common")
public class FileCommonAction {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CarBaseVehicleService carBaseVehicleService;
	@Autowired
	private CarDailyRepairRecordService carDailyRepairRecordService;

	/**
	 * 下载文件
	 */
	@RequestMapping("/download")
	public void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String url, String del) {
		try {
			logger.info(url);
			url = Base64.decode(url);
			logger.info(url);
			response.setHeader("Content-type", "textml;charset=UTF-8");
			File file = new File(request.getServletContext().getRealPath("/")
					+ url);
			logger.error(file.exists() + "");
			if (file.exists()) {
				response.setHeader("Content-Disposition",
						"attachment; filename="
								+ new String(file.getName().getBytes("gb2312"),
										"iso8859-1"));
				response.setContentType("binary/octet-stream");
				response.setContentLength((int) file.length());
				FileUtils.copyFile(file, response.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("下载文件失败:{0}", e.toString());
		}
	}

	/**
	 * 下载highchart图片
	 */
	@RequestMapping("/downloadHighcharts")
	public void downloadHighcharts(HttpServletRequest request,
			HttpServletResponse response, String type, String svg) {
		try {
			request.setCharacterEncoding("utf-8");// 注意编码
			ServletOutputStream out = response.getOutputStream();
			if (null != type && null != svg) {
				svg = svg.replaceAll(":rect", "rect");
				String ext = "";
				Transcoder t = null;
				if (type.equals("image/png")) {
					ext = "png";
					t = new PNGTranscoder();
				} else if (type.equals("image/jpeg")) {
					ext = "jpg";
					t = new JPEGTranscoder();
				}
				response.addHeader("Content-Disposition",
						"attachment; filename=chart." + ext);
				response.addHeader("Content-Type", type);

				if (null != t) {
					TranscoderInput input = new TranscoderInput(
							new StringReader(svg));
					TranscoderOutput output = new TranscoderOutput(out);
					t.transcode(input, output);
				} else if (ext == "svg") {
					out.print(svg);
				} else {
					out.print("Invalid type: " + type);
				}
			} else {
				response.addHeader("Content-Type", "text/html");
				out.println("不支持的类型下载");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("下载highcharts报表图片出错，出错原因：{0}", e);
		}
	}

	/**
	 * 方法一上传单文件文件 可以选择不用
	 */
	@RequestMapping("/fileUpload")
	@ResponseBody
	public ReturnDataInfo<Map<String, Object>> oneFileUpload(
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		// 获得原始文件名
		String fileName = file.getOriginalFilename();
		logger.info("原始文件名:" + fileName);
		// 新文件名
		String newFileName = DateUtils.TimeStamp.systemTimestamp() + fileName;
		logger.info("新文件名:" + fileName);
		// 上传位置
		String savePath = AttributeContext.getAppPath() + "\\"
				+ YSTConstants.CAR_IMAGE + "\\" + newFileName;
		File f = new File(savePath);
		if (!f.exists())
			f.mkdirs();
		if (!file.isEmpty()) {
			try {
				file.transferTo(f);
				map.put("msg", savePath);
				map.put("flag", true);
			} catch (Exception e) {
				logger.error("上传文件失败{0}", e);
				map.put("msg", "上传文件出错");
				return ReturnDataInfo.createSuccessfulSingleExecuteResult(map);
			}
		}
		return ReturnDataInfo.createSuccessfulSingleExecuteResult(map);
	}

	/**
	 * 方法一上传多文件
	 */
	@RequestMapping("/multipleFileUpload")
	@ResponseBody
	public ReturnDataInfo<Map<String, Object>> multipleFileUpload(
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", false);
		CommonsMultipartResolver cmr = new CommonsMultipartResolver(
				request.getServletContext());
		if (cmr.isMultipart(request)) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
			Iterator<String> files = mRequest.getFileNames();
			while (files.hasNext()) {
				MultipartFile mFile = mRequest.getFile(files.next());
				if (mFile != null) {
					String fileName = mFile.getOriginalFilename();
					String newFileName = DateUtils.TimeStamp.systemTimestamp()
							+ fileName;
					String savePath = AttributeContext.getAppPath() + "/"
							+ YSTConstants.CAR_IMAGE + "/" + newFileName;
					File localFile = new File(savePath);
					try {
						mFile.transferTo(localFile);
						map.put("msg", YSTConstants.CAR_IMAGE + "/"
								+ newFileName);
						map.put("flag", true);
					} catch (Exception e) {
						logger.error("上传文件失败{0}", e);
						map.put("msg", "上传文件出错");
						return ReturnDataInfo
								.createSuccessfulSingleExecuteResult(map);
					}
				}
			}
		}
		return ReturnDataInfo.createSuccessfulSingleExecuteResult(map);
	}

	/**
	 * 
	 * @Title: fileDelete
	 * @Description: 车辆图片删除
	 * @param imgPath
	 * @return
	 * @return: ReturnDataInfo<String>
	 */
	@RequestMapping("/fileDelete")
	@ResponseBody
	public ReturnDataInfo<String> fileDelete(HttpServletRequest request,@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) {
		// 根据车辆档案id得到车辆档案信息 删除url中的
		CarBaseVehicleDO carBaseVehicleDO = carBaseVehicleService.getCarBaseVehicleByID(Integer.parseInt(id));
		String imgString = carBaseVehicleDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		// 设置新地址
		carBaseVehicleDO.setPhotoUrl(newUrl);
		// 更新车辆档案中的photoUrl
		carBaseVehicleService.saveCarBaseVehicle(carBaseVehicleDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		//FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}
	
	
	@RequestMapping("/repairRecordfileDelete")
	@ResponseBody
	public ReturnDataInfo<String> repairRecordfileDelete(HttpServletRequest request,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "imgPath") String imgPath) {
		// 当前登录用户
					CarSysUserDO currentUser = (CarSysUserDO) SessionContext.Attribute
							.get(request, "carSysUserDOLogin");
		// 根据车辆档案id得到车辆档案信息 删除url中的
		CarDailyRepairRecordDO carDailyRepairRecordDO = carDailyRepairRecordService.getCarDailyRepairRecordByID(Integer.parseInt(id));
		String imgString = carDailyRepairRecordDO.getPhotoUrl();
		String[] stringArr = imgString.split(",");
		String newUrl = "";
		for (String string : stringArr) {
			if (!string.equals(imgPath)) {
				newUrl += string + ",";
			}
		}
		// 设置新地址
		carDailyRepairRecordDO.setPhotoUrl(newUrl);
		// 更新车辆档案中的photoUrl
		carDailyRepairRecordService.saveCarDailyRepairRecord(currentUser,carDailyRepairRecordDO);
		String uri = AttributeContext.getAppPath() + '/' + imgPath;
		File file = new File(uri);
		//FileUtils.deleteQuietly(file);
		return ReturnDataInfo.createSuccessfulSingleExecuteMessage("删除成功！");
	}
}
