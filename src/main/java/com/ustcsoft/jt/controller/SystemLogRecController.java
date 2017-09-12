package com.ustcsoft.jt.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ustcsoft.jt.service.SysLogManageService;
import com.ustcsoft.system.model.SysLogManageVO;

/**
 * 系统日志的控制器
 * @author  谈健
 * @since   2017年3月8日
 */
@RequestMapping("sysLog")
@Controller
public class SystemLogRecController extends AbstractRestController {
	@Resource
	private SysLogManageService sysLogManageService;
	/**
	 * 跳转到系统日志管理页面
	 * @author 谈健
	 * @since 2017年3月8日
	 * @return
	 */
	@RequestMapping("sysLogManageInit.do")
	public String index() {
		logger.info("sysLog/sysLogManageInit.do");
		return "system/sysLogManager";
	}
	
	/**
	 * 导出系统日志并下载
	 * 
	 * @author 宋真真
	 * @since 2017年3月29日
	 * @param operatorName
	 * @param operateType
	 * @param operateMenu
	 * @param searchDateStart
	 * @param searchDateEnd
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "exportExcel.do")
	public void exportExcel(String operatorName, String operateType,
			String operateMenu, String searchDateStart, String searchDateEnd,
			HttpServletRequest request, HttpServletResponse resp)
			throws UnsupportedEncodingException {
		logger.info("sysLog/exportExcel.do");
		// 初始化查询条件
		SysLogManageVO sysLogManage = new SysLogManageVO();
		sysLogManage.setOperatorName(operatorName);
		sysLogManage.setOperateType(operateType);
		sysLogManage.setOperateMenu(operateMenu);
		sysLogManage.setSearchDateStart(searchDateStart);
		sysLogManage.setSearchDateEnd(searchDateEnd);
		// 查询并返回
		List<SysLogManageVO> logs = sysLogManageService.find(sysLogManage);

		HSSFWorkbook wb = new HSSFWorkbook();
		request.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");

		String fileName = "系统日志.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		resp.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		HSSFSheet sheet = wb.createSheet("系统日志记录");
		sheet.setDefaultRowHeight((short) (2 * 256));
		sheet.setColumnWidth(0, 50 * 160);
		HSSFFont font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		HSSFRow row = sheet.createRow((int) 0);
		sheet.createRow((int) 1);
		sheet.createRow((int) 2);
		sheet.createRow((int) 3);
		sheet.createRow((int) 4);
		sheet.createRow((int) 5);
		sheet.createRow((int) 6);
		sheet.createRow((int) 7);
		sheet.createRow((int) 8);
		sheet.createRow((int) 9);
		sheet.createRow((int) 10);
		sheet.createRow((int) 11);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("操作人");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("操作模块");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("操作事项");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("浏览器");
		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("操作时间");
		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("操作详细");

		for (int i = 0; i < logs.size(); i++) {
			HSSFRow row1 = sheet.createRow((int) i + 1);
			SysLogManageVO log = logs.get(i);
			row1.createCell(0).setCellValue(i + 1);
			row1.createCell(1).setCellValue(log.getOperatorName());// 操作人
			row1.createCell(2).setCellValue(log.getOperateMenu());// 操作模块
			row1.createCell(3).setCellValue(log.getOperateType());// 操作事项
			row1.createCell(4).setCellValue(log.getBrowser());// 浏览器
			row1.createCell(5).setCellValue(StringUtils.isNotBlank(log.getCreateTime()) ? log.getCreateTime().replace(".0", ""): "");// 创建时间
			row1.createCell(6).setCellValue(log.getOperateContent());// 操作详细
		}

		try {
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e1) {
			logger.info("=====导出excel异常====");
		}
	}
}
