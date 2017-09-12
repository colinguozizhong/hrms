package com.ustcsoft.jt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.jt.mapper.InServiceEmpChartMapper;
import com.ustcsoft.jt.service.InServiceEmpChartService;
import com.ustcsoft.system.model.InServiceEmpChartVO;

@Service
public class InServiceEmpChartServiceImpl implements InServiceEmpChartService {

	@Resource
	private InServiceEmpChartMapper inServiceEmpChartMapper;

	public InServiceEmpChartVO searchSexInfo(InServiceEmpChartVO inServiceChart){
		inServiceChart = inServiceEmpChartMapper.searchSexInfo(inServiceChart);
		//计算男女总人数
		inServiceChart.setTotal(inServiceChart.getSexMaleCount()+inServiceChart.getSexFemaleCount());
		if(inServiceChart.getSexMaleCount()!=0&&inServiceChart.getSexFemaleCount()!=0){
		inServiceChart.setSexFemalePercent((float)inServiceChart.getSexFemaleCount()/inServiceChart.getTotal()*100);
		inServiceChart.setSexMalePercent((float)inServiceChart.getSexMaleCount()/inServiceChart.getTotal()*100);
		}else if(inServiceChart.getSexMaleCount()==0&&inServiceChart.getSexFemaleCount()!=0){
			inServiceChart.setSexMalePercent(0f);
			inServiceChart.setSexFemalePercent(100f);
		}else if(inServiceChart.getSexMaleCount()!=0&&inServiceChart.getSexFemaleCount()==0){
			inServiceChart.setSexMalePercent(100f);
			inServiceChart.setSexFemalePercent(0f);
		}else{
			inServiceChart.setSexMalePercent(0f);
			inServiceChart.setSexFemalePercent(0f);
		}
		return inServiceChart;
	}

	public InServiceEmpChartVO searchEduInfo(InServiceEmpChartVO inServiceChart){
		inServiceChart = inServiceEmpChartMapper.searchEduInfo(inServiceChart);
		inServiceChart.setTotal(inServiceChart.getEdu1()+inServiceChart.getEdu2()+inServiceChart.getEdu3()+inServiceChart.getEdu4());
		if(inServiceChart.getTotal()==0){
			inServiceChart.setEdu1Percent(0f);
			inServiceChart.setEdu2Percent(0f);
			inServiceChart.setEdu3Percent(0f);
			inServiceChart.setEdu4Percent(0f);
		}else{
			inServiceChart.setEdu1Percent((float)inServiceChart.getEdu1()/inServiceChart.getTotal()*100);
			inServiceChart.setEdu2Percent((float)inServiceChart.getEdu2()/inServiceChart.getTotal()*100);
			inServiceChart.setEdu3Percent((float)inServiceChart.getEdu3()/inServiceChart.getTotal()*100);
			inServiceChart.setEdu4Percent((float)inServiceChart.getEdu4()/inServiceChart.getTotal()*100);
		}
		return inServiceChart;
	}
	
	public InServiceEmpChartVO searchGLInfo(InServiceEmpChartVO inServiceChart){
		inServiceChart = inServiceEmpChartMapper.searchGLInfo(inServiceChart);
		if(inServiceChart.getGlTotal()==0){
			inServiceChart.setGlPercent1(0f);
			inServiceChart.setGlPercent2(0f);
			inServiceChart.setGlPercent3(0f);
			inServiceChart.setGlPercent4(0f);
			inServiceChart.setGlPercent5(0f);
			inServiceChart.setGlPercent6(0f);
		}else{
			inServiceChart.setGlPercent1((float)inServiceChart.getGl1()/inServiceChart.getGlTotal()*100);
			inServiceChart.setGlPercent2((float)inServiceChart.getGl2()/inServiceChart.getGlTotal()*100);
			inServiceChart.setGlPercent3((float)inServiceChart.getGl3()/inServiceChart.getGlTotal()*100);
			inServiceChart.setGlPercent4((float)inServiceChart.getGl4()/inServiceChart.getGlTotal()*100);
			inServiceChart.setGlPercent5((float)inServiceChart.getGl5()/inServiceChart.getGlTotal()*100);
			inServiceChart.setGlPercent6((float)inServiceChart.getGl6()/inServiceChart.getGlTotal()*100);
		}
		return inServiceChart;
	}
	
	public InServiceEmpChartVO searchAgeInfo(InServiceEmpChartVO inServiceChart){
		inServiceChart = inServiceEmpChartMapper.searchAgeInfo(inServiceChart);
		if(inServiceChart.getAgeTotal()==0){
			inServiceChart.setAgePercent1(0f);
			inServiceChart.setAgePercent2(0f);
			inServiceChart.setAgePercent3(0f);
			inServiceChart.setAgePercent4(0f);
			inServiceChart.setAgePercent5(0f);
			inServiceChart.setAgeAverage(0f);
		}else{
			inServiceChart.setAgePercent1((float)inServiceChart.getAge1()/inServiceChart.getAgeTotal()*100);
			inServiceChart.setAgePercent2((float)inServiceChart.getAge2()/inServiceChart.getAgeTotal()*100);
			inServiceChart.setAgePercent3((float)inServiceChart.getAge3()/inServiceChart.getAgeTotal()*100);
			inServiceChart.setAgePercent4((float)inServiceChart.getAge4()/inServiceChart.getAgeTotal()*100);
			inServiceChart.setAgePercent5((float)inServiceChart.getAge5()/inServiceChart.getAgeTotal()*100);
		}
		return inServiceChart;
	}
	
	public InServiceEmpChartVO searchStationInfo(InServiceEmpChartVO inServiceChart){
		inServiceChart = inServiceEmpChartMapper.searchStationInfo(inServiceChart);
		if(inServiceChart.getStationCount()==0){
			inServiceChart.setStationPuGongPercent(0f);
			inServiceChart.setStationWenZhiPercent(0f);
			inServiceChart.setStationJiShuPercent(0f);
			inServiceChart.setStationGuanLiPercent(0f);
			inServiceChart.setStationQiTaPercent(0f);
		}else{
			inServiceChart.setStationPuGongPercent((float)inServiceChart.getStationPuGongCount()/inServiceChart.getStationCount()*100);
			inServiceChart.setStationWenZhiPercent((float)inServiceChart.getStationWenZhiCount()/inServiceChart.getStationCount()*100);
			inServiceChart.setStationJiShuPercent((float)inServiceChart.getStationJiShuCount()/inServiceChart.getStationCount()*100);
			inServiceChart.setStationGuanLiPercent((float)inServiceChart.getStationGuanLiCount()/inServiceChart.getStationCount()*100);
			inServiceChart.setStationQiTaPercent((float)inServiceChart.getStationQiTaCount()/inServiceChart.getStationCount()*100);
		}
		return inServiceChart;
	}
	
}
