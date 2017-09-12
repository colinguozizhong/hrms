package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysStationRecService;
import com.ustcsoft.system.model.SystemStationRecVO;

/**
 * 岗位的rest控制器
 * @author  吴金华
 * @since   2017年1月10日
 */
@RequestMapping("sysStationRec")
@RestController
public class SystemStationRecRestController extends AbstractRestController {
	@Resource
	private SysStationRecService sysStationRecService;
	
	/**
	 * 分页查询所有岗位信息
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @return
	 */
	@RequestMapping("findStationRecList.do")
	public Page<SystemStationRecVO> findStationRecList(String empId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows) {
		logger.info("sysStationRec/findStationRecList.do");

		// 初始化查询条件
		SystemStationRecVO stationRec = new SystemStationRecVO();
		stationRec.setEmpId(empId);

		// 查询并返回
		return sysStationRecService.query(stationRec, page, rows);
	}
	
	/**
	 * 加载一条岗位信息
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	@RequestMapping("load.do")
	public SystemStationRecVO load(String id) {
		return sysStationRecService.load(id);
	}
}
