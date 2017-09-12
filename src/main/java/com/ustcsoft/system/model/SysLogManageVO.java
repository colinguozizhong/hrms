package com.ustcsoft.system.model;

/**
 * 人员异动的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月10日
 */
public class SysLogManageVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String operatorId;//操作者Id
	private String operatorName;//操作者姓名
	private String createTime;//创建时间
	private String operateMenu;//操作模块
	private String operateType;//操作事项
	private String operateContent;//操作详细
	private String browser;//浏览器
	private String object;//具体操作对象
	
	private String searchDateStart;//查询模块开始时间
	private String searchDateEnd;//查询模块结束时间
	
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperateMenu() {
		return operateMenu;
	}
	public void setOperateMenu(String operateMenu) {
		this.operateMenu = operateMenu;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public String getOperateContent() {
		return operateContent;
	}
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
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
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
}
