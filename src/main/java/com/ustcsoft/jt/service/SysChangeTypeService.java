package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysChangeTypeVO;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 人员异动类型的服务接口
 * @author  吴金华
 * @since   2017年1月9日
 */
public interface SysChangeTypeService {

	/**
	 * 分页查询所有人员异动类型
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SysChangeTypeVO> query(SysChangeTypeVO changeType, int page, int rows);

	/**
	 * 查询所有异动类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @return
	 */
	List<SystemStationVO> listAll();
	
	/**
	 * 增加人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param currentUser
	 * @return
	 */
	AjaxObj add(SysChangeTypeVO changeType, UserVO currentUser);
	
	/**
	 * 更新人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param currentUser
	 * @return
	 */
	AjaxObj update(SysChangeTypeVO changeType, UserVO currentUser);

	/**
	 * 删除人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param id
	 * @return
	 */
	AjaxObj delete(String id);
	
	/**
	 * 加载一条人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param typeId
	 * @return
	 */
	SysChangeTypeVO load(String typeId);

}
