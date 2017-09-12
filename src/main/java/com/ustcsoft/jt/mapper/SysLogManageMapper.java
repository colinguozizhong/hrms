package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysLogManageVO;

public interface SysLogManageMapper {

	/**
	 * 带分页的查询系统操作日志
	 * @return
	 */
	List<SysLogManageVO> queryWithPage(@Param("sysLogManage")SysLogManageVO sysLogManage, @Param("page") Page<SysLogManageVO> page);
	
	/**
	 * 录入操作日志
	 * @return
	 */
	void insertSysLog(SysLogManageVO sysLogManage);
    /**
     * 
     * @param sysLogManage
     * @return
     */
	List<SysLogManageVO> findLog(@Param("sysLogManage") SysLogManageVO sysLogManage);
}
