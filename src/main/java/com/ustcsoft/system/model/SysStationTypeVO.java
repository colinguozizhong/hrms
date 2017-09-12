package com.ustcsoft.system.model;

/**
 * 岗位类型的VO
 * 
 * @author 吴金华
 * @since 2017年1月10日
 */
public class SysStationTypeVO implements java.io.Serializable {
	private static final long serialVersionUID = -8879401087016003997L;
	private String id;// 记录ID
	private String stationCode;// 岗位编码
	private String stationName;// 岗位名称
	private String stationGrade;// 岗位等级
	private String totalPer;//岗位人员总数
	private String complementPer;//编制人员数
	private String creater;// 创建人
	private String createDate;// 创建时间
	private String stationType;// 岗位类型

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationGrade() {
		return stationGrade;
	}

	public void setStationGrade(String stationGrade) {
		this.stationGrade = stationGrade;
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

	public String getTotalPer() {
		return totalPer;
	}

	public void setTotalPer(String totalPer) {
		this.totalPer = totalPer;
	}

	public String getComplementPer() {
		return complementPer;
	}

	public void setComplementPer(String complementPer) {
		this.complementPer = complementPer;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

}
