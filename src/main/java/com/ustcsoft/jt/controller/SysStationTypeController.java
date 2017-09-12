package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位类型控制器
 * @author  吴金华
 * @since   2017年1月10日
 */
@RequestMapping("sysStationType")
@Controller
public class SysStationTypeController extends AbstractRestController {
	/**
	 * 跳转到岗位类型管理界面
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		logger.info("sysStationType/index.do");
		
		return "system/sysStationTypeManager";
	}
}
