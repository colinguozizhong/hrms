package com.ustcsoft.jt.service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreCheckVO;
import com.ustcsoft.system.model.PositionTrainRecordVO;

public interface PositionTrainRecordService {
	/**
	 * 分页查询所有职称培训记录
	 * 
	 */
	Page<PositionTrainRecordVO> query(PositionTrainRecordVO positionTrainRecord, int page, int rows);
	
	/**
	 * 添加职称培训记录
	 * 
	 */
	AjaxObj insertPositionTrainRecord(PositionTrainRecordVO positionTrainRecord, UserVO user);
	
	/**
	 * 修改职称培训记录
	 * 
	 */
	AjaxObj updatePositionTrainRecord(PositionTrainRecordVO positionTrainRecord);
	
	/**
	 * 根据id删除职称培训记录（以,分割）
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param id
	 * @return
	 */
	AjaxObj deletePositionTrainRecord(String id);
}
