package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

/**
 * 在职人员分析报表查询的VO
 * 
 * @author 谈健
 * @since 2017年1月11日
 */
public class InServiceEmpChartVO extends BaseVO  {
	private static final long serialVersionUID = 1L;
	
	private String orgId;
	private String orgName;
	private String businessId;
	
	//性别组成
	private int sexMaleCount;//男性总数
	private int sexFemaleCount;//女性总数
	private int total;//总人数
	private float sexMalePercent;//男性百分比
	private float sexFemalePercent;//女性百分比
	
	//受教育程度分析
	private int edu1;
	private int edu2;
	private int edu3;
	private int edu4;
	private float edu1Percent;
	private float edu2Percent;
	private float edu3Percent;
	private float edu4Percent;
	
	private int glTotal;//工龄分析总人数
	private int gl1;
	private int gl2;
	private int gl3;
	private int gl4;
	private int gl5;
	private int gl6;
	private float glPercent1;
	private float glPercent2;
	private float glPercent3;
	private float glPercent4;
	private float glPercent5;
	private float glPercent6;
	
	//在职人员年龄分析
	private int ageTotal;
	private int age1;
	private int age2;
	private int age3;
	private int age4;
	private int age5;
	private float ageAverage;
	private float agePercent1;
	private float agePercent2;
	private float agePercent3;
	private float agePercent4;
	private float agePercent5;
	
