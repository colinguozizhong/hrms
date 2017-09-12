package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysStationTypeVO;

/**
 * 岗位类型的服务接口
 * @author  吴金华
 * @since   2017年1月10日
 */
public interface SysStationTypeService {

	/**
	 * 分页查询所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysStationTypeVO> query(SysStationTypeVO stationType, int page,
			int rows);

	/**
	 * 加载一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	SysStationTypeVO load(String id);

	/**
	 * 添加一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	AjaxObj add(SysStationTypeVO stationType, UserVO currentUser);

	/**
	 * 更新一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	AjaxObj update(SysStationTypeVO stationType);

	/**
	 * 根据id删除岗位类型
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	AjaxObj delete(String id);

}
