package com.ustcsoft.jt.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStationTypeService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysStationTypeVO;

/**
 * 岗位类型的rest控制器
 * @author  吴金华
 * @since   2017年1月10日
 */
@RequestMapping("sysStationType")
@RestController
public class SysStationTypeRestController extends AbstractRestController {
	@Resource
	private SysStationTypeService sysStationTypeService;
	
	/**
	 * 分页查询所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("findStationTypeList.do")
	Page<SysStationTypeVO> findStationTypeList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		logger.info("sysStationType/findChangeTypeList.do");

		// 初始化查询条件
		SysStationTypeVO stationType = new SysStationTypeVO();

		// 查询并返回
		return sysStationTypeService.query(stationType, page, rows);
	}
	
	/**
	 * 加载一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("load.do")
	SysStationTypeVO load(String id) {
		return sysStationTypeService.load(id);
	}
	
	/**
	 * 添加一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	@RequestMapping("add.do")
	AjaxObj add(SysStationTypeVO stationType) {
		UserVO currentUser = this.getCurrentUser();
		stationType.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		return sysStationTypeService.add(stationType, currentUser);
	}
	
	/**
	 * 更新一条岗位类型信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param stationType
	 * @return
	 */
	@RequestMapping("update.do")
	AjaxObj update(SysStationTypeVO stationType) {
		return sysStationTypeService.update(stationType);
	}
	
	/**
	 * 根据id删除岗位类型
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("delete.do")
	AjaxObj delete(String id) {
		return sysStationTypeService.delete(id);
	}
}
