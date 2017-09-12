package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.CadreCheckVO;
import com.ustcsoft.system.model.PositionTrainRecordVO;

public interface PositionTrainRecordMapper {

	/**
	 * 带分页的查询职称培训记录
	 * @return
	 */
	List<PositionTrainRecordVO> queryWithPage(@Param("positionTrainRecord") PositionTrainRecordVO positionTrainRecord, @Param("page") Page<PositionTrainRecordVO> page);
    
	/**
	 * 新增职称培训记录
	 * @return
	 */
	void insertPositionTrainRecord(PositionTrainRecordVO positionTrainRecord);
	
	/**
	 * 删除职称培训记录
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param ids
	 */
	void deletePositionTrainRecord(List<String> ids);
	
	/**
	 * 更新职称培训记录
	 * @return
	 */
	void updatePositionTrainRecord(PositionTrainRecordVO positionTrainRecord);
}
