package com.ustcsoft.jt.service;

import com.ustcsoft.system.model.InServiceEmpChartVO;

/**
 * 在职人员分析报表接口
 * @since   2017年3月19日
 */
public interface InServiceEmpChartService {
	/**
	 * 查询在职人员性别组成信息
	 */
	InServiceEmpChartVO searchSexInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员学历组成信息
	 */
	InServiceEmpChartVO searchEduInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员工龄组成信息
	 */
	InServiceEmpChartVO searchGLInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员年龄组成信息
	 */
	InServiceEmpChartVO searchAgeInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员岗位组成信息
	 */
	InServiceEmpChartVO searchStationInfo(InServiceEmpChartVO chart);
}
