package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysStationTypeVO;

/**
 * 岗位类型的Mapper接口
 * @author  吴金华
 * @since   2017年1月10日
 */
public interface SysStationTypeMapper {

	/**
	 * 分页查询所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @param pageVO
	 * @return
	 */
	List<SysStationTypeVO> queryWithPage(@Param("stationType") SysStationTypeVO stationType,
			@Param("page") Page<SysStationTypeVO> pageVO);

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
	 */
	void add(SysStationTypeVO stationType);

	/**
	 * 更新一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 */
	void update(SysStationTypeVO stationType);

	/**
	 * 根据id删除岗位类型
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param list
	 */
	void deletes(List<String> list);

}
