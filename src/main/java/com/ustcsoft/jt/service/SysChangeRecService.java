package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;

/**
 * 人员异动的服务接口
 * @author  吴金华
 * @since   2017年1月10日
 */
public interface SysChangeRecService {
	/**
	 * 分页查询所有人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRecVO
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysChangeRecVO> query(SysChangeRecVO changeRec, int page, int rows);
	
	/**
	 * 分页查询所有人员异动申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRecVO
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysChangeRecVO> queryApplyList(SysChangeRecVO changeRec, int page, int rows);
	
	
	/**
	 * 录入一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param sysChangeRec
	 * @param user
	 * @return
	 */
	AjaxObj record(SysChangeRecVO sysChangeRec, UserVO user);
	
	/**
	 * 申请一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param sysChangeRec
	 * @param user
	 * @return
	 */
	AjaxObj apply(SysChangeRecVO sysChangeRec, UserVO user);
	
	/**
	 * 根据id删除人员异动申请（以,分割）
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj deleteChangeRec(String id);
	
	/**
	 * 加载一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	SysChangeRecVO load(String id);
	
	/**
	 * 加载部门名称
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	SysChangeRecVO loadDeptName(String id);
	
	/**
	 * 提交人员异动申请单
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj submitChangeRec(String id);
	
	/**
	 * 修改人员异动申请单
	 * 
	 */
	AjaxObj updateChangeRec(SysChangeRecVO sysChangeRec);
	
}
