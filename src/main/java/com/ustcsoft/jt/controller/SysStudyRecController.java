package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 学历控制器
 * @author  吴金华
 * @since   2017年1月11日
 */
@RequestMapping("studyRec")
@Controller
public class SysStudyRecController extends AbstractRestController {
	/**
	 * 跳转到学历管理的页面
	 * @author 吴金华
	 * @since 2017年1月11日
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		logger.info("studyRec/index.do");
		
		return "system/sysStudyRecManager";
	}
}
