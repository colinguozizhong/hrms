package com.ustcsoft.jt.service;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysLeaveEmpVO;

/**
 * 离退休人员信息的服务接口
 * @author  吴金华
 * @since   2017年1月9日
 */
public interface SysLeaveEmpService {

	/**
	 * 分页查询所有离退休人员基本信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param leaveEmp
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysLeaveEmpVO> query(SysLeaveEmpVO leaveEmp, int page, int rows);

}
