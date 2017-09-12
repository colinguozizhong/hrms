package com.ustcsoft.system.model;

/**
 * 岗位的VO
 * 
 * @author 吴金华
 * @since 2017年1月6日
 */
public class SystemStationRecVO implements java.io.Serializable {
	private static final long serialVersionUID = -815526144026381355L;
	private String id;// 记录ID
	private String empId;// 相关联的人员ID
	private String deptNo;// 所在部门
	private String oldStationId;// 原岗位
	private String oldStationGrade;// 原岗位等级
	private String oldPosition;// 原职称
	private String newDeptNo;// 变更后部门
	private String newStationId;// 变更后岗位
	private String newStationGrade;// 变更后岗位等级
	private String newPrositon;// 变更后职称
	private String alterDate;// 变更时间
	private String creater;// 创建人
	private String createDate;// 创建时间
	
	private String oldStationName;// 原岗位名称
	private String newStationName;// 原岗位名称
	private String deptName;//变更单位名称
	private String newDeptName;//变更后单位名称
	private String changeReason;// 变更原因

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
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

	public String getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(String oldPosition) {
		this.oldPosition = oldPosition;
	}

	public String getNewDeptNo() {
		return newDeptNo;
	}

	public void setNewDeptNo(String newDeptNo) {
		this.newDeptNo = newDeptNo;
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

	public String getNewPrositon() {
		return newPrositon;
	}

	public void setNewPrositon(String newPrositon) {
		this.newPrositon = newPrositon;
	}

	public String getAlterDate() {
		return alterDate;
	}

	public void setAlterDate(String alterDate) {
		this.alterDate = alterDate;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getNewDeptName() {
		return newDeptName;
	}

	public void setNewDeptName(String newDeptName) {
		this.newDeptName = newDeptName;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

}
