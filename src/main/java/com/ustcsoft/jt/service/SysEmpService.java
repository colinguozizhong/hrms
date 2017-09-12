package com.ustcsoft.jt.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.EmpFileVO;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SystemEmpVO;

/**
 * 人员基本信息的服务接口
 * @author  吴金华
 * @since   2017年1月4日
 */
public interface SysEmpService {
	/**
	 * 分页查询所有人员基本信息
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @param emp
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<SystemEmpVO> query(SystemEmpVO emp, int page, int rows);
	
	/**
	 * 分页查询所有人员对应附件
	 * @author 吴金华
	 * @since 2017年3月13日
	 * @param empId
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<EmpFileVO> queryEmpFile(EmpFileVO empFile, int page, int rows);
	
	/**
	 * 添加人员基本信息
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param emp 要添加的人员信息
	 * @param user 当前登录的用户
	 * @return
	 */
	AjaxObj add(SystemEmpVO emp, UserVO user);

	/**
	 * 更新人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param emp
	 * @return
	 */
	AjaxObj update(SystemEmpVO emp);
	
	/**
	 * 根据id删除人员基本信息（以,分割）
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj delete(String id);
	
	/**
	 * 根据id删除人员附件
	 * @author 谈健
	 * @since 2017年3月14日
	 * @param fileId
	 * @return
	 */
	AjaxObj deleteFile(String fileId);
	
//	/**
//	 * 根据id下载人员附件
//	 * @author 谈健
//	 * @since 2017年3月14日
//	 * @param fileId
//	 * @return
//	 */
//	AjaxObj downloadFile(String fileId);
	
	
	/**
	 * 加载一条带学历信息的人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param id
	 * @return
	 */
	SystemEmpVO loadWithStudy(String id);

	/**
	 * 加载一条人员基本信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	SystemEmpVO load(String id);
	
	/**
	 * 加载非在职人员的额外信息
	 * @author 谈健
	 * @since 2017年3月24日
	 * @param id
	 * @return
	 */
	SysChangeRecVO getEmpExtraInfo(String id);
	
	/**
	 * 加载一条人员附件基本信息
	 * @author 吴金华
	 * @since 2017年3月16日
	 * @param fileId
	 * @return
	 */
	EmpFileVO getEmpFileByFileId(String fileId);
	
	/**
	 * 加载一条人员岗位和职称
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	PositionApplyVO loadPosAndSta(String id);
	
	EmpFileVO upLoadFile(InputStream inputStream,EmpFileVO empFile);
	
    int insertFile(MultipartFile file,String fileName,EmpFileVO empFile);
    /**
     * 查询人员信息列表
     * @param dep
     * @return
     */
	List<SystemEmpVO> queryWithList(SystemEmpVO emp);
	/** 
     * 读取excel中的数据,生成list 
	 * @param currentUser 
     */ 
	String readExcelFile(MultipartFile file, UserVO currentUser);
}
