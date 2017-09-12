package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreReserveService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreCheckVO;
import com.ustcsoft.system.model.CadreReserveVO;

/**
 * 后备干部管理的rest控制器
 * 
 * @author 吴金华
 * @since 2017年1月4日
 */
@RequestMapping("cadreReserve")
@RestController
public class CadreReserveRestController extends AbstractRestController {
	@Resource
	private CadreReserveService cadreReserveService;

	/**
	 * 分页查询干部任命申请单信息
	 * 
	 * 
	 */
	@RequestMapping("findCadreReserveList.do")
	public Page<CadreReserveVO> findCadreReserveList(
			String empName, 
			String dept, 
			String recommendPersonName,
			String recommendDept,
			String recommendDateStart,  
			String recommendDateEnd,
			String appStatus,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("cadreReserve/findCadreReserveList.do");
		// 初始化查询条件
		CadreReserveVO cadreReserve = new CadreReserveVO();
		cadreReserve.setEmpName(empName);
		cadreReserve.setRecommendPersonName(recommendPersonName);
		cadreReserve.setRecommendDeptNO(recommendDept);
		cadreReserve.setRecommendDateStart(recommendDateStart);
		cadreReserve.setRecommendDateEnd(recommendDateEnd);
		cadreReserve.setAppStatus(appStatus);
		if(dept==null){
			UserVO currentUser = this.getCurrentUser();
			cadreReserve.setDept(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			cadreReserve.setDept(CommonUtils.getSearchId(dept));
		}
		
		// 查询并返回
		return cadreReserveService.query(cadreReserve, page, rows);
	}
	
	/**
	 * 增加后备干部申请
	 * 
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping("addCadreReserve.do")
	public AjaxObj insertCadreReserve(CadreReserveVO cadreReserve) {
		UserVO currentUser = this.getCurrentUser();
		return cadreReserveService.insertCadreReserve(cadreReserve, currentUser);
	}
	
	/**
	 * 更新后备干部
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param cadreReserve
	 * @return
	 */
	@RequestMapping("updateCadreReserve.do")
	public AjaxObj updateCadreReserve(CadreReserveVO cadreReserve) {
		return cadreReserveService.updateCadreReserve(cadreReserve);
	}
	
	/**
	 * 删除后备干部
	 * 
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping("deleteCadreReserve.do")
	public AjaxObj delete(@RequestParam(value = "headId")String id) {
		return cadreReserveService.deleteCadreReserve(id);
	}
	
	/**
	 * 提交后备干部
	 * 
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping("submitCadreReserve.do")
	public AjaxObj submitCadreReserve(@RequestParam(value = "headId")String id) {
		return cadreReserveService.submitCadreReserve(id);
	}
	
	/**
	 * 审批
	 * @author 谈健
	 * @since 2017年1月19日
	 * @param applyId
	 * @return
	 */
	@RequestMapping("approvCadreReserve.do")
	public AjaxObj approvCadreReserve(@RequestParam(value = "applyId") String applyId) {
		UserVO currentUser = this.getCurrentUser();
		return cadreReserveService.approvCadreReserve(applyId,currentUser);
	}
	
	/**
	 * 不予审批
	 * @author 谈健
	 * @since 2017年2月23日
	 * @param cadreReserve
	 * @return
	 */
	@RequestMapping("denyCadreReserve.do")
	public AjaxObj denyCadreReserve( CadreReserveVO cadreReserve) {
		UserVO currentUser = this.getCurrentUser();
		return cadreReserveService.denyCadreReserve(cadreReserve,currentUser);
	}
}
