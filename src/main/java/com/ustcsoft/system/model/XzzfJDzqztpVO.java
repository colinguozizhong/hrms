package com.ustcsoft.system.model;

import java.util.Date;

import com.ustcsoft.framework.vo.BaseVO;

public class XzzfJDzqztpVO extends BaseVO {

	private static final long serialVersionUID = 7414972676050558043L;
	 /** 主键id */
    private String zhuJianId = null;

    /** 签章账号 */
    private String qianZhangHao = null;

    /** 签章密码 */
    private String qianZhangMiMa = null;

    /** 姓名 */
    private String xingMing = null;

    /** 执法证号 */
    private String zhiFaZhengHao = null;

    /** 图片名称 */
    private String tuPianMing = null;

    /** 图片地址 */
    private String tuPianDiZhi = null;

    /** 签章类别 */
    private String qianZhangLeiBie = null;

    /** 创建者 */
    private String creater = null;

    /** 创建时间 */
    private Date createtime = null;

    /** 更新者 */
    private String updater = null;

    /** 更新时间 */
    private Date updatetime = null;

    /** 更新次数 */
    private Long rowversion = null;

    /** 删除标记 */
    private String deleteflag = null;
    
    //电子签章所属人ID
    private String userDzqztp = null;

	public String getZhuJianId() {
		return zhuJianId;
	}

	public void setZhuJianId(String zhuJianId) {
		this.zhuJianId = zhuJianId;
	}

	public String getQianZhangHao() {
		return qianZhangHao;
	}

	public void setQianZhangHao(String qianZhangHao) {
		this.qianZhangHao = qianZhangHao;
	}

	public String getQianZhangMiMa() {
		return qianZhangMiMa;
	}

	public void setQianZhangMiMa(String qianZhangMiMa) {
		this.qianZhangMiMa = qianZhangMiMa;
	}

	public String getXingMing() {
		return xingMing;
	}

	public void setXingMing(String xingMing) {
		this.xingMing = xingMing;
	}

	public String getZhiFaZhengHao() {
		return zhiFaZhengHao;
	}

	public void setZhiFaZhengHao(String zhiFaZhengHao) {
		this.zhiFaZhengHao = zhiFaZhengHao;
	}

	public String getTuPianMing() {
		return tuPianMing;
	}

	public void setTuPianMing(String tuPianMing) {
		this.tuPianMing = tuPianMing;
	}

	public String getTuPianDiZhi() {
		return tuPianDiZhi;
	}

	public void setTuPianDiZhi(String tuPianDiZhi) {
		this.tuPianDiZhi = tuPianDiZhi;
	}

	public String getQianZhangLeiBie() {
		return qianZhangLeiBie;
	}

	public void setQianZhangLeiBie(String qianZhangLeiBie) {
		this.qianZhangLeiBie = qianZhangLeiBie;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Long getRowversion() {
		return rowversion;
	}

	public void setRowversion(Long rowversion) {
		this.rowversion = rowversion;
	}

	public String getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getUserDzqztp() {
		return userDzqztp;
	}

	public void setUserDzqztp(String userDzqztp) {
		this.userDzqztp = userDzqztp;
	}

}
