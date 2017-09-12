package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SystemStudyTypeVO;

/**
 * 学历类型的服务接口
 * @author  吴金华
 * @since   2017年1月5日
 */
public interface SysStudyTypeService {

	/**
	 * 所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	List<SystemStudyTypeVO> listAll();

	/**
	 * 分页查询所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SystemStudyTypeVO> query(SystemStudyTypeVO studyType, int page,
			int rows);

	/**
	 * 加载一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	SystemStudyTypeVO load(String id);

	/**
	 * 添加一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @param currentUser
	 * @return
	 */
	AjaxObj add(SystemStudyTypeVO studyType, UserVO currentUser);

	/**
	 * 更新一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @return
	 */
	AjaxObj update(SystemStudyTypeVO studyType);
	
	/**
	 * 根据id删除学历类型
	 * @author 谈健
	 * @since 2017年3月13日
	 * @param studyTypeId
	 * @return
	 */
	AjaxObj deleteStudyType(String studyTypeId);
}
