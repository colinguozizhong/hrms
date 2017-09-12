package com.ustcsoft.system.model;

import java.util.Date;

import com.ustcsoft.framework.vo.BaseVO;

public class CadreAppointRecordVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	private String applyId;
	private String empId;
	private String empName;
	private String deptNO;
	private String stationId;
	private String stationName;
	private String job;
	private String positionCode;
	private String positionName;
	
	// 申请岗位
	private String appStationId;
	private String appStationName;
	// 申请职务
	private String appJob;
	// 申请职称
	private String appPositionCode;
	private String appPositionName;
	// 申请原因
	private String appReason;
	
	// 经办人
	private String opPerId;
	private String opPerName;

	// 申请时间
	private String appDate;
	// 申请状态
	private String appStatus;
	//创建人
	private String createPer;
	// 创建时间
	private String createDate;
	// 审核人
	private String verifyPer;
	// 审核时间
	private String verifyDate;
	// 审批人
	private String approvPer;
	// 审批时间
	private String approvDate;
	// 申请开始时间
	private String appDateStart;
	//申请结束时间
	private String appDateEnd;
	
	private String xingMing;
	private String xingBie;
	private String chuShengRiQi;
	private String chuShengDi;
	private String minZu;
	private String jiGuan;
	private String ruDangShiJian;
	private String gongZuoShiJian;
	private String jianKangZhuangKuang;
	private String jiShuZhiWu;
	private String zhuanChang;
	private String riZhiJiaoYu;
	private String xueXiao;
	private String zaiZhiJiaoYu;
	private String zaiZhiXueXiao;
	private String xianRenZhiWu;
	private String niRenZhiWu;
	private String niMianZhiWu;
	private String jianLi;
	private String jiangCheng;
	private String nianDuKaoHe;
	private String renMianLiYou;
	private String danWeiYiJian;
	private String shenPiYiJian;
	private String jiGuanYiJian;
	private String creater;
	private String riZhiJaoYuXeWei;
	private String RiZhiZhuanYe;
	
	
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getDeptNO() {
		return deptNO;
	}
	public void setDeptNO(String deptNO) {
		this.deptNO = deptNO;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getAppStationId() {
		return appStationId;
	}
	public void setAppStationId(String appStationId) {
		this.appStationId = appStationId;
	}
	public String getAppJob() {
		return appJob;
	}
	public void setAppJob(String appJob) {
		this.appJob = appJob;
	}
	public String getAppPositionCode() {
		return appPositionCode;
	}
	public void setAppPositionCode(String appPositionCode) {
		this.appPositionCode = appPositionCode;
	}
	public String getAppReason() {
		return appReason;
	}
	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getCreatePer() {
		return createPer;
	}
	public void setCreatePer(String createPer) {
		this.createPer = createPer;
	}
	public String getVerifyPer() {
		return verifyPer;
	}
	public void setVerifyPer(String verifyPer) {
		this.verifyPer = verifyPer;
	}
	public String getApprovPer() {
		return approvPer;
	}
	public void setApprovPer(String approvPer) {
		this.approvPer = approvPer;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getApprovDate() {
		return approvDate;
	}
	public void setApprovDate(String approvDate) {
		this.approvDate = approvDate;
	}
	public String getOpPerId() {
		return opPerId;
	}
	public void setOpPerId(String opPerId) {
		this.opPerId = opPerId;
	}
	public String getOpPerName() {
		return opPerName;
	}
	public void setOpPerName(String opPerName) {
		this.opPerName = opPerName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getAppDateStart() {
		return appDateStart;
	}
	public void setAppDateStart(String appDateStart) {
		this.appDateStart = appDateStart;
	}
	public String getAppDateEnd() {
		return appDateEnd;
	}
	public void setAppDateEnd(String appDateEnd) {
		this.appDateEnd = appDateEnd;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getAppPositionName() {
		return appPositionName;
	}
	public void setAppPositionName(String appPositionName) {
		this.appPositionName = appPositionName;
	}
	public String getAppStationName() {
		return appStationName;
	}
	public void setAppStationName(String appStationName) {
		this.appStationName = appStationName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getXingMing() {
		return xingMing;
	}
	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}
	public String getXingBie() {
		return xingBie;
	}
	public void setXingBie(String xingBie) {
		this.xingBie = xingBie;
	}
	public String getChuShengRiQi() {
		return chuShengRiQi;
	}
	public void setChuShengRiQi(String chuShengRiQi) {
		this.chuShengRiQi = chuShengRiQi;
	}
	public String getChuShengDi() {
		return chuShengDi;
	}
	public void setChuShengDi(String chuShengDi) {
		this.chuShengDi = chuShengDi;
	}
	public String getMinZu() {
		return minZu;
	}
	public void setMinZu(String minZu) {
		this.minZu = minZu;
	}
	public String getJiGuan() {
		return jiGuan;
	}
	public void setJiGuan(String jiGuan) {
		this.jiGuan = jiGuan;
	}
	public String getRuDangShiJian() {
		return ruDangShiJian;
	}
	public void setRuDangShiJian(String ruDangShiJian) {
		this.ruDangShiJian = ruDangShiJian;
	}
	public String getGongZuoShiJian() {
		return gongZuoShiJian;
	}
	public void setGongZuoShiJian(String gongZuoShiJian) {
		this.gongZuoShiJian = gongZuoShiJian;
	}
	public String getJianKangZhuangKuang() {
		return jianKangZhuangKuang;
	}
	public void setJianKangZhuangKuang(String jianKangZhuangKuang) {
		this.jianKangZhuangKuang = jianKangZhuangKuang;
	}
	public String getJiShuZhiWu() {
		return jiShuZhiWu;
	}
	public void setJiShuZhiWu(String jiShuZhiWu) {
		this.jiShuZhiWu = jiShuZhiWu;
	}
	public String getZhuanChang() {
		return zhuanChang;
	}
	public void setZhuanChang(String zhuanChang) {
		this.zhuanChang = zhuanChang;
	}
	public String getRiZhiJiaoYu() {
		return riZhiJiaoYu;
	}
	public void setRiZhiJiaoYu(String riZhiJiaoYu) {
		this.riZhiJiaoYu = riZhiJiaoYu;
	}
	public String getXueXiao() {
		return xueXiao;
	}
	public void setXueXiao(String xueXiao) {
		this.xueXiao = xueXiao;
	}
	public String getZaiZhiJiaoYu() {
		return zaiZhiJiaoYu;
	}
	public void setZaiZhiJiaoYu(String zaiZhiJiaoYu) {
		this.zaiZhiJiaoYu = zaiZhiJiaoYu;
	}
	public String getZaiZhiXueXiao() {
		return zaiZhiXueXiao;
	}
	public void setZaiZhiXueXiao(String zaiZhiXueXiao) {
		this.zaiZhiXueXiao = zaiZhiXueXiao;
	}
	public String getXianRenZhiWu() {
		return xianRenZhiWu;
	}
	public void setXianRenZhiWu(String xianRenZhiWu) {
		this.xianRenZhiWu = xianRenZhiWu;
	}
	public String getNiRenZhiWu() {
		return niRenZhiWu;
	}
	public void setNiRenZhiWu(String niRenZhiWu) {
		this.niRenZhiWu = niRenZhiWu;
	}
	public String getNiMianZhiWu() {
		return niMianZhiWu;
	}
	public void setNiMianZhiWu(String niMianZhiWu) {
		this.niMianZhiWu = niMianZhiWu;
	}
	public String getJianLi() {
		return jianLi;
	}
	public void setJianLi(String jianLi) {
		this.jianLi = jianLi;
	}
	public String getJiangCheng() {
		return jiangCheng;
	}
	public void setJiangCheng(String jiangCheng) {
		this.jiangCheng = jiangCheng;
	}
	public String getNianDuKaoHe() {
		return nianDuKaoHe;
	}
	public void setNianDuKaoHe(String nianDuKaoHe) {
		this.nianDuKaoHe = nianDuKaoHe;
	}
	public String getRenMianLiYou() {
		return renMianLiYou;
	}
	public void setRenMianLiYou(String renMianLiYou) {
		this.renMianLiYou = renMianLiYou;
	}
	public String getDanWeiYiJian() {
		return danWeiYiJian;
	}
	public void setDanWeiYiJian(String danWeiYiJian) {
		this.danWeiYiJian = danWeiYiJian;
	}
	public String getShenPiYiJian() {
		return shenPiYiJian;
	}
	public void setShenPiYiJian(String shenPiYiJian) {
		this.shenPiYiJian = shenPiYiJian;
	}
	public String getJiGuanYiJian() {
		return jiGuanYiJian;
	}
	public void setJiGuanYiJian(String jiGuanYiJian) {
		this.jiGuanYiJian = jiGuanYiJian;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getRiZhiJaoYuXeWei() {
		return riZhiJaoYuXeWei;
	}
	public void setRiZhiJaoYuXeWei(String riZhiJaoYuXeWei) {
		this.riZhiJaoYuXeWei = riZhiJaoYuXeWei;
	}
	public String getRiZhiZhuanYe() {
		return RiZhiZhuanYe;
	}
	public void setRiZhiZhuanYe(String riZhiZhuanYe) {
		RiZhiZhuanYe = riZhiZhuanYe;
	}

	
}