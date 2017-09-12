package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.PositionTrainRecordMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.PositionTrainRecordService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreCheckVO;
import com.ustcsoft.system.model.PositionTrainRecordVO;

@Service
public class PositionTrainRecordServiceImpl implements PositionTrainRecordService {

	@Resource
	private PositionTrainRecordMapper positionTrainRecordMapper;


	/**
	 * 分页查询所有职称培训记录信息
	 * 
	 */
	@Override
	public Page<PositionTrainRecordVO> query(PositionTrainRecordVO positionTrainRecord, int page, int rows) {
		Page<PositionTrainRecordVO> pageVO = Page.buildPageRequest(page, rows);
		List<PositionTrainRecordVO> list = positionTrainRecordMapper.queryWithPage(positionTrainRecord, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 添加职称培训记录
	 * 
	 */
	public AjaxObj insertPositionTrainRecord(PositionTrainRecordVO positionTrainRecord, UserVO user){
		//生成主键Id
		String positionTrainRecordId = UUID.randomUUID().toString().replace("-", "");
		positionTrainRecord.setPositionTrainId(positionTrainRecordId);;
		// 设置创建人
		positionTrainRecord.setCreatePer(user.getUserName());
		// 设置创建时间
		positionTrainRecord.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 正式添加
		positionTrainRecordMapper.insertPositionTrainRecord(positionTrainRecord);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setId(positionTrainRecordId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 根据id删除职称培训记录（以,分割）
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deletePositionTrainRecord(String id) {
		// 组装list参数
		List<String> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = Arrays.asList(id.split(","));
		}
		// 在根据id删除职称培训记录基本信息
		positionTrainRecordMapper.deletePositionTrainRecord(list);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	/**
	 * 更新职称培训记录
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param positionTrainRecord
	 * @return
	 */
	@Override
	public AjaxObj updatePositionTrainRecord(PositionTrainRecordVO positionTrainRecord) {
		// 正式更新
		positionTrainRecordMapper.updatePositionTrainRecord(positionTrainRecord);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}

}
