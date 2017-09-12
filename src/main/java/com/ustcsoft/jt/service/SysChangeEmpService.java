package com.ustcsoft.jt.service;

import java.util.List;
import java.util.Map;

import com.ustcsoft.system.model.LeaveEmpCharVO;
import com.ustcsoft.system.model.SysChangeEmpChartVO;

/**
 * 人员异动分析报表接口
 * @author  FanYou
 * @since   2017年3月17日
 */
public interface SysChangeEmpService {
	/**
	 * 查询人员异动相关信息
	 * @param map，Map对象，如{businessId:"3401040000000000", changeMonth:"2017-03"}
	 * businessId 所查询的单位或部门Id
	 * changeMonth 所查询的月份，如：2017-03
	 * @return
	 */
	List<SysChangeEmpChartVO> findChangeEmpInfo(Map<String, String> map);
	
	/**
	 * 离职人员分析-工龄分析
	 * @param leaveEmpCharVO
	 * @return
	 */
	LeaveEmpCharVO glFXList(LeaveEmpCharVO leaveEmpCharVO);
	LeaveEmpCharVO sjyFXList(LeaveEmpCharVO leaveEmpCharVO);
	LeaveEmpCharVO lzccFXList(LeaveEmpCharVO leaveEmpCharVO);
	List<LeaveEmpCharVO> gwFXList(LeaveEmpCharVO leaveEmpCharVO);
}
