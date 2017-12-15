package com.carTravelsky.service.system;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.carTravelsky.bean.system.CarSysUserDO;
import com.carTravelsky.bean.system.CustomSysPram;
import com.carTravelsky.bean.system.KeyCodeMasterDO;
import com.carTravelsky.bean.system.Select2VO;
import com.carTravelsky.common.YSTConstants;
import com.carTravelsky.mapper.system.KeyCodeMasterMapper;
import com.stopec.common.exception.ServiceException;
import com.stopec.common.keycode.CodeMaster;
import com.stopec.common.keycode.KeyTypeMapper;
import com.stopec.common.logger.Logger;
import com.stopec.common.logger.LoggerFactory;
import com.stopec.common.orm.mybatis.plugin.paging.PageBounds;
import com.stopec.common.orm.mybatis.plugin.paging.PowerBounds;
import com.stopec.web.context.AttributeContext;

@Component
public class KeyCodeMasterService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private KeyCodeMasterMapper keyCodeMasterMapper;
	@Autowired
	private DataSourceTransactionManager txManager;
	
	@Autowired
	private  KeyTypeMapper  keyTypeMapper;

	/**
	 * 
	 * @Title: getSelect2ListKeyCode
	 * @Description: 数据字典相关下拉框的列表
	 * @param currentUser
	 * @param kind 数据类型
	 * @param searchStr 搜索字段
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlong  
	 * @date: 2017年11月2日 下午2:23:22
	 */
	public List<Select2VO> getSelect2ListKeyCode(CarSysUserDO currentUser,
			String kind, String searchStr) {
		List<Select2VO> select2vos = new ArrayList<>();
		List<KeyCodeMasterDO> KeyCodeMasterDOs;
		// 封装查询对象
		KeyCodeMasterDO KeyCodeMasterDO = new KeyCodeMasterDO();
		KeyCodeMasterDO.setCompanyId(currentUser.getCompanyId());
		KeyCodeMasterDO.setKeyType(kind);
		if (StringUtils.isNotBlank(searchStr)) {
		} else {
			// 根据公司id和keyType查询列表
			KeyCodeMasterDOs = getKeyCodeMasterList(KeyCodeMasterDO);
		}
		return select2vos;
	}
	
	/**
	 * 保存keyCodeMasterDO
	 * @param carSysUser 
	 *
	 */
	public void saveKeyCodeMaster(KeyCodeMasterDO keyCodeMasterDO, CarSysUserDO carSysUser)
			throws ServiceException {
		keyCodeMasterDO.setCompanyId(carSysUser.getCompanyId());
		try {
			if (keyCodeMasterDO.getKeyID() == null) {
				keyCodeMasterMapper.insert(keyCodeMasterDO);
			} else {
				keyCodeMasterMapper.update(keyCodeMasterDO);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据keyID获取对象
	 *
	 */
	public KeyCodeMasterDO getKeyCodeMasterByID(Integer keyID) {
		return keyCodeMasterMapper.getByID(keyID);
	}

	/**
	 * 根据keyCodeMasterDO查询列表
	 *   devUpd
	 */
	public List<KeyCodeMasterDO> getKeyCodeMasterList(
			KeyCodeMasterDO keyCodeMasterDO) {
		return keyCodeMasterMapper.getList(keyCodeMasterDO);
	}

	/**
	 * 根据keyCodeMasterDO查询分页列表
	 * 
	 * @param carSysUser 
	 */
	public List<KeyCodeMasterDO> getKeyCodeMasterList(
			KeyCodeMasterDO keyCodeMasterDO, CarSysUserDO userDo, PageBounds pageBounds) {
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		return keyCodeMasterMapper.getList(keyCodeMasterDO, pageBounds);
	}
	 /**
     * 批量删除keyID
     * @Title: deleteCodeMasterByID
     * @Description: TODO
     * @param splitIds
     * @param loginId 
     * @return: List<Integer>
     */
	public void deleteCodeMasterByID(String[] splitIds, Integer loginId) {
		try {
			KeyCodeMasterDO keyCodeMasterDO = new KeyCodeMasterDO();
			for (String id : splitIds) {
					keyCodeMasterDO.setKeyID(Integer.parseInt(id));
					keyCodeMasterDO.setDeleteCode(YSTConstants.DISENABLE);
					keyCodeMasterDO.setUpdateBy(loginId+"");
					keyCodeMasterDO.setLastModified(new Date());
					keyCodeMasterMapper.update(keyCodeMasterDO);
				
			}
		} catch (Exception e) {
			throw new ServiceException("删除多个id出错",e);
		}
	}
	/**
	 * 刷新缓存
	 * 
	 * @param request
	 * @throws Exception
	 * <br>
	 *             创建日期: 2015年4月1日 <br>
	 *             创 建 人: ALVIN
	 */
	public void refreshCache(int companyID) throws Exception {
		CodeMaster.getCodeMaster().clear();
		String zhFileName = AttributeContext.getAppPath() + "/js/mc_resources.js";
		File zhFile = new File(zhFileName);
		if (zhFile.exists())
			 zhFile.delete();
			zhFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(zhFile);
		OutputStreamWriter writer = new OutputStreamWriter(fos, "UTF-8");

		int i = 0;

		// 将KeyCodeMaster放入JS中
		writer.write("var codeMaster = {\n");
		KeyCodeMasterDO codeMasterDO = new KeyCodeMasterDO();
		codeMasterDO.setCompanyId(companyID);
		List<KeyCodeMasterDO> keyTypeList = keyCodeMasterMapper.getList(codeMasterDO);
		i = 0;
		for (KeyCodeMasterDO keyTypeDO : keyTypeList) {
			if (i > 0) {
				writer.write(",\n");
			}
			String code = keyTypeDO.getCode();
			// StringUtils.replaceEach(code, new String[]{"$", "{", "}"}, new
			// String[]{"", "", ""});
			writer.write("'" + keyTypeDO.getKeyType() + "_" + code + "':\""
					+ keyTypeDO.getDisplay() + "\"");
			i++;
		}
		writer.write("\n};\n");
		writer.write("function getDisplay(keyType, code){\n");
		writer.write("    return codeMaster[keyType+'_'+code];\n");
		writer.write("}");
		writer.close();
		logger.info("初始化CodeMaster成功.");
	}

	@Transactional
	public void TestInsert(List<KeyCodeMasterDO> codeDOs) {
		try {
			for (KeyCodeMasterDO keyCodeMasterDO : codeDOs) {
				keyCodeMasterMapper.insert(keyCodeMasterDO);
			}
		} catch (Exception e) {
			logger.error("插入KeyCode出错", e);
		}

	}

	public void TestInsertManual(List<KeyCodeMasterDO> codeDOs) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = txManager.getTransaction(def); // 获得事务状态
		try {
			for (KeyCodeMasterDO keyCodeMasterDO : codeDOs) {
				keyCodeMasterMapper.insert(keyCodeMasterDO);
			}
			txManager.commit(status);
		} catch (Exception e) {
			logger.error("插入KeyCode出错", e);
			txManager.rollback(status);
		}

	}

	public List<Select2VO> getSelect2KeyCodeBystr(
			List<KeyCodeMasterDO> keyCodeMasterDOs, List<Select2VO> select2vos) {
		for (KeyCodeMasterDO keyCodeMasterDO : keyCodeMasterDOs) {
			Select2VO select2vo = new Select2VO();
			select2vo.setId(keyCodeMasterDO.getCode());
			select2vo.setText(keyCodeMasterDO.getDisplay());
			select2vos.add(select2vo);
		}
		return select2vos;
	}

	/**
	 * 
	 * @Title: getSelect2DisplayBystr
	 * @Description: 车辆新增时下拉框显示和向后台传的value一致
	 * @param keyCodeMasterDOs
	 * @param select2vos
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午7:33:22
	 */
	public List<Select2VO> getSelect2DisplayBystr(
			List<KeyCodeMasterDO> keyCodeMasterDOs, List<Select2VO> select2vos) {
		for (KeyCodeMasterDO keyCodeMasterDO : keyCodeMasterDOs) {
			Select2VO select2vo = new Select2VO();
			select2vo.setId(keyCodeMasterDO.getDisplay());
			select2vo.setText(keyCodeMasterDO.getDisplay());
			select2vos.add(select2vo);
		}
		return select2vos;
	}

	/**
	 * 获取类型code
	 * 
	 * @param str
	 * @param kind
	 * @return
	 * @Title: getKindCode
	 * @Description: TODO
	 * @return: void
	 */
	public List<Select2VO> getKindCode(String kind, String str,CarSysUserDO carSysUser) {
		try {
			KeyCodeMasterDO keyCodeMasterDO = new KeyCodeMasterDO();
			List<Select2VO> select2vos = new ArrayList<>();
			keyCodeMasterDO.setKeyType(kind);
			keyCodeMasterDO.setSearchStr(str);	
			keyCodeMasterDO.setCompanyId(carSysUser.getCompanyId());
			List<KeyCodeMasterDO> KeyCodeMasterDOs =keyCodeMasterMapper.getKeyCodeBystr(keyCodeMasterDO); 
			return getSelect2KeyCodeBystr(KeyCodeMasterDOs, select2vos);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 
	 * @Title: getKindDisplay
	 * @Description: 下拉框的值和value都是显示的值
	 * @param kind
	 * @param str
	 * @return
	 * @return: List<Select2VO>
	 * @author: yanlinlung
	 * @param carSysUser 
	 * @date: 2017年10月18日 下午7:56:58
	 */
	public List<Select2VO> getKindDisplay(String kind, String str, CarSysUserDO carSysUser) {
		try {
			KeyCodeMasterDO keyCodeMasterDO = new KeyCodeMasterDO();
			List<Select2VO> select2vos = new ArrayList<>();
			keyCodeMasterDO.setKeyType(kind);
			keyCodeMasterDO.setSearchStr(str);		
			keyCodeMasterDO.setCompanyId(carSysUser.getCompanyId());
			List<KeyCodeMasterDO> KeyCodeMasterDOs =keyCodeMasterMapper.getKeyCodeBystr(keyCodeMasterDO); 
			return getSelect2DisplayBystr(KeyCodeMasterDOs, select2vos);
			
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	/**
	 * 
	 * @Title: getKeyCodeByDisplay
	 * @Description: 根据显示的值得KeyCode
	 * @param display
	 * @return
	 * @return: String
	 * @author: yanlinlung
	 * @date: 2017年10月18日 下午10:12:47
	 */
	public KeyCodeMasterDO getKeyCodeByDisplay(String display) {
		return keyCodeMasterMapper.getKeyCodeByDisplay(display);
	}

	
	
	/**
	 * @Title: updateSysPram
	 * @Description: 更新系统设置参数 
	 * @param sysPram
	 * @param userDo
	 * @return: void
	 * @author: zy  
	 * @date: 2017-11-2 下午11:19:40
	 */
	public void updateSysPram(CustomSysPram sysPram,CarSysUserDO userDo) {
		// 在外车辆闲置时间 
		KeyCodeMasterDO keyCodeMasterDO = new KeyCodeMasterDO();
		/*keyCodeMasterDO.setKeyType("IDLECARTIME");
		keyCodeMasterDO.setCode(sysPram.getIdleCarTime());
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		keyCodeMasterMapper.updateSysParam(keyCodeMasterDO);*/
		
		// 是否显示到期提示窗体
		/*keyCodeMasterDO=new KeyCodeMasterDO();
		keyCodeMasterDO.setKeyType("WINDOW");
		keyCodeMasterDO.setCode(sysPram.getWindow());
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		keyCodeMasterMapper.updateSysParam(keyCodeMasterDO);*/
		
		// 设置到期提示时间		
		keyCodeMasterDO=new KeyCodeMasterDO();
		keyCodeMasterDO.setKeyType("EXPIRETIME");
		keyCodeMasterDO.setCode(sysPram.getExpireTime());
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		keyCodeMasterMapper.updateSysParam(keyCodeMasterDO);
		
		// 预计回车时间
		keyCodeMasterDO=new KeyCodeMasterDO();
		keyCodeMasterDO.setKeyType("PREDICTTIME");
		keyCodeMasterDO.setCode(sysPram.getPredictTime());
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		keyCodeMasterMapper.updateSysParam(keyCodeMasterDO);
		
		// 回车提醒时间 
		keyCodeMasterDO=new KeyCodeMasterDO();
		keyCodeMasterDO.setKeyType("REMINDTIME");
		keyCodeMasterDO.setCode(sysPram.getRemindTime());
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		keyCodeMasterMapper.updateSysParam(keyCodeMasterDO);
	}
	/**
	 * 全局搜索
	 * @param searchStr
	 * @param pageBounds
	 * @return
	 */
	public List<KeyCodeMasterDO> getSearchCodeMasterList(String searchStr,CarSysUserDO userDo, PageBounds pageBounds) {
		KeyCodeMasterDO keyCodeMasterDO=new KeyCodeMasterDO();
		keyCodeMasterDO.setSearchStr(searchStr);
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		return keyCodeMasterMapper.getsearchCodeMasterList(keyCodeMasterDO,pageBounds);
	}

	public String KeyType(KeyCodeMasterDO keyType,KeyCodeMasterDO keyCodeMasterDO, CarSysUserDO carSysUser){
		String flag="";
		keyType.setCompanyId(carSysUser.getCompanyId());
		List<KeyCodeMasterDO> keyList=this.getKeyCodeMasterList(keyType);
		if(keyList.size()>0){
			for(KeyCodeMasterDO code:keyList){
				if((StringUtils.equals(code.getCode(), keyCodeMasterDO.getCode())&&
						StringUtils.equals(code.getDisplay(), keyCodeMasterDO.getDisplay()))
						&&keyCodeMasterDO.getKeyID().intValue()!=code.getKeyID().intValue()){
					flag=" 字典名称 与 编码 已存在！";
				}//如果id 不相等  且 编码相等  那么
				else if(StringUtils.equals(code.getCode(), keyCodeMasterDO.getCode()) 
						&& keyCodeMasterDO.getKeyID().intValue()!=code.getKeyID().intValue()){
					flag="字典编码 已存在！";
				}//如果id 不相等  且 字典名称相等  那么
				else if(StringUtils.equals(code.getDisplay(), keyCodeMasterDO.getDisplay()) 
						&& keyCodeMasterDO.getKeyID().intValue()!=code.getKeyID().intValue()){
					flag="字典名称 已存在！";
				}
			}
		}
		return flag;
	}

	/**
	 * @Title: getSysPramList
	 * @Description: 查询系统设置参数
	 * @param asList
	 * @param userDo
	 * @return
	 * @return: List<KeyCodeMasterDO>
	 * @author: zy  
	 * @date: 2017-11-2 下午11:18:23
	 */
	public List<KeyCodeMasterDO> getSysPramList(List<String> asList, CarSysUserDO userDo) {
		return keyCodeMasterMapper.getSysPramList(asList,userDo.getCompanyId());
	}

	/**
	 * @Title: getKindCodeForDropdown
	 * @Description: 获取正常的下拉菜单
	 * @param kind
	 * @param searchStr
	 * @param userDo
	 * @param keyCodeMasterDO
	 * @return
	 * @return: List<Select2VO>
	 * @author: zy  
	 * @date: 2017-11-2 上午9:19:20
	 */
	public List<Select2VO> getKindCodeForDropdown(String kind, String searchStr, CarSysUserDO userDo, KeyCodeMasterDO keyCodeMasterDO)
	{
		keyCodeMasterDO.setKeyType(kind);
		keyCodeMasterDO.setSearchStr(searchStr);
		keyCodeMasterDO.setDeleteCode(YSTConstants.DELETE_CODE_NORMAL);
		keyCodeMasterDO.setCompanyId(userDo.getCompanyId());
		return keyCodeMasterMapper.getKeyCodeForDropdown(keyCodeMasterDO);
	}

	public List<KeyCodeMasterDO> getKeyCodeMasterList(KeyCodeMasterDO keyCodeMasterDO,CarSysUserDO currentUser) {
		//keyTypeMapper.getKeyType("CAR_TYPE");
		keyCodeMasterDO.setKeyType("CAR_TYPE");
		return  keyCodeMasterMapper.getList(keyCodeMasterDO);
	}

	public void testPowerPage(KeyCodeMasterDO keyCodeMasterDO,
			PageBounds pageBounds, PowerBounds powerBounds) {
		keyCodeMasterMapper.getList(keyCodeMasterDO,pageBounds);
		
		
	}
}