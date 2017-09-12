package com.ustcsoft.jt.constants;

/**
 * 常量集
 * @author  吴金华
 * @since   2017年1月5日
 */
public final class Constants {
	// 人员状态
	/**
	 * 在职
	 */
	public static final String EMP_SUTUS_ONLINE = "0";
	/**
	 * 离职
	 */
	public static final String EMP_SUTUS_OFFLINE = "1";
	/**
	 * 辞退
	 */
	public static final String EMP_SUTUS_FIRED = "2";
	/**
	 * 离退休
	 */
	public static final String EMP_SUTUS_RETIRED = "3";
	/**
	 * 试用期
	 */
	public static final String EMP_SUTUS_PROBATION = "4";
	/**
	 * 不在职
	 */
	public static final String EMP_SUTUS_OUTSERVICE = "5";
	//职称申请状态
	/**
	 * 已保存
	 */
	public static final String POSITION_APPLY_STATUS_SAVE = "0";
	/**
	 * 已提交
	 */
	public static final String POSITION_APPLY_STATUS_SUBMIT = "1";
	/**
	 * 已审核
	 */
	public static final String POSITION_APPLY_STATUS_VERIFY = "2";

	//后备干部申请状态
	/**
	 * 已保存
	 */
	public static final String CADRE_RESERVE_APPLY_STATUS_SAVE = "0";
	/**
	 * 已提交
	 */
	public static final String CADRE_RESERVE_APPLY_STATUS_SUBMIT = "1";
	/**
	 * 已审批
	 */
	public static final String CADRE_RESERVE_APPLY_STATUS_APPROVE = "2";
	/**
	 * 不予审批
	 */
	public static final String CADRE_RESERVE_APPLY_STATUS_UNPASS = "3";
	
	//异动申请状态
	/**
	 * 已保存
	 */
	public static final String PERSON_CHANGE_STATUS_SAVE = "0";
	/**
	 * 已提交
	 */
	public static final String PERSON_CHANGE_STATUS_SUBMIT = "1";
	/**
	 * 审批通过
	 */
	public static final String PERSON_CHANGE_STATUS_PASS = "2";
	/**
	 * 审批未过
	 */
	public static final String PERSON_CHANGE_STATUS_UNPASS = "3";
}
