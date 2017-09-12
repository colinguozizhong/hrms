package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysStudyRecVO;

/**
 * 学历的Mapper接口
 * @author  吴金华
 * @since   2017年1月5日
 */
public interface SysStudyRecMapper {
	/**
	 * 带分页的查询学历信息列表
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param studyRec
	 * @param pageVO
	 * @return
	 */
	List<SysStudyRecVO> queryWithPage(@Param("study") SysStudyRecVO studyRec,
			@Param("page") Page<SysStudyRecVO> pageVO);
	
	/**
	 * 根据人员id来分页查询学历信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyRec
	 * @param pageVO
	 * @return
	 */
	List<SysStudyRecVO> queryByEmpidWithPage(@Param("study") SysStudyRecVO studyRec,
			@Param("page") Page<SysStudyRecVO> pageVO);
	
	/**
	 * 添加学历信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param studyRec
	 */
	void add(SysStudyRecVO studyRec);

	/**
	 * 根据人员id删除学历信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param empids
	 */
	void deleteByEmpids(List<String> empids);
	
	/**
	 * 根据学历信息ID删除相关对应人员的学历信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param empids
	 */
	void deleteByStudyRecIds(List<String> studyRecIds);
	
	/**
	 * 根据人员ID查找相关联的学历信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param empid
	 * @return
	 */
	List<SysStudyRecVO> loadByEmpid(String empid);

	/**
	 * 更新学历信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param studyRec
	 */
	void update(SysStudyRecVO studyRec);
	
	/**
	 * 更新人员一条学习经历
	 * @author 谈健
	 * @since 2017年3月17日
	 * @param studyRec
	 */
	void updateStudyRec(SysStudyRecVO studyRec);
	

}
