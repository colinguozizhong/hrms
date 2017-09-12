package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SystemStudyTypeVO;

/**
 * 学历类型的Mapper接口
 * @author  吴金华
 * @since   2017年1月5日
 */
public interface SysStudyTypeMapper {
	/**
	 * 所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	public List<SystemStudyTypeVO> listAll();

	/**
	 * 分页查询所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @param pageVO
	 * @return
	 */
	public List<SystemStudyTypeVO> queryWithPage(@Param("studyType") SystemStudyTypeVO studyType,
			@Param("page") Page<SystemStudyTypeVO> pageVO);

	/**
	 * 加载一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	public SystemStudyTypeVO load(String id);

	/**
	 * 添加一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 */
	public void add(SystemStudyTypeVO studyType);

	/**
	 * 更新一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 */
	public void update(SystemStudyTypeVO studyType);
	
	/**
	 * 根据Id删除学历类型信息
	 * @author 谈健
	 * @since 2017年3月13日
	 * @return
	 */
	void deleteStudyType(List<String> studyTypeId);
}
