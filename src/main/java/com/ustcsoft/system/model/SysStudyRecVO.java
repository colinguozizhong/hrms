package com.ustcsoft.system.model;

/**
 * 学历的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月5日
 */
public class SysStudyRecVO implements java.io.Serializable {
	private static final long serialVersionUID = -4670987987743095157L;
	private String id;// 记录ID
	private String empId;// 人员ID
	private String studyInfo;// 学习形式
	private String eduRec;// 学历
	private String studyPro;// 专业
	private String degree;// 学位
	private String studySchool;// 毕业院校
	private String statrtDate;// 入学时间
	private String finishDate;// 毕业时间
	private String remark;// 备注
	private String reterence;// 证明人
	private String creater;// 创建人
	private String createDate;// 创建时间

	private String deptName;// 部门名称
	private String stationName;// 岗位名称
	private String eduName;// 学历名称
	private String eduType;// 学历类型 first 第一学历  top最高学历
	
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

	public String getStudyInfo() {
		return studyInfo;
	}

	public void setStudyInfo(String studyInfo) {
		this.studyInfo = studyInfo;
	}

	public String getEduRec() {
		return eduRec;
	}

	public void setEduRec(String eduRec) {
		this.eduRec = eduRec;
	}

	public String getStudyPro() {
		return studyPro;
	}

	public void setStudyPro(String studyPro) {
		this.studyPro = studyPro;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getStudySchool() {
		return studySchool;
	}

	public void setStudySchool(String studySchool) {
		this.studySchool = studySchool;
	}

	public String getStatrtDate() {
		return statrtDate;
	}

	public void setStatrtDate(String statrtDate) {
		this.statrtDate = statrtDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReterence() {
		return reterence;
	}

	public void setReterence(String reterence) {
		this.reterence = reterence;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getEduName() {
		return eduName;
	}

	public void setEduName(String eduName) {
		this.eduName = eduName;
	}

	public String getEduType() {
		return eduType;
	}

	public void setEduType(String eduType) {
		this.eduType = eduType;
	}

	
}
