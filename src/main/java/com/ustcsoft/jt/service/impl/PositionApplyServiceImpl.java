package com.ustcsoft.jt.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.constants.Constants;
import com.ustcsoft.jt.mapper.PositionApplyMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.PositionApplyService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.PositionApplyVO;

@Service
public class PositionApplyServiceImpl implements PositionApplyService {

	@Resource
	private PositionApplyMapper positionApplyMapper;

	/**
	 * 分页查询所有职称申请单信息
	 * 
	 */
	@Override
	public Page<PositionApplyVO> query(PositionApplyVO positionApply, int page, int rows) {
		Page<PositionApplyVO> pageVO = Page.buildPageRequest(page, rows);
		List<PositionApplyVO> list = positionApplyMapper.queryWithPage(positionApply, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 添加职称申请单
	 * 
	 */
	public AjaxObj insertPositionApply(PositionApplyVO positionApply, UserVO user){
		//生成主键Id
		String headId = UUID.randomUUID().toString().replace("-", "");
		positionApply.setHeadId(headId);
		// 设置创建人
		positionApply.setCreatePer(user.getUserName());
		// 设置创建时间
		positionApply.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		positionApply.setAppStatus(Constants.POSITION_APPLY_STATUS_SAVE);
		// 正式添加(head表)
		positionApplyMapper.insertPositionApplyHead(positionApply);
		//生成detail表主键Id
		String detailId = UUID.randomUUID().toString().replace("-", "");
		positionApply.setDetailId(detailId);
		// 正式添加detail表
		positionApplyMapper.insertPositionApplyDetail(positionApply);;
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setId(headId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 更新职称申请单
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param positionApply
	 * @return
	 */
	@Override
	public AjaxObj updatePositionApply(PositionApplyVO positionApply) {
		// 正式更新
		positionApplyMapper.updatePositionApply(positionApply);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 根据id删除职称申请单（以,分割）
	 * @author 谈健
	 * @since 2017年1月13日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deletePositionApply(String headId) {
		// 在根据id删除申请单（head表）
		positionApplyMapper.deletePositionApplyHead(headId);
		// 在根据id删除申请单（detail表）
		positionApplyMapper.deletePositionApplyDetail(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	/**
	 * 提交职称申请
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param String
	 * @return
	 */
	@Override
	public AjaxObj submitPositionApply(String headId) {
		// 正式更新
		positionApplyMapper.submitPositionApply(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 审核
	 * @author 谈健
	 * @since 2017年1月19日
	 * @param headId
	 * @return
	 */
	@Override
	public AjaxObj verifyPositionApply(String headId, UserVO user) {
		PositionApplyVO positionApply =new PositionApplyVO();
		positionApply.setHeadId(headId);
		positionApply.setVerifyPer(user.getUserName());
		positionApply.setAppStatus(Constants.POSITION_APPLY_STATUS_VERIFY);
		positionApply.setVerifyDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 正式更新
		positionApplyMapper.verifyPositionApply(positionApply);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
}
