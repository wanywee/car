package com.carTravelsky.service.carDaily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.CarBaseDriverDO;
import com.carTravelsky.bean.carDaily.CarBaseStaffDO;
import com.carTravelsky.bean.carDaily.CarBaseVehicleDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordDO;
import com.carTravelsky.bean.carDaily.CarDailyOutRecordHisDO;
import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.carDaily.CarBaseDriverMapper;
import com.carTravelsky.mapper.carDaily.CarBaseStaffMapper;
import com.carTravelsky.mapper.carDaily.CarBaseVehicleMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordHisMapper;
import com.carTravelsky.mapper.carDaily.CarDailyOutRecordMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.utils.StringUtils;


@Component
public class CarDailyOutRecordService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CarDailyOutRecordMapper carDailyOutRecordMapper;
    @Autowired
	private CarBaseVehicleMapper carBaseVehicleMapper;
    @Autowired
   	private CarBaseStaffMapper carBaseStaffMapper;
    @Autowired
   	private CarBaseDriverMapper carBaseDriverMapper;
    @Autowired 
    private CarDailyOutRecordHisMapper  carDailyOutRecordHisMapper;
    /**
     * ����carDailyOutRecordDO
     *
     */
    public void saveCarDailyOutRecord(CarDailyOutRecordDO carDailyOutRecordDO,String isOutAndBack) throws ServiceException {
        try{
        	//出车记录新增   把车辆 状态改为出车
        	CarBaseVehicleDO carBaseVehicleDO =new CarBaseVehicleDO();
        	CarBaseDriverDO carBaseDriverDO = new CarBaseDriverDO();
        	//设置司机的职工ID,通过姓名查询id
       	    carBaseDriverDO.setWorkID(carDailyOutRecordDO.getStffID());
            if(carDailyOutRecordDO.getId() == null){
            	 
            	//车id
            	 carBaseVehicleDO.setId(carDailyOutRecordDO.getCarID());
            	 //不需要审批直接设置为已出车
            	 carDailyOutRecordDO.setCarType(3);
            	if(StringUtils.isBlank(isOutAndBack)){
            		 
            		carDailyOutRecordMapper.insert(carDailyOutRecordDO);//新增出车
            		//设置驾驶员状态1空闲2出车3休假
                    carBaseDriverDO.setCurrStatus(1);
                    carBaseDriverMapper.updateCarDriverInfoByWorkID(carBaseDriverDO);
                	carBaseVehicleDO.setId(carDailyOutRecordDO.getCarID());
                	 //回车记录 新增  将 车状态改为可用
                    carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_USEABLE);
                    carBaseVehicleMapper.update(carBaseVehicleDO);
                	//新增回车记录将这条数据 改为可用
            		carDailyOutRecordDO.setCarType(YSTConstants.CAR_TYPE_RETURN);
            		 carDailyOutRecordMapper.update(carDailyOutRecordDO);
                    //状态 1可用2出车3维修4其他   
            		//出车记录新增将车状态改为 出车
                   /* carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_DISABLE);
                    //设置驾驶员状态1空闲2出车3休假
                    carBaseDriverDO.setCurrStatus(2);
                    carBaseDriverMapper.updateCarDriverInfoByWorkID(carBaseDriverDO);
                    carBaseVehicleMapper.update(carBaseVehicleDO);*/
            	}else{
            		
            	}
                //修改车辆状态
                //carBaseVehicleMapper.update(carBaseVehicleDO);
            	//如果回车数据不为空则编辑为回车
            }else if(carDailyOutRecordDO.getBackcarMileage()!=null
            		&&carDailyOutRecordDO.getBackTime()!=null
            		&&!StringUtils.isBlank(carDailyOutRecordDO.getParkPosition())
            		){
            	 //设置驾驶员状态1空闲2出车3休假
                carBaseDriverDO.setCurrStatus(1);
                carBaseDriverMapper.updateCarDriverInfoByWorkID(carBaseDriverDO);
            	carBaseVehicleDO.setId(carDailyOutRecordDO.getCarID());
            	 //回车记录 新增  将 车状态改为可用
                carBaseVehicleDO.setNowStatus(YSTConstants.CAR_TYPE_USEABLE);
                carBaseVehicleMapper.update(carBaseVehicleDO);
            	//新增回车记录将这条数据 改为可用
        		carDailyOutRecordDO.setCarType(YSTConstants.CAR_TYPE_RETURN);
                carDailyOutRecordMapper.update(carDailyOutRecordDO);
            }else{
            	//如果没有回车数据就编辑为普通出车修改
            	 carDailyOutRecordMapper.update(carDailyOutRecordDO);
            }
        } catch (Exception e) {
            throw new ServiceException("出车和回车记录=======新增编辑 出错"+e);
        }
    }
    /**
     * 
     * @Title: saveOutRecord
     * @Description: TODO新增出车记录
     * @return: void
     * @author: admin  
     * @date: 2017年12月5日 上午11:11:12
     */
    public void saveOutRecord(CarDailyOutRecordDO carDailyOutRecordDO){
    	/**将区域的id转换成文字，直接保存到数据库，1为省内，2为省外*/
    	if(carDailyOutRecordDO.getDestArea().equals("1")){
    		carDailyOutRecordDO.setDestArea("省内");
    	}else{
    		carDailyOutRecordDO.setDestArea("省外");
    	}
    	
    	carDailyOutRecordMapper.changeNowStatus(carDailyOutRecordDO.getCarID());
    	carDailyOutRecordMapper.saveOutRecord(carDailyOutRecordDO);
    }
    /**
     * ���id��ȡ����
     *
     */
    public CarDailyOutRecordDO getCarDailyOutRecordByID(Integer id) {
        return carDailyOutRecordMapper.getByID(id);
    }
    /**
     * 
     * @Title: getOutByCarID
     * @Description: 根据车辆id 查询出车 最新记录
     * @param id
     * @return
     * @return: CarDailyOutRecordDO
     * @author: fuxinrong
     * @date: 2017年11月2日 下午2:13:32
     */
     public CarDailyOutRecordDO getOutByCarID(Integer id) {
         return carDailyOutRecordMapper.getOutByCarID(id);
     }
     /**
      * 
      * @Title: getOutByCarID
      * @Description: 根据车辆id 查询回车最新记录
      * @param id
      * @return
      * @return: CarDailyOutRecordDO
      * @author: fuxinrong
      * @date: 2017年11月2日 下午2:13:32
      */
      public CarDailyOutRecordDO getBackByCarID(Integer id) {
          return carDailyOutRecordMapper.getBackByCarID(id);
      }
    /**
     * ���carDailyOutRecordDO��ѯ�б�
     *
     */
    public List<CarDailyOutRecordDO> getCarDailyOutRecordList(CarDailyOutRecordDO carDailyOutRecordDO) {
        return carDailyOutRecordMapper.getList(carDailyOutRecordDO);
    }

    /**
     * ���carDailyOutRecordDO��ѯ��ҳ�б�
     *
     */
    public List<CarDailyOutRecordDO> getCarDailyOutRecordList(CarDailyOutRecordDO carDailyOutRecordDO, PageBounds pageBounds) {
      //回车记录
    	return carDailyOutRecordMapper.getBackList(carDailyOutRecordDO, pageBounds);
    }
    /**
     * 
     * @Title: getsearchCarOutList
     * @Description: 出车记录 全局搜索
     * @param carDailyOutRecordDO
     * @param pageBounds
     * @param searchStr
     * @return
     * @return: List<CarDailyOutRecordDO>
     * @author: fuxinrong
     * @date: 2017年11月1日 下午1:34:37
     */
    public List<CarDailyOutRecordDO> getSearchCarOutList(Map<String, Object> map, PageBounds pageBounds) {
        return carDailyOutRecordMapper.getSearchCarOutList(map, pageBounds);
    }
    
    /**
     * 
     * @Title: getsearchCarBackList
     * @Description: 回车记录全局搜索
     * @param carDailyOutRecordDO
     * @param pageBounds
     * @param searchStr
     * @return
     * @return: List<CarDailyOutRecordDO>
     * @author: fuxinrong
     * @date: 2017年11月1日 下午1:34:52
     */
    public List<CarDailyOutRecordDO> getSearchCarBackList(Map<String, Object> map, PageBounds pageBounds) {
        return carDailyOutRecordMapper.getSearchCarBackList(map, pageBounds);
    }
    /**
     * 
     * @Title: deleteCarOutById
     * @Description: 删除出车记录
     * @param splitIds
     * @param loginId
     * @return: void
     * @author: fuxinrong
     * @date: 2017年11月1日 下午5:39:37
     */
    public List<String> deleteCarOutById(String[] splitIds,List<String> NoDeleteCar, Integer loginId) {
    	try {
			//出车删除 需要判断车辆状态 若为出车 不能删除
			CarDailyOutRecordDO carOutDO =new CarDailyOutRecordDO();
			for(String id :splitIds){
				//根据id查询  
				carOutDO=carDailyOutRecordMapper.getByID(Integer.parseInt(id));
				if(carOutDO!=null){
				//判断车辆 状态 1可用2出车3维修4其他
				if(carOutDO.getCarType()==YSTConstants.CAR_TYPE_DISABLE){
					//不能删除的车牌号
					NoDeleteCar.add(carOutDO.getLicenseno());
				}else{
					carOutDO.setUpdatePeople(loginId+"");
					carOutDO.setUpdateTime(new Date());
					carOutDO.setDeleteCode(YSTConstants.DELETE_CODE_DEL);
					carDailyOutRecordMapper.update(carOutDO);
				}
			  }
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServiceException("删除多个出车记录id出错",e);
		}
    	return NoDeleteCar;
    }
    /**
     * 
     * @Title: deleteCarOutAndBacksById
     * @Description: TODO
     * @param splitIds
     * @param loginId
     * @return: void
     * @author: fuxinrong
     * @date: 2017年10月30日 下午3:21:30
     */
    public void deleteCarBacksById(String[] splitIds, Integer loginId) {
		try {
			//回车记录删除
			CarDailyOutRecordDO carBackDO= new CarDailyOutRecordDO();
			for (String id : splitIds) {
				carBackDO.setId(Integer.parseInt(id));
				carBackDO.setUpdateTime(new Date());
				carBackDO.setUpdatePeople(loginId+"");
				carBackDO.setDeleteCode(YSTConstants.DELETE_CODE_DEL);
				carDailyOutRecordMapper.update(carBackDO);
			}
		} catch (Exception e) {
			throw new ServiceException("删除多个id出错",e);
		}
	}
    
    // 获取申请中的出车记录
	public CarDailyOutRecordDO getApplyCarDailyOutRecordByID(Integer valueOf) {
		// TODO Auto-generated method stub
		return carDailyOutRecordMapper.getApplyByID(valueOf);
	}
	// 批准并保存出车记录
	public void updateOutCar(CarDailyOutRecordDO carOutRecordDO) {
		if(carOutRecordDO.getId() == null) {
			carDailyOutRecordMapper.saveOutRecord(carOutRecordDO);
		} else {
			carDailyOutRecordMapper.update(carOutRecordDO);
		}
	}
	/**
	 * @Title: applyCar
	 * @Description: TODO  用车申请
	 * @param currentUser
	 * @param carDailyOutRecordDO
	 * @return: void
	 * @author: zy  
	 * @date: 2017-12-12 上午11:10:45
	 */
	public void applyCar(CarSysUserDO currentUser,CarDailyOutRecordDO carDailyOutRecordDO) {
		            //获取车辆信息
		           	CarBaseVehicleDO veh=carBaseVehicleMapper.getByID(carDailyOutRecordDO.getCarID());
		            
					// 得到用户部门id （如果是本部门的车 不进行申请，直接保存到记录中  否则需要进行申请调度）
					if(carDailyOutRecordDO.getDeptID().equals(currentUser.getDeptID())) { // id相同
						//本部门车辆
						carDailyOutRecordDO.setCaruser(String.valueOf(currentUser.getStaffID()));
						if("1".equalsIgnoreCase(carDailyOutRecordDO.getDestArea())){//1为省内，2为省外
							carDailyOutRecordDO.setCarType(YSTConstants.PROCESS_STATUS_HAVE_SEND_CAR);
						}else{
							carDailyOutRecordDO.setCarType(YSTConstants.PROCESS_STATUS_WAITING_LEADER_APPROVE);
						}
						
						carDailyOutRecordDO.setCreateTime(new Date());
						CarBaseStaffDO carBaseStaffDo = carBaseStaffMapper.getByID(new Integer (carDailyOutRecordDO.getCaruser()).intValue());
						carDailyOutRecordDO.setPhonenumber(carBaseStaffDo.getStaffPhone());
						//carDailyOutRecordDO.setCarType(2);
						carDailyOutRecordDO.setCreatePeople(String.valueOf(currentUser.getId()));
						if(carDailyOutRecordDO.getDestArea().equals("1")){
				    		carDailyOutRecordDO.setDestArea("省内");
				    	}else{
				    		carDailyOutRecordDO.setDestArea("省外");
				    	}
						carDailyOutRecordDO.setCaruser(String.valueOf(currentUser.getId()));
						carDailyOutRecordDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
						carDailyOutRecordDO.setOutcarMileage(veh.getCurrentMileage());
						Integer cdorID=carDailyOutRecordMapper.saveOutRecord(carDailyOutRecordDO);
						//更新历史状态
						CarDailyOutRecordHisDO  his=new CarDailyOutRecordHisDO();
						his.setCdorID(cdorID);
						his.setProcessStatus(carDailyOutRecordDO.getCarType());
						his.setOperator(currentUser.getId());
						his.setOperateTime(new Date());
						carDailyOutRecordHisMapper.insert(his);
						
						//更新车辆状态 为出车
						CarBaseVehicleDO  paramVeh=new CarBaseVehicleDO();
						paramVeh.setId(carDailyOutRecordDO.getCarID());
						paramVeh.setNowStatus(YSTConstants.CAR_STATUS_BE_USE);
						carBaseVehicleMapper.update(paramVeh);
						
					}else{
						//调度中心车辆 //省内和省外都需要调度中心审核
						carDailyOutRecordDO.setCaruser(String.valueOf(currentUser.getStaffID()));
						carDailyOutRecordDO.setDeleteCode(YSTConstants.PROCESS_STATUS_WAITING_DISPATCHING_CENTER);
						carDailyOutRecordDO.setCreateTime(new Date());
						CarBaseStaffDO carBaseStaffDo = carBaseStaffMapper.getByID(new Integer (carDailyOutRecordDO.getCaruser()).intValue());
						carDailyOutRecordDO.setPhonenumber(carBaseStaffDo.getStaffPhone());
						//carDailyOutRecordDO.setCarType(2);
						carDailyOutRecordDO.setCreatePeople(String.valueOf(currentUser.getId()));
						if(carDailyOutRecordDO.getDestArea().equals("1")){
				    		carDailyOutRecordDO.setDestArea("省内");
				    	}else{
				    		carDailyOutRecordDO.setDestArea("省外");
				    	}
						//出车记录
						carDailyOutRecordDO.setCaruser(String.valueOf(currentUser.getId()));
						carDailyOutRecordDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
						carDailyOutRecordDO.setOutcarMileage(veh.getCurrentMileage());
						Integer cdorID=carDailyOutRecordMapper.saveOutRecord(carDailyOutRecordDO);
						//历史记录
						CarDailyOutRecordHisDO  his=new CarDailyOutRecordHisDO();
						his.setCdorID(cdorID);
						his.setProcessStatus(carDailyOutRecordDO.getCarType());
						his.setOperator(currentUser.getId());
						his.setOperateTime(new Date());
						carDailyOutRecordHisMapper.insert(his);
						
						//更新车辆状态
						veh.setId(carDailyOutRecordDO.getCarID());
						veh.setNowStatus(YSTConstants.CAR_STATUS_APPLYING);
						carBaseVehicleMapper.update(veh);
					}
		
	}
	public String StffNameByID(String ids){
		//拆分id
		String[] splitIds = ids.split(",");
		String entourageUserName = "";
		//查询该随行人员 根据id 
		List<String> userName = new ArrayList<String>();
		for(String id :splitIds){
			
			CarBaseStaffDO carBaseStaffDO = carBaseStaffMapper.getByID(Integer.parseInt(id));
			
			userName.add(carBaseStaffDO.getStaffName());
		}
		if(userName.size()>0){
			for(String name :userName){
				entourageUserName += name +",";
			}
		}
		return entourageUserName;
	}
}