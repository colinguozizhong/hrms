package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

public class CadreCheckVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	private String cadreCheckId;
	private String empId;
	private String empName;
	private String dept;
	private String job;
	// 考核时间
	private String checkDate;
	// 考核内容
	private String checkContent;
	// 考核结果
	private String checkResult;
	// 考核评语
	private String checkComment;
	// 总得分
	private String checkScore;
	// 考核人Id
	private String checkPerId;
	// 考核人姓名
    private String checkPerName;
	// 创建人
	private String createPer;
	// 创建时间
	private String createDate;
	private String checkDateStart;//考核开始时间
	private String checkDateEnd;//考核结束时间
	private String stationName;
	public String getCadreCheckId() {
		return cadreCheckId;
	}
	public void setCadreCheckId(String cadreCheckId) {
		this.cadreCheckId = cadreCheckId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getCheckContent() {
		return checkContent;
	}
	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getCheckComment() {
		return checkComment;
	}
	public void setCheckComment(String checkComment) {
		this.checkComment = checkComment;
	}
	public String getCheckScore() {
		return checkScore;
	}
	public void setCheckScore(String checkScore) {
		this.checkScore = checkScore;
	}
	public String getCreatePer() {
		return createPer;
	}
	public void setCreatePer(String createPer) {
		this.createPer = createPer;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCheckDateStart() {
		return checkDateStart;
	}
	public void setCheckDateStart(String checkDateStart) {
		this.checkDateStart = checkDateStart;
	}
	public String getCheckDateEnd() {
		return checkDateEnd;
	}
	public void setCheckDateEnd(String checkDateEnd) {
		this.checkDateEnd = checkDateEnd;
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
	public String getCheckPerId() {
		return checkPerId;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setCheckPerId(String checkPerId) {
		this.checkPerId = checkPerId;
	}
	public String getCheckPerName() {
		return checkPerName;
	}
	public void setCheckPerName(String checkPerName) {
		this.checkPerName = checkPerName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
}