package com.ustcsoft.system.model;

/**
 * ‘人事报表-人员异动分析表’界面对应基本信息的VO对象
 * 
 * @author 樊友
 * @since 2017年3月21日
 */
public class SysChangeEmpChartVO {
	private String deptId;// 部门Id
	private String deptName;// 部门名称
	private int numOfBianZhi;// 编制人数
	private int numOfRuZhi;// 入职人数
	private int numOfCiZhi;// 辞职人数
	private int numOfCiTui;// 辞退人数
	private int numOfZiLi;// 自离人数
	private int numOfDiaoRu;// 调入人数
	private int numOfDiaoChu;// 调出人数
	private int numOfJinSheng;// 晋升人数
	private int numOfHuanGang;// 换岗人数
	private int numOfZhuanZheng;//转正人数
	private int numOfTuiXiu;//退休人数
	private int numOfTiaoZheng;//部门调整人数

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public int getNumOfBianZhi() {
		return numOfBianZhi;
	}

	public void setNumOfBianZhi(int numOfBianZhi) {
		this.numOfBianZhi = numOfBianZhi;
	}

	public int getNumOfRuZhi() {
		return numOfRuZhi;
	}

	public void setNumOfRuZhi(int numOfRuZhi) {
		this.numOfRuZhi = numOfRuZhi;
	}

	public int getNumOfCiZhi() {
		return numOfCiZhi;
	}

	public void setNumOfCiZhi(int numOfCiZhi) {
		this.numOfCiZhi = numOfCiZhi;
	}

	public int getNumOfCiTui() {
		return numOfCiTui;
	}

	public void setNumOfCiTui(int numOfCiTui) {
		this.numOfCiTui = numOfCiTui;
	}

	public int getNumOfZiLi() {
		return numOfZiLi;
	}

	public void setNumOfZiLi(int numOfZiLi) {
		this.numOfZiLi = numOfZiLi;
	}

	public int getNumOfDiaoRu() {
		return numOfDiaoRu;
	}

	public void setNumOfDiaoRu(int numOfDiaoRu) {
		this.numOfDiaoRu = numOfDiaoRu;
	}

	public int getNumOfDiaoChu() {
		return numOfDiaoChu;
	}

	public void setNumOfDiaoChu(int numOfDiaoChu) {
		this.numOfDiaoChu = numOfDiaoChu;
	}

	public int getNumOfJinSheng() {
		return numOfJinSheng;
	}

	public void setNumOfJinSheng(int numOfJinSheng) {
		this.numOfJinSheng = numOfJinSheng;
	}

	public int getNumOfHuanGang() {
		return numOfHuanGang;
	}

	public void setNumOfHuanGang(int numOfHuanGang) {
		this.numOfHuanGang = numOfHuanGang;
	}

	public int getNumOfZhuanZheng() {
		return numOfZhuanZheng;
	}

	public void setNumOfZhuanZheng(int numOfZhuanZheng) {
		this.numOfZhuanZheng = numOfZhuanZheng;
	}

	public int getNumOfTuiXiu() {
		return numOfTuiXiu;
	}

	public void setNumOfTuiXiu(int numOfTuiXiu) {
		this.numOfTuiXiu = numOfTuiXiu;
	}

	public int getNumOfTiaoZheng() {
		return numOfTiaoZheng;
	}

	public void setNumOfTiaoZheng(int numOfTiaoZheng) {
		this.numOfTiaoZheng = numOfTiaoZheng;
	}

}
