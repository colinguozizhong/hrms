package com.ustcsoft.jt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.jt.mapper.SysLeaveEmpMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysLeaveEmpService;
import com.ustcsoft.system.model.SysLeaveEmpVO;

@Service("sysLeaveEmpService")
public class SysLeaveEmpServiceImpl implements SysLeaveEmpService {
	@Resource
	private SysLeaveEmpMapper sysLeaveEmpMapper;
	
	/**
	 * 分页查询所有离退休人员基本信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param leaveEmp
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public Page<SysLeaveEmpVO> query(SysLeaveEmpVO leaveEmp, int page, int rows) {
		Page<SysLeaveEmpVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysLeaveEmpVO> list = sysLeaveEmpMapper.queryWithPage(leaveEmp, pageVO);// ?
		pageVO.setItems(list);
		return pageVO;
	}

}
