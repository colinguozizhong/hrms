package com.ustcsoft.jt.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysPositionTypeService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SysProfessionTypeVO;

/**
 * 职称类型的rest控制器
 * @author  谈健
 * @since   2017年1月11日
 */
@RequestMapping("sysPositionType")
@RestController
public class SysPositionTypeRestController extends AbstractRestController {
	@Resource
	private SysPositionTypeService sysPositionTypeService;
	
	/**
	 * 分页查询所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("findPositionTypeList.do")
	Page<SysPositionTypeVO> findPositionTypeList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		logger.info("sysPositionType/findPositionTypeList.do");
		// 初始化查询条件
		SysPositionTypeVO positionType = new SysPositionTypeVO();
		// 查询并返回
		return sysPositionTypeService.query(positionType, page, rows);
	}
	
	/**
	 * 新增职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	@RequestMapping("addPositionType.do")
	AjaxObj add(SysPositionTypeVO positionType) {
		logger.info("sysPositionType/addPositionType.do");
		UserVO currentUser = this.getCurrentUser();
		return sysPositionTypeService.addPositionType(positionType, currentUser);
	}
	
	/**
	 * 更新一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	@RequestMapping("updatePositionType.do")
	AjaxObj updatePositionType(SysPositionTypeVO positionType) {
		UserVO currentUser = this.getCurrentUser();
		logger.info("sysPositionType/updatePositionType.do");
		return sysPositionTypeService.updatePositionType(positionType,currentUser);
	}
	
	/**
	 * 更新一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param sysPositionType
	 * @return
	 */
	@RequestMapping("checkPositionId.do")
	
	AjaxObj checkPositionId(@RequestParam(value = "positionId") String positionId) {
		logger.info("sysPositionType/checkPositionId.do");
		return sysPositionTypeService.checkPositionId(positionId);
	}
	
	
	/**
	 * 所有职称类型的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	@RequestMapping("loadPositionType.do")
	public List<SysPositionTypeVO> listAll() {
		return sysPositionTypeService.listAll();
	}
	
	/**
	 * 加载一条职称类型信息
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	@RequestMapping("loadPositionTypeInformation.do")
	SysPositionTypeVO loadPositionTypeInformation(String positionId) {
		return sysPositionTypeService.loadPositionTypeInformation(positionId);
	}
	
	/**
	 * 根据id删除职称类型
	 * @author 谈健
	 * @since 2017年1月12日
	 * @param positionId
	 * @return
	 */
	@RequestMapping("deletePositionType.do")
	AjaxObj deletePositionType(String positionId) {
		return sysPositionTypeService.deletePositionType(positionId);
	}
	
	
	/**
	 * 所有专业的列表
	 * @author 谈健
	 * @since 2017年1月12日
	 * @return
	 */
	@RequestMapping("loadProfession.do")
	public List<SysProfessionTypeVO> loadProfession() {
		return sysPositionTypeService.loadProfession();
	}
}
