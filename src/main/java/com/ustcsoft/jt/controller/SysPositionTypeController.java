package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 职称编码管理控制器
 * @author  谈健
 * @since   2017年1月11日
 */
@RequestMapping("sysPositonType")
@Controller
public class SysPositionTypeController extends AbstractRestController {
	/**
	 * 跳转到职称类型管理界面
	 * @author 谈健
	 * @since 2017年1月11日
	 * @return
	 */
	@RequestMapping("sysPositonTypeInit.do")
	public String sysPositonTypeInit() {
		logger.info("sysPositonType/sysPositonTypeInit.do");
		return "system/sysPositionTypeManager";
	}
}
