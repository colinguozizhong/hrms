package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStudyRecService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SysStudyRecVO;

/**
 * 学历rest控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("studyRec")
@RestController
public class SystemStudyRecRestController extends AbstractRestController {
	@Resource
	private SysStudyRecService sysStudyRecService;
	
	/**
	 * 分页查询所有学历信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param empId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("findStudyRecList.do")
	public Page<SysStudyRecVO> findStudyRecList(String empId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		logger.info("studyRec/findStudyRecList.do");

		// 初始化查询条件
		SysStudyRecVO studyRec = new SysStudyRecVO();
		studyRec.setEmpId(empId);

		// 查询并返回
		return sysStudyRecService.query(studyRec, page, rows);
	}
	
	/**
	 * 根据人员id加载最新一条学历信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param empId
	 * @return
	 */
	@RequestMapping("lastByEmpid.do")
	public SysStudyRecVO lastByEmpid(String empId) {
		return sysStudyRecService.lastByEmpid(empId);
	}
	
	/**
	 * 添加学历信息
	 * @author 吴金华
	 * @since 2017年1月12日
	 * @param studyRec
	 * @return
	 */
	@RequestMapping("add.do")
	public AjaxObj add(SysStudyRecVO studyRec) {
		UserVO user = this.getCurrentUser();
		return sysStudyRecService.add(studyRec, user);
	}
	
	/**
	 * 更新一条学历信息
	 * @author 谈健
	 * @since 2017年3月17日
	 * @param studyRec
	 * @return
	 */
	@RequestMapping("updateStudyRec.do")
	AjaxObj updateStudyRec(SysStudyRecVO studyRec) {
//		UserVO currentUser = this.getCurrentUser();
		return sysStudyRecService.updateStudyRec(studyRec);
	}
	
	@RequestMapping("delete.do")
	public AjaxObj delete(String id) {
		return sysStudyRecService.delete(id);
	}
}