	//在职人员岗位分析
	private int stationCount;
	private int stationPuGongCount;
	private int stationWenZhiCount;
	private int stationJiShuCount;
	private int stationGuanLiCount;
	private int stationQiTaCount;
	private float stationPuGongPercent;
	private float stationWenZhiPercent;
	private float stationJiShuPercent;
	private float stationGuanLiPercent;
	private float stationQiTaPercent;

	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getSexMaleCount() {
		return sexMaleCount;
	}
	public void setSexMaleCount(int sexMaleCount) {
		this.sexMaleCount = sexMaleCount;
	}
	public int getSexFemaleCount() {
		return sexFemaleCount;
	}
	public void setSexFemaleCount(int sexFemaleCount) {
		this.sexFemaleCount = sexFemaleCount;
	}
	public float getSexMalePercent() {
		return sexMalePercent;
	}
	public void setSexMalePercent(float sexMalePercent) {
		this.sexMalePercent = sexMalePercent;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public float getSexFemalePercent() {
		return sexFemalePercent;
	}
	public void setSexFemalePercent(float sexFemalePercent) {
		this.sexFemalePercent = sexFemalePercent;
	}
	public int getEdu1() {
		return edu1;
	}
	public void setEdu1(int edu1) {
		this.edu1 = edu1;
	}
	public int getEdu2() {
		return edu2;
	}
	public void setEdu2(int edu2) {
		this.edu2 = edu2;
	}
	public int getEdu3() {
		return edu3;
	}
	public void setEdu3(int edu3) {
		this.edu3 = edu3;
	}
	public int getEdu4() {
		return edu4;
	}
	public void setEdu4(int edu4) {
		this.edu4 = edu4;
	}
	public float getEdu1Percent() {
		return edu1Percent;
	}
	public void setEdu1Percent(float edu1Percent) {
		this.edu1Percent = edu1Percent;
	}
	public float getEdu2Percent() {
		return edu2Percent;
	}
	public void setEdu2Percent(float edu2Percent) {
		this.edu2Percent = edu2Percent;
	}
	public float getEdu3Percent() {
		return edu3Percent;
	}
	public void setEdu3Percent(float edu3Percent) {
		this.edu3Percent = edu3Percent;
	}
	public float getEdu4Percent() {
		return edu4Percent;
	}
	public void setEdu4Percent(float edu4Percent) {
		this.edu4Percent = edu4Percent;
	}
	public int getGlTotal() {
		return glTotal;
	}
	public void setGlTotal(int glTotal) {
		this.glTotal = glTotal;
	}
	public int getGl1() {
		return gl1;
	}
	public void setGl1(int gl1) {
		this.gl1 = gl1;
	}
	public int getGl2() {
		return gl2;
	}
	public void setGl2(int gl2) {
		this.gl2 = gl2;
	}
	public int getGl3() {
		return gl3;
	}
	public void setGl3(int gl3) {
		this.gl3 = gl3;
	}
	public int getGl4() {
		return gl4;
	}
	public void setGl4(int gl4) {
		this.gl4 = gl4;
	}
	public int getGl5() {
		return gl5;
	}
	public void setGl5(int gl5) {
		this.gl5 = gl5;
	}
	public int getGl6() {
		return gl6;
	}
	public void setGl6(int gl6) {
		this.gl6 = gl6;
	}
	public float getGlPercent1() {
		return glPercent1;
	}
	public void setGlPercent1(float glPercent1) {
		this.glPercent1 = glPercent1;
	}
	public float getGlPercent2() {
		return glPercent2;
	}
	public void setGlPercent2(float glPercent2) {
		this.glPercent2 = glPercent2;
	}
	public float getGlPercent3() {
		return glPercent3;
	}
	public void setGlPercent3(float glPercent3) {
		this.glPercent3 = glPercent3;
	}
	public float getGlPercent4() {
		return glPercent4;
	}
	public void setGlPercent4(float glPercent4) {
		this.glPercent4 = glPercent4;
	}
	public float getGlPercent5() {
		return glPercent5;
	}
	public void setGlPercent5(float glPercent5) {
		this.glPercent5 = glPercent5;
	}
	public float getGlPercent6() {
		return glPercent6;
	}
	public void setGlPercent6(float glPercent6) {
		this.glPercent6 = glPercent6;
	}
	public int getAgeTotal() {
		return ageTotal;
	}
	public void setAgeTotal(int ageTotal) {
		this.ageTotal = ageTotal;
	}
	public int getAge1() {
		return age1;
	}
	public void setAge1(int age1) {
		this.age1 = age1;
	}
	public int getAge2() {
		return age2;
	}
	public void setAge2(int age2) {
		this.age2 = age2;
	}
	public int getAge3() {
		return age3;
	}
	public void setAge3(int age3) {
		this.age3 = age3;
	}
	public int getAge4() {
		return age4;
	}
	public void setAge4(int age4) {
		this.age4 = age4;
	}
	public int getAge5() {
		return age5;
	}
	public void setAge5(int age5) {
		this.age5 = age5;
	}
	public float getAgeAverage() {
		return ageAverage;
	}
	public void setAgeAverage(float ageAverage) {
		this.ageAverage = ageAverage;
	}
	public float getAgePercent1() {
		return agePercent1;
	}
	public void setAgePercent1(float agePercent1) {
		this.agePercent1 = agePercent1;
	}
	public float getAgePercent2() {
		return agePercent2;
	}
	public void setAgePercent2(float agePercent2) {
		this.agePercent2 = agePercent2;
	}
	public float getAgePercent3() {
		return agePercent3;
	}
	public void setAgePercent3(float agePercent3) {
		this.agePercent3 = agePercent3;
	}
	public float getAgePercent4() {
		return agePercent4;
	}
	public void setAgePercent4(float agePercent4) {
		this.agePercent4 = agePercent4;
	}
	public float getAgePercent5() {
		return agePercent5;
	}
	public void setAgePercent5(float agePercent5) {
		this.agePercent5 = agePercent5;
	}
	public int getStationCount() {
		return stationCount;
	}
	public void setStationCount(int stationCount) {
		this.stationCount = stationCount;
	}
	public int getStationPuGongCount() {
		return stationPuGongCount;
	}
	public void setStationPuGongCount(int stationPuGongCount) {
		this.stationPuGongCount = stationPuGongCount;
	}
	public int getStationWenZhiCount() {
		return stationWenZhiCount;
	}
	public void setStationWenZhiCount(int stationWenZhiCount) {
		this.stationWenZhiCount = stationWenZhiCount;
	}
	public int getStationJiShuCount() {
		return stationJiShuCount;
	}
	public void setStationJiShuCount(int stationJiShuCount) {
		this.stationJiShuCount = stationJiShuCount;
	}
	public int getStationGuanLiCount() {
		return stationGuanLiCount;
	}
	public void setStationGuanLiCount(int stationGuanLiCount) {
		this.stationGuanLiCount = stationGuanLiCount;
	}
	public int getStationQiTaCount() {
		return stationQiTaCount;
	}
	public void setStationQiTaCount(int stationQiTaCount) {
		this.stationQiTaCount = stationQiTaCount;
	}
	public void setStationQiTaPercent(int stationQiTaPercent) {
		this.stationQiTaPercent = stationQiTaPercent;
	}
	public float getStationPuGongPercent() {
		return stationPuGongPercent;
	}
	public void setStationPuGongPercent(float stationPuGongPercent) {
		this.stationPuGongPercent = stationPuGongPercent;
	}
	public float getStationWenZhiPercent() {
		return stationWenZhiPercent;
	}
	public void setStationWenZhiPercent(float stationWenZhiPercent) {
		this.stationWenZhiPercent = stationWenZhiPercent;
	}
	public float getStationJiShuPercent() {
		return stationJiShuPercent;
	}
	public void setStationJiShuPercent(float stationJiShuPercent) {
		this.stationJiShuPercent = stationJiShuPercent;
	}
	public float getStationGuanLiPercent() {
		return stationGuanLiPercent;
	}
	public void setStationGuanLiPercent(float stationGuanLiPercent) {
		this.stationGuanLiPercent = stationGuanLiPercent;
	}
	public float getStationQiTaPercent() {
		return stationQiTaPercent;
	}
	public void setStationQiTaPercent(float stationQiTaPercent) {
		this.stationQiTaPercent = stationQiTaPercent;
	}
	
}
