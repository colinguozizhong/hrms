package com.ustcsoft.system.model;

import java.util.Date;

import com.ustcsoft.framework.vo.BaseVO;

/**
 * 专业类型的VO
 * 
 * @author 谈健
 * @since 2017年1月11日
 */
public class SysProfessionTypeVO extends BaseVO  {
	private static final long serialVersionUID = 1L;
	private String proCode;//专业编码
	private String proName;//专业名称
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
	
}
