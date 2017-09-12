package com.ustcsoft.system.model;


/**
 * 人员基本信息的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月4日
 */
public class SystemEmpVO implements java.io.Serializable {
	private static final long serialVersionUID = -7772435963561620291L;
	private String id;// 人员ID
	private String deptNO;// 所在部门
	private String unitNO;// 所在单位
	private String name;// 姓名
	private String sex;// 性别
	private String cardid;// 身份证号
	private String birthday;// 出生日期
	private String birthPlace;// 出生地
	private String useName;// 曾用名
	private String nation;// 民族
	private String nativePlace;// 籍贯
	private String pStatus;// 政治面貌
	private String address;// 联系地址
	private String phone;// 联系电话
	private String email;// 邮箱
	private String photo;// 相片
	private String stationId;// 岗位
	private String stationGrade;// 岗位等级
	private String job;// 职务
	private String jobRank;// 职级
	private String position;// 职称
	private String positionName;//职称名称
	private String entryInfo;// 入职方式
	private String entryDate;// 入职时间
	private String identityInfo;//身份信息
	private String entryPartyDate;//入党（团）时间
	private String workDate;//工作时间
	private String status;// 人员状态
	private String orgnization;// 单位编制
	private String eduFirstRec;// 第一学历
	private String eduRec;// 最高学历
	private String remark;// 备注
	private String creater;// 创建人
	private String createDate;// 创建时间
	
	private String degree;// 学位
	private String studySchool;// 毕业院校
	private String finishDate;// 毕业时间
	private String deptName;// 所在部门名称
	private String unitName;// 所在单位名称
	private String stationName;// 所在岗位名称
	private String eduRecName;// 学历的名称
	/* 入职时间区间 */
	private String entryDateStart;// 开始时间
	private String entryDateEnd;// 结束时间
	
	private SysStudyRecVO studyRec;//学历
	
	private String daoRuFlag;//导入操作标志；0-新增；1-修改；2-删除；
	private int xuHao;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptNO() {
		return deptNO;
	}

	public void setDeptNO(String deptNO) {
		this.deptNO = deptNO;
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

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUseName() {
		return useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getpStatus() {
		return pStatus;
	}

	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public String getStationGrade() {
		return stationGrade;
	}

	public void setStationGrade(String stationGrade) {
		this.stationGrade = stationGrade;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEntryInfo() {
		return entryInfo;
	}

	public void setEntryInfo(String entryInfo) {
		this.entryInfo = entryInfo;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrgnization() {
		return orgnization;
	}

	public void setOrgnization(String orgnization) {
		this.orgnization = orgnization;
	}

	public String getEduRec() {
		return eduRec;
	}

	public void setEduRec(String eduRec) {
		this.eduRec = eduRec;
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

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
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

	public String getEduRecName() {
		return eduRecName;
	}

	public void setEduRecName(String eduRecName) {
		this.eduRecName = eduRecName;
	}

	public SysStudyRecVO getStudyRec() {
		return studyRec;
	}

	public void setStudyRec(SysStudyRecVO studyRec) {
		this.studyRec = studyRec;
	}

	public String getEntryDateStart() {
		return entryDateStart;
	}

	public void setEntryDateStart(String entryDateStart) {
		this.entryDateStart = entryDateStart;
	}

	public String getEntryDateEnd() {
		return entryDateEnd;
	}

	public void setEntryDateEnd(String entryDateEnd) {
		this.entryDateEnd = entryDateEnd;
	}

	public String getIdentityInfo() {
		return identityInfo;
	}

	public void setIdentityInfo(String identityInfo) {
		this.identityInfo = identityInfo;
	}

	public String getEntryPartyDate() {
		return entryPartyDate;
	}

	public void setEntryPartyDate(String entryPartyDate) {
		this.entryPartyDate = entryPartyDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getUnitNO() {
		return unitNO;
	}

	public void setUnitNO(String unitNO) {
		this.unitNO = unitNO;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getJobRank() {
		return jobRank;
	}

	public void setJobRank(String jobRank) {
		this.jobRank = jobRank;
	}

	public String getEduFirstRec() {
		return eduFirstRec;
	}

	public void setEduFirstRec(String eduFirstRec) {
		this.eduFirstRec = eduFirstRec;
	}

	public String getDaoRuFlag() {
		return daoRuFlag;
	}

	public void setDaoRuFlag(String daoRuFlag) {
		this.daoRuFlag = daoRuFlag;
	}

	public int getXuHao() {
		return xuHao;
	}

	public void setXuHao(int xuHao) {
		this.xuHao = xuHao;
	}


	
}
