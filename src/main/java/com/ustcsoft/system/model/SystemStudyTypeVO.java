package com.ustcsoft.system.model;

/**
 * 学历类型的VO
 * @author  吴金华
 * @since   2017年1月5日
 */
public class SystemStudyTypeVO implements java.io.Serializable {
	private static final long serialVersionUID = 9111197427492202787L;
	private String id;// 记录ID
	private String studyCode;// 学历编码
	private String studyName;// 学历名称
	private String creater;// 创建人
	private String createDate;// 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudyCode() {
		return studyCode;
	}

	public void setStudyCode(String studyCode) {
		this.studyCode = studyCode;
	}

	public String getStudyName() {
		return studyName;
	}

	public void setStudyName(String studyName) {
		this.studyName = studyName;
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

}
