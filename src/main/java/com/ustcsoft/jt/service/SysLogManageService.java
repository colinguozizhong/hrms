package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysLogManageVO;

public interface SysLogManageService {
	/**
	 * 分页查询所有系统操作日志
	 * 
	 */
	Page<SysLogManageVO> query(SysLogManageVO sysLogManage, int page, int rows);
	
	/**
	 * 录入系统日志
	 * @return 
	 * 
	 */
	AjaxObj insertSysLog(SysLogManageVO sysLogManage, UserVO user);
	/**
	 * 查询所有日志不分页
	 * @param sysLogManage
	 * @return
	 */
	List<SysLogManageVO> find(SysLogManageVO sysLogManage);
}