package com.ustcsoft.jt.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStudyTypeService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SystemStudyTypeVO;

/**
 * 学历类型rest控制器
 * @author  吴金华
 * @since   2017年1月5日
 */
@RequestMapping("studyType")
@RestController
public class SystemStudyTypeRestController extends AbstractRestController {
	@Resource
	private SysStudyTypeService sysStudyTypeService;
	
	/**
	 * 所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	@RequestMapping("all.do")
	public List<SystemStudyTypeVO> listAll() {
		return sysStudyTypeService.listAll();
	}
	
	/**
	 * 分页查询所有学历类型的列表
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("findStudyTypeList.do")
	Page<SystemStudyTypeVO> findStudyTypeList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		logger.info("studyType/findStationTypeList.do");

		// 初始化查询条件
		SystemStudyTypeVO studyType = new SystemStudyTypeVO();

		// 查询并返回
		return sysStudyTypeService.query(studyType, page, rows);
	}
	
	/**
	 * 加载一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("load.do")
	SystemStudyTypeVO load(String id) {
		return sysStudyTypeService.load(id);
	}
	
	/**
	 * 添加一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @return
	 */
	@RequestMapping("add.do")
	AjaxObj add(SystemStudyTypeVO studyType) {
		UserVO currentUser = this.getCurrentUser();
		studyType.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return sysStudyTypeService.add(studyType, currentUser);
	}
	
	/**
	 * 更新一条学历类型信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param studyType
	 * @return
	 */
	@RequestMapping("update.do")
	AjaxObj update(SystemStudyTypeVO studyType) {
		return sysStudyTypeService.update(studyType);
	}
	
	/**
	 * 根据id删除学历类型
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param studyTypeId
	 * @return
	 */
	@RequestMapping("deleteStudyType.do")
	AjaxObj deleteStudynType(@RequestParam(value = "studyTypeId")String studyTypeId) {
		return sysStudyTypeService.deleteStudyType(studyTypeId);
	}
}
