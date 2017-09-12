package com.ustcsoft.system.model;

/**
 * 离退休人员基本信息的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月9日
 */
public class SysLeaveEmpVO implements java.io.Serializable {
	private static final long serialVersionUID = -3276657507289511184L;
	private String id;// 记录ID
	private String empId;// 人员ID
	private String leaveType;// 离退休类型
	private String leaveDate;// 离退休时间
	private String leaveReason;// 离退休原因
	private String remark;// 备注
	private String creater;// 创建人
	private String createDate;// 创建时间

	
	private String name;// 离退休人员的姓名
	private String sex;// 离退休人员的性别
	private String deptName;// 离退休人员的原单位名称
	private String job;// 离退休人员的原单位职务
	private String phone;// 离退休人员的联系电话
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

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
