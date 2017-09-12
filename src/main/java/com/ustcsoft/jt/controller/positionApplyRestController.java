package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.PositionApplyService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.PositionApplyVO;

/**
 * 职称申请的rest控制器
 * 
 * @author 谈健
 * @since 2017年1月12日
 */
@RequestMapping("positionApply")
@RestController
public class positionApplyRestController extends AbstractRestController {
	@Resource
	private PositionApplyService positionApplyService;

	/**
	 * 分页查询干部任命申请单信息
	 * 
	 * 
	 */
	@RequestMapping("findPositionApplyList.do")
	public Page<PositionApplyVO> findCadreAppointApplyList(
			String applyEmpName, 
			String dept, 
			String applyDateStart,  
			String applyDateEnd,
			String appPositionId,
			String appStatus,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("positionApply/findPositionApplyList.do");
		// 初始化查询条件
		PositionApplyVO positionApply = new PositionApplyVO();
		positionApply.setApplyEmpName(applyEmpName);
		positionApply.setApplyDateStart(applyDateStart);
		positionApply.setApplyDateEnd(applyDateEnd);
		positionApply.setAppPositionId(appPositionId);
		positionApply.setAppStatus(appStatus);
		if(dept==null){
			UserVO currentUser = this.getCurrentUser();
			positionApply.setDept(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			positionApply.setDept(CommonUtils.getSearchId(dept));
		}
		// 查询并返回
		return positionApplyService.query(positionApply, page, rows);
	}
	
	/**
	 * 增加职称申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param 
	 * @return
	 */
	@RequestMapping("addPositionApply.do")
	public AjaxObj insertPositionApply(PositionApplyVO positionApply) {
		UserVO currentUser = this.getCurrentUser();
		return positionApplyService.insertPositionApply(positionApply, currentUser);
	}
	
	/**
	 * 删除职称申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param 
	 * @return
	 */
	@RequestMapping("deletePositionApply.do")
	public AjaxObj delete(@RequestParam(value = "headId")String id) {
		return positionApplyService.deletePositionApply(id);
	}
	
	/**
	 * 更新职称申请单
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param positionApply
	 * @return
	 */
	@RequestMapping("updatePositionApply.do")
	public AjaxObj updatePositionApply(PositionApplyVO positionApply) {
		return positionApplyService.updatePositionApply(positionApply);
	}
	
	/**
	 * 提交职称申请单
	 * 
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping("submitPositionApply.do")
	public AjaxObj submitCadreReserve(@RequestParam(value = "headId")String id) {
		return positionApplyService.submitPositionApply(id);
	}
	
	/**
	 * 审核职称申请单
	 * @author 谈健
	 * @since 2017年1月19日
	 * @param applyId
	 * @return
	 */
	@RequestMapping("verifyPositionApply.do")
	public AjaxObj verifyPositionApply(@RequestParam(value = "headId")String headId) {
		UserVO currentUser = this.getCurrentUser();
		return positionApplyService.verifyPositionApply(headId,currentUser);
	}
	
}
