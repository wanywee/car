package com.carTravelsky.mapper.carDaily;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.carDaily.GatePositionDO;
import com.carTravelsky.mapper.BaseMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface GatePositionMapper extends BaseMapper<GatePositionDO>{


	List<GatePositionDO> getSearchGatePositionList(HashMap<Object, Object> hashMap, PageBounds bounds);

	void updateDeleteCode(List<String> asList);

	void updateScanRadii(HashMap<Object, Object> hashMap);


}
