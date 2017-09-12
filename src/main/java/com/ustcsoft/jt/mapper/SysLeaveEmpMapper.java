package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysLeaveEmpVO;
import com.ustcsoft.system.model.SystemEmpVO;

/**
 * 离退休人员信息的Mapper接口
 * @author  吴金华
 * @since   2017年1月9日
 */
public interface SysLeaveEmpMapper {
		/**
		 * 带分页的查询离退休人员基本信息列表
		 * @author 吴金华
		 * @since 2017年1月9日
		 * @param emp
		 * @param page
		 * @return
		 */
		List<SysLeaveEmpVO> queryWithPage(@Param("leaveEmp") SysLeaveEmpVO emp, @Param("page") Page<SysLeaveEmpVO> page);
		
		/**
		 * 添加一个离退休人员
		 * @author 吴金华
		 * @since 2017年1月5日
		 * @param emp
		 */
		void insertLeaveEmp(SysLeaveEmpVO emp);
		
		/**
		 * 根据人员ID删除相关联的离退休人员信息
		 * @author 吴金华
		 * @since 2017年1月6日
		 * @param list
		 */
		void deleteByEmpids(List<String> list);

}
