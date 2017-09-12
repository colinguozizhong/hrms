package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SystemEmpVO;

/**
 * 人员异动的Mapper接口
 * @author  吴金华
 * @since   2017年1月10日
 */
public interface SysChangeRecMapper {
	/**
	 * 分页查询所有人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 * @param pageVO
	 * @return
	 */
	List<SysChangeRecVO> queryWithPage(@Param("changeRec") SysChangeRecVO changeRec,
			@Param("page") Page<SysChangeRecVO> pageVO);
	/**
	 * 分页查询所有人员异动申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 * @param pageVO
	 * @return
	 */
	List<SysChangeRecVO> queryApplyWithPage(@Param("changeRec") SysChangeRecVO changeRec,
			@Param("page") Page<SysChangeRecVO> pageVO);
	
	/**
	 * 添加一个人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	void add(SysChangeRecVO changeRec);

	/**
	 * 加载一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	SysChangeRecVO load(String id);
	
	
	/**
	 * 添加一个人员异动信息申请（head）
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	void insertChangeHead(SysChangeRecVO changeRec);
	
	/**
	 * 添加一个人员异动信息申请（detail）
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	void insertChangeDetail(SysChangeRecVO changeRec);
	/**
	 * 生成人员异动记录
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	void insertChangeRecord(SysChangeRecVO changeRec);
	/**
	 * 审核申请单更新表头信息（head）
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	void updateHeadInfo(SysChangeRecVO changeRec);
	
	/**
	 * 加载部门名称
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param changeRec
	 */
	SysChangeRecVO loadDeptName(String id);
	
	/**
	 * 根据Id删除人员异动申请(head表)
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param ids
	 */
	void deleteChangeRec(String headId);
	
	/**
	 * 根据Id删除人员异动申请(detail表)
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param ids
	 */
	void deleteChangeRecDetail(String headId);
	
	/**
	 * 提交人员异动申请
	 * @return
	 */
	void submitChangeRec(String headId);
	
	/**
	 * 修改人员异动申请
	 * @return                         
	 */
	void updateChangeRec(SysChangeRecVO changeRec);
	
	/**
	 * 审批异动申请单后更新对应的人员状态
	 * @return                         
	 */
	void updateEmpStatus(SystemEmpVO emp);
	/**
	 * 根据EmpId查询异动单号
	 * @param list
	 * @return
	 */
	List<String> findChangeHeadId(List<String> list);
	/**
	 * 在根据异动单号删除人员异动申请信息（head表）
	 * @param listC
	 */
	void deleteChangeRecByList(List<String> list);
	/**
	 * 根据EmpId删除人员异动申请信息（detail表）
	 * @param list
	 */
	void deleteChangeRecDetailByEmpId(List<String> list);
	
}
