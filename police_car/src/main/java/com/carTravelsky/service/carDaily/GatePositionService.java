package com.carTravelsky.service.carDaily;



import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carTravelsky.bean.carDaily.GatePositionDO;
import com.carTravelsky.mapper.carDaily.GatePositionMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
@Component
public class GatePositionService {
	
	@Autowired
	private GatePositionMapper gatePositionMapper;

	
	/**
	 * @param searchStr
	 * @param gatepositiondo
	 * @return 全局搜索
	 */
	public List<GatePositionDO> getSearchGatePositionList(HashMap<Object, Object> hashMap, PageBounds bounds) {
		return gatePositionMapper.getSearchGatePositionList(hashMap,bounds);
	}

	
	/**
	 * @param gatepositiondo
	 * @param bounds
	 * @return 查询分页列表
	 */
	public List<GatePositionDO> getGatePositionList(GatePositionDO gatepositiondo, PageBounds bounds) {
		return gatePositionMapper.getList(gatepositiondo,bounds);
	}


	
	/**
	 * @param id
	 * @return 根据id查找单条停机信息
	 */
	public GatePositionDO getGatePositionByID(Integer id) {
		return (GatePositionDO) gatePositionMapper.getByID(id);
	}


	/**
	 * 保存添加或者修改过后的停机位信息
	 * @param gatepositiondo
	 */
	public void saveGatePosition(GatePositionDO gatepositiondo) {
		try {
			if (gatepositiondo.getId() != null) {
				gatePositionMapper.update(gatepositiondo);
			}else {
				// 添加状态删除码
				gatePositionMapper.insert(gatepositiondo);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	/**
	 * @param asList
	 * 状态删除，修改状态删除码
	 */
	public void updateDeleteCode(List<String> asList) {
		gatePositionMapper.updateDeleteCode(asList);
	}


	/**
	 * 修改可扫描半径
	 * @param scanRadiiNumber
	 * @param asList
	 */
	public void updateScanRadii(HashMap<Object,Object> hashMap) {
		gatePositionMapper.updateScanRadii(hashMap);
	}

	
}
