package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ustcsoft.jt.service.CadreAppointRecordService;
@RequestMapping("sysCadre")
@Controller
public class CadreApplyController extends AbstractRestController{
	@Resource
	private CadreAppointRecordService cadreAppointApplyService;
	/**
	 * 干部任命申请初始化
	 */
	@RequestMapping("cardeAppointRecordInit.do")
	public String cardeAppointApplyInit() {
		logger.info("sysCadre/cardeAppointRecordInit.do");
		return "system/cadreAppointRecord";
	}
	
	/**
	 * 干部任命申信息打印
	 */
	@RequestMapping("sysCadre/sysCadrePrint.do")
	public String sysCadrePrint() {
		logger.info("sysCadre/sysCadrePrint.do");
		return "system/print";
	}
	
	/**
	 * 干部考评初始化
	 */
	@RequestMapping("cadreCheckInit.do")
	public String cadreCheckInit() {
		logger.info("sysCadre/cadreCheckInit.do");
		return "system/cadreCheck";
	}
	
	/**
	 * 后备干部初始化
	 */
	@RequestMapping("cadreReserveInit.do")
	public String cadreReserveInit() {
		logger.info("sysCadre/cadreReserveInit.do");
		return "system/cadreReserve";
	}
	
	/**
	 * 职称编码管理初始化
	 */              
	@RequestMapping("sysPostionTypeManageInit.do")
	public String sysPostionTypeManageInit() {
		logger.info("sysCadre/sysPostionTypeManageInit.do");
		return "system/sysPositionTypeManager";
	}
	
	/**
	 * 职称申请初始化
	 */              
	@RequestMapping("positionApplyInit.do")
	public String positionApplyInit() {
		logger.info("sysCadre/positionApplyInit.do");
		return "system/positionApply";
	}
	
	/**
	 * 职称培训记录初始化
	 */              
	@RequestMapping("positionTrainRecordInit.do")
	public String positionTrainRecordInit() {
		logger.info("sysCadre/positionTrainRecordInit.do");
		return "system/positionTrainRecord";
	}
}
