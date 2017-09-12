package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 人员异动信息的控制器
 * @author  吴金华
 * @since   2017年1月9日
 */
@RequestMapping("sysChangeRec")
@Controller
public class SysChangeRecController extends AbstractRestController {
	/**
	 * 跳转到 人员异动信息管理的主页面
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @return
	 */
	@RequestMapping("index.do")
	public String frameJsp() {
		logger.info("sysChangeRec/index.do");
		
		return "system/sysChangeRecManager";
	}
	
	/**
	 * 跳转到 人员异动信息记录的主页面
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @return
	 */
	@RequestMapping("records.do")
	public String records() {
		logger.info("sysChangeRec/records.do");
		
		return "system/sysChangeRecRecordManager";
	}
}
