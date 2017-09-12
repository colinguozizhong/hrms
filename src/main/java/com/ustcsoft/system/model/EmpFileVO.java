package com.ustcsoft.system.model;


/**
 * 人员附件信息的VO对象
 * 
 * @author 谈健
 * @since 2017年3月13日
 */
public class EmpFileVO implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileId;//附件Id
	private String empId;//人员Id
	private String savePath;//保存路径
	private String fileName;//文件名称
	private String fileFormat;//文件格式
	private String uploadPer;//上传人
	private String uploadTime;//上传时间
	private String remark;//备注
		
	private Long catalogid;//上传目录
	private String UUID;
	
	private String searchDateStart;//查询起始时间
	private String searchDateEnd;//查询截止时间
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getUploadPer() {
		return uploadPer;
	}
	public void setUploadPer(String uploadPer) {
		this.uploadPer = uploadPer;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getSearchDateStart() {
		return searchDateStart;
	}
	public void setSearchDateStart(String searchDateStart) {
		this.searchDateStart = searchDateStart;
	}
	public String getSearchDateEnd() {
		return searchDateEnd;
	}
	public void setSearchDateEnd(String searchDateEnd) {
		this.searchDateEnd = searchDateEnd;
	}
	public Long getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(Long catalogid) {
		this.catalogid = catalogid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
}
