package com.ustcsoft.jt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.jt.service.SysStationService;
import com.ustcsoft.system.model.SystemStationVO;

/**
 * 岗位类型的rest控制器
 * @author  吴金华
 * @since   2017年1月5日
 */
@RequestMapping("sysStation")
@RestController
public class SystemStationRestController extends AbstractRestController {
	@Resource
	private SysStationService sysStationService;
	
	/**
	 * 所有岗位类型的列表
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @return
	 */
	@RequestMapping("all.do")
	public List<SystemStationVO> listAll() {
		return sysStationService.listAll();
	}
}
