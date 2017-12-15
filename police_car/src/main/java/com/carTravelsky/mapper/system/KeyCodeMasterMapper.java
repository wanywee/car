package com.carTravelsky.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.stopec.common.keycode.KeyTypeMapper;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;

public interface KeyCodeMasterMapper extends KeyTypeMapper {

	public int insert(KeyCodeMasterDO keyCodeMasterDO);

	public int update(KeyCodeMasterDO keyCodeMasterDO);

	public int delete(Integer ID);

	public KeyCodeMasterDO getByID(Integer ID);

	public List<KeyCodeMasterDO> getList(KeyCodeMasterDO keyCodeMasterDO);

	public List<KeyCodeMasterDO> getList(KeyCodeMasterDO keyCodeMasterDO,
			PageBounds pageBounds);

	public List<KeyCodeMasterDO> getKeyCodeListsearchStr(String searchStr,
			PageBounds pageBounds);

	public List<KeyCodeMasterDO> getKeyCodeByKeyType(String keyType);

	public KeyCodeMasterDO getKeyCodeByDisplay(String display);
	public List<KeyCodeMasterDO> getsearchCodeMasterList(KeyCodeMasterDO keyCodeMasterDO, PageBounds pageBounds);

	
	// 修改行车记录单，是否显示窗体，到期日期提醒天数
	public void updateSysParam(KeyCodeMasterDO keyCodeMasterDO);
	
	public List<KeyCodeMasterDO> getSysPramList(@Param("list")List<String> asList,@Param("companyId")Integer companyId);
	
	// ---------------------------------
	
	public String searchAppWeight(@Param("appweight")String appweight);

	public List<KeyCodeMasterDO> getKeyCodeBystr(KeyCodeMasterDO keyCodeMasterDO);

	public List<KeyCodeMasterDO> getKeyCodeMasterListByKeyType(String kind);

	/**
	 * @Title: getKindCode
	 * @Description: 获取当前公司中系统参数下拉菜单
	 * @param kind
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: zy  
	 * @date: 2017-10-18 下午2:36:27
	 */
	public List<Select2VO> getKeyCodeForDropdown(KeyCodeMasterDO keyCodeMasterDO);
	// 获取燃油标号下拉菜单
	public List<Select2VO> getSelect2ListFueloil(@Param("str")String str);
	// 获取保险种类下拉菜单
	public List<Select2VO> getSelect2InsuranceType(String str);
	


}