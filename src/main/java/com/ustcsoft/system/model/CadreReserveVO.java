package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;
//后备干部申请VO
public class CadreReserveVO extends BaseVO {
	
	private static final long serialVersionUID = 1L;
	private String headId;
	private String detailId;
	//被推荐人Id
	private String empId;
	private String empName;
	private String dept;
	// 推荐时间
	private String recommendDate;
	// 推荐单位
	private String recommendDeptNO;
	// 推荐单位名称
	private String recommendDeptName;
	// 推荐原因
	private String recommendReason;
	// 推荐人Id
	private String recommendPersonId;
	// 推荐人姓名
	private String recommendPersonName;
	// 推荐职务
	private String recommendJob;
	// 推荐材料
	private String recommendMaterial;
	// 推荐票数
	private String recommendBallot;
	// 创建人
	private String createPer;
	// 创建时间
	private String createDate;
	private String appStatus;
	// 审批人
	private String verifyPer;
	//审批时间
	private String verifyDate;
	private String recommendDateStart;//开始推荐时间	
	private String recommendDateEnd;//结束推荐时间
	private String flag;//标记选择保存还是提交
	private String remark;//备注
	private String orgId;//所在单位Id
	
	
	
	//后备人才库主键Id
	private String reserverId;
	//不予审批原因
	private String failedReason;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getRecommendReason() {
		return recommendReason;
	}
	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}
	public String getRecommendPersonId() {
		return recommendPersonId;
	}
	public void setRecommendPersonId(String recommendPersonId) {
		this.recommendPersonId = recommendPersonId;
	}
	public String getRecommendJob() {
		return recommendJob;
	}
	public void setRecommendJob(String recommendJob) {
		this.recommendJob = recommendJob;
	}
	public String getRecommendMaterial() {
		return recommendMaterial;
	}
	public void setRecommendMaterial(String recommendMaterial) {
		this.recommendMaterial = recommendMaterial;
	}
	public String getRecommendBallot() {
		return recommendBallot;
	}
	public void setRecommendBallot(String recommendBallot) {
		this.recommendBallot = recommendBallot;
	}
	public String getCreatePer() {
		return createPer;
	}
	public void setCreatePer(String createPer) {
		this.createPer = createPer;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRecommendDate() {
		return recommendDate;
	}
	public void setRecommendDate(String recommendDate) {
		this.recommendDate = recommendDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getRecommendDateStart() {
		return recommendDateStart;
	}
	public void setRecommendDateStart(String recommendDateStart) {
		this.recommendDateStart = recommendDateStart;
	}
	public String getRecommendDateEnd() {
		return recommendDateEnd;
	}
	public void setRecommendDateEnd(String recommendDateEnd) {
		this.recommendDateEnd = recommendDateEnd;
	}
	public String getRecommendPersonName() {
		return recommendPersonName;
	}
	public void setRecommendPersonName(String recommendPersonName) {
		this.recommendPersonName = recommendPersonName;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getReserverId() {
		return reserverId;
	}
	public void setReserverId(String reserverId) {
		this.reserverId = reserverId;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getVerifyPer() {
		return verifyPer;
	}
	public void setVerifyPer(String verifyPer) {
		this.verifyPer = verifyPer;
	}
	public String getVerifyDate() {
		return verifyDate;
	}
	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}
	public String getRecommendDeptNO() {
		return recommendDeptNO;
	}
	public void setRecommendDeptNO(String recommendDeptNO) {
		this.recommendDeptNO = recommendDeptNO;
	}
	public String getRecommendDeptName() {
		return recommendDeptName;
	}
	public void setRecommendDeptName(String recommendDeptName) {
		this.recommendDeptName = recommendDeptName;
	}
	
}