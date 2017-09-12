package com.ustcsoft.jt.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysEmpService;
import com.ustcsoft.jt.service.SysPositionTypeService;
import com.ustcsoft.jt.service.SysStationService;
import com.ustcsoft.jt.service.SysStudyTypeService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.EmpFileVO;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;
import com.ustcsoft.system.model.SysPositionTypeVO;
import com.ustcsoft.system.model.SystemEmpVO;
import com.ustcsoft.system.model.SystemStationVO;
import com.ustcsoft.system.model.SystemStudyTypeVO;

/**
 * 人员基本信息的rest控制器
 * 
 * @author 吴金华
 * @since 2017年1月4日
 */
@RequestMapping("sysEmp")
@RestController
public class SystemEmpRestController extends AbstractRestController {
	@Resource
	private SysEmpService sysEmpService;
	@Resource
	private SysStationService sysStationService;
	@Resource
	private SysPositionTypeService sysPositionTypeService;
	@Resource
	private SysStudyTypeService sysStudyTypeService;

	/**
	 * 分页查询所有人员基本信息
	 * 
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @param deptNO
	 *            所在单位
	 * @param name
	 *            姓名
	 * @param job
	 *            职务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findEmpList.do")
	public Page<SystemEmpVO> findEmpList(
			String deptNO, 
			String status, 
			String name,  
			String job,
			String entryDateStart, 
			String entryDateEnd,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysEmp/findEmpList.do");

		// 初始化查询条件
		SystemEmpVO dep = new SystemEmpVO();
		dep.setDeptNO(CommonUtils.getSearchId(deptNO));
		dep.setStatus(status);
		dep.setName(name);
		dep.setJob(job);
		dep.setEntryDateStart(entryDateStart);
		dep.setEntryDateEnd(entryDateEnd);
		if(deptNO==null){
			UserVO currentUser = this.getCurrentUser();
			dep.setDeptNO(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			dep.setDeptNO(CommonUtils.getSearchId(deptNO));
		}

		// 查询并返回
		return sysEmpService.query(dep, page, rows);
	}
	
	/**
	 * 分页查询所有人员基本信息
	 * 
	 * @author 吴金华
	 * @since 2017年1月4日
	 * @param deptNO
	 *            所在单位
	 * @param name
	 *            姓名
	 * @param job
	 *            职务
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findFileList.do")
	public Page<EmpFileVO> findFileList(
			String fileName,
			String searchDateStart,
			String searchDateEnd,
			@RequestParam(value = "empId") String empId,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysEmp/findFileList.do");
		
		// 初始化查询条件
		EmpFileVO empFile = new EmpFileVO();
		empFile.setEmpId(empId);
		empFile.setFileName(fileName);
		empFile.setSearchDateStart(searchDateStart);
		empFile.setSearchDateEnd(searchDateEnd);
		// 查询并返回
		return sysEmpService.queryEmpFile(empFile, page, rows);
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param fileName
	 * @param wenhao
	 * @param fabuRiqi
	 * @param leiBieName
	 * @param leibie
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="uploadFile.do",method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})  
	
	public String  fileUpload(
			@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value = "fileName") String fileName,
			@RequestParam(value = "empId") String empId,
			@RequestParam(value = "remark") String remark,
			HttpServletRequest request) throws IOException {
		logger.info("sysEmp/uploadFile.do");
		// 全文检索
		EmpFileVO empFile = new EmpFileVO();
		// 目录ID
		empFile.setCatalogid(93l);
		empFile.setFileName(fileName);
		empFile.setEmpId(empId);
		UserVO currentUser = this.getCurrentUser();
		empFile.setUploadPer(currentUser.getUserName());
		empFile.setFileName(fileName);
		empFile.setEmpId(empId);
		empFile.setRemark(remark);

		 sysEmpService.insertFile(file,fileName,empFile);
		 return "succ";
	}
	
	/**
	 * 加载一条带学历信息的人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @return
	 */
	@RequestMapping("loadWithStudy.do")
	public SystemEmpVO loadWithStudy(String id) {
		return sysEmpService.loadWithStudy(id);
	}
	
	/**
	 * 加载一条人员基本信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("load.do")
	public SystemEmpVO load(String id) {
		return sysEmpService.load(id);
	}
	
	/**
	 * 加载一条人员基本信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("loadPosAndSta.do")
	public PositionApplyVO loadPosAndSta(String id) {
		return sysEmpService.loadPosAndSta(id);
	}
	
	
	/**
	 * 增加人员基本信息
	 * 
	 * @author 吴金华
	 * @since 2017年1月5日
	 * @param emp
	 * @return
	 */
	@RequestMapping("add.do")
	public AjaxObj add(SystemEmpVO emp) {
		UserVO currentUser = this.getCurrentUser();
		return sysEmpService.add(emp, currentUser);
	}
	
