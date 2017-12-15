package com.carTravelsky.service.trafficManage;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarBaseDriverMapper;
import com.carTravelsky.mapper.carDaily.CarBaseStaffMapper;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordMapper;
import com.carTravelsky.utils.DateUtil;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;
@Component
public class CarDailyBackRecordService {
	
	  protected final Logger logger = LoggerFactory.getLogger(getClass());
	    @Autowired
	    private CarDailyOutRecordMapper carDailyOutRecordMapper;
	    @Autowired
		private CarBaseVehicleMapper carBaseVehicleMapper;
	    @Autowired
	   	private CarBaseStaffMapper carBaseStaffMapper;
	    @Autowired
	   	private CarBaseDriverMapper carBaseDriverMapper;
	    /**
	     * 
	     * @Title: getCarDailyOutRecordByID
	     * @Description: TODO根据车辆id 查询出车 最新记录
	     * @param id
	     * @return
	     * @return: CarDailyOutRecordDO
	     * @author: bxl  
	     * @date: 2017年12月5日 下午3:05:22
	     */
	    public CarDailyOutRecordDO getCarDailyOutRecordByID(Integer id) {
	        return carDailyOutRecordMapper.getByID(id);
	    }
	    /**
	     * 
	     * @Title: getSearchCarBackList
	     * @Description: TODO根据输入搜索数据
	     * @param map
	     * @param pageBounds
	     * @return
	     * @return: List<CarDailyOutRecordDO>
	     * @author: bxl  
	     * @date: 2017年12月5日 下午3:05:46
	     */
	    public List<CarDailyOutRecordDO> getSearchCarBackList(Map<String, Object> map, PageBounds pageBounds) {
	    	 //获取cartyp为出车的列表
	    	List<CarDailyOutRecordDO> list1 = new ArrayList<CarDailyOutRecordDO>();
	        List<CarDailyOutRecordDO> list =carDailyOutRecordMapper.getSearchCarBackListByType(map, pageBounds);
	        int time = carDailyOutRecordMapper.getHour();
	        java.util.Date now = new Date();
	    	
	    	for (CarDailyOutRecordDO carDailyOutRecordDO2 : list) {
	    		
	    		float outtime = (now.getTime() - carDailyOutRecordDO2.getOutcarTime().getTime())/3600000;
	    		long estime = carDailyOutRecordDO2.getEstimateReturnTime().getTime()+(time*3600000);
	    		//System.out.println(carDailyOutRecordDO2.getEstimateReturnTime()+"前");
	    		Date date = new Date(estime);
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    		Date dateStr = DateUtil.ymdhms2Date(sdf.format(date));
	    		carDailyOutRecordDO2.setEstimateReturnTime(dateStr);
	    		//System.out.println(carDailyOutRecordDO2.getEstimateReturnTime()+"后");
	    		carDailyOutRecordDO2.setOutTime(outtime);
	    		list1.add(carDailyOutRecordDO2);
			}
			return list1;
	    }
	    
