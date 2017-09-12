package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

/**
 * 职称培训记录的VO
 * 
 * @author 谈健
 * @since 2017年1月13日
 */
public class PositionTrainRecordVO extends BaseVO {
	/**
	 * 职称培训记录VO
	 */
	private static final long serialVersionUID = 1L;
	private String positionTrainId;// 职称培训记录ID
	private String empId;// 员工Id
	private String empName;// 员工姓名
	private String dept;// 所在部门
	private String trainDate;// 培训日期
	private String trainContent;// 培训内容
	private String trainDept;// 培训部门
	private String trainTime;// 培训学时
	private String trainInfo;// 培训方式
	private String trainScore;// 培训成绩
	private String trainResult;// 培训结果
	private String stationName;// 岗位
	private String job;// 职务
	private String trainDateStart;// 培训开始日期
	private String trainDateEnd;// 培训结束日期
	private String createPer;// 创建人
	private String createDate;// 创建时间

	public String getPositionTrainId() {
		return positionTrainId;
	}

	public void setPositionTrainId(String positionTrainId) {
		this.positionTrainId = positionTrainId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getTrainDate() {
		return trainDate;
	}

	public void setTrainDate(String trainDate) {
		this.trainDate = trainDate;
	}

	public String getTrainContent() {
		return trainContent;
	}

	public void setTrainContent(String trainContent) {
		this.trainContent = trainContent;
	}

	public String getTrainDept() {
		return trainDept;
	}

	public void setTrainDept(String trainDept) {
		this.trainDept = trainDept;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public String getTrainInfo() {
		return trainInfo;
	}

	public void setTrainInfo(String trainInfo) {
		this.trainInfo = trainInfo;
	}

	public String getTrainScore() {
		return trainScore;
	}

	public void setTrainScore(String trainScore) {
		this.trainScore = trainScore;
	}

	public String getTrainResult() {
		return trainResult;
	}

	public void setTrainResult(String trainResult) {
		this.trainResult = trainResult;
	}

	public String getCreatePer() {
		return createPer;
	}

	public void setCreatePer(String createPer) {
		this.createPer = createPer;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTrainDateStart() {
		return trainDateStart;
	}

	public void setTrainDateStart(String trainDateStart) {
		this.trainDateStart = trainDateStart;
	}

	public String getTrainDateEnd() {
		return trainDateEnd;
	}

	public void setTrainDateEnd(String trainDateEnd) {
		this.trainDateEnd = trainDateEnd;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
