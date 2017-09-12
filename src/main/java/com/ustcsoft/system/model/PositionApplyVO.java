package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

/**
 * 职称类型的VO
 * 
 * @author 谈健
 * @since 2017年1月11日
 */
public class PositionApplyVO extends BaseVO  {
	/**
	 * 职称申请VO
	 */
	private static final long serialVersionUID = 1L;
	private String headId;// headId
	private String detailId;// detailId
	private String appStatus;//申请状态
	private String orgId;
	private String orgName;//部门名称
	private String remark;//备注
	private String applyEmpId;//申请员工姓名
	private String applyEmpName;// 申请员工姓名
	private String dept;//所在部门
	private String applyDate;// 职称申请日期
	private String applyReason;// 申请原因
	private String oldStationId;// 原岗位Id
	private String oldStationName;// 原岗位名称
	private String oldStationGrade;// 原岗位等级
	private String oldPositionId;// 原职称Id
	private String oldPositionName;// 原职称名称
	private String oldJob;// 原职务
	private String appStationId;// 申请岗位Id
	private String appPositionId;// 申请职称Id
	private String appStationName;// 申请岗位名称
	private String appPositionName;// 申请职称名称
	private String appJob;// 申请职务

	private String createPer;// 创建人
	private String createDate;// 创建时间
	private String verifyPer;// 审核人
	private String verifyDate;// 审核时间
	private String applyDateStart;//开始申请时间
	private String applyDateEnd;//开始申请时间

	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getOldStationId() {
		return oldStationId;
	}
	public void setOldStationId(String oldStationId) {
		this.oldStationId = oldStationId;
	}
	public String getOldStationName() {
		return oldStationName;
	}
	public void setOldStationName(String oldStationName) {
		this.oldStationName = oldStationName;
	}
	public String getOldStationGrade() {
		return oldStationGrade;
	}
	public void setOldStationGrade(String oldStationGrade) {
		this.oldStationGrade = oldStationGrade;
	}
	public String getOldPositionId() {
		return oldPositionId;
	}
	public void setOldPositionId(String oldPositionId) {
		this.oldPositionId = oldPositionId;
	}
	public String getOldPositionName() {
		return oldPositionName;
	}
	public void setOldPositionName(String oldPositionName) {
		this.oldPositionName = oldPositionName;
	}
	public String getOldJob() {
		return oldJob;
	}
	public void setOldJob(String oldJob) {
		this.oldJob = oldJob;
	}
	public String getAppStationId() {
		return appStationId;
	}
	public void setAppStationId(String appStationId) {
		this.appStationId = appStationId;
	}
	public String getAppPositionId() {
		return appPositionId;
	}
	public void setAppPositionId(String appPositionId) {
		this.appPositionId = appPositionId;
	}
	public String getAppJob() {
		return appJob;
	}
	public void setAppJob(String appJob) {
		this.appJob = appJob;
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
	public String getAppStationName() {
		return appStationName;
	}
	public void setAppStationName(String appStationName) {
		this.appStationName = appStationName;
	}
	public String getAppPositionName() {
		return appPositionName;
	}
	public void setAppPositionName(String appPositionName) {
		this.appPositionName = appPositionName;
	}
	public String getApplyEmpName() {
		return applyEmpName;
	}
	public void setApplyEmpName(String applyEmpName) {
		this.applyEmpName = applyEmpName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getApplyDateStart() {
		return applyDateStart;
	}
	public void setApplyDateStart(String applyDateStart) {
		this.applyDateStart = applyDateStart;
	}
	public String getApplyDateEnd() {
		return applyDateEnd;
	}
	public void setApplyDateEnd(String applyDateEnd) {
		this.applyDateEnd = applyDateEnd;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getHeadId() {
		return headId;
	}
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplyEmpId() {
		return applyEmpId;
	}
	public void setApplyEmpId(String applyEmpId) {
		this.applyEmpId = applyEmpId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
