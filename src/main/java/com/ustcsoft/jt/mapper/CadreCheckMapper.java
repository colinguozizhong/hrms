package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.CadreCheckVO;

public interface CadreCheckMapper {

	/**
	 * 带分页的查询干部考核信息
	 * @return
	 */
	List<CadreCheckVO> queryWithPage(@Param("cadreCheck")CadreCheckVO cadreCheck, @Param("page") Page<CadreCheckVO> page);
    
	/**
	 * 新增干部考核
	 * @return
	 */
	void insertCadreCheck(CadreCheckVO cadreCheck);
	
	/**
	 * 删除干部考核
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param ids
	 */
	void deleteCadreCheck(List<String> ids);
	
	/**
	 * 更新干部考核
	 * @return
	 */
	void updateCadreCheck(CadreCheckVO cadreCheck);
}
