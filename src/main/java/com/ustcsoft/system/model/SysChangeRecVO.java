package com.ustcsoft.system.model;

/**
 * 人员异动的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月10日
 */
public class SysChangeRecVO implements java.io.Serializable {
	private static final long serialVersionUID = 9006752020897087232L;
	private String headId;// headID
	private String detailId;// detailID
	private String orgId;//申请单位id
	private String orgName;//申请单位名称
	private String remark;//备注
	private String status;//单据状态
	private String empId;// 人员ID
	private String typeId;// 异动类型
	private String oldDeptNo;// 原部门/单位
	private String oldJob;// 原职务
	private String oldStationId;// 原岗位
	private String oldStationGrade;// 原岗位级别
	private String newDeptNo;// 异动后部门/单位
	private String newJob;// 异动后职务
	private String newStationId;// 异动后岗位
	private String newStationGrade;// 异动后岗位等级
	private String changeReason;// 异动原因
	private String changeDate;// 异动时间
	private String beOnTime;// 原职务在职时间
	private String creater;// 创建人
	private String createDate;// 创建时间
	private String verifyPer;//审核人
	private String verifyDate;//审核时间
	private String oldPosition;// 原职称
	private String newPrositon;// 变更后职称
	private String oldStationName;// 原岗位名称
	private String newStationName;// 异动后岗位名称
	private String oldDeptName;// 原部门/单位名称
	private String newDeptName;// 异动后部门/单位名称
	private String name;//涉及到的人员的姓名
	private String typeName;// 异动类型的名称
	private String transferUnit;//调入单位
	/* 异动时间的查询时间段 */
	private String changeDateStart;// 异动开始时间
	private String changeDateEnd;// 异动结束时间
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String info5;
    private String info6;
    private String changeReocrdId;//异动记录Id

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getOldDeptNo() {
		return oldDeptNo;
	}

	public void setOldDeptNo(String oldDeptNo) {
		this.oldDeptNo = oldDeptNo;
	}

	public String getOldJob() {
		return oldJob;
	}

	public void setOldJob(String oldJob) {
		this.oldJob = oldJob;
	}

	public String getOldStationId() {
		return oldStationId;
	}

	public void setOldStationId(String oldStationId) {
		this.oldStationId = oldStationId;
	}

	public String getOldStationGrade() {
		return oldStationGrade;
	}

	public void setOldStationGrade(String oldStationGrade) {
		this.oldStationGrade = oldStationGrade;
	}

	public String getNewDeptNo() {
		return newDeptNo;
	}

	public void setNewDeptNo(String newDeptNo) {
		this.newDeptNo = newDeptNo;
	}

	public String getNewJob() {
		return newJob;
	}

	public void setNewJob(String newJob) {
		this.newJob = newJob;
	}

	public String getNewStationId() {
		return newStationId;
	}

	public void setNewStationId(String newStationId) {
		this.newStationId = newStationId;
	}

	public String getNewStationGrade() {
		return newStationGrade;
	}

	public void setNewStationGrade(String newStationGrade) {
		this.newStationGrade = newStationGrade;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getBeOnTime() {
		return beOnTime;
	}

	public void setBeOnTime(String beOnTime) {
		this.beOnTime = beOnTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(String oldPosition) {
		this.oldPosition = oldPosition;
	}

	public String getNewPrositon() {
		return newPrositon;
	}

	public void setNewPrositon(String newPrositon) {
		this.newPrositon = newPrositon;
	}

	public String getOldStationName() {
		return oldStationName;
	}

	public void setOldStationName(String oldStationName) {
		this.oldStationName = oldStationName;
	}

	public String getNewStationName() {
		return newStationName;
	}

	public void setNewStationName(String newStationName) {
		this.newStationName = newStationName;
	}

	public String getOldDeptName() {
		return oldDeptName;
	}

	public void setOldDeptName(String oldDeptName) {
		this.oldDeptName = oldDeptName;
	}

	public String getNewDeptName() {
		return newDeptName;
	}

	public void setNewDeptName(String newDeptName) {
		this.newDeptName = newDeptName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getChangeDateStart() {
		return changeDateStart;
	}

	public void setChangeDateStart(String changeDateStart) {
		this.changeDateStart = changeDateStart;
	}

	public String getChangeDateEnd() {
		return changeDateEnd;
	}

	public void setChangeDateEnd(String changeDateEnd) {
		this.changeDateEnd = changeDateEnd;
	}

	public String getInfo1() {
		return info1;
	}

	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getInfo3() {
		return info3;
	}

	public void setInfo3(String info3) {
		this.info3 = info3;
	}

	public String getInfo4() {
		return info4;
	}

	public void setInfo4(String info4) {
		this.info4 = info4;
	}

	public String getInfo5() {
		return info5;
	}

	public void setInfo5(String info5) {
		this.info5 = info5;
	}

	public String getInfo6() {
		return info6;
	}

	public void setInfo6(String info6) {
		this.info6 = info6;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getTransferUnit() {
		return transferUnit;
	}

	public void setTransferUnit(String transferUnit) {
		this.transferUnit = transferUnit;
	}

	public String getChangeReocrdId() {
		return changeReocrdId;
	}

	public void setChangeReocrdId(String changeReocrdId) {
		this.changeReocrdId = changeReocrdId;
	}
	
}
