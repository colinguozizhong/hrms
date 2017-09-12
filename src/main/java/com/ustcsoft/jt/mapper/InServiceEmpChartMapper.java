package com.ustcsoft.jt.mapper;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.system.model.InServiceEmpChartVO;

public interface InServiceEmpChartMapper {
	/**
	 * 查询在职人员报表性别组成信息
	 */
	InServiceEmpChartVO searchSexInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员报表受教育程度组成信息
	 */
	InServiceEmpChartVO searchEduInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员报表工龄组成信息
	 */
	InServiceEmpChartVO searchGLInfo(@Param("chart") InServiceEmpChartVO  chart);
	
	/**
	 * 查询在职人员年龄组成信息
	 */
	InServiceEmpChartVO searchAgeInfo(InServiceEmpChartVO chart);
	
	/**
	 * 查询在职人员岗位组成信息
	 */
	InServiceEmpChartVO searchStationInfo(InServiceEmpChartVO chart);
	
}
