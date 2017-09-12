package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

public class LeaveEmpCharVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	private String orgId;
	private String orgName;
	private int month;
	private int year;
	
	private String leaveStartDateStr;
	private String leaveEndDateStr;
	
	private int glFXRS1;// 3个月以内离职人数
	private int glFXRS2;// 3-6月离职人数
	private int glFXRS3;// 6月-1年离职人数
	private int glFXRS4;// 1年-2年离职人数
	private int glFXRS5;// 2年-3年离职人数
	private int glFXRS6;// 3年以上离职人数
	
	private float glFXBL1;// 3个月以内离职百分比
	private float glFXBL2;// 3-6月离职百分比
	private float glFXBL3;// 6月-1年离职百分比
	private float glFXBL4;// 1年-2年离职百分比
	private float glFXBL5;// 2年-3年离职百分比
	private float glFXBL6;// 3年以上离职百分比
	
	private int sjyFXRS1;// 初中及以下 人数
	private int sjyFXRS2;// 高中/中专 人数
	private int sjyFXRS3;// 大专 人数
	private int sjyFXRS4;// 本科及以上 人数
	private int sjyFXRS5;// 其他 人数
	
	private float sjyFXBL1;// 初中及以下 离职百分比
	private float sjyFXBL2;// 高中/中专 离职百分比
	private float sjyFXBL3;// 大专 离职百分比
	private float sjyFXBL4;// 本科及以上 离职百分比
	private float sjyFXBL5;// 其他 离职百分比
	
	private int lzZRS; //  离职总人数
	private int lzPGRS;//  普工类 离职人数
	private int lzGLRS;//  管理类 离职人数
	private int lzJSRS;//  技术类 离职人数
	private int lzQLRS;//   其他类 离职人数
	
	private float lzZRSBL;//  离职百分比
	private float lzPGRSBL;// 普工类  占离职百分比
	private float lzGLRSBL;// 管理类 占离职百分比
	private float lzJSRSBL;// 技术类 占离职百分比
	private float lzQLRSBL;// 其他类 占离职百分比
	
	private String position; //岗位
	
	private int lzZRSP; //  岗位离职人数
	private float lzZRSPBL;// 各岗位离职百分比
	
	private String positionLZYY; //各岗位离职原因
	private String positionLZGS; //各岗位离职改善措施
	
	
	private String eduRace;  //受教育类别
	private int eduRaceCount;
	private String stationType;  //人员层次类别
	private int stationTypeCount;
	private String stationName;  //岗位类别
	private int stationNameCount;
	
	
	
	
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
	

	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public float getGlFXBL1() {
		return glFXBL1;
	}
	public void setGlFXBL1(float glFXBL1) {
		this.glFXBL1 = glFXBL1;
	}
	public float getGlFXBL2() {
		return glFXBL2;
	}
	public void setGlFXBL2(float glFXBL2) {
		this.glFXBL2 = glFXBL2;
	}
	public float getGlFXBL3() {
		return glFXBL3;
	}
	public void setGlFXBL3(float glFXBL3) {
		this.glFXBL3 = glFXBL3;
	}
	public float getGlFXBL4() {
		return glFXBL4;
	}
	public void setGlFXBL4(float glFXBL4) {
		this.glFXBL4 = glFXBL4;
	}
	public float getGlFXBL5() {
		return glFXBL5;
	}
	public void setGlFXBL5(float glFXBL5) {
		this.glFXBL5 = glFXBL5;
	}
	public float getGlFXBL6() {
		return glFXBL6;
	}
	public void setGlFXBL6(float glFXBL6) {
		this.glFXBL6 = glFXBL6;
	}
	public float getSjyFXBL1() {
		return sjyFXBL1;
	}
	public void setSjyFXBL1(float sjyFXBL1) {
		this.sjyFXBL1 = sjyFXBL1;
	}
	public float getSjyFXBL2() {
		return sjyFXBL2;
	}
	public void setSjyFXBL2(float sjyFXBL2) {
		this.sjyFXBL2 = sjyFXBL2;
	}
	public float getSjyFXBL3() {
		return sjyFXBL3;
	}
	public void setSjyFXBL3(float sjyFXBL3) {
		this.sjyFXBL3 = sjyFXBL3;
	}
	public float getSjyFXBL4() {
		return sjyFXBL4;
	}
	public void setSjyFXBL4(float sjyFXBL4) {
		this.sjyFXBL4 = sjyFXBL4;
	}
	public float getSjyFXBL5() {
		return sjyFXBL5;
	}
	public void setSjyFXBL5(float sjyFXBL5) {
		this.sjyFXBL5 = sjyFXBL5;
	}
	public int getLzZRS() {
		return lzZRS;
	}
	public void setLzZRS(int lzZRS) {
		this.lzZRS = lzZRS;
	}
	public int getLzPGRS() {
		return lzPGRS;
	}
	public void setLzPGRS(int lzPGRS) {
		this.lzPGRS = lzPGRS;
	}
	public int getLzGLRS() {
		return lzGLRS;
	}
	public void setLzGLRS(int lzGLRS) {
		this.lzGLRS = lzGLRS;
	}
	public int getLzJSRS() {
		return lzJSRS;
	}
	public void setLzJSRS(int lzJSRS) {
		this.lzJSRS = lzJSRS;
	}
	public int getLzQLRS() {
		return lzQLRS;
	}
	public void setLzQLRS(int lzQLRS) {
		this.lzQLRS = lzQLRS;
	}
	public float getLzZRSBL() {
		return lzZRSBL;
	}
	public void setLzZRSBL(float lzZRSBL) {
		this.lzZRSBL = lzZRSBL;
	}
	public float getLzPGRSBL() {
		return lzPGRSBL;
	}
	public void setLzPGRSBL(float lzPGRSBL) {
		this.lzPGRSBL = lzPGRSBL;
	}
	public float getLzGLRSBL() {
		return lzGLRSBL;
	}
	public void setLzGLRSBL(float lzGLRSBL) {
		this.lzGLRSBL = lzGLRSBL;
	}
	public float getLzJSRSBL() {
		return lzJSRSBL;
	}
	public void setLzJSRSBL(float lzJSRSBL) {
		this.lzJSRSBL = lzJSRSBL;
	}
	public float getLzQLRSBL() {
		return lzQLRSBL;
	}
	public void setLzQLRSBL(float lzQLRSBL) {
		this.lzQLRSBL = lzQLRSBL;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getLzZRSP() {
		return lzZRSP;
	}
	public void setLzZRSP(int lzZRSP) {
		this.lzZRSP = lzZRSP;
	}
	public float getLzZRSPBL() {
		return lzZRSPBL;
	}
	public void setLzZRSPBL(float lzZRSPBL) {
		this.lzZRSPBL = lzZRSPBL;
	}
	public String getPositionLZYY() {
		return positionLZYY;
	}
	public void setPositionLZYY(String positionLZYY) {
		this.positionLZYY = positionLZYY;
	}
	public String getPositionLZGS() {
		return positionLZGS;
	}
	public void setPositionLZGS(String positionLZGS) {
		this.positionLZGS = positionLZGS;
	}
	public String getLeaveStartDateStr() {
		return leaveStartDateStr;
	}
	public void setLeaveStartDateStr(String leaveStartDateStr) {
		this.leaveStartDateStr = leaveStartDateStr;
	}
	public String getLeaveEndDateStr() {
		return leaveEndDateStr;
	}
	public void setLeaveEndDateStr(String leaveEndDateStr) {
		this.leaveEndDateStr = leaveEndDateStr;
	}
	public String getEduRace() {
		return eduRace;
	}
	public void setEduRace(String eduRace) {
		this.eduRace = eduRace;
	}
	public int getEduRaceCount() {
		return eduRaceCount;
	}
	public void setEduRaceCount(int eduRaceCount) {
		this.eduRaceCount = eduRaceCount;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
	public int getStationTypeCount() {
		return stationTypeCount;
	}
	public void setStationTypeCount(int stationTypeCount) {
		this.stationTypeCount = stationTypeCount;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public int getStationNameCount() {
		return stationNameCount;
	}
	public void setStationNameCount(int stationNameCount) {
		this.stationNameCount = stationNameCount;
	}
	public float getGlFXRS1() {
		return glFXRS1;
	}
	public int getGlFXRS2() {
		return glFXRS2;
	}
	public void setGlFXRS2(int glFXRS2) {
		this.glFXRS2 = glFXRS2;
	}
	public int getGlFXRS3() {
		return glFXRS3;
	}
	public void setGlFXRS3(int glFXRS3) {
		this.glFXRS3 = glFXRS3;
	}
	public int getGlFXRS4() {
		return glFXRS4;
	}
	public void setGlFXRS4(int glFXRS4) {
		this.glFXRS4 = glFXRS4;
	}
	public int getGlFXRS5() {
		return glFXRS5;
	}
	public void setGlFXRS5(int glFXRS5) {
		this.glFXRS5 = glFXRS5;
	}
	public int getGlFXRS6() {
		return glFXRS6;
	}
	public void setGlFXRS6(int glFXRS6) {
		this.glFXRS6 = glFXRS6;
	}
	public int getSjyFXRS1() {
		return sjyFXRS1;
	}
	public void setSjyFXRS1(int sjyFXRS1) {
		this.sjyFXRS1 = sjyFXRS1;
	}
	public int getSjyFXRS2() {
		return sjyFXRS2;
	}
	public void setSjyFXRS2(int sjyFXRS2) {
		this.sjyFXRS2 = sjyFXRS2;
	}
	public int getSjyFXRS3() {
		return sjyFXRS3;
	}
	public void setSjyFXRS3(int sjyFXRS3) {
		this.sjyFXRS3 = sjyFXRS3;
	}
	public int getSjyFXRS4() {
		return sjyFXRS4;
	}
	public void setSjyFXRS4(int sjyFXRS4) {
		this.sjyFXRS4 = sjyFXRS4;
	}
	public int getSjyFXRS5() {
		return sjyFXRS5;
	}
	public void setSjyFXRS5(int sjyFXRS5) {
		this.sjyFXRS5 = sjyFXRS5;
	}
	public void setGlFXRS1(int glFXRS1) {
		this.glFXRS1 = glFXRS1;
	}

	
}