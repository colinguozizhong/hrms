package com.ustcsoft.system.model;

import java.util.Date;

import com.ustcsoft.framework.vo.BaseVO;

/**
 * 职称类型的VO
 * 
 * @author 谈健
 * @since 2017年1月11日
 */
public class SysPositionTypeVO extends BaseVO  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String positionId;// 记录ID
	private String positionName;// 职称名称
	private String positionPro;// 专业
	private String positionGrade;// 职称等级
	private String proName;//专业名称
	private String creater;// 创建人
	private Date createDate;// 创建时间
	
	private String positionIdTemp;
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPositionPro() {
		return positionPro;
	}
	public void setPositionPro(String positionPro) {
		this.positionPro = positionPro;
	}
	public String getPositionGrade() {
		return positionGrade;
	}
	public void setPositionGrade(String positionGrade) {
		this.positionGrade = positionGrade;
	}
	public String getPositionIdTemp() {
		return positionIdTemp;
	}
	public void setPositionIdTemp(String positionIdTemp) {
		this.positionIdTemp = positionIdTemp;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
	
}
