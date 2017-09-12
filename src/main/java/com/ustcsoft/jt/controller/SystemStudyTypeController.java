package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学历类型控制器
 * @author  吴金华
 * @since   2017年1月11日
 */
@RequestMapping("studyType")
@Controller
public class SystemStudyTypeController extends AbstractRestController {
	/**
	 * 跳转到学历类型的主页面
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		logger.info("sysLeaveEmp/index.do");
		return "system/sysStudyTypeManager";
	}
}
