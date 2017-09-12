package com.ustcsoft.jt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ustcsoft.jt.mapper.SysChangeEmpMapper;
import com.ustcsoft.jt.service.InServiceEmpChartService;
import com.ustcsoft.jt.service.SysChangeEmpService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.InServiceEmpChartVO;
import com.ustcsoft.system.model.SysChangeEmpChartVO;

@RequestMapping("chart")
@Controller
public class SysChangeEmpController extends AbstractRestController {
	@Resource
	private SysChangeEmpService sysChangeEmpService;
	@Resource
	private SysChangeEmpMapper sysChangeEmpMapper;
	@Resource
	private InServiceEmpChartService inServiceEmpChartService;

	/**
	 * 跳转到人力资源异动分析页面
	 * @author FanYou
	 * @since 2017年3月15日
	 * @return
	 */
	@RequestMapping("sysChangeEmpChart.do")
	public String index() {
		logger.info("chart/sysChangeEmpChart.do");
		return "system/sysChangeEmpChart";
	}
	
	/**
	 * 跳转到人力资源离职分析页面
	 * @author Szz
	 * @since 2017年3月15日
	 * @return
	 */
	@RequestMapping("sysLeaveEmpChart.do")
	public String sysLeaveEmpChart() {
		logger.info("chart/sysLeaveEmpChart.do");
		return "system/sysLeaveEmpChart";
	}
	
	/**
	 * 跳转到人事报表在职人员分析页面
	 * @author 谈健
	 * @since 2017年3月18日
	 * @return
	 */
	@RequestMapping("sysInServiceEmpChart.do")
	public String sysInServiceEmpChart() {
		logger.info("chart/sysInServiceEmpChart.do");
		return "system/sysInServiceEmpChart";
	}
	
	/**
	 * 查询人员异动相关信息
	 * @param businessId 所查询的单位或部门Id
	 * @param changeMonth 所查询的月份，如：2017-03
	 * @return
	 */
	@RequestMapping("findChangeEmpInfo.do")
	public List<SysChangeEmpChartVO> findChangeEmpInfo(String businessId, String changeMonth) {
		logger.info("chart/findChangeEmpInfo.do");
		Map<String, String> map = new HashMap<String, String>();
		map.put("businessId", businessId);
		map.put("changeMonth", changeMonth);
		
		return sysChangeEmpService.findChangeEmpInfo(map);
	}
	
	/**
	 * 查询在职人员性别组成
	 * @param businessId 所查询的单位或部门Id
	 * @return
	 */
	@RequestMapping("searchSexInfo.do")
	public InServiceEmpChartVO searchSexInfo(String businessId) {
		logger.info("chart/searchSexInfo.do");
		InServiceEmpChartVO inServiceChart = new InServiceEmpChartVO();
		inServiceChart.setBusinessId(CommonUtils.getSearchId(businessId));
		return inServiceEmpChartService.searchSexInfo(inServiceChart);
	}
	
	/**
	 * 查询在职人员学历组成
	 * @param businessId 所查询的单位或部门Id
	 * @return
	 */
	@RequestMapping("searchEduInfo.do")
	public InServiceEmpChartVO searchEduInfo(String businessId) {
		logger.info("chart/searchEduInfo.do");
		InServiceEmpChartVO inServiceChart = new InServiceEmpChartVO();
		inServiceChart.setBusinessId(CommonUtils.getSearchId(businessId));
		return inServiceEmpChartService.searchEduInfo(inServiceChart);
	}
	
	/**
	 * 查询在职人员工龄组成
	 * @param businessId 所查询的单位或部门Id
	 * @return
	 */
	@RequestMapping("searchGLInfo.do")
	public InServiceEmpChartVO searchGLInfo(String businessId) {
		logger.info("chart/searchGLInfo.do");
		InServiceEmpChartVO inServiceChart = new InServiceEmpChartVO();
		inServiceChart.setBusinessId(CommonUtils.getSearchId(businessId));
		return inServiceEmpChartService.searchGLInfo(inServiceChart);
	}
	
	/**
	 * 查询在职人员年龄组成
	 * @param businessId 所查询的单位或部门Id
	 * @return
	 */
	@RequestMapping("searchAgeInfo.do")
	public InServiceEmpChartVO searchAgeInfo(String businessId) {
		logger.info("chart/searchAgeInfo.do");
		InServiceEmpChartVO inServiceChart = new InServiceEmpChartVO();
		inServiceChart.setBusinessId(CommonUtils.getSearchId(businessId));
		return inServiceEmpChartService.searchAgeInfo(inServiceChart);
	}
	
	/**
	 * 查询在职人员岗位组成
	 * @param businessId 所查询的单位或部门Id
	 * @return
	 */
	@RequestMapping("searchStationInfo.do")
	public InServiceEmpChartVO searchStationInfo(String businessId) {
		logger.info("chart/searchStationInfo.do");
		InServiceEmpChartVO inServiceChart = new InServiceEmpChartVO();
		inServiceChart.setBusinessId(CommonUtils.getSearchId(businessId));
		return inServiceEmpChartService.searchStationInfo(inServiceChart);
	}
	
}
