package com.ustcsoft.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ustcsoft.jt.mybatis.Page;
import com.ustcsoft.system.model.CadreAppointRecordVO;
import com.ustcsoft.system.model.FamilyVO;

public interface CadreAppointRecordMapper {

	/**
	 * 带分页的查询干部任命申请单信息
	 * @return
	 */
	List<CadreAppointRecordVO> queryWithPage(@Param("cadreAppointRecord")CadreAppointRecordVO cadreAppointRecord, @Param("page") Page<CadreAppointRecordVO> page);
    
	/**
	 * 新增干部任命信息记录
	 * @return
	 */
	void insertCadreAppointRecord(CadreAppointRecordVO cadreAppointRecord);
	
	/**
	 * 删除干部任命信息记录
	 * @author 谈健
	 * @since 2017年1月10日
	 * @param ids
	 */
	void deleteCadreAppointRecord(List<String> ids);
	
	/**
	 * 修改干部任命信息记录
	 * @return
	 * */
	void updateCadreAppointRecord(CadreAppointRecordVO cadreAppointRecord);
    /**
     * 新增家庭成员
     * @param familyVO
     */
	void insertJiaTingChengYuan(FamilyVO familyVO);
    /**
     * 修改家庭成员
     * @param familyVO
     */
	void updateJiaTingChengYuan(FamilyVO familyVO);
   /**
    * 查询人员家庭成员
    * @param empId
    * @param pageVO
    * @return
    */
	List<FamilyVO> findJiaTingChengYuanPage(@Param("empId")String empId, @Param("page")Page<FamilyVO> page);
	/**
	 * 删除人员家庭成员
	 * @param id
	 */
	void deleteJiaTingChengYuan(String id);
    /**
     * 根据ID查询干部任命信息
     * @param cadreAppointApply
     * @return
     */
	CadreAppointRecordVO findCaderAppointById(CadreAppointRecordVO cadreAppointApply);
	   /**
	    * 查询人员家庭成员
	    * @param empId
	    * @param pageVO
	    * @return
	    */
	List<FamilyVO> findJiaTingChengYuan(String empId);
	 
}
