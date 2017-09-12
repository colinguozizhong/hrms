package com.ustcsoft.system.model;

/**
 * 人员异动类型的VO对象
 * 
 * @author 吴金华
 * @since 2017年1月9日
 */
public class SysChangeTypeVO implements java.io.Serializable {
	private static final long serialVersionUID = -6703541032111885363L;
	private String typeId;// 类型代码
	private String typeName;// 类型名称
	private String creater;// 创建人
	private String createDate;// 创建时间

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
