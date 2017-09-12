package com.ustcsoft.jt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysChangeTypeService;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.SysChangeTypeVO;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 人员异动类型的rest控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("sysChangeType")
@RestController
public class SysChangeTypeRestController extends AbstractRestController {
	@Resource
	private SysChangeTypeService sysChangeTypeService;
	
	/**
	 * 分页查询所有人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param deptNO
	 * @param status
	 * @param name
	 * @param job
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findChangeTypeList.do")
	public Page<SysChangeTypeVO> findChangeTypeList(String leaveType, String name,  String phone,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysChangeType/findChangeTypeList.do");

		// 初始化查询条件
		SysChangeTypeVO changeType = new SysChangeTypeVO();

		// 查询并返回
		return sysChangeTypeService.query(changeType, page, rows);
	}
	
	/**
	 * 所有异动类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	@RequestMapping("all.do")
	public List<SystemStationVO> listAll() {
		return sysChangeTypeService.listAll();
	}
	
	/**
	 * 增加人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @return
	 */
	@RequestMapping("add.do")
	public AjaxObj add(SysChangeTypeVO changeType) {
		UserVO currentUser = this.getCurrentUser();
		return sysChangeTypeService.add(changeType, currentUser);
	}
	
	/**
	 * 更新人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @return
	 */
	@RequestMapping("update.do")
	public AjaxObj update(SysChangeTypeVO changeType) {
		UserVO currentUser = this.getCurrentUser();
		return sysChangeTypeService.update(changeType, currentUser);
	}
	
	/**
	 * 删除人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @return
	 */
	@RequestMapping("deleteChangeType.do")
	public AjaxObj deleteChangeType(String typeId) {
		return sysChangeTypeService.delete(typeId);
	}
	
	/**
	 * 加载一条人员异动类型信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @param changeType
	 * @return
	 */
	@RequestMapping("load.do")
	public SysChangeTypeVO load(String typeId) {
		return sysChangeTypeService.load(typeId);
	}
}
