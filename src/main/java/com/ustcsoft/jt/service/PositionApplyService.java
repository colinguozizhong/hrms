package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.PositionApplyVO;

public interface PositionApplyService {
	/**
	 * 分页查询所有职称申请信息
	 * 
	 */
	Page<PositionApplyVO> query(PositionApplyVO positionApply, int page, int rows);
	
	/**
	 * 添加职称申请单
	 * 
	 */
	AjaxObj insertPositionApply(PositionApplyVO positionApply, UserVO user);
	
	/**
	 * 修改职称申请单
	 * 
	 */
	AjaxObj updatePositionApply(PositionApplyVO positionApply);
	
	/**
	 * 根据id删除职称申请单（以,分割）
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionApplyId
	 * @return   )
	 */
	AjaxObj deletePositionApply(String positionApplyId);
	
	/**
	 * 提交职称申请单
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj submitPositionApply(String id);
	
	
	/**
	 * 审核
	 * 
	 */
	AjaxObj verifyPositionApply(String applyId, UserVO user);
	
}