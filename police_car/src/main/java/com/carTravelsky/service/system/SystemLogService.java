package com.carTravelsky.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.system.SystemLogDO;
import com.carTravelsky.mapper.system.SystemLogMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

@Component
public class SystemLogService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SystemLogMapper systemLogMapper;

    /**
     * 保存systemLogDO
     *
     */
    public void saveSystemLog(SystemLogDO systemLogDO) throws ServiceException {
        try{
            if(systemLogDO.getLogID() == null){
                systemLogMapper.insert(systemLogDO);
            }else {
                systemLogMapper.update(systemLogDO);
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /**
     * 根据logID获取对象
     *
     */
    public SystemLogDO getSystemLogByID(Integer logID) {
        return systemLogMapper.getByID(logID);
    }

    /**
     * 根据systemLogDO查询列表
     *
     */
    public List<SystemLogDO> getSystemLogList(SystemLogDO systemLogDO) {
        return systemLogMapper.getList(systemLogDO);
    }

    /**
     * 根据systemLogDO查询分页列表
     *
     */
    public List<SystemLogDO> getSystemLogList(SystemLogDO systemLogDO, PageBounds pageBounds) {
        return systemLogMapper.getList(systemLogDO, pageBounds);
    }

}