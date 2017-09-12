package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 人员异动类型的控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("sysChangeType")
@Controller
public class SysChangeTypeController extends AbstractRestController {
	/**
	 * 跳转到人员异动类型管理的主页面
	 * @author 吴金华
	 * @since 2017年1月9日
	 * @return
	 */
	@RequestMapping("index.do")
	public String frameJsp() {
		logger.info("sysChangeType/index.do");
		return "system/sysChangeTypeManager";
	}
}
