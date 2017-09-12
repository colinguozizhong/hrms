package com.ustcsoft.jt.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.system.model.LeaveEmpCharVO;

public interface SysChangeEmpMapper {
	/**
	 * 查询人员异动相关信息
	 * @param map，Map对象，如{businessId:"3401040000000000", changeMonth:"2017-03"}
	 * deptId 所查询的单位或部门Id
	 * changeMonth 所查询的月份，如：2017-03
	 * @return
	 */
	List<Map<String, String>> findChangeEmpInfo(@Param("map")Map<String, String> map);
	/**
	 * 查询当月编制人数
	 * @param map，Map对象，如{businessId:"3401040000000000", changeMonth:"2017-03"}
	 * @return
	 */
	List<Map<String, String>> findNumOfBianZhi(@Param("map")Map<String, String> map);

	LeaveEmpCharVO glFXList(@Param("leaveEmpCharVO") LeaveEmpCharVO leaveEmpCharVO);

	List<LeaveEmpCharVO> sjyFXList(@Param("leaveEmpCharVO") LeaveEmpCharVO leaveEmpCharVO);

	List<LeaveEmpCharVO> lzccFXList(@Param("leaveEmpCharVO") LeaveEmpCharVO leaveEmpCharVO);

	List<LeaveEmpCharVO> gwFXList(@Param("leaveEmpCharVO") LeaveEmpCharVO leaveEmpCharVO);
}