	    /**
	     * 
	     * @Title: getCarDailyOutRecordList
	     * @Description: TODO获取出车信息列表
	     * @param carDailyOutRecordDO
	     * @return
	     * @return: List<CarDailyOutRecordDO>
	     * @author: bxl  
	     * @date: 2017年12月5日 下午3:06:30
	     */
	    public List<CarDailyOutRecordDO> getCarDailyOutRecordList(CarDailyOutRecordDO carDailyOutRecordDO) {
	        return carDailyOutRecordMapper.getList(carDailyOutRecordDO);
	    }
	    /**
	     * 
	     * @Title: getCarDailyOutRecordList
	     * @Description: TODO获取出车记录
	     * @param isOutAndBack
	     * @param carDailyOutRecordDO
	     * @param pageBounds
	     * @return
	     * @return: List<CarDailyOutRecordDO>
	     * @author: bxl  
	     * @date: 2017年12月5日 下午3:07:24
	     */
	    public List<CarDailyOutRecordDO> getCarDailyOutRecordList(CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds) {
	        //获取cartyp为出车的列表
	    	List<CarDailyOutRecordDO> list1 = new ArrayList<CarDailyOutRecordDO>();
	    	List<CarDailyOutRecordDO> list = carDailyOutRecordMapper.getBackListByType(carDailyOutRecordDO, pageBounds);
	    	
	    	 int time = carDailyOutRecordMapper.getHour();
		        java.util.Date now = new Date();
		    	
		    	for (CarDailyOutRecordDO carDailyOutRecordDO2 : list) {
		    		
		    		float outtime = (now.getTime() - carDailyOutRecordDO2.getOutcarTime().getTime())/3600000;
		    		long estime = carDailyOutRecordDO2.getEstimateReturnTime().getTime()+(time*3600000);
		    		//System.out.println(carDailyOutRecordDO2.getEstimateReturnTime()+"前");
		    		Date date = new Date(estime);
		    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    		Date dateStr = DateUtil.ymdhms2Date(sdf.format(date));
		    		carDailyOutRecordDO2.setEstimateReturnTime(dateStr);
		    		//System.out.println(carDailyOutRecordDO2.getEstimateReturnTime()+"后");
		    		carDailyOutRecordDO2.setOutTime(outtime);
		    		list1.add(carDailyOutRecordDO2);
				}
	    	
	     	return list1;
	     }
	    /**
	     * 
	     * @Title: saveCarDailyOutRecord
	     * @Description: TODO保存已回车记录
	     * @param carDailyOutRecordDO
	     * @param isOutAndBack
	     * @throws ServiceException
	     * @return: void
	     * @author: bxl  
	     * @date: 2017年12月5日 下午3:11:20
	     */
	    public void saveCarDailyOutRecord(CarDailyOutRecordDO carDailyOutRecordDO) throws ServiceException {
		try {
			// 回车记录新增 把车辆 状态改为回车
			CarBaseVehicleDO carBaseVehicleDO = new CarBaseVehicleDO();
			CarBaseDriverDO carBaseDriverDO = new CarBaseDriverDO();
			// 设置司机的职工ID,通过姓名查询id
			carBaseDriverDO.setWorkID(carDailyOutRecordDO.getStffID());
			//获取当前时间
			java.util.Date now = new Date();
			if (carDailyOutRecordDO.getBackcarMileage() != null && carDailyOutRecordDO.getTrip() != null
					&& !StringUtils.isBlank(carDailyOutRecordDO.getParkPosition())) {
				 //设置驾驶员状态1空闲2出车3休假
				carBaseDriverDO.setCurrStatus(1);
				carBaseDriverMapper.updateCarDriverInfoByWorkID(carBaseDriverDO);
				//设置车辆id
				carBaseVehicleDO.setId(carDailyOutRecordDO.getCarID());
				// 回车记录 新增 将 车状态改为可用
				carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_USEABLE);
				carBaseVehicleMapper.update(carBaseVehicleDO);
				// 新增回车记录将这条数据 改为可用
				carDailyOutRecordDO.setCarType(YSTConstants.CAR_TYPE_RETURN);
				//设置回车时间为当前时间
				carDailyOutRecordDO.setBackTime(now);
				carDailyOutRecordMapper.update(carDailyOutRecordDO);
			} else {
				// 如果没有回车数据就编辑为普通出车修改
				carDailyOutRecordMapper.update(carDailyOutRecordDO);
			}
		} catch (Exception e) {
			throw new ServiceException("回车记录=======编辑 出错" + e);
		}
	}
	    /**
	     * 
	     * @Title: saveCaraddTime
	     * @Description: TODO续时
	     * @param carDailyOutRecordDO
	     * @param day
	     * @return: void
	     * @author: bxl  
	     * @date: 2017年12月5日 下午5:05:39
	     */
	    public void saveCaraddTime(CarDailyOutRecordDO carDailyOutRecordDO,int day) {
	    	try {
	    		if(carDailyOutRecordDO.getEstimateReturnTime()!=null){
	    			Date startime = carDailyOutRecordDO.getEstimateReturnTime();				
				     Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				     Calendar c = Calendar.getInstance();
				     c.setTime(startime);
				     c.add(Calendar.DAY_OF_MONTH, day);// 今天+1天
				     Date tomorrow = c.getTime();
				     System.out.println("明天是:" + f.format(tomorrow));
				     
				     carDailyOutRecordDO.setEstimateReturnTime(DateUtil.ymdhms2Date(f.format(tomorrow)));
				     carDailyOutRecordMapper.update(carDailyOutRecordDO);
	    		}	    		 				
			} catch (Exception e) {
				throw new ServiceException("续时=======编辑 出错" + e);
			}
	    }
public static void main(String[] args) {
	
	/* Date time = DateUtil.ymdhms2Date("2017-11-24 09:10:10");
     Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     
    
     System.out.println("今天是:" + f.format(time));

     Calendar c = Calendar.getInstance();
     c.setTime(time);
     c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

     Date tomorrow = c.getTime();
     System.out.println("明天是:" + f.format(tomorrow));*/
	/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	try {
		java.util.Date now = new Date();
		//java.util.Date now = df.parse("2004-03-26 13:31:40");
		java.util.Date date=df.parse("2017-14-12 11:30:24");
		long l=now.getTime()-date.getTime();
		System.out.println(l);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	long time = 1247131311;
	Date date = new Date(time);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	String dateStr = sdf.format(date);
	System.out.println(dateStr);
	 	
}
}
