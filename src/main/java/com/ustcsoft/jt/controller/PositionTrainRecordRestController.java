package com.ustcsoft.jt.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.PositionTrainRecordService;
import com.ustcsoft.jt.util.CommonUtils;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.PositionTrainRecordVO;

/**
 * 职称培训记录的rest控制器
 * 
 * @author 谈健
 * @since 2017年1月13日
 */
@RequestMapping("positionTrainRecord")
@RestController
public class PositionTrainRecordRestController extends AbstractRestController {
	@Resource
	private PositionTrainRecordService positionTrainRecordService;

	/**
	 * 分页查询职称培训记录
	 * 
	 * 
	 */
	@RequestMapping("searchPositionTrainRecord.do")
	public Page<PositionTrainRecordVO> searchPositionTrainRecordList(
			String empName, 
			String trainDateStart,  
			String trainDateEnd,
			String empId,
			String deptNO,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows)
			throws Exception {
		logger.info("positionTrainRecord/searchPositionTrainRecord.do");
		
		// 初始化查询条件
		PositionTrainRecordVO positionTrainRecordVO = new PositionTrainRecordVO();
		positionTrainRecordVO.setEmpName(empName);
		positionTrainRecordVO.setTrainDateStart(trainDateStart);
		positionTrainRecordVO.setTrainDateEnd(trainDateEnd);
		positionTrainRecordVO.setEmpId(empId);
		if(deptNO==null){
			UserVO currentUser = this.getCurrentUser();
			positionTrainRecordVO.setDept(CommonUtils.getSearchId(currentUser.getBusinessId()));
		}else{
			positionTrainRecordVO.setDept(CommonUtils.getSearchId(deptNO));
		}
		// 查询并返回
		return positionTrainRecordService.query(positionTrainRecordVO, page, rows);
	}
	
	/**
	 * 增加职称培训记录
	 * 
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param 
	 * @return
	 */
	@RequestMapping("insertPositionTrainRecord.do")
	public AjaxObj insertCadreCheck(PositionTrainRecordVO positionTrainRecord) {
		UserVO currentUser = this.getCurrentUser();
		logger.info("positionTrainRecord/insertPositionTrainRecord.do");
		return positionTrainRecordService.insertPositionTrainRecord(positionTrainRecord, currentUser);
	}
	
	/**
	 * 删除职称培训记录
	 * 
	 * @author 谈健
	 * @since 2017年1月16日
	 * @param id
	 * @return
	 */
	@RequestMapping("deletePositionTrainRecord.do")
	public AjaxObj deletePositionTrainRecord(String id) {
		return positionTrainRecordService.deletePositionTrainRecord(id);
	}
	
	/**
	 * 更新职称培训记录
	 * @author 谈健
	 * @since 2017年1月16日
	 * @param positionTrainRecord
	 * @return
	 */
	@RequestMapping("updatePositionTrainRecord.do")
	public AjaxObj updatePositionTrainRecord(PositionTrainRecordVO positionTrainRecord) {
		return positionTrainRecordService.updatePositionTrainRecord(positionTrainRecord);
	}
}