	/**
	 * 更新人员基本信息
	 * @author 吴金华
	 * @since 2017年1月6日
	 * @param emp
	 * @return
	 */
	@RequestMapping("update.do")
	public AjaxObj update(SystemEmpVO emp) {
		return sysEmpService.update(emp);
	}
	
	@RequestMapping("delete.do")
	public AjaxObj delete(String id) {
		return sysEmpService.delete(id);
	}
	
	@RequestMapping("deleteFile.do")
	public AjaxObj deleteFile(String fileId) {
		return sysEmpService.deleteFile(fileId);
	}
	
	@RequestMapping("getEmpExtraInfo.do")
	public SysChangeRecVO getEmpExtraInfo(String id) {
		logger.info("sysEmp/getEmpExtraInfo.do");
		return sysEmpService.getEmpExtraInfo(id);
	}
	
	@RequestMapping("exportEmpDealxls.do")
	public void exportEmpDealxls(HttpServletRequest request,
			HttpServletResponse response,	
			String deptNO, 
			String status,  
			String name,
			String job) throws IOException, JRException {
		  String[] excelHeader = {"序号","姓名","性别", "身份证号", "出生日期", "出生地", "民族", "籍贯", "联系电话", "联系地址", "电子邮箱","岗位","职务", "职级","职称", "单位", "部门", "最高学历", "第一学历", "政治面貌", "入党（团）时间", "参加工作时间","入职时间", "员工状态", "操作标志"}; 
		  	HSSFWorkbook wb = new HSSFWorkbook();  
	        HSSFSheet sheet = wb.createSheet("留言信息"); 
	        HSSFRow row = sheet.createRow((int) 0);  
	        HSSFCellStyle titleStyle = wb.createCellStyle();  
	        HSSFFont font = wb.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setFontHeightInPoints((short) 12);
	        font.setFontName("宋体");
	        titleStyle.setFont(font);
	        titleStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
	        titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
	        titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
	        titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
	        titleStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex()); 
	        titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
	  
