package com.ustcsoft.jt.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mapper.SysLogManageMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.SysLogManageService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.PositionApplyVO;
import com.ustcsoft.system.model.SysLogManageVO;

@Service
public class SysLogManageServiceImpl implements SysLogManageService {

	@Resource
	private SysLogManageMapper sysLogManageMapper;


	/**
	 * 分页查询所有系统操作日志
	 * 
	 */
	@Override
	public Page<SysLogManageVO> query(SysLogManageVO sysLogManage, int page, int rows) {
		Page<SysLogManageVO> pageVO = Page.buildPageRequest(page, rows);
		List<SysLogManageVO> list = sysLogManageMapper.queryWithPage(sysLogManage, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	@Override
	public List<SysLogManageVO> find(SysLogManageVO sysLogManage){
		List<SysLogManageVO> list = sysLogManageMapper.findLog(sysLogManage);
		return list;
	}
	
	/**
	 * 录入系统日志
	 * 
	 */
	public AjaxObj insertSysLog(SysLogManageVO sysLogManage, UserVO user){
		//设置操作时间					   
		sysLogManage.setCreateTime(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		//设置操作人姓名
		sysLogManage.setOperatorName(user.getUserName());
		//设置操作人
		sysLogManage.setOperatorId(user.getUserId()+"");
		sysLogManage.setOperateContent(sysLogManage.getOperatorName()+"在"+sysLogManage.getOperateMenu()+sysLogManage.getOperateType()+sysLogManage.getObject());
		//插入系统日志表
		sysLogManageMapper.insertSysLog(sysLogManage);
		AjaxObj ao = new AjaxObj();
		ao.setCode(0);
		ao.setMsg("添加成功");
		return ao;
	}
}
