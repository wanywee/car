package com.carTravelsky.mapper.system;

import com.carTravelsky.bean.system.SystemLogDO;
import com.carTravelsky.mapper.BaseMapper;
public interface SystemLogMapper extends BaseMapper<SystemLogDO>  {
	public int deleteSystemOldLogs();

}