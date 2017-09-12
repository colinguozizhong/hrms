package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.PositionApplyVO;

public interface PositionApplyMapper {

	/**
	 * 带分页的查询职称申请单信息
	 * @return
	 */
	List<PositionApplyVO> queryWithPage(@Param("positionApply")PositionApplyVO positionApply, @Param("page") Page<PositionApplyVO> page);
    
	/**
	 * 新增职称申请单信息（head表）
	 * @return
	 */
	void insertPositionApplyHead(PositionApplyVO positionApply);
	
	/**
	 * 新增职称申请单信息（detail表）
	 * @return
	 */
	void insertPositionApplyDetail(PositionApplyVO positionApply);
	
	/**
	 * 删除后备干部(head表)
	 * @return
	 */
	void deletePositionApplyHead(String headId);
	
	/**
	 * 删除后备干部(Detail表)
	 * @return
	 */
	void deletePositionApplyDetail(String headId);
	/**
	 * 修改职称申请单信息
	 * @return                         
	 */
	void updatePositionApply(PositionApplyVO positionApply);
	
	/**
	 * 提交
	 * @return
	 */
	void submitPositionApply(String headId);
	
	/**
	 * 审核
	 * @return
	 */
	void verifyPositionApply(PositionApplyVO positionApply);
	
	/**
	 * 审批
	 * @return
	 */
	void approvPositionApply(PositionApplyVO positionApply);
}
