package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysChangeTypeVO;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 人员异动类型的Mapper接口
 * @author  吴金华
 * @since   2017年1月9日
 */
public interface SysChangeTypeMapper {

	/**
	 * 带分页的查询人员异动类型列表
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @param pageVO
	 * @return
	 */
	List<SysChangeTypeVO> queryWithPage(@Param("changeType") SysChangeTypeVO changeType,
			@Param("page") Page<SysChangeTypeVO> pageVO);

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
	 */
	void add(SysChangeTypeVO changeType);

	/**
	 * 更新人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 */
	void update(SysChangeTypeVO changeType);
	
	/**
	 * 加载一条人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param typeId
	 * @return
	 */
	SysChangeTypeVO load(String typeId);

	/**
	 * 根据id删除人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param list
	 */
	void deletes(List<String> list);

}
