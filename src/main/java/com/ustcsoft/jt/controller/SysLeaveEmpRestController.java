package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysLeaveEmpService;
import com.ustcsoft.system.model.SysLeaveEmpVO;

/**
 * 离退休人员基本信息rest控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("sysLeaveEmp")
@RestController
public class SysLeaveEmpRestController extends AbstractRestController {
	@Resource
	private SysLeaveEmpService sysLeaveEmpService;
	
	/**
	 * 分页查询所有离职人员基本信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param deptNO
	 * @param status
	 * @param name
	 * @param job
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findLeaveEmpList.do")
	public Page<SysLeaveEmpVO> findLeaveEmpList(String leaveType, String name,  String phone,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysLeaveEmp/findLeaveEmpList.do");

		// 初始化查询条件
		SysLeaveEmpVO leaveEmp = new SysLeaveEmpVO();
		leaveEmp.setLeaveType(leaveType);
		leaveEmp.setName(name);
		leaveEmp.setPhone(phone);

		// 查询并返回
		return sysLeaveEmpService.query(leaveEmp, page, rows);
	}
}