	        for (int i = 0; i < excelHeader.length; i++) {  
	            HSSFCell cell = row.createCell(i);  
	            cell.setCellValue(excelHeader[i]);  
	            cell.setCellStyle(titleStyle);  
	            sheet.autoSizeColumn(i); 
	            sheet.setColumnWidth(0, 10 * 256); 
	            sheet.setColumnWidth(1, 10 * 256); 
	            sheet.setColumnWidth(2, 10 * 256); 
	            sheet.setColumnWidth(3, 20 * 256); 
	            sheet.setColumnWidth(4, 20 * 256); 
	            sheet.setColumnWidth(5, 20 * 256); 
	            sheet.setColumnWidth(6, 20 * 256); 
	            sheet.setColumnWidth(7, 20 * 256); 
	            sheet.setColumnWidth(8, 20 * 256); 
	            sheet.setColumnWidth(9, 20 * 256); 
	            sheet.setColumnWidth(10, 20 * 256); 
	            sheet.setColumnWidth(11, 10 * 256); 
	            sheet.setColumnWidth(12, 20 * 256); 
	            sheet.setColumnWidth(13, 20 * 256); 
	            sheet.setColumnWidth(14, 10 * 256); 
	            sheet.setColumnWidth(15, 20 * 256); 
	            sheet.setColumnWidth(16, 20 * 256); 
	            sheet.setColumnWidth(17, 10 * 256); 
	            sheet.setColumnWidth(18, 10 * 256); 
	            sheet.setColumnWidth(19, 20 * 256); 
	            sheet.setColumnWidth(20, 20 * 256); 
	            sheet.setColumnWidth(21, 20 * 256); 
	            sheet.setColumnWidth(22, 10 * 256); 
	            sheet.setColumnWidth(23, 10 * 256); 
	            sheet.setColumnWidth(24, 10 * 256); 
	        }  
	        // 初始化查询条件
			SystemEmpVO emp = new SystemEmpVO();
			emp.setDeptNO(CommonUtils.getSearchId(deptNO));
			emp.setStatus(status);
			emp.setName(name);
			emp.setJob(job);
			if(("null").equals(deptNO) || !StringUtils.isNotEmpty(deptNO)){
				UserVO currentUser = this.getCurrentUser();
				emp.setDeptNO(CommonUtils.getSearchId(currentUser.getBusinessId()));
			}else{
				emp.setDeptNO(CommonUtils.getSearchId(deptNO));
			}
			List<SystemEmpVO> empList= sysEmpService.queryWithList(emp);
			 HSSFCellStyle bodyStyle = wb.createCellStyle();
				bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT); 
				bodyStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
				bodyStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
		        bodyStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
		        bodyStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex()); 
		        bodyStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框 
		        
		        HSSFFont bodyFont = wb.createFont();
		        bodyFont.setFontHeightInPoints((short) 10);
		        bodyFont.setFontName("宋体");
				bodyStyle.setFont(bodyFont );
	        for (int i = 0; i < empList.size(); i++) {  
	            row = sheet.createRow(i + 1);  
	            SystemEmpVO empVO = empList.get(i);  
	            row.createCell(0).setCellValue(i+1); 
	            row.getCell(0).setCellStyle(bodyStyle);
	            row.createCell(1).setCellValue(empVO.getName()); 
	            row.getCell(1).setCellStyle(bodyStyle);
	            row.createCell(2).setCellValue(empVO.getSex()); 
	            row.getCell(2).setCellStyle(bodyStyle);
	            row.createCell(3).setCellValue(empVO.getCardid()); 
	            row.getCell(3).setCellStyle(bodyStyle);
	            row.createCell(4).setCellValue(empVO.getBirthday().substring(0, 10)); 
	            row.getCell(4).setCellStyle(bodyStyle);
	            row.createCell(5).setCellValue(empVO.getBirthPlace()); 
	            row.getCell(5).setCellStyle(bodyStyle);
	            row.createCell(6).setCellValue(empVO.getNation()); 
	            row.getCell(6).setCellStyle(bodyStyle);
	            row.createCell(7).setCellValue(empVO.getNativePlace()); 
	            row.getCell(7).setCellStyle(bodyStyle);
	            row.createCell(8).setCellValue(empVO.getPhone()); 
	            row.getCell(8).setCellStyle(bodyStyle);
	            row.createCell(9).setCellValue(empVO.getAddress()); 
	            row.getCell(9).setCellStyle(bodyStyle);
	            row.createCell(10).setCellValue(empVO.getEmail()); 
	            row.getCell(10).setCellStyle(bodyStyle);
	            //岗位CODE
	            row.createCell(11).setCellValue(empVO.getStationId()); 
	            row.getCell(11).setCellStyle(bodyStyle);
	            row.createCell(12).setCellValue(empVO.getJob()); 
	            row.getCell(12).setCellStyle(bodyStyle);
	            row.createCell(13).setCellValue(empVO.getJobRank()); 
	            row.getCell(13).setCellStyle(bodyStyle);
	            //职称CODE
	            row.createCell(14).setCellValue(empVO.getPosition()); 
	            row.getCell(14).setCellStyle(bodyStyle);
	            //单位id
	            row.createCell(15).setCellValue(empVO.getUnitName()); 
	            row.getCell(15).setCellStyle(bodyStyle);
	            //部门id
	            row.createCell(16).setCellValue(empVO.getDeptName()); 
	            row.getCell(16).setCellStyle(bodyStyle);
	            //最高学历CODE
	            row.createCell(17).setCellValue(empVO.getEduRec()); 
	            row.getCell(17).setCellStyle(bodyStyle);
	          //第一学历CODE
	            row.createCell(18).setCellValue(empVO.getEduFirstRec()); 
	            row.getCell(18).setCellStyle(bodyStyle);
	            //政治面貌CODE
	            row.createCell(19).setCellValue(empVO.getpStatus()); 
	            row.getCell(19).setCellStyle(bodyStyle);
	            row.createCell(20).setCellValue(empVO.getEntryPartyDate().substring(0, 10)); 
	            row.getCell(20).setCellStyle(bodyStyle);
	            row.createCell(21).setCellValue(empVO.getWorkDate().substring(0, 10)); 
	            row.getCell(21).setCellStyle(bodyStyle);
	            row.createCell(22).setCellValue(empVO.getEntryDate().substring(0, 10)); 
	            row.getCell(22).setCellStyle(bodyStyle);
	            //员工状态code
	            row.createCell(23).setCellValue(empVO.getStatus()); 
	            row.getCell(23).setCellStyle(bodyStyle);
	            row.createCell(24).setCellValue(""); 
	            row.getCell(24).setCellStyle(bodyStyle);
	            
	            wb.createCellStyle(); 
	        } 
            //	 表格行数
	        int num = empList.size();
	        //岗位代码注释
	        List<SystemStationVO> stationList= sysStationService.listAll();
	        String beiZhu = "1、岗位编码名称对应：";
	        for(int i=0;i<stationList.size();i++){
	        	row = sheet.createRow(num + 1); 
	       	 	row.createCell(0).setCellValue("备注："); 
		        row.getCell(0).setCellStyle(bodyStyle);
	        	SystemStationVO stationVo = stationList.get(i);
	        	 beiZhu = beiZhu + stationVo.getStationCode() + "-" + stationVo.getStationName() + ";";
	        	 row.createCell(1).setCellValue(beiZhu); 
	 	         row.getCell(1).setCellStyle(bodyStyle);
	        }
	        //职称CODE注释
	        List<SysPositionTypeVO> positionList = sysPositionTypeService.listAll();
	        beiZhu = "2、职称编码名称对应：";
	        for(int i=0;i<stationList.size();i++){
	        	row = sheet.createRow(num + 2); 
	       	 	row.createCell(0).setCellValue(""); 
		        row.getCell(0).setCellStyle(bodyStyle);
		        SysPositionTypeVO position = positionList.get(i);
	        	 beiZhu = beiZhu + position.getPositionId() + "-" + position.getPositionName() + ";";
	        	 row.createCell(1).setCellValue(beiZhu); 
	 	         row.getCell(1).setCellStyle(bodyStyle);
	        }
	        //单位及部门ID名称对应;现在显示组织名称
	        //	     List<OrganizationVO> orgList =  sysOrgMapper.searchOrgList();
	        
	        //学历CODE注释
	        List<SystemStudyTypeVO> studyList = sysStudyTypeService.listAll();
	        beiZhu = "3、学历编码名称对应：";
	        for(int i=0;i<studyList.size();i++){
	        	row = sheet.createRow(num + 3); 
	       	 	row.createCell(0).setCellValue(""); 
		        row.getCell(0).setCellStyle(bodyStyle);
		        SystemStudyTypeVO study = studyList.get(i);
	        	 beiZhu = beiZhu + study.getStudyCode() + "-" + study.getStudyName() + ";";
	        	 row.createCell(1).setCellValue(beiZhu); 
	 	         row.getCell(1).setCellStyle(bodyStyle);
	        }
	        //政治面貌编码名称对应
	        beiZhu = "4、政治面貌编码名称对应：0-党员;1-团员;2-群众";
	        row = sheet.createRow(num + 4); 
       	 	row.createCell(0).setCellValue(""); 
	        row.getCell(0).setCellStyle(bodyStyle);
        	row.createCell(1).setCellValue(beiZhu); 
 	        row.getCell(1).setCellStyle(bodyStyle);
 	        //人员状态编码名称对应
	        beiZhu = "5、人员状态编码名称对应：0-在职;1-离职;2-辞退；3-离退休;4-试用期;5-不在职;";
	        row = sheet.createRow(num + 5); 
       	 	row.createCell(0).setCellValue(""); 
	        row.getCell(0).setCellStyle(bodyStyle);
        	row.createCell(1).setCellValue(beiZhu); 
 	        row.getCell(1).setCellStyle(bodyStyle);
 	        //组织及部门
	        beiZhu = "6、单位、部门名称必须与系统相同才能导入;单位名称不能为空;";
	        row = sheet.createRow(num + 6); 
       	 	row.createCell(0).setCellValue(""); 
	        row.getCell(0).setCellStyle(bodyStyle);
        	row.createCell(1).setCellValue(beiZhu); 
 	        row.getCell(1).setCellStyle(bodyStyle);
 	        //
	        beiZhu = "7、导入时操作标志：0:新增；1：修改；2：删除;";
	        row = sheet.createRow(num + 7); 
       	 	row.createCell(0).setCellValue(""); 
	        row.getCell(0).setCellStyle(bodyStyle);
        	row.createCell(1).setCellValue(beiZhu); 
 	        row.getCell(1).setCellStyle(bodyStyle);
 	        //
	        beiZhu = "8、导入数据时序号必填;";
	        row = sheet.createRow(num + 8); 
       	 	row.createCell(0).setCellValue(""); 
	        row.getCell(0).setCellStyle(bodyStyle);
        	row.createCell(1).setCellValue(beiZhu); 
 	        row.getCell(1).setCellStyle(bodyStyle);
 	       
	        //	HSSFWorkbook wb = studentExportService.export(list);  
	        response.setContentType("application/vnd.ms-excel");    
	        response.setHeader("Content-disposition", "attachment;filename=RYXX.xls");   
		    OutputStream ouputStream = response.getOutputStream();  
		    wb.write(ouputStream);  
		    ouputStream.flush();  
		    ouputStream.close();  
	}
	
	   @RequestMapping(value="daoRuExcel.do",method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})  
	    @ResponseBody  
	    public String  upload(@RequestParam(value="fileD",required = false)MultipartFile fileD,HttpServletRequest request, HttpServletResponse response){  
		   logger.info("sysEmp/daoRuExcel.do");
		   UserVO currentUser = this.getCurrentUser();
		   String result = sysEmpService.readExcelFile(fileD,currentUser);  
	        return result;  
	    } 
	   
}
