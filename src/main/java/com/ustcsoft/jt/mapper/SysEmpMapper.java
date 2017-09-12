package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.EmpFileVO;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SystemEmpVO;

/**
 * 人员基本信息的Mapper接口
 * @author  吴金华
 * @since   2017年1月4日
 */
public interface SysEmpMapper {
	/**
	 * 带分页的查询人员基本信息列表
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @param dep
	 * @param page
	 * @return
	 */
	List<SystemEmpVO> queryWithPage(@Param("emp") SystemEmpVO emp, @Param("page") Page<SystemEmpVO> page);
	
	/**
	 * 带分页的查询人员附件列表
	 * @author 谈健
	 * @since 2017年3月13日
	 * @param empFile
	 * @param page
	 * @return
	 */
	List<EmpFileVO> queryEmpFilePage(@Param("empFile") EmpFileVO empFile, @Param("page") Page<EmpFileVO> page);

	
	/**
	 * 添加一个人员基本信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param emp
	 */
	void add(SystemEmpVO emp);

	/**
	 * 删除人员基本信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param ids
	 */
	void deletes(List<String> ids);
	
	/**
	 * 删除id删除人员附件
	 * @author 谈健
	 * @since 2017年3月14日
	 * @param fileId
	 */
	void deleteFile(String fileId);
	
	/**
	 * 根据id查找人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param id
	 * @return
	 */
	SystemEmpVO load(String id);
	
	/**
	 * 根据id查找人员附件信息
	 * @author 吴金华
	 * @since 2017年3月16日
	 * @param fileId
	 * @return
	 */
	EmpFileVO getEmpFileByFileId(String fileId);
	
	/**
	 * 根据id查找人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param id
	 * @return
	 */
	PositionApplyVO loadPosAndSta(String id);
	
	/**
	 * 查询非在职人员的额外信息
	 * @author 吴金华
	 * @since 2017年3月24日
	 * @param id
	 */
	SysChangeRecVO getEmpExtraInfo(String id);
	
	/**
	 * 更新一个人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param emp
	 */
	void update(SystemEmpVO emp);
	
	/**
	 * 上传文件
	 * @author 谈健
	 * @since 2017年3月14日
	 * @param empFile
	 */
	void insertEmpFile(EmpFileVO empFile);
	/**
	 * 查询人员信息
	 * @param emp
	 * @return
	 */
	List<SystemEmpVO> queryWithList(@Param("emp") SystemEmpVO emp);
	/**
	 * 通过身份证号码查询人员信息
	 * @param cardid
	 * @return
	 */
	SystemEmpVO findEmpByCardId(String cardid);
	/**
	 * 根据ID删除人员信息
	 * @param emp
	 */
	void deleteEmpById(SystemEmpVO emp);

	String findOrgIdByOrgName(String unitName);
	
	
}
