package com.ustcsoft.system.model;

import java.util.Date;

public class MUserPesVO {
	private String mUserPesId;
	
	private String tableName;
	
	private String dataId;
	
	private Date createTime;
	
	private String userId;

	public String getmUserPesId() {
		return mUserPesId;
	}

	public void setmUserPesId(String mUserPesId) {
		this.mUserPesId = mUserPesId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
