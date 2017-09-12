package com.ustcsoft.jt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.constants.Constants;
import com.ustcsoft.jt.mapper.CadreReserveMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreReserveService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreReserveVO;

@Service
public class CadreReserveServiceImpl implements CadreReserveService {

	@Resource
	private CadreReserveMapper cadreReserveMapper;


	/**
	 * 分页查询所有后备干部申请
	 * 
	 */
	@Override
	public Page<CadreReserveVO> query(CadreReserveVO cadreReserve, int page, int rows) {
		Page<CadreReserveVO> pageVO = Page.buildPageRequest(page, rows);
		List<CadreReserveVO> list = cadreReserveMapper.queryWithPage(cadreReserve, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	
	/**
	 * 添加后备干部申请
	 * 
	 */
	public AjaxObj insertCadreReserve(CadreReserveVO cadreReserve, UserVO user){
		//生成head表主键Id
		String headId = UUID.randomUUID().toString().replace("-", "");
		cadreReserve.setHeadId(headId);
		// 设置创建人
		cadreReserve.setCreatePer(user.getUserName());
		// 设置创建时间
		cadreReserve.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		cadreReserve.setAppStatus(Constants.CADRE_RESERVE_APPLY_STATUS_SAVE);
		// 正式添加head表
		cadreReserveMapper.insertCadreReserveHead(cadreReserve);;
		//生成detail表主键Id
		String detailId = UUID.randomUUID().toString().replace("-", "");
		cadreReserve.setDetailId(detailId);
		// 正式添加detail表
		cadreReserveMapper.insertCadreReserveDetail(cadreReserve);;
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setId(headId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 根据id删除后备干部申请
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deleteCadreReserve(String headId) {
		// 在根据id删除申请单
		cadreReserveMapper.deleteCadreReserveHead(headId);
		cadreReserveMapper.deleteCadreReserveDetail(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
   
	/**
	 * 更新后备干部申请
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param CadreReserveVO
	 * @return
	 */
	@Override
	public AjaxObj updateCadreReserve(CadreReserveVO cadreReserve) {
		// 正式更新
		cadreReserveMapper.updateCadreReserve(cadreReserve);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 提交后备干部申请
	 * @author 谈健
	 * @since 2017年1月11日
	 * @param CadreReserveVO
	 * @return
	 */
	@Override
	public AjaxObj submitCadreReserve(String headId) {
		// 正式更新
		cadreReserveMapper.submitCadreReserve(headId);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 审批
	 * @author 谈健
	 * @since 2017年1月19日
	 * @param applyId
	 * @return
	 */
	@Override
	public AjaxObj approvCadreReserve(String applyId, UserVO user) {
		CadreReserveVO cadreReserve =new CadreReserveVO();
		cadreReserve.setHeadId(applyId);
		cadreReserve.setVerifyPer(user.getUserName());
		cadreReserve.setAppStatus(Constants.CADRE_RESERVE_APPLY_STATUS_APPROVE);
		cadreReserve.setVerifyDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 正式更新
		cadreReserveMapper.approvCadreReserve(cadreReserve);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 不予审批
	 * @author 谈健
	 * @since 2017年2月23日
	 * @param cadreReserve
	 * @return
	 */
	@Override
	public AjaxObj denyCadreReserve(CadreReserveVO cadreReserve, UserVO user) {
		cadreReserve.setVerifyPer(user.getUserName());
		cadreReserve.setAppStatus(Constants.CADRE_RESERVE_APPLY_STATUS_UNPASS);
		cadreReserve.setVerifyDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 修改申请状态
		cadreReserveMapper.updateAppStatus(cadreReserve);
		//跟新审批不通过原因
		cadreReserveMapper.updateFailedReason(cadreReserve);
		//录入后备人才库
		//生成后备人才库主键Id
		String cadreReserveId = UUID.randomUUID().toString().replace("-", "");
		//设置主键id
		cadreReserve.setReserverId(cadreReserveId);
		// 设置创建人
		cadreReserve.setCreatePer(user.getUserName());
		// 设置创建时间
		cadreReserve.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		//（录入后备干部人才库）
		cadreReserveMapper.denyCadreReserve(cadreReserve);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("不予审批成功");
		return obj;
	}
}
