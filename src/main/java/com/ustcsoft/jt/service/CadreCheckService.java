package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.CadreCheckVO;

public interface CadreCheckService {
	/**
	 * 分页查询所有干部考核信息
	 * 
	 */
	Page<CadreCheckVO> query(CadreCheckVO cadreCheck, int page, int rows);
	
	/**
	 * 添加干部考核
	 * 
	 */
	AjaxObj insertCadreCheck(CadreCheckVO cadreCheck, UserVO user);
	
	/**
	 * 修改干部考核
	 * 
	 */
	AjaxObj updateCadreCheck(CadreCheckVO cadreCheck);
	
	/**
	 * 根据id删除干部考核（以,分割）
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj deleteCadreCheck(String id);
}
