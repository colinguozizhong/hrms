package com.ustcsoft.system.model;

import com.ustcsoft.framework.vo.BaseVO;

public class FamilyVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	private String empId;
	private String id;
	private String chengWei;
	private String xingMingJt;
	private String nianLing;
	private String zhengZhiMianMao;
	private String gongZuoZhiWu;
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChengWei() {
		return chengWei;
	}
	public void setChengWei(String chengWei) {
		this.chengWei = chengWei;
	}


	public String getXingMingJt() {
		return xingMingJt;
	}
	public void setXingMingJt(String xingMingJt) {
		this.xingMingJt = xingMingJt;
	}
	public String getNianLing() {
		return nianLing;
	}
	public void setNianLing(String nianLing) {
		this.nianLing = nianLing;
	}
	public String getZhengZhiMianMao() {
		return zhengZhiMianMao;
	}
	public void setZhengZhiMianMao(String zhengZhiMianMao) {
		this.zhengZhiMianMao = zhengZhiMianMao;
	}
	public String getGongZuoZhiWu() {
		return gongZuoZhiWu;
	}
	public void setGongZuoZhiWu(String gongZuoZhiWu) {
		this.gongZuoZhiWu = gongZuoZhiWu;
	}

}