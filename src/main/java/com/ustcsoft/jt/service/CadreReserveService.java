package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreReserveVO;

public interface CadreReserveService {
	/**
	 * 分页查询所有后备干部信息
	 * 
	 */
	Page<CadreReserveVO> query(CadreReserveVO cadreCheck, int page, int rows);
	
	/**
	 * 添加后备干部
	 * 
	 */
	AjaxObj insertCadreReserve(CadreReserveVO cadreReserve, UserVO user);
	
	/**
	 * 根据id删除后备干部（以,分割）
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj deleteCadreReserve(String id);
	
	/**
	 * 提交
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj submitCadreReserve(String id);
	
	
	/**
	 * 更新后备干部信息
	 * 
	 */
	AjaxObj updateCadreReserve(CadreReserveVO cadreReserve);
	
	/**
	 * 审批
	 * 
	 */
	AjaxObj approvCadreReserve(String applyId, UserVO user);
	
	/**
	 * 不予审批
	 * 
	 */
	AjaxObj denyCadreReserve(CadreReserveVO cadreReserve, UserVO user);
}
