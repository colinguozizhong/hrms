package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 离退休人员基本信息控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("sysLeaveEmp")
@Controller
public class SysLeaveEmpController extends AbstractRestController {
	/**
	 * 跳转到 离退休人员基本信息的主页面
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @return
	 */
	@RequestMapping("index.do")
	public String frameJsp() {
		logger.info("sysLeaveEmp/index.do");
		return "system/sysLeaveEmpManager";
	}
}
