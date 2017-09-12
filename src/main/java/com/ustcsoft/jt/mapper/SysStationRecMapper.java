package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 岗位的Mapper接口
 * @author  吴金华
 * @since   2017年1月6日
 */
public interface SysStationRecMapper {
	/**
	 * 带分页的查询岗位列表
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param stationRec
	 * @param pageVO
	 * @return
	 */
	List<SystemStationRecVO> queryWithPage(@Param("rec") SystemStationRecVO stationRec,
			@Param("page") Page<SystemStationRecVO> pageVO);

	/**
	 * 加载一条岗位信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	SystemStationRecVO load(String id);
	
	/**
	 * 添加一个岗位信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param stationRec
	 */
	void add(SystemStationRecVO stationRec);

	/**
	 * 更新一个岗位信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param stationRec
	 */
	void update(SystemStationRecVO stationRec);

	/**
	 * 根据人员ID删除相关联的岗位信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param list
	 */
	void deleteByEmpids(List<String> list);

}
