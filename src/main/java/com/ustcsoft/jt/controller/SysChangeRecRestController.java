package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.apache.poi.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysOrgMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysChangeRecService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysChangeRecVO;

/**
 * 人员异动信息的rest控制器
 * @author  吴金华
 * @since   2017年1月10日
 */
@RequestMapping("sysChangeRec")
@RestController
public class SysChangeRecRestController extends AbstractRestController {
	@Resource
	private SysChangeRecService sysChangeRecService;
	
	/**
	 * 分页查询所有人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param typeId
	 * @param changeDateStart
	 * @param changeDateEnd
	 * @param oldDeptNo
	 * @param newDeptNo
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findChangeRecList.do")
	public Page<SysChangeRecVO> findChangeRecList(
			String typeId, 
			String changeDateStart, 
			String changeDateEnd,  
			String oldDeptNo,
			String newDeptNo,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysChangeRec/findChangeRecList.do");

		// 初始化查询条件
		SysChangeRecVO changeRecVO = new SysChangeRecVO();
		changeRecVO.setTypeId(typeId);
		changeRecVO.setChangeDateStart(changeDateStart);
		changeRecVO.setChangeDateEnd(changeDateEnd);
		changeRecVO.setOldDeptNo(oldDeptNo);
		changeRecVO.setNewDeptNo(newDeptNo);

		// 查询并返回
		return sysChangeRecService.query(changeRecVO, page, rows);
	}
	
	/**
	 * 分页查询所有人员异动申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param typeId
	 * @param changeDateStart
	 * @param changeDateEnd
	 * @param oldDeptNo
	 * @param newDeptNo
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("findChangeRecApplyList.do")
	public Page<SysChangeRecVO> findChangeRecApplyList(
			String typeId, 
			String changeDateStart, 
			String changeDateEnd,  
			String oldDeptNo,
			String newDeptNo,
			String status,
			String name,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("sysChangeRec/findChangeRecApplyList.do");
		
		// 初始化查询条件
		SysChangeRecVO changeRecVO = new SysChangeRecVO();
		changeRecVO.setTypeId(typeId);
		changeRecVO.setChangeDateStart(changeDateStart);
		changeRecVO.setChangeDateEnd(changeDateEnd);
		changeRecVO.setOldDeptNo(CommonUtils.getSearchId(oldDeptNo));
		changeRecVO.setNewDeptNo(newDeptNo);
		changeRecVO.setStatus(status);
		changeRecVO.setName(name);
		//如果未选择查看部门或单位，则默认查看用户所在单位
		if(oldDeptNo==null){
			UserVO currentUser = this.getCurrentUser();
			changeRecVO.setOrgId(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}
		// 查询并返回
		return sysChangeRecService.queryApplyList(changeRecVO, page, rows);
	}
	
	/**
	 * 录入一条异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param sysChangeRec
	 * @return
	 */
	@RequestMapping("record.do")
	public AjaxObj record(SysChangeRecVO sysChangeRec) {
		UserVO currentUser = this.getCurrentUser();
		// 调用录入服务
		return sysChangeRecService.record(sysChangeRec, currentUser);
	}
	
	/**
	 * 异动信息申请
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param sysChangeRec
	 * @return
	 */
	@RequestMapping("apply.do")
	public AjaxObj apply(SysChangeRecVO sysChangeRec) {
		UserVO currentUser = this.getCurrentUser();
		// 调用录入服务
		return sysChangeRecService.apply(sysChangeRec, currentUser);
	}
	
	/**
	 * 加载一条人员异动信息
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("load.do")
	public SysChangeRecVO load(String id) {
		return sysChangeRecService.load(id);
	}
	
	/**
	 * 加载部门名称
	 * @author 吴金华
	 * @since 2017年1月10日
	 * @param id
	 * @return
	 */
	@RequestMapping("loadDeptName.do")
	public SysChangeRecVO loadDeptName(String id) {
		return sysChangeRecService.loadDeptName(id);
	}
	
	/**
	 * 删除人员异动申请
	 * 
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param 
	 * @return
	 */
	@RequestMapping("deleteChangeRec.do")
	public AjaxObj delete(String id) {
		return sysChangeRecService.deleteChangeRec(id);
	}
	
	/**
	 * 提交人员异动申请
	 * 
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param 
	 * @return
	 */
	@RequestMapping("submitChangeRec.do")
	public AjaxObj submitChangeRec(@RequestParam(value = "headId")String id) {
		return sysChangeRecService.submitChangeRec(id);
	}
	
	/**
	 * 更新职称申请单
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param positionApply
	 * @return
	 */
	@RequestMapping("updateChangeRec.do")
	public AjaxObj updateChangeRec(SysChangeRecVO sysChangeRec) {
		return sysChangeRecService.updateChangeRec(sysChangeRec);
	}
}
