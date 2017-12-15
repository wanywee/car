package com.carTravelsky.utils;

import java.awt.Color;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;

public class MSExcelUtil {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	public boolean ExportExcel(List<CarBaseVehicleDO> list,HttpServletResponse response) { 
		boolean result=true;
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("车辆登记表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0); 
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle cellStyle = wb.createCellStyle();
        sheet.setColumnWidth(1, 6*512);
        sheet.setColumnWidth(4, 6*512);
        //样式
        short colorIndex = 10;
        HSSFPalette palette = wb.getCustomPalette();
        Color rgb = Color.GREEN;
        short bgIndex = colorIndex ++; 
        palette.setColorAtIndex(bgIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
        short bdIndex = colorIndex ++;
        rgb = Color.BLACK;
        palette.setColorAtIndex(bdIndex, (byte) rgb.getRed(), (byte) rgb.getGreen(), (byte) rgb.getBlue());
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //bdIndex 边框颜色下标值
        cellStyle.setBottomBorderColor(bdIndex);
        cellStyle.setLeftBorderColor(bdIndex);
        cellStyle.setRightBorderColor(bdIndex);
        cellStyle.setTopBorderColor(bdIndex);
         
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式 
        row=sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("车辆登记表");
        
        //合并单元格
        CellRangeAddress region = new CellRangeAddress(0, // first row
                0, // last row
                0, // first column
                9 // last column
        );
        sheet.addMergedRegion(region);
        cell.setCellStyle(cellStyle);
        row.setHeightInPoints(50);
        row = sheet.createRow(1);
        cell=row.createCell(0);  
        cell.setCellValue("部门");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(1);  
        cell.setCellValue("车牌号");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(2);  
        cell.setCellValue("车辆类型");  
        cell.setCellStyle(cellStyle);  
  
  /*      cell = row.createCell(3);  
        cell.setCellValue("车辆型号");  
        cell.setCellStyle(cellStyle);*/  
  
        cell = row.createCell(3);  
        cell.setCellValue("厂牌型号");  
        cell.setCellStyle(cellStyle);  
  
       /* cell = row.createCell(5);  
        cell.setCellValue("发动机号");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(6);  
        cell.setCellValue("车架号");  
        cell.setCellStyle(cellStyle);  
  */
        cell = row.createCell(4);  
        cell.setCellValue("购进日期");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(5);  
        cell.setCellValue("排量");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(6);  
        cell.setCellValue("厂家");  
        cell.setCellStyle(cellStyle);  
  
        cell = row.createCell(7);  
        cell.setCellValue("购置价格");  
        cell.setCellStyle(cellStyle);  
  
  /*      cell = row.createCell(11);  
        cell.setCellValue("保养周期");  
        cell.setCellStyle(cellStyle);*/
        
        cell = row.createCell(8);  
        cell.setCellValue("座位数");  
        cell.setCellStyle(cellStyle);
        
        cell = row.createCell(9);  
        cell.setCellValue("状态");  
        cell.setCellStyle(cellStyle);
        
        /*cell = row.createCell(14);  
        cell.setCellValue("备注");  
        cell.setCellStyle(cellStyle);*/
        
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        for (int i = 0; i < list.size(); i++) {  
            row = sheet.createRow((int) i + 2);
            row.setHeightInPoints(30);
            CarBaseVehicleDO CS = (CarBaseVehicleDO) list  .get(i);
            String nowStatusName="";
            if(CS.getNowStatus()==1){
            	nowStatusName="可用";
            }else if(CS.getNowStatus()==2){
            	nowStatusName="出车";
            }else if(CS.getNowStatus()==3){
            	nowStatusName="维修";
            }else if (CS.getNowStatus()==4) {
            	nowStatusName="其他";
			}
            // 第四步，创建单元格，并设置值  
            cell=row.createCell(0);
            cell.setCellValue(CS.getDeptName());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(1);
            cell.setCellValue(CS.getLicenseno());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(2);
            cell.setCellValue(CS.getTypeName());
            cell.setCellStyle(cellStyle);
           /* cell=row.createCell(3);
            cell.setCellValue(CS.getModelName());  
            cell.setCellStyle(cellStyle);*/
            cell=row.createCell(3);
            cell.setCellValue(CS.getBrandName());
            cell.setCellStyle(cellStyle);
            
            /*cell=row.createCell(5);
            cell.setCellValue(CS.getEngineno());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(6);
            cell.setCellValue(CS.getChassisno());
            cell.setCellStyle(cellStyle);*/
            
            cell=row.createCell(4);
            cell.setCellValue(DateUtil.ymd(CS.getBuyTime()));
            cell.setCellStyle(cellStyle);
            cell=row.createCell(5);
            cell.setCellValue(CS.getConsumption());
            cell.setCellStyle(cellStyle);
            
            cell=row.createCell(6);
            cell.setCellValue(CS.getSupplyName());
            cell.setCellStyle(cellStyle);
            cell=row.createCell(7);
            cell.setCellValue(CS.getBuyPrice());
            cell.setCellStyle(cellStyle);
            
            /*cell=row.createCell(11);
            cell.setCellValue(CS.getPeriod());
            cell.setCellStyle(cellStyle);*/
            cell=row.createCell(8);
            cell.setCellValue(CS.getSeats());
            cell.setCellStyle(cellStyle);
            
            cell=row.createCell(9);
            cell.setCellValue(CS.getDisplayStatus());
            cell.setCellStyle(cellStyle);
            /*cell=row.createCell(14);
            cell.setCellValue(CS.getComments());
            cell.setCellStyle(cellStyle);*/
            
        }  
        // 第六步，将文件存到指定位置  
        try { 
        	 // 如果文件名有中文，必须URL编码 
        	String fileName="车辆登记表";
            fileName = URLEncoder.encode(fileName, "UTF-8"); 
            response.reset();
        	response.addHeader("Content-Disposition", "attachment;filename="+fileName+".xls");  
        	response.setContentType("application/ms-excel;charset=GB2312");
            OutputStream os= new BufferedOutputStream(response.getOutputStream());  
        	wb.write(os);
			logger.info("导出成功");
			os.close();
        } catch (Exception e) {
        	logger.info("导出失败");
        	result=false;
            e.printStackTrace();  
        }
		return result;  
    }
}
