package com.ustcsoft.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位的控制器
 * @author  吴金华
 * @since   2017年1月6日
 */
@RequestMapping("sysStationRec")
@Controller
public class SystemStationRecController extends AbstractRestController {
	/**
	 * 跳转到人员岗位管理页面
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @return
	 */
	@RequestMapping("index.do")
	public String index() {
		logger.info("sysStationRec/index.do");
		return "system/sysStationRecManager";
	}
	
	// TODO 人员岗位管理
}
