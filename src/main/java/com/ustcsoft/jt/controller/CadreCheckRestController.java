package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreCheckService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.CadreCheckVO;

/**
 * 干部考核的rest控制器
 * 
 * @author 谈健
 * @since 2017年1月8日
 */
@RequestMapping("cadreCheck")
@RestController
public class CadreCheckRestController extends AbstractRestController {
	@Resource
	private CadreCheckService cadreCheckService;

	/**
	 * 分页查询干部考核信息
	 * 
	 * 
	 */
	@RequestMapping("searchCadreCheckList.do")
	public Page<CadreCheckVO> searchCadreCheckList(
			String empName, 
			String dept, 
			String checkDateStart,  
			String checkDateEnd,
			String deptNO,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("cadreCheck/searchCadreCheck.do");
		// 初始化查询条件
		CadreCheckVO cadreCheck = new CadreCheckVO();
		cadreCheck.setEmpName(empName);
		cadreCheck.setDept(dept);
		cadreCheck.setCheckDateStart(checkDateStart);
		cadreCheck.setCheckDateEnd(checkDateEnd);
		if(deptNO==null){
			UserVO currentUser = this.getCurrentUser();
			cadreCheck.setDept(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			cadreCheck.setDept(CommonUtils.getSearchId(deptNO));
		}
		// 查询并返回
		return cadreCheckService.query(cadreCheck, page, rows);
	}
	
	/**
	 * 增加干部任命申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("addCadreCheck.do")
	public AjaxObj insertCadreCheck(CadreCheckVO cadreCheck) {
		UserVO currentUser = this.getCurrentUser();
		return cadreCheckService.insertCadreCheck(cadreCheck, currentUser);
	}
	
	/**
	 * 删除干部任命申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("deleteCadreCheck.do")
	public AjaxObj delete(String id) {
		return cadreCheckService.deleteCadreCheck(id);
	}
	
	/**
	 * 更新干部任命申请单
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param emp
	 * @return
	 */
	@RequestMapping("updateCadreCheck.do")
	public AjaxObj updateCadreCheck(CadreCheckVO cadreCheck) {
		return cadreCheckService.updateCadreCheck(cadreCheck);
	}
}
