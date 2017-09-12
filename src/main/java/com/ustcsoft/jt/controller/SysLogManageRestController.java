package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysLogManageService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreReserveVO;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysLogManageVO;

/**
 * 系统日志管理的rest控制器
 * 
 * @author 谈健
 * @since 2017年3月9日
 */
@RequestMapping("sysLog")
@RestController
public class SysLogManageRestController extends AbstractRestController {
	@Resource
	private SysLogManageService sysLogManageService;

	/**
	 * 分页查询所有系统操作日志信息
	 * 
	 * 
	 */
	@RequestMapping("findSysLogList.do")
	public Page<SysLogManageVO> findSysLogList(
			String operatorName, 
			String operateType,
			String operateMenu, 
			String searchDateStart,
			String searchDateEnd,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysLog/findSysLogList.do");
		// 初始化查询条件
		SysLogManageVO sysLogManage = new SysLogManageVO();
		sysLogManage.setOperatorName(operatorName);
		sysLogManage.setOperateType(operateType);
		sysLogManage.setOperateMenu(operateMenu);
		sysLogManage.setSearchDateStart(searchDateStart);
		sysLogManage.setSearchDateEnd(searchDateEnd);
		// 查询并返回
		return sysLogManageService.query(sysLogManage, page, rows);
	}
	
	/**
	 * 录入操作日志
	 * 
	 * @author 谈健
	 * @since 2017年3月9日
	 * @param 
	 * @return
	 */
	@RequestMapping("insertSysLog.do")
	public AjaxObj insertSysLog(SysLogManageVO sysLogManage) {
		UserVO currentUser = this.getCurrentUser();
		return sysLogManageService.insertSysLog(sysLogManage, currentUser);
	}
}
