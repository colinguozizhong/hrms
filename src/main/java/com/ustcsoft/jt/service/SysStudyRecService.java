package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysStudyRecVO;

/**
 * 学历的服务接口
 * @author  吴金华
 * @since   2017年1月5日
 */
public interface SysStudyRecService {

	/**
	 * 分页查询所有学历信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param studyRec
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysStudyRecVO> query(SysStudyRecVO studyRec, int page, int rows);

	/**
	 * 根据人员id加载最新一条学历信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param empId
	 * @return
	 */
	SysStudyRecVO lastByEmpid(String empId);

	/**
	 * 添加一条学历信息
	 * @author 吴金华
	 * @since 2017年1月12日
	 * @param studyRec
	 * @param user
	 * @return
	 */
	AjaxObj add(SysStudyRecVO studyRec, UserVO user);
	
	/**
	 * 根据id删除人员基本信息（以,分割）
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj delete(String id);
	
	/**
	 * 修改一条学历信息
	 * @author 谈健
	 * @since 2017年3月17日
	 * @param studyRec
	 * @param user
	 * @return
	 */
	AjaxObj updateStudyRec(SysStudyRecVO studyRec);
	

}
