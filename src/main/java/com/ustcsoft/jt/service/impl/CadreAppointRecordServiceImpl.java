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
import com.ustcsoft.jt.mapper.CadreAppointRecordMapper;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.jt.service.CadreAppointRecordService;
import com.ustcsoft.jt.util.DateUtil;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.FamilyVO;

@Service
public class CadreAppointRecordServiceImpl implements CadreAppointRecordService {

	@Resource
	private CadreAppointRecordMapper cadreAppointRecordMapper;

	/**
	 * 分页查询所有干部任命申请单信息
	 * 
	 */
	@Override
	public Page<CadreAppointRecordVO> query(CadreAppointRecordVO cadreAppointRecord, int page, int rows) {
		Page<CadreAppointRecordVO> pageVO = Page.buildPageRequest(page, rows);
		List<CadreAppointRecordVO> list = cadreAppointRecordMapper.queryWithPage(cadreAppointRecord, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	/**
	 * 添加干部任命申请单
	 * 
	 */
	public AjaxObj insertCadreAppointRecord(CadreAppointRecordVO cadreAppointRecord, UserVO user){
		//生成主键Id
		String cadreAppiontApplyId = UUID.randomUUID().toString();
		cadreAppointRecord.setApplyId(cadreAppiontApplyId);
		// 设置创建人
		cadreAppointRecord.setCreater(user.getUserName());
		// 设置创建时间
		cadreAppointRecord.setCreateDate(DateUtil.format("yyyy-MM-dd HH:mm:ss", new Date()));
		// 正式添加
		cadreAppointRecordMapper.insertCadreAppointRecord(cadreAppointRecord);;
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setId(cadreAppiontApplyId);
		obj.setMsg("添加成功");
		return obj;
	}
	
	/**
	 * 更新干部任命申请单
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param cadreAppointApply
	 * @return
	 */
	@Override
	public AjaxObj updateCadreAppointRecord(CadreAppointRecordVO cadreAppointApply) {
		// 正式更新
		cadreAppointRecordMapper.updateCadreAppointRecord(cadreAppointApply);
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("更新成功");
		return obj;
	}
	
	/**
	 * 根据id删除干部任命申请单（以,分割）
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	@Override
	public AjaxObj deleteCadreAppointRecord(String id) {
		// 组装list参数
		List<String> list = null;
		if (StringUtils.isNotEmpty(id)) {
			list = Arrays.asList(id.split(","));
		}
		// 在根据id删除人员基本信息
		cadreAppointRecordMapper.deleteCadreAppointRecord(list);
		
		AjaxObj obj = new AjaxObj();
		obj.setCode(0);
		obj.setMsg("删除成功");
		return obj;
	}
	
	@Override
	public String saveJiaTingChengYuan(FamilyVO familyVO){
		if(!StringUtils.isNotEmpty(familyVO.getId())||("0").equals(familyVO.getId())){
			familyVO.setId(UUID.randomUUID().toString());
			familyVO.setCreateTime(new Date());
			cadreAppointRecordMapper.insertJiaTingChengYuan(familyVO);
		}else{
			cadreAppointRecordMapper.updateJiaTingChengYuan(familyVO);
		}
		return "0";
	}
	
	@Override
	public String deleteJiaTingChengYuan(String id){
		cadreAppointRecordMapper.deleteJiaTingChengYuan(id);
		return "0";
	}
	@Override
	public Page<FamilyVO> findJiaTingChengYuan(String empId, int page, int rows){
		Page<FamilyVO> pageVO = Page.buildPageRequest(page, rows);
		List<FamilyVO> list = cadreAppointRecordMapper.findJiaTingChengYuanPage(empId, pageVO);
		pageVO.setItems(list);
		return pageVO;
	}
	@Override
	public List<FamilyVO> findJiaTingChengYuan(String empId){
		List<FamilyVO> list = cadreAppointRecordMapper.findJiaTingChengYuan(empId);
		return list;
	}
	@Override
	public CadreAppointRecordVO findCaderAppointById(CadreAppointRecordVO cadreAppointApply){
		CadreAppointRecordVO vo = cadreAppointRecordMapper.findCaderAppointById(cadreAppointApply);
		return vo;
	}
}
