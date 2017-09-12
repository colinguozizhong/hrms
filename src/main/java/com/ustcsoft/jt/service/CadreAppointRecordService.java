package com.ustcsoft.jt.service;

import java.util.List;

import com.ustcsoft.framework.vo.UserVO;
import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.AjaxObj;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.FamilyVO;
import com.ustcsoft.system.model.SystemEmpVO;

public interface CadreAppointRecordService {
	/**
	 * 分页查询所有干部任命申请单信息
	 * 
	 */
	Page<CadreAppointRecordVO> query(CadreAppointRecordVO CadreAppointRecord, int page, int rows);
	
	/**
	 * 添加干部任命申请单
	 * 
	 */
	AjaxObj insertCadreAppointRecord(CadreAppointRecordVO CadreAppointRecord, UserVO user);
	
	/**
	 * 修改干部任命申请单
	 * 
	 */
	AjaxObj updateCadreAppointRecord(CadreAppointRecordVO CadreAppointRecord);
	
	/**
	 * 根据id删除干部任命申请单（以,分割）
	 * @author 谈健
	 * @since 2017年1月5日
	 * @param id
	 * @return
	 */
	AjaxObj deleteCadreAppointRecord(String id);
  /**
   *  新增家庭成员
   * @param familyVO
   * @return
   */
	String saveJiaTingChengYuan(FamilyVO familyVO);
	/**
	 * 查询人员家庭成员
	 * @param empId
	 * @param page
	 * @param rows
	 * @return
	 */
	Page<FamilyVO> findJiaTingChengYuan(String empId, int page, int rows);
	/**
	 *  删除 家庭成员
	 * @param id
	 * @return
	 */
	String deleteJiaTingChengYuan(String id);
    /**
     * 根据id查询干部任命信息
     * @param cadreAppointApply
     * @return
     */
	CadreAppointRecordVO findCaderAppointById(CadreAppointRecordVO cadreAppointApply);
	/**
	 * 查询人员家庭成员
	 * @param empId
	 * @return
	 */
	List<FamilyVO> findJiaTingChengYuan(String empId);
}
